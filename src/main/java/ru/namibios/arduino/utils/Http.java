package ru.namibios.arduino.utils;

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
import ru.namibios.arduino.model.Screen;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Http {
	
	private static final String TELEGRAM_ALARMER_BOT_URL = "https://alarmerbot.ru";
	private static final String BYTE_KAPCHA_URL = "http://%s/fishingserver/captcha/byte";

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

	public String parseByteKapcha(String key, byte[] captcha) throws IOException{

		HttpPost post = new HttpPost(String.format(BYTE_KAPCHA_URL, "192.168.0.220:8080"));

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addTextBody("USER", key, ContentType.TEXT_PLAIN);
		builder.addBinaryBody("SCREEN", captcha, ContentType.DEFAULT_BINARY, "file.ext");

		HttpEntity entity = builder.build();
		post.setEntity(entity);

		response = httpClient.execute(post);
		entity = response.getEntity();

		return EntityUtils.toString(entity, "UTF-8").trim();
	}
	
	private static class Builder {
		
		private HttpPost post;
		private ArrayList<BasicNameValuePair> postParameters;
		
		private Builder(){
			postParameters = new ArrayList<>();
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

	public static void main(String[] args) throws IOException {

		Screen screen = new Screen("resources/kapcha/20181020_195950_313.jpg");

		Http http = new Http();

		String key = http.parseByteKapcha(System.getProperty("user.name"), ImageUtils.imageToBytes(screen.getScreenShot()));
		System.out.println("key = " + key);

	}
}