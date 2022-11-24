package com.proyecto.wallpaperx.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.wallpaperx.Listeners.OnRecyclerClickListener;
import com.proyecto.wallpaperx.MainActivity;
import com.proyecto.wallpaperx.Models.Photo;
import com.proyecto.wallpaperx.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CuratedAdapter extends RecyclerView.Adapter<CuratedViewHolmer>{

    Context context;
    List<Photo> List;
    OnRecyclerClickListener Listener;

    public CuratedAdapter(Context context, java.util.List<Photo> photos, MainActivity mainActivity) {
        this.context = context;
    }

    public CuratedAdapter(java.util.List<Photo> list) {
        List = list;
    }

    public CuratedAdapter(OnRecyclerClickListener listener) {
        Listener = listener;
    }

    @NonNull
    @Override
    public CuratedViewHolmer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CuratedViewHolmer(LayoutInflater.from(context).inflate(R.layout.home_list, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull CuratedViewHolmer holder, int position) {
        Picasso.get().load(List.get(position).getUrl()).placeholder(R.drawable.placecholder).into(holder.imageView_List);
        holder.home_List_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.onClick(List.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}

class CuratedViewHolmer extends RecyclerView.ViewHolder {
    CardView home_List_container;
    ImageView imageView_List;

    public CuratedViewHolmer(@NonNull View itemView) {
        super(itemView);
        home_List_container = itemView.findViewById(R.id.home_List_container);
        imageView_List = itemView.findViewById(R.id.imageView_List);

    }
}
