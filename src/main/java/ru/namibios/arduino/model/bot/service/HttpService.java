package ru.namibios.arduino.model.bot.service;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.User;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.command.ShortCommand;
import ru.namibios.arduino.utils.AppUtils;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.JSON;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static ru.namibios.arduino.config.Message.SERVER_NOT_AVAILABLE;

public class HttpService {

    private static final Logger LOG = Logger.getLogger(HttpService.class);
	
	private static final String TELEGRAM_ALARMER_URL = "https://alarmerbot.ru";

	private static final String TELEGRAM_MESSAGE_URL = "https://%s/fishingserver/message";
	private static final String TELEGRAM_PHOTO_URL = "https://%s/fishingserver/photo";

    private static final String USER_STATUS_URL = "https://%s/fishingserver/user/status";
    private static final String CLOSE_BOT = "https://%s/fishingserver/user/close";

	private static final String INFO_URL = "https://%s/fishingserver/info";
	private static final String BYTE_CAPTCHA_URL = "https://%s/fishingserver/captcha/decode";
    private static final String MARK_FAILURE_STATUS_URL = "https://%s/fishingserver/captcha/status";
    private static final String LAST_RELEASE_URL = "https://api.github.com/repos/Symb1OS/blackdesert-fishbot/releases/latest";

	private static final String CALL_URL = "https://%s/fishingserver/call";
    private static final String SYNC_URL = "https://%s/fishingserver/sync";

    private HttpClient httpClient;

    public HttpService() {

        SSLContext tls = getSSLContext();

        Header forwarded = new BasicHeader("X-FORWARDED-FOR", AppUtils.getForwaded());
        Header session = new BasicHeader("USER_SESSION", Application.SESSION);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Application.getInstance().HTTP_DEFAULT_CONNECT_TIMEOUT())
                .setSocketTimeout(Application.getInstance().HTTP_DEFAULT_SOCKET_TIMEOUT())
                .build();

        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setDefaultHeaders(Arrays.asList(forwarded, session))
                .setSSLContext(tls)
                .build();
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

	public void sendTelegramAlarmer(String key, String message) throws IOException{

		HttpPost post = Builder.config().setUrl(TELEGRAM_ALARMER_URL)
				.setParameter(new BasicNameValuePair("key", key))
				.setParameter(new BasicNameValuePair("message", message))
				.build();

        HttpResponse httpResponse = httpClient.execute(post);

        EntityUtils.consume(httpResponse.getEntity());
	}

    public void sendTelegramMessage(String key, String message) throws IOException{

        HttpPost post = Builder.config().setUrl(String.format(TELEGRAM_MESSAGE_URL, Application.getInstance().URL_CAPTCHA_SERVICE()))
                .setParameter(new BasicNameValuePair("key", key))
                .setParameter(new BasicNameValuePair("message", message))
                .build();

        HttpResponse httpResponse = httpClient.execute(post);

        EntityUtils.consume(httpResponse.getEntity());
    }

