package basic;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetRequestDemo {
	
	
	
	
	@BeforeClass
	public void setup()
	{
		RestAssured.basePath="/maps/api";
		RestAssured.baseURI="https://maps.googleapis.com";
		
	}
	
	
	
	@Test
	public void statusCodeVerification()
	{
		given()
		.param("units", "imperial")
		.param("origins", "Washington,DC")
		.param("destinations", "New+York+City,NY")
		.param("key", "AIzaSyCIPdu7SXyV9TWzhs7A_QygOHlayJAocfc")
		.when()
		.get("/distancematrix/json")
		.then()
		.statusCode(200)
		.and()
		.body("rows[0].elements[0].distance.text",equalTo("225 mi"))
		.contentType(ContentType.JSON);
		
		
	}
	
	
	@Test
	public void bodyVerification()
	{
		Response res= given()
		.param("units", "imperial")
		.param("origins", "Washington,DC")
		.param("destinations", "New+York+City,NY")
		.param("key", "AIzaSyCIPdu7SXyV9TWzhs7A_QygOHlayJAocfc")
		.when()
		.get("/distancematrix/json");
		System.out.println(res.body().asString());
		
		
		
	}

}
