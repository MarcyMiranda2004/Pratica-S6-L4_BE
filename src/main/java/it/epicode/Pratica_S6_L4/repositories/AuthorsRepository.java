package it.epicode.Pratica_S6_L4.repositories;


import it.epicode.Pratica_S6_L4.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);
}
