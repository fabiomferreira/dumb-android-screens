package com.myteacher.fabiomferreira.myteacherex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myteacher.fabiomferreira.myteacherex.model.Agendamento;
import com.myteacher.fabiomferreira.myteacherex.model.Aluno;
import com.myteacher.fabiomferreira.myteacherex.model.Instrutor;


public class AgendamentoActivity extends AppCompatActivity {
    private static final String TAG = "Problema de conexao!";
    private String nomeInstrutor;
    private String valorInstrutor;
    private Spinner data;
    private Spinner hora;
    private Spinner local;
    private Aluno aluno;
    private Instrutor instrutor;
    @Override
    protected void onStart(){
        super.onStart();
        Intent myIntent = getIntent(); // gets the previously created intent
        nomeInstrutor = myIntent.getStringExtra("nome"); // will return "FirstKeyValue"
        valorInstrutor= myIntent.getStringExtra("valor");
        String idInstrutor= myIntent.getStringExtra("idInstrutor");
        
        DatabaseReference mAluno;
        DatabaseReference mDatabase;
        mAluno = FirebaseDatabase.getInstance().getReference().child("aluno").child("-LDxiNKt8CaAe4_wWBts");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("instrutor").child("1");

        // Carrega o aluno atual
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                aluno = dataSnapshot.getValue(Aluno.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mAluno.addListenerForSingleValueEvent(postListener);

        // Carrega o professor atual
        ValueEventListener instrutorListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                instrutor = dataSnapshot.getValue(Instrutor.class);
                TextView nomeInstrutor = (TextView) findViewById(R.id.textView);
                nomeInstrutor.setText("Instrutor: " + instrutor.getNome());
                TextView valorInstrutor = (TextView) findViewById(R.id.textView2);
                valorInstrutor.setText("Valor: R$" + instrutor.getValor());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addListenerForSingleValueEvent(instrutorListener);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agendamento);
        String[] arraySpinner = new String[] {
                "7/05/2018", "8/05/2018", "9/05/2018", "10/05/2018", "11/05/2018"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);
        data = s;
        arraySpinner = new String[] {
                "8:00", "14:00", "16:00", "17:00", "18:00"
        };
       s = (Spinner) findViewById(R.id.spinner2);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);
        hora = s;

        arraySpinner = new String[] {
                "Minha Casa", "Casa do Instrutor", "Local Neutro"
        };
        s = (Spinner) findViewById(R.id.spinner3);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);

        local = s;
        Button ok = (Button) findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgendamentoActivity.this, InstrutorActivity.class);
                DatabaseReference mAgendamento;
                mAgendamento = FirebaseDatabase.getInstance().getReference().child("agendamento");
                Agendamento agendamento = new Agendamento();
                agendamento.setAluno(aluno);
                agendamento.setInstrutor(instrutor);
                agendamento.setSelectedDate(data.getSelectedItem().toString());
                agendamento.setSelectedHour(hora.getSelectedItem().toString());
                agendamento.setSelectedPlace(local.getSelectedItem().toString());
                String key = mAgendamento.push().getKey();
                mAgendamento.child(key).setValue(agendamento);
                startActivity(intent);
            }
        });
    }
}
