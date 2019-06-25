package utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ApiUtils {
    public static String path;
    public static String jsonPathTerm;
    static PropertyReader properties = new PropertyReader();

    //Sets Base URI
    public static void setBaseURI (){
        try {
            RestAssured.baseURI = properties.getPropValues("app.baseURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Sets base path
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }

    //Reset Base URI (after test)
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    //Reset base path
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    //Sets ContentType
    public static void setContentType (ContentType Type){
        given().contentType(Type);
    }

    //Returns response by given path
    public static Response getResponsebyPath(String path) {
        return get(path);
    }

    //Returns response
    public static Response getResponse() {
        return get();
    }

    //Returns JsonPath object
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        //System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }

    public static String ObjectToJson(Object value){
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";
        try {
            // get Oraganisation object as a json string
            jsonStr = Obj.writeValueAsString(value);
            // Displaying JSON String
            System.out.println(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

}

class PropertyReader {
    private String result = "";
    private InputStream inputStream;

    public String getPropValues(String key) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
                System.out.println("read config successfully");
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
