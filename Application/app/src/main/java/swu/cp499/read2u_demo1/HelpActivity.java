package swu.cp499.read2u_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.View;

import swu.cp499.read2u_demo1.databinding.ActivityHelpBinding;

public class HelpActivity extends AppCompatActivity {

    ActivityHelpBinding binding;
    private int i =0;
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvDisplay.setText(Html.fromHtml(
                "<big>READ TO YOU </big>"  +"<br>"+
                        "<small>แอปพลิชั่นสำหรับการอ่านข้อความ</small>" + "<br>"+"<br>"
                        + "<b>วิธีการอ่านข้อความ</b>" + "<br>"
                        + "<small>1. ผู้ใช้กดปุ่มเลือกเข้าการทำงาน</small>" + "<br>"
                        + "<small>2. หน้าการทำงาน จะมีปุ่มถ่ายภาพ และ ปุ่มวิเคราะห์รูปภาพอยู่บริเวณด้านล่าง โดยปุ่มถ่ายภาพจะทำการเปิดการทำงานของกล้องเพื่อถ่ายภาพ" +
                        " ถ่ายภาพเสร็จแล้วกดปุ่มวิเคราะห์รูปภาพ</small>" + "<br>"
                        + "<small>3. เมื่อได้ยินเสียง วิเคราะห์เสร็จเรียบร้อย จะมีปุ่ม2ปุ่มอยู่บริเวณด้านล่าง คือ " +
                        "ปุ่มฟังเสียง และ ปุ่มหยุดฟัง กดปุ่มฟังเสียงเพื่อทำการอ่านข้อความที่ได้จากการวิเคราะห์รูปภาพ และ ปุ่มหยุดฟังคือการหยุดการทำงานของเสียงพูด ซึ่งจะไม่เล่นต่อจากที่หยุดไว้</small>" + "<br>"


        ));
        String text = binding.tvDisplay.getText().toString();
        binding.buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//                    @Override
//                    public void onInit(int i) {
//                        if (i == TextToSpeech.SUCCESS) {
//                            //tts.setLanguage(Locale.US);
//                            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//                        }
//                    }
//                });

                Intent intent = new Intent(new Intent(HelpActivity.this, MainActivity4.class));
                startActivity(intent);
            }
        });

    }
}