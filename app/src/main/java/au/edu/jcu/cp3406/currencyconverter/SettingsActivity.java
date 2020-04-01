package au.edu.jcu.cp3406.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingsActivity extends AppCompatActivity {
    RadioGroup sigFigRadioGroup;
    RadioGroup themeRadioGroup;
    RadioButton wholeNumber;
    RadioButton twoDecimals;
    RadioButton defaultTheme;
    RadioButton nightTheme;
    SharedPreferences preferences; //used to store and manage values based on user activity from current and other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Creating objects
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme(); //Setting preferred theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings"); //Setting display title
        sigFigRadioGroup = findViewById(R.id.sigFigRadioGroup);
        wholeNumber = findViewById(R.id.wholeNumber);
        twoDecimals = findViewById(R.id.twoDecimals);
        themeRadioGroup = findViewById(R.id.themeRadioGroup);
        defaultTheme = findViewById(R.id.defaultTheme);
        nightTheme = findViewById(R.id.nightTheme);
//        funTheme = findViewById(R.id.funTheme);
//        defaultImage = findViewById(R.id.defaultImage);

        //IF/ELSE statement to decide which radio button is checked by default, based on previous or default selection for the Significant Figures
        if(preferences.getInt("roundingOff", 2) == 0){
            sigFigRadioGroup.check(wholeNumber.getId());
        }
        else{sigFigRadioGroup.check(twoDecimals.getId());}

        //IF/ELSE statement to decide which radio button is checked by default, based on previous or default selection for the Themes
        if(preferences.getString("themeName", "AppTheme").equals("AppTheme")){
            themeRadioGroup.check(defaultTheme.getId());
        }
        else if(preferences.getString("themeName", "AppTheme").equals("NightMode")){
            themeRadioGroup.check(nightTheme.getId());
        }
    }

    @Override
    protected void onStart() {  //onStart load the image for the preferred currency settings, based on previous or default selection
        super.onStart();
        String imageFileName = preferences.getString("settingsPref", "sgd");
        int resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
//        defaultImage.setImageResource(resId);
    }

    //Method onButtonPress for android:onClick in the activity_settings.xml file
    public void onButtonPress(View view){
        Intent goToCurrencyMenu = new Intent(this, CurrencyList.class);  //Intent for switching to the currency options page
        Intent returnToCurrencyConverter = new Intent(this, ConversionActivity.class);  //Intent for returning to the currency conversion page

        //Switch-Case statement for the different buttons and actions to be carried out
        switch(view.getId()){
            case R.id.backButton:   //Returns user to the currency conversion activity
                startActivity(returnToCurrencyConverter);
                finish();
                break;

//            case R.id.currencyOptionsButton:    //Brings user to the currency type screen
//                preferences.edit().putBoolean("settingsBoolean", true).apply(); //Boolean variable to check if currency options page is accessed from Settings, and not the curreny conversion activity
//                startActivity(goToCurrencyMenu);
//                break;
        }
    }

    //Method onRadioButtonPress for android:onClick for radio buttons in the activity_settings.xml file
    public void onRadioButtonPress(View view){
        Intent refreshSettings = new Intent(this, SettingsActivity.class);  //Intent used to restart the activity to reflect changes to the theme
        refreshSettings.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); //Enables a smoother transition
        switch(view.getId()){
            case R.id.wholeNumber:
                preferences.edit().putInt("roundingOff", 0).apply();    //set preferences to whole numbers
                break;
            case R.id.twoDecimals:
                preferences.edit().putInt("roundingOff", 2).apply();    //set preferences to two decimals
                break;
            case R.id.defaultTheme:
                preferences.edit().putString("themeName", "AppTheme").apply(); //set preferences to the default theme
                finish();   //stops the current activity
                startActivity(refreshSettings); //Starts the activity again
                break;
            case R.id.nightTheme:
                preferences.edit().putString("themeName", "NightMode").apply(); //set preferences to the night theme
                finish();   //stops the current activity
                startActivity(refreshSettings); //Starts the activity again
                break;
        }
    }

    //Method to set preference application theme according to previous user selection
    public void setPrefTheme(){
        String prefTheme = preferences.getString("themeName", "AppTheme");
        if(prefTheme.equals("AppTheme")){
            setTheme(R.style.AppTheme);
        }
        else if(prefTheme.equals("NightMode")){
            setTheme(R.style.NightMode);
        }

    }
}
