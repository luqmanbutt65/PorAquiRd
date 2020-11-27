package com.example.realestate.Model;

import android.net.Uri;

public class ImagesData {


    private Uri uri;

    public ImagesData(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
