package api.tests;

import api.endpoint.UserEndpont;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {
    User userPayload;

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUsers(String userID, String username, String fname, String lname, String emailID, String pass, String phNo){

        userPayload = new User();
        userPayload.setId(userID);
        userPayload.setUsername(username);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(emailID);
        userPayload.setPassword(pass);
        userPayload.setPhone(phNo);

        Response response = UserEndpont.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2, dataProvider = "usernames", dataProviderClass = DataProviders.class)
    public void testDeleteUsers(String username){
        Response response = UserEndpont.deleteUser(username);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

//    @Test (priority = 2)
//    public void testCreateUser(){
//        System.out.println(userPayload);
//        Response response = UserEndpont.createUser(userPayload);
//        response.then().log().all();
//        Assert.assertEquals(response.getStatusCode(),200);
//        Assert.assertEquals(response.jsonPath().getString("message"),this.userPayload.getId());
//    }
}
