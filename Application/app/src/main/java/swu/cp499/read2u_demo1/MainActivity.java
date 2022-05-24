package swu.cp499.read2u_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btEnter, btToHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity.this, MainActivity4.class));
                startActivity(intent);
            }
        });

        btToHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(MainActivity.this, HelpActivity.class));
                startActivity(intent);
            }
        });
    }

    private void initUi(){
        btEnter = findViewById(R.id.bt_Enter);
        btToHelp = findViewById(R.id.bt_toHelp);
    }
}