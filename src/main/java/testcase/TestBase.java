package testcase;

import org.testng.annotations.BeforeMethod;
import utils.ApiUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;

public class TestBase {
    public Response res = null;
    public JsonPath jp = null;
    private ThreadLocal<String> testName = new ThreadLocal<>();

    public String getTestName() {
        return testName.get();
    }

    @BeforeClass
    public void setup (){
        //Test Setup
        ApiUtils.setBaseURI();
        System.out.println(RestAssured.baseURI);
        ApiUtils.setBasePath("/api/");
        ApiUtils.setContentType(ContentType.JSON);
    }

    @BeforeMethod
    public void BeforeMethod(Method method, Object[] testData){
        testName.set(testData[0] + "_" + method.getName()+"-"+testData[1]);
    }

    @AfterClass
    public void afterTest (){
        //Reset Values
        ApiUtils.resetBaseURI();
        ApiUtils.resetBasePath();
    }
}
