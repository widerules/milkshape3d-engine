package com.project.ms3d;


public class MATERIAL{
	public String	Name;			// 32 
	public float	Ambient[];
	public float	Diffuse[];
	public float	Specular[];
	public float	Emissive[];  
	public float	Transparency;
	public float	Shininess; 
	public char	Mode;
	public String	Texture;		// 128
	public String	AlphaMap;		// 128

	public MATERIAL{
		Ambient		= new float[4];
		Diffuse		= new float[4];
		Specular	= new float[4];
		Emissive	= new float[4];
	}
};
