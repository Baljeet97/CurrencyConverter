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
    TextView currencyType;
    TextView convertedCurrencyType;
    double userInput; //to store user input
    double userInputUsd; //conversion calculations
    double convertedResult; //conversion calculations
    SharedPreferences sharedPreferences; //to store and manage values based activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme(); //Setting preferred theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        setTitle("Currency Converter"); //Setting title
        inputCurrency = findViewById(R.id.inputCurrency);
        convertedCurrency = findViewById(R.id.convertedCurrency);
        currencyType = findViewById(R.id.currencyType);
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

        convertedCurrencyType.setText(sharedPreferences.getString("Option2", "Choose a currency!"));

        inputCurrency.setText(sharedPreferences.getString("UserInput", null)); //Saving user input between activities.

        inputCurrency.addTextChangedListener(new TextWatcher() { //Listener for EditText inputCurrency
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    sharedPreferences.edit().putString("UserInput", charSequence.toString()).apply(); //saving currency value
                    userInput = Double.parseDouble(charSequence.toString()); //converts user input into EditText to string

                    //if statement to check if currency type is selected
                    if (!convertedCurrencyType.getText().toString().equals("Choose a currency!")) {
                        userInputUsd = userInput(userInput);
                        convertedResult = conversion(convertedCurrencyType.getText().toString(), userInputUsd);
                        int roundingOff = sharedPreferences.getInt("roundingOff", 2);
                        convertedCurrency.setText(Double.toString(roundConvertedValue(convertedResult, roundingOff)));
                    }
                } catch (Exception ignored) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


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
            case R.id.currencyButton:
                sharedPreferences.edit().putBoolean("currencyBoolean", false).apply();
                startActivity(CurrencyList);
                break;
            //resetting everything
            case R.id.resetButton:
                currencyType.setText("AUD");
                convertedCurrencyType.setText("Choose a currency!");
                sharedPreferences.edit().putString("Option2", null).apply();
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
        String prefTheme = sharedPreferences.getString("themeName", "AppTheme");
        if (prefTheme.equals("AppTheme")) {
            setTheme(R.style.AppTheme);
        } else if (prefTheme.equals("NightMode")) {
            setTheme(R.style.NightMode);
        }
    }
}

