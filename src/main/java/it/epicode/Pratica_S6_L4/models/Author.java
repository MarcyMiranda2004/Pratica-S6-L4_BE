package it.epicode.Pratica_S6_L4.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
    private String avatar;

/* Per evitare lo stackoverflow error che avviene quando si manda un Author come risposta (può avvenire anche quando mandiamo un BlogPost con un Author)
   si può o togliere la bidirezionalità, oppure usare @JsonIgnore, oppure farsi un payload di risposta custom.
  @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Blogpost> blogpostList;*/
}
