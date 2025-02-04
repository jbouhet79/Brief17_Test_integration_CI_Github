package com.example.brief17;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.example.brief17.entity.Student;
import com.example.brief17.service.StudentService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

// TODO: Ajouter les tags nécessaires pour charger H2, charger le profil de test et importer le StudentService
@DataJpaTest // Charge la BDD H2 en mémoire pour les tests et JPA
@ActiveProfiles("test") //  Pour spécifier que le profil de test doit être actif lors de l'exécution de ce test.
@Import(StudentService.class) // Importer le StudentService
class StudentServiceIntegrationTest {

    @Autowired 
    private StudentService studentService; // Injecte StudentService pour effectuer des opérations.

    @Test
    void shouldSaveAndRetrieveStudent() {
        // TODO: Implémenter le test d'intégration, insérer un Student en base de données et le récupérer
        // Création d'un étudiant
        Student student = new Student();
        student.setName("Alice");
        student.setAddress("123 Avenue de Paris 79000 NIORT");

        // Sauvegarde en base
        Student savedStudent = studentService.saveStudent(student);

        // Vérification que l'ID est bien généré
        assertThat(savedStudent.getId()).isNotNull();

        // Récupération par ID
        Optional<Student> retrievedStudent = studentService.findStudentById(savedStudent.getId());

        // Vérification que les données sont correctes
        assertThat(retrievedStudent).isPresent();
        assertThat(retrievedStudent.get().getName()).isEqualTo("Alice");
        assertThat(retrievedStudent.get().getAddress()).isEqualTo("123 Avenue de Paris 79000 NIORT");
    }
}