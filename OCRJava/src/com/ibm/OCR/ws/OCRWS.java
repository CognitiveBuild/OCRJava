package com.ibm.OCR.ws;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.ibm.OCR.OCRHelper;
import com.ibm.waston.WastonSpeechHelper;

@Path("/secure/ocr")
public class OCRWS {
	private OCRHelper helper = new OCRHelper();

	@POST
	@Path(value = "/recognize")
	@Consumes(value = "image/*")
	public String recognizeText(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws IOException {

		response.setCharacterEncoding("UTF-8");
		Integer chunk = null;
		String tempFileName = null;
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println("uploadPath= " + uploadPath);
		File up = new File(uploadPath);
		String fullPath = null;
		Map<String, String> result = new HashMap<String, String>();
		if (!up.exists()) {
			up.mkdir();
		}

		OutputStream out = null;
		InputStream in = null;

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(2048);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(10 * 1024 * 1024);
				List<FileItem> items = upload.parseRequest(request);
				// should be one file only.
				File savedFile = null;
				for (FileItem item : items) {
					if (item.isFormField()) {
						if (item.getFieldName().equals("name")) {
							tempFileName = item.getString();

						} else if (item.getFieldName().equals("chunk")) {
							chunk = Integer.parseInt(item.getString());

						}
					} else {
						if (tempFileName != null) {
							// fileName = item.getName();
							String chunkName = tempFileName;
							if (chunk != null) {
								chunkName = chunk + "_" + tempFileName;
							}
							savedFile = new File(uploadPath, chunkName);
							item.write(savedFile);
						}
					}
				}

				String message = helper.recognizeText(savedFile);

				in = new WastonSpeechHelper().getVoice(message);

				response.setHeader("content-disposition",
                        "attachment; filename=result.wav");
				response.setContentType("audio/wav");
				out = response.getOutputStream();
				byte[] buffer = new byte[2048];
				int read;
				while ((read = in.read(buffer)) != -1) {
					out.write(buffer, 0, read);
				}
				
				out.flush();

				result.put("message", message);
				result.put("result", "success");
			} catch (FileUploadException e) {
				deleteFile(fullPath);
				result.put("result", "Upload file failed");
			} catch (Exception e) {
				deleteFile(fullPath);
				result.put("result", "convert image to voice failed");
			} finally {
				try {
					if (out != null)
						out.close();
					if (in != null);
						in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return JSON.toJSONString(result);
	}

	private void deleteFile(String path) {
		if (path == null) {
			return;
		}
		File f = new File(path);
		if (f.exists() && f.isFile()) {
			f.delete();
		}
	}
}
