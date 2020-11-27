package com.example.realestate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.realestate.Adapters.ImagesAdapter;
import com.example.realestate.Model.ImagesData;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Adddata extends AppCompatActivity {
    private static final int PICK_IMAGE_ONE = 0;
    private static final int PICK_IMAGE_MULTI = 2;
    Context context;
    Button addImage;
    ImageView featureImage;
    Spinner bedroomSpiner,bathsSpiner;
    EditText unit_of_measure,date_of_construction;
    RecyclerView recyclerView;
    ArrayList<ImagesData> imagesDataArrayList=new ArrayList<>();
        Uri imageuri;
    ImagesAdapter imagesAdapter;


    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        bedroomSpiner = findViewById(R.id.bedroom);
        bathsSpiner = findViewById(R.id.baths);
        unit_of_measure = findViewById(R.id.unitOfmeasure);
        date_of_construction = findViewById(R.id.dateofconstruction);


        list = new ArrayList<String> ();
        list.add("");
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");
        list.add("06");
        list.add("07");
        list.add("08");
        list.add("09");
        list.add("10");
        list.add("011");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedroomSpiner.setAdapter(arrayAdapter);
        bathsSpiner.setAdapter(arrayAdapter);


        bedroomSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), bedroomSpiner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bathsSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), bathsSpiner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        context = this;
        recyclerView = findViewById(R.id.images_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new ImagesAdapter(context, imagesDataArrayList));
        featureImage = findViewById(R.id.featureimageprorperty);
        addImage = findViewById(R.id.addimage);


        featureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                imagesDataArrayList.clear();
                imagesAdapter = new ImagesAdapter(Adddata.this, imagesDataArrayList);
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTI);

            }
        });


    }

    public void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_ONE);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//
//            if (requestCode == PICK_IMAGE_ONE) {
//
//
//                Log.e("++data", "" + data.getClipData().getItemCount());// Get count of image here.
//
//                Log.e("++count", "" + data.getClipData().getItemCount());
//
//                if (data.getClipData() != null) {
//
//                    int cout = data.getClipData().getItemCount();
//
//                    for (int i = 0; i < cout; i++) {
//
//                        Uri imageuris = data.getClipData().getItemAt(i).getUri();
//                        imagesUri.get(i).setUri(imageuris);
//
//                    }
//                    featureImage.setImageURI(imageUri);
//                } else {
//                    Uri ImageUri = data.getData();
//                    featureImage.setImageURI(ImageUri);
//
//                }
//
//            }
//        }
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {

            if (requestCode == PICK_IMAGE_MULTI) {

                Log.e("++data", "" + data.getClipData().getItemCount());// Get count of image here.

                Log.e("++count", "" + data.getClipData().getItemCount());

                if (data.getClipData().getItemCount() > 8) {
                    imagesAdapter.notifyDataSetChanged();
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.addimage), "You can not select more than 8 images", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent();
                                    intent.setType("image/*");
                                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTI);
                                }
                            });
                    snackbar.setActionTextColor(Color.BLUE);
                    View sbView = snackbar.getView();
                    TextView textView = sbView.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();

                } else {
                    imagesDataArrayList.clear();
                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        ImagesData tem=new ImagesData(data.getClipData().getItemAt(i).getUri());
                        imagesDataArrayList.add(tem);
                    }
                    Log.e("SIZE", imagesDataArrayList.size() + "");
                    imagesAdapter = new ImagesAdapter(Adddata.this, imagesDataArrayList);
                    recyclerView.setAdapter(imagesAdapter);
                    imagesAdapter.notifyDataSetChanged();
                }

            }

//            if (requestCode == PICK_IMAGE_ONE) {
//
//                Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
//                featureImage.setImageBitmap(selectedImage);
//            }


            if (requestCode == PICK_IMAGE_ONE && resultCode == RESULT_OK && data != null && data.getData() != null) {

                imageuri = data.getData();

//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);

                    featureImage.setImageURI(imageuri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            }
        }

    }
}





