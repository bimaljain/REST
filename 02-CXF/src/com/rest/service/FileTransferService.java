package com.rest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component("fileTransferService")
@Path("/file")
public class FileTransferService {	
	private static final String FILE_PATH = "C:\\_Bimal\\DevelopmentKit\\_Workspaces\\RestfulWS_Workspace\\ItemList.txt";
	private static final String IMAGE_PATH = "C:\\_Bimal\\DevelopmentKit\\_Workspaces\\RestfulWS_Workspace\\Cherry-icon.png";
	private static final String PDF_PATH = "C:\\_Bimal\\DevelopmentKit\\_Workspaces\\RestfulWS_Workspace\\Document1.pdf";
	private static final String EXCEL_PATH = "C:\\_Bimal\\DevelopmentKit\\_Workspaces\\RestfulWS_Workspace\\data.xlsx";

	// http://localhost:8080/01-Rest/rest/file/download/image
	@GET
	@Path("/download/log")
	@Produces("text/plain")
	public Response getTextFile() { 
		File file = new File(FILE_PATH); 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"file_from_server.log\"");
		return response.build(); 
	}

	@GET
	@Path("/download/image")
	@Produces("image/png")
	public Response getImageFile() { 
		File file = new File(IMAGE_PATH); 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition","attachment; filename=image_from_server.png");
		return response.build(); 
	}

	@GET
	@Path("/download/pdf")
	@Produces("application/pdf")
	public Response getPDFFile() { 
		File file = new File(PDF_PATH); 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition","attachment; filename=new-android-book.pdf");
		return response.build(); 
	}

	@GET
	@Path("/download/excel")
	@Produces("application/vnd.ms-excel")
	public Response getFile() { 
		File file = new File(EXCEL_PATH); 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition","attachment; filename=new-excel-file.xlsx");
		return response.build(); 
	}

	// http://localhost:8080/01-Rest/upload.html
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition uploadedfileDetail) { 
		String uploadedFileLocation = "C:\\_Bimal\\DevelopmentKit\\_Workspaces\\RestfulWS_Workspace\\" + uploadedfileDetail.getFileName();
		writeToFile(uploadedInputStream, uploadedFileLocation); 
		return Response.status(200).entity("File uploaded to : " + uploadedFileLocation).build(); 
	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) { 
		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024]; 
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) { 
			e.printStackTrace();
		} 
	} 
}