package tests;

import api.StatusCode;
import api.endpoints.PlaylistAPI;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Error;
import pojo.Playlist;
import utilities.TracksLoader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utilities.FakerUtils.generateDescription;
import static utilities.FakerUtils.generateName;

@Epic("Spotify APIs OAuth02")
@Feature("Playlist API")
public class T02_PlaylistTests {
     String playlistId;
     static Playlist request;

    @BeforeClass
    public void setData() {
     request = playlistBuilder(generateName(), generateDescription(), true);
    }


    @Story("Create a playlist")
    @Description("Verify creating a playlist with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1,description = "should be able to create a playlist with valid data")
    public void TestToCreateAPlaylist(){
        Response response = PlaylistAPI.post(request);
        playlistId = response.jsonPath().getString("id");
        assertStatusCode(response.statusCode(),StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class),request);
    }

    @Story("Get a playlist with valid id")
    @Description("Verify that user is able to get a playlist using valid playlist ID")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2,description = "should be able to get a playlist with playlist ID", dependsOnMethods = "TestToCreateAPlaylist")
    public void TestToGetAPlaylist(){
        Response response = PlaylistAPI.get(playlistId);
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
        assertThat(response.jsonPath().getString("id"),equalTo(playlistId));
        assertPlaylistEqual(response.as(Playlist.class),request);
    }

    @Story("Update a playlist with valid Id")
    @Description("Verify that user is able to get a playlist using valid playlist ID")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3,description = "should be able to update a playlist with valid data", dependsOnMethods = "TestToGetAPlaylist")
    public void TestToUpdateAPlaylist(){
        request = playlistBuilder(generateName(), generateDescription(), true);
        Response response = PlaylistAPI.update(playlistId, request);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }


    @Story("Get a user playlists with valid Id")
    @Description("Verify that user is able to get his playlists")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4,description = "should be able to get a user playlists")
    public void TestToGetAUserPlaylists(){
        Response response = PlaylistAPI.get();
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
    }

    @Story("Add items to a playlist")
    @Description("Verify that user is able to add tracks to a playlist")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5,description = "should be able to add items to a playlist",dependsOnMethods = "TestToCreateAPlaylist")
    public void TestToAddItemsToPlaylist(){
        String[] uri = TracksLoader.getTrackItem();
        Response response = PlaylistAPI.post(uri, playlistId);
        assertStatusCode(response.statusCode(),StatusCode.CODE_201);
    }

    @Story("Remove items from a playlist")
    @Description("Verify that user is able to remove tracks from a playlist")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6,description = "should be able to remove items from a playlist", dependsOnMethods = "TestToAddItemsToPlaylist")
    public void TestToRemoveItemsFromPlaylist(){
        String tracks = TracksLoader.getTracksObject();
        Response response = PlaylistAPI.delete(tracks,playlistId);
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
    }

    @Story("Create a playlist")
    @Description("Verify that user is not able to create a playlist with missing the name")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 7,description = "should not be able to create a playlist without name")
    public void TestToCreateAPlaylistWithoutName(){
        Playlist requestPlaylist = playlistBuilder("",generateDescription(), false);
        Response response = PlaylistAPI.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Create a playlist")
    @Description("Verify that user is not able to create a playlist with expired token")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 8,description = "should not be able to create a playlist with expired token")
    public void TestToCreateAPlaylistWithExpiredToken(){
        String expired_token = "BQDcSbAP6dwY6rNkSQufm4NOH-Ujpr340btR2Xf5Wx_LD0SRjZvOLWv1qKF22XZDKN6J7vb_Wq9Gh-LXC6n0mOL3g3Z3p3kPQAjD7ONbPR3CMGgwl4JtKcs9exNYDIycw-7C2nb3FqNsYkuZwBk-NfjzTaQny4f6lQqZ7p7pfmOExcRfVn1Cv7wNi6WOvfNhyc0sk-zDBWeBmNQFwPptdqzazgkAxzmZAE6LsWsndOuYgru3H6F7ysXFF8n69jqmVqli8PwAWeBFbnwvBUFqHjosQUg";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(expired_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401_expired_token);
        assertError(response.as(Error.class), StatusCode.CODE_401_expired_token);
    }

    @Story("Create a playlist")
    @Description("Verify that user is not able to create a playlist with invalid token")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 9,description = "should not be able to create a playlist with invalid token")
    public void TestToCreateAPlaylistWithInvalidToken(){
        String invalid_token = "7C2nb3FqNsYkuZwBk";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401_invalid_token);
        assertError(response.as(Error.class), StatusCode.CODE_401_invalid_token);
    }



    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }
    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    @Step
    public void assertError(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }

}
