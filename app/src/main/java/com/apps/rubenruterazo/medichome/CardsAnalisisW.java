package com.apps.rubenruterazo.medichome;

import java.io.Serializable;
import java.util.ArrayList;

public class CardsAnalisisW implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<CardsAnalisis> item;

    public CardsAnalisisW(ArrayList<CardsAnalisis> items) {
        this.item = items;
    }

    public ArrayList<CardsAnalisis> getItemDetails() {
        return item;
    }
}
