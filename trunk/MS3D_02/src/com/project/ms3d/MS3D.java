package com.project.ms3d;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.lang.String;
import java.lang.Object;
import java.lang.Byte;
import java.util.StringTokenizer;

import android.content.Context;


import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;




public class MS3D{
/* 
	verander alle Public variable to private wanneer je klaar ben met
	testen
*/
	private byte[]			SHByte	= new byte[2];
	private byte[]			FLByte	= new byte[4];
	private byte[]			SXByte	= new byte[6];
	private byte[]			TWByte	= new byte[12];
	private byte[]			TSByte	= new byte[16];
	private byte[]			NXByte	= new byte[32];
	private byte[]			XLByte	= new byte[128];

	private Context			context;
	private boolean			bActive;
	private InputStream		Is;

	private Javatypes		jt;

	public String			ID; // our MS3D header
	public int			version;
	public short			num_vertex;
	public short			num_triangles;
	public short			num_meshes;
	public short			num_materials;
	public short			num_joints;

	public float			AnimationFPS;
	public float			CurrentTime;
	public int			TotalFrames;
	public short			Numb;

	private Triad			tri;

	private VERTEX			Vertices[];
	private TRIANGLE		Triangles[];
	private MESH			Meshes[];
	public  MATERIAL		Materials[];	
	private TEXTURE			Textures[];
	private JOINT			Joints[];


	public MS3D(Context context){
		this.context	= context;
		bActive 	= false;  
		version		= 0;
		num_vertex	= 0;
		num_triangles	= 0;
		num_meshes	= 0;
		num_materials	= 0;

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
		Is.read(FLByte);
		version = jt.toInt( FLByte);
	}


	public void LoadVertex() throws IOException{

		Is.read(SHByte);
		num_vertex = jt.toShort( SHByte);

		Vertices = new VERTEX[num_vertex];

		for(int i = 0; i < num_vertex; i++){
			Vertices[i]      = new VERTEX();
			float  aa[]      = new float[3]; 

			Vertices[i].Flag      = (char)Is.read();

/*
	probeer dit ook in een groep van te maken.
*/

			Is.read(FLByte);
			aa[0] = jt.toFloat(FLByte);

			Is.read(FLByte);
			aa[1] = jt.toFloat(FLByte);

			Is.read(FLByte);
			aa[2] = jt.toFloat(FLByte);

			Vertices[i].Vertex = aa;

			Vertices[i].BoneId   = (char)Is.read();
			Vertices[i].RefCount = (char)Is.read();
		}
	}


	public void LoadTriangles() throws IOException{

		Is.read(SHByte);
		num_triangles = jt.toShort( SHByte);

		Triangles = new TRIANGLE[num_triangles];

		for(int i = 0; i < num_triangles; i++){
			Triangles[i] = new TRIANGLE();

			Is.read(SHByte);

			// read Indices
			Is.read(SXByte);
			short Ind[] = new short[3];
			Ind[0] = jt.toShort(SXByte[0], SXByte[1]);
			Ind[1] = jt.toShort(SXByte[2], SXByte[3]);
			Ind[2] = jt.toShort(SXByte[4], SXByte[5]);


			// read vertexNormals
			float VertexNormals[][] = new float[3][3];
			Is.read(TWByte);
			VertexNormals[0]    = jt.toFloatArray( TWByte, 3);
			Is.read(TWByte);
			VertexNormals[1]    = jt.toFloatArray( TWByte, 3);
			Is.read(TWByte);
			VertexNormals[2]    = jt.toFloatArray( TWByte, 3);

			// read Flag
			short Flag = jt.toShort(SHByte);


			Is.read(TWByte);
			// read S/T
			float Sx[] = new float[3];
			Sx = jt.toFloatArray( TWByte, 3);

			Is.read(TWByte);
			float Tx[] = new float[3];
			Tx = jt.toFloatArray( TWByte, 3);

			char SmoothingGroup   = (char)Is.read();
			char GroupIndex       = (char)Is.read();

			Triangles[i].Setup(Flag, Ind, VertexNormals, Sx, Tx, SmoothingGroup, GroupIndex);
		}
	}



	public void LoadMeshes() throws IOException{

		Is.read(SHByte);
		num_meshes = jt.toShort( SHByte);
		Meshes = new MESH[num_meshes];

		for(int i = 0; i < num_meshes; i++){
			Meshes[i] = new MESH();
			String MeshName;

			byte   TriInd[];

			char Flag = (char)Is.read(); 

			// read Mesh name
			Is.read(NXByte);
			MeshName = new String(NXByte);

			// read numbers of triangle 
			Is.read(SHByte);
			short Num_Tri = jt.toShort(SHByte);

			TriInd = new byte[Num_Tri * 2];
			Is.read(TriInd);
			short Ind[] = new short[Num_Tri];  

			for(int c = 0; c < Num_Tri; c++){
				Ind[c] = jt.toShort( TriInd[(c * 2)], TriInd[1 + (c * 2) ]); 
			}
   
			char mIndex = (char)Is.read();
			System.out.println("Material index:"+mIndex);
//			System.out.println(mIndex);

			Meshes[i].Setup(Flag, MeshName, Num_Tri, Ind, mIndex);
		}
	}


