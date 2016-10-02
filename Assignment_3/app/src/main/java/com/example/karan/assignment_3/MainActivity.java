package com.example.karan.assignment_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameData;
    private EditText rollNumberData;
    private EditText batchData;

    private SharedPreferences prefData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton;
        final String filename = "MySharedPreferences";

        nameData = (EditText) findViewById(R.id.name);
        rollNumberData = (EditText) findViewById(R.id.rollNumber);
        batchData = (EditText) findViewById(R.id.batch);

        submitButton = (Button) findViewById(R.id.submit);

        prefData = getSharedPreferences(filename, MODE_PRIVATE);                         // Creating a SharedPreference File

        nameData.setText(prefData.getString("name", ""));
        rollNumberData.setText(prefData.getString("rollNumber", ""));
        batchData.setText(prefData.getString("batch", ""));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                            // Submit Button set onClick Listener
                String name = nameData.getText().toString();
                String rollNumber = rollNumberData.getText().toString();
                String batch = batchData.getText().toString();

                SharedPreferences.Editor editor = prefData.edit();                      // Save the values in sharedPreference

                editor.putString("name", name);
                editor.putString("rollNumber", rollNumber);
                editor.putString("batch", batch);

                editor.commit();

                Intent intent = new Intent(view.getContext(), FormActivity.class);      // On submit, call a new intent having a form
                startActivity(intent);
            }
        });

    }
}
