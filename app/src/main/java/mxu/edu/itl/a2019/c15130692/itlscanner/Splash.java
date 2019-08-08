package mxu.edu.itl.a2019.c15130692.itlscanner;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView itl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.splash);
        itl = findViewById(R.id.itl);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent i = new Intent(Splash.this,
                        MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 4000);


    }
}
