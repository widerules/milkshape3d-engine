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


public class Grid 
{
 private FloatBuffer   vertexBuffer;


 private float vertices[] = 
 { 


  -150.0f, 000.0f,140.0f,  //Bottom Left
   150.0f, 000.0f,140.0f,  //Bottom Right

  -150.0f, 000.0f,130.0f,  //Bottom Left
   150.0f, 000.0f,130.0f,  //Bottom Right

  -150.0f, 000.0f,120.0f,  //Bottom Left
   150.0f, 000.0f,120.0f,  //Bottom Right

  -150.0f, 000.0f,110.0f,  //Bottom Left
   150.0f, 000.0f,110.0f,  //Bottom Right

  -150.0f, 000.0f,100.0f,  //Bottom Left
   150.0f, 000.0f,100.0f,  //Bottom Right

  -150.0f, 000.0f, 90.0f,  //Bottom Left
   150.0f, 000.0f, 90.0f,  //Bottom Right

  -150.0f, 000.0f, 80.0f,  //Bottom Left
   150.0f, 000.0f, 80.0f,  //Bottom Right

  -150.0f, 000.0f, 70.0f,  //Bottom Left
   150.0f, 000.0f, 70.0f,  //Bottom Right

  -150.0f, 000.0f, 60.0f,  //Bottom Left
   150.0f, 000.0f, 60.0f,  //Bottom Right

  -150.0f, 000.0f, 50.0f,  //Bottom Left
   150.0f, 000.0f, 50.0f,  //Bottom Right

  -150.0f, 000.0f, 40.0f,  //Bottom Left
   150.0f, 000.0f, 40.0f,  //Bottom Right

  -150.0f, 000.0f, 30.0f,  //Bottom Left
   150.0f, 000.0f, 30.0f,  //Bottom Right

  -150.0f, 000.0f, 20.0f,  //Bottom Left
   150.0f, 000.0f, 20.0f,  //Bottom Right

  -150.0f, 000.0f, 10.0f,  //Bottom Left
   150.0f, 000.0f, 10.0f,  //Bottom Right

  -150.0f, 000.0f, 00.0f,  //Bottom Left
   150.0f, 000.0f, 00.0f,  //Bottom Right

  -150.0f, 000.0f,-10.0f,  //Bottom Left
   150.0f, 000.0f,-10.0f,  //Bottom Right

  -150.0f, 000.0f,-20.0f,  //Bottom Left
   150.0f, 000.0f,-20.0f,  //Bottom Right

  -150.0f, 000.0f,-30.0f,  //Bottom Left
   150.0f, 000.0f,-30.0f,  //Bottom Right

  -150.0f, 000.0f,-40.0f,  //Bottom Left
   150.0f, 000.0f,-40.0f,  //Bottom Right

  -150.0f, 000.0f,-50.0f,  //Bottom Left
   150.0f, 000.0f,-50.0f,  //Bottom Right

  -150.0f, 000.0f,-60.0f,  //Bottom Left
   150.0f, 000.0f,-60.0f,  //Bottom Right

  -150.0f, 000.0f,-70.0f,  //Bottom Left
   150.0f, 000.0f,-70.0f,  //Bottom Right

  -150.0f, 000.0f,-80.0f,  //Bottom Left
   150.0f, 000.0f,-80.0f,  //Bottom Right

  -150.0f, 000.0f,-90.0f,  //Bottom Left
   150.0f, 000.0f,-90.0f,  //Bottom Right

  -150.0f, 000.0f,-100.0f,  //Bottom Left
   150.0f, 000.0f,-100.0f,  //Bottom Right

  -150.0f, 000.0f,-110.0f,  //Bottom Left
   150.0f, 000.0f,-110.0f,  //Bottom Right

  -150.0f, 000.0f,-120.0f,  //Bottom Left
   150.0f, 000.0f,-120.0f,  //Bottom Right

  -150.0f, 000.0f,-130.0f,  //Bottom Left
   150.0f, 000.0f,-130.0f,  //Bottom Right

  -150.0f, 000.0f,-140.0f,  //Bottom Left
   150.0f, 000.0f,-140.0f,  //Bottom Right





   140.0f, 000.0f, 150.0f,  //Bottom Left
   140.0f, 000.0f,-150.0f,  //Bottom Right

   130.0f, 000.0f, 150.0f,  //Bottom Left
   130.0f, 000.0f,-150.0f,  //Bottom Right

   120.0f, 000.0f, 150.0f,  //Bottom Left
   120.0f, 000.0f,-150.0f,  //Bottom Right

   110.0f, 000.0f, 150.0f,  //Bottom Left
   110.0f, 000.0f,-150.0f,  //Bottom Right

   100.0f, 000.0f, 150.0f,  //Bottom Left
   100.0f, 000.0f,-150.0f,  //Bottom Right

   90.0f, 000.0f, 150.0f,  //Bottom Left
   90.0f, 000.0f,-150.0f,  //Bottom Right

   80.0f, 000.0f, 150.0f,  //Bottom Left
   80.0f, 000.0f,-150.0f,  //Bottom Right

   70.0f, 000.0f, 150.0f,  //Bottom Left
   70.0f, 000.0f,-150.0f,  //Bottom Right

   60.0f, 000.0f, 150.0f,  //Bottom Left
   60.0f, 000.0f,-150.0f,  //Bottom Right

   50.0f, 000.0f, 150.0f,  //Bottom Left
   50.0f, 000.0f,-150.0f,  //Bottom Right

   40.0f, 000.0f, 150.0f,  //Bottom Left
   40.0f, 000.0f,-150.0f,  //Bottom Right

   30.0f, 000.0f, 150.0f,  //Bottom Left
   30.0f, 000.0f,-150.0f,  //Bottom Right

   20.0f, 000.0f, 150.0f,  //Bottom Left
   20.0f, 000.0f,-150.0f,  //Bottom Right

   10.0f, 000.0f, 150.0f,  //Bottom Left
   10.0f, 000.0f,-150.0f,  //Bottom Right

   00.0f, 000.0f, 150.0f,  //Bottom Left
   00.0f, 000.0f,-150.0f,  //Bottom Right

  -10.0f, 000.0f, 150.0f,  //Bottom Left
  -10.0f, 000.0f,-150.0f,  //Bottom Right

  -20.0f, 000.0f, 150.0f,  //Bottom Left
  -20.0f, 000.0f,-150.0f,  //Bottom Right

  -30.0f, 000.0f, 150.0f,  //Bottom Left
  -30.0f, 000.0f,-150.0f,  //Bottom Right

  -40.0f, 000.0f, 150.0f,  //Bottom Left
  -40.0f, 000.0f,-150.0f,  //Bottom Right

  -50.0f, 000.0f, 150.0f, //Bottom Left
  -50.0f, 000.0f,-150.0f,  //Bottom Right

  -60.0f, 000.0f, 150.0f,  //Bottom Left
  -60.0f, 000.0f,-150.0f,  //Bottom Right

  -70.0f, 000.0f, 150.0f,  //Bottom Left
  -70.0f, 000.0f,-150.0f,  //Bottom Right

  -80.0f, 000.0f, 150.0f,  //Bottom Left
  -80.0f, 000.0f,-150.0f,  //Bottom Right

  -90.0f, 000.0f, 150.0f,  //Bottom Left
  -90.0f, 000.0f,-150.0f,  //Bottom Right

  -100.0f, 000.0f, 150.0f,  //Bottom Left
  -100.0f, 000.0f,-150.0f,  //Bottom Right

  -110.0f, 000.0f, 150.0f,  //Bottom Left
  -110.0f, 000.0f,-150.0f,  //Bottom Right

  -120.0f, 000.0f, 150.0f,  //Bottom Left
  -120.0f, 000.0f,-150.0f,  //Bottom Right

  -130.0f, 000.0f, 150.0f,  //Bottom Left
  -130.0f, 000.0f,-150.0f,  //Bottom Right

  -140.0f, 000.0f, 150.0f,  //Bottom Left
  -140.0f, 000.0f,-150.0f,  //Bottom Right

  -150.0f, 000.0f,150.0f,  //Bottom Left  1
   150.0f, 000.0f,150.0f,  //Bottom Right

  -150.0f, 010.0f,150.0f,  //Bottom Left  2
   150.0f, 010.0f,150.0f,  //Bottom Right

  -150.0f, 020.0f,150.0f,  //Bottom Left  3
   150.0f, 020.0f,150.0f,  //Bottom Right

  -150.0f, 030.0f,150.0f,  //Bottom Left  4
   150.0f, 030.0f,150.0f,  //Bottom Right

  -150.0f, 040.0f,150.0f,  //Bottom Left  5
   150.0f, 040.0f,150.0f,  //Bottom Right

  -150.0f, 050.0f,150.0f,  //Bottom Left  6
   150.0f, 050.0f,150.0f,  //Bottom Right

  -150.0f, 060.0f,150.0f,  //Bottom Left  7
   150.0f, 060.0f,150.0f,  //Bottom Right

  -150.0f, 070.0f,150.0f,  //Bottom Left  8
   150.0f, 070.0f,150.0f,  //Bottom Right

  -150.0f, 080.0f,150.0f,  //Bottom Left  9
   150.0f, 080.0f,150.0f,  //Bottom Right

  -150.0f, 090.0f,150.0f,  //Bottom Left  10
   150.0f, 090.0f,150.0f,  //Bottom Right


  -150.0f, 000.0f,150.0f,  //Bottom Left  11
  -150.0f, 090.0f,150.0f,  //Bottom Right

  -140.0f, 000.0f,150.0f,  //Bottom Left  12
  -140.0f, 090.0f,150.0f,  //Bottom Right

  -130.0f, 000.0f,150.0f,  //Bottom Left  13
  -130.0f, 090.0f,150.0f,  //Bottom Right

  -120.0f, 000.0f,150.0f,  //Bottom Left  14
  -120.0f, 090.0f,150.0f,  //Bottom Right

  -110.0f, 000.0f,150.0f,  //Bottom Left  15
  -110.0f, 090.0f,150.0f,  //Bottom Right

  -100.0f, 000.0f,150.0f,  //Bottom Left  16
  -100.0f, 090.0f,150.0f,  //Bottom Right

  -90.0f, 000.0f,150.0f,  //Bottom Left   17
  -90.0f, 090.0f,150.0f,  //Bottom Right

  -80.0f, 000.0f,150.0f,  //Bottom Left   18
  -80.0f, 090.0f,150.0f,  //Bottom Right

  -70.0f, 000.0f,150.0f,  //Bottom Left   19
  -70.0f, 090.0f,150.0f,  //Bottom Right

  -60.0f, 000.0f,150.0f,  //Bottom Left   20
  -60.0f, 090.0f,150.0f,  //Bottom Right

  -50.0f, 000.0f,150.0f,  //Bottom Left   21
  -50.0f, 090.0f,150.0f,  //Bottom Right

  -40.0f, 000.0f,150.0f,  //Bottom Left   22
  -40.0f, 090.0f,150.0f,  //Bottom Right


  -30.0f, 000.0f,150.0f,  //Bottom Left   23
  -30.0f, 090.0f,150.0f,  //Bottom Right

  -20.0f, 000.0f,150.0f,  //Bottom Left   24
  -20.0f, 090.0f,150.0f,  //Bottom Right

  -10.0f, 000.0f,150.0f,  //Bottom Left   25
  -10.0f, 090.0f,150.0f,  //Bottom Right

   00.0f, 000.0f,150.0f,  //Bottom Left   26
   00.0f, 090.0f,150.0f,  //Bottom Right

   10.0f, 000.0f,150.0f,  //Bottom Left   27
   10.0f, 090.0f,150.0f,  //Bottom Right

   20.0f, 000.0f,150.0f,  //Bottom Left   28 
   20.0f, 090.0f,150.0f,  //Bottom Right

   30.0f, 000.0f,150.0f,  //Bottom Left   29
   30.0f, 090.0f,150.0f,  //Bottom Right

   40.0f, 000.0f,150.0f,  //Bottom Left   30
   40.0f, 090.0f,150.0f,  //Bottom Right

   50.0f, 000.0f,150.0f,  //Bottom Left   31
   50.0f, 090.0f,150.0f,  //Bottom Right

   60.0f, 000.0f,150.0f,  //Bottom Left   32
   60.0f, 090.0f,150.0f,  //Bottom Right

   70.0f, 000.0f,150.0f,  //Bottom Left   33
   70.0f, 090.0f,150.0f,  //Bottom Right
   
   80.0f, 000.0f,150.0f,  //Bottom Left   34
   80.0f, 090.0f,150.0f,  //Bottom Right

   90.0f, 000.0f,150.0f,  //Bottom Left   35
   90.0f, 090.0f,150.0f,  //Bottom Right

   100.0f, 000.0f,150.0f,  //Bottom Left   36
   100.0f, 090.0f,150.0f,  //Bottom Right

   110.0f, 000.0f,150.0f,  //Bottom Left   37
   110.0f, 090.0f,150.0f,  //Bottom Right

   120.0f, 000.0f,150.0f,  //Bottom Left   38
   120.0f, 090.0f,150.0f,  //Bottom Right

   130.0f, 000.0f,150.0f,  //Bottom Left   39
   130.0f, 090.0f,150.0f,  //Bottom Right

   140.0f, 000.0f,150.0f,  //Bottom Left   40
   140.0f, 090.0f,150.0f,  //Bottom Right

   150.0f, 000.0f,150.0f,  //Bottom Left   41 
   150.0f, 090.0f,150.0f,  //Bottom Right


  -150.0f, 000.0f,-150.0f,  //Bottom Left
   150.0f, 000.0f,-150.0f,  //Bottom Right

  -150.0f, 010.0f,-150.0f,  //Bottom Left
   150.0f, 010.0f,-150.0f,  //Bottom Right

  -150.0f, 020.0f,-150.0f,  //Bottom Left
   150.0f, 020.0f,-150.0f,  //Bottom Right

  -150.0f, 030.0f,-150.0f,  //Bottom Left
   150.0f, 030.0f,-150.0f,  //Bottom Right

  -150.0f, 040.0f,-150.0f,  //Bottom Left
   150.0f, 040.0f,-150.0f,  //Bottom Right

  -150.0f, 050.0f,-150.0f,  //Bottom Left
   150.0f, 050.0f,-150.0f,  //Bottom Right

  -150.0f, 060.0f,-150.0f,  //Bottom Left
   150.0f, 060.0f,-150.0f,  //Bottom Right

  -150.0f, 070.0f,-150.0f,  //Bottom Left
   150.0f, 070.0f,-150.0f,  //Bottom Right

  -150.0f, 080.0f,-150.0f,  //Bottom Left
   150.0f, 080.0f,-150.0f,  //Bottom Right

  -150.0f, 090.0f,-150.0f,  //Bottom Left
   150.0f, 090.0f,-150.0f,  //Bottom Right



  -150.0f, 000.0f,-150.0f,  //Bottom Left
  -150.0f, 090.0f,-150.0f,  //Bottom Right

  -140.0f, 000.0f,-150.0f,  //Bottom Left
  -140.0f, 090.0f,-150.0f,  //Bottom Right

  -130.0f, 000.0f,-150.0f,  //Bottom Left
  -130.0f, 090.0f,-150.0f,  //Bottom Right

  -120.0f, 000.0f,-150.0f,  //Bottom Left
  -120.0f, 090.0f,-150.0f,  //Bottom Right

  -110.0f, 000.0f,-150.0f,  //Bottom Left
  -110.0f, 090.0f,-150.0f,  //Bottom Right

  -100.0f, 000.0f,-150.0f,  //Bottom Left
  -100.0f, 090.0f,-150.0f,  //Bottom Right

  -90.0f, 000.0f,-150.0f,  //Bottom Left
  -90.0f, 090.0f,-150.0f,  //Bottom Right

  -80.0f, 000.0f,-150.0f,  //Bottom Left
  -80.0f, 090.0f,-150.0f,  //Bottom Right

  -70.0f, 000.0f,-150.0f,  //Bottom Left
  -70.0f, 090.0f,-150.0f,  //Bottom Right

  -60.0f, 000.0f,-150.0f,  //Bottom Left
  -60.0f, 090.0f,-150.0f,  //Bottom Right

  -50.0f, 000.0f,-150.0f,  //Bottom Left
  -50.0f, 090.0f,-150.0f,  //Bottom Right

  -40.0f, 000.0f,-150.0f,  //Bottom Left
  -40.0f, 090.0f,-150.0f,  //Bottom Right


  -30.0f, 000.0f,-150.0f,  //Bottom Left
  -30.0f, 090.0f,-150.0f,  //Bottom Right

  -20.0f, 000.0f,-150.0f,  //Bottom Left
  -20.0f, 090.0f,-150.0f,  //Bottom Right

  -10.0f, 000.0f,-150.0f,  //Bottom Left
  -10.0f, 090.0f,-150.0f,  //Bottom Right

   00.0f, 000.0f,-150.0f,  //Bottom Left
   00.0f, 090.0f,-150.0f,  //Bottom Right

   10.0f, 000.0f,-150.0f,  //Bottom Left
   10.0f, 090.0f,-150.0f,  //Bottom Right

   20.0f, 000.0f,-150.0f,  //Bottom Left
   20.0f, 090.0f,-150.0f,  //Bottom Right

   30.0f, 000.0f,-150.0f,  //Bottom Left
   30.0f, 090.0f,-150.0f,  //Bottom Right

   40.0f, 000.0f,-150.0f,  //Bottom Left
   40.0f, 090.0f,-150.0f,  //Bottom Right

   50.0f, 000.0f,-150.0f,  //Bottom Left
   50.0f, 090.0f,-150.0f,  //Bottom Right

   60.0f, 000.0f,-150.0f,  //Bottom Left
   60.0f, 090.0f,-150.0f,  //Bottom Right

   70.0f, 000.0f,-150.0f,  //Bottom Left
   70.0f, 090.0f,-150.0f,  //Bottom Right

   80.0f, 000.0f,-150.0f,  //Bottom Left
   80.0f, 090.0f,-150.0f,  //Bottom Right

   90.0f, 000.0f,-150.0f,  //Bottom Left
   90.0f, 090.0f,-150.0f,  //Bottom Right

   100.0f, 000.0f,-150.0f,  //Bottom Left
   100.0f, 090.0f,-150.0f,  //Bottom Right

   110.0f, 000.0f,-150.0f,  //Bottom Left
   110.0f, 090.0f,-150.0f,  //Bottom Right

   120.0f, 000.0f,-150.0f,  //Bottom Left
   120.0f, 090.0f,-150.0f,  //Bottom Right

   130.0f, 000.0f,-150.0f,  //Bottom Left
   130.0f, 090.0f,-150.0f,  //Bottom Right

   140.0f, 000.0f,-150.0f,  //Bottom Left
   140.0f, 090.0f,-150.0f,  //Bottom Right

   150.0f, 000.0f,-150.0f,  //Bottom Left
   150.0f, 090.0f,-150.0f,  //Bottom Right

   150.0f, 000.0f, 150.0f,  //Bottom Left
   150.0f, 000.0f,-150.0f,  //Bottom Right

   150.0f, 010.0f, 150.0f,  //Bottom Left
   150.0f, 010.0f,-150.0f,  //Bottom Right

   150.0f, 020.0f, 150.0f,  //Bottom Left
   150.0f, 020.0f,-150.0f,  //Bottom Right

   150.0f, 030.0f, 150.0f,  //Bottom Left
   150.0f, 030.0f,-150.0f,  //Bottom Right

   150.0f, 040.0f, 150.0f,  //Bottom Left
   150.0f, 040.0f,-150.0f,  //Bottom Right

   150.0f, 050.0f, 150.0f,  //Bottom Left
   150.0f, 050.0f,-150.0f,  //Bottom Right

   150.0f, 060.0f, 150.0f,  //Bottom Left
   150.0f, 060.0f,-150.0f,  //Bottom Right

   150.0f, 070.0f, 150.0f,  //Bottom Left
   150.0f, 070.0f,-150.0f,  //Bottom Right

   150.0f, 080.0f, 150.0f,  //Bottom Left
   150.0f, 080.0f,-150.0f,  //Bottom Right

   150.0f, 090.0f, 150.0f,  //Bottom Left
   150.0f, 090.0f,-150.0f,  //Bottom Right



   150.0f, 000.0f,150.0f,  //Bottom Left
   150.0f, 090.0f,150.0f,  //Bottom Right

   150.0f, 000.0f,140.0f,  //Bottom Left
   150.0f, 090.0f,140.0f,  //Bottom Right

   150.0f, 000.0f,130.0f,  //Bottom Left
   150.0f, 090.0f,130.0f,  //Bottom Right

   150.0f, 000.0f,120.0f,  //Bottom Left
   150.0f, 090.0f,120.0f,  //Bottom Right

   150.0f, 000.0f,110.0f,  //Bottom Left
   150.0f, 090.0f,110.0f,  //Bottom Right

   150.0f, 000.0f,100.0f,  //Bottom Left
   150.0f, 090.0f,100.0f,  //Bottom Right

   150.0f, 000.0f, 90.0f,  //Bottom Left
   150.0f, 090.0f, 90.0f,  //Bottom Right

   150.0f, 000.0f, 80.0f,  //Bottom Left
   150.0f, 090.0f, 80.0f,  //Bottom Right

   150.0f, 000.0f, 70.0f,  //Bottom Left
   150.0f, 090.0f, 70.0f,  //Bottom Right

   150.0f, 000.0f, 60.0f,  //Bottom Left
   150.0f, 090.0f, 60.0f,  //Bottom Right

   150.0f, 000.0f, 50.0f,  //Bottom Left
   150.0f, 090.0f, 50.0f,  //Bottom Right

   150.0f, 000.0f, 40.0f,  //Bottom Left
   150.0f, 090.0f, 40.0f,  //Bottom Right

   150.0f, 000.0f, 30.0f,  //Bottom Left
   150.0f, 090.0f, 30.0f,  //Bottom Right

   150.0f, 000.0f, 20.0f,  //Bottom Left
   150.0f, 090.0f, 20.0f,  //Bottom Right

   150.0f, 000.0f, 10.0f,  //Bottom Left
   150.0f, 090.0f, 10.0f,  //Bottom Right

   150.0f, 000.0f, 00.0f,  //Bottom Left
   150.0f, 090.0f, 00.0f,  //Bottom Right

   150.0f, 000.0f,-10.0f,  //Bottom Left
   150.0f, 090.0f,-10.0f,  //Bottom Right

   150.0f, 000.0f,-20.0f,  //Bottom Left
   150.0f, 090.0f,-20.0f,  //Bottom Right

   150.0f, 000.0f,-30.0f,  //Bottom Left
   150.0f, 090.0f,-30.0f,  //Bottom Right

   150.0f, 000.0f,-40.0f,  //Bottom Left
   150.0f, 090.0f,-40.0f,  //Bottom Right

   150.0f, 000.0f,-50.0f,  //Bottom Left
   150.0f, 090.0f,-50.0f,  //Bottom Right

   150.0f, 000.0f,-60.0f,  //Bottom Left
   150.0f, 090.0f,-60.0f,  //Bottom Right

   150.0f, 000.0f,-70.0f,  //Bottom Left
   150.0f, 090.0f,-70.0f,  //Bottom Right

   150.0f, 000.0f,-80.0f,  //Bottom Left
   150.0f, 090.0f,-80.0f,  //Bottom Right

   150.0f, 000.0f,-90.0f,  //Bottom Left
   150.0f, 090.0f,-90.0f,  //Bottom Right

   150.0f, 000.0f,-100.0f,  //Bottom Left
   150.0f, 090.0f,-100.0f,  //Bottom Right

   150.0f, 000.0f,-110.0f,  //Bottom Left
   150.0f, 090.0f,-110.0f,  //Bottom Right

   150.0f, 000.0f,-120.0f,  //Bottom Left
   150.0f, 090.0f,-120.0f,  //Bottom Right

   150.0f, 000.0f,-130.0f,  //Bottom Left
   150.0f, 090.0f,-130.0f,  //Bottom Right

   150.0f, 000.0f,-140.0f,  //Bottom Left
   150.0f, 090.0f,-140.0f,  //Bottom Right

   150.0f, 000.0f,-150.0f,  //Bottom Left
   150.0f, 090.0f,-150.0f,  //Bottom Right

  -150.0f, 000.0f, 150.0f,  //Bottom Left
  -150.0f, 000.0f,-150.0f,  //Bottom Right

  -150.0f, 010.0f, 150.0f,  //Bottom Left
  -150.0f, 010.0f,-150.0f,  //Bottom Right

  -150.0f, 020.0f, 150.0f,  //Bottom Left
  -150.0f, 020.0f,-150.0f,  //Bottom Right

  -150.0f, 030.0f, 150.0f,  //Bottom Left
  -150.0f, 030.0f,-150.0f,  //Bottom Right

  -150.0f, 040.0f, 150.0f,  //Bottom Left
  -150.0f, 040.0f,-150.0f,  //Bottom Right

  -150.0f, 050.0f, 150.0f,  //Bottom Left
  -150.0f, 050.0f,-150.0f,  //Bottom Right

  -150.0f, 060.0f, 150.0f,  //Bottom Left
  -150.0f, 060.0f,-150.0f,  //Bottom Right

  -150.0f, 070.0f, 150.0f,  //Bottom Left
  -150.0f, 070.0f,-150.0f,  //Bottom Right

  -150.0f, 080.0f, 150.0f,  //Bottom Left
  -150.0f, 080.0f,-150.0f,  //Bottom Right

  -150.0f, 090.0f, 150.0f,  //Bottom Left
  -150.0f, 090.0f,-150.0f,  //Bottom Right



  -150.0f, 000.0f,150.0f,  //Bottom Left
  -150.0f, 090.0f,150.0f,  //Bottom Right

  -150.0f, 000.0f,140.0f,  //Bottom Left
  -150.0f, 090.0f,140.0f,  //Bottom Right

  -150.0f, 000.0f,130.0f,  //Bottom Left
  -150.0f, 090.0f,130.0f,  //Bottom Right

  -150.0f, 000.0f,120.0f,  //Bottom Left
  -150.0f, 090.0f,120.0f,  //Bottom Right

  -150.0f, 000.0f,110.0f,  //Bottom Left
  -150.0f, 090.0f,110.0f,  //Bottom Right

  -150.0f, 000.0f,100.0f,  //Bottom Left
  -150.0f, 090.0f,100.0f,  //Bottom Right

  -150.0f, 000.0f, 90.0f,  //Bottom Left
  -150.0f, 090.0f, 90.0f,  //Bottom Right

  -150.0f, 000.0f, 80.0f,  //Bottom Left
  -150.0f, 090.0f, 80.0f,  //Bottom Right

  -150.0f, 000.0f, 70.0f,  //Bottom Left
  -150.0f, 090.0f, 70.0f,  //Bottom Right

  -150.0f, 000.0f, 60.0f,  //Bottom Left
  -150.0f, 090.0f, 60.0f,  //Bottom Right

  -150.0f, 000.0f, 50.0f,  //Bottom Left
  -150.0f, 090.0f, 50.0f,  //Bottom Right

  -150.0f, 000.0f, 40.0f,  //Bottom Left
  -150.0f, 090.0f, 40.0f,  //Bottom Right

  -150.0f, 000.0f, 30.0f,  //Bottom Left
  -150.0f, 090.0f, 30.0f,  //Bottom Right

  -150.0f, 000.0f, 20.0f,  //Bottom Left
  -150.0f, 090.0f, 20.0f,  //Bottom Right

  -150.0f, 000.0f, 10.0f,  //Bottom Left
  -150.0f, 090.0f, 10.0f,  //Bottom Right

  -150.0f, 000.0f, 00.0f,  //Bottom Left
  -150.0f, 090.0f, 00.0f,  //Bottom Right

  -150.0f, 000.0f,-10.0f,  //Bottom Left
  -150.0f, 090.0f,-10.0f,  //Bottom Right

  -150.0f, 000.0f,-20.0f,  //Bottom Left
  -150.0f, 090.0f,-20.0f,  //Bottom Right

  -150.0f, 000.0f,-30.0f,  //Bottom Left
  -150.0f, 090.0f,-30.0f,  //Bottom Right

  -150.0f, 000.0f,-40.0f,  //Bottom Left
  -150.0f, 090.0f,-40.0f,  //Bottom Right

  -150.0f, 000.0f,-50.0f,  //Bottom Left
  -150.0f, 090.0f,-50.0f,  //Bottom Right

  -150.0f, 000.0f,-60.0f,  //Bottom Left
  -150.0f, 090.0f,-60.0f,  //Bottom Right

  -150.0f, 000.0f,-70.0f,  //Bottom Left
  -150.0f, 090.0f,-70.0f,  //Bottom Right

  -150.0f, 000.0f,-80.0f,  //Bottom Left
  -150.0f, 090.0f,-80.0f,  //Bottom Right

  -150.0f, 000.0f,-90.0f,  //Bottom Left
  -150.0f, 090.0f,-90.0f,  //Bottom Right

  -150.0f, 000.0f,-100.0f,  //Bottom Left
  -150.0f, 090.0f,-100.0f,  //Bottom Right

  -150.0f, 000.0f,-110.0f,  //Bottom Left
  -150.0f, 090.0f,-110.0f,  //Bottom Right

  -150.0f, 000.0f,-120.0f,  //Bottom Left
  -150.0f, 090.0f,-120.0f,  //Bottom Right

  -150.0f, 000.0f,-130.0f,  //Bottom Left
  -150.0f, 090.0f,-130.0f,  //Bottom Right

  -150.0f, 000.0f,-140.0f,  //Bottom Left
  -150.0f, 090.0f,-140.0f,  //Bottom Right

  -150.0f, 000.0f,-150.0f,  //Bottom Left
  -150.0f, 090.0f,-150.0f,  //Bottom Right


 };


 public Grid()
 {
  ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
  byteBuf.order(ByteOrder.nativeOrder());

  vertexBuffer = byteBuf.asFloatBuffer();
  vertexBuffer.put(vertices);
  vertexBuffer.position(0);
 }	


 


 public void Draw(GL10 gl)
 {		
  gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
  gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
  //Draw the vertices as triangle strip

//  gl.glDrawArrays(GL10.GL_LINES, 0, vertices.length / 3);		  280 + 82 = 362 + 82 = 444
  gl.glDrawArrays(GL10.GL_LINES, 0, 444);

  //Disable the client state before leaving
  gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
 }

 public int getlength()
 {
  return vertices.length;
 }
}

//



















