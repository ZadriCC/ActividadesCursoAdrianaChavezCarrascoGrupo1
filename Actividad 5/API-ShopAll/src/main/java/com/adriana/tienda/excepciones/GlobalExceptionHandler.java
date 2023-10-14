package com.adriana.tienda.excepciones;

import java.util.Date;
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

import com.adriana.tienda.datos.dto.ErrorDetalles;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetalles> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {
		ErrorDetalles errorDetales = new ErrorDetalles(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetales, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailDuplicadoException.class)
	public ResponseEntity<ErrorDetalles> handleEmailDuplicadoException(EmailDuplicadoException exception) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(),
				"Por favor verifique su correo electronico o ingrese otro");
		return new ResponseEntity<>(errorDetalles, HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(PasswordInvalidException.class)
	public ResponseEntity<ErrorDetalles> handlePasswordInvalidException(PasswordInvalidException exception) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(),
				"La contraseña debe tener almenos una letra, un numero y un caracter especial, ademas debe de ser de 8 caracteres");
		return new ResponseEntity<>(errorDetalles, HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(CampoNuloOVacioException.class)
	public ResponseEntity<ErrorDetalles> handleCampoNuloOVacioException(CampoNuloOVacioException exception,
			WebRequest webRequest) {
		ErrorDetalles errorDetales = new ErrorDetalles(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetales, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(AppException.class)
//	public ResponseEntity<ErrorDetalles> handleAppException(AppException exception, WebRequest webRequest) {
//		ErrorDetalles errorDetales = new ErrorDetalles(new Date(), exception.getMessage(),
//				webRequest.getDescription(false));
//		return new ResponseEntity<>(errorDetales, HttpStatus.PRECONDITION_FAILED);
//	}
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorDetalles> handleAppException(AppException exception, WebRequest webRequest) {
		String mensaje = exception.getMessage(); // Obtén el mensaje de la excepción
		if (mensaje == null) {
			mensaje = "Ocurrió un error inesperado"; // Define un mensaje predeterminado si es null
		}

		ErrorDetalles errorDetales = new ErrorDetalles(new Date(), mensaje, webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetales, exception.getEstado());
	}

	@ExceptionHandler(TelefonoInvalidoException.class)
	public ResponseEntity<ErrorDetalles> handleTelefonoInvalidoException(TelefonoInvalidoException exception,
			WebRequest webRequest) {
		ErrorDetalles errorDetales = new ErrorDetalles(new Date(), exception.getMessage(),
				"El nuemero de telefono debe tener 10 digitos");
		return new ResponseEntity<>(errorDetales, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StockInsuficienteException.class)
	public ResponseEntity<ErrorDetalles> handleStockInsuficienteException(StockInsuficienteException exception) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(),
				"Verifique el stock del producto");
		return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorDetalles errorDetales = new ErrorDetalles(new Date(), exception.getMessage(),
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
