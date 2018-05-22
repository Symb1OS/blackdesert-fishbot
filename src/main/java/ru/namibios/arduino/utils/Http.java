package ru.namibios.arduino.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Http {
	
	private static final String TELEGRAM_ALARMER_BOT_URL = "https://alarmerbot.ru";
	
	private HttpClient httpClient;
	
	private HttpResponse response;

	public Http() {
		httpClient = HttpClients.createDefault();
	}
	
	public String sendTelegram(String key, String message) throws ClientProtocolException, IOException{
		
		HttpPost post = Builder.config().setUrl(TELEGRAM_ALARMER_BOT_URL)
				.setParameter(new BasicNameValuePair("key", key))
				.setParameter(new BasicNameValuePair("message", message))
				.build();

		response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8").trim();
	}
	
	private static class Builder {
		
		private HttpPost post;
		private ArrayList<BasicNameValuePair> postParameters;
		
		private Builder(){
			postParameters = new ArrayList<BasicNameValuePair>();
		}
	
		private static Builder config() {
			return new Builder();
		}
		
		private Builder setUrl(String url) {
			post = new HttpPost(url);
			return this;
		}
		
		private Builder setParameter(BasicNameValuePair value) {
			postParameters.add(value);
			return this;
		}
		
		private HttpPost build() throws UnsupportedEncodingException {
			post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			return post;
		} 
	}
}