## EditCurrency - an EditText extension for Android

#### About

EditCurrency is an extension of the EditText view, used in the development of Android applications.  It was designed to make entering currency values into a touch-screen mobile device easier on both the user and the developer.  An EditCurrency view has its input type set to numeric by default, and automatically updates the displayed number with NumberFormat currency formatting.  The user simply has to type in digits to enter a currency value, or press the delete key to go back a step if a mistake is made.  The developer simply has to add an EditCurrency view to their chosen layout xml file, and call the `getTextAsDouble()` method to retrieve the current currency value as a double.

#### How to Use EditCurrency

1. Copy the EditCurrency.java and CurrencyWatcher.java files from this repository into your existing Android project's java class directory.  It's usually in app/src/main/java/com/something/something (the same directory your MainActivity.java file is in).
2. Edit the first line of each of these files to match your package address (com.something.something).
3. Add the following block of code as a view in one of your layout xml files:
```
<com.something.something.EditCurrency
        android:id="@+id/edit_currency"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="$0.00" />
```
Be sure to change the `com.something.something` part to match your package address!  You can also feel free to change any of the attributes that you can normally change with EditText, though it is recommended that you do **not** change the value of the `android:inputType` attribute.

That's it!  Now you can access the current double value of the EditCurrency view (within an OnClickListener, for example) by binding the view to a variable and calling the `getTextAsDouble()` method, like so:
```
EditCurrency editCurrency = (EditCurrency) findViewById(R.id.edit_currency);
double value = editCurrency.getTextAsDouble();
```

#### License

This code is licensed for use under the [GNU General Public License, version 3 (GPL-3.0)](https://opensource.org/licenses/GPL-3.0).
