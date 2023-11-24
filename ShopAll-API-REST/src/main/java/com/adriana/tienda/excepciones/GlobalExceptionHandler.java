package com.adriana.tienda.excepciones;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RecursoNoEncontradoException.class)
	public ResponseEntity<ErrorDetalles> handleResourceNotFoundException(RecursoNoEncontradoException exception,
			WebRequest webRequest) {
		ErrorDetalles errorDetales = new ErrorDetalles(LocalDate.now(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetales, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CampoDuplicadoException.class)
	public ResponseEntity<ErrorDetalles> handleEmailDuplicadoException(CampoDuplicadoException exception) {
		ErrorDetalles errorDetalles = new ErrorDetalles(LocalDate.now(), exception.getMessage(),
				"Por favor, verifique el campo o intente con un valor diferente.");
		return new ResponseEntity<>(errorDetalles, HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(DatoNoValidoException.class)
	public ResponseEntity<ErrorDetalles> handlePasswordInvalidException(DatoNoValidoException exception) {
		ErrorDetalles errorDetalles = new ErrorDetalles(LocalDate.now(), exception.getMessage(),
				"Por favor, revise los datos ingresados y asegúrese de que cumplan con los requisitos necesarios.");
		return new ResponseEntity<>(errorDetalles, HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(CampoNuloOVacioException.class)
	public ResponseEntity<ErrorDetalles> handleCampoNuloOVacioException(CampoNuloOVacioException exception,
			WebRequest webRequest) {
		ErrorDetalles errorDetales = new ErrorDetalles(LocalDate.now(), exception.getMessage(),
				"Por favor, asegúrese de completar todos los campos requeridos y que no estén vacíos.");
		return new ResponseEntity<>(errorDetales, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ErrorAppException.class)
	public ResponseEntity<ErrorDetalles> handleAppException(ErrorAppException exception, WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(LocalDate.now(), exception.getMessage(),
				"Verifique que los ID´s sean correctos.");
		return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StockInsuficienteException.class)
	public ResponseEntity<ErrorDetalles> handleStockInsuficienteException(StockInsuficienteException exception) {
		ErrorDetalles errorDetalles = new ErrorDetalles(LocalDate.now(), exception.getMessage(),
				"Verifique el stock del producto y asegúrese de que haya suficientes unidades disponibles.");
		return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorDetalles errorDetales = new ErrorDetalles(LocalDate.now(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetales, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String nombreCampo = ((FieldError) error).getField();
			String mensaje = error.getDefaultMessage();
			errores.put(nombreCampo, mensaje);
		});
		return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
	}

}
