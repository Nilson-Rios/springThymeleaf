package com.threeway.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threeway.curso.boot.dao.CargoDao;
import com.threeway.curso.boot.domain.Cargo;

//anotacao que torna a classe um Bean (uma classe comum, mesma ideia dos @Repository nas classes dao, mas no dao a ideia eh especifica para trabalhar com DAO data access object) gerenciado pelo
//todos metodos dessa classe vao abrir uma nvoa transacao . mais utilizado quando vai se alterar algo no banco de dados, nao eh necessario, por exemplo em caso de listagens

//@override sobrescrevo a anotacao da classe porque estou somente fazendo um select

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoDao dao;

	@Override
	public void salvar(Cargo cargo) {
		dao.save(cargo);
	}

	@Override
	public void editar(Cargo cargo) {
		dao.update(cargo);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true) // nao abrir uma nova transação
	public Cargo buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true) // nao abrir uma nova transação
	public List<Cargo> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public boolean cargoTemFuncionarios(Long id) {
		if(buscarPorId(id).getFuncionarios().isEmpty()){
		return false;
		}
	return true;
	}
}
