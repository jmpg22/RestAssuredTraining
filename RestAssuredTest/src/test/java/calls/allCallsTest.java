package calls;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.*;

public class allCallsTest {
	private static RequestSpecification req;
	
	@BeforeClass
	public void createRequest() {
		req = (RequestSpecification) new RequestSpecBuilder()
				.setBaseUri("https://pokeapi.co")
				.build();
	}
	
	@Test
	public void jsonResponseValidation() {
	    given().spec(req).queryParam("id", "1").when().get("/api/v2/berry/")	.then().statusCode(200).log().all();
	}
	
	@Test
	public void extractAnyFromResponse() {

		Response act = (Response) given().spec(req).queryParam("id", "1").when().get("/api/v2/berry/")	.then().contentType(ContentType.JSON).extract().response();//.body("count", equalTo("64"));
				String firstName = act.path("results.name[0]");		//resutls es del response "results": [   { "name": "cheri",...
				int  ndName = act.path("count");
				String  nulo = act.path("previous");
				Assert.assertEquals(firstName, "cheri");
				Assert.assertEquals(ndName, 64);
		System.out.println("el nombre es: "+ firstName);
		System.out.println("el valor del elemento count  es: "+ ndName);
		System.out.println("el nulo  es: "+ nulo);
	}
	
	@Test
	public void extractHeaders() {		
		System.out.println("headers all  son: \n "+ get("https://pokeapi.co/api/v2/berry/1").then().extract().headers());
	}
}
