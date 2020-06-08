package com.example.ad340_team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity {
    private EditText dogName;
    private EditText email;
    private EditText dogAge;
    private EditText dogBreed;
    private Spinner dogSize;
    private EditText description;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogName = findViewById(R.id.dogName);
        email = findViewById(R.id.email);
        dogAge = findViewById(R.id.dogAge);
        dogBreed = findViewById(R.id.dogBreed);
        dogSize = findViewById(R.id.dogSize);
        description = findViewById(R.id.description);
        button = findViewById(R.id.login);

        Spinner dropdown = findViewById(R.id.dogSize);
        String[] items = new String[]{"small", "medium", "big"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public boolean isValid(String field, String fieldName) {
        boolean isValid = false;

        if (TextUtils.isEmpty(field)) {
            StringBuilder msg = new StringBuilder(fieldName).append(getString(R.string.required));

            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();
        } else {
            if (fieldName == Constants.KEY_EMAIL && !Patterns.EMAIL_ADDRESS.matcher(field).matches()) {
                validateEmail(fieldName);
            }
            if (fieldName == Constants.KEY_AGE) {
                int age = Integer.parseInt(field);

                if (age < 1) {
                    Toast toast = Toast.makeText(this, R.string.tooYoung, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 100);
                    toast.show();
                    return false;

                } else if (age > 20) {
                    Toast toast = Toast.makeText(
                            this,
                            R.string.tooOld,
                            Toast.LENGTH_SHORT
                    );
                    toast.setGravity(Gravity.TOP, 0, 100);
                    toast.show();
                    return false;
                }
            }
            if (fieldName == Constants.KEY_SIZE) {
                if (field == "small" || field == "medium" || field == "big") {
                    isValid = true;
                }
                else {
                    isValid = false;
                }
            } else {
                isValid = true;
            }
        }

        return isValid;
    }

    public void goToProfile(View view) {
        String emailAddress = String.valueOf(email.getText()).trim();
        String name = String.valueOf(dogName.getText()).trim();
        String age = String.valueOf(dogAge.getText()).trim();
        String breed = String.valueOf(dogBreed.getText()).trim();
        String dogDescription = String.valueOf(description.getText()).trim();
        Spinner mySpinner = (Spinner) findViewById(R.id.dogSize);
        String size = mySpinner.getSelectedItem().toString();

        boolean emailIsValid = isValid(emailAddress, Constants.KEY_EMAIL);
        boolean nameIsValid = false;
        boolean breedIsValid = false;
        boolean sizeIsValid = false;
        boolean ageIsValid = false;
        boolean descriptionIsValid = false;

        if (emailIsValid) nameIsValid = isValid(name, Constants.KEY_NAME);
        if (nameIsValid) ageIsValid = isValid(age, Constants.KEY_AGE);
        if (ageIsValid) breedIsValid = isValid(breed, Constants.KEY_NAME);
        if (breedIsValid) sizeIsValid = isValid(size, Constants.KEY_SIZE);
        if (sizeIsValid) descriptionIsValid = isValid(dogDescription, Constants.KEY_DESCRIPTION);

        if (nameIsValid && emailIsValid && descriptionIsValid && ageIsValid && breedIsValid && sizeIsValid) {
            Intent intent = new Intent(this, ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_NAME, name);
            bundle.putString(Constants.KEY_EMAIL, emailAddress);
            bundle.putString(Constants.KEY_AGE, age);
            bundle.putString(Constants.KEY_DESCRIPTION, dogDescription);
            bundle.putString(Constants.KEY_BREED, dogDescription);
            bundle.putString(Constants.KEY_SIZE, dogDescription);


            intent.putExtras(bundle);

            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, R.string.signup, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();
        }
    }

    public void validateEmail(String fieldName) {
        StringBuilder msg = new StringBuilder(fieldName).append(getString(R.string.wrongFormat));
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.TOP, 0, 100);
        toast.show();
    }
}