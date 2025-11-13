package com.example.catalogofacil.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.catalogofacil.R;

public class SplashActivity extends AppCompatActivity {

    // Tempo de exibição da tela de carregamento (2000 milissegundos = 2 segundos)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Ocultar a ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Tempo de espera (2 segundos)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView sharedElement = findViewById(R.id.imageViewLogo);

                Intent i = new Intent(SplashActivity.this, MainActivity.class);

                // Configura a transição: elemento de origem, nome da transição
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                SplashActivity.this,
                                sharedElement,
                                "logoTransition" // Deve ser o mesmo transitionName do XML
                        );

                // Inicia a Activity com a transição
                startActivity(i, options.toBundle());
            }
        }, 2000); // 2 segundos
    }//onCreate

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}