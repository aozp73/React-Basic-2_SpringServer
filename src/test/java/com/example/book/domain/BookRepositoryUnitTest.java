package com.example.book.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository 단위 테스트
 * - 필요: DB 관련 Bean
 */
@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY) // 가짜 내장 DB
@DataJpaTest
public class BookRepositoryUnitTest {

    @Autowired
    private BookRespository bookRespository;

    @Test
    public void saveTest() {
        // given
        Book book = new Book(null, "제목1", "저자1");

        // when
        Book bookEntity = bookRespository.save(book);

        // then
        assertEquals("제목1", bookEntity.getTitle());
        assertEquals("저자1", bookEntity.getAuthor());
    }
}
