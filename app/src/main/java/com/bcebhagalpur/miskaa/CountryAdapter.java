package com.bcebhagalpur.miskaa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private ArrayList<CountryModel> listdata;
    private Context context;

    // RecyclerView recyclerView;
    public CountryAdapter(ArrayList<CountryModel> listdata, Context context) {
        this.listdata = listdata;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.country_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CountryModel myListData = listdata.get(position);
//        Picasso.get().load(myListData.getFlag()).fit().into(holder.image);
       holder.countryName.setText(myListData.getName());
        holder.countryCapital.setText(myListData.getCapital());
        holder.region.setText(myListData.getRegion()+" "+"( ");
        holder.subRegion.setText(myListData.getSubRegion()+" )");
        Utils.fetchSvg(context, myListData.getFlag(), holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("name",myListData.getName());
                intent.putExtra("capital",myListData.getCapital());
                intent.putExtra("region",myListData.getRegion());
                intent.putExtra("subRegion",myListData.getSubRegion());
                intent.putExtra("flag",myListData.getFlag());
                intent.putExtra("population",myListData.getPopulation());
                intent.putExtra("borders",myListData.getBorders());
                intent.putExtra("language",myListData.getLanguages());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView countryName,countryCapital,region,subRegion;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.countryName = (TextView) itemView.findViewById(R.id.countryName);
            countryCapital=itemView.findViewById(R.id.capitalName);
            region = itemView.findViewById(R.id.region);
            subRegion = itemView.findViewById(R.id.subRegion);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}