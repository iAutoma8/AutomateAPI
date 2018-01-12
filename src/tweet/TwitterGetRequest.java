package tweet;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TwitterGetRequest {
	
	String consumerKey = "W019oJQKoBub4xGSVErWCYvvG";
	String consumerSecret = "tM8qsmXBrn8VxlolhI215n98dh0pjhC9yro5EAKVS7HNEoOuRL";
	String accessToken = "90011222-2pUEVaLCfWrSfjZ1oQYBsVKd9bX44iTo9MywyTs1g";
	String accessSecret = "KoP8t6EN9JnZBSHZptKOjce1zs07ebMisiisLcSPcqjgc";
	String TweetID;
	 
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
		 
		TweetID=res.path("id_str");
		System.out.println("ID: " +TweetID);

	}
	
	@Test(dependsOnMethods= {"postTweet"})
	public void readTweet() {
		
		Response res=given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
		.queryParam("id", TweetID)
		.when()
		.get("/show.json")
		.then().extract().response();
		 String text=res.path("text");
		 System.out.println("TWEET: " +text);

	}
	
	@Test(dependsOnMethods= {"postTweet","readTweet"})
	public void deleteTweet() {
		
		given()
		.auth()
		.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
		.queryParam("id", TweetID)
		.when()
		.post("/destroy.json")
		.then().statusCode(200);

	}

}
