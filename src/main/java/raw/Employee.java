package raw;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import bodyobject.CreateEmp;

public class Employee {

    public Response createEmployee(String name, String sal, String age) {
        RequestSpecification request = RestAssured.given();
        CreateEmp jsonBody = new CreateEmp()
                .setName(name)
                .setSalary(sal)
                .setAge(age);
        Response response = request.body(jsonBody)
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .post("/v1/create");
        return response;
    }
}
