package swu.cp499.read2u_demo1;

import com.google.gson.annotations.SerializedName;

public class User {

    private String language;
//    private String mode;
    private String data;
    @SerializedName("avt")
    private String avatar;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

//    public String getMode() {
//        return mode;
//    }
//
//    public void setMode(String mode) {
//        this.mode = mode;
//    }

    public String getData() {
        return data;
    }

    public void setData(String data) { this.data = data; }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
