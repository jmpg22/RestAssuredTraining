package calls;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class PostCalls {
	private static RequestSpecification requ;
	
	String uri = "https://reqres.in";	String resource  = "/api/users";	String qParam = "";
	
	@BeforeClass
	public void createRequest() {
		requ = (RequestSpecification) new RequestSpecBuilder().setBaseUri(uri).build();
	}
	
	@Test
	public void verifyAPIRuns() {		
		given().spec(requ).when().get(resource).then().assertThat().statusCode(200).log().all();
	}
	
	@Test
	public void POST_Test () { //POST : Request (Response : 201)
		JSONObject request = new JSONObject();
		request.put("first_name", "manuel");		request.put("last_name", "garate");
		System.out.println(request);		System.out.println(request.toString());
		given().body(request.toJSONString()).when().post("https://reqres.in/api/users").then().statusCode(201);
	}
	
	@Test
	public void PUT_Test() { //PUT : Request (Response : 200)

		JSONObject request = new JSONObject();
		request.put("first_name", "juan");
		request.put("last_name", "perez");

		System.out.println(request);
		System.out.println(request.toString());

		given().body(request.toJSONString()).when().put("https://reqres.in/api/users/2").then().statusCode(200);
	}
	
	@Test
	public void DELETE_Test() { //DELETE : Request (Response : 204)

		JSONObject request = new JSONObject();
		given().body(request.toJSONString()).when().	delete("https://reqres.in/api/users/2").	then().statusCode(204).log().all();
	}
	
	@Test 
	public void PATCH_TEST() { //PATCH : Request (Response : 200)
		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BAA");

		System.out.println(request);
		System.out.println(request.toString());

		given().body(request.toJSONString()).when().	patch("https://reqres.in/api/users").	then().statusCode(200);
	}
}
