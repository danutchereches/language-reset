package com.example.langfuck;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.activity_main);
		
		Locale locale = new Locale("en", "US");
		
		/*
		Resources res = getBaseContext().getResources();
		Locale.setDefault(locale);
		Configuration config = res.getConfiguration();
		config.locale = locale;
		res.updateConfiguration(config, res.getDisplayMetrics());
		*/
		
		//startActivity(new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS));
		
		try {
			changeLanguage(locale);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finish();
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
	
	@SuppressWarnings("unchecked")
	public static void changeLanguage(Locale locale)
			throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		@SuppressWarnings("rawtypes")
		Class amnClass = Class.forName("android.app.ActivityManagerNative");
		Object amn = null;
		Configuration config = null;
		
		// amn = ActivityManagerNative.getDefault();
		Method methodGetDefault = amnClass.getMethod("getDefault");
		methodGetDefault.setAccessible(true);
		amn = methodGetDefault.invoke(amnClass);
		
		// config = amn.getConfiguration();
		
		Method methodGetConfiguration = amnClass.getMethod("getConfiguration");
		methodGetConfiguration.setAccessible(true);
		config = (Configuration) methodGetConfiguration.invoke(amn);
		
		// config.userSetLocale = true;
		@SuppressWarnings("rawtypes")
		Class configClass = config.getClass();
		Field f = configClass.getField("userSetLocale");
		f.setBoolean(config, true);
		
		// set the locale to the new value
		config.locale = locale;
		
		// amn.updateConfiguration(config);
		Method methodUpdateConfiguration = amnClass.getMethod("updateConfiguration", Configuration.class);
		methodUpdateConfiguration.setAccessible(true);
		methodUpdateConfiguration.invoke(amn, config);
	}
}
