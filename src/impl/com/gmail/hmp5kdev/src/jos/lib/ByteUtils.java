package com.gmail.hmp5kdev.src.jos.lib;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * @author Cudmore, Hannes103
 * @see <a href="http://www.daniweb.com/software-development/java/code/216874/primitive-types-as-byte-arrays">Byte Converter Source</a>
 * @version 1.0.1
 */
public final class ByteUtils {
	
	public static final long     IEC_KiB     = 1024L;
	public static final long     IEC_MiB     = 1048576L;
	public static final long     IEC_GiB     = 1073741824L;
	public static final long     IEC_TiB     = 1099511627776L;
	public static final long     IEC_PiB     = 1125899906842624L;
	public static final long     IEC_EiB     = 1152921504606846976L;
	
	public static final long    BYTE_MASK    = 0x00000000000000FFL;
	public static final int     BYTE_SIZE    = 1;
	public static final byte    BYTE_MIN     = -128;
	public static final byte    BYTE_MAX     = 127;
	
	public static final long    BOOLEAN_MASK = 0x0000000000000001L;
	public static final int     BOOLEAN_SIZE = 1;
	public static final boolean BOOLEAN_MIN  = false;
	public static final boolean BOOLEA_NMAX  = true;
	
	public static final long    SHORT_MASK   = 0x000000000000FFFFL;
	public static final int     SHORT_SIZE   = 2;
	public static final short   SHORT_MIN    = -32768;
	public static final short   SHORT_MAX    = 32767;
	
	public static final long    CHAR_MASK    = 0x000000000000FFFFL;
	public static final int     CHAR_SIZE    = 2;
	public static final char    CHAR_MIN     = 0;
	public static final char    CHAR_MAX     = 65535;
	
	public static final long    FLOAT_MASK   = 0x00000000FFFFFFFFL;
	public static final int     FLOAT_SIZE   = 4;
	public static final float   FLOAT_MIN    = 0x0.000002P-126f;
	public static final float   FLOAT_MAX    = 0x1.fffffeP+127f;
	
	public static final long    INTEGER_MASK = 0x00000000FFFFFFFFL;
	public static final int     INTEGER_SIZE = 4;
	public static final int     INTEGER_MIN  = -2147483648;
	public static final int     INTEGER_MAX  = 2147483647;
	
	public static final long    DOUBLE_MASK  = 0xFFFFFFFFFFFFFFFFL;
	public static final int     DOUBLE_SIZE  = 8;
	public static final double  DOUBLE_MIN   = 0x0.0000000000001P-1022 ;
	public static final double  DOUBLE_MAX   = 0x1.fffffffffffffP+1023;
	
	public static final long    LONG_MASK    = 0xFFFFFFFFFFFFFFFFL;
	public static final int     LONG_SIZE    = 8;
	public static final long    LONG_MIN     = -9223372036854775808L;
	public static final long    LONG_MAX     = 9223372036854775807L;

    public static final double  POSITIVE_INF = 1.0 / 0.0;
    public static final double  NEGATIVE_INF = -1.0 / 0.0;
	public static final double  NaN          = 0.0D/0.0D;
	
    public final static char[]  ALPHABET     = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static int[]        APLHAPETtoInt= new int[128]; 
    static {
        for(int i=0; i< ALPHABET.length; i++)
            APLHAPETtoInt[ALPHABET[i]] = i;  
    }
	
	public static final int STRING(String message){
		return message.length() * CHAR_SIZE;
	}
	
	/* ========================= */
	/* "primitive type --> byte[] data" Methods */
	/* ========================= */

	public static byte[] toByta(byte data) {
	    return new byte[]{data};
	}

	public static byte[] toByta(byte[] data) {
	    return data;
	}

	/* ========================= */

	public static byte[] toByta(short data) {
	    return new byte[] {
	        (byte)((data >> 8) & 0xff),
	        (byte)((data >> 0) & 0xff),
	    };
	}

	public static byte[] toByta(short[] data) {
	    if (data == null) return null;
	    // ----------
	    byte[] byts = new byte[data.length * 2];
	    for (int i = 0; i < data.length; i++)
	        System.arraycopy(toByta(data[i]), 0, byts, i * 2, 2);
	    return byts;
	}

	/* ========================= */

	public static byte[] toByta(char data) {
	    return new byte[] {
	        (byte)((data >> 8) & 0xff),
	        (byte)((data >> 0) & 0xff),
	    };
	}

	public static byte[] toByta(char[] data) {
	    if (data == null) return null;
	    // ----------
	    byte[] byts = new byte[data.length * 2];
	    for (int i = 0; i < data.length; i++)
	        System.arraycopy(toByta(data[i]), 0, byts, i * 2, 2);
	    return byts;
	}

