package com.grondzal.lab2i3.helpers;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

/**
 * Created by Andrii on 05.04.2016.
 */
public class ModelOceny implements Parcelable {
    private String nazwaOceny;
    private Integer ocena;

    public ModelOceny(String nazwaOceny) {
        this.nazwaOceny = nazwaOceny;
        ocena = 0;
    }

    public ModelOceny(String nazwaOceny, Integer ocena) {
        this.nazwaOceny = nazwaOceny;
        this.ocena = ocena;
    }

    protected ModelOceny(Parcel in) {
        nazwaOceny = in.readString();
    }

    public static final Creator<ModelOceny> CREATOR = new Creator<ModelOceny>() {
        @Override
        public ModelOceny createFromParcel(Parcel in) {
            return new ModelOceny(in);
        }

        @Override
        public ModelOceny[] newArray(int size) {
            return new ModelOceny[size];
        }
    };

    public String getNazwaOceny() {
        return nazwaOceny;
    }

    public void setNazwaOceny(String nazwaOceny) {
        this.nazwaOceny = nazwaOceny;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public Integer getOcena() {
        return ocena;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nazwaOceny);
    }
}
