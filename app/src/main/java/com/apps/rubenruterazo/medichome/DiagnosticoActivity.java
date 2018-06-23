package com.apps.rubenruterazo.medichome;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DiagnosticoActivity extends AppCompatActivity {
    CountDownTimer timer1, timer2;
    int state;
    ArrayList<CardsAnalisis> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico);
        lista=new ArrayList<>();
        CardsAnalisisW wrap=(CardsAnalisisW) getIntent().getExtras().getSerializable("lista");
        lista=wrap.getItemDetails();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        state=0;
        GifImageView gifImageView = (GifImageView) findViewById(R.id.cargadiagnostico);
        gifImageView.setGifImageResource(R.drawable.load);

        timer1 = new CountDownTimer(5000, 100) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c);
                df = new SimpleDateFormat("HH:mm:ss");
                String ft=df.format(c);

                Random rand = new Random();
                state = rand.nextInt(3) + 1;
                //state=3;
                findViewById(R.id.escaneo).setVisibility(View.INVISIBLE);
                switch (state){
                    case 1:
                        CardsAnalisis ca=new CardsAnalisis(R.drawable.ic_delete_black_24dp,formattedDate,ft,"Saludable");
                        lista.add(ca);
                        findViewById(R.id.saludable).setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        ca=new CardsAnalisis(R.drawable.ic_delete_black_24dp,formattedDate,ft,"Infección");
                        lista.add(ca);
                        findViewById(R.id.infeccion).setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        ca=new CardsAnalisis(R.drawable.ic_delete_black_24dp,formattedDate,ft,"Apendicitis");
                        lista.add(ca);
                        findViewById(R.id.apendicitis).setVisibility(View.VISIBLE);
                        break;
                }


                /*
                findViewById(R.id.Boton).setVisibility(View.VISIBLE);
                GifImageView gifImageView2 = (GifImageView) findViewById(R.id.operacionemergencia);
                gifImageView2.setGifImageResource(R.drawable.circles);
                timer2.start();*/
            }
        };
        timer1.start();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        CardsAnalisisW wrap=new CardsAnalisisW(lista);
        if (id == android.R.id.home){
            switch (state){
                case 0:

                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
                case 2:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("Si sale sin descargar la receta no tendrá posibilidad de recuperarla.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            CardsAnalisisW wrap=new CardsAnalisisW(lista);
                            getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                            setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                            DiagnosticoActivity.super.finish();
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
                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
                case 3:
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("Si sale ahora, dejará sin operar.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            CardsAnalisisW wrap=new CardsAnalisisW(lista);
                            getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                            setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                            DiagnosticoActivity.super.finish();
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
                case 4:
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("Importante");
                    builder.setMessage("No se puede interrumpir una operación");

                    builder.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            dialog.dismiss();
                        }
                    });
                    alert = builder.create();
                    alert.show();
                    break;
                case 5:
                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
                case 22:
                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        CardsAnalisisW wrap=new CardsAnalisisW(lista);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (state) {
                case 0:
                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
                case 2:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("Si sale sin descargar la receta no tendrá posibilidad de recuperarla.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            CardsAnalisisW wrap=new CardsAnalisisW(lista);
                            getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                            setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                            DiagnosticoActivity.super.finish();
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
                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
                case 3:
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("¿Seguro que quiere salir?");
                    builder.setMessage("Si sale ahora, dejará sin operar.");

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            CardsAnalisisW wrap=new CardsAnalisisW(lista);
                            getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                            setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                            DiagnosticoActivity.super.finish();
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
                case 4:
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("Importante");
                    builder.setMessage("No se puede interrumpir una operación");

                    builder.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            dialog.dismiss();
                        }
                    });
                    alert = builder.create();
                    alert.show();
                    break;
                case 5:
                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
                case 22:
                    getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
                    setResult(DiagnosticoActivity.RESULT_OK, getIntent());
                    this.finish();
                    break;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    public void descargar(View view) {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String formattedDate = df.format(c);
        new DownloadFile().execute("https://docs.google.com/uc?export=download&id=1678czw9PaDr4j0tjWX_f1xjX12vst10n", "receta_"+formattedDate+".pdf");
        Button b=findViewById(R.id.buttondescarga);
        b.setClickable(false);
        b.setText("Descargado");
        state=22;
        //https://docs.google.com/uc?export=download&id=1678czw9PaDr4j0tjWX_f1xjX12vst10n
    }

    public void operar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Está seguro de que quiere operar?");
        builder.setMessage("Una vez comenzada la operación no puede interrumpirse bajo ningún concepto, por tanto continue solo si entiende los riesgos.");

        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                state=4;
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                findViewById(R.id.apendicitis).setVisibility(View.INVISIBLE);
                timer2 = new CountDownTimer(15000, 100) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        state=5;
                        getSupportActionBar().setDisplayShowHomeEnabled(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        findViewById(R.id.operando).setVisibility(View.INVISIBLE);
                        findViewById(R.id.operado).setVisibility(View.VISIBLE);

                    }
                };
                findViewById(R.id.operando).setVisibility(View.VISIBLE);
                GifImageView gifImageView2 = (GifImageView) findViewById(R.id.operaciondiagnostico);
                gifImageView2.setGifImageResource(R.drawable.circles);
                timer2.start();
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

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "Download");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
    }
}