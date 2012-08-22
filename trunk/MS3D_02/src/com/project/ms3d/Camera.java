package com.project.ms3d;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;



public class Camera 
{
 private Vector Pose;
 private Vector View;
 private Vector UpVx;

 public Camera()
 {
  Pose = new Vector();
  View = new Vector();
  UpVx = new Vector();

  Pose.X =  0.0f;  Pose.Y =  0.0f;  Pose.Z =-10.0f; 
  View.X =  0.0f;  View.Y =  0.0f;  View.Z =  0.0f; 
  UpVx.X =  0.0f;  UpVx.Y =  1.0f;  UpVx.Z =  0.0f; 
 }

 public void SetCamera(GL10 gl)
 {
  GLU.gluLookAt(gl, Pose.X, Pose.Y, Pose.Z, 
                    View.X, View.Y, View.Z, 
                    UpVx.X, UpVx.Y, UpVx.Z);
 }
 
 public void Move()
 {

 }

}

