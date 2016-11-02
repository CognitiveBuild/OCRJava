#OCRJava
Optical Character Recognition Service. This is the project for Innovation Day practice as well as an important assets of Bluemix and Cognitive CoEs.

[![Language: Java](https://img.shields.io/badge/language-java-black.svg?style=flat)](https://github.com/CognitiveBuild/OCRJava)
[![Watson: Text-to-Speech](https://img.shields.io/badge/watson-text--to--speech-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/OCRJava)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/CognitiveBuild/Chatbot/master/LICENSE)

#Prerequisite
* Register your [Bluemix](https://console.ng.bluemix.net/) account
* Create `Java Liberty Runtime`
* Connect Java Liberty Runtime with `Text to Speech` service
* Install Bluemix and CF CLI
* Install Xcode (macOS only) 
* Install the `tesseract`
```shell
	brew install tesseract`
```
* Install **Eclipse Java EE IDE for Web Developers** as your IDE
* Setup `Websphere Application Server Liberty Profile` or `Tomcat` in the **Eclipse** for debugging purpose

#Installation guide

* Install HomeBrew (macOS only)
```shell
	/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
* Install the `tesseract`
* Grant authority for the folder by changing owner or grant 766 in case you don't have the access
```shell
	sudo chown -R $USER /usr/local
```
* Install Xcode (macOS only), because `tesseract` needs to be compiled as it only provide the source code
* Some Chinese characters cannot be well recognized due to the font issue, so the `tesseract` need to be trained, please check the reference below (Chinese version) http://www.cnblogs.com/mjorcen/p/3800739.html
* Run git command to clone the sample code
```shell
	git clone git@github.com:CognitiveBuild/OCRJava.git
```
- When run the code in liberty as a web project.  you need to download jai_imageio-1.1.jar and put it into /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib/ext folder. Maybe OSGI cause the problem
- Add `Text-to-Speech` credentials in the code file: `/OCRJava/src/com/ibm/waston/WastonSpeechHelper.java`, obtain the credentials from [Bluemix](https://bluemix.net/) account
```java
	private static final String TEXT_TO_SPEECH_USERNAME = "your_username";
	private static final String TEXT_TO_SPEECH_PASSWORD = "your_password";
```
* **Right click** on the Chatbot project, choose `Run As` &gt; `Run on Server` to open the OCR sample application

#Dependencies
* Tesseract for Java
* Apache Common IO
* FastJSON
* jai-imageio
* JNA
* Apache HTTP Client (Upload)
* Watson Java SDK

#Issues
* jai_imageio-1.1.jar can't be loaded in the project dependencies if the Java Runtime is the Liberty Profile

#License
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
