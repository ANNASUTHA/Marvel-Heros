package Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.annasutha.marvelsuperheros.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Entity.SuperHeroEntity;

public class SuperHeroAdapter extends RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder> {
    private final List<SuperHeroEntity> superHeroEntities;
    Context context;
    public SuperHeroAdapter(List<SuperHeroEntity> superHeroEntities, Context context) {
        this.superHeroEntities = superHeroEntities;
        this.context = context;
    }

    @Override
    public SuperHeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hero_recycler, parent, false);
        return new SuperHeroViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(SuperHeroViewHolder holder, int position) {
        /*Picasso
                .with(context)
                .load(your image source here)
                .resize(600, 200) // resizes the image to these dimensions (in pixel)
                .centerInside()
                .into(imageViewResizeCenterCrop);
        Picasso.with(this).load("www.journaldev.com").error(R.mipmap.ic_launcher).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "onSuccess");
            }

            @Override
            public void onError() {
                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.imageView.setImageURI(Uri.parse(superHeroEntities.get(position).getImageUrl()));
        //holder.imageView.setImageURI(superHeroEntities.get(position).getImageUrl());
        holder.name.setText(superHeroEntities.get(position).getName());
        holder.realName.setText(superHeroEntities.get(position).getRealName());
        holder.team.setText(superHeroEntities.get(position).getTeam());
        holder.firstAppearance.setText(superHeroEntities.get(position).getFirstAppearance());
        holder.createdBy.setText(superHeroEntities.get(position).getCreatedBy());
        holder.publisher.setText(superHeroEntities.get(position).getPublisher());
        holder.bio.setText(superHeroEntities.get(position).getBio());
    }

    @Override
    public int getItemCount() {
        Log.v(SuperHeroAdapter.class.getSimpleName(), "" + superHeroEntities.size());
        return superHeroEntities.size();
    }

    public class SuperHeroViewHolder extends RecyclerView.ViewHolder{
        public TextView realName,name,team,firstAppearance,createdBy,publisher,bio;
        private ImageView imageView;

        public SuperHeroViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            realName = itemView.findViewById(R.id.realname);
            name = itemView.findViewById(R.id.name);
            team = itemView.findViewById(R.id.team);
            firstAppearance = itemView.findViewById(R.id.firstappearance);
            /*createdBy = itemView.findViewById(R.id.createdby);
            publisher = itemView.findViewById(R.id.publisher);
            bio = itemView.findViewById(R.id.bio);*/
        }
    }
}
