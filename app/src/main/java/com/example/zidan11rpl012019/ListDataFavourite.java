package com.example.zidan11rpl012019;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

    public class ListDataFavourite extends AppCompatActivity {
        Realm realm;
        RealmHelper realmHelper;
        private RecyclerView recyclerView;
        private DataAdapterFavourite adapter;
        private List<ModelMovieRealm> DataArrayList; //kit add kan ke adapter


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_data);
            recyclerView = (RecyclerView) findViewById(R.id.ListDT);
            DataArrayList = new ArrayList<>();
            // Setup Realm
            RealmConfiguration configuration = new RealmConfiguration.Builder().build();
            realm = Realm.getInstance(configuration);
            realmHelper = new RealmHelper(realm);
            DataArrayList = realmHelper.getAllMahasiswa();
            adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
                @Override
                public void onClick(int position) {
                    //intent ke detail movie
                    Intent move = new Intent(getApplicationContext(), DetailFavourite.class);
                    move.putExtra("judul",DataArrayList.get(position).getJudul());
                    move.putExtra("path",DataArrayList.get(position).getPath());
                    move.putExtra("date",DataArrayList.get(position).getReleaseDate());
                    move.putExtra("deskripsi",DataArrayList.get(position).getDesc());
                    startActivity(move);
                }

                @Override
                public void test() {

                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavourite.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }
    }