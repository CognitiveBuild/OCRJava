# OCRJava
Optical Character Recognition Service. This is the project for Innovation Day practice as well as an important assets of Bluemix and Cognitive CoEs.

#License
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).


for mac environment,  you need to install the tesseract first.

#grant authority for the folder by changing owner. or grant 766
1. sudo chown -R $USER /usr/local

# if you don't have xcode, you need to install.  because, tesseract only provide source code. need xcode to compile
2. install xcode

#  install the tesseract
3. brew install tesseract

# some Chinese characters cannot be well recognized due to font, need to be trained. see below doc.
http://www.cnblogs.com/mjorcen/p/3800739.html
