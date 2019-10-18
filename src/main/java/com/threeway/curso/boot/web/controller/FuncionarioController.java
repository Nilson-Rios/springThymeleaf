package com.threeway.curso.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "/funcionario/lista";

	}

	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionário incluido com sucesso");
		return "redirect:/funcionarios/listar";
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

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "/funcionario/cadastro";

	}

	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		try {
			funcionarioService.editar(funcionario);
			attr.addFlashAttribute("success", "Funcionário editado com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", e.getMessage());//exceção e, uso get para pegar a mensagem de exceção
			e.printStackTrace();
		}
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		funcionarioService.excluir(id);
		model.addAttribute("success", "Funcionário excluído com sucesso.");
		return listar(model); // no lugar do model poderia ter colocado o endereço, como nos demais métodos
	}
	
	@GetMapping("/buscar/nome")
	public String getProNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		return "/funcionario/lista";
	}
}
