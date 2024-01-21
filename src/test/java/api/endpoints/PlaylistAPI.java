package api.endpoints;

import api.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Playlist;
import utilities.ConfigLoader;

import static api.Routes.*;
import static api.TokenManager.getToken;

public class PlaylistAPI {
    @Step
    public static Response post(Playlist payload){

        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS , getToken(), payload);
    }
    public static Response post(String token,Playlist payload){

        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS , token, payload);
    }
    public static Response post(String[] uris, String playlistId){

        return RestResource.post(PLAYLISTS+"/"+ playlistId+ TRACKS , getToken(), uris);
    }

    public static Response get(){

        return RestResource.get(USERS+"/"+ConfigLoader.getInstance().getUserId()+PLAYLISTS+"/" , getToken());
    }
    public static Response get(String playlistId){

        return RestResource.get(PLAYLISTS + "/" + playlistId , getToken());
    }


    public static Response update(String playlistId,Playlist payload){

        return RestResource.update(PLAYLISTS+"/"+playlistId , getToken(), payload);
    }
    public static Response delete(String uris,String playlistId){
        return RestResource.delete(PLAYLISTS + "/" + playlistId + TRACKS, getToken(), uris);
    }

}
