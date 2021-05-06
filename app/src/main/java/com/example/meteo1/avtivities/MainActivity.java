package com.example.meteo1.avtivities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meteo1.R;
import com.example.meteo1.utils.Util;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewCityName;
    private TextView mTextViewNoConnexion;
    private EditText mEditTextMessage;
    private Button mButton2;

    private LinearLayout mLinearLayoutCurrentCity;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        Log.d("lol", " MainActivity -------> onCreate()");



        // Initialisation des vues
        mTextViewCityName = findViewById(R.id.text_view_city_name);
        mTextViewNoConnexion = findViewById(R.id.text_view_no_connexion);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        mLinearLayoutCurrentCity = findViewById(R.id.linear_layout_current_city);





        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("lol", "Oui je suis connecté à internet");
            mLinearLayoutCurrentCity.setVisibility(View.VISIBLE);
            mTextViewNoConnexion.setVisibility(View.INVISIBLE);
        } else {
            Log.d("lol", "Non je ne suis pas connecté à internet");
            mLinearLayoutCurrentCity.setVisibility(View.INVISIBLE);
            mTextViewNoConnexion.setVisibility(View.VISIBLE);
        }
    }

    public void onClickButtonFavorite(View view) {
        EditText editText = findViewById(R.id.edit_text_message);

        Intent intent = new Intent(mContext, FavoriteActivity.class);
        String message = mEditTextMessage.getText().toString();
        intent.putExtra(Util.KEY_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lol", " MainActivity -------> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lol", " MainActivity -------> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lol", " MainActivity -------> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lol", " MainActivity -------> onStop()");
    }

    @Override
    protected void onDestroy() {
        Log.d("lol", " MainActivity -------> onDestroy()");
        super.onDestroy();
    }
}

