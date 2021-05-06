package com.example.meteo1.avtivities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.meteo1.R;
import com.example.meteo1.adapter.FavoriteAdapter;
import com.example.meteo1.models.City;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private ArrayList<City> mCities;

    private RecyclerView mRecyclerView;
    private FavoriteAdapter mFavoriteAdapter;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mContext = this;

        Log.d("lol", " FavoriteActivity -------> onCreate()");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Ajouter une ville");

                View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_add_favorite, null);
                final EditText editText = v.findViewById(R.id.edit_text_dialog_add_favorite);

                builder.setView(v);

                builder.setPositiveButton("Ajouter",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String cityName = editText.getText().toString();
                        City newCity = new City(cityName, "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
                        mCities.add(newCity);
                        mFavoriteAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Annuler", null);
                builder.create().show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCities = new ArrayList<>();

        City city1 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city2 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city3 = new City("Paris", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city4 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);

        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mFavoriteAdapter = new FavoriteAdapter(this, mCities);

        mRecyclerView.setAdapter(mFavoriteAdapter);
    }

}
