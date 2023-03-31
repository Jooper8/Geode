package com.facens.geotest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    //Declara uma variável de timer.
    private final Timer timer = new Timer();
    TimerTask timerTask;
    //Faz com que a splash screen seja mostrada.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Cria uma nova variável timerTask, que executa o método gotoMainActivity().
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //Faz com que o timer timerTask tenha um delay de 3000ms até ser acionado.
        timer.schedule(timerTask, 3000);
    }
    //Redireciona o usuário até a parte principal do app, MainActivity.
    private void gotoMainActivity() {
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    }
}