package RestAssuredSpotify.Spotify;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import java.net.http.HttpRequest;

public class SpotifyApi {
	String token="";
    String userId;
    
	@BeforeTest
	public void setup() {
		token="Bearer BQByBmXYCRKoVtXPYjyinwYpl9hB6iTIiisBN_4fQhcCcmKgwVKQ2RagACsVL_Zw7-imXr43-XGaOhCBZR1AVYDuB7umFa9XQsEhpfTgiPKJ7mKEnQmRtZaK-jcnS_SZucHlN9bKu7XCOXdZ-kwTPElEqu-x4xGpRIx6GaJcWberqAoHtFtDDp6ncBgXwx-xvrIS6M3aF3KC4Hc01rh3bhI2_TIADldeZbUNJrnWHBd-LtlcUvKelGUk5i7ae6T-E-6VbEBa6KizRv-i3HCcjq-AtLWG2dvuOOBgE7MjQw";
	}
	
@Test
public void testGET_CurrentUserProfile_ShouldReturn_StatusCode200() {
	Response response = given().contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .header("Authorization", token)
               .get("https://api.spotify.com/v1/me");
	response.prettyPrint();
    int statusCode = response.getStatusCode();
	System.out.println("status code is:" + statusCode);
    Assert.assertEquals(statusCode, 200);
}

@Test
public void testGET_CurrentUserProfileId_ShouldReturn_StatusCode200() {
	Response response = given().contentType(ContentType.JSON)
    .accept(ContentType.JSON)
    .header("Authorization", token)
    .get("https://api.spotify.com/v1/me");
	userId = response.then().extract().path("id");
    System.out.println("user Id:=" +userId);
    response.prettyPrint();
    response.then().statusCode(200);
}

@Test
public void testPost_CreateAPlaylist_Should_ReturnStatusCode201() {
	RequestSpecification httpRequest = RestAssured.given();
	JSONObject requestParam = new JSONObject();
	requestParam.put("name", "Vijju playlist");
	requestParam.put("description", "Second playlist description");
	requestParam.put("public", "false");
	httpRequest.header("Authorization", token);
	httpRequest.body(requestParam.toString());
	Response response = httpRequest.request(Method.POST, "https://api.spotify.com/v1/users/31jw2bkmspwjjdazoaufm3kio6ei/playlists");
	String responseBody = response.getBody().asString();
	System.out.println("response body is:" + responseBody);
	int statusCode = response.getStatusCode();
    System.out.println("status code is:" + statusCode);
    Assert.assertEquals(statusCode, 201);
}

@Test
public void testPut_ChangePlaylistDetails_Should_ReturnStatusCode200() {
	RequestSpecification httpRequest = RestAssured.given();
	JSONObject requestParam = new JSONObject();
	requestParam.put("name", "Vijji playlist");
	requestParam.put("description", "Updated playlist description");
	httpRequest.header("Authorization", token);
	httpRequest.body(requestParam.toString());
	Response response = httpRequest.request(Method.PUT, "https://api.spotify.com/v1/playlists/3yPNJ6pnfklIUVe9LLlKXW");
	String responseBody = response.getBody().asString();
	System.out.println("response body is:" + responseBody);
	int statusCode = response.getStatusCode();
	System.out.println(response.asString());
    Assert.assertEquals(statusCode, 200);
   
}
@Test
public void testPost_AddItemstoPlaylist_Should_ReturnStatusCode201() {
	RequestSpecification httpRequest = RestAssured.given();
	httpRequest.queryParam("uris","spotify:track:7sVEoPc5ZUUQerRkgTypUZ");
	httpRequest.header("Authorization", token);
	Response response = httpRequest.request(Method.POST, "https://api.spotify.com/v1/playlists/3yPNJ6pnfklIUVe9LLlKXW/tracks");
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
	
}

@Test
public void testDELETE_RemovePlaylistItems_ShouldReturn_StatusCode200() {
	Response response = given().contentType(ContentType.JSON)
    .accept(ContentType.JSON)
    .header("Authorization", token)
    .delete("https://api.spotify.com/v1/playlists/3yPNJ6pnfklIUVe9LLlKXW/tracks");
	response.prettyPrint();
	int statusCode = response.getStatusCode();
	System.out.println("Status code is :=" + statusCode);
	Assert.assertEquals(statusCode, 200);
    
}

}








