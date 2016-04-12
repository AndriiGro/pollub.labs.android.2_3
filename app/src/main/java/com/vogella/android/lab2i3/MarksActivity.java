package com.vogella.android.lab2i3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.grondzal.lab2i3.helpers.CalculationHelper;
import com.grondzal.lab2i3.helpers.ModelOceny;
import com.grondzal.lab2i3.helpers.RadioListAdapter;
import com.grondzal.lab2i3.helpers.ValidationHelper;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity {

    private ListView marksList;
    private ArrayList<ModelOceny> marksDataList;
    private Integer marksCount;
    private String userName;
    private String userLastName;
    private Button doneButton;
    private RadioListAdapter adapter;

    private ValidationHelper validationHelper = new ValidationHelper();
    private CalculationHelper calcHelper = new CalculationHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        Intent baseIntent = getIntent();

        marksCount = Integer.parseInt(baseIntent.getStringExtra("marksCount"));
        userName = baseIntent.getStringExtra("userName");
        userLastName = baseIntent.getStringExtra("userLastName");

        if ( marksDataList == null || marksDataList.size() == 0) {
            marksDataList = new ArrayList<ModelOceny>();

            for (int iterator = 1; iterator <= marksCount; iterator++) {
                marksDataList.add(new ModelOceny("Ocena " + iterator + " "));
            }
        }

        adapter = new RadioListAdapter(this, marksDataList);
        marksList = (ListView) findViewById(R.id.marksList);
        marksList.setAdapter(adapter);

        doneButton = (Button) findViewById(R.id.buttonDone);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDone:
                if (validationHelper.checkIfAllMarksSet(marksDataList)) {
                    Intent intent =
                            new Intent(MarksActivity.this, ResultActivity.class);
                    intent.putExtra("marksCount", marksCount.toString());
                    intent.putExtra("userName", userName);
                    intent.putExtra("userLastName", userLastName);
                    intent.putExtra("averageMark",
                            String.valueOf(calcHelper.calculateAverageMark(marksDataList)));
                    MarksActivity.this.startActivity(intent);
                } else {
                    doneButton.setError("Wszystkie oceny powinny być zaznaczone");
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("marksDataList", marksDataList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        marksDataList = savedInstanceState.getParcelableArrayList("marksDataList");
        adapter.clear();
        adapter.addAll(marksDataList);
        adapter.notifyDataSetChanged();
    }
}
