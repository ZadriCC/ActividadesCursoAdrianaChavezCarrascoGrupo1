package com.adriana.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriana.tienda.dto.ProductoDTO;
import com.adriana.tienda.servicio.PServicio;

@RestController
@RequestMapping("/shopall/productos")
public class PControlador {

	@Autowired
	private PServicio pSer;

	@PostMapping("/agregar/vendedor/{idVendedor}/subcategoria/{idSubcategoria}")
	public ResponseEntity<ProductoDTO> addProductos(@PathVariable(value = "idVendedor") int idVendedor,
			@PathVariable(value = "idSubcategoria") int idSubcategoria, @RequestBody ProductoDTO pDTO) {
		return new ResponseEntity<>(pSer.addProductos(idVendedor, idSubcategoria, pDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<ProductoDTO> getAll() {
		return pSer.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> getProductosById(@PathVariable int id) {
		ProductoDTO productoDTO = pSer.getById(id);
		return new ResponseEntity<>(productoDTO, HttpStatus.OK);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<ProductoDTO> updateProductos(@PathVariable int id, @RequestBody ProductoDTO pDTO) {
		ProductoDTO productoActualizado = pSer.updateProductos(id, pDTO);
		return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteProductos(@PathVariable int id) {
		pSer.deleteProductos(id);
		return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.OK);
	}

}
