package com.sistema.web.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sistema.models.service.exception.EmailJaCadastradoExecption;
import com.sistema.models.service.exception.IdNaoPodeSerZeroOuNulo;
import com.sistema.models.service.exception.SenhaDiferenteException;

@ControllerAdvice
public class ProjetoExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
		ProblemType problemType = ProblemType.NAO_ESPERADO;
		String detail = ex.getMessage();
		Problem problem = createBuilderProblem(status, problemType, detail)
				             .addUserMessage("Erro não esperado, procure o administrador!")
				             .build();
		
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	
	@ExceptionHandler(SenhaDiferenteException.class)
	public ResponseEntity<?> handlerSenhaDiferenteException(SenhaDiferenteException ex, WebRequest request) {
		HttpStatus status = HttpStatus.FOUND;
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = ex.getMessage();
		Problem problem = createBuilderProblem(status, problemType, detail)
				             .addUserMessage("Senhas diferentes!")
				             .build();
		
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(EmailJaCadastradoExecption.class)
	public ResponseEntity<?> handlerEmailJaCadastradoException(EmailJaCadastradoExecption ex, WebRequest request) {
		HttpStatus status = HttpStatus.FOUND;
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = ex.getMessage();
		Problem problem = createBuilderProblem(status, problemType, detail)
				             .addUserMessage("E-mail já em uso no sistema!")
				             .build();
		
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handlerUsernameNotFound(UsernameNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.USUARIO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		Problem problem = createBuilderProblem(status, problemType, detail)
				             .addUserMessage(ex.getMessage())
				             .build();
		
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(LockedException.class)
	public ResponseEntity<?> handlerLocked(LockedException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.USUARIO_BLOQUEADO;
		String detail = ex.getMessage();
		Problem problem = createBuilderProblem(status, problemType, detail)
				             .addUserMessage(ex.getMessage())
				             .build();
		
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	
	@ExceptionHandler(IdNaoPodeSerZeroOuNulo.class)
	public ResponseEntity<?> handlerIdNaoPodeSerZeroOuNulo(IdNaoPodeSerZeroOuNulo ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ERRO_NEGOGIO;
		String detail = ex.getMessage();
		Problem problem = createBuilderProblem(status, problemType, detail)
				             .addUserMessage("O id não pode ser zero!")
				             .build();
		
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handlerBadCredentials(BadCredentialsException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = ex.getMessage();
		Problem problem = createBuilderProblem(status, problemType, detail)
				             .addUserMessage(ex.getMessage())
				             .build();
		
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
		
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		
		String detail = "Um ou mais campos estão inválidos. "
				+ " Faça o preenchimento correto e tente novamente";
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Fields> fields = bindingResult.getFieldErrors()
									       .stream()
									       .map(fieldError -> {
												String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
												Fields field = new Fields();
												field.setNome(fieldError.getField());
												field.setUserMessage(message);
												return field;
									       	})
									       .collect(Collectors.toList());
		
		Problem problem = createBuilderProblem(status, problemType, detail)
				.addUserMessage(detail)
				.addListFields(fields)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(),  status, request);
	}
	
	

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if (body == null ) {
			
			body = Problem.builder()
					      .addTimeStamp(LocalDateTime.now())
					      .addStatus(status.value())
					      .addTitle(status.getReasonPhrase())
					      .addUserMessage("Ocorreu um erro interno inesperado "
					      		+ "no sistema. Tente novamente mais tarde e se o problema persistir, "
					      		+ "entre em contato com o administrador").build();
		} else if ( body instanceof String) {
			body = Problem.builder()
				      .addTimeStamp(LocalDateTime.now())
				      .addStatus(status.value())
				      .addTitle(status.getReasonPhrase())
				      .addUserMessage("Ocorreu um erro interno inesperado "
				      		+ "no sistema. Tente novamente mais tarde e se o problema persistir, "
				      		+ "entre em contato com o administrador").build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.Builder createBuilderProblem(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
					  .addTimeStamp(LocalDateTime.now())
					  .addStatus(status.value())
					  .addType(problemType.getUri())
					  .addTitle(problemType.getTitle())
					  .addDetail(detail);
	}
	
	
	

}
