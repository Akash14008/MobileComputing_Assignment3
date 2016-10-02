package com.example.karan.assignment_3;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FormActivity extends AppCompatActivity {

    private EditText nameData;
    private EditText rollNumberData;
    private EditText batchData;
    private EditText addressData;
    private EditText mobileData;
    private EditText cgpaData;

    private SharedPreferences prefData;
    private FileOutputStream outStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final Button updateButton;
        final Button doneButton;
        Button deleteButton;
        final String filename1 = "MySharedPreferences";
        final String filename2 = "MyInternalFile";
        final String filename3 = "MyExternalFilePub";
        final String filename4 = "MyExternalFilePrivate";
        final String filename5 = "MyExternalFile";

        nameData = (EditText) findViewById(R.id.name);
        rollNumberData = (EditText) findViewById(R.id.rollNumber);
        batchData = (EditText) findViewById(R.id.batch);
        addressData = (EditText) findViewById(R.id.address);
        mobileData = (EditText) findViewById(R.id.mobileNo);
        cgpaData = (EditText) findViewById(R.id.cgpa);

        nameData.setEnabled(false);
        rollNumberData.setEnabled(false);
        batchData.setEnabled(false);
        addressData.setEnabled(false);
        mobileData.setEnabled(false);
        cgpaData.setEnabled(false);

        updateButton = (Button) findViewById(R.id.update);
        doneButton = (Button) findViewById(R.id.done);
        deleteButton = (Button) findViewById(R.id.delete);

        doneButton.setVisibility(View.GONE);

        prefData = getSharedPreferences(filename1, MODE_PRIVATE);
        outStream = null;

        nameData.setText(prefData.getString("name", ""));
        rollNumberData.setText(prefData.getString("rollNumber", ""));
        batchData.setText(prefData.getString("batch", ""));
                                                                                    // Sqlite command to read from the database
        SQLStuff read_entry = new SQLStuff(FormActivity.this);
        Cursor rs = read_entry.read(nameData.getText().toString(), rollNumberData.getText().toString(), batchData.getText().toString());
        if(rs != null && rs.moveToFirst()) {
            addressData.setText(rs.getString(4));
            mobileData.setText(rs.getString(5));
            cgpaData.setText(rs.getString(6));
        }
        read_entry.close();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                // Handler for update button pressed
                nameData.setEnabled(true);
                rollNumberData.setEnabled(true);
                batchData.setEnabled(true);
                addressData.setEnabled(true);
                mobileData.setEnabled(true);
                cgpaData.setEnabled(true);

                updateButton.setVisibility(View.GONE);
                doneButton.setVisibility(View.VISIBLE);

            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                // saves file on making changes
                String name = nameData.getText().toString();
                String rollNumber = rollNumberData.getText().toString();
                String batch = batchData.getText().toString();
                String address = addressData.getText().toString();
                String mobileNo = mobileData.getText().toString();
                String cgpa = cgpaData.getText().toString();

                try {                                                            // saving in an internal file
                    outStream = openFileOutput(filename2, Context.MODE_PRIVATE);
                    OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
                    outWriter.append("Name:" + name);
                    outWriter.append("\r\n");
                    outWriter.append("Roll Number: " + rollNumber);
                    outWriter.append("\r\n");
                    outWriter.append("Batch: " + batch);
                    outWriter.append("\r\n");
                    outWriter.append("Address: " + address);
                    outWriter.append("\r\n");
                    outWriter.append("Mobile No.: " + mobileNo);
                    outWriter.append("\r\n");
                    outWriter.append("CGPA: " + cgpa);
                    outWriter.append("\r\n");
                    outWriter.close();
                    outStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {       // check if media mounted
                    File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename3);
                    if (!file1.exists()) {                                            // saving in a external storage public doenloads
                        try {
                            file1.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        outStream = new FileOutputStream(file1);
                        OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
                        outWriter.append("Name:" + name);
                        outWriter.append("\r\n");
                        outWriter.append("Roll Number: " + rollNumber);
                        outWriter.append("\r\n");
                        outWriter.append("Batch: " + batch);
                        outWriter.append("\r\n");
                        outWriter.append("Address: " + address);
                        outWriter.append("\r\n");
                        outWriter.append("Mobile No.: " + mobileNo);
                        outWriter.append("\r\n");
                        outWriter.append("CGPA: " + cgpa);
                        outWriter.append("\r\n");
                        outWriter.close();
                        outStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    File file2 = new File(getExternalFilesDir(null), filename4);
                    if (!file2.exists()) {                                          // saving in a external private storage
                        try {
                            file2.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        outStream = new FileOutputStream(file2);
                        OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
                        outWriter.append("Name:" + name);
                        outWriter.append("\r\n");
                        outWriter.append("Roll Number: " + rollNumber);
                        outWriter.append("\r\n");
                        outWriter.append("Batch: " + batch);
                        outWriter.append("\r\n");
                        outWriter.append("Address: " + address);
                        outWriter.append("\r\n");
                        outWriter.append("Mobile No.: " + mobileNo);
                        outWriter.append("\r\n");
                        outWriter.append("CGPA: " + cgpa);
                        outWriter.append("\r\n");
                        outWriter.close();
                        outStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                File file3 = new File(Environment.getExternalStorageDirectory(), filename5);
                if (!file3.exists()) {                                                  // storing in external primary root storage
                    try {
                        file3.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    outStream = new FileOutputStream(file3);
                    OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
                    outWriter.append("Name:" + name);
                    outWriter.append("\r\n");
                    outWriter.append("Roll Number: " + rollNumber);
                    outWriter.append("\r\n");
                    outWriter.append("Batch: " + batch);
                    outWriter.append("\r\n");
                    outWriter.append("Address: " + address);
                    outWriter.append("\r\n");
                    outWriter.append("Mobile No.: " + mobileNo);
                    outWriter.append("\r\n");
                    outWriter.append("CGPA: " + cgpa);
                    outWriter.append("\r\n");
                    outWriter.close();
                    outStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int flag = 0;
                String idData = "";
                SQLStuff temp_entry = new SQLStuff(FormActivity.this);
                Cursor rs = temp_entry.read(name, rollNumber, batch);
                if(rs != null && rs.moveToFirst()) {
                    flag = 1;
                    idData = rs.getString(0);
                }
                temp_entry.close();

                if(flag == 0) {                                                         // if new data insert
                    SQLStuff insert_entry = new SQLStuff(FormActivity.this);
                    insert_entry.insert(name, rollNumber, batch, address, mobileNo, cgpa);
                    insert_entry.close();
                }

                else {
                    SQLStuff update_entry = new SQLStuff(FormActivity.this);            // if data already exist update
                    update_entry.update(idData, name, rollNumber, batch, address, mobileNo, cgpa);
                    update_entry.close();
                }

                nameData.setEnabled(false);
                rollNumberData.setEnabled(false);
                batchData.setEnabled(false);
                addressData.setEnabled(false);
                mobileData.setEnabled(false);
                cgpaData.setEnabled(false);

                doneButton.setVisibility(View.GONE);
                updateButton.setVisibility(View.VISIBLE);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                    // delete button handler
                int flag = 0;
                String idData = "";
                SQLStuff temp_entry = new SQLStuff(FormActivity.this);                  // delete data if exist
                Cursor rs = temp_entry.read(nameData.getText().toString(), rollNumberData.getText().toString(), batchData.getText().toString());
                if(rs != null && rs.moveToFirst()) {
                    flag = 1;
                    idData = rs.getString(0);
                }
                temp_entry.close();
                if(flag == 1) {
                    SQLStuff delete_entry = new SQLStuff(FormActivity.this);
                    delete_entry.delete(idData);
                    delete_entry.close();
                }
                finish();
            }
        });

    }
}
