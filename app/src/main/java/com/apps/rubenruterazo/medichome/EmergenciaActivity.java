package com.apps.rubenruterazo.medichome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class EmergenciaActivity extends AppCompatActivity {
    CountDownTimer timer1, timer2;
    Boolean ambu=false;
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);
        state=0;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GifImageView gifImageView = (GifImageView) findViewById(R.id.cargaemergencia);
        gifImageView.setGifImageResource(R.drawable.load);
        ImageButton b=findViewById(R.id.botonAmbu);

        if(ambu)
            b.setImageResource(R.drawable.circuloverde);
        else
            b.setImageResource(R.drawable.circulorojo);



        timer1 = new CountDownTimer(5000, 100) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                state=1;
                findViewById(R.id.detectarEmergencia).setVisibility(View.INVISIBLE);
                findViewById(R.id.solucionarEmergencia).setVisibility(View.VISIBLE);
                findViewById(R.id.Boton).setVisibility(View.VISIBLE);
                GifImageView gifImageView2 = (GifImageView) findViewById(R.id.operacionemergencia);
                gifImageView2.setGifImageResource(R.drawable.circles);
                timer2.start();
            }
        };

        timer2 = new CountDownTimer(15000, 100) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                state=2;
                findViewById(R.id.solucionarEmergencia).setVisibility(View.INVISIBLE);
                findViewById(R.id.finEmergencia).setVisibility(View.VISIBLE);

            }
        };
        timer1.start();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            switch (state){
                case 0:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("La emergencia aún no ha sido detectada y pondría en riesgo al paciente.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            timer1.cancel();
                            timer2.cancel();
                            EmergenciaActivity.super.finish();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    break;
                case 1:
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("La emergencia será tratada en segundo plano.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            timer1.cancel();
                            timer2.cancel();
                            EmergenciaActivity.super.finish();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    alert = builder.create();
                    alert.show();
                    break;
                case 2:
                    timer1.cancel();
                    timer2.cancel();
                    this.finish();
                    break;
            }

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (state){
                case 0:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("La emergencia aún no ha sido detectada y pondría en riesgo al paciente.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            timer1.cancel();
                            timer2.cancel();
                            EmergenciaActivity.super.finish();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    break;
                case 1:
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("La emergencia será tratada en segundo plano.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            timer1.cancel();
                            timer2.cancel();
                            EmergenciaActivity.super.finish();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    alert = builder.create();
                    alert.show();
                    break;
                case 2:
                    timer1.cancel();
                    timer2.cancel();
                    this.finish();
                    break;
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    public void llamaAmbu(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.preguntaAmbu);
        builder.setMessage("");

        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                ImageButton b=findViewById(R.id.botonAmbu);
                b.setImageResource(R.drawable.circuloverde);
                b.setClickable(false);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}