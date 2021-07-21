package com.bk.learn.catalogservice.web;

import java.time.Year;
import com.bk.learn.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        Book book =     new Book("1234567892", "Polar Journey", "Iorek Polarson", Year.of(1993), 12.90, "Polar");

        assertThat(json.write(book)).extractingJsonPathStringValue("@.isbn")
                .isEqualTo("1234567892");
        assertThat(json.write(book)).extractingJsonPathStringValue("@.title")
                .isEqualTo("Polar Journey");
        assertThat(json.write(book)).extractingJsonPathStringValue("@.author")
                .isEqualTo("Iorek Polarson");
        assertThat(json.write(book)).extractingJsonPathStringValue("@.publishingYear")
                .isEqualTo("1993");
        assertThat(json.write(book)).extractingJsonPathNumberValue("@.price")
                .isEqualTo(12.90);
        assertThat(json.write(book)).extractingJsonPathStringValue("@.publisher")
                .isEqualTo("Polar");
    }

    @Test
    void testDeserialize() throws Exception {
        String content = "{\"isbn\":\"1234567892\",\"title\":\"Polar Journey\", \"author\":\"Iorek Polarson\", \"publishingYear\":\"1993\", \"price\":12.90,\"publisher\":\"Polar\"}";
        assertThat(json.parse(content))
                .usingRecursiveComparison().isEqualTo(

                   new Book("1234567892", "Polar Journey", "Iorek Polarson", Year.of(1993), 12.90, "Polar")


        );
        assertThat(json.parseObject(content).getIsbn()).isEqualTo("1234567892");
    }
}