package com.ibm.OCR.ws.test;

import java.io.File;

import org.junit.Test;

import com.ibm.OCR.OCRHelper;

public class OCRTest {
	@Test
	public void testRecognizeText(){
		String filePath = "/Users/freddy/Documents/aa.png";
    	new OCRHelper().recognizeText(new File(filePath));
	}
}
