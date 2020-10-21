package br.com.bootcamp.nossoBancoDigital.configuration.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidacaoErroHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public List<ErroFormularioDto> handleErroDeValidacao(MethodArgumentNotValidException e) {

		return e.getBindingResult().getFieldErrors().stream().map(erro -> {
			String campo = erro.getField();
			String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			ErroFormularioDto dto = new ErroFormularioDto(campo, mensagem);
			return dto;
		}).collect(Collectors.toList());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErroFormularioDto handleErroDeCampoUnico(DataIntegrityViolationException e) {

		String causaCompleta = e.getMostSpecificCause().getMessage();
		Pattern pattern = Pattern.compile("\\.(.*)_ÚNICO");
		Matcher matcher = pattern.matcher(causaCompleta);

		if (matcher.find()) {
			String campo = matcher.group(1);
			return new ErroFormularioDto(campo, "Já existe um registro com o valor informado");
		}else {
			String campo = "Constraint não identificada";
			return new ErroFormularioDto(campo, "Já existe um registro com o valor informado");
		}


	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String handleErroDeCampoUnico(NotFoundException e) {
		return e.getMessage();
	}

}
