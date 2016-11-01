#OCRJava

Optical Character Recognition Service. This is the project for Innovation Day practice as well as an important assets of Bluemix and Cognitive CoEs.

###Installation guide

- For macOS users, please install the `tesseract` first.
  
* Grant authority for the folder by changing owner or grant 766 in case you don't have the access
  `sudo chown -R $USER /usr/local`

* Install Xcode on the macOS, because `tesseract` needs to be compiled as it only provide the source code

* Install the tesseract
  `brew install tesseract`

* Some Chinese characters cannot be well recognized due to the font issue, so the `tesseract` need to be trained, please check the reference below (Chinese version)
  http://www.cnblogs.com/mjorcen/p/3800739.html

- When run the code in liberty as a web project.  you need to download jai_imageio-1.1.jar and put it into /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext folder. Maybe OSGI cause the problem.

- Add Text-to-Speech credentials in the code file: `/OCRJava/src/com/ibm/waston/WastonSpeechHelper.java`, obtain the credentials from Bluemix

#License
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
