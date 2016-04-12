package com.grondzal.lab2i3.helpers;

import java.util.ArrayList;

/**
 * Created by Andrii on 03.04.2016.
 */
public class ValidationHelper {

    public boolean checkIfAllMarksSet(ArrayList<ModelOceny> marksList) {
        boolean isAllMarksSet = true;

        for (ModelOceny ocena : marksList) {
            if (ocena.getOcena() == null || ocena.getOcena().equals(0)){
                isAllMarksSet = false;
                break;
            }
        }

        return isAllMarksSet;
    }
}
