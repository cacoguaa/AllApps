package com.business.cacoguaa.business;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.business.cacoguaa.business.DB.BusinessOperations;
import com.business.cacoguaa.business.Models.Business;

import java.util.List;

public class ViewAllBusinesses extends ListActivity {

    private BusinessOperations businessOps;
    List<Business> businesses;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_businesses);
        businessOps = new BusinessOperations(this);
        businessOps.open();
        businesses = businessOps.getAllBusiness();
        businessOps.close();

        searchView=(SearchView) findViewById(R.id.search_view);
        searchView.setQueryHint("Search View");
        ArrayAdapter<Business> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, businesses);
        setListAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                filterBusinesses(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }
    public void filterBusinesses(String name){
        businessOps.open();
        businesses = businessOps.getBusiness(name);
        ArrayAdapter<Business> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, businesses);
        setListAdapter(adapter);
        businessOps.close();
    }
}
