package api;

public enum  StatusCode {

    CODE_200(200, ""),
    CODE_201(201, ""),
    CODE_204(204, ""),
    CODE_400(400, "Missing required field: name"),
    CODE_400_BR(400, "Bad request."),
    CODE_400_Token_Id(400,"invalid_client","Invalid client"),
    CODE_400_Token_Secret(400,"invalid_client","Invalid client secret"),
    CODE_400_refresh_token(400,"invalid_grant","Invalid refresh token"),
    CODE_400_Token_Type(400,"unsupported_grant_type","is not supported"),
    CODE_401_invalid_token(401, "Invalid access token"),
    CODE_401_expired_token(401, "The access token expired"),
    CODE_404(404, "Invalid playlist Id");

    public final int code;
    public final String msg;
    public final String desc;
    StatusCode(int code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        desc = null;
    }



}
