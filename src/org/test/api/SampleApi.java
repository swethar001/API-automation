package org.test.api;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;


public class SampleApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("https://v6.exchangerate-api.com/v6/1fc80820c72b0163bc9c7536/latest/USD");
		
		try {
			
			int statusCode = response.getStatusCode();
			System.out.println("The Status code of GET response is: "+statusCode);
			Assert.assertEquals(statusCode, 200, "The response code is not 200. Verify the request again and proceed.");
			
			JsonPath js = response.jsonPath();
			HashMap<String, ?> cr_map = js.get("conversion_rates");
			
			//Total number of currencies
			int totalCurrCount = cr_map.size();
			System.out.println("Total currencies in response: "+totalCurrCount);
			
			//Verify GBP is part of response
			boolean hasCurrGbp = cr_map.containsKey("GBP");
			Assert.assertTrue(hasCurrGbp, "Response contains 'GBP'");
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
