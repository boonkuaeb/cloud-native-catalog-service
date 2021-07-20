package com.bk.learn.catalogservice.web;

import java.time.Year;
import com.bk.learn.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenPostRequestThenBookCreated() {
        Book expectedBook = new Book("1231231231", "Title", "Author", Year.of(1991), 9.90);
        ResponseEntity<Book> response = restTemplate.postForEntity("/books", expectedBook, Book.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getIsbn()).isEqualTo(expectedBook.getIsbn());
    }
}