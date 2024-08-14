package com.example.bookbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookbook.domain.dto.BookDTO;
import com.example.bookbook.domain.dto.BookSearchResponse.Item;
import com.example.bookbook.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

	// 책 서비스
	@Autowired
	private final BookService bookservice;

	// 메인페이지 이동
	@GetMapping
	public String main(Model model) {
		bookservice.getDefaultBooks(model);
		return "views/index/index.html";
	}

	@GetMapping("/bookList")
	public String listBooks(Model model) {
		System.out.println(">>>>>>>>>>>>" + model);
		bookservice.getBookList(model);
		return "views/index/serchBookList";
	}
	

	@GetMapping("/search")
	public String search(@RequestParam("query") String query, Model model) {
		bookservice.searchBooks(query, model);
		return "views/index/serchBookList.html";
	}
	@GetMapping("/detail/{isbn}")
	public String detail(@PathVariable("isbn") String isbn, Model model) {
		try {
			Item book = bookservice.getBookByIsbn(isbn);
			if (book != null) {
				model.addAttribute("book", book);
			} else {
				model.addAttribute("error", "도서를 찾을 수 없습니다.");
			}
		} catch (Exception e) {
			model.addAttribute("error", "도서 정보를 가져오는 중 오류가 발생했습니다.");
		}
		return "views/index/detail";
	}
	
	@PostMapping("/api/books/favorite")
	@ResponseBody
	public ResponseEntity<String> addToFavorites(@RequestParam("isbn") String isbn) {
	    System.out.println("Controller: Received request to add book to favorites: " + isbn);
	    try {
	    	bookservice.addToFavorites(isbn);
	        System.out.println("Controller: Successfully added book to favorites: " + isbn);
	        return ResponseEntity.ok("Book successfully added to favorites");
	    } catch (Exception e) {
	        System.out.println("Controller: Error adding book to favorites: " + e.getMessage());
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error adding book to favorites: " + e.getMessage());
	    }
	}

	// 이벤트페이지
	@GetMapping("/event")
	public String event() {
		return "views/index/event.html";
	}

}