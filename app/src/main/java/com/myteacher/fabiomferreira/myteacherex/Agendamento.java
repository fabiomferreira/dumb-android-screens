package com.myteacher.fabiomferreira.myteacherex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Agendamento extends AppCompatActivity {

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

        arraySpinner = new String[] {
                "8:00", "14:00", "16:00", "17:00", "18:00"
        };
       s = (Spinner) findViewById(R.id.spinner2);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);

        arraySpinner = new String[] {
                "Minha Casa", "Casa do Instrutor", "Local Neutro"
        };
        s = (Spinner) findViewById(R.id.spinner3);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);
        Button ok = (Button) findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendamento.this, InstrutorActivity.class);
                startActivity(intent);
            }
        });
    }
}
