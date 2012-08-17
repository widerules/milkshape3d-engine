package com.project.ms3d;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.lang.String;
import java.lang.Object;
import java.io.InputStreamReader;

import android.content.Context;
import java.io.BufferedReader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MS3D{
	private Context			context;
	private boolean			bActive;
	private InputStream		Is;

	private Javatypes		jt;

	private short           	num_materials;

	public String			ID; // our MS3D header
	public int			version;
	public short			num_vertex;
	public short			num_triangles;
	public short			num_meshes;

	private Triad			tri;

	private VERTEX			Vertices[];
	private TRIANGLE		Triangles[];
	private MESH			Meshes[];


	public MS3D(Context context){
		this.context	= context;
		bActive 	= false;  
		version		= 0;
		num_vertex	= 0;
		num_triangles	= 0;
		num_meshes	= 0;

		tri		= new Triad();
	} 


	public boolean Load( int id) throws IOException {
		Is = context.getResources().openRawResource(id);

		bActive = true;
		return bActive;
	}


	public void Close() throws IOException{
		if(bActive){
			Is.close();
			Is = null;
		}
	}


	public void LoadHeader() throws IOException {
		// read MS3D Header
		byte header[] = new byte[10];
		Is.read(header);
		ID = new String(header);

		// read MS3D Version
		byte versionBuf[] = new byte[4];
		Is.read(versionBuf);
		version = jt.toInt( versionBuf);
	}


	public void LoadVertex() throws IOException{
		byte vertexBuf[] = new byte[2];

		Is.read(vertexBuf);
		num_vertex = jt.toShort( vertexBuf);

		Vertices = new VERTEX[num_vertex];

		for(int i = 0; i < num_vertex; i++){
			Vertices[i]      = new VERTEX();
			byte   Vertexs[] = new byte[4];
			float  aa[]      = new float[3]; 

			char   Flag      = (char)Is.read();

			Is.read(Vertexs);
			aa[0] = jt.toFloat(Vertexs);

			Is.read(Vertexs);
			aa[1] = jt.toFloat(Vertexs);

			Is.read(Vertexs);
			aa[2] = jt.toFloat(Vertexs);

			char BoneId   = (char)Is.read();
			char RefCount = (char)Is.read();

			Vertices[i].Setup(Flag, aa, BoneId, RefCount);
		}
	}


	public void LoadTriangles() throws IOException{
		byte triBuf[] = new byte[2];

		Is.read(triBuf);
		num_triangles = jt.toShort( triBuf);

		Triangles = new TRIANGLE[num_triangles];

		for(int i = 0; i < num_triangles; i++){
			Triangles[i] = new TRIANGLE();

			byte VertexsFlg[]   = new byte[2];
			byte VertexsInd[]   = new byte[6];

			byte VertexsNrmx[]  = new byte[12];
			byte VertexsNrmy[]  = new byte[12];
			byte VertexsNrmz[]  = new byte[12];

			byte S[]            = new byte[12];
			byte T[]            = new byte[12];

			Is.read(VertexsFlg);
			Is.read(VertexsInd);

			Is.read(VertexsNrmx);
			Is.read(VertexsNrmy);
			Is.read(VertexsNrmz);

			Is.read(S);
			Is.read(T);

			// read Flag
			short Flag = jt.toShort(VertexsFlg);

			// read Indices
			short Ind[] = new short[3];
			Ind[0] = jt.toShort(VertexsInd[0], VertexsInd[1]);
			Ind[1] = jt.toShort(VertexsInd[2], VertexsInd[3]);
			Ind[2] = jt.toShort(VertexsInd[4], VertexsInd[5]);

			// read vertexNormals
			float VertexNormals[][] = new float[3][3];
			VertexNormals[0][0] = jt.toFloat( VertexsNrmx[0], VertexsNrmx[1], VertexsNrmx[2],  VertexsNrmx[3]);
			VertexNormals[0][1] = jt.toFloat( VertexsNrmx[4], VertexsNrmx[5], VertexsNrmx[6],  VertexsNrmx[7]);
			VertexNormals[0][2] = jt.toFloat( VertexsNrmx[8], VertexsNrmx[9], VertexsNrmx[10], VertexsNrmx[11]);

			VertexNormals[1][0] = jt.toFloat( VertexsNrmy[0], VertexsNrmy[1], VertexsNrmy[2],  VertexsNrmy[3]);
			VertexNormals[1][1] = jt.toFloat( VertexsNrmy[4], VertexsNrmy[5], VertexsNrmy[6],  VertexsNrmy[7]);
			VertexNormals[1][2] = jt.toFloat( VertexsNrmy[8], VertexsNrmy[9], VertexsNrmy[10], VertexsNrmy[11]);

			VertexNormals[2][0] = jt.toFloat( VertexsNrmz[0], VertexsNrmz[1], VertexsNrmz[2],  VertexsNrmz[3]);
			VertexNormals[2][1] = jt.toFloat( VertexsNrmz[4], VertexsNrmz[5], VertexsNrmz[6],  VertexsNrmz[7]);
			VertexNormals[2][2] = jt.toFloat( VertexsNrmz[8], VertexsNrmz[9], VertexsNrmz[10], VertexsNrmz[11]);

			// read S/T
			float Sx[] = new float[3];
			Sx[0] = jt.toFloat( S[0], S[1], S[2],  S[3]);
			Sx[1] = jt.toFloat( S[4], S[5], S[6],  S[7]);
			Sx[2] = jt.toFloat( S[8], S[9], S[10], S[11]);

			float Tx[] = new float[3];
			Tx[0] = jt.toFloat( T[0], T[1], T[2],  T[3]);
			Tx[1] = jt.toFloat( T[4], T[5], T[6],  T[7]);
			Tx[2] = jt.toFloat( T[8], T[9], T[10], T[11]);

			char SmoothingGroup   = (char)Is.read();
			char GroupIndex       = (char)Is.read();

			Triangles[i].Setup(Flag, Ind, VertexNormals, Sx, Tx, SmoothingGroup, GroupIndex);
		}
	}


	public void LoadMeshes() throws IOException{
		byte meshBuf[] = new byte[2];

		Is.read(meshBuf);
		num_meshes = jt.toShort( meshBuf);
		Meshes = new MESH[num_meshes];

		for(int i = 0; i < num_meshes; i++){
			Meshes[i] = new MESH();
			String MeshName;
			byte   Name[]     = new byte[32];
			byte   NumTri[]   = new byte[2];
			byte   TriInd[];

			char Flag = (char)Is.read(); 

			// read Mesh name
			Is.read(Name);
			MeshName = new String(Name);

			// read numbers of triangle 
			Is.read(NumTri);

			short Num_Tri = jt.toShort(NumTri);

			TriInd = new byte[Num_Tri * 2];
			Is.read(TriInd);
			short Ind[] = new short[Num_Tri];  

			for(int c = 0; c < Num_Tri; c++){
				Ind[c] = jt.toShort( TriInd[(c * 2)], TriInd[1 + (c * 2) ]); 
			}
   
			char mIndex = (char)Is.read();

			Meshes[i].Setup(Flag, MeshName, Num_Tri, Ind, mIndex);
		}
	}





	public void Draw(GL10 gl){
		for(int m = 0; m < num_meshes; m++){
			for(int t = 0; t < Meshes[m].NumTriangles; t++){

				short triIndices = Meshes[m].TrianglesIndices[t];
				
				short I1 = Triangles[triIndices].VertexIndices[0]; 
				short I2 = Triangles[triIndices].VertexIndices[1]; 
				short I3 = Triangles[triIndices].VertexIndices[2]; 


				tri.SetNormals(gl, Triangles[triIndices].VertexNormals);
				tri.Draw(gl,  Vertices[I1].Vertex[0], Vertices[I1].Vertex[1], Vertices[I1].Vertex[2], 						      Vertices[I2].Vertex[0], Vertices[I2].Vertex[1], Vertices[I2].Vertex[2], 						      Vertices[I3].Vertex[0], Vertices[I3].Vertex[1], Vertices[I3].Vertex[2]);
			}	
		}
	}


}


















