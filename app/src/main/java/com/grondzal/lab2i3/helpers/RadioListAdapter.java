package com.grondzal.lab2i3.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vogella.android.lab2i3.R;

import java.util.List;

/**
 * Created by Andrii on 05.04.2016.
 */
public class RadioListAdapter extends ArrayAdapter<ModelOceny> {

    private Context context;
    private List<ModelOceny> list;

    public RadioListAdapter(Context context, List<ModelOceny> objects) {
        super(context, R.layout.radio_list_row, objects);
        this.context = context;
        this.list = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.radio_list_row, null);
        }

        final RadioGroup radioGroup = (RadioGroup) convertView.findViewById(R.id.radioGroupRow);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ModelOceny modelOceny = list.get(position);
                switch (checkedId) {
                    case R.id.radioButton2:
                        //radioGroup.check(R.id.radioButton2);
                        modelOceny.setOcena(2);
                        list.set(position, modelOceny);
                        break;
                    case R.id.radioButton3:
                        //radioGroup.check(R.id.radioButton3);
                        modelOceny.setOcena(3);
                        list.set(position, modelOceny);
                        break;
                    case R.id.radioButton4:
                        //radioGroup.check(R.id.radioButton4);
                        modelOceny.setOcena(4);
                        list.set(position, modelOceny);
                        break;
                    case R.id.radioButton5:
                        //radioGroup.check(R.id.radioButton5);
                        modelOceny.setOcena(5);
                        list.set(position, modelOceny);
                        break;
                }
            }
        });

        switch (list.get(position).getOcena()){
            case 2:
                radioGroup.check(R.id.radioButton2);
                break;
            case 3:
                radioGroup.check(R.id.radioButton3);
                break;
            case 4:
                radioGroup.check(R.id.radioButton4);
                break;
            case 5:
                radioGroup.check(R.id.radioButton5);
                break;
            default:
                radioGroup.clearCheck();
                break;
        }

        TextView textView = (TextView) convertView.findViewById(R.id.radioGroupTextView);
        textView.setText(list.get(position).getNazwaOceny());

        return convertView;
    }
}
