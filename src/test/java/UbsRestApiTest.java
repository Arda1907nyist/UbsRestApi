import Pojo.UbsRole;
import Pojo.UbsUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UbsRestApiTest {
    private RequestSpecification reqSpec;
    private Map<String, String> requestBody;
    private Object id;
    private UbsUser user;
    private UbsRole role;

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = "https://examples.com";

        reqSpec = given()
                .log().uri()
                .header("Authorization", "Bearer 4a0eee6d56180a8378fa56")
                .contentType(ContentType.JSON);
        role = new UbsRole();
        role.setRoleName("SDET");

        user = new UbsUser();
        user.setName("Alex");
        user.setLastName("De Souza");
        user.setEmployeeId(301);
        user.setRole(role);


    }

    @Test
    public void createUserTest() {

        id = given()
                .spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/companyurl1")
                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");
    }

    @Test
    public void getUserRoleTest() {
        UbsRole[] roles = given()
                .spec(reqSpec)
                .when()
                .get("/companyurl3")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .response().body().as(UbsRole[].class);

    }

    @Test
    public void createUbsRoleTest() {
        UbsRole newRole = new UbsRole();
        newRole.setRoleName("Scrum Master");

        given()
                .spec(reqSpec)
                .when()
                .body(newRole)
                .post("/companyurl4")
                .then()
                .log().body()
                .statusCode(200)
                .body("roleName", equalTo(newRole.getRoleName()));


    }

    @Test
    public void getUserList() {

        UbsUser[] userList = given()
                .contentType(ContentType.JSON)
                .get("/companyurl3")
                .then()
                .statusCode(200)
                .extract()
                .response().body().as(UbsUser[].class);


    }

    @Test
    public void associateRoleTest() {

        UbsRole newRole = new UbsRole();
        newRole.setRoleName("LEAD SDET");

        given()
                .spec(reqSpec)
                .body(newRole)
                .when()
                .put("companyurl4" + user.getId())
                .then()
                .log().body()
                .statusCode(200)
                .body("UbsRole.roleName", equalTo(newRole.getRoleName()));


    }

}
