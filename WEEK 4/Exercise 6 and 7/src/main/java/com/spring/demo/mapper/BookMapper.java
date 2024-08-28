package com.spring.demo.mapper;



import com.spring.demo.dto.BookDTO;
import com.spring.demo.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);

    // Method to map a list of Book entities to a list of BookDTOs
    List<BookDTO> bookToBookDTOList(List<Book> books);

    // Method to map a list of BookDTOs to a list of Book entities (if needed)
    List<Book> bookDTOToBookList(List<BookDTO> bookDTOs);
}
