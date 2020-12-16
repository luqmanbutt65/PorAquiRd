package com.example.realestate;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.realestate.Activities.BaseActivity;
import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.ImagesAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.CustomeClasses.NumberTextWatcher;
import com.example.realestate.Model.GetList.GetCitiesListResponse;
import com.example.realestate.Model.ImagesData;
import com.example.realestate.Model.REST.Properties.AddPropertiesData;
import com.example.realestate.Model.REST.Properties.Properties_Add_Response;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adddata extends BaseActivity {
    private static final int PICK_IMAGE_ONE = 0;
    private static final int PICK_IMAGE_MULTI = 2;
    private static final int MY_PERMISSIONS_REQUEST = 1;
    Context context;
    Button addImage;
    ImageView featureImage, backbtn;
    Spinner bedroomSpiner, bathsSpiner, pricespiner;
    Button add_data;
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

    CheckBox chUsed, chnew, chnewProject;
    String statusVal;
    String bathVal;
    String bedroomVal;
    String propertytypeval;
    String propertyCondition;
    boolean statusValCheck = false;
    Bitmap bitmapmainimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);
        add_data = findViewById(R.id.Add_Data);
        pricespiner = findViewById(R.id.pricespiner);
        statusbutton = (RadioGroup) findViewById(R.id.togglegroup2);
        backbtn = findViewById(R.id.back_btnAddData);
        bedroomSpiner = findViewById(R.id.bedroom);
        bathsSpiner = findViewById(R.id.baths);
        forrentt = (RadioButton) findViewById(R.id.forrent);
        forsale = (RadioButton) findViewById(R.id.forsale);

        chnew = findViewById(R.id.neww);
        chUsed = findViewById(R.id.used);
        chnewProject = findViewById(R.id.newproject);
        date_of_construction = findViewById(R.id.dateofconstruction);


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


        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {










                String id = (new SharedPreferenceConfig().getidOfUSerFromSP("id", Adddata.this));
                String titl_value = title.getText().toString();
                String description_value = description.getText().toString();
                String price_value = price.getText().toString();
                String city_value = city.getText().toString();
                String location_value = location.getText().toString();
                String sector_value = sector.getText().toString();
                String unitofmeasure_value = unit_of_measure.getText().toString();
                String date_of_construction_value = date_of_construction.getText().toString();
                String petscheks_value = petcheks.getText().toString();
                String parkingcheks_value = parkingcheks.getText().toString();
                String BedroomSpiner = bedroomVal;
                String BathroomSpiner = bathVal;
                String propertytypeSpiner = propertytypeval;
                String propertystatus = statusVal;
                String propertycondition_Val = propertyCondition;


                if (titl_value.isEmpty() || description_value.isEmpty() || price_value.isEmpty() || city_value.isEmpty() || sector_value.isEmpty() || unitofmeasure_value.isEmpty() ||
                        date_of_construction_value.isEmpty() || petscheks_value.isEmpty() || parkingcheks_value.isEmpty() || BedroomSpiner.isEmpty() || BathroomSpiner.isEmpty() ||
                        propertytypeSpiner.isEmpty() || propertystatus.isEmpty() || location_value.isEmpty() || propertycondition_Val.isEmpty()) {
                    showToast("Please Fill all Data");

                } else {
                    if (ContextCompat.checkSelfPermission(Adddata.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(Adddata.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        // Permission is granted
                        AddPropertyData(id, propertystatus, propertytypeSpiner, titl_value, description_value, price_value, location_value, city_value, sector_value, BedroomSpiner, BathroomSpiner, unitofmeasure_value, date_of_construction_value, petscheks_value, parkingcheks_value, propertycondition_Val);

                    }
                    else {
                        //Permission is not granted so you have to request it
                        ActivityCompat.requestPermissions(Adddata.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST);
                    }




                }

            }
        });
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
                    statusVal = "For Rent";
                    statusValCheck = true;
                    forrentt.setTextColor(Color.WHITE);
                    forsale.setTextColor(Color.BLACK);
                }
                if (forsale.isChecked()) {
                    statusVal = "For Sale";
                    statusValCheck = false;
                    forsale.setTextColor(Color.WHITE);
                    forrentt.setTextColor(Color.BLACK);
                }
            }
        });


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
                if (position == 0) {
                    propertytypeval = "";

                } else {

                    propertytypeval = prpertytype.getSelectedItem().toString();
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

                if (position == 0) {
                    bedroomVal = "";

                } else {

                    bedroomVal = bedroomSpiner.getSelectedItem().toString();
                }

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

                if (position == 0) {
                    bathVal = "";

                } else {

                    bathVal = bedroomSpiner.getSelectedItem().toString();
                }
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
            if(requestCode==MY_PERMISSIONS_REQUEST){

            }


        }


        if (requestCode == PICK_IMAGE_ONE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageuri = data.getData();
            featureImage.setImageURI(imageuri);
            try {
                bitmapmainimage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
            }catch (Exception e){

            }




//            try {
//                bitmapmainimage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
//                featureImage.setImageBitmap(bitmapmainimage);
//                File file = null;
//                try {
//                    file = savebitmap(bitmapmainimage, getUnixTimeStamp());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Bitmap myBitmap2 = BitmapFactory.decodeFile(file.getAbsolutePath());
//                RequestBody feature_Image = RequestBody.create(MediaType.parse("image/*"), file);
//                MultipartBody.Part featureImag1 = MultipartBody.Part.createFormData("main_image", file.getPath(), feature_Image);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


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


    private void AddPropertyData(String id, String status, String property_type, String title, String description, String price, String location, String city, String sector, String bedroom, String bath, String unitOfMeasure, String dateOfConstruction, String petroom, String parkingLot, String propertycondition) {



            // Permission is granted

            if (parkingLot == null) {
                parkingLot = "";
            }
//        otpProgressDialog.show();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                    .addConverterFactory(GsonConverterFactory.create()).build();

            RequestBody id1 = RequestBody.create(MediaType.parse("text/plain"), id);

            MultipartBody.Part[] multipartTypedOutput = new MultipartBody.Part[imagesDataArrayList.size()];

            for (int index = 0; index < imagesDataArrayList.size(); index++) {
                Log.d("Upload request", "requestUploadpropertImages: property image " + index + "  " + imagesDataArrayList.get(index).getUri().toString());
                File multiImageFile= null;
                String pathOfFile=null;
                try {
                    pathOfFile =getFilePath(this,imagesDataArrayList.get(index).getUri());
                    multiImageFile=new File(pathOfFile);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
 //            Bitmap myBitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
                if(multiImageFile!=null){
                    RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), multiImageFile);
                    multipartTypedOutput[index] = MultipartBody.Part.createFormData("property_images", multiImageFile.getPath(), surveyBody);
                }else {
                    showToast("Please ReSelect Images");
                }

            }

        File mainImageFile= null;
        String pathOfFile=null;
        try {
            pathOfFile =getFilePath(this,imageuri);
            mainImageFile=new File(pathOfFile);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }




            RequestBody feature_Image = RequestBody.create(MediaType.parse("image/*"), mainImageFile);
            MultipartBody.Part featureImag1 = MultipartBody.Part.createFormData("main_image", mainImageFile.getPath(), feature_Image);
            RequestBody status1 = RequestBody.create(MediaType.parse("text/plain"), status);
            RequestBody property_type1 = RequestBody.create(MediaType.parse("text/plain"), property_type);
            RequestBody title1 = RequestBody.create(MediaType.parse("text/plain"), title);
            RequestBody description1 = RequestBody.create(MediaType.parse("text/plain"), description);
            RequestBody price1 = RequestBody.create(MediaType.parse("text/plain"), price);
            RequestBody location1 = RequestBody.create(MediaType.parse("text/plain"), location);
            RequestBody city1 = RequestBody.create(MediaType.parse("text/plain"), city);
            RequestBody sector1 = RequestBody.create(MediaType.parse("text/plain"), sector);
            RequestBody bedroom1 = RequestBody.create(MediaType.parse("text/plain"), bedroom);
            RequestBody bath1 = RequestBody.create(MediaType.parse("text/plain"), bath);
            RequestBody unitOfMeasure1 = RequestBody.create(MediaType.parse("text/plain"), unitOfMeasure);
            RequestBody dateOfConstruction1 = RequestBody.create(MediaType.parse("text/plain"), dateOfConstruction);
            RequestBody petroom1 = RequestBody.create(MediaType.parse("text/plain"), petroom);
            RequestBody parkingLot1 = RequestBody.create(MediaType.parse("text/plain"), parkingLot);
            RequestBody propertycondition1 = RequestBody.create(MediaType.parse("text/plain"), propertycondition);

       // multipartTypedOutput,
            Call<Properties_Add_Response> call = retrofit.create(ApiInterface.class).ADD_PROPERTY_DATA(id1, status1, property_type1, title1, description1, price1, location1, city1, sector1, bedroom1, bath1, unitOfMeasure1, dateOfConstruction1, petroom1, parkingLot1, propertycondition1,  featureImag1);
            call.enqueue(new Callback<Properties_Add_Response>() {
                @Override
                public void onResponse(Call<Properties_Add_Response> call, Response<Properties_Add_Response> response) {
                    if (response.isSuccessful()) {
                        Properties_Add_Response properties_add_response = response.body();
                        if (properties_add_response.getMessage().equals("Property Added Succesfully")) {

//                            AddPropertiesData addPropertiesData = new AddPropertiesData();
//                            addPropertiesData = GlobalState.getInstance().getProperties();
//                            addPropertiesData.setSale_type(status);
//                            addPropertiesData.setTitle(title);
//                            addPropertiesData.setArea(unitOfMeasure);
//                            addPropertiesData.setBath(bath);
//                            addPropertiesData.setBedroom(bedroom);
//                            addPropertiesData.setCity(city);
//                            addPropertiesData.setLocation(location);
//                            addPropertiesData.setPrice(price);
//                            addPropertiesData.setDescription(description);
//                            addPropertiesData.setSector(sector);
//                            addPropertiesData.setDate_of_construction(dateOfConstruction);
//                            addPropertiesData.setParking(parkingLot);
//                            addPropertiesData.setPets(petroom);
//                            addPropertiesData.setProperty_type(property_type);
//                            addPropertiesData.setProperty_condition(propertycondition);
//                            addPropertiesData.setMain_image(featureImag1);
//                            addPropertiesData.setPropertiesGalleryArrayList(multipartTypedOutput);

                        } else {

                            Toast.makeText(getApplicationContext(), "Dta Uploading error", Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                    }
//                otpProgressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Properties_Add_Response> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                otpProgressDialog.dismiss();
                }
            });








    }


    public void onchecked(View v) {

        if (v.getId() == R.id.neww) {

            propertyCondition = "New";

            chUsed.setChecked(false);
            chnewProject.setChecked(false);
            chnew.setChecked(true);

        }

        if (v.getId() == R.id.used) {
            propertyCondition = "Used";

            chnewProject.setChecked(false);
            chnew.setChecked(false);
            chUsed.setChecked(true);
        }

        if (v.getId() == R.id.newproject) {

            propertyCondition = "New Project";

            chUsed.setChecked(false);
            chnew.setChecked(false);
            chnewProject.setChecked(true);

        }

    }
    public static File savebitmap(Bitmap bmp,String fileName) throws IOException {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + fileName+".jpg");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }
    public static File bitmapToFile(Context context,Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + fileNameToSave);
            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 60 , bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        }catch (Exception e){
            e.printStackTrace();
            return file; // it will return null
        }
    }




    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context,uri)) {//DocumentsContract.isDocumentUri(context.getApplicationContext(), uri))
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


//    public void GetCitiesList() {
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        Call<GetCitiesListResponse> call = retrofit.create(ApiInterface.class).CITYLIST_CALL();
//        call.enqueue(new Callback<GetCitiesListResponse>() {
//            @Override
//            public void onResponse(Call<GetCitiesListResponse> call, Response<GetCitiesListResponse> response) {
//                if (response.isSuccessful()) {
//                    GetCitiesListResponse getCitiesListResponse = response.body();
//                    if (getCitiesListResponse.getMessage().equals("all cities")) {
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Server Error! Please try again!", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetCitiesListResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}