    public void sendTelegramPhoto(String key, byte[] photo) throws IOException{
        HttpPost post = new HttpPost(String.format(TELEGRAM_PHOTO_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        builder.addTextBody("key", key, ContentType.TEXT_PLAIN);
        builder.addBinaryBody("photo", photo, ContentType.DEFAULT_BINARY, "file.ext");

        post.setEntity(builder.build());

        HttpResponse httpResponse = httpClient.execute(post);
        EntityUtils.consume(httpResponse.getEntity());

    }

	public String getLastReleaseTag() throws IOException{

        HttpGet get = new HttpGet(LAST_RELEASE_URL);

        HttpResponse httpResponse = httpClient.execute(get);
        HttpEntity entity = httpResponse.getEntity();

        String response = EntityUtils.toString(entity, "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(response, new TypeReference<Map<String, Object>>(){});
        String tag = (String) map.get("tag_name");

        return tag;
    }

	public String parseByteCaptcha(String name,  byte[] captcha) throws IOException {

		HttpPost post = new HttpPost(String.format(BYTE_CAPTCHA_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		User user = Application.getUser();

		builder.addTextBody("USER", JSON.getInstance().writeValueAsString(user));
		builder.addBinaryBody("SCREEN", captcha, ContentType.DEFAULT_BINARY, "file.ext");
        builder.addTextBody("NAME", name, ContentType.TEXT_PLAIN);

		HttpEntity entity = builder.build();
		post.setEntity(entity);

        HttpResponse httpResponse;

        try {

            HttpClientContext context = new HttpClientContext();

            context.setRequestConfig(RequestConfig.custom()
                    .setSocketTimeout(Application.getInstance().HTTP_CAPTCHA_SOCKET_TIMEOUT())
                    .build());

            httpResponse = httpClient.execute(post, context);

        } catch (HttpHostConnectException e) {

            LOG.error(SERVER_NOT_AVAILABLE);

            return ShortCommand.IGNORE.getKey();
        }

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		entity = httpResponse.getEntity();
		String response = EntityUtils.toString(entity, "UTF-8").trim();
		if (statusCode == HttpStatus.SC_OK) {
		    return response;
		} else {

			LOG.error("Status code: " + statusCode );
			LOG.error(response);

			return ShortCommand.IGNORE.getKey();
		}

	}

    private void testCaptcha() throws IOException {
		HttpService httpService = new HttpService();

		String name = UUID.randomUUID().toString();
        String s = httpService.parseByteCaptcha(name, new Screen("resources/1.jpg").toByteArray());
        if (!s.isEmpty()) {
            httpService.markFail(name);
        }

	}

	public void markFail(String name) throws IOException{

		HttpPost post = new HttpPost(String.format(MARK_FAILURE_STATUS_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

		ArrayList<BasicNameValuePair> postParameters = new ArrayList<>();
		postParameters.add(new BasicNameValuePair("USER", JSON.getInstance().writeValueAsString(Application.getUser())));
		postParameters.add(new BasicNameValuePair("NAME", name));
		post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));

        HttpResponse httpResponse = httpClient.execute(post);

		EntityUtils.consume(httpResponse.getEntity());

	}

	public String getInfo() throws IOException {

		HttpGet get = new HttpGet(String.format(INFO_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

        HttpResponse httpResponse = httpClient.execute(get);
		HttpEntity entity = httpResponse.getEntity();

		return EntityUtils.toString(entity, "UTF-8");
	}

    public User getUserStatus(User user) throws IOException, URISyntaxException {

        HttpGet get = new HttpGet(String.format(USER_STATUS_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

        URI uri = new URIBuilder(get.getURI())
                .addParameter("USER", JSON.getInstance().writeValueAsString(user))
                .build();

        get.setURI(uri);

        HttpResponse httpResponse = httpClient.execute(get);
        HttpEntity entity = httpResponse.getEntity();
        String response = EntityUtils.toString(entity, "UTF-8");

        return JSON.getInstance().readValue(response, User.class);

    }

    public void close(int status) throws IOException {

        HttpPost post = new HttpPost(String.format(CLOSE_BOT, Application.getInstance().URL_CAPTCHA_SERVICE()));

        ArrayList<BasicNameValuePair> postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("USER", JSON.getInstance().writeValueAsString(Application.getUser())));
        postParameters.add(new BasicNameValuePair("STATUS", String.valueOf(status)));

        post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));

        HttpResponse httpResponse = httpClient.execute(post);

        EntityUtils.consume(httpResponse.getEntity());

    }

	public void call(String name) throws IOException {

		HttpPost post = Builder.config().setUrl(String.format(CALL_URL, Application.getInstance().URL_CAPTCHA_SERVICE()))
				.setParameter(new BasicNameValuePair("USER", JSON.getInstance().writeValueAsString(Application.getUser())))
				.setParameter(new BasicNameValuePair("NAME", name))
				.build();

        httpClient.execute(post, this::defaultHandleResponse);

	}

    public void sync(File file) throws IOException {

        HttpPost post = new HttpPost(String.format(SYNC_URL, Application.getInstance().URL_CAPTCHA_SERVICE()));

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        User user = Application.getUser();

        builder.addTextBody("USER", JSON.getInstance().writeValueAsString(user));
        builder.addBinaryBody("LOOT", file, ContentType.DEFAULT_BINARY, file.getName());

        HttpEntity reqEntity = builder.build();
        post.setEntity(reqEntity);

        HttpResponse httpResponse = httpClient.execute(post);

        HttpEntity entity = httpResponse.getEntity();
        LOG.info(EntityUtils.toString(entity));

    }

    private Boolean defaultHandleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {

            String message = EntityUtils.toString(response.getEntity());
            LOG.error(message);

            return false;

        }

        return true;
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