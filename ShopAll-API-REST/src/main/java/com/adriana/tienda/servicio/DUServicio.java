package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetalleUsuario;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.DetalleUsuarioDTO;
import com.adriana.tienda.excepciones.DatoNoValidoException;
import com.adriana.tienda.excepciones.ErrorAppException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.DURepositorio;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class DUServicio {

	@Autowired
	private DURepositorio dtuRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private DetalleUsuarioDTO mapearDTO(DetalleUsuario dtUser) {
		DetalleUsuarioDTO dtuDTO = new DetalleUsuarioDTO();
		dtuDTO.setIdDUsuario(dtUser.getIdDUsuario());
		dtuDTO.setDuDireccion(dtUser.getDuDireccion());
		dtuDTO.setDuTelefono(dtUser.getDuTelefono());
		return dtuDTO;
	}

	// convertir de DTO a Entidad
	private DetalleUsuario mapearEntidad(DetalleUsuarioDTO dtuDTO) {
		DetalleUsuario dtUser = new DetalleUsuario();
		dtUser.setIdDUsuario(dtuDTO.getIdDUsuario());
		dtUser.setDuDireccion(dtuDTO.getDuDireccion());
		dtUser.setDuTelefono(dtuDTO.getDuTelefono());
		return dtUser;
	}

	public DetalleUsuarioDTO addDTUsuario(int idUsuario, DetalleUsuarioDTO dtuDTO) {
		DetalleUsuario dtUser = mapearEntidad(dtuDTO);
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Validaciones.validarCampoNuloOVacio(dtuDTO.getDuDireccion(), "Direccion");
		Validaciones.validarCampoNuloOVacio(dtuDTO.getDuTelefono(), "Telefono");

		String telefono = dtuDTO.getDuTelefono();

		if (!isValidPhoneNumber(telefono)) {
			throw new DatoNoValidoException("El número de teléfono no es válido.");
		}
		dtUser.setUsuario(user);
		DetalleUsuario nuevoDTUser = dtuRep.save(dtUser);
		return mapearDTO(nuevoDTUser);
	}

	private boolean isValidPhoneNumber(String phoneNumber) {

		String regex = "\\d{10,16}";
		return phoneNumber.matches(regex);
	}

	public List<DetalleUsuarioDTO> getAll() {
		List<DetalleUsuario> lUsuarios = dtuRep.findAll();
		return lUsuarios.stream().map(user -> mapearDTO(user)).collect(Collectors.toList());
	}

	public DetalleUsuarioDTO getById(Integer idUsuario, Integer idDUsuario) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		DetalleUsuario dtUser = dtuRep.findById(idDUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Detalle de usuario", "id", idDUsuario));
		if (dtUser.getUsuario().getIdUsuario() != user.getIdUsuario()) {
			throw new ErrorAppException(
					"IDs incorrectos: " + dtUser.getUsuario().getIdUsuario() + " y " + user.getIdUsuario());
		}
		return mapearDTO(dtUser);
	}

	public DetalleUsuarioDTO updateDTUsuario(Integer idUsuario, Integer idDUsuario, DetalleUsuarioDTO dtuDTO) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		DetalleUsuario dtUser = dtuRep.findById(idDUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Detalle de usuario", "id", idDUsuario));
		if (dtUser.getUsuario().getIdUsuario() != user.getIdUsuario()) {
			throw new ErrorAppException(
					"IDs incorrectos: " + dtUser.getUsuario().getIdUsuario() + " y " + user.getIdUsuario());
		}
		Validaciones.validarCampoNuloOVacio(dtuDTO.getDuDireccion(), "Direccion");
		Validaciones.validarCampoNuloOVacio(dtuDTO.getDuTelefono(), "Telefono");

		String telefono = dtuDTO.getDuTelefono();

		if (!isValidPhoneNumber(telefono)) {
			throw new DatoNoValidoException("El número de teléfono no es válido.");
		}
		dtUser.setDuDireccion(dtuDTO.getDuDireccion());
		dtUser.setDuTelefono(dtuDTO.getDuTelefono());
		DetalleUsuario dtuActualizado = dtuRep.save(dtUser);
		return mapearDTO(dtuActualizado);
	}

	public void deleteDTUsuario(Integer idUsuario, Integer idDUsuario) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		DetalleUsuario dtUser = dtuRep.findById(idDUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Detalle de usuario", "id", idDUsuario));
		if (dtUser.getUsuario().getIdUsuario() != user.getIdUsuario()) {
			throw new ErrorAppException(
					"IDs incorrectos: " + dtUser.getUsuario().getIdUsuario() + " y " + user.getIdUsuario());
		}
		dtuRep.delete(dtUser);
	}
}
