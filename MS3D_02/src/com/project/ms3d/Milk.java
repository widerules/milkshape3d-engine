package com.project.ms3d;

import android.app.Activity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;



public class Milk extends Activity{
    /** Called when the activity is first created. */

	private Core		core;

	@Override
	public void onCreate(Bundle savedInstanceState){

	        super.onCreate(savedInstanceState);

		core = new Core(this);
		setContentView(core);
	}


	@Override protected void onResume() {
		super.onResume();
		core.onResume();
	}


	@Override protected void onPause() {
		super.onPause();
		core.onPause();
	}
}
