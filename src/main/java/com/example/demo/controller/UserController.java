package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@Controller
public class UserController {
	//フィールド
	private final UsersRepository usersRepository;

	//コンストラクタ
	public UserController(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@GetMapping({ "/", "/login" })
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {
		//		// 名前が空の場合にエラーとする
		//		if (email == null || email.length() == 0) {
		//			if (password == null || password.length() == 0) {
		//				model.addAttribute("message", "名前を入力してください");
		return "login";
	}

	@GetMapping("/users/add")
	public String create() {
		return "addUser";
	}

	@PostMapping("/users/add")
	public String store(
			@RequestParam String name,
			@RequestParam String password,
			Model model) {

		Users user = new Users(null, name, password, null, null, null);

		usersRepository.save(user);

		return "redirect:/login";
	}
}
