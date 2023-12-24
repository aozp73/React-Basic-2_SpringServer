package com.example.book.web;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

/** 
 * Controller 단위 테스트 
 * - 필요: Controller / Filter / ControllerAdvice 등
 */
@Transactional // Test에서는 각 테스트 종료 후, 자동 rollback
@AutoConfigureMockMvc // MockMvc IoC 등록
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
}
