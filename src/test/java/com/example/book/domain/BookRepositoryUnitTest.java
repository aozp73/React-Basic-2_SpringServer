package com.example.book.domain;

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
}
