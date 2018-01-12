package basic;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PlacesAddModel;

public class PostBasicPOJO {
	
	
	@BeforeClass
	public void setup()
	{
		RestAssured.basePath="/maps/api";
		RestAssured.baseURI="https://maps.googleapis.com";
		
	}
	
	
	
	@Test
	public void statusCodeVerificationPost()
	{
		
		Map<String,Double>locationMap=new HashMap<String,Double>();
		locationMap.put("lat", -33.8669710);
		locationMap.put("lng", 151.1958750);
		
		List<String>types=new ArrayList<>();
		types.add("shoe_store");
		
		
		PlacesAddModel places=new PlacesAddModel();
		places.setName("Google Shoes!");
		places.setAccuracy(50);
		places.setAddress("48 Pirrama Road, Pyrmont, NSW 2009, Australia");
		places.setPhone_number("(02) 9374 4000");
		places.setLocation(locationMap);
		places.setLanguage("en-AU");
		places.setTypes(types);
		places.setWebsite("\"http://www.google.com.au/");
		
		
		
		//Response res=
				given()
		.queryParam("key", "AIzaSyCIPdu7SXyV9TWzhs7A_QygOHlayJAocfc")
		.body(places)
		.when()
		.post("/place/add/json")
		//System.out.println(res.body().asString());
		.then()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON).and()
		.body("scope",equalTo("APP"))
		.body("status", equalTo("OK "));
		
	}

}
