package com.informe.servicios;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.informe.utiles.enumerados.TipoInforme;


@Service
public class GeneradorInformeService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void generarInformePdf( @RequestBody Map<String,Object> datos, OutputStream salida ) throws Exception {
		
		logger.debug("Entramos en el metodo generarInformePdf()" );
		
		try {
			
			if(datos == null ) {
				throw new Exception("Error, no han llegado los datos necesarios para realizar el informe");
			}
			
			TipoInforme tipoInforme = (TipoInforme) datos.get("tipoInforme");
			
			String nombrePlantilla = this.nombrePlantillaPorTipo(tipoInforme);
			
			logger.debug("La plantilla utilizada es: " + nombrePlantilla);
			
			ByteArrayOutputStream _os = new ByteArrayOutputStream();
			
			byte[] pdfData = _os.toByteArray();
			
			pdfData.notify();
			
		} catch(Exception e) {
			
			logger.error("Error, no se ha podido generar el informe");
			
			e.printStackTrace();
			
			throw new Exception("Error, no se ha podido generar el informe");
		}
	}

	
	public String nombrePlantillaPorTipo(TipoInforme tipoInforme) {
		
		if( tipoInforme == null) {
			
			tipoInforme = TipoInforme.SIN_TIPO;
		}
		
		switch(tipoInforme) {
		
			case GASTOS:
				
				return "gasto.pdf";
			
			case INGRESOS:
				
				return "ingresos.pdf";
				
			case RESULTADOS:
				
				return "resultado.pdf";
				
			case COMPLETO:
				
				return "completo.pdf";
				
			default:
				
				return "";
		}
	}
	
	
	
}
