package com.threeway.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.threeway.curso.boot.domain.Cargo;

//impl classe de implementação
//@Autowired injeta 
//@Repository faz o objeto visível para ser injetavel pelo autowired  (mostra que a classe deve ser gerenciada pelo spring) vai falar pro spring que a calsse ai ser gerenciada para injetar o objeto dela onde for preciso, criara o objeto onde ofr preciso, quando for usar a variavel dao o spring vai injetar o metodo a partir da variavel 
@Repository
public class CargoDaolmpl extends GenericDao<Cargo> implements CargoDao{

	

}
