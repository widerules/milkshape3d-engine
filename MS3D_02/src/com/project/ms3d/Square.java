package com.project.ms3d;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;




public class Square 
{
 private FloatBuffer   vertexBuffer;
 private FloatBuffer   textureBuffer;
 private ByteBuffer    indexBuffer;

 private ByteBuffer    byteBuf;

 private int[]         textures = new int[1];

 private float vertices[] = 
 { 
   410.0f, 480.0f, 0.0f, //Bottom Left
   480.0f, 480.0f, 0.0f, //Bottom Right
   410.0f, 410.0f, 0.0f, //Top Left
   480.0f, 410.0f, 0.0f  //Top Right
 };


 private float texture[] = 
 { 
   0.0f,  1.0f,
   1.0f,  1.0f,
   0.0f,  0.0f,
   1.0f,  0.0f
 };





 public Square()
 {
  byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
  byteBuf.order(ByteOrder.nativeOrder());
  textureBuffer = byteBuf.asFloatBuffer();
  textureBuffer.put(texture);
  textureBuffer.position(0);

  byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
  byteBuf.order(ByteOrder.nativeOrder());
  vertexBuffer = byteBuf.asFloatBuffer();
  vertexBuffer.put(vertices);
  vertexBuffer.position(0);

 }	






 public void loadGLTexture(GL10 gl, int id, Context context)
 {
  InputStream is = context.getResources().openRawResource(id);
  Log.d( "Stream ", "Loading images");
	
  Bitmap bitmap = null;

  try 
  {
   //BitmapFactory is an Android graphics utility for images
   Log.d( "Stream ", "Trying to decode");
   bitmap = BitmapFactory.decodeStream(is);
  } 
  finally 
  {
   //Always clear and close
   try 
   {
    is.close();
    is = null;
   } 
   catch (IOException e) 
   { 
     Log.d( "Stream ", "is FUCKED ");
   }
  }

  gl.glEnable(GL10.GL_TEXTURE_2D);
  //Generate one texture pointer...
  gl.glGenTextures(1, textures, 0);
  //...and bind it to our array
  gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
  //Create Nearest Filtered Texture
  gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
  gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

  //Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
  gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
  gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
  //Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
  GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

  //Clean up
  bitmap.recycle();
 }


 public void Draw(GL10 gl, float x, float y, float width, float height)
 {
	

  float Width  = width  * 0.5f;
  float Height = height * 0.5f;

  vertices[0] = x - Width; vertices[ 1] =  y + Height;
  vertices[3] = x + Width; vertices[ 4] =  y + Height;
  vertices[6] = x - Width; vertices[ 7] =  y - Height;         
  vertices[9] = x + Width; vertices[10] =  y - Height;         

  vertexBuffer = byteBuf.asFloatBuffer();
  vertexBuffer.put(vertices);
  vertexBuffer.position(0);

  //Enable vertex buffer
  gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
  gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

  //Point to our vertex buffer
  gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
  gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);		
		
  //Draw the vertices as triangle strip
  gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

  gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		
 }

}

//



















