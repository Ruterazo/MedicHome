package com.apps.rubenruterazo.medichome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ConnectActivity extends AppCompatActivity {
    ImageButton botonwifis;
    Boolean connectState;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connect);
        connectState= getIntent().getExtras().getBoolean("connectState",false);
        if(savedInstanceState!=null)
            connectState = savedInstanceState.getBoolean("conectado");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        botonwifis = (ImageButton) findViewById(R.id.BotonwifiIn);
        TextView label = (TextView) findViewById(R.id.textCon);
        if(connectState) {
            botonwifis.setImageResource(R.drawable.conwifi);
            label.setText(R.string.conconex);
        }
        else {
            botonwifis.setImageResource(R.drawable.sinwifi);
            label.setText(R.string.preconex);
        }



        timer = new CountDownTimer(5000, 100) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                findViewById(R.id.constraintLayoutCon2).setVisibility(View.INVISIBLE);
                findViewById(R.id.constraintLayoutCon1).setVisibility(View.VISIBLE);
                connectState=true;
                botonwifis.setImageResource(R.drawable.conwifi);
                TextView label = (TextView) findViewById(R.id.textCon);
                label.setText(R.string.conconex);
            }
        };

        }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            timer.cancel();
            getIntent().putExtra("connectState",connectState);  // put data that you want returned to activity A
            setResult(ConnectActivity.RESULT_OK, getIntent());
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getIntent().putExtra("connectState",connectState);  // put data that you want returned to activity A
            setResult(ConnectActivity.RESULT_OK, getIntent());
            this.finish();
        }

        return super.onKeyDown(keyCode, event);
    }
    public void clickWifiIn(View view) {
        connectState=false;
        findViewById(R.id.constraintLayoutCon1).setVisibility(View.INVISIBLE);
        findViewById(R.id.constraintLayoutCon2).setVisibility(View.VISIBLE);
        timer.start();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putBoolean("conectado", connectState);


        // etc.

        super.onSaveInstanceState(savedInstanceState);
    }

//onRestoreInstanceState

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        connectState = savedInstanceState.getBoolean("conectado");

    }
}

