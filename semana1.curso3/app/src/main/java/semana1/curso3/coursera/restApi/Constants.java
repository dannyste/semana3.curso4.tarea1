package semana1.curso3.coursera.restApi;

public class Constants {

    /*
     * INSTAGRAM
     */

    private static final String INSTAGRAM_VERSION = "/v1/";

    public static final String INSTAGRAM_ROOT_URL = "https://api.instagram.com" + INSTAGRAM_VERSION;

    public static final String INSTAGRAM_ACCESS_TOKEN = "6953085721.0468ebf.84bfd0439f5b4b2ea6d236405311f71e";

    private static final String INSTAGRAM_KEY_ACCESS_TOKEN = "access_token=";

    private static final String INSTAGRAM_KEY_GET_SEARCH_USER = "users/search";

    private static final String INSTAGRAM_KEY_GET_RECENT_MEDIA_USER_ID = "users/{user_id}/media/recent/?";

    static final String INSTAGRAM_URL_GET_SEARCH_USER = INSTAGRAM_KEY_GET_SEARCH_USER;

    static final String INSTAGRAM_URL_GET_RECENT_MEDIA_USER_ID = INSTAGRAM_KEY_GET_RECENT_MEDIA_USER_ID + INSTAGRAM_KEY_ACCESS_TOKEN + INSTAGRAM_ACCESS_TOKEN;

    /*
     *  SERVER
     */

    public static final String SERVER_ROOT_URL = "https://guarded-shore-98698.herokuapp.com/";

    public static final String URL_POST_REGISTRAR_USUARIO = "registrar-usuario/";

}