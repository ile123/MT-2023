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

        public TextView name;
        public TextView region;
        public TextView currency;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.countryName);
            region = itemView.findViewById(R.id.countryRegion);
            currency = itemView.findViewById(R.id.countryCurrency);
        }
    }

    private final List<Country> countries;

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
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Country country = countries.get(position);

        holder.name.setText(country.getName());
        holder.region.setText(country.getRegion());
        holder.currency.setText(country.getRegion());
    }
}
