package com.apps.rubenruterazo.medichome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuItem;

import java.util.ArrayList;

public class ResultadosActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AdaptadorRecycler mAdapter;
    private RecyclerView.LayoutManager mLayout;
    ArrayList<CardsAnalisis> analisis;
    int superposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resultados);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        analisis=new ArrayList<>();
        CardsAnalisisW wrap= (CardsAnalisisW)getIntent().getExtras().getSerializable("lista");
        analisis=wrap.getItemDetails();
        if(savedInstanceState!=null) {

            wrap= (CardsAnalisisW) savedInstanceState.getSerializable("lista");
            analisis= wrap.getItemDetails();
        }
        //analisis.add(new CardsAnalisis(R.drawable.ic_delete_black_24dp,"hola","jili","joder"));

        buildRecyclerView();

    }
    public void removeItem(int position){
        superposition=position;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar resultado");
        builder.setMessage("¿Seguro que quiere eliminar el resultado?");


        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                analisis.remove(superposition);
                mAdapter.notifyItemRemoved(superposition);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            CardsAnalisisW wrap=new CardsAnalisisW(analisis);
            getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
            setResult(DiagnosticoActivity.RESULT_OK, getIntent());
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            CardsAnalisisW wrap=new CardsAnalisisW(analisis);
            getIntent().putExtra("lista",wrap);  // put data that you want returned to activity A
            setResult(DiagnosticoActivity.RESULT_OK, getIntent());
            this.finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    public void buildRecyclerView(){
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayout=new LinearLayoutManager(this);
        mAdapter=new AdaptadorRecycler(analisis);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdaptadorRecycler.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent res= new Intent(ResultadosActivity.this, ResultadoActivity.class);
                res.putExtra("label",analisis.get(position).getText1()+" "+analisis.get(position).getText2());
                res.putExtra("contenido",analisis.get(position).getContenido());
                startActivity(res);
            }

            @Override
            public void onDeleteClick(int position) {

                removeItem(position);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.


        CardsAnalisisW wrap=new CardsAnalisisW(analisis);
        savedInstanceState.putSerializable("lista",wrap);

        // etc.

        super.onSaveInstanceState(savedInstanceState);
    }
}
