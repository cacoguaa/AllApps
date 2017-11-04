package com.business.cacoguaa.business;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.business.cacoguaa.business.DB.BusinessOperations;
import com.business.cacoguaa.business.Models.Business;

public class AddUpdateBusiness extends AppCompatActivity {

    private static final String EXTRA_EMP_ID = "com.cacoguaa.Id";
    private static final String EXTRA_ADD_UPDATE = "com.cacoguaa.add_update";

    private RadioGroup radioGroup;
    private RadioButton consultRadioButton, developmentRadioButton, fabricRadioButton;
    private EditText nameEditText;
    private EditText urlEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText descriptionEditText;
    private Button addUpdateButton;
    private Business newBusiness;
    private Business oldBusiness;
    private String mode;
    private long id;
    private BusinessOperations businessData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_business);
        newBusiness = new Business();
        oldBusiness = new Business();
        nameEditText = (EditText)findViewById(R.id.edit_text_name);
        urlEditText = (EditText)findViewById(R.id.edit_text_url);
        phoneEditText = (EditText) findViewById(R.id.edit_text_phone);
        emailEditText = (EditText)findViewById(R.id.edit_text_email);
        descriptionEditText = (EditText)findViewById(R.id.edit_text_description);

        radioGroup = (RadioGroup) findViewById(R.id.radio_type);
        consultRadioButton = (RadioButton) findViewById(R.id.radio_consult);
        developmentRadioButton = (RadioButton) findViewById(R.id.radio_development);
        fabricRadioButton = (RadioButton) findViewById(R.id.radio_fabric);

        addUpdateButton = (Button)findViewById(R.id.button_add_update_business);
        businessData = new BusinessOperations(this);
        businessData.open();


        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);
        if(mode.equals("Update")){
            addUpdateButton.setText("Update Business");
            id = getIntent().getLongExtra(EXTRA_EMP_ID,0);
            initializeBusiness(id);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radio_consult) {
                    newBusiness.setType("Consult");
                    if(mode.equals("Update")){
                        oldBusiness.setType("Consult");
                    }
                } else if (checkedId == R.id.radio_development) {
                    newBusiness.setType("Development");
                    if(mode.equals("Update")){
                        oldBusiness.setType("Development");
                    }
                } else if (checkedId == R.id.radio_fabric) {
                    newBusiness.setType("Fabric");
                    if(mode.equals("Update")){
                        oldBusiness.setType("Fabric");
                    }
                }
            }

        });

        addUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mode.equals("Add") ) {
                    newBusiness.setName(nameEditText.getText().toString());
                    newBusiness.setUrl(urlEditText.getText().toString());
                    newBusiness.setPhone(phoneEditText.getText().toString());
                    newBusiness.setEmail(emailEditText.getText().toString());
                    newBusiness.setDescription(descriptionEditText.getText().toString());
                    businessData.addBusiness(newBusiness);
                    Toast t = Toast.makeText(AddUpdateBusiness.this, "Employee "+ newBusiness.getName() + "has been added successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateBusiness.this,MainActivity.class);
                    startActivity(i);
                } else {
                    oldBusiness.setName(nameEditText.getText().toString());
                    oldBusiness.setUrl(urlEditText.getText().toString());
                    oldBusiness.setPhone(phoneEditText.getText().toString());
                    oldBusiness.setEmail(emailEditText.getText().toString());
                    oldBusiness.setDescription(descriptionEditText.getText().toString());
                    businessData.updateEmployee(oldBusiness);
                    Toast t = Toast.makeText(AddUpdateBusiness.this, "Employee "+ oldBusiness.getName() + " has been updated successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateBusiness.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void initializeBusiness(long id) {
        oldBusiness = businessData.getEmployee(id);

        nameEditText.setText(oldBusiness.getName());
        urlEditText.setText(oldBusiness.getUrl());
        phoneEditText.setText(oldBusiness.getPhone());
        emailEditText.setText(oldBusiness.getEmail());
        descriptionEditText.setText(oldBusiness.getDescription());
        String type = oldBusiness.getType();
        int i = 0;
        if( type.equals("Consult") ) i = R.id.radio_consult;
        else if ( type.equals("Development") ) i = R.id.radio_development;
        else i = R.id.radio_fabric;
        radioGroup.check(i);
    }

}
