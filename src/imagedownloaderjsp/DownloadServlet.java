package imagedownloaderjsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("images");
		String [] urls = str.split(",");
		ByteArrayOutputStream byteArrayOutputStream = createArchive(urls);
		
		response.setContentType("application/octet-stream");
		response.setContentLength(byteArrayOutputStream.size());
		response.addHeader("Content-Disposition", "attachment; filename=" + "images.zip");
		
		try (ServletOutputStream servletOutputStream = response.getOutputStream()){
			byteArrayOutputStream.writeTo(servletOutputStream);
		}
	}
	
	private ByteArrayOutputStream createArchive (String [] urls) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)){
			for(int i = 0; i < urls.length; i++) {
				URL url = new URL(urls[i]);
				
				try (InputStream inputStream = url.openStream()){
					String fileName = url.getFile();
					ZipEntry zipEntry = new ZipEntry("image"+i+fileName.substring(fileName.lastIndexOf(".")));
					zipOutputStream.putNextEntry(zipEntry);
					
					byte[] b = new byte[2048];
		            int len;
		            
		            // Reads 2048 bytes from the input stream and stores them into the buffer array b
		            while ((len = inputStream.read(b)) != -1) {
		                
		                // Writes len bytes from the b byte array starting at offset 0 to this file output stream
		            	zipOutputStream.write(b, 0, len); 
		            }
		            zipOutputStream.closeEntry();
				}
				
			}
			
		}
		return byteArrayOutputStream;
		
	}

}
