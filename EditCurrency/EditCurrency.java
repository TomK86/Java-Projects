package com.your.package;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import java.text.NumberFormat;
import java.text.ParseException;

public class EditCurrency extends EditText {

    public EditCurrency(Context context) {
        super(context);
        init();
    }

    public EditCurrency(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditCurrency(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public double getTextAsDouble() throws ParseException {
        String s = getText().toString();
        return NumberFormat.getCurrencyInstance().parse(s).doubleValue();
    }
    
    private void init() {
        String zero_currency = NumberFormat.getCurrencyInstance().format(0);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setText(zero_currency);
        setSelection(zero_currency.length());

        addTextChangedListener(new CurrencyWatcher(this));

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection(getText().length());
            }
        });
    }

}
