package tweet;

import org.omg.Messaging.SyncScopeHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TwitterResponse {
	String consumerKey = "W019oJQKoBub4xGSVErWCYvvG";
	String consumerSecret = "tM8qsmXBrn8VxlolhI215n98dh0pjhC9yro5EAKVS7HNEoOuRL";
	String accessToken = "90011222-2pUEVaLCfWrSfjZ1oQYBsVKd9bX44iTo9MywyTs1g";
	String accessSecret = "KoP8t6EN9JnZBSHZptKOjce1zs07ebMisiisLcSPcqjgc";

	
	@BeforeClass
	public void setup() {
		RestAssured.basePath="";
		RestAssured.baseURI="";
		
		
	}
	
	
	@Test
	public void getResponse()
	{
		Response res=given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
		.when()
		.get()
		.then()
		.extract().response();
		
		
		String id= res.path("id_str");
		System.out.println(id);
	
		
		
	}
	

}
