package com.ibm.OCR.ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestServiceApplication extends Application {

   public Set<Class<?>> getClasses() {
       Set<Class<?>> classes = new HashSet<Class<?>>();
       classes.add(OCRWS.class);
       return classes;
   }
}
