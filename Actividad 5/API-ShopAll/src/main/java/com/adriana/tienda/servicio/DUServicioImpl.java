package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetalleUsuario;
import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.DetalleUsuarioDTO;
import com.adriana.tienda.excepciones.AppException;
import com.adriana.tienda.excepciones.CampoNuloOVacioException;
import com.adriana.tienda.excepciones.ResourceNotFoundException;
import com.adriana.tienda.excepciones.TelefonoInvalidoException;
import com.adriana.tienda.repositorio.DURepositorio;
import com.adriana.tienda.repositorio.URepositorio;

@Service
public class DUServicioImpl implements DUServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DURepositorio dtuRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private DetalleUsuarioDTO mapearDTO(DetalleUsuario dtUser) {
		DetalleUsuarioDTO dtuDTO = modelMapper.map(dtUser, DetalleUsuarioDTO.class);
		return dtuDTO;
	}

	// convertir de DTO a Entidad
	private DetalleUsuario mapearEntidad(DetalleUsuarioDTO dtuDTO) {
		DetalleUsuario dtUser = modelMapper.map(dtuDTO, DetalleUsuario.class);
		return dtUser;
	}

	@Override
	public DetalleUsuarioDTO addDTUsuario(int idUser, DetalleUsuarioDTO dtuDTO) {
		DetalleUsuario dtUser = mapearEntidad(dtuDTO);
		Usuarios user = uRep.findById(idUser).orElseThrow();

		//validación del número de teléfono
		String telefono = dtuDTO.getDuTelefono();
		if (!isValidPhoneNumber(telefono)) {
			throw new TelefonoInvalidoException("El número de teléfono no es válido.");
		}
		if (dtuDTO.getDuDireccion() == null || dtuDTO.getDuDireccion().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Dirección' no puede estar vacío.");
		}
		if (dtuDTO.getDuTelefono() == null || dtuDTO.getDuTelefono().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Teléfono' no puede estar vacío.");
		}
		dtUser.setUsuarios(user);
		DetalleUsuario nuevoDTUser = dtuRep.save(dtUser);
		return mapearDTO(nuevoDTUser);
	}

	@Override
	public List<DetalleUsuarioDTO> getAll() {
		List<DetalleUsuario> lUsuarios = dtuRep.findAll();
		return lUsuarios.stream().map(user -> mapearDTO(user)).collect(Collectors.toList());
	}

	@Override
	public DetalleUsuarioDTO getById(Integer idUser, Integer dtUser) {
		Usuarios user = uRep.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUser));
		DetalleUsuario dtUser2 = dtuRep.findById(dtUser)
				.orElseThrow(() -> new ResourceNotFoundException("Detalle de usuario", "id", dtUser));

		// Comparar los ID de usuario en lugar de los objetos completos
		if (dtUser2.getUsuarios().getIdUsuario() != user.getIdUsuario()) {
			throw new AppException(HttpStatus.BAD_REQUEST,
					"IDs incorrectos: " + dtUser2.getUsuarios().getIdUsuario() + " y " + user.getIdUsuario());
		}
		return mapearDTO(dtUser2);
	}

	@Override
	public DetalleUsuarioDTO updateDTUsuario(Integer idUser, Integer dtUser, DetalleUsuarioDTO dtuDTO) {
		Usuarios user = uRep.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUser));
		DetalleUsuario dtUser2 = dtuRep.findById(dtUser)
				.orElseThrow(() -> new ResourceNotFoundException("Detalle de usuario", "id", dtUser));

		// Comparar los ID de usuario en lugar de los objetos completos
		if (dtUser2.getUsuarios().getIdUsuario() != user.getIdUsuario()) {
			throw new AppException(HttpStatus.BAD_REQUEST,
					"IDs incorrectos: " + dtUser2.getUsuarios().getIdUsuario() + " y " + user.getIdUsuario());
		}
		// Realiza la validación del número de teléfono
		String telefono = dtuDTO.getDuTelefono();
		if (!isValidPhoneNumber(telefono)) {
			throw new TelefonoInvalidoException("El número de teléfono no es válido.");
		}
		if (dtuDTO.getDuDireccion() == null || dtuDTO.getDuDireccion().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Dirección' no puede estar vacío.");
		}
		if (dtuDTO.getDuTelefono() == null || dtuDTO.getDuTelefono().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Teléfono' no puede estar vacío.");
		}
		dtUser2.setDuDireccion(dtuDTO.getDuDireccion());
		dtUser2.setDuTelefono(dtuDTO.getDuTelefono());
		DetalleUsuario dtuActualizado = dtuRep.save(dtUser2);
		return mapearDTO(dtuActualizado);
	}

	@Override
	public void deleteDTUsuario(Integer idUsuario, Integer idDUsuario) {
		Usuarios user = uRep.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
		DetalleUsuario dtUser2 = dtuRep.findById(idDUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Detalle de usuario", "id", idDUsuario));

		// Comparar los ID de usuario en lugar de los objetos completos
		if (dtUser2.getUsuarios().getIdUsuario() != user.getIdUsuario()) {
			throw new AppException(HttpStatus.BAD_REQUEST,
					"IDs incorrectos: " + dtUser2.getUsuarios().getIdUsuario() + " y " + user.getIdUsuario());
		}
		dtuRep.delete(dtUser2);

	}

	private boolean isValidPhoneNumber(String phoneNumber) {

		String regex = "\\d{10}";
		return phoneNumber.matches(regex);
	}
}
