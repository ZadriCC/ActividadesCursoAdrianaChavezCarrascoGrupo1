package com.assessment.servicio;

import com.assessment.dto.TareaDTO;
import com.assessment.dto.TareaRespuesta;

public interface TareaServicio {
	
	public TareaDTO crearTarea(TareaDTO tareaDTO);
	
	public TareaRespuesta obtenerTareas(int numeroPagina, int medidaPagina);
	
	public TareaDTO obtenerPorId(long id);
	
	public TareaDTO actualizarTarea(TareaDTO tDTO, long id);
	
	public void eliminarTarea(long id);

}
