package com.androidapp.testapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidapp.testapp.model.Contact;
import com.androidapp.testapp.model.Model;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
   List<Contact> contactList;
   Context context;

    public CustomAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.main_list_item,viewGroup,false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {

        Contact contact = contactList.get(i);
        customViewHolder.name.setText(contact.getName());
        customViewHolder.email.setText(contact.getEmail());
        customViewHolder.home.setText(contact.getPhone().getHome());
        customViewHolder.mobile.setText(contact.getPhone().getMobile());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;
        TextView home;
        TextView mobile;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            home = itemView.findViewById(R.id.home);
            mobile = itemView.findViewById(R.id.mobile);
        }
    }

    public void updatList(List<Contact> newList){
        contactList = new ArrayList<>();
        contactList.addAll(newList);
        notifyDataSetChanged();
    }
}
