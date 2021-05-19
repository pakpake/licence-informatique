/*
 * MASC (Mobile Adaptive Software Components)
 * Copyright (c) 2001 VALORIA Laboratory, University of South Brittany, France.
 * All rights reserved.
 *
 * File    : $DataBufferizer.java$ 
 * Author  : $F. Guidec, P. $
 * Version : $1.1$
 * Date    : $Wed Mar 17 09:02:54 CET 2011$
 */

public class DataBufferizer {

	private static char[] digits = {'0','1','2','3','4','5','6','7','8','9',
		'A','B','C','D','E','F'};

	//------------------------------------------------------------
	public static boolean readBoolean(byte[] buffer, int offset) {
		return (buffer[offset] != 0);
	}

	//------------------------------------------------------------
	public static void writeBoolean(boolean v, byte[] buffer, int offset) {
		if (v) buffer[offset] = 1;
		else buffer[offset] = 0;
	}

	//------------------------------------------------------------
	public static byte readByte(byte[] buffer, int offset) {
		return buffer[offset];
	}

	//------------------------------------------------------------
	public static void writeByte(byte v, byte[] buffer, int offset) {
		buffer[offset] = v;
	}

	//------------------------------------------------------------
	public static short readUnsignedByte(byte[] buffer, int offset) {
		short	val = (short)(buffer[offset] & 0x00FF);
		return val;
	}

	//------------------------------------------------------------
	public static void writeUnsignedByte(short v, byte[] buffer, int offset) {
		buffer[offset] = (byte)(0x00FF & v);
	}

	//------------------------------------------------------------
	public static byte[] readByteArray(byte[] buffer, int offset, int length) {
		byte[] val = new byte[length];
		System.arraycopy(buffer,offset,val,0,length);
		return val;
	}

	//------------------------------------------------------------
	public static void writeByteArray(byte[] v, byte[] buffer, int offset, int length) {
		System.arraycopy(v,0,buffer,offset,length);
	}

	//------------------------------------------------------------
	public static char readChar(byte[] buffer, int offset) {
		int val = buffer[offset] << 8;
		val |= buffer[offset+1];
		return ((char)val);
	}

	//------------------------------------------------------------
	public static void writeChar(char v, byte[] buffer, int offset) {
		buffer[offset] = (byte)((v & 0xFF00) >> 8);
		buffer[offset+1] = (byte)((v & 0x00FF));		
	}

	//------------------------------------------------------------
	public static double readDouble(byte[] buffer, int offset) {
		return Double.longBitsToDouble(readLong(buffer, offset));
	}

	//------------------------------------------------------------
	public static void writeDouble(double v, byte[] buffer, int offset) {
		writeLong(Double.doubleToLongBits(v), buffer, offset);
	}

	//------------------------------------------------------------
	public static float readFloat(byte[] buffer, int offset)  {
		return Float.intBitsToFloat(readInt(buffer, offset));
	}

	//------------------------------------------------------------
	public static void writeFloat(float v, byte[] buffer, int offset) {
		writeInt(Float.floatToIntBits(v), buffer, offset);
	}

	//------------------------------------------------------------
	public static int readInt(byte[] buffer, int offset) {
		int value = ((int)buffer[offset] & 0xFF) << 24;
		value |= ((int)buffer[offset+1] & 0xFF) << 16;
		value |= ((int)buffer[offset+2] & 0xFF) << 8;
		value |= ((int)buffer[offset+3] & 0xFF);
		return value;
	}

	//------------------------------------------------------------
	public static void writeInt(int v, byte[] buffer, int offset) {
		buffer[offset] = (byte)(0xFF & (v >> 24));
		buffer[offset+1] = (byte)(0xFF & (v >> 16));
		buffer[offset+2] = (byte)(0xFF & (v >> 8));
		buffer[offset+3] = (byte)(0xFF & v);
	}

	//------------------------------------------------------------
	public static long readLong(byte[] buffer, int offset) {
		return ((long)readInt(buffer, offset) << 32) 
		| ((long)readInt(buffer, offset+4) & 0xffffffffL);
	}

	//------------------------------------------------------------
	public static void writeLong(long v, byte[] buffer, int offset) {
		int hiInt=(int)(v >> 32);
		int loInt=(int)(v & 0xFFFFFFFF);

		writeInt(hiInt, buffer, offset);
		writeInt(loInt, buffer, offset+4);
	}

	//------------------------------------------------------------
	public static short readShort(byte[] buffer, int offset) {
		short val = (short)((buffer[offset] & 0xFF) << 8);
		val |= ((short)buffer[offset+1] & 0xFF);
		return val;
	}

	//------------------------------------------------------------
	public static void writeShort(short v, byte[] buffer, int offset) {
		buffer[offset] = (byte)(0xFF & (v >> 8));
		buffer[offset+1] = (byte)(0xFF & v);
	}

	//------------------------------------------------------------
	public static short getBytesLenght(String str) {
		return (short)str.getBytes().length;
	}

	//------------------------------------------------------------
	public static String bufferToString(byte[] buffer) {
		return bufferToString(buffer, 0, buffer.length);
	}

	//------------------------------------------------------------
	public static String bufferToString(byte[] buffer, 
			int offset, 
			int length) {
		StringBuffer result = new StringBuffer();
		int max = (length+offset>buffer.length?buffer.length:length+offset);
		for (int i=offset; i<max; i++)
			result.append(hexa(buffer[i]) + " ");
		return result.toString();
	}

	//------------------------------------------------------------
	public static String bufferToBinaryString(byte[] buffer) {
		return bufferToBinaryString(buffer, 0, buffer.length);
	}

	//------------------------------------------------------------
	public static String bufferToBinaryString(byte[] buffer, 
			int offset, 
			int length) {
		StringBuffer result = new StringBuffer();
		int max = (length+offset>buffer.length?buffer.length:length+offset);
		for (int i=offset; i<max; i++)
			result.append(bin(buffer[i]) + " ");
		return result.toString();
	}

	//------------------------------------------------------------
	public static String hexa(byte v) {
		int low  = v & 0x0F;
		int high = (v & 0xF0) >> 4;
		return String.valueOf(digits[high]) + String.valueOf(digits[low]);
	}

	//------------------------------------------------------------
	public static String bin(byte v) {
		String s = "";
		for (int i=0;i<8;i++) {
			if ((v & 0x01) == 1) s = "1" + s; else s = "0" + s;
			v = (byte)(v >> 1);
		}
		return s;
	}

	//------------------------------------------------------------
	public static boolean compareBuffer(byte[] sourceBuffer, 
			int sourceOffset, 
			byte[] toBuffer, 
			int toOffset, 
			int length) {
		for (int i=0; i<length; i++)
			if (toBuffer[toOffset + i] != sourceBuffer[sourceOffset + i])
				return false;
		return true;
	}
}