	/* ========================= */

	public static byte[] toByta(int data) {
	    return new byte[] {
	        (byte)((data >> 24) & 0xff),
	        (byte)((data >> 16) & 0xff),
	        (byte)((data >> 8) & 0xff),
	        (byte)((data >> 0) & 0xff),
	    };
	}

	public static byte[] toByta(int[] data) {
	    if (data == null) return null;
	    // ----------
	    byte[] byts = new byte[data.length * 4];
	    for (int i = 0; i < data.length; i++)
	        System.arraycopy(toByta(data[i]), 0, byts, i * 4, 4);
	    return byts;
	}

	/* ========================= */

	public static byte[] toByta(long data) {
	    return new byte[] {
	        (byte)((data >> 56) & 0xff),
	        (byte)((data >> 48) & 0xff),
	        (byte)((data >> 40) & 0xff),
	        (byte)((data >> 32) & 0xff),
	        (byte)((data >> 24) & 0xff),
	        (byte)((data >> 16) & 0xff),
	        (byte)((data >> 8) & 0xff),
	        (byte)((data >> 0) & 0xff),
	    };
	}

	public static byte[] toByta(long[] data) {
	    if (data == null) return null;
	    // ----------
	    byte[] byts = new byte[data.length * 8];
	    for (int i = 0; i < data.length; i++)
	        System.arraycopy(toByta(data[i]), 0, byts, i * 8, 8);
	    return byts;
	}

	/* ========================= */

	public static byte[] toByta(float data) {
	    return toByta(Float.floatToRawIntBits(data));
	}

	public static byte[] toByta(float[] data) {
	    if (data == null) return null;
	    // ----------
	    byte[] byts = new byte[data.length * 4];
	    for (int i = 0; i < data.length; i++)
	        System.arraycopy(toByta(data[i]), 0, byts, i * 4, 4);
	    return byts;
	}

	/* ========================= */

	public static byte[] toByta(double data) {
	    return toByta(Double.doubleToRawLongBits(data));
	}

	public static byte[] toByta(double[] data) {
	    if (data == null) return null;
	    // ----------
	    byte[] byts = new byte[data.length * 8];
	    for (int i = 0; i < data.length; i++)
	        System.arraycopy(toByta(data[i]), 0, byts, i * 8, 8);
	    return byts;
	}

	/* ========================= */

	public static byte[] toByta(boolean data) {
	    return new byte[]{(byte)(data ? 0x01 : 0x00)}; // bool -> {1 byte}
	}

	public static byte[] toByta(boolean[] data) {
	    // Advanced Technique: The byte array containts information
	    // about how many boolean values are involved, so the exact
	    // array is returned when later decoded.
	    // ----------
	    if (data == null) return null;
	    // ----------
	    int len = data.length;
	    byte[] lena = toByta(len); // int conversion; length array = lena
	    byte[] byts = new byte[lena.length + (len / 8) + (len % 8 != 0 ? 1 : 0)];
	    // (Above) length-array-length + sets-of-8-booleans +? byte-for-remainder
	    System.arraycopy(lena, 0, byts, 0, lena.length);
	    // ----------
	    // (Below) algorithm by Matthew Cudmore: boolean[] -> bits -> byte[]
	    for (int i = 0, j = lena.length, k = 7; i < data.length; i++) {
	        byts[j] |= (data[i] ? 1 : 0) << k--;
	        if (k < 0) { j++; k = 7; }
	    }
	    // ----------
	    return byts;
	}

	/* ========================= */

	public static byte[] toByta(String data) {
	    try {
			return (data == null) ? null : data.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Error(e);
		}
	}

	public static byte[] toByta(String[] data) {
	    // Advanced Technique: Generates an indexed byte array
	    // which contains the array of Strings. The byte array
	    // contains information about the number of Strings and
	    // the length of each String.
	    // ----------
	    if (data == null) return null;
	    // ---------- flags:
	    int totalLength = 0; // Measure length of final byte array
	    int bytesPos = 0; // Used later
	    // ----- arrays:
	    byte[] dLen = toByta(data.length); // byte array of data length
	    totalLength += dLen.length;
	    int[] sLens = new int[data.length]; // String lengths = sLens
	    totalLength += (sLens.length * 4);
	    byte[][] strs = new byte[data.length][]; // array of String bytes
	    // ----- pack strs:
	    for (int i = 0; i < data.length; i++) {
	        if (data[i] != null) {
	            strs[i] = toByta(data[i]);
	            sLens[i] = strs[i].length;
	            totalLength += strs[i].length;
	        } else {
	            sLens[i] = 0;
	            strs[i] = new byte[0]; // prevent null entries
	        }
	    }
	    // ----------
	    byte[] bytes = new byte[totalLength]; // final array
	    System.arraycopy(dLen, 0, bytes, 0, 4);
	    byte[] bsLens = toByta(sLens); // byte version of String sLens
	    System.arraycopy(bsLens, 0, bytes, 4, bsLens.length);
	    // -----
	    bytesPos += 4 + bsLens.length; // mark position
	    // -----
	    for (byte[] sba : strs) {
	        System.arraycopy(sba, 0, bytes, bytesPos, sba.length);
	        bytesPos += sba.length;
	    }
	    // ----------
	    return bytes;
	}