	public void LoadMaterials() throws IOException{

		Is.read(SHByte);

		num_materials	= jt.toShort( SHByte);
		Materials	= new MATERIAL[num_materials];
		Textures	= new TEXTURE[num_materials];

		for(int i = 0; i < num_materials; i++){
			String	mName;			
			Materials[i]		= new MATERIAL();
			Textures[i]		= new TEXTURE();

			Is.read(NXByte);
			Materials[i].Name = new String(NXByte);	

			Is.read(TSByte);
			Materials[i].Ambient		= jt.toFloatArray( TSByte, 4);
		
			Is.read(TSByte);
			Materials[i].Diffuse		= jt.toFloatArray( TSByte, 4);
	
			Is.read(TSByte);
			Materials[i].Specular		= jt.toFloatArray( TSByte, 4);

			Is.read(TSByte);
			Materials[i].Emissive		= jt.toFloatArray( TSByte, 4);

			Is.read(FLByte);
			Materials[i].Transparency	= jt.toFloat(FLByte);			
	
			Is.read(FLByte);
			Materials[i].Shininess		= jt.toFloat(FLByte);

			Materials[i].Mode		= (char)Is.read();

			Is.read(XLByte);
			Materials[i].Texture		= new String(XLByte);

			Is.read(XLByte);
			Materials[i].AlphaMap		= new String(XLByte);
		}	
	}	
	
/*
	probeer van alle byte buff tot een enkel global buffs te maken.
	anders hoef je het niet steeds weer voor elke functie te roepen

-939524096
-939524096

*/
	public void LoadJoint() throws IOException{

		Is.read(FLByte);		// Animation FPS
		AnimationFPS		= jt.toFloat( FLByte);

		Is.read(FLByte);		// Current Time
		CurrentTime		= jt.toFloat( FLByte);

		Is.read(FLByte);		// Total Frames
		TotalFrames		= jt.toInt( FLByte);

		Is.read(SHByte);	// num Joints
		num_joints		= jt.toShort( SHByte);


	}


	public void LoadImg(int index, GL10 gl){
		System.out.println(">>>> Load Image <<<<");

		StringTokenizer Str = new StringTokenizer(Materials[index].Texture, ".");

		String x   = new Integer(Str.countTokens()).toString();
//		System.out.println("Tokens Count: " +x);
	
//		String Path = "R.drawable.";
//		String res  = Path.concat(Str.nextToken());
		String Name = Str.nextToken();
		System.out.println(Name);

		String Package = context.getPackageName();
		System.out.println(Package);

		int ID = context.getResources().getIdentifier( Name, "drawable", Package);
		x   = new Integer(ID).toString();

		System.out.println("Resource ID:");
		System.out.println(x);
		Textures[index].loadGLTexture(gl, ID, context);

	}


	public void RenderMaterials(GL10 gl, int Index){

			if(num_materials > 0){
				gl.glEnable(GL10.GL_TEXTURE_2D);
				gl.glBindTexture(GL10.GL_TEXTURE_2D, Textures[Index].textures[0]);
			}

			gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT,  Materials[Index].Ambient, 0);
			gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE,  Materials[Index].Diffuse, 0);
			gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, Materials[Index].Specular, 0);
			gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_EMISSION, Materials[Index].Emissive, 0);
			gl.glMaterialf (GL10.GL_FRONT, GL10.GL_SHININESS,Materials[Index].Shininess);

	}


	public void Draw(GL10 gl){
		for(int m = 0; m < num_meshes; m++){
			RenderMaterials(gl, (int)Meshes[m].MaterialIndex);
			for(int t = 0; t < Meshes[m].NumTriangles; t++){

				short triIndices = Meshes[m].TrianglesIndices[t];
				
				short I1 = Triangles[triIndices].VertexIndices[0]; 
				short I2 = Triangles[triIndices].VertexIndices[1]; 
				short I3 = Triangles[triIndices].VertexIndices[2]; 

				tri.SetTexCoord( gl, Triangles[triIndices].S, Triangles[triIndices].T);
				tri.SetNormals(gl, Triangles[triIndices].VertexNormals);
				tri.Draw(gl,  Vertices[I1].Vertex[0], Vertices[I1].Vertex[1], Vertices[I1].Vertex[2], 						      Vertices[I2].Vertex[0], Vertices[I2].Vertex[1], Vertices[I2].Vertex[2], 						      Vertices[I3].Vertex[0], Vertices[I3].Vertex[1], Vertices[I3].Vertex[2]);
			}	
			if(num_materials > 0){
				gl.glDisable(GL10.GL_TEXTURE_2D);
			}
		}
	}


}


















