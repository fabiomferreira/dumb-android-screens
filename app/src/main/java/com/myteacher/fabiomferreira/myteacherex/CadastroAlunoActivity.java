package com.myteacher.fabiomferreira.myteacherex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class CadastroAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        Button prox1 = (Button) findViewById(R.id.prox1);
        Button prox2 = (Button) findViewById(R.id.prox2);
        Button voltar2 = (Button) findViewById(R.id.voltar2);
        Button voltar3 = (Button) findViewById(R.id.voltar3);
        final LinearLayout tela1 = (LinearLayout) findViewById(R.id.tela1);
        final LinearLayout tela2 = (LinearLayout) findViewById(R.id.tela2);
        final LinearLayout tela3 = (LinearLayout) findViewById(R.id.tela3);
        prox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela1.setVisibility(View.INVISIBLE);
                tela2.setVisibility(View.VISIBLE);
            }
        });
        prox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela2.setVisibility(View.INVISIBLE);
                tela3.setVisibility(View.VISIBLE);
            }
        });

        voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela2.setVisibility(View.INVISIBLE);
                tela1.setVisibility(View.VISIBLE);
            }
        });

        voltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela3.setVisibility(View.INVISIBLE);
                tela2.setVisibility(View.VISIBLE);
            }
        });

    }
}
