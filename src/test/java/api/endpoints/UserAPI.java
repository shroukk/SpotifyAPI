package api.endpoints;
import api.RestResource;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static api.Routes.*;
import static api.TokenManager.getToken;

public class UserAPI {
    public static Response get(){
        return RestResource.get( User, getToken());
    }
    public static Response update(String playlistId){
        return RestResource.update( PLAYLISTS+"/"+playlistId+FOLLOWERS, getToken(),"true");
    }
    public static Response delete(String playlistId){
        return RestResource.delete( PLAYLISTS+"/"+playlistId+FOLLOWERS, getToken(),"true");
    }
    public static Response putArt(String artists ){
        return RestResource.update( User+FOLLOWING+"?type=artist", getToken(), artists);
    }

    public static Response deleteArt(String artists){
        return RestResource.delete( User+FOLLOWING+"?type=artist", getToken(),artists);
    }

}
