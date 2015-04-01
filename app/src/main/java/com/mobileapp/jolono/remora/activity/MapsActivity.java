package com.mobileapp.jolono.remora.activity;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.Services.GPSSystem;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{
    
    private EditText mAddressView;
    private Button mFindAddressButton;
    private Button mFindMeButton;
    private LatLng mAddress;
    private LatLng mMyLocation;
    private GoogleMap mMap = null;
    private Marker mMarker = null;
    private Marker mMyMarker = null;
    private GPSSystem gps;

    
    private LocationManager locationManager;
    private LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = 
                (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mAddressView = (EditText) findViewById(R.id.et_location);
        mAddress = new LatLng(39.999446, -83.012967);

        mFindAddressButton = (Button) findViewById(R.id.btn_find);
        mFindMeButton = (Button) findViewById(R.id.find_me_map_button);
        mFindMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                gps = new GPSSystem(MapsActivity.this);
                mMyLocation = gps.getMyLoc();
                if(mMyLocation != null) {
                    mMyMarker = mMap.addMarker(new MarkerOptions().position(mMyLocation));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMyLocation, 13));
                    gps.stopUsingGPS();
                }
                
            }
        });
        mFindAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMarker != null) {
                    mMarker.remove();
                }
                LatLng address = getAddress();
                if(address != null) {
                    mMarker = mMap.addMarker(new MarkerOptions().position(address));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(address, 13));
                }
            }
        });
        
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) { 
        savedInstanceState.putString("address", mAddressView.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //mAddressView.setText(savedInstanceState.getString("address"));
        
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        if(mAddressView.getText().toString() != null) {
            mFindAddressButton.performClick();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    public LatLng getAddress() {
        Log.d("In Get address", mAddressView.getText().toString());
        String address = mAddressView.getText().toString();
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocationName(address, 5);
            if (addresses.size() > 0) {

                Double lat = (double) (addresses.get(0).getLatitude());
                Double lon = (double) (addresses.get(0).getLongitude());

                Log.d("lat-long", "" + lat + "......." + lon);
                return new LatLng(lat, lon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;

    }

}
