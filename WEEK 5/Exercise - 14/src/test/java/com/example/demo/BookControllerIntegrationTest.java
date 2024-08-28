package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        // Clear the repository before each test
        bookRepository.deleteAll();
    }

    @Test
    public void testCreateBook() throws Exception {
        String bookJson = "{ \"title\": \"The Great Gatsby\", \"author\": \"F. Scott Fitzgerald\", \"isbn\": \"9780743273565\", \"price\": 10.99 }";

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Great Gatsby"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("F. Scott Fitzgerald"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("9780743273565"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.99));
    }

    // Additional integration tests
}
