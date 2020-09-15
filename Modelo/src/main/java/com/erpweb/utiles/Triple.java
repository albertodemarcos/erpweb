package com.erpweb.utiles;

import java.io.Serializable;

public class Triple<I, C, D> implements Serializable {
	
	private static final long serialVersionUID = -5529682106495102001L;
	
	private I izquierda;
	private C centro;
	private D derecha;
	
	public Triple(I izquierda, C centro, D derecha) {
		super();
		this.izquierda = izquierda;
		this.centro = centro;
		this.derecha = derecha;
	}

	public I getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(I izquierda) {
		this.izquierda = izquierda;
	}

	public C getCentro() {
		return centro;
	}

	public void setCentro(C centro) {
		this.centro = centro;
	}

	public D getDerecha() {
		return derecha;
	}

	public void setDerecha(D derecha) {
		this.derecha = derecha;
	}
	
	public final Boolean isNull() {
		return this.izquierda == null && this.centro == null && this.derecha == null;
	}
	
	public final Boolean hasAnyNull() {
		return this.izquierda == null || this.centro == null || this.derecha == null;
	}
	
	public final Boolean isIzquierdaNull() {
		return this.izquierda == null;
	}
	
	public final Boolean isCentroNull() {
		return this.centro == null;
	}
	
	public final Boolean isDerechaNull() {
		return this.derecha == null;
	}
	
}
