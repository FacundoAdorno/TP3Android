package com.example.examplelocation;


import android.location.*;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity implements LocationListener {

	private TextView latitudeText;
	private TextView longitudeText;
	private LocationManager locationManager;
	
	
	public TextView getLatitudeText() {
		return latitudeText;
	}

	public void setLatitudeText(TextView latitudeText) {
		this.latitudeText = latitudeText;
	}

	public TextView getLongitudeText() {
		return longitudeText;
	}

	public void setLongitudeText(TextView longitudeText) {
		this.longitudeText = longitudeText;
	}

	public LocationManager getLocationManager() {
		return locationManager;
	}

	public void setLocationManager(LocationManager locationManager) {
		this.locationManager = locationManager;
	}

	/*
	 * Se crean los TextLabels que mostrarán la lat. long. del Usuario.
	 * Se obtiene un LocationManager para utilizar el GPS. 
	 * La actividad queda seteada como Listener del LocationManager. 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Se le pide a la clase "R.java" los layout widgets "textView2" y "textView4". 
		setLatitudeText((TextView) findViewById(R.id.textView2));
		setLongitudeText((TextView) findViewById(R.id.textView4));
		
		setLocationManager((LocationManager) getSystemService(Context.LOCATION_SERVICE));
		
	}
	
	/*
	 *  Durante la puesta en marcha de la APP, ya sea recién iniciada o vuelta de la pausa 
	 *  (onPause), la actividad se agregar como Listener a la lista de Listener's del LocationManager.
	 */
	public void onResume(){
		super.onResume();
		getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
	}
	
	/*
	 * Durante la pausa de la APP, se le indica al Location Manager que la Activity se quite 
	 * temporalmente de su lista de Listener's.
	 */
	public void onPause(){
		super.onPause();
		getLocationManager().removeUpdates(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

////////////////////////////////////  Interfaz Listener  ///////////////////////////////////////
	
	@Override
	public void onLocationChanged(Location location) {
		if (location != null){
			getLatitudeText().setText(String.valueOf(location.getLatitude()));
			getLongitudeText().setText(String.valueOf(location.getLongitude()));
		}	
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
			//TODO
	}

//////////////////////////////////// Fin Interfaz Listener  ///////////////////////////////////////
}
