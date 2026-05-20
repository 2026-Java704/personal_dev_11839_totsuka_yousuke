package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.model.Account;
import com.example.demo.repository.UsersRepository;

@Controller
public class UserController {
	//フィールド
	private final UsersRepository usersRepository;
	private final HttpSession session;
	private final Account account;

	//コンストラクタ
	public UserController(UsersRepository usersRepository, HttpSession session, Account account) {
		this.usersRepository = usersRepository;
		this.session = session;
		this.account = account;

	}

	//ログイン画面
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		session.invalidate();
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam String name,
			@RequestParam String password,
			Model model) {
		// 名前が空の場合にエラーとする
		if (name.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "ログイン情報を入力してください");
			return "login";
		}
		//入力必須
		List<Users> userList = usersRepository.findByNameAndPassword(name, password);
		if (userList.size() == 0) {
			model.addAttribute("message", "一致しないよ");
			return "login";
		}

		account.setName(name);

		return "redirect:/records/add";
	}

	//新規登録
	@GetMapping("/users/add")
	public String create() {
		return "addUser";
	}

	@PostMapping("/users/add")
	public String store(
			@RequestParam String name,
			@RequestParam String password) {

		Users user = new Users(name, password, null, null, null);

		usersRepository.save(user);

		return "redirect:/login";
	}
}
