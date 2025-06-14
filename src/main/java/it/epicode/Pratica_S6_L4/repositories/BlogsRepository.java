package it.epicode.Pratica_S6_L4.repositories;


import it.epicode.Pratica_S6_L4.models.Author;
import it.epicode.Pratica_S6_L4.models.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogsRepository extends JpaRepository<Blogpost, Integer> {
    List<Blogpost> findByAuthor(Author author);
}
