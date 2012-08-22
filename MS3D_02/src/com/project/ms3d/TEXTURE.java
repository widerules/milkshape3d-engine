package com.project.ms3d;

import java.io.IOException;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import java.io.InputStream;
import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLUtils;


public class TEXTURE{

	public int[]         textures = new int[1];


	public TEXTURE(){

	}


	public void loadGLTexture(GL10 gl, int id, Context context){
		InputStream is = context.getResources().openRawResource(id);
		Log.d( "Stream ", "Loading images");
	
		Bitmap bitmap = null;

		try {
			//BitmapFactory is an Android graphics utility for images
			Log.d( "Stream ", "Trying to decode");
			bitmap = BitmapFactory.decodeStream(is);
		} 
		finally {
			//Always clear and close
			try {
				is.close();
				is = null;
			} 
			catch (IOException e) { 
			Log.d( "Stream ", "is FUCKED ");
			}
		}

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glGenTextures(1, textures, 0);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);


		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}
}
