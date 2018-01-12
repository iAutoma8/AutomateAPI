package tweet;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TwitterPost {
	
	String consumerKey = "W019oJQKoBub4xGSVErWCYvvG";
	String consumerSecret = "tM8qsmXBrn8VxlolhI215n98dh0pjhC9yro5EAKVS7HNEoOuRL";
	String accessToken = "90011222-2pUEVaLCfWrSfjZ1oQYBsVKd9bX44iTo9MywyTs1g";
	String accessSecret = "KoP8t6EN9JnZBSHZptKOjce1zs07ebMisiisLcSPcqjgc";

	 
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";

	}

	@Test
	public void postTweet() {
		
		Response res=given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
		.queryParam("status", "Sample API Tweet again")
		.when()
		.post("/update.json")
		.then().extract().response();
		//.statusCode(200).extract().response();
		 
		String  id=res.path("id_str");
		System.out.println("ID: " +id);
		
		String resp=res.asString();
		System.out.println(resp);
		JsonPath jspath=new JsonPath(resp);
		String namex=jspath.get("user.name");
		System.out.println("Name: " +namex);
		
		

	}

}
