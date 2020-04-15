package br.com.projetafacil.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.projetafacil.model.ComposicaoServico;

@Component
public class ComposicaoServicoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ComposicaoServico.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "classe.id", "", "Selecione uma Classe");
		ValidationUtils.rejectIfEmpty(errors, "nome", "", "Informe o Nome");
		ValidationUtils.rejectIfEmpty(errors, "unidade", "", "Informe a Unidade");
		
		ComposicaoServico composicaoServico = (ComposicaoServico) target;
		validarSeInformouItens(errors, composicaoServico);
	}

	private void validarSeInformouItens(Errors errors, ComposicaoServico composicaoServico) {
		if (composicaoServico.getInsumos().isEmpty()) {
			errors.reject("", "Adicione pelo menos um insumo na composição");
		}
	}

	
}
