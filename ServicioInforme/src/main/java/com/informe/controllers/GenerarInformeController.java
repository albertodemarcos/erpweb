package com.informe.controllers;

import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.informe.servicios.GeneradorInformeService;
import com.informe.utiles.AccionRespuesta;



@Controller
@RequestMapping("/generarInforme")
public class GenerarInformeController {

	@Autowired
	private GeneradorInformeService generadorInformeService;
	
	
	@PostMapping( { "/gastos", "/ingresos", "/resultados", "/completo" })
	@ResponseBody void getGenerarInformeGastosPdf( @RequestBody AccionRespuesta informe, HttpServletResponse response ) throws Exception {
		
		HashMap<String, Object> mapaDatos = informe.getData();
		
		OutputStream salida = response.getOutputStream();
		
		this.generadorInformeService.generarInformePdf(mapaDatos, salida);
	}
	
	
	
}
