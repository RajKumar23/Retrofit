package com.rajkumarrajan.sampleapplication.Support;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyPreference {

    private Context context;
    public MyPreference(Context context){
        this.context = context;
    }

    public final boolean isInternetOn() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                // Toast.makeText(getApplicationContext(), activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                //Toast.makeText(getApplicationContext(), activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
}
