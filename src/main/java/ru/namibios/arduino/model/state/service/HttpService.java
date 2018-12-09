package ru.namibios.arduino.model.state.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.command.ShortCommand;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;

public class HttpService {

    private static final Logger LOG = Logger.getLogger(HttpService.class);
	
	private static final String TELEGRAM_ALARMER_URL = "https://alarmerbot.ru";
	private static final String BYTE_CAPTCHA_URL = "http://%s/fishingserver/captcha/decode";
    private static final String MARK_FAILURE_STATUS_URL = "http://%s/fishingserver/captcha/status";
    private static final String LAST_RELEASE_URL = "https://api.github.com/repos/Symb1OS/blackdesert-fishbot/releases/latest";

    private HttpClient httpClient;
	
	private HttpResponse httpResponse;

	public HttpService() {

        SSLContext tls = getSSLContext();

        httpClient = HttpClients.custom().setSSLContext(tls).build();
    }

    private SSLContext getSSLContext() {

        SSLContext ctx = null;

        try {

            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            LOG.error(ExceptionUtils.getString(e));
        }

        return ctx;
    }

	public void sendTelegram(String key, String message) throws IOException{
		
		HttpPost post = Builder.config().setUrl(TELEGRAM_ALARMER_URL)
				.setParameter(new BasicNameValuePair("key", key))
				.setParameter(new BasicNameValuePair("message", message))
				.build();

		httpClient.execute(post);
	}

	public String getLastReleaseTag() throws IOException{

        HttpGet get = new HttpGet(LAST_RELEASE_URL);

        httpResponse = httpClient.execute(get);
        HttpEntity entity = httpResponse.getEntity();

        String response = EntityUtils.toString(entity, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(response, new TypeReference<Map<String, Object>>(){});
        String tag = (String) map.get("tag_name");

        return tag;
    }

	public String parseByteCaptcha(String key, String name,  byte[] captcha) throws IOException {

		HttpPost post = new HttpPost(String.format(BYTE_CAPTCHA_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addTextBody("USER", key, ContentType.TEXT_PLAIN);
		builder.addBinaryBody("SCREEN", captcha, ContentType.DEFAULT_BINARY, "file.ext");

		if (name != null) builder.addTextBody("NAME", name, ContentType.TEXT_PLAIN);

		HttpEntity entity = builder.build();
		post.setEntity(entity);

		httpResponse = httpClient.execute(post);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {

			LOG.debug("Status code: " + statusCode );
			entity = httpResponse.getEntity();
			return EntityUtils.toString(entity, "UTF-8").trim();
		} else {

			LOG.error("Status code: " + statusCode );
			return ShortCommand.IGNORE.getKey();
		}

	}

	public void markFail(String name, String status) throws IOException{

		HttpPost post = new HttpPost(String.format(MARK_FAILURE_STATUS_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

		ArrayList<BasicNameValuePair> postParameters = new ArrayList<>();
		postParameters.add(new BasicNameValuePair("NAME", name));
		postParameters.add(new BasicNameValuePair("STATUS", status));
		post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));

		httpResponse = httpClient.execute(post);

	}

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

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

}