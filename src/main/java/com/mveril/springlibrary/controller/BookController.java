package com.mveril.springlibrary.controller;

import com.mveril.springlibrary.business.Book;
import com.mveril.springlibrary.business.DAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/books")
public class BookController {
    @Autowired
    DAOService<Book> bookService;

    @GetMapping
    public Collection<Book> getAll() {
        return bookService.getAll();
    }

    @PostMapping
    public void create(@RequestBody Book book){
        bookService.add(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") int id) {
        var o = bookService.getById(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") int id, @RequestBody Book book) {
        if(id != book.getId()){
            return ResponseEntity.badRequest().build();
        } else {
            var o = bookService.getById(id);
            if(o.isPresent()){
                bookService.update(id,book);
                return ResponseEntity.ok(o.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> Delete(@PathVariable("id") int id) {
        if(bookService.delete(id)){
            return  ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
