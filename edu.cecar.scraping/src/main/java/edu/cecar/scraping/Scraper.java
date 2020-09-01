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

import java.io.IOException;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *  Clase que scrapea una pagina del sitio web del meridiano
 *  
 */

public class Scraper {
	
	private void scrapingPaginaMeridiano(String url) {
		
		try {
			
			Document document = Jsoup.connect(url).get();
			
			String titulo = document.select("h1").text();
			
			Elements contenido = document.select("div.News-content-description");
			
			System.out.println("Titulo:" +  titulo);
			
			System.out.println("\n\nContenido:" +  contenido);
			
			for (Element element : contenido) {
				System.out.println(element.text());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		String url = "https://elmeridiano.co/noticia/capturado-en-tuchin-tenia-una-cuenta-pendiente-en-sucre";
		
		new Scraper().scrapingPaginaMeridiano(url);
		
	}
	
	

}
