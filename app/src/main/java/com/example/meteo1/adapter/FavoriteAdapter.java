package com.example.meteo1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.meteo1.R;
import com.example.meteo1.models.City;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> { // Constructor

    private Context mContext;
    private ArrayList<City> mCities;

    public FavoriteAdapter(Context context, ArrayList<City> cities) {
        mContext = context;
        mCities = cities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_favorite_city, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        City city = mCities.get(position);

        holder.mTextViewItemName.setText(city.mName);
        holder.mTextViewItemDesc.setText(city.mDescription);
        holder.mTextViewItemTemp.setText(city.mTemperature);
        holder.mImageViewItemIcon.setImageResource(city.mWeatherIcon);

        holder.mCity = city;
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    // Classe holder qui contient la vue d’un item
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewItemName;
        public TextView mTextViewItemTemp;
        public TextView mTextViewItemDesc;
        public ImageView mImageViewItemIcon;

        public City mCity;

        public ViewHolder(View view) {
            super(view);

            mTextViewItemName = view.findViewById(R.id.text_view_item_city_name);
            mTextViewItemTemp = view.findViewById(R.id.text_view_item_city_temp);
            mTextViewItemDesc = view.findViewById(R.id.text_view_item_city_desc);
            mImageViewItemIcon = view.findViewById(R.id.image_view_item_weather_icon);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Etes-vous sur de vouloir supprimer " + mCity.mName + " ?");

                    builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            mCities.remove(mCity);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Annuler", null);
                    builder.create().show();

                    return true;
                }
            });
        }
    }
}
