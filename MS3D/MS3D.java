package com.project.ms3d;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.lang.String;
import java.lang.Object;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import android.content.Context;
import java.io.BufferedReader;

import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;




public class MS3D{
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

	private Triad			tri;

	private VERTEX			Vertices[];
	private TRIANGLE		Triangles[];
	private MESH			Meshes[];
	public  MATERIAL		Materials[];	
	private TEXTURE			Textures[];


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

			Vertices[i].Flag      = (char)Is.read();

			Is.read(Vertexs);
			aa[0] = jt.toFloat(Vertexs);

			Is.read(Vertexs);
			aa[1] = jt.toFloat(Vertexs);

			Is.read(Vertexs);
			aa[2] = jt.toFloat(Vertexs);

			Vertices[i].Vertex = aa;

			Vertices[i].BoneId   = (char)Is.read();
			Vertices[i].RefCount = (char)Is.read();
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
			VertexNormals[0]    = jt.toFloatArray( VertexsNrmx, 3);
			VertexNormals[1]    = jt.toFloatArray( VertexsNrmy, 3);
			VertexNormals[2]    = jt.toFloatArray( VertexsNrmz, 3);


			// read S/T
			float Sx[] = new float[3];
			Sx = jt.toFloatArray( S, 3);

			float Tx[] = new float[3];
			Tx = jt.toFloatArray( T, 3);

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


	public void LoadMaterials() throws IOException{
		byte matBuf[]	= new byte[2];

		Is.read(matBuf);

		num_materials	= jt.toShort( matBuf);
		Materials	= new MATERIAL[num_materials];
		Textures	= new TEXTURE[num_materials];

		for(int i = 0; i < num_materials; i++){
			String	mName;
			byte	ffbit[]		= new byte[4];			
			byte	xxbit[]		= new byte[16];
			byte	nxbit[]		= new byte[32];
			byte	XLbit[]		= new byte[128];
			
			Materials[i]		= new MATERIAL();
			Textures[i]		= new TEXTURE();

			Is.read(nxbit);
			Materials[i].Name = new String(nxbit);	

			Is.read(xxbit);
			Materials[i].Ambient		= jt.toFloatArray( xxbit, 4);
		
			Is.read(xxbit);
			Materials[i].Diffuse		= jt.toFloatArray( xxbit, 4);
	
			Is.read(xxbit);
			Materials[i].Specular		= jt.toFloatArray( xxbit, 4);

			Is.read(xxbit);
			Materials[i].Emissive		= jt.toFloatArray( xxbit, 4);

			Is.read(ffbit);
			Materials[i].Transparency	= jt.toFloat(ffbit);			
	
			Is.read(ffbit);
			Materials[i].Shininess		= jt.toFloat(ffbit);

			Materials[i].Mode		= (char)Is.read();

			Is.read(XLbit);
			Materials[i].Texture		= new String(XLbit);

			Is.read(XLbit);
			Materials[i].AlphaMap		= new String(XLbit);
		}	
	}	
	

	public void LoadImg(int index, GL10 gl){
		System.out.println(">>>> Load Image <<<<");

		StringTokenizer Str = new StringTokenizer(Materials[index].Texture, ".");

		String x   = new Integer(Str.countTokens()).toString();
		System.out.println("Tokens Count: " +x);
	
		String Path = "R.drawable.";
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
			RenderMaterials(gl, m);
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


















