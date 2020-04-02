package au.edu.jcu.cp3406.currencyconverter;
/*ConversionMethod is use to convert store user input and convert it to selected currency*/

import java.math.BigDecimal;
import java.math.RoundingMode;

class ConversionMethods {

    /*this method is use to store user input*/
    static double userInput(double userInput) {
        double userInputAud;
        userInputAud = userInput;
        return userInputAud;
    }

    /*Part two of converting user input into selected currency type*/
    static double conversion(String currencyType, double userInputAud) {
        //Conversion rates are from  date : "30/3/2020" Time: 1pm"
        double convertedResult = 0.0;
        switch (currencyType) {
            //American dollar
            case "usd":
                convertedResult = userInputAud * 0.61;
                break;
            //Pound sterling
            case "gbp":
                convertedResult = userInputAud * 0.5;
                break;
            //Canadian Dollar
            case "cad":
                convertedResult = userInputAud * 0.86;
                break;
            //Thai Baht
            case "thb":
                convertedResult = userInputAud * 19.86;
                break;
            //New Zealand Dollar
            case "nz":
                convertedResult = userInputAud * 1.02;
                break;
            //Japanese Yen
            case "jpy":
                convertedResult = userInputAud * 65.91;
                break;
            //Euro
            case "euro":
                convertedResult = userInputAud * 0.55;
                break;
            //Singapore Dollar
            case "sin":
                convertedResult = userInputAud * 0.88;
                break;
            //Indonesian Rupiah
            case "idr":
                convertedResult = userInputAud * 10036.34;
                break;
            //United Arab Emirates Dirham
            case "uae":
                convertedResult = userInputAud * 2.26;
                break;
            //Indian Rupee
            case "inr":
                convertedResult = userInputAud * 46.37;
                break;
            //Hong Kong Dollar
            case "hkd":
                convertedResult = userInputAud * 4.76;
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
