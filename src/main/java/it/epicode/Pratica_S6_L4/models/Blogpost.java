package it.epicode.Pratica_S6_L4.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blogposts")
public class Blogpost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;
}
