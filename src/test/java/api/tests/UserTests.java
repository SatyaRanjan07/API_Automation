package api.tests;
import com.github.javafaker.Faker;
import api.endpoint.UserEndpont;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    User userPayload;
    Faker faker = new Faker();

    @BeforeClass
    public void setupData() {

        userPayload = new User();
        userPayload.setId(String.valueOf(faker.idNumber().hashCode()));
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
       // userPayload.setUserStatus(faker.number().digits(10));
    }

    @Test (priority = 1)
    public void testCreateUser(){
        System.out.println(userPayload);
       Response response = UserEndpont.createUser(userPayload);
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("message"),this.userPayload.getId());
    }

    @Test (priority = 2)
    public void testGetUserByUsername(){
        Response response = UserEndpont.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getInt("id"),String.valueOf(this.userPayload.getId()));
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndpont.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        Response responseAfterUpdate = UserEndpont.getUser(userPayload.getUsername());
        Assert.assertEquals(response.jsonPath().getString("firstName"),this
                .userPayload.getFirstName());

    }

    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = UserEndpont.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

}
