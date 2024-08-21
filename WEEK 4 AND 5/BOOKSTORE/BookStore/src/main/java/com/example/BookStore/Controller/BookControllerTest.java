package com.example.BookStore.Controller;

import com.example.BookStore.Dto.BookDto;
import com.example.BookStore.Service.BookService;
import org.junit.Test;

import java.util.Collections;

import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.web.servlet.function.ServerResponse.status;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testCreateBook() throws Exception {
        BookDto bookDto = new BookDto(1L, "Test Book", "Test Author", 29.99);
        when(bookService.createBook(any(BookDto.class))).thenReturn(bookDto);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content.id").value(1))
                .andExpect(jsonPath("$.content.title").value("Test Book"))
                .andExpect(jsonPath("$.content.author").value("Test Author"))
                .andExpect(jsonPath("$.content.price").value(29.99));
    }

    @Test
    public void testGetBookById() throws Exception {
        BookDto bookDto = new BookDto(1L, "Test Book", "Test Author", 29.99);
        when(bookService.getBookById(anyLong())).thenReturn(bookDto);

        mockMvc.perform(get("/api/books/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.id").value(1))
                .andExpect(jsonPath("$.content.title").value("Test Book"))
                .andExpect(jsonPath("$.content.author").value("Test Author"))
                .andExpect(jsonPath("$.content.price").value(29.99));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        BookDto bookDto = new BookDto(1L, "Test Book", "Test Author", 29.99);
        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(bookDto));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Test Book"))
                .andExpect(jsonPath("$.content[0].author").value("Test Author"))
                .andExpect(jsonPath("$.content[0].price").value(29.99));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookDto bookDto = new BookDto(1L, "Updated Book", "Updated Author", 39.99);
        when(bookService.updateBook(anyLong(), any(BookDto.class))).thenReturn(bookDto);

        mockMvc.perform(put("/api/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.id").value(1))
                .andExpect(jsonPath("$.content.title").value("Updated Book"))
                .andExpect(jsonPath("$.content.author").value("Updated Author"))
                .andExpect(jsonPath("$.content.price").value(39.99));
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        mockMvc.perform(delete("/api/books/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted successfully"));
    }

    @Test
    public void testSearchBooks() throws Exception {
        BookDto bookDto = new BookDto(1L, "Search Book", "Search Author", 49.99);
        when(bookService.searchBooks(any(String.class), any(String.class)))
                .thenReturn(Collections.singletonList(bookDto));

        mockMvc.perform(get("/api/books/search")
                        .param("title", "Search Book")
                        .param("author", "Search Author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Search Book"))
                .andExpect(jsonPath("$.content[0].author").value("Search Author"))
                .andExpect(jsonPath("$.content[0].price").value(49.99));
    }
}
