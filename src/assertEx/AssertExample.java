package assertEx;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

public class AssertExample {

		String consumerKey = "W019oJQKoBub4xGSVErWCYvvG";
		String consumerSecret = "tM8qsmXBrn8VxlolhI215n98dh0pjhC9yro5EAKVS7HNEoOuRL";
		String accessToken = "90011222-2pUEVaLCfWrSfjZ1oQYBsVKd9bX44iTo9MywyTs1g";
		String accessSecret = "KoP8t6EN9JnZBSHZptKOjce1zs07ebMisiisLcSPcqjgc";

		@BeforeClass
		public void setup() {
			RestAssured.baseURI = "https://api.twitter.com";
			RestAssured.basePath = "/1.1/statuses";

		}

		@Test(enabled=false)
		public void testMethod() {
			given().log().ifValidationFails().auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
					.queryParam("screen_name", "Vinay Rana")
					.when().get("/user_timeline.json")
					.then().statusCode(200).body("user.name", hasItem("Vinay Rana"))
					.body("entities.hashtags[0].text", hasItem("GoldenGlobes"));
					

		}
		
		
		@Test
		public void testMethod1() {
			Response res= given().log().ifValidationFails().auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
					.queryParam("screen_name", "Vinay Rana")
					.when().get("/user_timeline.json")
					.then().extract().response();
		//	System.out.println(res.asString());
					JsonPath js=new JsonPath(res.asString());
					String s=js.get("entities[0].hashtags[0].text");
					
						System.out.println(s);
					

		}

	}

