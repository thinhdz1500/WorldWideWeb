package com.thinne;

import com.thinne.backend.models.Post;
import com.thinne.backend.models.User;
import com.thinne.backend.repositories.PostRepository;
import com.thinne.backend.repositories.UserTableRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

@SpringBootApplication
public class LabWeek6Application {

    public static void main(String[] args) {
        SpringApplication.run(LabWeek6Application.class, args);
    }

    @Autowired
    private UserTableRepository userTableRepository;
    @Autowired
    private PostRepository postRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
//            List<User> users = new ArrayList<>();
            Faker faker = new Faker();
//            for (int i = 0; i < 10; i++) {
//                User user = new User();
//                user.setFirstName(faker.name().firstName());
//                user.setMiddleName(faker.name().nameWithMiddle());
//                user.setLastName(faker.name().lastName());
//                user.setEmail(faker.internet().emailAddress());
//                user.setMobile(faker.phoneNumber().cellPhone());
//                user.setPasswordHash(faker.internet().password());
//                System.out.println("Password: " + user.getPasswordHash());
//                System.out.println("PasswordFaker: " + faker.internet().password());
//
//                Date date = faker.date().between(new Date(2023, 10, 10), new Date(2024, 10, 10));
//                LocalDate registeredAt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                user.setRegisteredAt(registeredAt);
//                user.setLastLogin(faker.date().between(new Date(2023, 10, 10), new Date(2024, 11, 11)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//                user.setIntro(faker.lorem().sentence());
//                user.setProfile(faker.lorem().paragraph());
//                userTableRepositories.save(user);
//                users.add(user);
//            }
//            users.forEach(System.out::println);
//            for (int i = 1; i < 11; i++) {
//                for (int j = 0; j < 5; j++) {
//                    Post post = new Post();
////                    post.setId((long) i * 10 + j);
//                    User user = userTableRepository.getUserById(i);
//                    post.setAuthor(user);
//                    post.setTitle(faker.book().title());
//                    post.setMetaTitle(faker.lorem().sentence(5));
//                    post.setSummary(faker.lorem().paragraph());
//                    post.setPublished(faker.bool().bool());
//                    post.setCreatedAt(Instant.now().plusSeconds(faker.number().numberBetween(0, 3600 * 24 * 7)));
//                    post.setUpdatedAt(Instant.now().plusSeconds(faker.number().numberBetween(0, 3600 * 24 * 7)));
//                    if (post.getPublished()) {
//                        post.setPublishedAt(Instant.now().plusSeconds(faker.number().numberBetween(0, 3600 * 24 * 7)));
//                    }
//                    String content = faker.lorem().paragraph(10);
//                    if (content.length() > 20) {
//                        content = content.substring(0, 20);
//                    }
//                    post.setContent(content);
//                    postRepository.save(post);
//
//                }
//            }

        };
    }
}
