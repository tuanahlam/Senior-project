package swu.cp499.read2u_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {
    private Button btnThai,btnEng,btnEngThai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        initUI();
        btnThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity4.this, MainActivity2.class));
                intent.putExtra("language","thai");
                startActivity(intent);
            }
        });

        btnEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity4.this, MainActivity2.class));
                intent.putExtra("language","eng");
                startActivity(intent);
            }

        });

        btnEngThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity4.this, MainActivity2.class));
                intent.putExtra("language","engthai");
                startActivity(intent);
            }
        });
    }

    private void initUI(){
        btnEng = findViewById(R.id.engbutton);
        btnEngThai = findViewById(R.id.thaengbutton);
        btnThai = findViewById(R.id.thaibutton);
    }
}