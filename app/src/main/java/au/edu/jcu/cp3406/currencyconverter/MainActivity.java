package au.edu.jcu.cp3406.currencyconverter;
/*Main activity runs */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    SharedPreferences sharedPreferences; //to store preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setTheme(); //Setting theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
    }

    public void onButtonPress(View view) {
        if (view.getId() == R.id.startButton) {
            Intent convertCurrency = new Intent(this, ConversionActivity.class);
            startActivity(convertCurrency);
        }
    }

    public void setTheme() {
        String prefTheme = sharedPreferences.getString("themeName", "AppTheme");
        if (prefTheme.equals("AppTheme")) {
            setTheme(R.style.AppTheme);
        } else if (prefTheme.equals("NightMode")) {
            setTheme(R.style.NightMode);
        }
    }
}
