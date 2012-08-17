package com.project.ms3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import java.io.IOException;


public class Core extends GLSurfaceView implements Renderer{

	private Context 	context;
	private MS3D		milk;	

	private Grid		grid;
	private Camera		cam;
	private Triangle	triangle;

	private float		r;

	public Core(Context context){
		super(context);
		this.setRenderer(this);		
	
		milk 	= new MS3D(context);

		grid 	= new Grid();
		cam  	= new Camera();
		triangle= new Triangle();
		r	= 0.0f;

		LoadZombie();
	}

	public void onResume(){

	}


	public void onPause(){

	}

	
	public void onSurfaceCreated(GL10 gl, EGLConfig config){

		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glClearDepthf(1.0f); 
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 

		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
	}


	public void onDrawFrame(GL10 gl){

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		gl.glLoadIdentity();

		cam.SetCamera(gl);

		gl.glColor4f( 0, 1, 0, 1);
		gl.glPushMatrix();
		gl.glTranslatef( 0.0f, -7.0f, 0.0f);	
		grid.Draw(gl);
		gl.glPopMatrix();


		gl.glPushMatrix();
//		gl.glScalef( 0.25f, 0.25f, 0.25f);
		gl.glTranslatef(0.0f, -8.5f, 20.0f);		//Move up 2.5 Units
		gl.glRotatef( r, 0, 1, 0);


		gl.glEnable(GL10.GL_LIGHTING);
		gl.glFrontFace(GL10.GL_CW);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		gl.glColor4f( 1, 1, 1, 1);
		milk.Draw(gl);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisable(GL10.GL_LIGHTING);

		gl.glPopMatrix();

		

		r += 0.15f;

	}

	public void LoadZombie(){
		Log.d( ">>> Starting up to load Zombie.ms3d ", ".....");
		try{
			Log.d( ">>> Log: 0", "Open Zombie File");
//			milk.Load( R.drawable.model);
			milk.Load( R.drawable.zombie);
		}
		catch(IOException e){ Log.d( "Log: Error", "error opening file?"); }		

		try{
			milk.LoadHeader();    
				     Log.d( "Log: 2", " Header ID:"+milk.ID);
				     Log.d( "Log: 3", " MS3D version:"+milk.version);
			milk.LoadVertex();
				     Log.d( "Log: 4", " Numbers of vertices:"+milk.num_vertex);
			milk.LoadTriangles();
				     Log.d( "Log: 5", " Numbers of Triangles:"+milk.num_triangles);
			milk.LoadMeshes();
				     Log.d( "Log: 6", " Numbers of Meshes:"+milk.num_meshes);
			milk.Close();Log.d( "Log: 7", "Close File");
		} 
		catch(IOException e){ Log.d( "Log: Error", "error Reading file?");}
	}


	public void onSurfaceChanged(GL10 gl, int width, int height){

		if(height == 0) {
			height = 1;
		}

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity(); 

		float Ratio = (float)width / (float)height;

		GLU.gluPerspective(gl, 45.0f, Ratio, 0.1f, 1500.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); 
		gl.glEnable( GL10.GL_CULL_FACE); 
		gl.glCullFace( GL10.GL_FRONT); 
	}

}


