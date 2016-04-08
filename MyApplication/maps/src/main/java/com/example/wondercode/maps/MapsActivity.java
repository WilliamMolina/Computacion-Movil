package com.example.wondercode.maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.renderscript.RenderScript;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.design.widget.TabLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest lr = new LocationRequest();
    private Polyline p;
    private Marker m;
    private List<LatLng> points;
    private PolylineOptions options;
    private LatLng t1=new LatLng(6.283965,-75.584123);
    private LatLng t2=new LatLng(6.264702,-75.569235);
    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment sp=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapa);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        lr.setInterval(10000);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager=getSupportFragmentManager();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(6.2796094, -75.5900592);
        m = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(t1));
        mMap.addMarker(new MarkerOptions().position(t2));
        options = this.decodePoly("kcge@p}glMkA]u@a@]I]Ew@?wAXeALa@DY?gAKiA_@");
        points=options.getPoints();
        p=mMap.addPolyline(options);
        p.setColor(Color.rgb(224, 111, 96));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20));
    }

    @Override
    public void onConnected(Bundle bundle) {
       try{
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
           if (mLastLocation != null) {
            Log.d("latitude", mLastLocation.getLatitude()+"");
            Log.d("longitude", mLastLocation.getLongitude()+"");
            startLocationUpdates();
        }
       }catch(SecurityException ex){

       }

    }
    protected void startLocationUpdates() {
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, lr, this);
        }catch(SecurityException ex){

        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        //mGoogleApiClient.connect();

        super.onStart();
    }

    @Override
    protected void onStop() {
        //mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onLocationChanged(Location location) {
        LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
        String s=location.getLatitude()+" "+location.getLongitude();
        float[]r =new float[1];
        Location a=new Location("");
        Location b=new Location("");
        Location c=new Location("");
        Location d=new Location("");
        a.setLatitude(ll.latitude);
        a.setLongitude(ll.longitude);
        b.setLatitude(m.getPosition().latitude);
        b.setLongitude(m.getPosition().longitude);
        c.setLatitude(t1.latitude);
        c.setLongitude(t1.longitude);
        d.setLatitude(t2.latitude);
        d.setLongitude(t2.longitude);
        float d1=a.distanceTo(b);
        float d2=a.distanceTo(c);
        float d3=a.distanceTo(d);
        if(d1<=100){
            Toast.makeText(getApplicationContext(),"You are at "+d1+"m of your target",Toast.LENGTH_SHORT).show();
        }else if(d2<=100){
            Toast.makeText(getApplicationContext(),"You are at "+d2+"m of el control",Toast.LENGTH_SHORT).show();
        }else if(d3<=100){
            Toast.makeText(getApplicationContext(),"You are at "+d3+"m of UdeA",Toast.LENGTH_SHORT).show();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 20));
        points.add(ll);
        p.setPoints(points);
        //mMap.addMarker(new MarkerOptions().position(ll).title("My location"));
        //Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    private PolylineOptions decodePoly(String encoded) {
        PolylineOptions options=new PolylineOptions();
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
            options.add(p);
            Log.d("Location", p.toString());
        }

        return options;
    }
}
