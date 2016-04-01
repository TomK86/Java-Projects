package com.tkelly.splitthebill;

import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PayerListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payer_list);

        final PayerListAdapter adapter = new PayerListAdapter(getApplicationContext(),
                R.layout.content_payer_row, ((MyApplication) getApplication()).getPayers());
        ListView payer_list = (ListView) findViewById(android.R.id.list);
        payer_list.setAdapter(adapter);

        payer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int idx = position;
                final Dialog dialog = new Dialog(PayerListActivity.this);
                dialog.setContentView(R.layout.dialog_confirm);
                dialog.setTitle("Remove a party member");
                dialog.setCancelable(true);

                TextView query_text = (TextView) dialog.findViewById(R.id.query_text);
                query_text.setText(R.string.confirm_remove_payer);

                Button yes_btn = (Button) dialog.findViewById(R.id.yes_btn);
                yes_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.remove(adapter.getItem(idx));
                        dialog.dismiss();
                    }
                });

                Button no_btn = (Button) dialog.findViewById(R.id.no_btn);
                no_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(PayerListActivity.this);
                dialog.setContentView(R.layout.dialog_add_payer);
                dialog.setTitle("Add a new payer");
                dialog.setCancelable(true);

                final EditText name_edit = (EditText) dialog.findViewById(R.id.name_edit);

                Button submit_btn = (Button) dialog.findViewById(R.id.submit_btn);
                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = name_edit.getText().toString();
                        if (name.isEmpty()) {
                            makeToast(R.string.error_add_payer_name_empty);
                        } else {
                            adapter.add(new Payer(name));
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    protected void makeToast(int s) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(s),
                Toast.LENGTH_SHORT).show();
    }

}
