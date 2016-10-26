package com.ibm.OCR;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRHelper {
    
    public String recognizeText( File imageFile, String dataPath) {
//    	System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
    	
//        File imageFile = new File(filePath);
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setLanguage("chi_sim");
        if (dataPath != null)
        instance.setDatapath(dataPath);
        String result = null;
        try {
           result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    
    
}
