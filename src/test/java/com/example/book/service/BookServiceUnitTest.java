package com.example.book.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.book.domain.BookRespository;

/** 
 * Service 단위 테스트 
 * - 필요: Mock Repository
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {
    
    @InjectMocks // 해당 파일에 있는 @Mock등록 Bean 주입
    private BookService bookService;
    @Mock
    private BookRespository bookRespository;

}
