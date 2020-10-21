package br.com.bootcamp.nossoBancoDigital.proposta.controller.validation;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.bootcamp.nossoBancoDigital.configuration.validation.ErroFormularioDto;
import br.com.bootcamp.nossoBancoDigital.proposta.controller.dto.ClienteForm;

public abstract class CampoUnicoValidator<T> implements Validator {

	Class<T> Type;

	public CampoUnicoValidator(Class<T> type) {
		this.Type = type;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ClienteForm.class.isAssignableFrom(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(Object target, Errors errors) {

	
		T form = (T) target;
		buscaPorCampo(form);

		getNomesCamposInvalidos().forEach((erroForm) -> {
			errors.rejectValue(erroForm.getCampo(), null, erroForm.getErro());
		});

	}

	protected abstract void buscaPorCampo(Object form);

	protected abstract List<ErroFormularioDto> getNomesCamposInvalidos();

}
