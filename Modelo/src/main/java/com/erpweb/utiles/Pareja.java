package com.erpweb.utiles;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Pareja<I extends Comparable<? super I>, D extends Comparable<? super D>> implements  Comparable< Pareja<I, D> >, Serializable {

	private static final long serialVersionUID = -409716719355811639L;
	
	private I izquierda;
	private D derecha;
	
	
	public Pareja(I izquierda, D derecha) {
		
		this.izquierda = izquierda;
		this.derecha = derecha;
		
	}

	public I getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(I izquierda) {
		this.izquierda = izquierda;
	}

	public D getDerecha() {
		return derecha;
	}

	public void setDerecha(D derecha) {
		this.derecha = derecha;
	}

	public final Boolean isNull() {
		return this.izquierda == null && this.derecha == null;
	}
	
	public final Boolean isNotNull() {
		return this.izquierda != null && this.derecha != null;
	}
	
	public final Boolean hasAnyNull() {
		return this.izquierda == null || this.derecha == null;
	}
	
	public final Boolean isIzquierdaNull() {
		return this.izquierda == null;
	}
	
	public final Boolean isDerechaNull() {
		return this.derecha == null;
	}

	@Override
	public int compareTo(Pareja<I, D> o) {
		// Si ambos son nulos
		if(this.isNull() && o.isNull() ) {
			return 0;
		}

		//Si el primero es nulo pero el segundo no
		if( this.isNull() && !o.isNull() ) {
			return 1;
		}
		
		//Si el primero no es nulo pero el segundo si
		if( !this.isNull() && o.isNull() ) {
			return -1;
		}
		
		// Caso 1: izquieda nula
		if( this.isIzquierdaNull() ) {
			//Comprobamos la otra izquieda
			if( o.isIzquierdaNull() ) {
				return this.getDerecha().compareTo(o.getDerecha());
			}
			//o es mayor
			return 1;
		}
		
		// Caso 2: derecha nula
		if( this.isDerechaNull() ) {
			//O no es nulo
			if( o.isNotNull() ) {
				return 1;
			}
			//Si las izquierdas son nulas y la derecha otra no
			if( (this.isIzquierdaNull() && o.isIzquierdaNull()) && !o.isDerechaNull() ) {
				return 1;
			}
			
			//Izquierda nula			
			if( this.isIzquierdaNull() ) {
				return 1;
			}
			
			//O izq nula
			if( o.isIzquierdaNull() ) {
				return -1;
			}
			
			//O derecha nula
			if( o.isDerechaNull() ) {
				return this.getIzquierda().compareTo(o.getIzquierda());
			}
			
			if( this.getIzquierda().equals(o.getIzquierda()) && !o.isDerechaNull()) {
				return 1;
			}
		}
		
		// Caso 3: o izquierda nula
		if( o.isIzquierdaNull() ) {
			//Si la izquierda no es nula
			if( !this.isIzquierdaNull() ) {
				return -1;
			}
			// Si los dos no son nulos
			if( this.isNotNull() ) {
				return -1;
			}
			//Si izquieda es nula y derecha no
			if( this.isIzquierdaNull() && !this.isDerechaNull() ) {
				//O derecha es nulo
				if( o.isDerechaNull() ) {
					return -1;
				}
				return this.getDerecha().compareTo(o.getDerecha());				
			}
			//Todo nulo y o derecha no es nulo
			if( this.isNull() && !o.isDerechaNull() ) {
				return 1;
			}
		}
		
		
		// Caso 4: o derecha nula
		if( o.isDerechaNull() ) {
			//No es nulo y o izquierda es nula
			if( this.isNotNull() && o.isIzquierdaNull() ) {
				return -1;
			}
			//Comparamos izquierdas
			if( !this.isIzquierdaNull()) {
				return this.getIzquierda().compareTo(o.getIzquierda());
			}

			if(o.isIzquierdaNull()) {
				return 1;
			}
		}	
		
		return 0;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return new HashCodeBuilder(17, 37)
				.append(this.izquierda)
				.append(this.derecha)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		//Si los objetos son iguales
		if (this == obj) {
			return true;
		} 
		//Si el objeto es nulo
		if (obj == null) {
			return false;
		} 
		//Si no coinciden las clases
		if (this.getClass() != obj.getClass()) {
			return false;
		} 
		
		//Convertimos el objeto a Pareja
		@SuppressWarnings("unchecked")
		Pareja<I,D> other = (Pareja<I,D>) obj;
		
		return this.compareTo(other) == 0 ? true : false;
	}
	
	
	
}
