package br.com.projetafacil.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.projetafacil.model.Orcamento;

@Component
public class OrcamentoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Orcamento.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Orcamento orcamento = (Orcamento) target;

		ValidationUtils.rejectIfEmpty(errors, "descricao", "", "Descrição do Projeto é obrigatório");
		
		validarSeInformouItens(errors, orcamento);
//		verificaPrecoTotalDoOrcamento(errors, orcamento);
	}

	private void verificaPrecoTotalDoOrcamento(Errors errors, Orcamento orcamento) {
		if (orcamento.getPrecoTotal().doubleValue() < BigDecimal.ONE.doubleValue()) {
			errors.reject("", "Valor total não pode ser menor que R$ 1,00");
		}
		
	}

	private void validarSeInformouItens(Errors errors, Orcamento orcamento) {
		if (orcamento.getItensOrcamento().isEmpty()) {
			errors.reject("", "Adicione pelo menos um serviço no orçamento");
		}
	}

	
}
