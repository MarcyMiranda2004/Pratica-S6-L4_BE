package it.epicode.Pratica_S6_L4.services;

import com.cloudinary.Cloudinary;
import it.epicode.Pratica_S6_L4.exceptions.BadRequestException;
import it.epicode.Pratica_S6_L4.exceptions.NotFoundException;
import it.epicode.Pratica_S6_L4.models.Author;
import it.epicode.Pratica_S6_L4.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Author save(Author body) {
        authorsRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.getEmail() + " è già stata utilizzata");
        });

        //per l'avatar place holder
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getName().charAt(0) + "+" + body.getSurname().charAt(0));

        Author saved = authorsRepository.save(body);

        // email di conferma
        sendMail(saved.getEmail());

        return saved;
    }


    public Page<Author> getAuthors(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return authorsRepository.findAll(pageable);
    }

    public Author findById(int id) {
        return authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        authorsRepository.delete(found);
    }

    public Author findByIdAndUpdate(int id, Author body) {

        Author found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setDateOfBirth(body.getDateOfBirth());
        found.setAvatar(body.getAvatar());
        return authorsRepository.save(found);
    }

    public String patchAuthor(int id, MultipartFile file) throws IOException {
        Author autoreDaPatchare = findById(id); // metodo corretto
        String url = (String) cloudinary.uploader()
                .upload(file.getBytes(), Collections.emptyMap())
                .get("url");
        autoreDaPatchare.setAvatar(url); // aggiorna l'avatar
        authorsRepository.save(autoreDaPatchare);
        return url;
    }


    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");
        message.setFrom("marcellomiranda2004@gmail.com");

        javaMailSender.send(message);
    }
}
