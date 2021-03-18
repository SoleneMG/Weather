package main.presentation.utils;

import java.text.NumberFormat;

public class NumberFormatPresentation {

    public static String twoNumberAfterDecimalPoint(double number){
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        return format.format(number);
    }
}
