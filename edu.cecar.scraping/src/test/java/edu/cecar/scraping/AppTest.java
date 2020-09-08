/**
 * @Clase: AppTest.java
 * 
 * @version  1.0
 * 
 * @since 8/09/2020
 * 
 * @autor Ing. Jhon Mendez
 *
 * @Copyrigth: Legal Credit Solution
 */

package edu.cecar.scraping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.cecar.componentes.basesdedatos.ConectarMySQL;

/**
 *  Descripción de la clase
 *  
 */

public class AppTest {
	
	@Test
	public void scrapingPaginaMeridianoTest() {
		
		//Se define los datos e informacion esperada
		String tituloEsperado = "Confirman crimen de policía en el Bajo Cauca antioqueño";
		String contenidoEsperado = "Jaime Luis Castaño Castillo, fue el miembro de la Policía asesinado en el sector de la Gloria en la vía que conduce de Caucasia a El Bagre.\r\n" + 
				"\r\n" + 
				"El uniformado murió al trasladarlo al hospital César Uribe Piedrahita de Caucasia. Inicialmente se hablaba de dos policías muertos, pero se confirmó que fue uno y dos más están gravemente heridos.\r\n" + 
				"\r\n" + 
				"El vehículo en el que se movilizaban los uniformados quedó casi destruido al ser atacado a bala por miembros de una de las bandas criminales que operan en esa región.";
		
		
		contenidoEsperado = contenidoEsperado.replaceAll("\r|\n", " ").replaceAll("( )+", " ");
		
		//Se obtiene los datos e información resultante del software
		try {
			
			
			ConectarMySQL.getConectarMySQL("127.0.0.1", "analisis", "root", "");
			String url = "https://elmeridiano.co/noticia/confirman-crimen-de-policia-en-el-bajo-cauca-antioque%C3%B1o-";
			Scraper scraper = new Scraper();
			scraper.scrapingPaginaMeridiano(url);
			
			PreparedStatement consultarArticulo = ConectarMySQL.getConexion().prepareStatement("select titulo, contenido " + 
																								"from articulos " + 
																								"order by identificador desc " + 
																								"limit 1");
			ResultSet resultado = consultarArticulo.executeQuery();
			resultado.next();
			
			String tituloObtenido = resultado.getString(1);
			String contenidoObtenido = resultado.getString(2);
			
			assertEquals("El titulo del articulo no corresponde",tituloEsperado,tituloObtenido);
			assertEquals("El contenido del articulo no corresponde",contenidoEsperado,contenidoObtenido);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error de conexion de la bases de datos " + e);
			
		}

		

		
		
	}

}
