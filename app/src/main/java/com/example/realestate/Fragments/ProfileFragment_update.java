package com.example.realestate.Fragments;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.UpdateData;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.GetList.Cities_Data;
import com.example.realestate.Model.GetList.City;
import com.example.realestate.Model.GetList.GetCitiesListResponse;
import com.example.realestate.Model.GetUpdateData.UpdateData_data;
import com.example.realestate.Model.GetUpdateData.UpdateData_response;
import com.example.realestate.Model.GetUpdateData.User;
import com.example.realestate.Model.GetUpdateData.User_Data;
import com.example.realestate.Model.Login;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.realestate.Activities.Adddata.isDownloadsDocument;
import static com.example.realestate.Activities.Adddata.isExternalStorageDocument;
import static com.example.realestate.Activities.Adddata.isMediaDocument;


public class ProfileFragment_update extends Fragment {
    public static final int PICK_IMAGE = 1;
    public static final int PICK_PHOTOID = 3;
    public static final int PICK_PDF_FILE = 2;
    EditText username, name_company, phone_no, address, sector, Id, rnc, cell_no;
    Spinner city;
    Button submit, uploadImage, uploadFile, uploadphotoid;
    EditText tv_uploadFile;
    TextView tv_company, tv_name, tv_id, tv_rnc, tv_phone, tv_cell, tv_address, tv_city, tv_sector;
    TextView tv_userName, tv_email;
    ImageView back_btn, iv_uploadImage, iv_uploadphotoid;
    CircleImageView user_photo;
    String use_id;
    ArrayList<City> cityArrayList = new ArrayList<>();
    String citystring = "";
    ProgressDialog updateprogress;
    Uri imageuriuser_photo;
    Uri imageuriphotoid, fileuri;
    String cityzeroindex = "";

    public ProfileFragment_update() {
        // Required empty public constructor
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_update, container, false);

        updateprogress = new ProgressDialog(getContext());
        updateprogress.setMessage("Loading..."); // Setting Message
        updateprogress.setCancelable(false);
        use_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());

        ProfileData(use_id);

//        GetCitiesList();
        tv_userName = view.findViewById(R.id.tv_userName);
        tv_email = view.findViewById(R.id.tv_email);
        tv_company = view.findViewById(R.id.tv_company);
        tv_name = view.findViewById(R.id.tv_name);
        tv_id = view.findViewById(R.id.tv_id);
        tv_rnc = view.findViewById(R.id.tv_rnc);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_cell = view.findViewById(R.id.tv_cell);
        tv_address = view.findViewById(R.id.tv_address);
        tv_city = view.findViewById(R.id.tv_city);
        tv_sector = view.findViewById(R.id.tv_sector);


        user_photo = view.findViewById(R.id.user_photo);
        username = view.findViewById(R.id.username);
        phone_no = view.findViewById(R.id.phoneno);
        address = view.findViewById(R.id.address);
        city = view.findViewById(R.id.city);
        sector = view.findViewById(R.id.sector);
        Id = view.findViewById(R.id.id);
        rnc = view.findViewById(R.id.rnc);
        cell_no = view.findViewById(R.id.cell_no);
        name_company = view.findViewById(R.id.name_compantname);


        submit = view.findViewById(R.id.SubmitBtn);
        uploadImage = view.findViewById(R.id.uploadimage);
        uploadphotoid = view.findViewById(R.id.uploadphotoid);
        uploadFile = view.findViewById(R.id.uploadfile);
        iv_uploadImage = view.findViewById(R.id.iv_uploadimage);
        iv_uploadphotoid = view.findViewById(R.id.iv_uplophotoid);
        tv_uploadFile = view.findViewById(R.id.et_uploadfile);


        tv_userName.setText(new SharedPreferenceConfig().getNameOfUSerFromSP("name", getContext()));
        tv_email.setText(new SharedPreferenceConfig().getEmailOfUSerFromSP("Email", getContext()));


        chngeTextColor(name_company, tv_company);
        chngeTextColor(username, tv_name);
        chngeTextColor(phone_no, tv_phone);
        chngeTextColor(address, tv_address);
        chngeTextColor(sector, tv_sector);
        chngeTextColor(Id, tv_id);
        chngeTextColor(rnc, tv_rnc);
        chngeTextColor(cell_no, tv_cell);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/png");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        uploadphotoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/png");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTOID);
            }
        });

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("application/pdf");
//                intent.putExtra(Intent.EXTRA_TITLE, "invoice.pdf");
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_PDF_FILE);
            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//

                citystring = city.getSelectedItem().toString();
