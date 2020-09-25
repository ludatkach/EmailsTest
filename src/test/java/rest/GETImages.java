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
	
	public String getRandomCatURL() {
        HttpClient client = HttpClient.newBuilder().followRedirects(Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://aws.random.cat/meow"))
                .build();

		try {
	        HttpResponse<String> response;
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
			JSONObject responseJSON = new JSONObject(response.body());
			return responseJSON.getString("file");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "";
	}
	
	public String getRandomDogURL() {
		HttpClient client = HttpClient.newBuilder().followRedirects(Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://random.dog/woof.json"))
                .build();

		try {
	        HttpResponse<String> response;
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
			JSONObject responseJSON = new JSONObject(response.body());
			return responseJSON.getString("url");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "";
	}
	
	public String getRandomFoxURL() {
		HttpClient client = HttpClient.newBuilder().followRedirects(Redirect.NORMAL).build();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://randomfox.ca/floof/"))
                .build();

		try {
	        HttpResponse<String> response;
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
			JSONObject responseJSON = new JSONObject(response.body());
			return responseJSON.getString("image");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "";
	}
	
	@Test
	void TestGetCatUrl() {
		System.out.println(getRandomCatURL());
	}
		
	@Test
	void TestGetDogUrl() {
		System.out.println(getRandomDogURL());
	}
	
	@Test
	void TestGetFoxUrl() {
		System.out.println(getRandomFoxURL());
	}
	
	

//	/*
//	 * @Test void GETCatUrl() { baseURL = "http://aws.random.cat"; client =
//	 * WebTestClient.bindToServer() .baseUrl(baseURL) .build();
//	 * 
//	 * EntityExchangeResult<String> catUrl = client.get() .uri("/meow")
//	 * .accept(MediaType.APPLICATION_JSON) .exchange() .expectStatus() .isOk()
//	 * .expectHeader().contentType(MediaType.APPLICATION_JSON)
//	 * .expectBody(String.class) .returnResult();
//	 * System.out.println(catUrl.getResponseBody()); }
//	 */
//	
//	@Test
//	void GETDogUrl() {
//		baseURL = "https://random.dog";
//		client = WebTestClient.bindToServer().baseUrl(baseURL).build();
//
//		try {
//			EntityExchangeResult<String> dogUrl1 = client.get().uri("/woof.json")
//					.exchange()
//					.expectBody(String.class).returnResult();
//			
//			JSONObject responseJSON = new JSONObject(dogUrl1.getResponseBody());
//			String sURL = responseJSON.getString("url");
//			System.out.println(sURL);
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		// expectStatus().isOk().expectHeader().
//		EntityExchangeResult<String> dogUrl = client.get().uri("/woof.json").accept(MediaType.APPLICATION_JSON)
//				.exchange().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody(String.class)
//				.returnResult();
//
//		JSONObject responseJSON = new JSONObject(dogUrl.getResponseBody());
//		String sURL = responseJSON.getString("url");
//		System.out.println(sURL);
//	}
//
//	@Test
//	void GETFoxUrl() {
//		baseURL = "http://randomfox.ca";
//		client = WebTestClient.bindToServer().baseUrl(baseURL).build();
//
//		EntityExchangeResult<String> foxUrl = client.get().uri("/floof/").accept(MediaType.APPLICATION_JSON).exchange()
//				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody(String.class)
//				.returnResult();
//		System.out.println(foxUrl.getResponseBody());
//	}

}
