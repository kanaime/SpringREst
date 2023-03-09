package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@RestController
public class BookController {
    @Autowired
    private BookRepository respository;

    @GetMapping("/Books")
    public List<Book> index() {
        return respository.findAll();
    }

    @GetMapping("/Books/search")
    public List<Book> search(@RequestParam String nomDeRecherche) {
        String searchTerm = nomDeRecherche;
        return respository.findByTitleContainingOrAuthorContaining(searchTerm, searchTerm);
    }

    @GetMapping("/Books/show/{id}")
    public Book show(@PathVariable Long id) {
        return respository.findById(id).get();
    }

    @PostMapping("/Book/save")
    public Book create(@RequestBody Book Book) {
        return respository.save(Book);
    }

    @PutMapping("/Books/update/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book Book) {
        // getting Book
        Book BookToUpdate = respository.findById(id).get();
        BookToUpdate.setTitle(Book.getTitle());
        BookToUpdate.setAuthor(Book.getAuthor());
        BookToUpdate.setDescription(Book.getDescription());
        return respository.save(BookToUpdate);
    }

    @DeleteMapping("Books/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        respository.deleteById(id);
        return true;
    }
}