	/* ========================= */
	/* "byte[] data --> primitive type" Methods */
	/* ========================= */

	public static byte toByte(byte[] data) {
	    return (data == null || data.length == 0) ? 0x0 : data[0];
	}

	public static byte[] toByteA(byte[] data) {
	    return data;
	}

	/* ========================= */

	public static short toShort(byte[] data) {
	    if (data == null || data.length != 2) return 0x0;
	    // ----------
	    return (short)(
	            (0xff & data[0]) << 8   |
	            (0xff & data[1]) << 0
	            );
	}

	public static short[] toShortA(byte[] data) {
	    if (data == null || data.length % 2 != 0) return null;
	    // ----------
	    short[] shts = new short[data.length / 2];
	    for (int i = 0; i < shts.length; i++) {
	        shts[i] = toShort( new byte[] {
	            data[(i*2)],
	            data[(i*2)+1]
	        } );
	    }
	    return shts;
	}

	/* ========================= */

	public static char toChar(byte[] data) {
	    if (data == null || data.length != 2) return 0x0;
	    // ----------
	    return (char)(
	            (0xff & data[0]) << 8   |
	            (0xff & data[1]) << 0
	            );
	}

	public static char[] toCharA(byte[] data) {
	    if (data == null || data.length % 2 != 0) return null;
	    // ----------
	    char[] chrs = new char[data.length / 2];
	    for (int i = 0; i < chrs.length; i++) {
	        chrs[i] = toChar( new byte[] {
	            data[(i*2)],
	            data[(i*2)+1],
	        } );
	    }
	    return chrs;
	}

	/* ========================= */

	public static int toInt(byte[] data) {
	    if (data == null || data.length != 4) return 0x0;
	    // ----------
	    return (int)( // NOTE: type cast not necessary for int
	            (0xff & data[0]) << 24  |
	            (0xff & data[1]) << 16  |
	            (0xff & data[2]) << 8   |
	            (0xff & data[3]) << 0
	            );
	}

	public static int[] toIntA(byte[] data) {
	    if (data == null || data.length % 4 != 0) return null;
	    // ----------
	    int[] ints = new int[data.length / 4];
	    for (int i = 0; i < ints.length; i++)
	        ints[i] = toInt( new byte[] {
	            data[(i*4)],
	            data[(i*4)+1],
	            data[(i*4)+2],
	            data[(i*4)+3],
	        } );
	    return ints;
	}

	/* ========================= */

	public static long toLong(byte[] data) {
	    if (data == null || data.length != 8) return 0x0;
	    // ----------
	    return (long)(
	            // (Below) convert to longs before shift because digits
	            //         are lost with ints beyond the 32-bit limit
	            (long)(0xff & data[0]) << 56  |
	            (long)(0xff & data[1]) << 48  |
	            (long)(0xff & data[2]) << 40  |
	            (long)(0xff & data[3]) << 32  |
	            (long)(0xff & data[4]) << 24  |
	            (long)(0xff & data[5]) << 16  |
	            (long)(0xff & data[6]) << 8   |
	            (long)(0xff & data[7]) << 0
	            );
	}

	public static long[] toLongA(byte[] data) {
	    if (data == null || data.length % 8 != 0) return null;
	    // ----------
	    long[] lngs = new long[data.length / 8];
	    for (int i = 0; i < lngs.length; i++) {
	        lngs[i] = toLong( new byte[] {
	            data[(i*8)],
	            data[(i*8)+1],
	            data[(i*8)+2],
	            data[(i*8)+3],
	            data[(i*8)+4],
	            data[(i*8)+5],
	            data[(i*8)+6],
	            data[(i*8)+7],
	        } );
	    }
	    return lngs;
	}

	/* ========================= */

