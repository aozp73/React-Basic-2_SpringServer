package com.example.book.web;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@Transactional // Test에서는 각 테스트 종료 후, 자동 rollback
@AutoConfigureMockMvc // MockMvc IoC 등록
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BookControllerUnitTest {
  
    @Autowired
    private MockMvc mockMvc;
}
