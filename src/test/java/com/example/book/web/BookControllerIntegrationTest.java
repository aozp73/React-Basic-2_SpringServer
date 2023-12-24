package com.example.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.book.domain.Book;
import com.example.book.domain.BookRespository;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Autowired
    private BookRespository bookRespository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void init() {
        entityManager.createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }

    @Test
    public void save() throws Exception {
        // given
        Book book = new Book(null, "스프링 따라하기", "cos");
        String content = new ObjectMapper().writeValueAsString(book);

        // when
        ResultActions resultActions = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findall() throws Exception {
        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링 따라하기", "cos"));
        books.add(new Book(2L, "리액트 따라하기", "cos"));
        bookRespository.saveAll(books);

        // when
        ResultActions resultActions = mockMvc.perform(get("/book")
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[0].title").value("스프링 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findById() throws Exception {
        // given
        Long id = 2L;

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링 따라하기", "cos"));
        books.add(new Book(2L, "리액트 따라하기", "cos"));
        books.add(new Book(3L, "JUnit 따라하기", "cos"));
        bookRespository.saveAll(books);

        // when
        ResultActions resultActions = mockMvc.perform(get("/book/{id}", id)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("리액트 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update() throws Exception {
        // given
        Long id = 2L;

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링 따라하기", "cos"));
        books.add(new Book(2L, "리액트 따라하기", "cos"));
        books.add(new Book(3L, "JUnit 따라하기", "cos"));
        bookRespository.saveAll(books);

        Book book = new Book(null, "C++ 따라하기", "cos");
        String content = new ObjectMapper().writeValueAsString(book);

        // when
        ResultActions resultActions = mockMvc.perform(put("/book/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.title").value("C++ 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTest() throws Exception {
        // given
        Long id = 1L;

        Book book = new Book(1L, "스프링 따라하기", "cos");
        bookRespository.save(book);

        // when
        ResultActions resultActions = mockMvc.perform(delete("/book/{id}", id)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        MvcResult requesrResult = resultActions.andReturn();
        String res = requesrResult.getResponse().getContentAsString();
        assertEquals("ok", res);
    }

}
