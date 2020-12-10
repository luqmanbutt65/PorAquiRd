package com.example.realestate;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.ImagesAdapter;
import com.example.realestate.CustomeClasses.NumberTextWatcher;
import com.example.realestate.Fragments.MapsFragment;
import com.example.realestate.Fragments.PrivecyPolicy;
import com.example.realestate.Model.ImagesData;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Adddata extends AppCompatActivity {
    private static final int PICK_IMAGE_ONE = 0;
    private static final int PICK_IMAGE_MULTI = 2;
    Context context;
    Button addImage;
    ImageView featureImage, backbtn;
    Spinner bedroomSpiner, bathsSpiner, pricespiner;

    RecyclerView recyclerView;
    ArrayList<ImagesData> imagesDataArrayList = new ArrayList<>();
    Uri imageuri;
    ImagesAdapter imagesAdapter;
    RadioGroup statusbutton;
    RadioButton forrentt, forsale;
    EditText description, city, sector, petcheks, parkingcheks, title, price, chekBoxpet, chekBoxroom, location, unit_of_measure, date_of_construction;

    Spinner prpertytype;
    List<String> list;
    List<String> listprice;
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog constructionDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        pricespiner = findViewById(R.id.pricespiner);
        statusbutton = (RadioGroup) findViewById(R.id.togglegroup2);
        backbtn = findViewById(R.id.back_btnAddData);
        bedroomSpiner = findViewById(R.id.bedroom);
        bathsSpiner = findViewById(R.id.baths);
        forrentt = (RadioButton) findViewById(R.id.forrent);
        forsale = (RadioButton) findViewById(R.id.forsale);

        unit_of_measure = findViewById(R.id.unitOfmeasure);
        date_of_construction = findViewById(R.id.dateofconstruction);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        city = findViewById(R.id.city);
        sector = findViewById(R.id.Sector);
        petcheks = findViewById(R.id.petcheks);
        parkingcheks = findViewById(R.id.parkingcheks);
        price = findViewById(R.id.price);
        location = findViewById(R.id.location);
        chekBoxpet = findViewById(R.id.petcheks);
        chekBoxroom = findViewById(R.id.parkingcheks);
        prpertytype = findViewById(R.id.proprtyType);
        price.addTextChangedListener(new NumberTextWatcher(price));

        String titl_value = title.getText().toString();
        String description_value = description.getText().toString();
        String price_value = price.getText().toString();
        String city_value = city.getText().toString();
        String sector_value = sector.getText().toString();
        String unitofmeasure_value = unit_of_measure.getText().toString();
        String date_of_construction_value = date_of_construction.getText().toString();
        String petscheks_value = petcheks.getText().toString();
        String parkingcheks_value = parkingcheks.getText().toString();

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (new SharedPreferenceConfig().getLocationOfUSerFromSP("location", Adddata.this) != null) {

                    location.setText(new SharedPreferenceConfig().getLocationOfUSerFromSP("location", Adddata.this));
                } else {
//                    Fragment fragment = new MapsFragment();
//                    FragmentManager fragmentManager = Adddata.this.getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.addDataframe, fragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();

                }
            }
        });

//        RichTextActions richTextActions = (RichTextActions) findViewById(R.id.text_actions);
//        description.setRichTextActionsView(richTextActions);
//        description.setPreviewText("view");
//        description.setHint("Description");

//        if (savedInstanceState != null) {
//            description.restoreState(savedInstanceState);
//        }


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adddata.this, MainActivity.class);

                startActivity(intent);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplication().getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE)) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }


        statusbutton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (forrentt.isChecked()) {
                    forrentt.setTextColor(Color.WHITE);
                    forsale.setTextColor(Color.BLACK);
                }
                if (forsale.isChecked()) {
                    forsale.setTextColor(Color.WHITE);
                    forrentt.setTextColor(Color.BLACK);
                }
            }
        });


