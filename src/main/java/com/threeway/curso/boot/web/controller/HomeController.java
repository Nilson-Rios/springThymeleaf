package com.threeway.curso.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// A '/' referencia o que se tem depois do contexto da aplicação
	@GetMapping("/")
	public String Home() {
		return "/home"; // não precisa adicionar a extensão html
	}

}
