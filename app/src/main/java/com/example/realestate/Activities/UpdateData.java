package com.example.realestate.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Adapters.ImagesAdapter;
import com.example.realestate.Adapters.UpdatePrpertyGalleryAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.AppConstant;
import com.example.realestate.CustomeClasses.NumberTextWatcher;
import com.example.realestate.Fragments.GoogleMapFragment1;
import com.example.realestate.Fragments.MapsFragment;
import com.example.realestate.Model.Delete_Property.UpdatePropertyResponse;
import com.example.realestate.Model.GetList.Cities_Data;
import com.example.realestate.Model.GetList.City;
import com.example.realestate.Model.GetList.GetCitiesListResponse;
import com.example.realestate.Model.GetList.GetListPropertyType.GetpropertyListResponse;
import com.example.realestate.Model.GetList.GetListPropertyType.PropertyType;
import com.example.realestate.Model.GetList.GetListPropertyType.PropertyType_Data;
import com.example.realestate.Model.GetUpdateData.UpdateData_response;
import com.example.realestate.Model.ImagesData;
import com.example.realestate.Model.MyProject.AddProperties_Response;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.PropertiesExtra;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.example.realestate.R;
import com.example.realestate.SetMapdataInterface;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateData extends BaseActivity implements SetMapdataInterface {
    private static final int PICK_IMAGE_ONE = 0;
    private static final int PICK_IMAGE_MULTI = 2;
    private static final int MY_PERMISSIONS_REQUEST = 1;
    private static final String TAG = "AddData:Loc";
    final Calendar myCalendar = Calendar.getInstance();
    Context context;
    Button addImage;
    ImageView featureImage, backbtn;
    Spinner bedroomSpiner, bathsSpiner, pricespiner, city;
    Button add_data;
    RecyclerView recyclerView;
    Bitmap genralBitmap = null;
    Handler myHandle;
    Uri imageuri;
    List<String> parklist;
    Properties currentPropertites;

    UpdatePrpertyGalleryAdapter updatePrpertyGalleryAdapter;
    RadioGroup statusbutton;
    RadioButton forrentt, forsale;
    EditText description, sector, title, price, chekBoxpet, chekBoxroom, propert_location, unit_of_measure, date_of_construction;
    Spinner prpertytype, parkingcheks;
    List<String> listbath;
    List<String> listbed;
    List<String> listprice;
    DatePickerDialog constructionDatePicker;

    String pet = "";
    String park = "";
    int propertieID = 1;
    Bundle extras;
    ProgressDialog genralLoaderPD;
    ProgressDialog genralImageLoaderPD;


    CheckBox chUsed, chnew, chnewProject, petcheks;
    String statusVal = "For Sale";
    String bathVal;
    String bedroomVal;
    String priceType = "";
    //check this
    String propertytypeval = "";
    String propertyCondition = "";
    String citystring;
    ArrayList<City> cityArrayList;
    ArrayList<PropertyType> propertyTypeArrayList = new ArrayList<>();
    String bathzeroindex = "";
    String pricezeroindex = "";
    String bedroomzeroindex = "";
    String cityzeroindex = "";
    String propertytypezeroindex = "";
    ProgressDialog AddDataProgressDialog;

    double latitude, longitude;
    String property_main_id;
    double lat, lon;
    ArrayList<PropertiesGallery> propertiesGalleryArrayList;
    ArrayList<PropertiesExtra> propertiesExtraArrayList;
    ArrayList<PropertiesGallery> propertiesGalleryArrayListForIntent;
    //ads
    InterstitialAd mInterstitialAd;
    private ProgressDialog processingDialog;
    private boolean isLocFetch = false;
    private Location currentLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private int locationRequestCode = 1000;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    // private boolean isContinue = false;
    private boolean isGPS = false;

    public static File savebitmap(Bitmap bmp, String fileName) throws IOException {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + fileName + ".jpg");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }

    public static File bitmapToFile(Context context, Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + fileNameToSave);
            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 60, bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file; // it will return null
        }
    }

    public static String getFilePath(Context context, Uri uri) throws Exception {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, uri)) {//DocumentsContract.isDocumentUri(context.getApplicationContext(), uri))
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
                e.printStackTrace();
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

    @Override
    public void onResume() {
        super.onResume();
        statusCheck2();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        if (isNetworkConnected()) {

        } else {
            networkalert();
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(UpdateData.this);


        if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", UpdateData.this)) {
            setLocale("");
        } else if (new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", UpdateData.this)) {
            setLocale("es");

        } else if (new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", UpdateData.this)) {
            setLocale("sp");
        }

        //luqman ad

        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder()
                .build();


        add_data = findViewById(R.id.Add_Data);
        pricespiner = findViewById(R.id.pricespiner);
        statusbutton = (RadioGroup) findViewById(R.id.togglegroup2);
        backbtn = findViewById(R.id.back_btnAddData);
        bedroomSpiner = findViewById(R.id.bedroom);
        bathsSpiner = findViewById(R.id.baths);
        forrentt = (RadioButton) findViewById(R.id.forrent);
        forsale = (RadioButton) findViewById(R.id.forsale);
        genralLoaderPD = new ProgressDialog(this);
        genralLoaderPD.setMessage("Loading...");
        genralLoaderPD.setCancelable(false);

        genralImageLoaderPD = new ProgressDialog(this);
        genralImageLoaderPD.setMessage("Loading...");
        genralImageLoaderPD.setCancelable(false);
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
        propert_location = findViewById(R.id.location);
        prpertytype = findViewById(R.id.proprtyType);
        price.addTextChangedListener(new NumberTextWatcher(price));

        context = this;
        recyclerView = findViewById(R.id.images_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        extras = getIntent().getExtras();
        if (extras != null) {
            propertieID = extras.getInt("propertieIDKey");
        }

        getpropertydata(String.valueOf(propertieID));


        cityArrayList = new ArrayList<>();
        propertyTypeArrayList = new ArrayList<>();

        AddDataProgressDialog = new ProgressDialog(UpdateData.this);
        AddDataProgressDialog.setMessage("Loading..."); // Setting Message
        AddDataProgressDialog.setCancelable(false);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        petcheks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (petcheks.isChecked()) {
                    pet = "yes";

                } else {
                    pet = "no";
                }
            }
        });
        parkingcheks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                park = parkingcheks.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = (new SharedPreferenceConfig().getidOfUSerFromSP("id", UpdateData.this));
                String titl_value = title.getText().toString();
                String description_value = description.getText().toString();
                String price_value = price.getText().toString();
                //priceType + " " +
                price_value = price_value.replace(",", "");
                String city_value = citystring;
                String location_value = propert_location.getText().toString();
                String sector_value = sector.getText().toString();
                String unitofmeasure_value = unit_of_measure.getText().toString();
                String date_of_construction_value = date_of_construction.getText().toString();
                String petscheks_value = pet;
                String parkingcheks_value = park;
                String BedroomSpiner = bedroomVal;
                String BathroomSpiner = bathVal;
                String propertytypeSpiner = propertytypeval;
                String propertystatus = statusVal;
                String propertycondition_Val = propertyCondition;
                String pricetypeval = priceType;


                if (titl_value.isEmpty() || description_value.isEmpty() || price_value.isEmpty() || city_value.isEmpty() || sector_value.isEmpty() || unitofmeasure_value.isEmpty() ||
                        date_of_construction_value.isEmpty() || petscheks_value.isEmpty() || parkingcheks_value.isEmpty() || BedroomSpiner.isEmpty() || BathroomSpiner.isEmpty() ||
                        propertytypeSpiner.isEmpty() || propertystatus.isEmpty() || location_value.isEmpty() || propertycondition_Val.isEmpty() || pricetypeval.isEmpty()) {


                    showToast("Please Fill all Data");


                } else {
                    if (ContextCompat.checkSelfPermission(UpdateData.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(UpdateData.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        // Permission is granted

                        AddPropertyData(id, propertystatus, propertytypeSpiner, titl_value, description_value, price_value, location_value, city_value, sector_value, BedroomSpiner, BathroomSpiner, unitofmeasure_value, date_of_construction_value, petscheks_value, parkingcheks_value, propertycondition_Val, pricetypeval);

                    } else {
                        //Permission is not granted so you have to request it
                        ActivityCompat.requestPermissions(UpdateData.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST);
                    }


                }

            }
        });


        parklist = new ArrayList<String>();
        parklist.add("0");
        parklist.add("1");
        parklist.add("2");
        parklist.add("3");
        parklist.add("4");
        parklist.add("5");
        parklist.add("6");
        parklist.add("7");
        parklist.add("8");
        parklist.add("9");
        parklist.add("10");
        parklist.add("11");

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(UpdateData.this, android.R.layout.simple_spinner_item, parklist);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parkingcheks.setAdapter(arrayAdapter1);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                citystring = city.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        propert_location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!statusCheck()) {
                    showToast("GPS Not On");

                } else {

                    genralLoaderPD.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            gotoFregmnet();
                            genralLoaderPD.dismiss();
                        }
                    }, 3000);
                }
            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateData.this, MainActivity.class);

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
                    forrentt.setTextColor(Color.WHITE);
                    forsale.setTextColor(Color.BLACK);
                }
                if (forsale.isChecked()) {
                    statusVal = "For Sale";
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
                constructionDatePicker = new DatePickerDialog(UpdateData.this,
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


        getpricetype();

        prpertytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), prpertytype.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();


                propertytypeval = prpertytype.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        pricespiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                priceType = pricespiner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bedroomSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), bedroomSpiner.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();


                bedroomVal = bedroomSpiner.getSelectedItem().toString();


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


                bathVal = bathsSpiner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // updatePrpertyGalleryAdapter=new UpdatePrpertyGalleryAdapter(context,propertiesGalleryArrayList,UpdateData.this);
        //recyclerView.setAdapter(updatePrpertyGalleryAdapter);
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


