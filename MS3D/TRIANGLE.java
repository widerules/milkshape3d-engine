package com.project.ms3d;


public class TRIANGLE{
	public  short   VertexIndices[];
	public  float   VertexNormals[][];

	private short   Flag;
	private float   S[], T[];
	private char    SmoothingGroup;
	private char    GroupIndex;


	public TRIANGLE(){
		VertexIndices = new short[3];
		VertexNormals = new float[3][3];
		S             = new float[3];
		T             = new float[3];
	}

	public void Setup(short Flag, short [] VertexIndices, float [][] VertexNormals, float [] S, float [] T, 
		          char SmoothingGroup, char GroupIndex){
		this.Flag           = Flag;
		this.VertexIndices  = VertexIndices;
		this.VertexNormals  = VertexNormals;
		this.S              = S;   
		this.T              = T; 
		this.SmoothingGroup = SmoothingGroup;
		this.GroupIndex     = GroupIndex;
	} 

}