	public static float toFloat(byte[] data) {
	    if (data == null || data.length != 4) return 0x0;
	    // ---------- simple:
	    return Float.intBitsToFloat(toInt(data));
	}

	public static float[] toFloatA(byte[] data) {
	    if (data == null || data.length % 4 != 0) return null;
	    // ----------
	    float[] flts = new float[data.length / 4];
	    for (int i = 0; i < flts.length; i++) {
	        flts[i] = toFloat( new byte[] {
	            data[(i*4)],
	            data[(i*4)+1],
	            data[(i*4)+2],
	            data[(i*4)+3],
	        } );
	    }
	    return flts;
	}

	/* ========================= */

	public static double toDouble(byte[] data) {
	    if (data == null || data.length != 8) return 0x0;
	    // ---------- simple:
	    return Double.longBitsToDouble(toLong(data));
	}

	public static double[] toDoubleA(byte[] data) {
	    if (data == null) return null;
	    // ----------
	    if (data.length % 8 != 0) return null;
	    double[] dbls = new double[data.length / 8];
	    for (int i = 0; i < dbls.length; i++) {
	        dbls[i] = toDouble( new byte[] {
	            data[(i*8)],
	            data[(i*8)+1],
	            data[(i*8)+2],
	            data[(i*8)+3],
	            data[(i*8)+4],
	            data[(i*8)+5],
	            data[(i*8)+6],
	            data[(i*8)+7],
	        } );
	    }
	    return dbls;
	}

	/* ========================= */

	public static boolean toBoolean(byte[] data) {
	    return (data == null || data.length == 0) ? false : data[0] != 0x00;
	}

	public static boolean[] toBooleanA(byte[] data) {
	    // Advanced Technique: Extract the boolean array's length
	    // from the first four bytes in the char array, and then
	    // read the boolean array.
	    // ----------
	    if (data == null || data.length < 4) return null;
	    // ----------
	    int len = toInt(new byte[]{data[0], data[1], data[2], data[3]});
	    boolean[] bools = new boolean[len];
	    // ----- pack bools:
	    for (int i = 0, j = 4, k = 7; i < bools.length; i++) {
	        bools[i] = ((data[j] >> k--) & 0x01) == 1;
	        if (k < 0) { j++; k = 7; }
	    }
	    // ----------
	    return bools;
	}

	/* ========================= */

	public static String toString(byte[] data) {
	    try {
			return (data == null) ? null : new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Error(e);
		}
	}

	public static String[] toStringA(byte[] data) {
	    // Advanced Technique: Extract the String array's length
	    // from the first four bytes in the char array, and then
	    // read the int array denoting the String lengths, and
	    // then read the Strings.
	    // ----------
	    if (data == null || data.length < 4) return null;
	    // ----------
	    byte[] bBuff = new byte[4]; // Buffer
	    // -----
	    System.arraycopy(data, 0, bBuff, 0, 4);
	    int saLen = toInt(bBuff);
	    if (data.length < (4 + (saLen * 4))) return null;
	    // -----
	    bBuff = new byte[saLen * 4];
	    System.arraycopy(data, 4, bBuff, 0, bBuff.length);
	    int[] sLens = toIntA(bBuff);
	    if (sLens == null) return null;
	    // ----------
	    String[] strs = new String[saLen];
	    for (int i = 0, dataPos = 4 + (saLen * 4); i < saLen; i++) {
	        if (sLens[i] > 0) {
	            if (data.length >= (dataPos + sLens[i])) {
	                bBuff = new byte[sLens[i]];
	                System.arraycopy(data, dataPos, bBuff, 0, sLens[i]);
	                dataPos += sLens[i];
	                strs[i] = toString(bBuff);
	            } else return null;
	        }
	    }
	    // ----------
	    return strs;
	}

	/* ========================= */
	/* "Base64 --> byte[] data" Methods */
	/* "byte[] data --> Base64" Methods */
	/* ========================= */
	
    public static final String encode(byte[] buf){
        int size = buf.length;
        char[] ar = new char[((size + 2) / 3) * 4];
        int a = 0;
        int i=0;
        while(i < size){
            byte b0 = buf[i++];
            byte b1 = (i < size) ? buf[i++] : 0;
            byte b2 = (i < size) ? buf[i++] : 0;

            int mask = 0x3F;
            ar[a++] = ALPHABET[(b0 >> 2) & mask];
            ar[a++] = ALPHABET[((b0 << 4) | ((b1 & 0xFF) >> 4)) & mask];
            ar[a++] = ALPHABET[((b1 << 2) | ((b2 & 0xFF) >> 6)) & mask];
            ar[a++] = ALPHABET[b2 & mask];
        }
        switch(size % 3){
            case 1: ar[--a]  = '=';
            case 2: ar[--a]  = '=';
        }
        return new String(ar);
    }

