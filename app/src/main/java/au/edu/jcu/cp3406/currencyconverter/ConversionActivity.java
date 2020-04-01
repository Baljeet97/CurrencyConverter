package au.edu.jcu.cp3406.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.convertActual;
import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.convertUSD;
import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.roundConvertedValue;


public class ConversionActivity extends AppCompatActivity {

    EditText inputCurrency;
    TextView convertedCurrency;
    TextView inputCurrencyType;
    TextView convertedCurrencyType;
    double userInput; //used to store user input for calculations
    double userInputUsd; //used in the conversion calculations
    double convertedResult; //used in the conversion calculations
    SharedPreferences preferences; //used to store and manage values based on user activity from current and other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate, creates all required objects
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme(); //Setting preferred theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converting_currency);
        setTitle("Currency Converter"); //Setting display title
        inputCurrency = findViewById(R.id.inputCurrency);
        convertedCurrency = findViewById(R.id.convertedCurrency);
        inputCurrencyType = findViewById(R.id.inputCurrencyType);
        convertedCurrencyType = findViewById(R.id.convertedCurrencyType);

    }

    @Override
    protected void onStart() { //onStart, run main method currencyConverterMain
        super.onStart();
        currencyConverterMain();
    }

    protected void onResume() { //onResume, run main method currencyConverterMain
        super.onResume();
        currencyConverterMain();
    }

    /*Main method run onStart and onResume
    Sets selected preferences, string values, and images, as well as converting user input according to selected currency types*/
    public void currencyConverterMain() {
//        String prefCurrencyType = "AUD";    //String value for the preferred currency type

//        inputCurrencyType.setText(preferences.getString("settingsPref", "aud")); //setting the display text based on the preferred currency type


//        String checkUserInput = preferences.getString("Option", prefCurrencyType); //Checking for user selection for input currency type
//        if(!inputCurrencyType.getText().toString().equals(checkUserInput)){ //If user has selected a currency type that is not the default or value set in the preferences, run
//            inputCurrencyType.setText(preferences.getString("Option1", prefCurrencyType)); //setting the display text based on the selected currency type
//        }

        convertedCurrencyType.setText(preferences.getString("Option2", "Choose a currency!"));

        inputCurrency.setText(preferences.getString("initialUserInput", null)); //Saves the user's input between switching activities.

        inputCurrency.addTextChangedListener(new TextWatcher() { //Listener for EditText inputCurrency
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    preferences.edit().putString("initialUserInput", charSequence.toString()).apply(); //to save the value when returning from choosing currency type
                    userInput = Double.parseDouble(charSequence.toString()); //converts user input into EditText to string

                    //IF/ELSE statement to check if both currency type have been selected, and if so, proceeds with the conversion
                    if (!convertedCurrencyType.getText().toString().equals("Choose a currency!")) {
                        userInputUsd = convertUSD(userInput); //Calling conversion methods
                        convertedResult = convertActual(convertedCurrencyType.getText().toString(), userInputUsd); //Calling conversion methods
                        int roundOffInt = preferences.getInt("roundingOff", 2); //Obtaining the int value to round off based on user preference (default 2 decimal places)
                        convertedCurrency.setText(Double.toString(roundConvertedValue(convertedResult, roundOffInt))); //Rounding off converted result, then parsing to String

                    }
                } catch (Exception ignored) {
                } //Throws exception
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    //Method called for android:onClick in the activity_convert_currency.xml file
    public void onButtonPress(View view) {
        Intent goToOptions = new Intent(this, SettingsActivity.class); //Intent for switching to the settings screen
        Intent returnToMenu = new Intent(this, MainActivity.class); //Intent for returning to the main menu
        Intent goToCurrencyMenu = new Intent(this, CurrencyList.class);  //Intent for switching to the currency options menu
        switch (view.getId()) {
            case R.id.homeButton:   //Home button returns to the initial screen
                startActivity(returnToMenu);
                break;

            case R.id.optionsButton: //Switches to the settings screen
                startActivity(goToOptions);
                break;

            case R.id.convertedCurrencyButton:    //Starts activity that allows users to choose currency type
                preferences.edit().putBoolean("currencyBoolean", false).apply(); //Boolean = False if button clicked is converted currency
                startActivity(goToCurrencyMenu);
                break;

            case R.id.resetButton:  //Resets all variables and stored values, based on default values and preferences
                inputCurrencyType.setText("AUD"); //Setting input currency text based on preferences
                convertedCurrencyType.setText("Choose a currency!"); //Setting converting currency text to default
                preferences.edit().putString("Option2", null).apply(); //Resets user selection for converting currency
                inputCurrency.setText(""); //Resetting values for user input
                convertedCurrency.setText("The result appear here!"); //Resetting values for converted result
                userInput = 0; //Resetting variable value
                userInputUsd = 0; //Resetting variable value
                convertedResult = 0; //Resetting variable value
                break;
        }
    }

    //Method to set preference application theme according to previous user selection
    public void setPrefTheme() {
        String prefTheme = preferences.getString("themeName", "AppTheme");
        if (prefTheme.equals("AppTheme")) {
            setTheme(R.style.AppTheme);
        } else if (prefTheme.equals("NightMode")) {
            setTheme(R.style.NightMode);
        }
    }
}

