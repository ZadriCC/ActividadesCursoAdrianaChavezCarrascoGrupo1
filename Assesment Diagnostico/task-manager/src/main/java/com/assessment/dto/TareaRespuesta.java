package com.assessment.dto;

import java.util.List;

public class TareaRespuesta {
	
	private List<TareaDTO> contenido;
	private int noPagina;
	private int medidaPag;
	private long totalEle;
	private int totalPag;
	private boolean ultima;

	public List<TareaDTO> getContenido() {
		return contenido;
	}

	public void setContenido(List<TareaDTO> contenido) {
		this.contenido = contenido;
	}

	public int getNoPagina() {
		return noPagina;
	}

	public void setNoPagina(int noPagina) {
		this.noPagina = noPagina;
	}

	public int getMedidaPag() {
		return medidaPag;
	}

	public void setMedidaPag(int medidaPag) {
		this.medidaPag = medidaPag;
	}

	public long getTotalEle() {
		return totalEle;
	}

	public void setTotalEle(long totalEle) {
		this.totalEle = totalEle;
	}

	public int getTotalPag() {
		return totalPag;
	}

	public void setTotalPag(int totalPag) {
		this.totalPag = totalPag;
	}

	public boolean isUltima() {
		return ultima;
	}

	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}

	public TareaRespuesta() {
		super();
	}

}
