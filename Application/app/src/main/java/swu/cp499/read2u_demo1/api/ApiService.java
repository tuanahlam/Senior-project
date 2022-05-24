package swu.cp499.read2u_demo1.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import swu.cp499.read2u_demo1.User;


public interface ApiService {

    public static final String DOMAIN = "https://4d6f-2001-fb1-2d-5625-e48d-4d5c-75ee-b4bb.ngrok.io/";

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @Multipart
    @POST("/ocr")
    Call<User> ocrApi(@Part(Const.KEY_LANGUAGE)RequestBody language, @Part MultipartBody.Part avt);
//(@Part(Const.KEY_LANGUAGE)RequestBody language
//@Part(Const.KEY_MODE)RequestBody mode

}
