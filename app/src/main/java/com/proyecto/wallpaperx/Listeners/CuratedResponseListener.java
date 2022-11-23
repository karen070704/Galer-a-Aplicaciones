package com.proyecto.wallpaperx.Listeners;

import com.proyecto.wallpaperx.Models.CuratedApiResponse;

public interface CuratedResponseListener {
    void onFetch(CuratedApiResponse response, String message);
    void onError(String message);
}
