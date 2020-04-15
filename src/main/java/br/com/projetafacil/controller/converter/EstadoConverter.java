package br.com.projetafacil.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.projetafacil.model.Estado;


public class EstadoConverter implements Converter<String, Estado> {

	@Override
	public Estado convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Estado estado = new Estado();
			estado.setId(Long.valueOf(codigo));
			return estado;
		}
		
		return null;
	}

}
