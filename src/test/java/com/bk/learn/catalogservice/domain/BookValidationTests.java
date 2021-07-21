package com.bk.learn.catalogservice.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Year;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        Book book1 = new Book("1234567891", "Northern Lights", "Lyra Silvertongue", Year.of(2011), 9.90, "Polar");
        Set<ConstraintViolation<Book>> violations = validator.validate(book1);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        Book book2 = new Book("12345678SS91", "Northern Lights", "Lyra Silvertongue", Year.of(2011), 9.90, "Polar");
        Set<ConstraintViolation<Book>> violations = validator.validate(book2);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must follow the standards ISBN-10 or ISBN-13.");
    }
}
