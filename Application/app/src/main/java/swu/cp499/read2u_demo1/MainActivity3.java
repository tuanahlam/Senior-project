package swu.cp499.read2u_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private TextView textView;
    private Button btSpeak,btStop;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initUI();

        Intent intent = getIntent();
        String t = intent.getStringExtra("text");
        String l = intent.getStringExtra("language");
        textView.setText(t);

        textToSpeech  = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        btSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(t,TextToSpeech.QUEUE_FLUSH,null);
//                Intent intent = new Intent(new Intent(MainActivity3.this, MainActivity2.class));
//                startActivity(intent);
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.stop();
                //textToSpeech.shutdown();
            }
        });


    }

    private void initUI(){
        textView = findViewById(R.id.tv_showText);
        btSpeak = findViewById(R.id.bt_Speak);
        btStop = findViewById(R.id.bt_Stop);
    }
}