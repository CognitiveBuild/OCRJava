package com.ibm.OCR.ws.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.ibm.OCR.OCRHelper;
import com.ibm.waston.WastonSpeechHelper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.RecognizedText;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class OCRTest {
	@Test
	public void testRecognizeText() {
		String filePath = "/Users/freddy/Documents/aa.png";
		String buildPath = this.getClass().getClassLoader().getResource("").getPath();
		new OCRHelper().recognizeText(new File(filePath),buildPath.substring(0, buildPath.indexOf("/WEB-INF/classes/")));
	}

	@Test
	public void testTextToSpeech() {
		InputStream in = new WastonSpeechHelper().getVoice("This is a bluemix innovation day testing");
		inputstreamtofile(in, new File("/Users/freddy/Documents/test.wav"));
		System.out.println("done");
	}

	public void inputstreamtofile(InputStream ins, File file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (ins != null)
					ins.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Test
	public void testVisualRecogintion(){
		
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey("44c703d3d2488f684473e57d162b8c94e128e633");
		service.setEndPoint("https://gateway-a.watsonplatform.net/visual-recognition/api");

		System.out.println("Classify an image");
		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
		    .images(new File("/Users/freddy/Documents/bb.png"))
		    .build();
		
		VisualClassification result = service.classify(options).execute();
		
		System.out.println(result);
		
		VisualRecognitionOptions options1 = new VisualRecognitionOptions.Builder().images(new File("/Users/freddy/Documents/bb1.png"))
		    .build();
		RecognizedText rt = service.recognizeText(options1).execute();
		
		System.out.println(rt);
	}
}
