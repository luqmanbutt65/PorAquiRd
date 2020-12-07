package com.example.realestate.BottomSheets;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.realestate.CustomeClasses.NumberTextWatcher;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;


public class BottomSheet extends Fragment {
    AutoCompleteTextView autoCompleteTextView;
    RelativeLayout forRent, forSale, maximumButton, minimumButton;
    AppCompatButton BedroomAny, BathroomAny, oneBedroom, oneBathroom, twoBedroom, twoBathroom, threeBedroom, threeBathroom,
            fourBedroom, fourBathroom, enterBedroom, enterBathroom, applyFilters;
    ArrayAdapter<String> adapter;
    List<String> list;
    Spinner typespiner;
EditText miniprice,maxprice;
    RadioGroup statusbutton;
    RadioButton forrentt, forsale;
    public static final String[] category = new String[]{"House", "Office", "Shop"};

    public BottomSheet() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.managefiltersheet, container, false);
        typespiner = view.findViewById(R.id.spinertype);
//        forRent = view.findViewById(R.id.forRent);
        forSale = view.findViewById(R.id.forsale);

//        maximumButton = view.findViewById(R.id.maximum);
//        minimumButton = view.findViewById(R.id.minimum);

        BedroomAny = view.findViewById(R.id.anyButton);
        BathroomAny = view.findViewById(R.id.bathroomAny);
        oneBedroom = view.findViewById(R.id.oneBedroom);
        oneBathroom = view.findViewById(R.id.oneBathroom);
        twoBedroom = view.findViewById(R.id.twoBedrrom);
        twoBathroom = view.findViewById(R.id.twoBathroom);
        threeBedroom = view.findViewById(R.id.threeBedroom);
        threeBathroom = view.findViewById(R.id.threeBathroom);
        fourBedroom = view.findViewById(R.id.fourBedroomRoom);
        fourBathroom = view.findViewById(R.id.fourBathroom);
        enterBedroom = view.findViewById(R.id.enterBedroom);
        enterBathroom = view.findViewById(R.id.enterBathroom);
        applyFilters = view.findViewById(R.id.anyButton);

        miniprice = view.findViewById(R.id.minimumprice);
        maxprice = view.findViewById(R.id.maximumprice);
        miniprice.addTextChangedListener(new NumberTextWatcher(miniprice));
        maxprice.addTextChangedListener(new NumberTextWatcher(maxprice));


        list = new ArrayList<String>();
        list.add("Select one");
        list.add("Apartamentos");
        list.add("Edificios");
        list.add("Solares");
        list.add("Casas");
        list.add("Villas");
        list.add("Naves Industriales");
        list.add("Fincas");
        list.add("Local Comercial");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typespiner.setAdapter(arrayAdapter);
        typespiner.setAdapter(arrayAdapter);
        forrentt = (RadioButton) view.findViewById(R.id.forrent1);
        forsale = (RadioButton) view.findViewById(R.id.forsale1);
        statusbutton = (RadioGroup) view.findViewById(R.id.togglegroup);
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

        typespiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), typespiner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        BedroomAny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Bedroom Any", Toast.LENGTH_SHORT).show();

            }
        });
        BathroomAny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Bathroom Any", Toast.LENGTH_SHORT).show();
            }
        });
        oneBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "1+", Toast.LENGTH_SHORT).show();
            }
        });
        oneBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "1+", Toast.LENGTH_SHORT).show();
            }
        });
        twoBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "2+", Toast.LENGTH_SHORT).show();
            }
        });
        twoBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "2+", Toast.LENGTH_SHORT).show();
            }
        });
        threeBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "3+", Toast.LENGTH_SHORT).show();
            }
        });
        threeBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "3+", Toast.LENGTH_SHORT).show();
            }
        });
        fourBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "4+", Toast.LENGTH_SHORT).show();
            }
        });
        fourBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilougeEnterBathrom();
            }
        });
        enterBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dilougeEnterBedroom();
            }
        });
        enterBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilougeEnterBathrom();
            }
        });
        applyFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Now Apply Filters", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    public void dilougeEnterBedroom() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customedilouge_filter_bedroom, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void dilougeEnterBathrom() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customedilouge_filter_bath, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

}