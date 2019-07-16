package SM2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class SM3 {
    private static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String IV_HEX_STR = "7380166f 4914b2b9 172442d7 da8a0600 a96f30bc 163138aa e38dee4d b0fb0e4e";
    private static final BigInteger IV = new BigInteger("7380166f 4914b2b9 172442d7 da8a0600 a96f30bc 163138aa e38dee4d b0fb0e4e".replaceAll(" ", ""), 16);
    private static final Integer TJ15 = Integer.valueOf("79cc4519", 16);
    private static final Integer TJ63 = Integer.valueOf("7a879d8a", 16);
    private static final byte[] FIRST_PADDING = new byte[]{-128};
    private static final byte[] ZERO_PADDING = new byte[]{0};

    public SM3() {
    }

    private static int T(int j) {
        if (j >= 0 && j <= 15) {
            return TJ15;
        } else if (j >= 16 && j <= 63) {
            return TJ63;
        } else {
            throw new RuntimeException("data invalid");
        }
    }

    private static Integer FF(Integer x, Integer y, Integer z, int j) {
        if (j >= 0 && j <= 15) {
            return x ^ y ^ z;
        } else if (j >= 16 && j <= 63) {
            return x & y | x & z | y & z;
        } else {
            throw new RuntimeException("data invalid");
        }
    }

    private static Integer GG(Integer x, Integer y, Integer z, int j) {
        if (j >= 0 && j <= 15) {
            return x ^ y ^ z;
        } else if (j >= 16 && j <= 63) {
            return x & y | ~x & z;
        } else {
            throw new RuntimeException("data invalid");
        }
    }

    private static Integer P0(Integer x) {
        return x ^ Integer.rotateLeft(x, 9) ^ Integer.rotateLeft(x, 17);
    }

    private static Integer P1(Integer x) {
        return x ^ Integer.rotateLeft(x, 15) ^ Integer.rotateLeft(x, 23);
    }

    private static byte[] padding(byte[] source) throws IOException {
        if ((long)source.length >= 2305843009213693952L) {
            throw new RuntimeException("src data invalid.");
        } else {
            long l = (long)(source.length * 8);
            long k = 448L - (l + 1L) % 512L;
            if (k < 0L) {
                k += 512L;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(source);
            baos.write(FIRST_PADDING);

            for(long i = k - 7L; i > 0L; i -= 8L) {
                baos.write(ZERO_PADDING);
            }

            baos.write(long2bytes(l));
            return baos.toByteArray();
        }
    }

    private static byte[] long2bytes(long l) {
        byte[] bytes = new byte[8];

        for(int i = 0; i < 8; ++i) {
            bytes[i] = (byte)((int)(l >>> (7 - i) * 8));
        }

        return bytes;
    }

    public static byte[] hash(byte[] source) throws IOException {
        byte[] m1 = padding(source);
        int n = m1.length / 64;
        byte[] vi = IV.toByteArray();
        byte[] vi1 = null;

        for(int i = 0; i < n; ++i) {
            byte[] b = Arrays.copyOfRange(m1, i * 64, (i + 1) * 64);
            vi1 = CF(vi, b);
            vi = vi1;
        }

        return vi1;
    }

    private static byte[] CF(byte[] vi, byte[] bi) throws IOException {
        int a = toInteger(vi, 0);
        int b = toInteger(vi, 1);
        int c = toInteger(vi, 2);
        int d = toInteger(vi, 3);
        int e = toInteger(vi, 4);
        int f = toInteger(vi, 5);
        int g = toInteger(vi, 6);
        int h = toInteger(vi, 7);
        int[] w = new int[68];
        int[] w1 = new int[64];

        int ss1;
        for(ss1 = 0; ss1 < 16; ++ss1) {
            w[ss1] = toInteger(bi, ss1);
        }

        for(ss1 = 16; ss1 < 68; ++ss1) {
            w[ss1] = P1(w[ss1 - 16] ^ w[ss1 - 9] ^ Integer.rotateLeft(w[ss1 - 3], 15)) ^ Integer.rotateLeft(w[ss1 - 13], 7) ^ w[ss1 - 6];
        }

        for(ss1 = 0; ss1 < 64; ++ss1) {
            w1[ss1] = w[ss1] ^ w[ss1 + 4];
        }

        for(int j = 0; j < 64; ++j) {
            ss1 = Integer.rotateLeft(Integer.rotateLeft(a, 12) + e + Integer.rotateLeft(T(j), j), 7);
            int ss2 = ss1 ^ Integer.rotateLeft(a, 12);
            int tt1 = FF(a, b, c, j) + d + ss2 + w1[j];
            int tt2 = GG(e, f, g, j) + h + ss1 + w[j];
            d = c;
            c = Integer.rotateLeft(b, 9);
            b = a;
            a = tt1;
            h = g;
            g = Integer.rotateLeft(f, 19);
            f = e;
            e = P0(tt2);
        }

        byte[] v = toByteArray(a, b, c, d, e, f, g, h);

        for(int i = 0; i < v.length; ++i) {
            v[i] ^= vi[i];
        }

        return v;
    }

    private static int toInteger(byte[] source, int index) {
        StringBuilder valueStr = new StringBuilder("");

        for(int i = 0; i < 4; ++i) {
            valueStr.append(hexDigits[(byte)((source[index * 4 + i] & 240) >> 4)]);
            valueStr.append(hexDigits[(byte)(source[index * 4 + i] & 15)]);
        }

        return Long.valueOf(valueStr.toString(), 16).intValue();
    }

    private static byte[] toByteArray(int a, int b, int c, int d, int e, int f, int g, int h) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        baos.write(toByteArray(a));
        baos.write(toByteArray(b));
        baos.write(toByteArray(c));
        baos.write(toByteArray(d));
        baos.write(toByteArray(e));
        baos.write(toByteArray(f));
        baos.write(toByteArray(g));
        baos.write(toByteArray(h));
        return baos.toByteArray();
    }

    public static byte[] toByteArray(int i) {
        byte[] byteArray = new byte[]{(byte)(i >>> 24), (byte)((i & 16777215) >>> 16), (byte)((i & '\uffff') >>> 8), (byte)(i & 255)};
        return byteArray;
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return "" + hexDigits[d1] + hexDigits[d2];
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }
}

