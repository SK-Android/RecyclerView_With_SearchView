package com.androidapp.testapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.androidapp.testapp.model.Contact;
import com.androidapp.testapp.model.Model;
import com.androidapp.testapp.service.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    List<Contact> contactList;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ApiInterface apiInterface = mainViewModel.getApi();
        getData(apiInterface);

        recyclerView = findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void getData(ApiInterface apiInterface) {

        Call<Model> call = apiInterface.getContactList();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                    for(int i=0;i<response.body().getContacts().size();i++){
                       String name = response.body().getContacts().get(i).getName();
                       String email = response.body().getContacts().get(i).getEmail();
                        String home = response.body().getContacts().get(i).getPhone().getHome();
                        String mobile = response.body().getContacts().get(i).getPhone().getMobile();

                        contactList = response.body().getContacts();

                        Log.i("MainActivity","\n"+contactList.size());

//                        String PersonContact = "Name:"+name+"\n\n"
//                                +"Email:"+email+"\n\n"+
//                                "Home:"+home+"\n\n" +
//                                "Mobile:"+mobile+"\n\n";
//                        Log.i("MainActivity","\n"+PersonContact);
                        customAdapter = new CustomAdapter(contactList,getApplicationContext());
                        recyclerView.setAdapter(customAdapter);


                    }

                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

                Log.e("MainActivity",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menu1 = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menu1.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userInput = s.toLowerCase();
        List<Contact> newList = new ArrayList<>();
        for(Contact contact:contactList){
            if(contact.getName().toLowerCase().contains(userInput)){
                newList.add(contact);
            }
        }
        customAdapter.updatList(newList);
        return false;
    }
}
