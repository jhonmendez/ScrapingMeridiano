/**
 * @Clase: Scraper.java
 * 
 * @version  1.0
 * 
 * @since 1/09/2020
 * 
 * @autor Ing. Jhon Mendez
 *
 * @Copyrigth: CECAR
 */

package edu.cecar.scraping;

import java.sql.PreparedStatement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.cecar.componentes.basesdedatos.ConectarMySQL;

/**
 *  Clase que scrapea una pagina del sitio web del meridiano
 *  
 */

public class Scraper {



	public void scrapingPaginaMeridiano(String url) {

		try {

			Document document = Jsoup.connect(url).get();

			Elements div = document.select("div.News-content-description");
			Element element = div.get(0);

			String titulo = document.select("h1").text();
			String contenido = element.text();

			String insertarArticulo = "Insert into articulos values(null,?,?,sysdate())";
			
			PreparedStatement preparedStatement = ConectarMySQL.getConexion().
					                               prepareStatement(insertarArticulo);
			preparedStatement.setString(1, titulo);
			preparedStatement.setString(2, contenido);
			preparedStatement.execute();
			
			System.out.println("Articulo insertado de manera exitosa");


		} catch (Exception e) {
			e.printStackTrace();

		} 


	}


	public static void main(String[] args) {

		//Se realiza la conexion a bases de datos
		try {
			
			ConectarMySQL.getConectarMySQL("127.0.0.1", "analisis", "root", "");

			String url = "https://elmeridiano.co/noticia/confirman-crimen-de-policia-en-el-bajo-cauca-antioque%C3%B1o-";

			new Scraper().scrapingPaginaMeridiano(url);

		} catch (Exception e) {
			
			e.printStackTrace();

		}

	}



}
