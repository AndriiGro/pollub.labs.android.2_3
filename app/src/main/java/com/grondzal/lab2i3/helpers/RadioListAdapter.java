package com.grondzal.lab2i3.helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vogella.android.lab2i3.R;

import java.util.ArrayList;

/**
 * Created by Andrii on 05.04.2016.
 */
public class RadioListAdapter extends ArrayAdapter<ModelOceny> {

    private static final String TAG = "RadioListAdapter";
    private Context context;
    private ArrayList<ModelOceny> list;

    public RadioListAdapter(Context context, ArrayList<ModelOceny> objects) {
        super(context, R.layout.radio_list_row, objects);

        this.context = context;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v(TAG, "position=" + position);

        ViewHolder viewHolder;

        View row = convertView;

        if (row == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();

            row = layoutInflater.inflate(R.layout.radio_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.markName = (TextView) row.findViewById(R.id.radioGroupTextView);
            viewHolder.radioGroup = (RadioGroup) row.findViewById(R.id.radioGroupRow);

            viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int position = (int) group.getTag();
                    ModelOceny modelOceny = list.get(position);
                    switch (checkedId) {
                        case R.id.radioButton2:
                            modelOceny.setOcena(2);
                            break;
                        case R.id.radioButton3:
                            modelOceny.setOcena(3);
                            break;
                        case R.id.radioButton4:
                            modelOceny.setOcena(4);
                            break;
                        case R.id.radioButton5:
                            modelOceny.setOcena(5);
                            break;
                        default:
                            modelOceny.setOcena(-1);
                            break;
                    }
                }
            });

            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        ModelOceny model = list.get(position);
        viewHolder.markName.setText(model.getNazwaOceny());
        viewHolder.radioGroup.setTag(position);

        Integer tempOcenaAtPosition = list.get(position).getOcena();
        switch (tempOcenaAtPosition) {
            case 2:
                viewHolder.radioGroup.check(R.id.radioButton2);
                break;
            case 3:
                viewHolder.radioGroup.check(R.id.radioButton3);
                break;
            case 4:
                viewHolder.radioGroup.check(R.id.radioButton4);
                break;
            case 5:
                viewHolder.radioGroup.check(R.id.radioButton5);
                break;
            default:
                viewHolder.radioGroup.clearCheck();
                break;
        }

        return row;
    }

    private static class ViewHolder {
        TextView markName;
        RadioGroup radioGroup;
    }
}
