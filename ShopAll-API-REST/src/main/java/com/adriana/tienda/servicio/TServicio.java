package com.adriana.tienda.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetalleUsuario;
import com.adriana.tienda.datos.MetodoEnvio;
import com.adriana.tienda.datos.MetodoPago;
import com.adriana.tienda.datos.Pedido;
import com.adriana.tienda.datos.Transaccion;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.NotificacionDTO;
import com.adriana.tienda.dto.TransaccionDTO;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.MERepositorio;
import com.adriana.tienda.reposiorio.MPRepositorio;
import com.adriana.tienda.reposiorio.PdRepositorio;
import com.adriana.tienda.reposiorio.TRepositorio;
import com.adriana.tienda.utilidades.GeneradorCodigo;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class TServicio {

	@Autowired
	private TRepositorio tRep;

	@Autowired
	private PdRepositorio pdRep;

	@Autowired
	private MPRepositorio mpRep;

	@Autowired
	private MERepositorio meRep;

	@Autowired
	private NServicio nSer;

	// convertir entidad a dto
	private TransaccionDTO mapearDTO(Transaccion t) {
		TransaccionDTO tDTO = new TransaccionDTO();
		tDTO.setIdTransaccion(t.getIdTransaccion());
		tDTO.settFecha(t.gettFecha());
		tDTO.settNotasCliente(t.gettNotasCliente());
		tDTO.settNoSeguimiento(t.gettNoSeguimiento());
		tDTO.settDireccionEnvio(t.gettDireccionEnvio());
		tDTO.settTotal(t.gettTotal());
		return tDTO;
	}

	// convertir de DTO a Entidad
	private Transaccion mapearEntidad(TransaccionDTO tDTO) {
		Transaccion t = new Transaccion();
		t.setIdTransaccion(tDTO.getIdTransaccion());
		t.settFecha(tDTO.gettFecha());
		t.settNotasCliente(tDTO.gettNotasCliente());
		t.settNoSeguimiento(tDTO.gettNoSeguimiento());
		t.settDireccionEnvio(tDTO.gettDireccionEnvio());
		t.settTotal(tDTO.gettTotal());
		return t;
	}

	public TransaccionDTO addTransaccion(int idPedido, int idMPago, int idMEnvio, TransaccionDTO tDTO) {
		Transaccion t = mapearEntidad(tDTO);
		Pedido pd = pdRep.findById(idPedido)
				.orElseThrow(() -> new RecursoNoEncontradoException("Pedido", "id", idPedido));
		MetodoPago mp = mpRep.findById(idMPago)
				.orElseThrow(() -> new RecursoNoEncontradoException("Metodo de pago", "id", idMPago));
		MetodoEnvio me = meRep.findById(idMEnvio)
				.orElseThrow(() -> new RecursoNoEncontradoException("Metodo de envio", "id", idMEnvio));
		LocalDate fecha = LocalDate.now();
		t.settFecha(fecha);
		t.setMetodoPago(mp);
		t.setMetodoEnvio(me);
		t.setPedido(pd);

		t.settNoSeguimiento(generadorNoSeguimiento());
		pd.setPdEstado("Enviado");

		double total = pd.getPdSubtotal() + me.getMePrecio();
		t.settTotal(total);
		Transaccion tGuardada = tRep.save(t);

		NotificacionDTO nuevaNDTO = new NotificacionDTO();
		nuevaNDTO.setnDescripcion("Tu pedido se a pagado y enviado con exito.");
		nSer.addNotificacion(pd.getUsuario().getIdUsuario(), nuevaNDTO);
		return mapearDTO(tGuardada);
	}

	private String generadorNoSeguimiento() {
		String numeroSeguimiento;
		do {
			numeroSeguimiento = GeneradorCodigo.generarNumeroSeguimiento();
		} while (tRep.existsBytNoSeguimiento(numeroSeguimiento));
		return numeroSeguimiento;
	}
}
