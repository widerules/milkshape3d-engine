package com.project.ms3d;




public class JOINT{

	public char		Flags;
	public String		Names;		// byte 32
	public String		ParentName;	// byte 32
	public float		Rotation[] = new float[3];
	public float		Position[] = new float[3];
	public int		Parent;

	public short		Num_KeyFramesRot;
	public short		Num_KeyFramesTrans;

	KEYFRAME     []		KeyFramesRot;
	KEYFRAME     []		KeyFramesTrans;
	
	public JOINT(){

	}
}


class KEYFRAME{
	public float		Time;
	public float		Vertex[] = new float[3];
}



