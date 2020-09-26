package rest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

class GETImages {

	private WebTestClient client;
	private String baseURL;
	
	public String getRandomURL( String url, String responseAttribute) {
        HttpClient client = HttpClient.newBuilder().followRedirects(Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

		try {
	        HttpResponse<String> response;
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
			JSONObject responseJSON = new JSONObject(response.body());
			return responseJSON.getString(responseAttribute);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "";
	}
	
	@Test
	void TestGetCatUrl() {
		System.out.println(getRandomURL("http://aws.random.cat/meow", "file"));
	}
		
	@Test
	void TestGetDogUrl() {
		System.out.println(getRandomURL("https://random.dog/woof.json", "url"));
	}
	
	@Test
	void TestGetFoxUrl() {
		System.out.println(getRandomURL("http://randomfox.ca/floof/", "image"));
	}
	

}
