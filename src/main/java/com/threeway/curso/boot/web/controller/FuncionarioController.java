package com.threeway.curso.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.threeway.curso.boot.domain.Cargo;
import com.threeway.curso.boot.domain.Funcionario;
import com.threeway.curso.boot.domain.UF;
import com.threeway.curso.boot.service.CargoService;
import com.threeway.curso.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	CargoService cargoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String listar() {
		return "/funcionario/lista";

	}

	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionário incluido com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}

	// essa anotação já coloca o retorno desse método na variável 'cargos'
	@ModelAttribute("cargos")
	public List<Cargo> listaCargos() {
		return cargoService.buscarTodos();
	}

	// essa anotação já coloca o retorno desse método na variável 'ufs'
	@ModelAttribute("ufs")
	public UF[] listaUFS() {
		return UF.values();
	}
}
