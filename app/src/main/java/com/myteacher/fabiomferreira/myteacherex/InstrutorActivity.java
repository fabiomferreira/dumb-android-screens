package com.myteacher.fabiomferreira.myteacherex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myteacher.fabiomferreira.myteacherex.model.Instrutor;

import org.w3c.dom.Text;


public class InstrutorActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Instrutor instrutor;


    @Override
    public void onStart() {
        super.onStart();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("instrutor").child("1");
        ValueEventListener getListener = new ValueEventListener() {
            public String getEstrelas(Double atributo) {
                String estrelas = "";
                if(atributo >= 1) {
                    estrelas =  "★ ";
                }
                if(atributo >= 2) {
                    estrelas += "★ ";
                }
                if(atributo >= 3) {
                    estrelas += "★ ";
                }
                if(atributo >= 4) {
                    estrelas += "★ ";
                }
                if(atributo >= 5) {
                    estrelas += "★";
                }
                return estrelas;
            }

            private static final String TAG = "Problema!";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                instrutor = dataSnapshot.getValue(Instrutor.class);
                System.out.println(instrutor.getNome());
                TextView nome = (TextView) findViewById(R.id.textView);
                nome.setText("Nome: " + instrutor.getNome());
                TextView especialidade = (TextView) findViewById(R.id.textView2);
                especialidade.setText("Especialidade: " + instrutor.getArea());
                TextView valor = (TextView) findViewById(R.id.textView3);
                valor.setText("Valor: " + instrutor.getArea() + " hora/aula");
                TextView nota = (TextView) findViewById(R.id.textView4);
                double averageGrade = (
                                instrutor.getConhecimento() +
                                instrutor.getCordialidade() +
                                instrutor.getDinamismo() +
                                        instrutor.getPontualidade())/4;
                nota.setText("Nota Geral: ★" + averageGrade);
                TextView conhecimentos = (TextView) findViewById(R.id.textView5);
                conhecimentos.setText("Conhecimentos e Habilidades: " + instrutor.getConhecimentos());

                TextView pontualidade = (TextView) findViewById(R.id.textView6);
                pontualidade.setText("Pontualidade: " + getEstrelas(instrutor.getPontualidade()));

                TextView cordialidade = (TextView) findViewById(R.id.textView7);
                cordialidade.setText("Cordialidade: " + getEstrelas(instrutor.getCordialidade()));

                TextView conhecimento = (TextView) findViewById(R.id.textView8);
                conhecimento.setText("Conhecimento: " + getEstrelas(instrutor.getConhecimento()));

                TextView dinamismo = (TextView) findViewById(R.id.textView9);
                dinamismo.setText("Dinamismo: " + getEstrelas(instrutor.getDinamismo()));

                TextView cadastro = (TextView) findViewById(R.id.textView10);
                cadastro.setText("Data de Cadastro: " + instrutor.getDataCriacao());

                TextView nAulas = (TextView) findViewById(R.id.textView11);
                nAulas.setText("Aulas Cadastradas: " + instrutor.getnAulas());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addListenerForSingleValueEvent(getListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_instrutor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstrutorActivity.this, AgendamentoActivity.class);
                intent.putExtra("idInstrutor", "1");

                intent.putExtra("nome",instrutor.getNome());
                intent.putExtra("valor",instrutor.getValor());
                startActivity(intent);
            }
        });
    }
}
