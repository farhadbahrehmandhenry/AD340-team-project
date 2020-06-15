package com.example.ad340_team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HostActivity extends AppCompatActivity {
    public ImageView picture;
    public TextView name;
    public TextView price;
    public TextView location;
    public TextView available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        location = findViewById(R.id.location);
        available = findViewById(R.id.available);
        picture = findViewById(R.id.image);

        Bundle extras = getIntent().getExtras();
        String value;
        if (extras != null) {
//            picture.setImageDrawable(Drawable.createFromPath(extras.getString(Constants.KEY_PICTURE)));
            price.setText(extras.getString(Constants.KEY_PRICE));
            available.setText(extras.getString(Constants.KEY_AVAILABLE));
            location.setText(extras.getString(Constants.KEY_LOCATION));
            name.setText(extras.getString(Constants.KEY_NAME));
        }
    }

    public void back(View view) {
        finish();
    }

    public void select(View view) {
        Toast toast = Toast.makeText(this, "You Selected This Host!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 100);
        toast.show();
    }
}