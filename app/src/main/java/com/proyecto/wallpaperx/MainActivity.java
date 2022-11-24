package com.proyecto.wallpaperx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.proyecto.wallpaperx.Adapters.CuratedAdapter;
import com.proyecto.wallpaperx.Listeners.CuratedResponseListener;
import com.proyecto.wallpaperx.Listeners.OnRecyclerClickListener;
import com.proyecto.wallpaperx.Models.CuratedApiResponse;
import com.proyecto.wallpaperx.Models.Photo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRecyclerClickListener {

    RecyclerView recyclerView_home;
    CuratedAdapter adapter;
    ProgressDialog dialog;
    RequestManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("CARGANDO...");

        manager = new RequestManager(this);
        manager.getCuratedWallpapers(listener, "1");
    }

    private final CuratedResponseListener listener = new CuratedResponseListener() {
        @Override
        public void onFetch(CuratedApiResponse response, String message) {
           if (response.getPhotos().isEmpty()){
               Toast.makeText(MainActivity.this, "NO SE ENCONTRÓ NINGUNA IMAGEN!!", Toast.LENGTH_SHORT).show();
               return;
           }
           showData(response.getPhotos());
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(List<Photo> photos) {
        recyclerView_home = findViewById(R.id.recycler_home);
        recyclerView_home.setHasFixedSize(true);
        recyclerView_home.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CuratedAdapter(MainActivity.this, photos, this);
        recyclerView_home.setAdapter(adapter);


    }

    @Override
    public void onClick(Photo photo) {
        Toast.makeText(MainActivity.this, photo.getPhotographer(), Toast.LENGTH_SHORT).show();


    }
}