package com.bk.learn.catalogservice.domain;

import java.time.Year;
import java.util.Optional;
import com.bk.learn.catalogservice.persistence.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryJpaTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findBookByIsbnWhenExisting() {
        String bookIsbn = "1234561235";
        Book expectedBook = new Book(bookIsbn,  "Polar Journey", "Iorek Polarson", Year.of(1993), 12.90, "Polar");

        entityManager.persist(expectedBook);

        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getIsbn()).isEqualTo(expectedBook.getIsbn());
    }
}