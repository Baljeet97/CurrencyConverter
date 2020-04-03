package au.edu.jcu.cp3406.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingsActivity extends AppCompatActivity {
    RadioGroup sigFigRadioGroup;
    RadioGroup themeRadioGroup;
    RadioButton wholeNumber;
    RadioButton twoDecimals;
    RadioButton defaultTheme;
    RadioButton nightTheme;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setTheme(); //Setting preferred theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings"); //Setting display title
        sigFigRadioGroup = findViewById(R.id.sigFigRadioGroup);
        wholeNumber = findViewById(R.id.wholeNumber);
        twoDecimals = findViewById(R.id.twoDecimals);
        themeRadioGroup = findViewById(R.id.themeRadioGroup);
        defaultTheme = findViewById(R.id.defaultTheme);
        nightTheme = findViewById(R.id.nightTheme);

//if else to check the radio button selection
        if (sharedPreferences.getInt("roundingOff", 2) == 0) {
            sigFigRadioGroup.check(wholeNumber.getId());
        } else {
            sigFigRadioGroup.check(twoDecimals.getId());
        }

        if (sharedPreferences.getString("themeName", "AppTheme").equals("AppTheme")) {
            themeRadioGroup.check(defaultTheme.getId());
        } else if (sharedPreferences.getString("themeName", "AppTheme").equals("NightMode")) {
            themeRadioGroup.check(nightTheme.getId());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onButtonPress(View view) {
        Intent returnToCurrencyConverter = new Intent(this, ConversionActivity.class);

        if (view.getId() == R.id.backButton) {
            startActivity(returnToCurrencyConverter);
            finish();
        }
    }

    public void onRadioButtonPress(View view) {
        Intent refreshSettings = new Intent(this, SettingsActivity.class);  //Intent used to restart the activity to reflect changes to the theme
        refreshSettings.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); //Enables a smoother transition
        switch (view.getId()) {
            case R.id.wholeNumber:
                sharedPreferences.edit().putInt("roundingOff", 0).apply();    //set preferences to whole numbers
                break;
            case R.id.twoDecimals:
                sharedPreferences.edit().putInt("roundingOff", 2).apply();    //set preferences to decimals
                break;
            case R.id.defaultTheme:
                sharedPreferences.edit().putString("themeName", "AppTheme").apply();
                finish();   //stops the current activity
                startActivity(refreshSettings); //Starts the activity again
                break;
            case R.id.nightTheme:
                sharedPreferences.edit().putString("themeName", "NightMode").apply(); //set preferences to the night theme
                finish();
                startActivity(refreshSettings);
                break;
        }
    }

    //method to to set selected theme
    public void setTheme() {
        String prefTheme = sharedPreferences.getString("themeName", "AppTheme");
        if (prefTheme.equals("AppTheme")) {
            setTheme(R.style.AppTheme);
        } else if (prefTheme.equals("NightMode")) {
            setTheme(R.style.NightMode);
        }
    }
}
