package com.example.question1_library_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.question1_library_api.model.Book;


@RestController()
@RequestMapping("/api/books")
public class BookApi {

    List<Book> books = new ArrayList<>();
    
    public BookApi() {
        books.add(new Book(1323,"Preacher's Doctrine","Preacher Y","PR123",1998));
        books.add(new Book(1431,"Safe Space Project","Coosin D","DA123",2050));
        books.add(new Book(1142,"Daily Life as a Sugar Mommy","Karlie A","ALK23",2050));
    }

    @GetMapping()
    public List<Book> getAll() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return books.stream()
                    .filter(el->el.getId()==id)
                    .findFirst().orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Book not found with id " + id
                        )
                    );
    }

    @GetMapping("/search")
    public List<Book> searchByTitle(@RequestParam String title) {
        List<Book> result = books.stream()
                    .filter(el->el.getTitle().contains(title))
                    .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found with title " + title
            );
        }
        return result;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book addNewBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteBookById(@PathVariable long id){
        Book bookToDelete = books.stream()
                    .filter(el->el.getId()==id)
                    .findFirst().orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Book not found with id " + id
                        )
                    );
        books.remove(bookToDelete);
        return Map.of(
            "Message", "Book deleted successfully",
            "Book_Id", String.valueOf(id)
        );
    }
    
}
