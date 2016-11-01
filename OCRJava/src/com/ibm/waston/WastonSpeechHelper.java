package com.ibm.waston;

import java.io.InputStream;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public class WastonSpeechHelper {
	private static final String END_POINT_URL = "https://stream.watsonplatform.net/text-to-speech/api";
	
	/**
	 * TODO: Obtain your credentials from Bluemix
	 */
	private static final String TEXT_TO_SPEECH_USERNAME = "";
	private static final String TEXT_TO_SPEECH_PASSWORD = "";

	public InputStream getVoice(String message) throws Exception {
		if(TEXT_TO_SPEECH_USERNAME.isEmpty() || TEXT_TO_SPEECH_PASSWORD.isEmpty()){
			throw new Exception("Obtain your credentials from Bluemix");
		}
		TextToSpeech service = new TextToSpeech(TEXT_TO_SPEECH_USERNAME, TEXT_TO_SPEECH_PASSWORD);
		service.setEndPoint(END_POINT_URL);

		ServiceCall<InputStream> serviceCall = service.synthesize(message, Voice.EN_LISA, AudioFormat.WAV);

		InputStream in = serviceCall.execute();
		return in;
	}
}
