package com.vogella.android.lab2i3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private String averageMarkString;
    private double averageMarkDouble;
    private EditText nameEditText;
    private EditText lastnameEditText;
    private EditText markscountEditText;
    private Button messageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent baseIntent = getIntent();

        averageMarkDouble = Double.parseDouble(baseIntent.getStringExtra("averageMark"));
        averageMarkString = String.format("%.2f", averageMarkDouble);

        nameEditText = (EditText) findViewById(R.id.editText_name);
        lastnameEditText = (EditText) findViewById(R.id.editText_lastname);
        markscountEditText = (EditText) findViewById(R.id.editText_marksCount);
        messageButton = (Button) findViewById(R.id.button_message);

        nameEditText.setText(baseIntent.getStringExtra("userName"));
        lastnameEditText.setText(baseIntent.getStringExtra("userLastName"));
        markscountEditText.setText(baseIntent.getStringExtra("marksCount"));

        TextView textView = (TextView) findViewById(R.id.textView_Result);
        textView.setText("Twoja średnia to " + averageMarkString);

        if (averageMarkDouble < 3.0) {
            messageButton.setText("Tym razem mi nie poszło");
        } else {
            messageButton.setText("Super :)");
        }
    }

    public void onClick(View view) {
        String toastMessage = "";
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        switch (view.getId()) {
            case R.id.button_message:
                if (averageMarkDouble < 3.0) {
                    toastMessage = "Wysyłam podanie o zaliczenie warunkowe";
                } else {
                    toastMessage = "Gratulacje! Otrzymujesz zaliczenie!";
                }
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(toastMessage)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
