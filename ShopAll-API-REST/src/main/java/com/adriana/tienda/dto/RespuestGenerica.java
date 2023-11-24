package com.adriana.tienda.dto;

import java.util.ArrayList;
import java.util.List;

public class RespuestGenerica {

    private int codigo;
    private boolean exito;
    private String mensaje;
    private List<Object> datos;

    public RespuestGenerica(){
        this.datos = new ArrayList<>();
    }

    public RespuestGenerica(int codigo, boolean exito, String mensaje) {
        super();
        this.codigo = codigo;
        this.exito = exito;
        this.mensaje = mensaje;
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<Object> getDatos() {
		return datos;
	}

	public void setDatos(List<Object> datos) {
		this.datos = datos;
	}
}
