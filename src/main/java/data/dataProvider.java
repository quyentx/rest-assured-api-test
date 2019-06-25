package data;

import org.testng.annotations.DataProvider;

import static utils.DataGenerator.randomString;

public class dataProvider {

    @DataProvider(name="createEmp")
    public Object[][] createEmp(){
        return new Object[][]{
                {"TC_001", "valid information", randomString(6)+" "+ randomString(8), "100000", "26"},
                {"TC_002", "invalid information", randomString(6)+" "+ randomString(8), "120000", "30"}
        };
    }
}
