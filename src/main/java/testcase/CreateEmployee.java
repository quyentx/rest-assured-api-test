package testcase;

import data.dataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestbodyinitiator.CreateEmp;

import java.lang.reflect.Method;

public class CreateEmployee extends TestBase  implements ITest {


    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeMethod
    public void BeforeMethod(Method method, Object[] testData){
        testName.set(testData[0] + "_" + method.getName()+ "_" +testData[1]);
    }

    @Override
    public String getTestName() {
        return testName.get();
    }

    @Test(dataProvider = "createEmp", dataProviderClass = dataProvider.class)
    public void createEmployee(String testcaseID, String testDesc, String name, String sal, String age) {
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

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println(response.getBody().asString());
    }
}
