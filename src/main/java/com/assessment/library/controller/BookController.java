package com.assessment.library.controller;

import com.assessment.library.service.BookService;
import com.assessment.library.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping({"/","/books","/index"})
    public String home(Model model){
        List<BookDTO> books = bookService.getAllBooks();
        BookDTO newBook = new BookDTO();
        model.addAttribute("books",books.size() ==0? null:books);
        model.addAttribute("book",newBook);
        return "index";
    }

    @GetMapping("/books/{bookId}")
    public String getBook(@PathVariable("bookId") Long bookId, Model model, HttpServletResponse response)throws Exception{
        Optional<BookDTO> book = bookService.getBook(bookId);
        if(book.isPresent()){
            model.addAttribute("book",book.get());
            return "book";
        }
        response.sendError(HttpStatus.NOT_FOUND.value(),"book not found");
        return "book";
    }

    @PostMapping("/books")
    public String createBook(BookDTO book){
        BookDTO savedBook = bookService.createBook(book);
        return "redirect:/books/"+savedBook.getId();
    }

    @PostMapping("/books/{bookId}")
    public String updateBook(@PathVariable("bookId") Long bookId, BookDTO book){
        book.setId(bookId);
        bookService.updateBook(book);
        return "redirect:/";
    }

    @RequestMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return "redirect:/";
    }
}
