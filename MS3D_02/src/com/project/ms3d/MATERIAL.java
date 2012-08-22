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


	public MATERIAL(){
		Ambient		= new float[4];
		Diffuse		= new float[4];
		Specular	= new float[4];
		Emissive	= new float[4];
	}

	public void Setup(String Name, float [] Ambient, float [] Diffuse, float [] Specular, float [] Emissive,
			  float Transparency, float Shininess, char Mode, String Texture, String AlphaMap){
		this.Name		= Name;
		this.Ambient		= Ambient;
		this.Diffuse		= Diffuse;
		this.Specular		= Specular;
		this.Emissive		= Emissive;
		this.Transparency	= Transparency;
		this.Shininess		= Shininess;
		this.Mode		= Mode;
		this.Texture		= Texture;
		this.AlphaMap		= AlphaMap;
	}
};
