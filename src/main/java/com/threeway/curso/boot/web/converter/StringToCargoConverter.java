package com.threeway.curso.boot.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.threeway.curso.boot.domain.Cargo;
import com.threeway.curso.boot.service.CargoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo>{
	
	@Autowired
	CargoService service;

	@Override
	public Cargo convert(String texto) {
		if(texto.isEmpty()) {
			return null;
		}
//		Long id = Long.valueOf(texto);
		return service.buscarPorId(Long.valueOf(texto));
	}

}
