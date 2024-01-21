package api.endpoints;

import api.RestResource;
import io.restassured.response.Response;
import java.util.HashMap;

public class TokenAPI {

    public  static Response  postAccount(String clientId, String clientSecret, String refreshToken, String grantType){

        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", clientId);
        formParams.put("client_secret", clientSecret);
        formParams.put("refresh_token", refreshToken);
        formParams.put("grant_type", grantType);

        return RestResource.postAccount(formParams);

        }

}
