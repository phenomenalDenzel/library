package com.assessment.library.service;

import com.assessment.library.model.Book;
import com.assessment.library.repository.BookRepository;
import com.assessment.library.service.dto.BookDTO;
import com.assessment.library.service.mapper.BookMapper;
import com.assessment.library.service.utils.Preconditions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    public Optional<BookDTO> getBook(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto);
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO updateBook(BookDTO bookDTO){
        Book book = bookMapper.toEntity(bookDTO);
        Preconditions.checkState(bookRepository.findById(book.getId()).isPresent(),"Tried to update a book that does not exist");
        Book saveBook = bookRepository.save(book);
        return bookMapper.toDto(saveBook);
    }

    public void deleteBook(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        Preconditions.checkState(book.isPresent(),"Invalid Book ID");
        bookRepository.deleteById(bookId);
    }
}
