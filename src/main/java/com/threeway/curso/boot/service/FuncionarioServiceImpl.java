package com.threeway.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threeway.curso.boot.dao.FuncionarioDao;
import com.threeway.curso.boot.domain.Funcionario;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService{
	
	@Autowired
	private FuncionarioDao dao;

	@Override
	public void salvar(Funcionario funcionario) {
		dao.save(funcionario);
	}

	@Override
	public void editar(Funcionario funcionario) throws Exception {
		if(funcionario.getDataSaida() ==null) {
			dao.update(funcionario);
		}else{
			throw new Exception("O funcionario não pode ser editado");
		}
		
		dao.update(funcionario);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return dao.findByName(nome);
	}

	

}