//        String Title = title.getText().toString();
//        String Decription = description.getText().toString();
//        String Price = price.getText().toString();
//        String Location = location.getText().toString();
//        String BedroomSpiner = bedroomSpiner.getSelectedItem().toString();
//        String BathroomSpiner = bathsSpiner.getSelectedItem().toString();


        date_of_construction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

// date picker dialog
                constructionDatePicker = new DatePickerDialog(Adddata.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = getDateInCorrectFormat(year, monthOfYear, dayOfMonth);

                                date_of_construction.setText(date);

                            }
                        }, year, month, day);
                constructionDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());// TODO: used to hide future date,month and year

                constructionDatePicker.show();


            }
        });


        list = new ArrayList<String>();
        list.add("Select A type");
        list.add("Apartamentos");
        list.add("Edificios");
        list.add("Solares");
        list.add("Casas");
        list.add("Villas");
        list.add("Naves Industriales");
        list.add("Fincas");
        list.add("Local Comercial");


        listprice = new ArrayList<String>();
        listprice.add("Select A type");
        listprice.add("USD");
        listprice.add("DOP");


        ArrayAdapter<String> arrrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        arrrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prpertytype.setAdapter(arrrayAdapter);
        prpertytype.setAdapter(arrrayAdapter);

        ArrayAdapter<String> arrrayAdapterr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, listprice);
        arrrayAdapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pricespiner.setAdapter(arrrayAdapterr);

        prpertytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), prpertytype.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();

                switch (position) {
                    case 0:
                        if (position == 0) {
//                            Toast.makeText(context, "plz swelect other value", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        pricespiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), pricespiner.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        list = new ArrayList<String>();
        list.add("");
        list.add("0");
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
//                Toast.makeText(getBaseContext(), bedroomSpiner.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bathsSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), bathsSpiner.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {

            if (requestCode == PICK_IMAGE_MULTI) {

                //   Log.e("++data", "" + data.getClipData().getItemCount());// Get count of image here.

                // Log.e("++count", "" + data.getClipData().getItemCount());

                if (data != null) {
                    if (data.getClipData() != null) {
                        if (data.getClipData().getItemCount() > 10) {
                            imagesAdapter.notifyDataSetChanged();
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.addimage), "You can not select more than 10 images", Snackbar.LENGTH_LONG)
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
                                ImagesData tem = new ImagesData(data.getClipData().getItemAt(i).getUri());
                                imagesDataArrayList.add(tem);
                            }
                            Log.e("SIZE", imagesDataArrayList.size() + "");
                            imagesAdapter = new ImagesAdapter(Adddata.this, imagesDataArrayList);
                            recyclerView.setAdapter(imagesAdapter);
                            imagesAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (data.getData() != null) {


                            ImagesData tem = new ImagesData(data.getData());
                            imagesDataArrayList.add(tem);
                            // Log.e("SIZE", imagesDataArrayList.size() + "");
                            imagesAdapter = new ImagesAdapter(Adddata.this, imagesDataArrayList);
                            recyclerView.setAdapter(imagesAdapter);
                            imagesAdapter.notifyDataSetChanged();


                        } else {
                            Toast.makeText(this, "Please Select Multiple Images", Toast.LENGTH_SHORT).show();

                        }
                    }
                } else {
                    Toast.makeText(this, "Please Select Multiple Images", Toast.LENGTH_SHORT).show();
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

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_of_construction.setText(sdf.format(myCalendar.getTime()));
    }

    private String getDateInCorrectFormat(int year, int monthOfYear, int dayOfMonth) {

        String date = "";
        String formatedMonth = "";
        String formatedDay = "";
        if (monthOfYear < 9) {
            formatedMonth = "0" + (monthOfYear + 1);
        } else {
            formatedMonth = String.valueOf(monthOfYear + 1);
        }

        if (dayOfMonth < 10) {
            formatedDay = "0" + dayOfMonth;
        } else {
            formatedDay = String.valueOf(dayOfMonth);
        }
        date = formatedMonth + "/" + formatedDay + "/" + year;
        return date;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}




