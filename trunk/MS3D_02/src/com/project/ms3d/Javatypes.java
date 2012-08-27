package com.project.ms3d;

import java.io.ByteArrayInputStream;
import java.util.StringTokenizer;




public class Javatypes{

	public static float toFloat(byte[] bytes){
		int accum = 0;
		int inc   = 0; 

		for( int shiftBy = 0; shiftBy < 32; shiftBy+=8 ){
			accum |= ( bytes[inc] & 0xff ) << shiftBy;
			inc++;
		}

		return Float.intBitsToFloat(accum);
	}


	public static float toFloat(byte A, byte B, byte C, byte D){
		int accum = 0;
		int inc   = 0; 

		byte array[] = new byte[4];
		array[0] = A; 
		array[1] = B; 
		array[2] = C; 
		array[3] = D; 

		for( int shiftBy = 0; shiftBy < 32; shiftBy+=8 ){
			accum |= ( array[inc] & 0xff ) << shiftBy;
			inc++;
		}

		return Float.intBitsToFloat(accum);
	}


	public static float[] toFloatArray(byte [] byteArray, int offset){

		float Result[] = new float[offset];
		int incr = 0;
		
		for(int i = 0; i < offset; i++){
			
			Result[i] = toFloat(  byteArray[0+incr],  byteArray[1+incr],  byteArray[2+incr],   byteArray[3+incr]);
			incr += 4;
		}

		return Result;
	}


	public static short toShort(byte[] bytes){
		int low  = bytes[0] & 0xff;
		int high = bytes[1];

		return(short)( high << 8 | low );
	}


	public static short toShort(byte A, byte B){
		int low  = A & 0xff;
		int high = B;

		return(short)( high << 8 | low );
	}

	public static int toInt(byte [] bytes){
		//return (bytes[0] << 24) | ((bytes[1] & 0xff) << 16) | ((bytes[2] & 0xff) << 8) | (bytes[3] & 0xff); 
		return( java.nio.ByteBuffer.wrap(bytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt());
	}

	public static byte[] intToByteArray(int a){
		byte[] ret = new byte[4];
		ret[0] = (byte) (a & 0xFF);   
		ret[1] = (byte) ((a >> 8) & 0xFF);   
		ret[2] = (byte) ((a >> 16) & 0xFF);   
		ret[3] = (byte) ((a >> 24) & 0xFF);

		return ret;
	}

	public static int byteArrayToInt(byte[] b) {
	    int value = 0;
	    for (int i = 0; i < 4; i++) {
		value = (value << 8) | (b[i] & 0xFF);
	    }
	    return value;
	}

/*
	public static int byteArrayToInt(byte[] b) {
	    int value = 0;
	    for (int i = 0; i < 4; i++) {
		value = (value << 8) | (b[i] & 0xFF);
	    }
	    return value;
	}
*/

}
