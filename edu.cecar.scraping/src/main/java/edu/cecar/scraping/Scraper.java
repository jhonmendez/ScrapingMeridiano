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
			
			
			
			Elements div = document.select("div.News-content-description");
			Element element = div.get(0);
			
			String titulo = document.select("h1").text();
			String contenido = element.text();
			
			System.out.println("Titulo: " +  titulo);
			System.out.println("Contenido: " + contenido);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		String url = "https://elmeridiano.co/noticia/confirman-crimen-de-policia-en-el-bajo-cauca-antioque%C3%B1o-";
		
		new Scraper().scrapingPaginaMeridiano(url);
		
	}
	
	

}
