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



public class Triad {
	private FloatBuffer   vertexBuffer;
	private FloatBuffer   textureBuffer;

	public  ByteBuffer    vbyteBuf;
	public  ByteBuffer    cbyteBuf;


	private float vertices[] = { 
		0.0f, 0.0f, 0.0f, //Bottom Left
		0.0f, 0.0f, 0.0f, //Bottom Right
		0.0f, 0.0f, 0.0f, //Top Left
	};


	private float texture[] = 
	{ 
		0.0f,  1.0f,
		1.0f,  1.0f,
		0.0f,  0.0f,
	 };

	public Triad(){
		cbyteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		cbyteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = cbyteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);

		vbyteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		vbyteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = vbyteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}	


	public void SetTexCoord(GL10 gl, float [] X, float [] Y){

		texture[0] = X[0];texture[1] = Y[0];
		texture[2] = X[1];texture[3] = Y[1];
		texture[4] = X[2];texture[5] = Y[2];

		textureBuffer.put(texture);
		textureBuffer.position(0);

		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);	
	}

	public void SetNormals(GL10 gl, float vNormals[][]){
		float s = 1.45f;

		vertices[0] = vNormals[0][0] * s;
		vertices[1] = vNormals[0][1] * s;
		vertices[2] = vNormals[0][2] * s;

		vertices[3] = vNormals[1][0] * s;
		vertices[4] = vNormals[1][1] * s; 
		vertices[5] = vNormals[1][2] * s;

		vertices[6] = vNormals[2][0] * s;
		vertices[7] = vNormals[2][1] * s;
		vertices[8] = vNormals[2][2] * s;

		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		gl.glNormalPointer(GL10.GL_FLOAT, 0, vertexBuffer);
	}


	public void Draw(GL10 gl, float x1, float y1, float z1
				, float x2, float y2, float z2
				, float x3, float y3, float z3){
		vertices[0] = x1;
		vertices[1] = y1;
		vertices[2] = z1;

		vertices[3] = x2;
		vertices[4] = y2; 
		vertices[5] = z2;

		vertices[6] = x3;
		vertices[7] = y3;
		vertices[8] = z3;

		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);		
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
	}

}
