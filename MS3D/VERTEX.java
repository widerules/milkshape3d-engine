package com.project.ms3d;


public class VERTEX{
	private char    Flag;
	public  float   Vertex[] = new float[3];
	public char     BoneId;
	public char     RefCount;

	public VERTEX(){

	}

	public void Setup(char Flag, float [] Vertex, char BoneId, char RefCount){
		this.Flag     = Flag;
		this.Vertex   = Vertex; 
		this.BoneId   = BoneId;
		this.RefCount = RefCount;
	} 
}
