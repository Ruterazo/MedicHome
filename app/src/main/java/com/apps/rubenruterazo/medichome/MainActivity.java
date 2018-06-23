package com.apps.rubenruterazo.medichome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton botonwifis;
    boolean connectState=false;
   // private SharedPreferences mPrefs;
    public final static int REQUEST_CODE_A = 2;
    public final static int REQUEST_CODE_B = 3;
    private ArrayList<CardsAnalisis> mainlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null) {
            connectState = savedInstanceState.getBoolean("conectado");
            CardsAnalisisW wrap= (CardsAnalisisW) savedInstanceState.getSerializable("lista");
            mainlist= wrap.getItemDetails();
        }
        //mainlist.add(new CardsAnalisis(R.drawable.ic_delete_black_24dp,"hola","jili","joder"));
        //SharedPreferences mPrefs = getSharedPreferences();
       // connectState = mPrefs.getBoolean("conectado", connectState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botonwifis = (ImageButton) findViewById(R.id.Botonwifi);
        if(connectState) {
            botonwifis.setImageResource(R.drawable.conwifi);
        }
        else {
            botonwifis.setImageResource(R.drawable.sinwifi);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("WIP");
        builder.setMessage("Se implementará en futuras versiones");

        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.dismiss();
            }
        });


        AlertDialog alert = builder.create();
        //alert.show();
        if (id == R.id.nav_resultados) {
            // Handle the camera action
            Intent res= new Intent(MainActivity.this, ResultadosActivity.class);
            CardsAnalisisW wrap=new CardsAnalisisW(mainlist);
            res.putExtra("lista",wrap);
            startActivityForResult(res,REQUEST_CODE_B);
        } else if (id == R.id.nav_recom) {
            alert.show();
        } else if (id == R.id.nav_info) {
            alert.show();
        } else if (id == R.id.nav_manageaccounts) {
            alert.show();
        } else if (id == R.id.nav_notifications) {
            alert.show();
        } else if (id == R.id.nav_phones) {
            alert.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_CODE_A:
                connectState=data.getExtras().getBoolean("connectState",false);
                if(connectState)
                    botonwifis.setImageResource(R.drawable.conwifi);
                else
                    botonwifis.setImageResource(R.drawable.sinwifi);
                break;
            case REQUEST_CODE_B:
                CardsAnalisisW wrap=(CardsAnalisisW)  data.getExtras().getSerializable("lista");
                mainlist= wrap.getItemDetails();

        }
    }

    public void clickEmer(View view) {
        if(connectState) {
            Intent emer = new Intent(MainActivity.this, EmergenciaActivity.class);
            startActivity(emer);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aviso");
            builder.setMessage("Primero conecte el dispositivo a la MedicTable");

            builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    dialog.dismiss();
                }
            });


            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void clickDiag(View view) {
        if(connectState) {
            Intent diag = new Intent(MainActivity.this, DiagnosticoActivity.class);
            CardsAnalisisW wrap=new CardsAnalisisW(mainlist);
            diag.putExtra("lista",wrap);
            startActivityForResult(diag,REQUEST_CODE_B);

        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aviso");
            builder.setMessage("Primero conecte el dispositivo a la MedicTable");

            builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    dialog.dismiss();
                }
            });


            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void clickWifi(View view) {
        Intent conect= new Intent(MainActivity.this,ConnectActivity.class);
        conect.putExtra("connectState",connectState);
        startActivityForResult(conect,REQUEST_CODE_A);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putBoolean("conectado", connectState);
        CardsAnalisisW wrap=new CardsAnalisisW(mainlist);
        savedInstanceState.putSerializable("lista",wrap);

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
