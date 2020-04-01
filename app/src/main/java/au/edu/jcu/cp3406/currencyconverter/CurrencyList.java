package au.edu.jcu.cp3406.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CurrencyList extends AppCompatActivity {
    Boolean currencyOptionStatus; //Boolean variable checks if user has clicked on initial or converted currency button
    Button usdButton, eurButton, gbpButton, hkdButton, sinButton,
            idrButton, inrButton, jpyButton, cadButton, nzdButton, thbButton, uaeButton;
    SharedPreferences preferences; //used to store and manage values based on user activity from current and other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate, creating objects to be used
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        setTitle("List of Currencies");
        usdButton = findViewById(R.id.usdButton);
        gbpButton = findViewById(R.id.gbpButton);
        cadButton = findViewById(R.id.cadButton);
        thbButton = findViewById(R.id.thbButton);
        nzdButton = findViewById(R.id.nzButton);
        jpyButton = findViewById(R.id.jpyButton);
        eurButton = findViewById(R.id.eurButton);
        sinButton = findViewById(R.id.sinButton);
        idrButton = findViewById(R.id.idrButton);
        uaeButton = findViewById(R.id.uaeButton);
        inrButton = findViewById(R.id.inrButton);
        hkdButton = findViewById(R.id.hkdButton);
        cadButton = findViewById(R.id.cadButton);

    }

    @Override
    protected void onStart() {  //onStart, get Boolean variables that help to decide how this activity was started
        super.onStart();
        currencyOptionStatus = preferences.getBoolean("currencyBoolean", true);
    }

    @Override
    protected void onResume() { //onResume, get Boolean variables that help to decide how this activity was started
        super.onResume();
        currencyOptionStatus = preferences.getBoolean("currencyBoolean", true);
    }

    /*Method onButtonPress for android:onClick in the activity_currency_selection.xml file
    Checks the Boolean variable currencyOptionStatus to assign values accordingly;
    True appends the preferences values to reflect changes in the initial currency type
    False appends the preferences values to reflect changes in the converting currency type
    Boolean variable settingsOptionStatus is used to determine if activity was started from SettingsActivity; True = yes, False = no*/
    public void onButtonPress(View view) {
        new Intent(this, ConversionActivity.class);
        new Intent(this, SettingsActivity.class);
        switch (view.getId()) {
            case (R.id.usdButton):
                if (!currencyOptionStatus) {   //Else if button clicked is for converting result, and not from settings page
                    preferences.edit().putString("Option2", "usd").apply();

                }
                finish(); //Ends the activity
                break;

            case (R.id.gbpButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "gbp").apply();

                }
                finish();
                break;

            case (R.id.cadButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "cad").apply();
                }
                finish();
                break;

            case (R.id.thbButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "thb").apply();
                }
                finish();
                break;

            case (R.id.nzButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "nz").apply();
                }
                finish();
                break;

            case (R.id.jpyButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "jpy").apply();
                }
                finish();
                break;

            case (R.id.eurButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "eur").apply();
                }
                finish();
                break;

            case (R.id.sinButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "sin").apply();
                }
                finish();
                break;

            case (R.id.idrButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "idr").apply();
                }
                finish();
                break;

            case (R.id.uaeButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "uae").apply();
                }
                finish();
                break;

            case (R.id.inrButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "inr").apply();
                }
                finish();
                break;

            case (R.id.hkdButton):
                if (!currencyOptionStatus) {
                    preferences.edit().putString("Option2", "hkd").apply();
                }
                finish();
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

