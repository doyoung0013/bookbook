package com.example.bookbook.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookbook.domain.dto.QNACreateDTO;
import com.example.bookbook.domain.dto.accountUpdateDTO;
import com.example.bookbook.security.CustomUserDetails;
import com.example.bookbook.service.CouponService;
import com.example.bookbook.service.MypageUserService;
import com.example.bookbook.service.QNAService;


@Controller
@RequiredArgsConstructor
public class MypageController {
	
	private final QNAService qnaService;
	private final MypageUserService userService;
	private final CouponService couponService;
	private final PasswordEncoder passwordEncoder;
	
	//회원정보
	@GetMapping("/mypage/account")
	public String account(Model model, @AuthenticationPrincipal CustomUserDetails user) {
		userService.readProcess(model, user);
		return "views/mypage/account";
	}
	
	@PutMapping("/mypage/account")
	public String accountUpdate(accountUpdateDTO dto) {
		userService.updateProcess(dto);
		return "redirect:/mypage/account";
	}
	
	@GetMapping("/mypage/account/delete")
	public String accountDel() {
		return "views/mypage/account-delete";
	}
	
	@PutMapping("/mypage/account/delete")
	@ResponseBody
	public String accountStatusChange(@RequestParam("password") String password , @AuthenticationPrincipal CustomUserDetails user) {
		if(passwordEncoder.matches(password, user.getPassword())) {
			userService.changeStatus(user);
			return "성공적으로 탈퇴 처리 되었습니다.";
		}else {
			return "비밀번호가 일치하지 않습니다.";
		}
	}
	
	//1:1 문의
	@GetMapping("/mypage/questions")
	public String question(Model model, @AuthenticationPrincipal CustomUserDetails user) {
		qnaService.findAllProcess(model, user);
		return "views/mypage/question";
	}
	
	@GetMapping("/mypage/questions/detail")
	public String questionDetail() {
		return "views/mypage/question-detail";
	}
	
	@PostMapping("/mypage/questions/detail")
	public String qnaCreate(QNACreateDTO dto) {
		qnaService.saveProcess(dto);
		return "redirect:/mypage/questions";
	}
	
	@GetMapping("/mypage/questions/{qnaNum}")
	public String qnaForm(@PathVariable("qnaNum") long qnaNum, Model model) {
		qnaService.findProcess(model, qnaNum);
		return "views/mypage/question-form";
	}
	
	@DeleteMapping("/mypage/questions/{qnaNum}")
	public String deleteQna(@PathVariable("qnaNum") long qnaNum) {
		qnaService.deleteProcess(qnaNum);
		return "redirect:/mypage/questions";
	}
	
	
	//쿠폰
	@GetMapping("/mypage/coupons")
	public String coupon(Model model, @AuthenticationPrincipal CustomUserDetails user) {
		couponService.findProcess(model, user);
		return "views/mypage/coupon";
	}
	
	@PostMapping("/mypage/coupons")
	@ResponseBody
	public String couponAdd(@RequestParam("couponNum") long couponNum , @AuthenticationPrincipal CustomUserDetails user) {
		System.out.println(">>>>couponNum : "+couponNum);
		if(!couponService.checkProcess(couponNum)) {
			if(couponService.checkDuplicateCoupon(couponNum)) {
				couponService.addProcess(couponNum, user);
				return "쿠폰이 정상적으로 등록되었습니다.";
			}else {
				return "이미 등록된 쿠폰입니다.";
			}
		}else {
			return "존재하지 않는 쿠폰 번호입니다.";
		}
		
		/*
		if(!couponService.checkProcess(couponNum)) {
			return "존재하지 않는 쿠폰 번호입니다.";
		} else if(couponService.checkDuplicateCoupon(couponNum)) {
			return "이미 등록된 쿠폰입니다.";
		}else {
			couponService.addProcess(couponNum, user);
			return "쿠폰이 정상적으로 등록되었습니다.";
		}
		*/
	}
	
	//나의 취향
	@GetMapping("/mypage/favorites")
	public String favorite() {
		return "views/mypage/favorite";
	}

}
