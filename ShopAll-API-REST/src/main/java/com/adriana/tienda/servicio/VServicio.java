package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.datos.Vendedor;
import com.adriana.tienda.dto.NotificacionDTO;
import com.adriana.tienda.dto.VendedorDTO;
import com.adriana.tienda.excepciones.ErrorAppException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.reposiorio.VRepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class VServicio {

	@Autowired
	private VRepositorio vRep;

	@Autowired
	private URepositorio uRep;

	@Autowired
	private NServicio nSer;

	// convertir entidad a dto
	private VendedorDTO mapearDTO(Vendedor v) {
		VendedorDTO vDTO = new VendedorDTO();
		vDTO.setIdVendedor(v.getIdVendedor());
		vDTO.setvNombre(v.getvNombre());
		vDTO.setvDescripcion(v.getvDescripcion());
		vDTO.setvGiro(v.getvGiro());
		vDTO.setvSitioWeb(v.getvSitioWeb());
		// vDTO.setUsuario(v.getUsuario());
		return vDTO;
	}

	// convertir de DTO a Entidad
	private Vendedor mapearEntidad(VendedorDTO vDTO) {
		Vendedor v = new Vendedor();
		v.setIdVendedor(vDTO.getIdVendedor());
		v.setvNombre(vDTO.getvNombre());
		v.setvDescripcion(vDTO.getvDescripcion());
		v.setvGiro(vDTO.getvGiro());
		v.setvSitioWeb(vDTO.getvSitioWeb());
		// v.setUsuario(vDTO.getUsuario());
		return v;
	}

	public VendedorDTO addVendedor(int idUsuario, VendedorDTO vDTO) {
		Vendedor v = mapearEntidad(vDTO);
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Vendedor perfilVendedor = vRep.findByUsuario(user);

		if (perfilVendedor != null) {
			throw new ErrorAppException("Ya existe un perfil de vendedor para este usuario.");
		}

		v.setUsuario(user);

		Validaciones.validarCampoNuloOVacio(vDTO.getvNombre(), "Nombre");
		Validaciones.validarCampoNuloOVacio(vDTO.getvDescripcion(), "Descripción");
		Validaciones.validarCampoNuloOVacio(vDTO.getvGiro(), "Giro");

		user.setuRol("vendedor");
		uRep.save(user);
		Vendedor nuevoPV = vRep.save(v);

		NotificacionDTO nuevaNDTO = new NotificacionDTO();
		nuevaNDTO.setnDescripcion("Se ha creado el perfil con exito.");
		nSer.addNotificacion(idUsuario, nuevaNDTO);

		VendedorDTO pvRespuesta = mapearDTO(nuevoPV);
		return pvRespuesta;
	}

	public List<VendedorDTO> getAll() {
		List<Vendedor> lVendedores = vRep.findAll();
		return lVendedores.stream().map(v -> mapearDTO(v)).collect(Collectors.toList());
	}

	public VendedorDTO getById(Integer idUsuario, Integer idVendedor) {
		uRep.findById(idUsuario).orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Vendedor v = vRep.findById(idVendedor)
				.orElseThrow(() -> new RecursoNoEncontradoException("Vendedor", "id", idVendedor));
		Validaciones.compararIDs(v.getUsuario().getIdUsuario(), idUsuario);
		return mapearDTO(v);
	}

	public VendedorDTO updateVendedor(Integer idUsuario, Integer idVendedor, VendedorDTO vDTO) {
		uRep.findById(idUsuario).orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Vendedor v = vRep.findById(idVendedor)
				.orElseThrow(() -> new RecursoNoEncontradoException("Vendedor", "id", idVendedor));

		Validaciones.compararIDs(v.getUsuario().getIdUsuario(), idUsuario);
		v.setvNombre(vDTO.getvNombre());
		v.setvGiro(vDTO.getvGiro());
		v.setvDescripcion(vDTO.getvDescripcion());
		v.setvSitioWeb(vDTO.getvSitioWeb());
		Validaciones.validarCampoNuloOVacio(vDTO.getvNombre(), "Nombre");
		Validaciones.validarCampoNuloOVacio(vDTO.getvDescripcion(), "Descripción");
		Validaciones.validarCampoNuloOVacio(vDTO.getvGiro(), "Giro");
		Vendedor pvActualizado = vRep.save(v);
		return mapearDTO(pvActualizado);
	}

	public void deleteVendedor(Integer idUsuario, Integer idVendedor) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Vendedor v = vRep.findById(idVendedor)
				.orElseThrow(() -> new RecursoNoEncontradoException("Vendedor", "id", idVendedor));

		Validaciones.compararIDs(v.getUsuario().getIdUsuario(), idUsuario);
		vRep.delete(v);

		user.setuRol("comprador");
		uRep.save(user);
	}
}
