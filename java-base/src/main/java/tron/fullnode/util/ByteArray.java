package tron.fullnode.util;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class ByteArray {

  public static String toHexString(byte[] data) {
    return data == null ? "" : Hex.toHexString(data);
  }
  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  /**
   * get bytes data from hex string data.
   */
  public static byte[] fromHexString(String data) {
    if (data == null) {
      return EMPTY_BYTE_ARRAY;
    }
    if (data.startsWith("0x")) {
      data = data.substring(2);
    }
    if (data.length() % 2 != 0) {
      data = "0" + data;
    }
    return Hex.decode(data);
  }

  public static byte[] fromLong(long val) {
    return Longs.toByteArray(val);
  }

  public static byte[] fromInt(int val) {
    return Ints.toByteArray(val);
  }


  /**
   * Stringify byte[] x
   * null for null
   * null for empty []
   */
  public static String toJsonHex(byte[] x) {
    return x == null || x.length == 0 ? "0x" : "0x" + Hex.toHexString(x);
  }

  public static String toJsonHex(Long x) {
    return x == null ? null : "0x" + Long.toHexString(x);
  }


  public static BigInteger hexToBigInteger(String input) {
    if (input.startsWith("0x")) {
      return new BigInteger(input.substring(2), 16);
    } else {
      return new BigInteger(input, 10);
    }
  }



  public static int jsonHexToInt(String x) throws Exception {
    if (!x.startsWith("0x")) {
      throw new Exception("Incorrect hex syntax");
    }
    x = x.substring(2);
    return Integer.parseInt(x, 16);
  }

  /**
   * Generate a subarray of a given byte array.
   *
   * @param input the input byte array
   * @param start the start index
   * @param end the end index
   * @return a subarray of <tt>input</tt>, ranging from <tt>start</tt> (inclusively) to <tt>end</tt>
   * (exclusively)
   */
  public static byte[] subArray(byte[] input, int start, int end) {
    byte[] result = new byte[end - start];
    System.arraycopy(input, start, result, 0, end - start);
    return result;
  }

  public static boolean isEmpty(byte[] input) {
    return input == null || input.length == 0;
  }

  public static boolean matrixContains(List<byte[]> source, byte[] obj) {
    for (byte[] sobj : source) {
      if (Arrays.equals(sobj, obj)) {
        return true;
      }
    }
    return false;
  }

  public static String fromHex(String x) {
    if (x.startsWith("0x")) {
      x = x.substring(2);
    }
    if (x.length() % 2 != 0) {
      x = "0" + x;
    }
    return x;
  }
}
