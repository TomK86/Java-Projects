package com.tkelly.splitthebill;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.text.ParseException;

public class EvenSplitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_split);

        final TextView result_text = (TextView) findViewById(R.id.result_text);
        final EditCurrency total_edit = (EditCurrency) findViewById(R.id.total_edit);
        final NumberPicker payers_edit = (NumberPicker) findViewById(R.id.payers_edit);
        payers_edit.setMinValue(1);
        payers_edit.setMaxValue(100);

        Button submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double total = total_edit.getTextAsDouble();
                    int payers = payers_edit.getValue();
                    String result = "Each person owes " +
                            NumberFormat.getCurrencyInstance().format(total / payers);
                    result_text.setText(result);
                } catch (ParseException e) {
                    makeToast(R.string.error_currency_format);
                }
            }
        });
    }

    protected void makeToast(int s) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(s),
                Toast.LENGTH_SHORT).show();
    }

}