//

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        back_btn = view.findViewById(R.id.back_btn_updateprofile);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RNC = rnc.getText().toString();
                String cellno1 = cell_no.getText().toString();
                String ID = Id.getText().toString();
                String UserName = username.getText().toString();
                String PhoneNo = phone_no.getText().toString();
                String address2 = address.getText().toString();
                String City = citystring;
                String Sector = sector.getText().toString();
                String Name_Compantname = name_company.getText().toString();


                if (UserName.isEmpty() || address2.isEmpty() || City.isEmpty() || Sector.isEmpty() || cellno1.isEmpty()) {

                    Toast.makeText(getContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();

                } else {

                    if (RNC.trim().length() != 0 && !RNC.trim().equalsIgnoreCase("")) {

                        if (RNC.length() < 9 || ID.length() < 11) {

                            Toast.makeText(getContext(), "ID less than 11/RNC less than 9 character", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    updateProfile(UserName, PhoneNo, address2, City, Sector, ID, RNC, Name_Compantname, cellno1);
                }
            }
        });


        return view;
    }

    private void updateProfile(String userName, String phone_no, String address, String city, String sector, String iD, String rnc, String compny_name, String cellNo) {

        updateprogress.show();

        File mainImageFile = null;
        String pathOfFile = null;
        try {
            pathOfFile = getFilePath(getContext(), imageuriuser_photo);
            mainImageFile = new File(pathOfFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File mainImagephotoid = null;
        String pathOfphotoid = null;
        try {
            pathOfphotoid = getFilePath(getContext(), imageuriphotoid);
            mainImagephotoid = new File(pathOfphotoid);
        } catch (Exception e) {
            e.printStackTrace();
        }


        File permit = null;
        String pathpermit = null;
        try {

            pathpermit = getFilePath(getContext(), fileuri);
            permit = new File(pathpermit);
        } catch (Exception e) {
            e.printStackTrace();
        }


        MultipartBody.Part user_image = null;
        MultipartBody.Part mainfile = null;
        MultipartBody.Part photoid = null;

        if (mainImageFile != null) {
            RequestBody feature_Image = RequestBody.create(MediaType.parse("image/png"), mainImageFile);
            user_image = MultipartBody.Part.createFormData("user_image", mainImageFile.getPath(), feature_Image);
        } else {
            RequestBody feature_Image = RequestBody.create(MultipartBody.FORM, "");
            user_image = MultipartBody.Part.createFormData("user_image", "", feature_Image);

        }

        if (permit != null) {
            RequestBody permFile = RequestBody.create(MediaType.parse("application/pdf"), permit);
            mainfile = MultipartBody.Part.createFormData("work_permit", permFile.toString(), permFile);
        } else {
            RequestBody permFile = RequestBody.create(MultipartBody.FORM, "");
            mainfile = MultipartBody.Part.createFormData("work_permit", "", permFile);

        }

        if (mainImagephotoid != null) {
            RequestBody photo_id = RequestBody.create(MediaType.parse("image/png"), mainImagephotoid);
            photoid = MultipartBody.Part.createFormData("photo_id", mainImagephotoid.getPath(), photo_id);

        } else {

            RequestBody photo_id = RequestBody.create(MultipartBody.FORM, "");
            photoid = MultipartBody.Part.createFormData("photo_id", "", photo_id);

        }


        RequestBody id1 = RequestBody.create(MediaType.parse("text/plain"), use_id);
        RequestBody name1 = RequestBody.create(MediaType.parse("text/plain"), userName);
        RequestBody number1 = RequestBody.create(MediaType.parse("text/plain"), phone_no);
        RequestBody city1 = RequestBody.create(MediaType.parse("text/plain"), city);
        RequestBody sector1 = RequestBody.create(MediaType.parse("text/plain"), sector);
        RequestBody your_id1 = RequestBody.create(MediaType.parse("text/plain"), iD);
        RequestBody rnc1 = RequestBody.create(MediaType.parse("text/plain"), rnc);
        RequestBody company_name1 = RequestBody.create(MediaType.parse("text/plain"), compny_name);
        RequestBody address1 = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody phone_number1 = RequestBody.create(MediaType.parse("text/plain"), cellNo);

        Call<Login> call = ApiClient.getRetrofit().create(ApiInterface.class).UPDATEPROFIL_CALL(id1, name1, number1, city1, sector1, your_id1, rnc1, company_name1, address1, phone_number1, user_image, mainfile, photoid);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    Login login = response.body();
                    if (login.getMessage().equals("Profile changed successfully")) {
                        ///
                        Toast.makeText(getContext(), "Profile changed successfully", Toast.LENGTH_SHORT).show();

                        Fragment fragment = new ProfileFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameprofile, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();


                    } else {

                        Toast.makeText(getContext(), "Profile not Matches", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                updateprogress.dismiss();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                updateprogress.dismiss();
            }
        });
    }

    private void ProfileData(String id) {

        updateprogress.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<UpdateData_response> call = retrofit.create(ApiInterface.class).GET_UPDATE_DATA(id);
        call.enqueue(new Callback<UpdateData_response>() {
            @Override
            public void onResponse(Call<UpdateData_response> call, Response<UpdateData_response> response) {
                if (response.isSuccessful()) {
                    UpdateData_response updateData_response = response.body();
                    if (updateData_response.getMessage().equals("user whole detail")) {
                        UpdateData_data updateData_data = response.body().getData();

                        if (updateData_data != null) {

                            User userInfo = response.body().getData().getUser();
                            if (userInfo != null) {

                                User_Data user_data = response.body().getData().getUser_data();
                                if (user_data != null) {
                                    String imagepath = userInfo.getUser_image();
                                    new SharedPreferenceConfig().saveimageOfUSerInSP("image", imagepath, getContext());
                                    username.setText(userInfo.getName());
                                    phone_no.setText(user_data.getPhone_number());
                                    address.setText(userInfo.getAddress());
                                    cityzeroindex = userInfo.getCity();
                                    sector.setText(userInfo.getSector());
                                    Id.setText(user_data.getYour_id());
                                    rnc.setText(user_data.getRnc());
                                    cell_no.setText(user_data.getPhone_number());
                                    name_company.setText(user_data.getCompany_name());

                                    if (userInfo.getUser_image() != null) {
                                        Glide.with(getContext()).load("https://poraquird.stepinnsolution.com/public/user_images/" + userInfo.getUser_image()).into(user_photo);

                                    } else {
                                        String path = new SharedPreferenceConfig().geteimageOfUSerFromSP("image", getContext());
                                        Glide.with(getContext()).load(path).into(user_photo);
                                    }
//                                       Glide.with(getContext()).load("https://i0.wp.com/www.complexsql.com/wp-content/uploads/2018/11/null.png?resize=300%2C300").into(user_photo);
                                }

                            }
                        }
                        GetCitiesList();

                    } else {

                        Toast.makeText(getContext(), "Profile not Matches", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                updateprogress.dismiss();
            }

            @Override
            public void onFailure(Call<UpdateData_response> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                updateprogress.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            imageuriuser_photo = data.getData();
            iv_uploadImage.setImageURI(imageuriuser_photo);
        }
        if (requestCode == PICK_PHOTOID) {
            imageuriphotoid = data.getData();
            iv_uploadphotoid.setImageURI(imageuriphotoid);
        }
        if (requestCode == PICK_PDF_FILE) {

            fileuri = data.getData();
            String path = data.getDataString();
            tv_uploadFile.setText((CharSequence) path);
        }
    }

    private void chngeTextColor(EditText editText, TextView textView) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editText.getText().length() > 0) {
                    textView.setTextColor(Color.parseColor("#000000"));
                } else {
                    textView.setTextColor(Color.parseColor("#EE5181"));

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void GetCitiesList() {

        updateprogress.show();
        Call<GetCitiesListResponse> call = ApiClient.getRetrofit().create(ApiInterface.class).CITYLIST_CALL();
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

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cityList);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                city.setAdapter(arrayAdapter);

                            }
                        }

                    } else {
                        Toast.makeText(getContext(), "Server Error! Please try again!", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                updateprogress.dismiss();
            }

            @Override
            public void onFailure(Call<GetCitiesListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                updateprogress.dismiss();
            }
        });
    }
}