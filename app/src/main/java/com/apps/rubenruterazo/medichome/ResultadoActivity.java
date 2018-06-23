package com.apps.rubenruterazo.medichome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String label= getIntent().getExtras().getString("label");
        String contenido= getIntent().getExtras().getString("contenido");
        setTitle(label);
        TextView t=findViewById(R.id.textoresultado);
        t.setText(contenido);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){

            //getIntent().putExtra("connectState",connectState);  // put data that you want returned to activity A
            //setResult(ConnectActivity.RESULT_OK, getIntent());
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
