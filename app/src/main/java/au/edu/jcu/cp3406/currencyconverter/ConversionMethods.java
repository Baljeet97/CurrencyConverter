package au.edu.jcu.cp3406.currencyconverter;
/*ConversionMethods is used to contain all the methods regarding the calculation of the currency conversion methods, and future mathematical methods*/

import java.math.BigDecimal;
import java.math.RoundingMode;

class ConversionMethods {

    /*Part one of converting user input into selected currency type
    Method convertUSD converts the user's input of selected currency type, stored in string variable currencyType,
    into a base unit of United States Dollar (USD) and stores it in the global variable userInputUsd*/
    static double convertUSD(double userInput) {
        double userInputUsd;
        userInputUsd = userInput;
        return userInputUsd;
    }

    /*Part two of converting user input into selected currency type.
    Method convertActual takes in the value of userInputUsd and calculates the result of the conversion based on what user has selected, stored in string variable currencyType*/
    static double convertActual(String currencyType, double userInputUsd) {
        //Conversion rates are from  date : "30/3/2020" Time: 1pm"
        double convertedResult = 0.0;
        switch (currencyType) {
            //American dollar
            case "usd":
                convertedResult = userInputUsd * 0.61;
                break;
            //Pound sterling
            case "gbp":
                convertedResult = userInputUsd * 0.50;
                break;
            //Canadian Dollar
            case "cad":
                convertedResult = userInputUsd * 0.86;
                break;
            //Thai Baht
            case "thb":
                convertedResult = userInputUsd * 19.86;
                break;
            //New Zealand Dollar
            case "nz":
                convertedResult = userInputUsd * 1.02;
                break;
            //Japanese Yen
            case "jpy":
                convertedResult = userInputUsd * 65.91;
                break;
            //Euro
            case "eur":
                convertedResult = userInputUsd * 0.55;
                break;
            //Singapore Dollar
            case "sin":
                convertedResult = userInputUsd * 0.88;
                break;
            //Indonesian Rupiah
            case "idr":
                convertedResult = userInputUsd * 10036.34;
                break;
            //United Arab Emirates Dirham
            case "uae":
                convertedResult = userInputUsd * 2.26;
                break;
            //Indian Rupee
            case "inr":
                convertedResult = userInputUsd * 46.37;
                break;
            //Hong Kong Dollar
            case "hkd":
                convertedResult = userInputUsd * 4.76;
                break;
            case "":
                break;
        }
        return convertedResult;
    }

    /*Method to round double number to two decimal places.*/
    static Double roundConvertedValue(Double convertedResult, int decimalPlace) {
        if (decimalPlace < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(convertedResult);
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
