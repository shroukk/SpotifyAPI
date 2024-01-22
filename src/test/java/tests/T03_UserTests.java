package tests;

import api.StatusCode;
import api.endpoints.UserAPI;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Error;
import pojo.User;
import utilities.ArtistsIdsLoader;
import utilities.DataLoader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utilities.FakerUtils.generateArraysOfIds;
import static utilities.FakerUtils.generateRandomString;


@Epic("Spotify APIs OAuth02")
@Feature("User Profile APIs")
public class T03_UserTests {

    @Story("Get a user profile data")
    @Description("Verify that user is able to retrieve his profile data")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1, description = "should be able to get a user profile data")
    public void TestToGetUserProfile(){
        Response response = UserAPI.get();
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_200.code));
        assertUserEqual(response.as(User.class));
    }

    @Story("Follow a playlist")
    @Description("Verify that user is able to follow an existing playlist")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2,description = "should be able to follow an existing playlist")
    public void TestToFollowPlaylistWithValidId(){
        Response response = UserAPI.update(DataLoader.getInstance().getPlaylistId());
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_200.code));
    }

    @Story("Unfollow a playlist")
    @Description("Verify that user is able to unfollow an existing playlist")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3,description = "should be able to unfollow an existing playlist", dependsOnMethods ="TestToFollowPlaylistWithValidId")
    public void TestToUnfollowPlaylistWithValidId(){
        Response response = UserAPI.delete(DataLoader.getInstance().getPlaylistId());
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_200.code));
    }

    @Story("Follow a playlist")
    @Description("Verify that error message displaying when following non existing playlist")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4, description = "should not be able to follow non existing playlist")
    public void TestToFollowPlaylistWithInvalidId(){
        Response response = UserAPI.update(generateRandomString(20));
        assertError(response.as(Error.class), StatusCode.CODE_404);
    }

    @Story("Unfollow a playlist")
    @Description("Verify that error message displaying when unfollowing non existing playlist")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 5, description = "should not be able to unfollow non existing playlist")
    public void TestToUnfollowPlaylistWithInvalidId(){
        Response response = UserAPI.delete(generateRandomString(21));
        assertError(response.as(Error.class), StatusCode.CODE_404);
    }

    @Story("Follow artists")
    @Description("Verify that user is able to follow existing artists")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 6, description = "should be able to follow existing artists")
    public void TestToFollowArtistWithValidId(){
        Response response = UserAPI.putArt(ArtistsIdsLoader.getArtistsIds());
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_204.code));
    }

    @Story("Unfollow artists")
    @Description("Verify that user is able to unfollow existing artists")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 7, description = "should be able to unfollow existing artists", dependsOnMethods = "TestToFollowArtistWithValidId")
    public void TestToUnfollowArtistWithValidId(){
        Response response = UserAPI.deleteArt(ArtistsIdsLoader.getArtistsIds());
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_204.code));
    }

    @Story("Follow artists")
    @Description("Verify that error message displaying when following non existing artists")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 8,  description = "should not be able to follow non existing artists")
    public void TestToFollowArtistWithInvalidId(){
        Response response = UserAPI.putArt(generateArraysOfIds());
        assertError(response.as(Error.class), StatusCode.CODE_400_BR);
    }

    @Story("Unfollow artists")
    @Description("Verify that error message displaying when unfollowing non existing artists")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 9,  description = "should not be able to unfollow non existing artists")
    public void TestToUnfollowArtistWithInvalidId(){
        Response response = UserAPI.deleteArt(generateArraysOfIds());
        assertError(response.as(Error.class), StatusCode.CODE_400_BR);
    }

    @Step
    public void assertUserEqual(User response){
        assertThat(response.getDisplay_name(), equalTo(DataLoader.getInstance().getUserName()));
        assertThat(response.getEmail(), equalTo(DataLoader.getInstance().getUserEmail()));
        assertThat(response.getCountry(), equalTo(DataLoader.getInstance().getUserCountry()));
        assertThat(response.getType(), equalTo(DataLoader.getInstance().getUserType()));
    }
    @Step
    public void assertError(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }


}
