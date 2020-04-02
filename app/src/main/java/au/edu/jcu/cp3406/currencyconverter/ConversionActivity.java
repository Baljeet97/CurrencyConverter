package au.edu.jcu.cp3406.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.conversion;
import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.userInput;
import static au.edu.jcu.cp3406.currencyconverter.ConversionMethods.roundConvertedValue;


public class ConversionActivity extends AppCompatActivity {

    EditText inputCurrency;
    TextView convertedCurrency;
    TextView inputCurrencyType;
    TextView convertedCurrencyType;
    double userInput; //to store user input
    double userInputUsd; //conversion calculations
    double convertedResult; //conversion calculations
    SharedPreferences preferences; //to store and manage values based activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme(); //Setting preferred theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converting_currency);
        setTitle("Currency Converter"); //Setting title
        inputCurrency = findViewById(R.id.inputCurrency);
        convertedCurrency = findViewById(R.id.convertedCurrency);
        inputCurrencyType = findViewById(R.id.inputCurrencyType);
        convertedCurrencyType = findViewById(R.id.convertedCurrencyType);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currencyConverterMain();
    }

    protected void onResume() {
        super.onResume();
        currencyConverterMain();
    }

    /*Method to set preference, values, and converting user input to final result*/
    public void currencyConverterMain() {

        convertedCurrencyType.setText(preferences.getString("Option2", "Choose a currency!"));

        inputCurrency.setText(preferences.getString("initialUserInput", null)); //Saving user input between activities.

        inputCurrency.addTextChangedListener(new TextWatcher() { //Listener for EditText inputCurrency
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    preferences.edit().putString("initialUserInput", charSequence.toString()).apply(); //saving currency type value
                    userInput = Double.parseDouble(charSequence.toString()); //converts user input into EditText to string

                    //if statement to check if currency type is selected
                    if (!convertedCurrencyType.getText().toString().equals("Choose a currency!")) {
                        userInputUsd = userInput(userInput);
                        convertedResult = conversion(convertedCurrencyType.getText().toString(), userInputUsd);
                        int roundOffInt = preferences.getInt("roundingOff", 2);
                        convertedCurrency.setText(Double.toString(roundConvertedValue(convertedResult, roundOffInt)));
                    }
                } catch (Exception ignored) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    //Method called for android:onClick in the activity_convert_currency.xml file
    @SuppressLint("SetTextI18n")
    public void onButtonPress(View view) {
        Intent settings = new Intent(this, SettingsActivity.class); //Intent for switching to the settings screen
        Intent returnToMenu = new Intent(this, MainActivity.class); //Intent for returning to the main menu
        Intent CurrencyList = new Intent(this, CurrencyList.class);  //Intent for switching to the currency menu
        switch (view.getId()) {
            //home button
            case R.id.homeButton:
                startActivity(returnToMenu);
                break;
            //settings button
            case R.id.settingsButton:
                startActivity(settings);
                break;
            case R.id.convertedCurrencyButton:
                preferences.edit().putBoolean("currencyBoolean", false).apply(); //Boolean = False if button clicked is converted currency
                startActivity(CurrencyList);
                break;
            //resetting everything
            case R.id.resetButton:
                inputCurrencyType.setText("AUD");
                convertedCurrencyType.setText("Choose a currency!");
                preferences.edit().putString("Option2", null).apply();
                inputCurrency.setText("");
                convertedCurrency.setText("The result will appear here!");
                userInput = 0;
                userInputUsd = 0;
                convertedResult = 0;
                break;
        }
    }

    //Method to theme according to user preference
    public void setPrefTheme() {
        String prefTheme = preferences.getString("themeName", "AppTheme");
        if (prefTheme.equals("AppTheme")) {
            setTheme(R.style.AppTheme);
        } else if (prefTheme.equals("NightMode")) {
            setTheme(R.style.NightMode);
        }
    }
}

