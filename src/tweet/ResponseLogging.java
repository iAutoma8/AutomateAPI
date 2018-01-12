package tweet;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ResponseLogging {
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
	public void testMethod() {
		given().log().ifValidationFails().auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
				.queryParam("status", "API Tweet Sample11").when().post("/update.json").then().log().ifError().statusCode(200);

	}

}
