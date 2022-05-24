package swu.cp499.read2u_demo1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swu.cp499.read2u_demo1.api.ApiService;
import swu.cp499.read2u_demo1.api.Const;

public class MainActivity2 extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    private TextToSpeech textToSpeech;
    private Button btCapture, btRegcognize;
    private ImageView imgView;
    private Uri image_uri;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initUI();

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        btCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        openCamera();
                    }
                } else {
                    openCamera();
                }
            }
        });

        btRegcognize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image_uri != null){
                    textToSpeech.speak("กำลังวิเคราะห์รูปภาพ กรุณารอสักครู่ ", TextToSpeech.QUEUE_FLUSH,null);
                    callOcrApi();
                }else{
                    textToSpeech.speak("โปรดเลือกรูปภาพ",TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });
    }

    private void initUI(){
        btCapture = findViewById(R.id.bt_Capture);
        btRegcognize = findViewById(R.id.bt_Recognize);
        imgView = findViewById(R.id.imgView);
        tvText = findViewById(R.id.tv_Text);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void openCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        //mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
        startActivityForResult(intent, IMAGE_CAPTURE_CODE);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            imgView.setImageURI(image_uri);
            textToSpeech.speak("ถ่ายรูปภาพเสร็จแล้ว กดปุ่มวิเคราะห์รูปภาพต่อไป",TextToSpeech.QUEUE_FLUSH,null);

        }
    }


    private void  callOcrApi() {

        Intent intent = getIntent();
//        String mode = intent.getStringExtra("mode");
        String strLanguage = intent.getStringExtra("language");

        String strRealPath = RealPathUtil.getRealPath(this,image_uri);
        Log.e("image",strRealPath);
        File file = new File(strRealPath);

        RequestBody requestBodyLanguage = RequestBody.create(MediaType.parse("multipart/form-data"),strLanguage);
//        RequestBody requestBodyMode = RequestBody.create(MediaType.parse("multipart/form-data"),strMode);
        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyAvt = MultipartBody.Part.createFormData(Const.KEY_AVT, file.getName(),requestBodyAvt);


        ApiService.apiService.ocrApi(requestBodyLanguage,multipartBodyAvt).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String user = response.body().getData();

                tvText.setText(user);
                textToSpeech.speak("วิเคราะห์เสร็จเรียบร้อย",TextToSpeech.QUEUE_FLUSH,null);
                Intent intent = new Intent(new Intent(MainActivity2.this, MainActivity3.class));
                intent.putExtra("text",user);
                intent.putExtra("language",strLanguage);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity2.this,"Call Api fail",Toast.LENGTH_SHORT).show();
                textToSpeech.speak("ไม่สามารถวิเคราะห์รูปภาพได้ โปรดถ่ายรูปภาพใหม่",TextToSpeech.QUEUE_FLUSH,null);

            }

        });

    }
}