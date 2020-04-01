package au.edu.jcu.cp3406.currencyconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button currencyButton;
    SharedPreferences preferences; //used to store and manage values based on user activity from current and other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme(); //Setting preferred theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyButton = findViewById(R.id.currencyButton);
    }

    //Method onButtonPress for android:onClick in the activity_main.xml file
    public void onButtonPress(View view){
        switch(view.getId()){
            case R.id.currencyButton:   //Starts activity_convert_currency on button click
                Intent convertCurrency = new Intent(this, ConversionActivity.class);
                startActivity(convertCurrency);
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
