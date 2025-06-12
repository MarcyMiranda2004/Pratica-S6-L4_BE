package it.epicode.Pratica_S6_L4.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBlogPostPayload {

    @Min(value = 1, message = "L'ID dell'autore deve essere maggiore di zero")
    private int authorId;

    @NotBlank(message = "Deve esserci almeno una categoria")
    private String category;

    private String cover;

    @NotBlank(message = "Non puoi pubblicare un post vuoto")
    private String content;

    @NotBlank(message = "Devi dare un titolo al tuo post")
    private String title;

    private double readingTime;
}
