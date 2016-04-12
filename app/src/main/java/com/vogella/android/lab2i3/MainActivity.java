package com.vogella.android.lab2i3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText lastnameEditText;
    private EditText markscountEditText;
    private Button marksButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText) findViewById(R.id.editText_name);
        lastnameEditText = (EditText) findViewById(R.id.editText_lastname);
        markscountEditText = (EditText) findViewById(R.id.editText_marksCount);
        marksButton = (Button) findViewById(R.id.button_oceny);

        markscountEditText.addTextChangedListener(rangeWatcher);

        nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(nameEditText.getText().toString())) {
                        nameEditText.setError("Pole nie może być puste");
                    }
                }
            }
        });

        lastnameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(lastnameEditText.getText().toString())) {
                        lastnameEditText.setError("Pole nie może być puste");
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_oceny :
                if(markscountEditText.getText().toString().equals("")
                        || nameEditText.getText().toString().equals("")
                        || lastnameEditText.getText().toString().equals(""))
                {
                    markscountEditText.setError("Liczba ocen powinna być w zakresie 5-15");
                    break;
                }
                if(Integer.parseInt(markscountEditText.getText().toString())<5){
                    markscountEditText.setText("");
                    break;
                }
                Intent intent = new Intent(MainActivity.this, MarksActivity.class);
                intent.putExtra("marksCount", markscountEditText.getText().toString());
                intent.putExtra("userName", nameEditText.getText().toString());
                intent.putExtra("userLastName", lastnameEditText.getText().toString());
                MainActivity.this.startActivity(intent);
                break;
        }
    }

    private final TextWatcher rangeWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            markscountEditText.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            try {
                int val = Integer.parseInt(s.toString());
                if (val > 15) {
                    s.replace(0, s.length(), "");
                    markscountEditText.setError("Liczba ocen powinna być w zakresie 5-15");
                } else if (val < 5) {
                    markscountEditText.setError("Liczba ocen powinna być w zakresie 5-15");
                }
            } catch (NumberFormatException ex) {
            }

            if (TextUtils.isEmpty(nameEditText.getText().toString()) |
                    TextUtils.isEmpty(lastnameEditText.getText().toString()) |
                    TextUtils.isEmpty(markscountEditText.getText().toString())) {
                marksButton.setVisibility(View.GONE);
            }
            else {
                marksButton.setVisibility(View.VISIBLE);
            }
        }
    };
}
