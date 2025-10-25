package api.endpoint;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpont {

    //method to get urls from properties file

    public static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload){

        String post_url = getURL().getString("createUser");
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
                .log().uri()

                .when()
                .post(post_url);

        return response;
    }

    public static Response getUser(String userName){

        String get_url = getURL().getString("getUser");
       Response response = given()
                .accept(ContentType.JSON)
               .pathParam("username",userName)
                .when()
                .get(get_url);
       return response;
    }

    public static Response updateUser(String userName, User payload){

        String put_url = getURL().getString("updateUser");
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .get(put_url);
        return response;
    }

    public static Response deleteUser(String userName){
        String delete_url = getURL().getString("deleteUser");
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .when()
                .get(delete_url);
        return response;
    }



}
