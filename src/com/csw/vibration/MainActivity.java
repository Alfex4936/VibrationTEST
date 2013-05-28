/*
 * Copyright (C) 2013 최석원
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.csw.vibration;

import com.csw.vibration.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Vibrator mVib;
	private static final String TAG = "Vibration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		if (!mVib.hasVibrator()) {
			Log.e(TAG, "This device has no vibration function");
			Toast.makeText(this, R.string.vibration_no,
					Toast.LENGTH_SHORT).show();
		}
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.settings, menu);
    	
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.system_settings:
    		Log.d(TAG, "Opening System Settings: " + item.getItemId());
    		Toast.makeText(this, R.string.system, Toast.LENGTH_SHORT).show();
    		Intent intent = new Intent(Settings.ACTION_SETTINGS);
    		startActivity(intent);
    		break;
    	case R.id.app_settings:
    		Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
    		Intent settings = new Intent(this, SettingsActivity.class);
    		startActivity(settings);
    		return true;
    	}
    	return false;
    }

    protected void onDestory() {
    	super.onDestroy();
    	mVib.cancel();
    }

    public void mOnClick(View v) {
    	switch (v.getId()) {
    	case R.id.button1:
    		Log.d(TAG, "Vibration One time");
			Toast.makeText(this, "진동을 한 번 울립니다.",
					Toast.LENGTH_SHORT).show();
    		mVib.vibrate(500);
    		break;
    	case R.id.button2:
    		Log.d(TAG, "Continue Vibration Activity");
			Toast.makeText(MainActivity.this, "진동을 계속 울립니다.",
					Toast.LENGTH_SHORT).show();
    		mVib.vibrate(new long[] {200, 300, 500, 400}, 0);
    		break;
    	case R.id.button3:
    		Log.w(TAG, "Canceling Vibration Activity");
			Toast.makeText(MainActivity.this, "진동을 취소 합니다.",
					Toast.LENGTH_SHORT).show();
    		mVib.cancel();
    		break;
    	case R.id.custom_image:
    		LinearLayout linear = (LinearLayout)View.inflate(MainActivity.this, R.layout.activity_image, null);
    		Toast csw = new Toast(this);
    		csw.setView(linear);
    		csw.show();
    		break;
    		
    	}
    }
    
}
