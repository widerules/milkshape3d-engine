package com.project.ms3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import java.io.IOException;
import java.util.StringTokenizer;


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
//		gl.glClearDepthf(10.0f); 
		gl.glEnable(GL10.GL_DEPTH_TEST);

		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 

		gl.glCullFace(GL10.GL_BACK);
		gl.glEnable(GL10.GL_CULL_FACE);		

		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		Log.d( ">>> Starting up to load Images ", "..... <<<");
		for(int i = 0; i < milk.num_materials; i++){
			milk.LoadImg(i, gl);	
		}
		Log.d( ">>> Finished up to load Images ", "..... <<<");
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}


	public void onDrawFrame(GL10 gl){

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		gl.glLoadIdentity();

		

		cam.SetCamera(gl);

		gl.glColor4f( 0, 1, 0, 1);
		gl.glPushMatrix();
		gl.glTranslatef( 0.0f, -7.0f, 0.0f);	
		gl.glRotatef( 45, 0, 1, 0);
		grid.Draw(gl);
		gl.glPopMatrix();


		gl.glPushMatrix();
//		gl.glScalef( 0.25f, 0.25f, 0.25f);
		gl.glTranslatef(0.0f, -8.5f, 10.0f);		//Move up 2.5 Units
		gl.glRotatef( r, 0, 1, 0);


		gl.glEnable(GL10.GL_LIGHTING);
		gl.glFrontFace(GL10.GL_CW);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glColor4f( 1, 1, 1, 1);
		milk.Draw(gl);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glDisable(GL10.GL_LIGHTING);

		gl.glPopMatrix();

		

		r += 0.35f;

	}

	public void LoadZombie(){
		Log.d( ">>> Starting up to load Zombie.ms3d ", ".....");
		try{
			Log.d( ">>> Log:	 1", "Open Zombie File");
//			milk.Load( R.drawable.dwarf1);
			milk.Load( R.drawable.model);
			milk.Load( R.drawable.zombie);
		}
		catch(IOException e){ Log.d( "Log: Error", "error opening file?"); }		

		String br = "";

		try{
			milk.LoadHeader();    
				     Log.d( "Log:	 2", " Header ID:"+milk.ID);
				     Log.d( "Log:	 3", " MS3D version:"+milk.version);
			milk.LoadVertex();
				     Log.d( "Log:	 4", " Numbers of vertices:"+milk.num_vertex);
			milk.LoadTriangles();
				     Log.d( "Log:	 5", " Numbers of Triangles:"+milk.num_triangles);
			milk.LoadMeshes();
				     Log.d( "Log:	 6", " Numbers of Meshes:"+milk.num_meshes);
			milk.LoadMaterials();
				     Log.d( "Log:	 7", " Numbers of Materials:"+milk.num_materials);
			milk.LoadJoint();
				     Log.d( "Log:	 8", " Numbers of Joints:"+milk.num_joints);
				     Log.d( "Log:	 9", " Total Frames:"	  +milk.TotalFrames);
				     Log.d( "Log:	10", " Total Frames:"	  +milk.Numb);

					if( milk.TotalFrames == milk.Numb){
					     Log.d( "Log:	11", " Equal");
					}else{
					     Log.d( "Log:	11", " Bad luck");
					}
//				for(int i = 0; i < milk.num_materials; i++){
//				     Log.d( "Log:	 X", " Materials Path:"+milk.Materials[i].Name);
//				     Log.d( "Path	 X", " Materials Path:"+milk.Materials[i].Texture);
//					milk.LoadImg(i);	
//				}	

			milk.Close();Log.d( "Log:	10", " Close File");
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

		GLU.gluPerspective(gl, 45.0f, Ratio, 0.75f, 1000.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); 
		gl.glEnable( GL10.GL_CULL_FACE); 
		gl.glCullFace( GL10.GL_FRONT); 
	}

}


