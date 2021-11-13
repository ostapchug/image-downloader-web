package com.example.imagedownloaderjsp.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.imagedownloaderjsp.model.Downloader;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("images");
		
		String [] urls = str.split(",");
		Downloader downloader = new Downloader();
		ByteArrayOutputStream byteArrayOutputStream = downloader.createArchive(urls);
		
		response.setContentType("application/octet-stream");
		response.setContentLength(byteArrayOutputStream.size());
		response.addHeader("Content-Disposition", "attachment; filename=" + "images.zip");
		
		try (ServletOutputStream servletOutputStream = response.getOutputStream()){
			byteArrayOutputStream.writeTo(servletOutputStream);
		}
	}

}
