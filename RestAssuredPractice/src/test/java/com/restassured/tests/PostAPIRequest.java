package com.restassured.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

public class PostAPIRequest {
  @Test
  public void createBooking() {
	  
	  JSONObject booking = new JSONObject();
	  JSONObject bookingdates = new JSONObject();
	  booking.put("firstname", "CustomerFName");
	  booking.put("lastname", "CustomerLName");
	  booking.put("totalprice", 1000);
	  booking.put("depositpaid", true);
	  booking.put("additionalneeds", "CusteakfastomerFName");
	  booking.put("bookingdates", bookingdates);
	  bookingdates.put("checkin", "2024-10-25");
	  bookingdates.put("checkout", "2024-10-30");
	  
	  RestAssured
	  		.given()
	  			.contentType(ContentType.JSON)
	  			.body(booking.toString())
	  			.baseUri("https://restful-booker.herokuapp.com/booking")
	  		.when()
	  			.post()
	  		.then()
	  			.assertThat()
	  			.statusCode(200)
	  			.body("booking.firstname", Matchers.equalTo("CustomerFName"))
	  			.body("booking.bookingdates.checkin", Matchers.equalTo("2024-10-25"))
	  			.body("booking.bookingdates.checkout", Matchers.equalTo("2024-10-30"));
	  	
  }
}
