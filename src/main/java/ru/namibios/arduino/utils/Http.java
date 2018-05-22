package ru.namibios.arduino.utils;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;

public class Http {
	
	private static final String AUTH_URL = "http://%s/fishingserver/authorized";
	private static final String KAPCHA_URL = "http://%s/fishingserver/kapcha";
	private static final String BYTE_KAPCHA_URL = "http://%s/fishingserver/byte_kapcha";
	private static final String UPLOAD_IMAGE_URL = "http://%s/fishingserver/upload";
	
	private static final String TELEGRAM_ALARMER_BOT_URL = "https://alarmerbot.ru";
	
	private HttpClient httpClient;
	
	private HttpResponse response;

	public Http() {
		httpClient = HttpClients.createDefault();
	}
	
	public String parseKapcha(String key, String matrix) throws ClientProtocolException, IOException{
		
		HttpPost post = Builder.config().setUrl(String.format(KAPCHA_URL, Application.getInstance().HTTP_SERVER()))
				.setParameter(new BasicNameValuePair("HASH", key))
				.setParameter(new BasicNameValuePair("MATRIX", matrix))
				.build();

		response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}
	
	public String parseByteKapcha(String key, byte[] kapcha) throws ClientProtocolException, IOException{
		
		HttpPost post = new HttpPost(String.format(BYTE_KAPCHA_URL, Application.getInstance().HTTP_SERVER()));
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addTextBody("HASH", key, ContentType.TEXT_PLAIN);
		builder.addBinaryBody("SCREEN", kapcha, ContentType.DEFAULT_BINARY, "file.ext");
		 
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		
		response = httpClient.execute(post);
		entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8").trim();
	}
	
	public String auth(String key) throws ClientProtocolException, IOException{
		
		HttpPost post = Builder.config().setUrl(String.format(AUTH_URL,Application.getInstance().HTTP_SERVER()))
				.setParameter(new BasicNameValuePair("HASH", key))
				.build();

		response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8").trim();
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
	
	public void uploadImage(String key, BufferedImage image) throws ClientProtocolException, IOException{
		
		HttpPost post = new HttpPost(String.format(UPLOAD_IMAGE_URL, Application.getInstance().HTTP_SERVER()));
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addTextBody("HASH", key, ContentType.TEXT_PLAIN);
		builder.addBinaryBody("SCREEN", ImageUtils.imageToBytes(image), ContentType.DEFAULT_BINARY, "file.ext");
		 
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		
		response = httpClient.execute(post);
		
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException, AWTException {
		Screen screen = new Screen("/home/symbios/fishingserver/resources/kapcha/13.jpg");
		
		ImageParser parser = new ImageParser(screen);
		parser.parse(Screen.GRAY);
		
		Http http = new Http();
		String nn = http.parseByteKapcha(Application.getInstance().HASH(), screen.toByteArray());
		String alg = http.parseKapcha(Application.getInstance().HASH(), JSON.getInstance().writeValueAsString(parser.getImageMatrix()));
		System.out.println("NN: " + nn);
		System.out.println("Alg: " + alg);
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