//                imagesDataArrayList.clear();
//                imagesDataArrayList2.clear();
//                imagesAdapter = new ImagesAdapter(Adddata.this, imagesDataArrayList2,Adddata.this);
//                recyclerView.setAdapter(imagesAdapter);
//                imagesAdapter.notifyDataSetChanged();

                Intent intent = new Intent();
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTI);

            }
        });


    }

    public void getpricetype() {
        listprice = new ArrayList<String>();
        listprice.add("USD");
        listprice.add("DOP");
        listprice.add(0, pricezeroindex);

        ArrayAdapter<String> arrrayAdapterr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, listprice);
        arrrayAdapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pricespiner.setAdapter(arrrayAdapterr);
    }

    public void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/png");
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
                            updatePrpertyGalleryAdapter.notifyDataSetChanged();
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.addimage), "You can not select more than 10 images", Snackbar.LENGTH_LONG)
                                    .setAction("RETRY", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent();
                                            intent.setType("image/pmg");
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

                            propertiesGalleryArrayList.clear();
                            for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                                PropertiesGallery propertiesGallery = new PropertiesGallery();
                                propertiesGallery.setTtype(1);
                                propertiesGallery.setProperty_images(data.getClipData().getItemAt(i).getUri().toString());
                                propertiesGalleryArrayList.add(propertiesGallery);
                            }
                            Log.e("SIZE", propertiesGalleryArrayList.size() + "");
