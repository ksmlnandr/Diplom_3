package resources;

public class RestClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String API_VERSION = "/api";
    private static final String AUTH = "/auth";
    private static final String USER_REGISTER = API_VERSION + AUTH + "/register";
    private static final String USER_UPDATE = API_VERSION + AUTH + "/user";


    public String getBaseUrl() {return BASE_URL;}
    public String getUserRegister() {return USER_REGISTER;}
    public String getUserUpdate() {return USER_UPDATE;}
}
