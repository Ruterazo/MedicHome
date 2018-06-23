package com.apps.rubenruterazo.medichome;

import java.io.Serializable;
import java.util.ArrayList;



public class CardsAnalisis implements Serializable{
    private static final long serialVersionUID = 1L;
    private int mImageResource;
    private String mText1;
    private String mText2;
    private String mContenido;
    public CardsAnalisis(int imageResource,String text1,String text2,String contenido){
        mImageResource=imageResource;
        mText1=text1;
        mText2=text2;
        mContenido=contenido;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getText1(){
        return mText1;
    }
    public String getText2(){
        return mText2;
    }

    public String getContenido() {
        return mContenido;
    }
}
