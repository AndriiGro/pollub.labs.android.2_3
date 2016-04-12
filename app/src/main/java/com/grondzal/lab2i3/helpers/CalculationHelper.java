package com.grondzal.lab2i3.helpers;

import java.util.ArrayList;

/**
 * Created by Andrii on 05.04.2016.
 */
public class CalculationHelper {
    public double calculateAverageMark(ArrayList<ModelOceny> marksList){
        double average = 0.0;
        double marksSum = 0.0;
        double marksQuantity = 0.0;

        for (ModelOceny ocena : marksList) {
            marksSum+=ocena.getOcena();
            marksQuantity+=1;
        }

        average = marksSum / marksQuantity;

        return average;
    }
}