    public static final byte[] decode(String s){
        int delta = s.endsWith( "==" ) ? 2 : s.endsWith( "=" ) ? 1 : 0;
        byte[] buffer = new byte[s.length()*3/4 - delta];
        int mask = 0xFF;
        int index = 0;
        for(int i=0; i< s.length(); i+=4){
            int c0 = APLHAPETtoInt[s.charAt( i )];
            int c1 = APLHAPETtoInt[s.charAt( i + 1 )];
            buffer[index++]= (byte)(((c0 << 2) | (c1 >> 4)) & mask);
            if(index >= buffer.length){
                return buffer;
            }
            int c2 = APLHAPETtoInt[s.charAt( i + 2 )];
            buffer[index++]= (byte)(((c1 << 4) | (c2 >> 2)) & mask);
            if(index >= buffer.length){
                return buffer;
            }
            int c3 = APLHAPETtoInt[s.charAt( i + 3 )];
            buffer[index++]= (byte)(((c2 << 6) | c3) & mask);
        }
        return buffer;
    } 
    
	/* ========================= */
	/* "Bit" methods
	/* ========================= */
    
    public static final long compile(int i0, int i1){
    	return ((long)i0 << 32)|(long)i1;
    }
    
    public static final int  compile(short s0, short s1){
    	return ((int)s0 << 16)|(int) s1;
    }
    
    public static final short compile(byte b0, byte b1){
    	return (short) (((byte)b0 << 8)|(byte) b1);
    }
    
    public static final BigInteger maxValue(int bits, boolean signed){
    	BigInteger i = BigInteger.valueOf(2L);
    	return i.pow(bits - (signed ? 1 : 0)).subtract(BigInteger.valueOf(1L));
    }
    
    public static final int bitsOf(int bytes){ return bitsOf(bytes, false); }
    public static final int bitsOf(int bytes, boolean signed){
    	return (bytes * 8) - (signed ? 1 : 0);
    }
    
    public static final BigInteger minValue(int bits, boolean signed){
    	if(!signed)
    		return BigInteger.ZERO;
    	BigInteger i = BigInteger.valueOf(2L);
    	return i.pow(bits - 1).negate();
    }
    
    
    public static final long setBits(long base, long flag){
    	return (base | flag);
    }
    
    public static final boolean areBitsSet(long base, long flag){
    	return (base & flag) == flag;
    }
    
    public static final long setBit(long base, int nbit, boolean flag){
    	if(flag){
    		return base | 1L << nbit;
    	}else{
    		return base & ~(1L << nbit);
    	}
    }
    
    public static final long flipBit(long base, int nbit){
    	return base ^ (1 << nbit);
    }
    
    public static final boolean isPowerOfTwo(long base){
    	if(base == 0)
    		return false;
    	return ((base - 1) & base) == 0;
    }
    
    public static final long absValue(long base){
    	return (base ^ (base>>63)) - (base>>63);
    }
    
    public static final boolean[] getBits(long base){
    	boolean[] bits = new boolean[LONG_SIZE];
    	for(int nbit = 0 ; nbit < LONG_SIZE ; nbit++)
    		bits[nbit] = ((base >>> nbit) & 0x1L) == 1L;
    	return bits;
    }
    
    public static final boolean getBit(long base, int nbit){
    	//return (base & nbit) == nbit;
    	return ((base >>> nbit) & 0x1L) == 1L;
    }
    
    public static final long andBit(long base , long and){
    	return base & and;
    }
    
    public static final long orBit(long base, long or){
    	return base | or;
    }
    
    public static final long xorBit(long base, long xor){
    	return base ^ xor;
    }
    
    public static final long notBit(long base){
    	return ~base;
    }
    
    public static final String toLongBinaryString(long base){ return toBinaryString(base, true, ' '); }
    public static final String toBinaryString(long base){ return toBinaryString(base, false, null); }
    private static final String toBinaryString(long base, boolean longFormat, Character delimiter){
    	String data = "";
    	boolean append = longFormat;
    	boolean append_deli = delimiter != null;
    	for(int nbit = ((LONG_SIZE * 8) - 1) ; nbit >= 0 ; nbit--){
    		long bit = (base >>> nbit) & 0x1L;
    		
    		if(bit == 1L && append == false)
    			append = true;
    		
    		if(append){
    			data += bit;
    			if(nbit % 8 == 0 && append_deli){
    				data += delimiter;
    			}
    		}
    	}
    	return data;
    }
}
