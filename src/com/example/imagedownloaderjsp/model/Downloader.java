package com.example.imagedownloaderjsp.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Downloader {
	
	public ByteArrayOutputStream createArchive (String [] urls) throws IOException {
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