//                            imagesAdapter = new ImagesAdapter(UpdateData.this, imagesDataArrayList2, UpdateData.this);
                            recyclerView.setAdapter(updatePrpertyGalleryAdapter);
                            updatePrpertyGalleryAdapter.notifyDataSetChanged();

                        }

                    } else {
                        if (data.getData() != null) {

                            if (propertiesGalleryArrayList != null) {
                                if (propertiesGalleryArrayList.size() > 0) {
                                    ImagesData tem = new ImagesData(data.getData());

                                    PropertiesGallery propertiesGallery = new PropertiesGallery();
                                    propertiesGallery.setTtype(1);
                                    propertiesGallery.setProperty_images(data.getData().toString());
                                    propertiesGalleryArrayList.add(propertiesGallery);
                                    // Log.e("SIZE", imagesDataArrayList.size() + "");
                                    updatePrpertyGalleryAdapter.notifyDataSetChanged();
                                } else {
                                    ImagesData tem = new ImagesData(data.getData());

                                    PropertiesGallery propertiesGallery = new PropertiesGallery();
                                    propertiesGallery.setTtype(1);
                                    propertiesGallery.setProperty_images(data.getData().toString());
                                    propertiesGalleryArrayList.add(propertiesGallery);
                                    // Log.e("SIZE", imagesDataArrayList.size() + "");
                                    updatePrpertyGalleryAdapter = new UpdatePrpertyGalleryAdapter(context, propertiesGalleryArrayList, UpdateData.this);
                                    recyclerView.setAdapter(updatePrpertyGalleryAdapter);
                                    updatePrpertyGalleryAdapter.notifyDataSetChanged();

                                }
                            }


                        }
                    }


                } else {
                    if (data.getData() != null) {


                        ImagesData tem = new ImagesData(data.getData());

                        PropertiesGallery propertiesGallery = new PropertiesGallery();
                        propertiesGallery.setTtype(1);
                        propertiesGallery.setProperty_images(data.getData().toString());
                        propertiesGalleryArrayList.add(propertiesGallery);
                        // Log.e("SIZE", imagesDataArrayList.size() + "");
//                        imagesAdapter = new ImagesAdapter(UpdateData.this, imagesDataArrayList2, UpdateData.this);
                        recyclerView.setAdapter(updatePrpertyGalleryAdapter);
                        updatePrpertyGalleryAdapter.notifyDataSetChanged();


                    } else {
                        Toast.makeText(this, "Please Select Multiple Images", Toast.LENGTH_SHORT).show();

                    }
                }
            } else {
                Toast.makeText(this, "Please Select Multiple Images", Toast.LENGTH_SHORT).show();
            }
            if (requestCode == MY_PERMISSIONS_REQUEST) {

            }
            if (requestCode == AppConstant.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }


        if (requestCode == PICK_IMAGE_ONE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageuri = data.getData();
            featureImage.setImageURI(imageuri);
            currentPropertites.setMain_image(imageuri.toString());
            currentPropertites.setTttype(1);


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
        date = year + "-" + formatedMonth + "-" + formatedDay;
        return date;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void AddPropertyData(String id, String status, String property_type, String title, String description, String price, String location, String city, String sector, String bedroom, String bath, String unitOfMeasure, String dateOfConstruction, String petroom, String parkingLot, String propertycondition, String priceType) {
        //        MultipartBody.Builder builder = new MultipartBody.Builder();
//        builder.setType(MultipartBody.FORM);


        // Permission is granted
        if (parkingLot == null) {
            parkingLot = "";
        }
//       MultipartBody.Part[] parts = new MultipartBody.Part[imagesDataArrayList.size()];
        //  Old From Luqman
        //   MultipartBody.Part[] multipartTypedOutput = new MultipartBody.Part[imagesDataArrayList.size()];
        ArrayList<MultipartBody.Part> multipartTypedOutput = new ArrayList<>();
        for (int index = 0; index < propertiesGalleryArrayList.size(); index++) {

            File multiImageFile = null;
            String pathOfFile = null;
            switch (propertiesGalleryArrayList.get(index).getTtype()) {
                case 0:
                    final String myUrlStr = AppConstant.IMAGE_PATH + propertiesGalleryArrayList.get(index).getProperty_images();
                    URL url = null;
                    Bitmap bmp = null;
                    Bitmap bitm = null;
                    try {
                        bitm = new DownloadImageTask(UpdateData.this, bmp)
                                .execute(myUrlStr).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    Uri uri2 = getImageUri(this, bitm);

                    try {
                        pathOfFile = getFilePath(this, uri2);
                        multiImageFile = new File(pathOfFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    URL url=null;
//                    Uri uri2=null;
//                    try {
//                        url = new URL(myUrlStr);
//                        uri2 = Uri.parse( url.toURI().toString() );
//                    } catch (MalformedURLException e1) {
//                        e1.printStackTrace();
//                    } catch (URISyntaxException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        pathOfFile = getFilePath(this, uri2);
//                        multiImageFile = new File(pathOfFile);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                    break;

                case 1:

                    Uri uri = Uri.parse(propertiesGalleryArrayList.get(index).getProperty_images());
                    try {
                        pathOfFile = getFilePath(this, uri);
                        multiImageFile = new File(pathOfFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

            }


            //            Bitmap myBitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
            if (multiImageFile != null) {
                RequestBody surveyBody = RequestBody.create(MediaType.parse("image/png"), multiImageFile);
                //  multipartTypedOutput[index] = MultipartBody.Part.createFormData("property_images[]", "image" + index + getUnixTimeStamp(), surveyBody);
                multipartTypedOutput.add(MultipartBody.Part.createFormData("property_images[]", "image" + index + getUnixTimeStamp(), surveyBody));

            } else {
                showToast("Please ReSelect Images");
            }
        }


        File mainImageFile = null;
        String pathOfFile = null;
        switch (currentPropertites.getTttype()) {
            case 0:
                final String myUrlStr = AppConstant.IMAGE_PATH + currentPropertites.getMain_image();
                URL url = null;
                Bitmap bmp = null;
                Bitmap bitm = null;
                try {
                    bitm = new DownloadImageTask(UpdateData.this, bmp)
                            .execute(myUrlStr).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Uri uri2 = getImageUri(this, bitm);

                try {
                    pathOfFile = getFilePath(this, uri2);
                    mainImageFile = new File(pathOfFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                    URL url=null;
//                    Uri uri2=null;
//                    try {
//                        url = new URL(myUrlStr);
//                        uri2 = Uri.parse( url.toURI().toString() );
//                    } catch (MalformedURLException e1) {
//                        e1.printStackTrace();
//                    } catch (URISyntaxException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        pathOfFile = getFilePath(this, uri2);
//                        multiImageFile = new File(pathOfFile);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                break;

            case 1:

                Uri uri = Uri.parse(currentPropertites.getMain_image());
                try {
                    pathOfFile = getFilePath(this, uri);
                    mainImageFile = new File(pathOfFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }


        AddDataProgressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                AddDataProgressDialog.dismiss();
            }
        }, 5000); // 3000 milliseconds delay


        RequestBody id1 = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody feature_Image = RequestBody.create(MediaType.parse("image/png"), mainImageFile);
        MultipartBody.Part featureImag1 = MultipartBody.Part.createFormData("main_image", mainImageFile.getPath(), feature_Image);
        RequestBody status1 = RequestBody.create(MediaType.parse("text/plain"), status);
        RequestBody patio = RequestBody.create(MediaType.parse("text/plain"), "patio");
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


        RequestBody lng = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(longitude));
        RequestBody lat = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(latitude));
        RequestBody property_id = RequestBody.create(MediaType.parse("text/plain"), property_main_id);
        RequestBody price_type = RequestBody.create(MediaType.parse("text/plain"), priceType);
        AddDataProgressDialog.show();

        Call<AddProperties_Response> call = ApiClient.getRetrofit().create(ApiInterface.class).ADD_UPDATED_PROPERTY_DATA(id1, property_id, status1, property_type1, title1, description1, price1, location1, lng, lat, city1, sector1, bedroom1, bath1, unitOfMeasure1, dateOfConstruction1, petroom1,
                parkingLot1, propertycondition1, price_type, featureImag1, multipartTypedOutput);


        call.enqueue(new Callback<AddProperties_Response>() {
            @Override
            public void onResponse(Call<AddProperties_Response> call, Response<AddProperties_Response> response) {
                if (response.isSuccessful()) {

                    AddProperties_Response properties_response = response.body();
                    if (properties_response.getMessage().equals("Property Updated Successfully")) {

                        showToast("Data Added Succesfully");
//                        AdRequest adRequest = new AdRequest.Builder()
//                                .build();
//                        mInterstitialAd.loadAd(adRequest);
                        Intent i = new Intent(UpdateData.this, MainActivity.class);
                        startActivity(i);

                    } else if (properties_response.getMessage().equals("Image upload limit exceeded, Must be less than 10 pictures.")) {
                        Toast.makeText(getApplicationContext(), "Image size must not be more than 2MB/Image upload limit exceeded, Must be less than 10 pictures.", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "Data Uploading error !Retry Again", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                AddDataProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<AddProperties_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                AddDataProgressDialog.dismiss();
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


    public void removeFromImagearray(int position) {
        //imagesDataArrayList2.remove(position);
    }

    public void getpropertydata(String propertyid) {

        genralLoaderPD.show();

        Call<UpdatePropertyResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).EDIT_PROPERT_DETAIL_CALL(propertyid);
        call.enqueue(new Callback<UpdatePropertyResponse>() {
            @Override
            public void onResponse(Call<UpdatePropertyResponse> call, Response<UpdatePropertyResponse> response) {
                if (response.isSuccessful()) {
                    UpdatePropertyResponse updatePropertyResponse = response.body();
                    if (updatePropertyResponse.getMessage().equals("Edit property details")) {


                        if (updatePropertyResponse.getData() != null) {

                            if (updatePropertyResponse.getData().getProperties() != null) {
                                //propertydata
                                Properties properties = updatePropertyResponse.getData().getProperties();
                                currentPropertites = properties;

                                pricezeroindex = properties.getCurrency();

                                date_of_construction.setText(properties.getDate_of_construction());
                                cityzeroindex = properties.getCity();
                                propertytypezeroindex = properties.getProperty_type();
                                property_main_id = String.valueOf(properties.getId());


                                String title_val = ((title_val = properties.getTitle()) != null) ? title_val : "N/A";
                                title.setText(title_val);

                                String description_val = ((description_val = properties.getDescription()) != null) ? description_val : "N/A";
                                description.setText(description_val);

                                String propert_location_val = ((propert_location_val = properties.getLocation()) != null) ? propert_location_val : "N/A";
                                propert_location.setText(propert_location_val);

                                String sector_val = ((sector_val = properties.getSector()) != null) ? sector_val : "N/A";
                                sector.setText(sector_val);

                                String unit_of_measure_val = ((unit_of_measure_val = properties.getArea()) != null) ? unit_of_measure_val : "N/A";
                                unit_of_measure.setText(unit_of_measure_val);

                                String date_of_construction_val = ((date_of_construction_val = properties.getDate_of_construction()) != null) ? date_of_construction_val : "N/A";
                                date_of_construction.setText(date_of_construction_val);

                                String price_val = ((price_val = properties.getPrice()) != null) ? price_val : "N/A";
                                price.setText(price_val);


                                if (properties.getSale_type().equals("For Sale")) {
                                    forsale.setChecked(true);
                                } else if (properties.getSale_type().equals("For Rent")) {
                                    forrentt.isChecked();
                                    forrentt.setChecked(true);
                                } else {
                                    forsale.setChecked(false);
                                    forrentt.setChecked(false);
                                }


                                Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + properties.getMain_image()).into(featureImage);


                                if (updatePropertyResponse.getData().getProperties().getPropertiesGalleryArrayList() != null) {

                                }

                                if (updatePropertyResponse.getData().getProperties().getPropertiesExtraArrayList() != null) {

                                    if (updatePropertyResponse.getData().getProperties().getPropertiesExtraArrayList().size() > 0) {

                                        //extras
                                        propertiesExtraArrayList = updatePropertyResponse.getData().getProperties().getPropertiesExtraArrayList();

                                        for (int i = 0; i < propertiesExtraArrayList.size(); i++) {
                                            if (propertiesExtraArrayList.get(i).getType().equals("pets")) {
                                                if (propertiesExtraArrayList.get(i).getQuantity().equals("yes")) {
                                                    petcheks.setChecked(true);

                                                } else {
                                                    petcheks.setChecked(false);
                                                }

                                            } else if (propertiesExtraArrayList.get(i).getType().equals("parking")) {

                                                if (propertiesExtraArrayList.get(i).getQuantity().equals("yes")) {
//                                                    parkingcheks.setChecked(true);

                                                } else {
//                                                    parkingcheks.setChecked(false);
                                                }

                                            } else if (propertiesExtraArrayList.get(i).getType().equals("bedrooms")) {
                                                bedroomzeroindex = (propertiesExtraArrayList.get(i).getQuantity());
                                            } else if (propertiesExtraArrayList.get(i).getType().equals("bathrooms")) {
                                                bathzeroindex = (propertiesExtraArrayList.get(i).getQuantity());
                                            }

                                        }

                                        if (updatePropertyResponse.getData().getProperties().getPropertiesGalleryArrayList() != null) {
                                            propertiesGalleryArrayList = updatePropertyResponse.getData().getProperties().getPropertiesGalleryArrayList();
                                            if (propertiesGalleryArrayList.size() > 0) {


                                                updatePrpertyGalleryAdapter = new UpdatePrpertyGalleryAdapter(context, propertiesGalleryArrayList, UpdateData.this);
                                                recyclerView.setAdapter(updatePrpertyGalleryAdapter);

                                            }
                                        } else {
                                            propertiesGalleryArrayList = new ArrayList<>();

                                        }
                                    }
                                }
                            }
                        }
                        spinerchek();
                        GetCitiesList();
                        GetPropertyTypeList();
                        getpricetype();


                    } else {


                        showToast("Data Null");
                    }


                } else {


                    showToast("Error! Please try again!");
                }
                genralLoaderPD.dismiss();
            }

            @Override
            public void onFailure(Call<UpdatePropertyResponse> call, Throwable t) {

                showToast(t.getMessage());
                genralLoaderPD.dismiss();
            }
        });

    }

    public void GetCitiesList() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<GetCitiesListResponse> call = retrofit.create(ApiInterface.class).CITYLIST_CALL();
        call.enqueue(new Callback<GetCitiesListResponse>() {
            @Override
            public void onResponse(Call<GetCitiesListResponse> call, Response<GetCitiesListResponse> response) {
                if (response.isSuccessful()) {
                    GetCitiesListResponse getCitiesListResponse = response.body();
                    if (getCitiesListResponse.getMessage().equals("all cities")) {

                        Cities_Data cities_data = response.body().getData();
                        if (cities_data != null) {
                            cityArrayList = cities_data.getCityArrayList();
                            if (cityArrayList != null) {
                                ArrayList<String> cityList = new ArrayList<>();
                                if (cityArrayList.size() > 0) {

                                    for (City city : cityArrayList) {
                                        cityList.add(city.getCity());
                                    }
                                }
                                cityList.add(0, cityzeroindex);

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdateData.this, android.R.layout.simple_spinner_item, cityList);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                city.setAdapter(arrayAdapter);

                            }
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Server Error! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetCitiesListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void GetPropertyTypeList() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<GetpropertyListResponse> call = retrofit.create(ApiInterface.class).PROPERTY_TYPE_LIST_CALL();
        call.enqueue(new Callback<GetpropertyListResponse>() {
            @Override
            public void onResponse(Call<GetpropertyListResponse> call, Response<GetpropertyListResponse> response) {
                if (response.isSuccessful()) {
                    GetpropertyListResponse getCitiesListResponse = response.body();
                    if (getCitiesListResponse.getMessage().equals("all property types")) {

                        PropertyType_Data propertyType_data = response.body().getData();
                        if (propertyType_data != null) {
                            propertyTypeArrayList = propertyType_data.getCityArrayList();
                            if (propertyTypeArrayList != null) {
                                ArrayList<String> propertyType = new ArrayList<>();
                                if (propertyTypeArrayList.size() > 0) {

                                    for (PropertyType propertyType1 : propertyTypeArrayList) {
                                        propertyType.add(propertyType1.getType());
                                    }
                                }


                                propertyType.add(0, propertytypezeroindex);

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdateData.this, android.R.layout.simple_spinner_item, propertyType);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                prpertytype.setAdapter(arrayAdapter);

                            }
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Server Error! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetpropertyListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void spinerchek() {
        listbath = new ArrayList<String>();
        listbath.add("0");
        listbath.add("01");
        listbath.add("02");
        listbath.add("03");
        listbath.add("04");
        listbath.add("05");
        listbath.add("06");
        listbath.add("07");
        listbath.add("08");
        listbath.add("09");
        listbath.add("10");
        listbath.add("011");
        listbath.add(0, bathzeroindex);

        listbed = new ArrayList<String>();
        listbed.add("0");
        listbed.add("01");
        listbed.add("02");
        listbed.add("03");
        listbed.add("04");
        listbed.add("05");
        listbed.add("06");
        listbed.add("07");
        listbed.add("08");
        listbed.add("09");
        listbed.add("10");
        listbed.add("011");
        listbed.add(0, bedroomzeroindex);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listbath);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bathsSpiner.setAdapter(arrayAdapter);

        ArrayAdapter<String> bedadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listbath);
        bedadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedroomSpiner.setAdapter(bedadapter);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "test" + getUnixTimeStamp(), null);
        return Uri.parse(path);
    }

    public String getUnixTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String uNixtimeStamp = tsLong.toString();
        return uNixtimeStamp;
    }

    private Uri getImageUri2(Bitmap inImage) {
        File tempDir = Environment.getExternalStorageDirectory();
        tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
        tempDir.mkdir();
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp" + getUnixTimeStamp(), ".jpg", tempDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] bitmapData = bytes.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(tempFile);
    }

    private void gotoFregmnet() {
        Fragment fragment = new MapsFragment(this);
        FragmentManager fragmentManager = UpdateData.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_updatemain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onclick(double lat, double lng) {

        propert_location.setText(getAddres(lat, lng));
        latitude = lat;
        longitude = lng;
    }

    public boolean statusCheck() {
        final LocationManager manager = (LocationManager) UpdateData.this.getSystemService(LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            statusCheck2();
            return false;

        } else {
            return true;
        }
    }

    public void statusCheck2() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                getCurrentLocation();
            }
            //st.toast("Enabled");

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(UpdateData.this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void getCurrentLocation() {

        final LocationRequest locationRequest = new LocationRequest();

        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(UpdateData.this).removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestlocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestlocationIndex).getLongitude();

//                            sp.saveStringValue("latitude", String.valueOf(latitude));
//                            sp.saveStringValue("longitude", String.valueOf(longitude));
                        }

                    }
                }, Looper.myLooper());

    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        Bitmap bmImage;
        private Activity parent;
        private ProgressDialog payRequestPD;

        public DownloadImageTask(Activity parent, Bitmap bmImage) {
            this.bmImage = bmImage;
            this.parent = parent;
            payRequestPD = new ProgressDialog(parent);
            payRequestPD.setMessage("Tetopaati...");
        }

        protected void onPreExecute() {
            // called on UI thread
            // parent.showDialog(LOADING_DIALOG);
//            payRequestPD.show();
//            payRequestPD.setCancelable(false);
//            payRequestPD.setCanceledOnTouchOutside(false);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            try {
                if (payRequestPD != null && payRequestPD.isShowing()) {
                    payRequestPD.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            genralBitmap = result;
            this.bmImage = result;

        }
    }
}
