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

public class OCRTest {
	@Test
	public void testRecognizeText() {
		String filePath = "/Users/freddy/Documents/aa.png";
		new OCRHelper().recognizeText(new File(filePath));
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
}
