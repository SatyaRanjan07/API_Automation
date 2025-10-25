package api.endpoint;

public class Routes {

    /*

    Create Token  https://restful-booker.herokuapp.com/auth
    Create booking  https://restful-booker.herokuapp.com/booking
    Get booking  https://restful-booker.herokuapp.com/booking/:id
    Get booking id  https://restful-booker.herokuapp.com/booking
    Update booking  https://restful-booker.herokuapp.com/booking/:id
    Partial update booking  https://restful-booker.herokuapp.com/booking/:id/user
    Delete booking  https://restful-booker.herokuapp.com/booking/1
    Health check  https://restful-booker.herokuapp.com/ping

     */
    public static String base_URI = "https://petstore.swagger.io/v2";
    public static String createUser = base_URI+"/user";
    public static String getUser = base_URI+"/user/{username}";
    public static String updateUser = base_URI+"/user/{username}";
    public static String deleteUser = base_URI +"/user/{username}";
}
