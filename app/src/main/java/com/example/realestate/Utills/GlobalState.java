package com.example.realestate.Utills;




        import android.app.Application;

        import com.example.realestate.Model.REST.Properties.Properties;

        import java.util.ArrayList;



public class GlobalState extends Application {

    private static GlobalState mInstance;
    private ArrayList<Properties> propertiesArrayList;

    public ArrayList<Properties> getPropertiesArrayList() {
        return propertiesArrayList;
    }

    public void setPropertiesArrayList(ArrayList<Properties> propertiesArrayList) {
        this.propertiesArrayList = propertiesArrayList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized GlobalState getInstance() {
        if (mInstance == null) {
            mInstance = new GlobalState();
        }
        return mInstance;
    }

    public void clearData() {
//mInstance.merchantDetail=null;
    }
}