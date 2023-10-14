package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.PerfilVendedor;
import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.PerfilVendedorDTO;
import com.adriana.tienda.excepciones.AppException;
import com.adriana.tienda.excepciones.CampoNuloOVacioException;
import com.adriana.tienda.excepciones.ResourceNotFoundException;
import com.adriana.tienda.repositorio.PVRepositorio;
import com.adriana.tienda.repositorio.URepositorio;

@Service
public class PVServicioImpl implements PVServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PVRepositorio pvRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private PerfilVendedorDTO mapearDTO(PerfilVendedor pv) {
		PerfilVendedorDTO pvDTO = modelMapper.map(pv, PerfilVendedorDTO.class);
		return pvDTO;
	}

	// convertir de DTO a Entidad
	private PerfilVendedor mapearEntidad(PerfilVendedorDTO pvDTO) {
		PerfilVendedor pv = modelMapper.map(pvDTO, PerfilVendedor.class);
		return pv;
	}

	@Override
	public PerfilVendedorDTO addPerfilVendedor(int idUsuario, PerfilVendedorDTO pvDTO) {
		Usuarios user = uRep.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
		PerfilVendedor existingPV = pvRep.findByVendedor(user);

		if (existingPV != null) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Ya existe un perfil de vendedor para este usuario.");
		}
		
		user.setuRol("vendedor");
		uRep.save(user);

		PerfilVendedor pv = mapearEntidad(pvDTO);
		pv.setVendedor(user);
		if (pvDTO.getvNombre() == null || pvDTO.getvNombre().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Nombre' no puede estar vacío.");
		}
		if (pvDTO.getvDescripcion() == null || pvDTO.getvDescripcion().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Descipción' no puede estar vacío.");
		}
		if (pvDTO.getvGiro() == null || pvDTO.getvGiro().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Giro' no puede estar vacío.");
		}
		PerfilVendedor nuevoPV = pvRep.save(pv);
		PerfilVendedorDTO pvRespuesta = mapearDTO(nuevoPV);
		return pvRespuesta;
	}

	@Override
	public List<PerfilVendedorDTO> getAll() {
		List<PerfilVendedor> lPVendedor = pvRep.findAll();
		return lPVendedor.stream().map(pv -> mapearDTO(pv)).collect(Collectors.toList());
	}

	@Override
	public PerfilVendedorDTO getById(Integer idUsuario, Integer idPVendedor) {
		Usuarios user = uRep.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
		PerfilVendedor pv = pvRep.findById(idPVendedor)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil Vendedor", "id", idPVendedor));
		
		if (pv.getVendedor().getIdUsuario() != user.getIdUsuario()) {
			throw new AppException(HttpStatus.BAD_REQUEST,
					"IDs incorrectos: " + pv.getVendedor().getIdUsuario() + " y " + user.getIdUsuario());
		}
		return mapearDTO(pv);
	}

	@Override
	public PerfilVendedorDTO updatePVendedor(Integer idUsuario, Integer idPVendedor, PerfilVendedorDTO pvDTO) {
		Usuarios user = uRep.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
		PerfilVendedor pv = pvRep.findById(idPVendedor)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil Vendedor", "id", idPVendedor));

		// Comparar los id de usuario 
		if (pv.getVendedor().getIdUsuario() != user.getIdUsuario()) {
			throw new AppException(HttpStatus.BAD_REQUEST,
					"IDs incorrectos: " + pv.getVendedor().getIdUsuario() + " y " + user.getIdUsuario());
		}
		pv.setvNombre(pvDTO.getvNombre());
		pv.setvGiro(pvDTO.getvGiro());
		pv.setvDescripcion(pvDTO.getvDescripcion());
		pv.setvSitioWeb(pvDTO.getvSitioWeb());
		if (pvDTO.getvNombre() == null || pvDTO.getvNombre().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Nombre' no puede estar vacío.");
		}
		if (pvDTO.getvDescripcion() == null || pvDTO.getvDescripcion().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Descipción' no puede estar vacío.");
		}
		if (pvDTO.getvGiro() == null || pvDTO.getvGiro().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Giro' no puede estar vacío.");
		}
		PerfilVendedor pvActualizado = pvRep.save(pv);
		return mapearDTO(pvActualizado);
	}

	@Override
	public void deletePVendedor(Integer idUsuario, Integer idVendedor) {
		Usuarios user = uRep.findById(idUsuario) .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
		PerfilVendedor pv = pvRep.findById(idVendedor)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil Vendedor", "id", idVendedor));

		// Comparar los ID de usuario en lugar de los objetos completos
		if (pv.getVendedor().getIdUsuario() != user.getIdUsuario()) {
			throw new AppException(HttpStatus.BAD_REQUEST,
					"IDs incorrectos: " + pv.getVendedor().getIdUsuario() + " y " + user.getIdUsuario());
		}
		pvRep.delete(pv);

		// Cambiar el rol del usuario a "comprador"
		user.setuRol("comprador");
		uRep.save(user);
	}
}
