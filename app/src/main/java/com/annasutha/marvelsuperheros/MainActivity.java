package com.annasutha.marvelsuperheros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Adapter.SuperHeroAdapter;
import DAO.SuperHeroDao;
import Entity.SuperHeroEntity;
import Repository.SuperHeroRepository;
import TrackRoom.TrackRoomDatabase;
import ViewModel.SuperHeroViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewUsers;
    private SuperHeroAdapter superHeroAdapter;
    private SuperHeroDao superHeroDao;
    private LinearLayout rootLinearLayout;
    private ArrayList<SuperHeroEntity> superHeroEntityList;
    private SuperHeroViewModel superHeroViewModel;
    private SuperHeroRepository superHeroRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if (savedInstanceState != null) {
            //gameState = savedInstanceState.getString(GAME_STATE_KEY);
        }*/
        superHeroViewModel = ViewModelProviders.of(this).get(SuperHeroViewModel.class);
        TrackRoomDatabase db = TrackRoomDatabase.getDatabase(getApplicationContext());
        superHeroDao = db.superHeroDao();
        rootLinearLayout = findViewById(R.id.layout);
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Details");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF6200EE"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        superHeroEntityList = new ArrayList<>();
        superHeroAdapter = new SuperHeroAdapter(superHeroEntityList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        getDataFromServer();
        //initViews();
        //initObjects();
    }
   /* private void initViews() {
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
    }*/
    /*private void initObjects() {
        superHeroEntityList = new ArrayList<>();
        superHeroAdapter = new SuperHeroAdapter(superHeroEntityList,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        getDataFromServer();
    }*/

    public void getDataFromServer() {

        superHeroViewModel.fetchList().observe(this, new Observer<List<SuperHeroEntity>>() {
            @Override
            public void onChanged(List<SuperHeroEntity> superHeroEntities) {
                if (superHeroEntities.size() != 0) {
                    superHeroEntityList.clear();
                    for (int i = 0; i < superHeroEntities.size(); i++) {
                        superHeroEntityList.add(superHeroEntities.get(i));
                        superHeroAdapter = new SuperHeroAdapter(superHeroEntityList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerViewUsers.setLayoutManager(mLayoutManager);
                        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
                        recyclerViewUsers.setAdapter(superHeroAdapter);
                        superHeroAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }
}