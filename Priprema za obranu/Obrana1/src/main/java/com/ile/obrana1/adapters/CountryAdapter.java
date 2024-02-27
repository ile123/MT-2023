package com.ile.obrana1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ile.obrana1.R;
import com.ile.obrana1.entities.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView countryName;
        public  TextView countryRegion;
        public  TextView countryCurrency;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.countryName);
            countryRegion = itemView.findViewById(R.id.countryRegion);
            countryCurrency = itemView.findViewById(R.id.countryCurrency);
        }
    }

    private List<Country> countries;

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Country country = countries.get(position);

        holder.countryName.setText(country.getName());
        holder.countryRegion.setText(country.getRegion());
        holder.countryCurrency.setText(country.getCurrency());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
