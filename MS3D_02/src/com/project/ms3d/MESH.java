package com.project.ms3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class MESH{
	private char       Flags;
	String             Name;
	public short       NumTriangles;
	public short    [] TrianglesIndices;
	public char	   MaterialIndex;
	public float       Triangles[];
	private ByteBuffer indexBuffer;
	public byte        indices[];

	private ByteBuffer byteBuf;


	public MESH(){

	}


	public String GetName(){
		return Name;
	}


	public void Setup(char Flags, String Name, short NumTriangles, short [] TrianglesIndices, char MaterialIndex){
		this.Flags         = Flags;
		this.Name          = Name;
		this.NumTriangles  = NumTriangles;

		this.TrianglesIndices = new short[this.NumTriangles];
		this.TrianglesIndices = TrianglesIndices; 

		Triangles             = new float[this.NumTriangles * 9];

		//  byte indices[] = new byte[this.NumTriangles*3];

		byteBuf = ByteBuffer.allocateDirect(Triangles.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());

		indices = new byte[this.NumTriangles*3];

		for(int i = 0; i < this.NumTriangles*3; i++){
			indices[i] = (byte)i;
		}

		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);

		this.MaterialIndex = MaterialIndex;
 	}

}






