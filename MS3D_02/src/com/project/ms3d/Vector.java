package com.project.ms3d;


public class Vector {
	public float X;
	public float Y;
	public float Z;

	public Vector(){
		X = 0.0f;
		Y = 0.0f;
		Z = 0.0f;
	}


	public float Magnitude(){
		return(float)Math.sqrt((X * X)+
				       (Y * Y)+
				       (Z * Z));
	}

	public void Normalize(){
		float nSize = Magnitude();
		X = X / nSize;
		Y = Y / nSize;
		Z = Z / nSize;
	}
}

