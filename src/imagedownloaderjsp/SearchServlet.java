package imagedownloaderjsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url"); 
		String [] images = getImages(url);
		request.setAttribute("images", images);
				
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/images.jsp");  
		requestDispatcher.forward(request, response); 
	}
	
	private String [] getImages (String url) throws IOException {
		List <String> urls = new ArrayList <> ();
		
		// The connect() method creates a new connection, and get() fetches and parses a HTML file
        Document doc = Jsoup.connect(url).get();
        
        // Returns a list of matching elements 
        Elements links = doc.select("a[href]");
        Elements imports = doc.select("link[href]");
        Elements images = doc.select("img[src]");
        
        // Extract attributes from elements
        // To get an absolute URL, there is a attribute key prefix abs:
        // that will cause the attribute value to be resolved against the document's base URI 
        for (Element el: links) {
            String imageUrl = el.attr("abs:href");
            if(imageUrl.toLowerCase().endsWith(".jpg")||imageUrl.toLowerCase().endsWith(".png")) {
            	urls.add(imageUrl);
            }
        }
        
        for (Element el: imports) {
            String imageUrl = el.attr("abs:href");
            if(imageUrl.toLowerCase().endsWith(".jpg")||imageUrl.toLowerCase().endsWith(".png")) {
            	urls.add(imageUrl);
            }
        }
        
        for (Element el: images) {
            String imageUrl = el.attr("abs:src");
            urls.add(imageUrl);
        }
		
	    return urls.toArray(new String[urls.size()]);  
	}

}
