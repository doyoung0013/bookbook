package com.example.bookbook.service;

import java.util.List;

import org.springframework.ui.Model;

import com.example.bookbook.domain.dto.BookDTO;
import com.example.bookbook.domain.dto.BookSearchResponse.Item;

public interface BookService {

	//void searchBooks(String string, Model model);


	void getBookList(Model model);


	//List<BookDTO> searchBooks(String query);

	Item getBookByIsbn(String isbn);

	void searchBooks(String query, Model model);


	void getDefaultBooks(Model model);





}