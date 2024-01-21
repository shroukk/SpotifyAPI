package tests;

import api.StatusCode;
import api.endpoints.TokenAPI;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.TokenError;
import utilities.ConfigLoader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static utilities.FakerUtils.generateRandomString;

@Epic("Spotify APIs OAuth02")
@Feature("Renew an access token")
public class T01_RenewTokenTests {
    @Story("Renew a token with invalid data")
    @Description("Verify an error message display when renew token with invalid client id")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1,description = "should Not be able to renew a token with invalid client Id")
    public void TestToRenewTokenUsingInvalidClientId(){
        Response response = TokenAPI.postAccount(generateRandomString(32), ConfigLoader.getInstance().getClientSecret(),ConfigLoader.getInstance().getRefreshToken(), ConfigLoader.getInstance().getGrantType());

        assertStatusCode(response.statusCode(),StatusCode.CODE_400_Token_Id);
        assertError(response.as(TokenError.class), StatusCode.CODE_400_Token_Id);
    }

    @Story("Renew a token with invalid data")
    @Description("Verify an error message display when renew token with invalid client secret")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, description = "should Not be able to renew a token using invalid client secret")
    public void TestToRenewTokenUsingInvalidClientSecret(){
        Response response = TokenAPI.postAccount(ConfigLoader.getInstance().getClientId(),generateRandomString(32),ConfigLoader.getInstance().getRefreshToken() , ConfigLoader.getInstance().getGrantType());

        assertStatusCode(response.statusCode(),StatusCode.CODE_400_Token_Secret);
        assertError(response.as(TokenError.class), StatusCode.CODE_400_Token_Secret);
    }

    @Story("Renew a token with invalid data")
    @Description("Verify an error message display when renew token with refresh token")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3, description = "should Not be able to renew a token using invalid refresh token")
    public void TestToRenewTokenUsingInvalidRefreshToken(){
        Response response = TokenAPI.postAccount(ConfigLoader.getInstance().getClientId(),ConfigLoader.getInstance().getClientSecret(),generateRandomString(32) , ConfigLoader.getInstance().getGrantType());

        assertStatusCode(response.statusCode(),StatusCode.CODE_400_refresh_token);
        assertError(response.as(TokenError.class), StatusCode.CODE_400_refresh_token);
    }

    @Story("Renew a token with invalid data")
    @Description("Verify an error message display when renew token with grant type")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4, description = "should Not be able to renew a token using invalid grant type")
    public void TestToRenewTokenUsingInvalidGrantType(){
        Response response = TokenAPI.postAccount(ConfigLoader.getInstance().getClientId(),ConfigLoader.getInstance().getClientSecret(),ConfigLoader.getInstance().getRefreshToken() , "wrong");

        assertStatusCode(response.statusCode(),StatusCode.CODE_400_Token_Type);
        assertError(response.as(TokenError.class), StatusCode.CODE_400_Token_Type);
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    @Step
    public void assertError(TokenError responseErr, StatusCode statusCode){

        assertThat(responseErr.getError(), equalTo(statusCode.msg));
        assertThat(responseErr.getError_description(),containsString(statusCode.desc));
    }
}
