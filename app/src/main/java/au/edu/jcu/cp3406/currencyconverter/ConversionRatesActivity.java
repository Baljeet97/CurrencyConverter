package au.edu.jcu.cp3406.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class ConversionRatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_rates);
    }

    public void homeClicked(View view) {
        Intent returnToHome = new Intent(this, MainActivity.class);
        if (view.getId() == R.id.returnHome) {
            startActivity(returnToHome);
            finish();
        }
    }
}
