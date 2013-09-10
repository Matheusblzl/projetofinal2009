package com.projetoboaviagem.components;

import java.text.ParseException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.projetoboaviagem.util.GlobalUtil;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int componentId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();

        try {
            if (getActivity().findViewById(componentId) instanceof Button) {
                Button button = (Button) getActivity().findViewById(componentId);
                calendar.setTime(GlobalUtil.getInstance().parseStringInDateMedio(button.getText().toString()));
            } else if (getActivity().findViewById(componentId) instanceof EditText) {
                EditText editText = (EditText) getActivity().findViewById(componentId);
                calendar.setTime(GlobalUtil.getInstance().parseStringInDateMedio(editText.getText().toString()));
            }
        } catch (ParseException e) {
            calendar = Calendar.getInstance();
        }

        return new DatePickerDialog(getActivity(), this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = GlobalUtil.getInstance().converterEmCalendar(dayOfMonth, monthOfYear, year);
        String date = GlobalUtil.getInstance().formatarDataMedio(calendar.getTime());
        if (getActivity().findViewById(componentId) instanceof Button) {
            Button button = (Button) getActivity().findViewById(componentId);
            button.setText(date);
        } else if (getActivity().findViewById(componentId) instanceof EditText) {
            EditText editText = (EditText) getActivity().findViewById(componentId);
            editText.setText(date);
        }
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

}
