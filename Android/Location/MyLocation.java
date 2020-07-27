package com.orenda.canzy.Location;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocation extends ReactContextBaseJavaModule {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private static ReactApplicationContext reactContext;

    MyLocation(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }
    @NonNull
    @Override
    public String getName() {
        return "MyLocation";
    }

    @Override
    public void initialize() {

    }

    @Override
    public boolean canOverrideExistingModule() {
        return false;
    }

    @Override
    public void onCatalystInstanceDestroy() {

    }

    @ReactMethod
    public void getCurretnLocation(Promise callback){
        try{
            GPSTracker location = new GPSTracker(getReactApplicationContext());
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

            WritableMap event = Arguments.createMap();
            event.putDouble("longitude", longitude);
            event.putDouble("latitude", latitude);
            callback.resolve(event);
        }catch (Exception ex){
            callback.reject(ex);
        }
    }
}
