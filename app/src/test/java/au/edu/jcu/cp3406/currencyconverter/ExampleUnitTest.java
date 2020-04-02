package au.edu.jcu.cp3406.currencyconverter;

import org.junit.Test;

import static org.junit.Assert.*;

import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.userInput;
import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.conversion;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void conversion_inputCheck() {
        assertEquals(5, ConversionMethods.userInput(5), 1);
        assertNotEquals(2.1, ConversionMethods.userInput(5), 1);
    }

    @Test
    public void conversion_convertedResult() {
        double userInputAud;
        double convertedResult;

        //test for USD
        userInputAud = userInput(1);
        convertedResult = conversion("usd", userInputAud);
        assertEquals(0.61, convertedResult, 10);

        //test for Pound sterling
        userInputAud = userInput(1);
        convertedResult = conversion("gbp", userInputAud);
        assertEquals(0.5, convertedResult, 10);

        //test for Canadian Dollar
        userInputAud = userInput(1);
        convertedResult = conversion("cad", userInputAud);
        assertEquals(0.86, convertedResult, 10);

        //test for Thai Baht
        userInputAud = userInput(1);
        convertedResult = conversion("thb", userInputAud);
        assertEquals(19.86, convertedResult, 10);

        //test for New Zealand Dollar
        userInputAud = userInput(1);
        convertedResult = conversion("nz", userInputAud);
        assertEquals(1.02, convertedResult, 10);

        //test for Japanese Yen
        userInputAud = userInput(1);
        convertedResult = conversion("jpy", userInputAud);
        assertEquals(65.91, convertedResult, 10);

        //test for Euro
        userInputAud = userInput(1);
        convertedResult = conversion("euro", userInputAud);
        assertEquals(0.55, convertedResult, 10);

        //test for Singapore Dollar
        userInputAud = userInput(1);
        convertedResult = conversion("sin", userInputAud);
        assertEquals(0.88, convertedResult, 10);

        //test for Indonesian Rupiah
        userInputAud = userInput(1);
        convertedResult = conversion("idr", userInputAud);
        assertEquals(10036.34, convertedResult, 10);

        //test for United Arab Emirates Dirham
        userInputAud = userInput(1);
        convertedResult = conversion("uae", userInputAud);
        assertEquals(2.26, convertedResult, 10);

        //test for Indian Rupee
        userInputAud = userInput(1);
        convertedResult = conversion("inr", userInputAud);
        assertEquals(46.37, convertedResult, 10);

        //Hong Kong Dollar
        userInputAud = userInput(1);
        convertedResult = conversion("hkd", userInputAud);
        assertEquals(4.76, convertedResult, 10);
    }
}