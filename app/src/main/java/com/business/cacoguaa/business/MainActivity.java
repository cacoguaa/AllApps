package com.business.cacoguaa.business;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.business.cacoguaa.business.DB.BusinessOperations;
import com.business.cacoguaa.business.Models.Business;

public class MainActivity extends AppCompatActivity {

    private Button addBusinessButton;
    private Button editBusinessButton;
    private Button deleteBusinessButton;
    private Button viewAllBusinessButton;
    private BusinessOperations businessOps;
    private static final String EXTRA_EMP_ID = "com.cacoguaa.Id";
    private static final String EXTRA_ADD_UPDATE = "com.cacoguaa.add_update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addBusinessButton = (Button) findViewById(R.id.button_add_business);
        editBusinessButton = (Button) findViewById(R.id.button_edit_business);
        deleteBusinessButton = (Button) findViewById(R.id.button_delete_business);
        viewAllBusinessButton = (Button)findViewById(R.id.button_view_businesses);
        businessOps = new BusinessOperations(MainActivity.this);
        addBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddUpdateBusiness.class);
                i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });
        editBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIdAndUpdateBus();
            }
        });
        deleteBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIdAndRemoveBus();
            }
        });
        viewAllBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewAllBusinesses.class);
                startActivity(i);
            }
        });
    }

    public void getIdAndUpdateBus(){

        LayoutInflater li = LayoutInflater.from(this);
        View getBusIdView = li.inflate(R.layout.dialog_get_bus_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_emp_id.xml to alertdialog builder
        alertDialogBuilder.setView(getBusIdView);

        final EditText userInput = (EditText) getBusIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        if(!userInput.getText().toString().equals("")) {
                            Intent i = new Intent(MainActivity.this, AddUpdateBusiness.class);
                            i.putExtra(EXTRA_ADD_UPDATE, "Update");
                            i.putExtra(EXTRA_EMP_ID, Long.parseLong(userInput.getText().toString()));
                            startActivity(i);
                        }
                    }
                }).create()
                .show();
    }

    public void getIdAndRemoveBus(){

        LayoutInflater li = LayoutInflater.from(this);
        View getBusIdView = li.inflate(R.layout.dialog_get_bus_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_emp_id.xml to alertdialog builder
        alertDialogBuilder.setView(getBusIdView);

        final EditText userInput = (EditText) getBusIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        if(!userInput.getText().toString().equals("")) {
                            final AlertDialog alert;
                            final Business bus = businessOps.getBusiness(Long.parseLong(userInput.getText().toString()));
                            AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
                            dialog2.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    if(!userInput.getText().toString().equals("")) {
                                        businessOps = new BusinessOperations(MainActivity.this);
                                        businessOps.removeBusiness(businessOps.getBusiness(Long.parseLong(userInput.getText().toString())));
                                        Toast t = Toast.makeText(MainActivity.this, "Empresa borrada exitosamente!", Toast.LENGTH_SHORT);
                                        t.show();
                                    }
                                }
                            });
                            dialog2.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {

                                }
                            });
                            alert = dialog2.create();
                            alert.setTitle("Delete " + bus.getName()     + "?");
                            alert.setMessage("Estas seguro de borrar esta empresa?");
                            alert.show();
                        }
                    }
                }).create()
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        businessOps.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        businessOps.close();
    }
}
