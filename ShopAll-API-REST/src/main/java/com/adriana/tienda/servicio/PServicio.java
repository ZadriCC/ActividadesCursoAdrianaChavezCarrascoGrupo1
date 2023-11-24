package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Producto;
import com.adriana.tienda.datos.Subcategoria;
import com.adriana.tienda.datos.Vendedor;
import com.adriana.tienda.dto.ProductoDTO;
import com.adriana.tienda.excepciones.CampoNuloOVacioException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.PRepositorio;
import com.adriana.tienda.reposiorio.SRepositorio;
import com.adriana.tienda.reposiorio.VRepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class PServicio {

	@Autowired
	private PRepositorio pRep;

	@Autowired
	private VRepositorio vRep;

	@Autowired
	private SRepositorio sRep;

	// convertir entidad a dto
	private ProductoDTO mapearDTO(Producto p) {
		ProductoDTO pDTO = new ProductoDTO();
		pDTO.setIdProducto(p.getIdProducto());
		pDTO.setpNombre(p.getpNombre());
		pDTO.setpDescripcion(p.getpDescripcion());
		pDTO.setpPrecio(p.getpPrecio());
		pDTO.setpStock(p.getpStock());
		return pDTO;
	}

	// convertir de DTO a Entidad
	private Producto mapearEntidad(ProductoDTO pDTO) {
		Producto p = new Producto();
		p.setIdProducto(pDTO.getIdProducto());
		p.setpNombre(pDTO.getpNombre());
		p.setpDescripcion(pDTO.getpDescripcion());
		p.setpPrecio(pDTO.getpPrecio());
		p.setpStock(pDTO.getpStock());
		return p;
	}

	public ProductoDTO addProductos(int idVendedor, int idSubcategoria, ProductoDTO pDTO) {
		Producto p = mapearEntidad(pDTO);
		Subcategoria sc = sRep.findById(idSubcategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Subcategoria", "id", idSubcategoria));
		Vendedor pv = vRep.findById(idVendedor)
				.orElseThrow(() -> new RecursoNoEncontradoException("Vendedor", "id", idVendedor));
		Validaciones.validarCampoNuloOVacio(pDTO.getpNombre(), "Nombre");
		Validaciones.validarCampoNuloOVacio(pDTO.getpDescripcion(), "Descripción");
		if (pDTO.getpPrecio() == 0) {
			throw new CampoNuloOVacioException("El campo 'Precio' no puede estar vacío o nulo.");
		}
		if (pDTO.getpStock() == 0) {
			throw new CampoNuloOVacioException("El campo 'Stock' no puede estar vacío o nulo.");
		}
		p.setSubcategoria(sc);
		p.setVendedor(pv);
		Producto nuevoP = pRep.save(p);
		ProductoDTO pRespuesta = mapearDTO(nuevoP);
		return pRespuesta;
	}

	public List<ProductoDTO> getAll() {
		List<Producto> lProductos = pRep.findAll();
		return lProductos.stream().map(p -> mapearDTO(p)).collect(Collectors.toList());
	}

	public ProductoDTO getById(int idProducto) {
		Producto p = pRep.findById(idProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", idProducto));
		return mapearDTO(p);
	}

	public ProductoDTO updateProductos(int idProducto, ProductoDTO pDTO) {
		Producto p = pRep.findById(idProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", idProducto));
		Validaciones.validarCampoNuloOVacio(pDTO.getpNombre(), "Nombre");
		Validaciones.validarCampoNuloOVacio(pDTO.getpDescripcion(), "Descripción");
		p.setpNombre(pDTO.getpNombre());
		p.setpDescripcion(pDTO.getpDescripcion());
		p.setpPrecio(pDTO.getpPrecio());
		p.setpStock(pDTO.getpStock());
		Producto pActualizado = pRep.save(p);

		return mapearDTO(pActualizado);
	}

	public void deleteProductos(int idProducto) {
		Producto p = pRep.findById(idProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", idProducto));
		pRep.delete(p);

	}

}
