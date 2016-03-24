package com.your.package;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditCurrency extends EditText {

    public EditCurrency(Context context) {
        super(context);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        addTextChangedListener(new CurrencyWatcher(this));
    }

    public EditCurrency(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        addTextChangedListener(new CurrencyWatcher(this));
    }

    public EditCurrency(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        addTextChangedListener(new CurrencyWatcher(this));
    }

    public double getTextAsDouble() throws NumberFormatException {
        String s = getText().toString().replaceAll("[$,]", "");
        return Double.parseDouble(s);
    }

}
