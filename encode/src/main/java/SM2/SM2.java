package SM2;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class SM2 {
    private static BigInteger n = new BigInteger("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123", 16);
    private static BigInteger p = new BigInteger("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF", 16);
    private static BigInteger a = new BigInteger("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC", 16);
    private static BigInteger b = new BigInteger("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93", 16);
    private static BigInteger gx = new BigInteger("32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7", 16);
    private static BigInteger gy = new BigInteger("BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0", 16);
    private static final int DIGEST_LENGTH = 32;
    private static SecureRandom random = new SecureRandom();
    private static ECCurve.Fp curve;
    private static ECPoint G;
    private static ECDomainParameters ecc_bc_spec;

    public SM2() {
    }

    public static String sm2Encrypt(String plainText, String pubKey) {
        byte[] data = encrypt(plainText, pubKey);
        String enData = Base64.encode(data);
        return enData;
    }

    public static String sm2Decrypt(String plainText, String priKey) {
        byte[] encryptData = Base64.decode(plainText);
        String rawData = decrypt(encryptData, priKey);
        return rawData;
    }

    public static void printHexString(byte[] b) {
        for(int i = 0; i < b.length; ++i) {
            String hex = Integer.toHexString(b[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            System.out.print(hex.toUpperCase());
        }

        System.out.println();
    }

    private static BigInteger random(BigInteger max) {
        BigInteger r;
        for(r = new BigInteger(256, random); r.compareTo(max) >= 0; r = new BigInteger(128, random)) {
        }

        return r;
    }

    private static boolean allZero(byte[] buffer) {
        for(int i = 0; i < buffer.length; ++i) {
            if (buffer[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public static byte[] encrypt(String input, String pubKeyStr) {
        ECPoint publicKey = curve.decodePoint(hexStr2Bytes(pubKeyStr));
        byte[] inputBuffer = new byte[0];

        try {
            inputBuffer = input.getBytes("UTF8");
        } catch (UnsupportedEncodingException var11) {
            var11.printStackTrace();
        }

        byte[] C1Buffer;
        ECPoint kpb;
        byte[] t;
        do {
            BigInteger k = random(n);
            ECPoint C1 = G.multiply(k);
            C1Buffer = C1.getEncoded(false);
            BigInteger h = ecc_bc_spec.getH();
            if (h != null) {
                ECPoint S = publicKey.multiply(h);
                if (S.isInfinity()) {
                    throw new IllegalStateException();
                }
            }

            kpb = publicKey.multiply(k).normalize();
            byte[] kpbBytes = kpb.getEncoded(false);
            t = KDF(kpbBytes, inputBuffer.length);
        } while(allZero(t));

        byte[] C2 = new byte[inputBuffer.length];

        for(int i = 0; i < inputBuffer.length; ++i) {
            C2[i] = (byte)(inputBuffer[i] ^ t[i]);
        }

        byte[] C3 = sm3hash(kpb.getXCoord().toBigInteger().toByteArray(), inputBuffer, kpb.getYCoord().toBigInteger().toByteArray());
        byte[] encryptResult = new byte[C1Buffer.length + C2.length + C3.length];
        System.arraycopy(C1Buffer, 0, encryptResult, 0, C1Buffer.length);
        System.arraycopy(C2, 0, encryptResult, C1Buffer.length, C2.length);
        System.arraycopy(C3, 0, encryptResult, C1Buffer.length + C2.length, C3.length);
        return encryptResult;
    }

    public static String decrypt(byte[] encryptData, String priKeyStr) {
        BigInteger privateKey = new BigInteger(priKeyStr, 16);
        byte[] C1Byte = new byte[65];
        System.arraycopy(encryptData, 0, C1Byte, 0, C1Byte.length);
        ECPoint C1 = curve.decodePoint(C1Byte).normalize();
        BigInteger h = ecc_bc_spec.getH();
        ECPoint dBC1;
        if (h != null) {
            dBC1 = C1.multiply(h);
            if (dBC1.isInfinity()) {
                throw new IllegalStateException();
            }
        }

        dBC1 = C1.multiply(privateKey).normalize();
        byte[] dBC1Bytes = dBC1.getEncoded(false);
        int klen = encryptData.length - 65 - 32;
        byte[] t = KDF(dBC1Bytes, klen);
        if (allZero(t)) {
            System.err.println("all zero");
            throw new IllegalStateException();
        } else {
            byte[] M = new byte[klen];

            for(int i = 0; i < M.length; ++i) {
                M[i] = (byte)(encryptData[C1Byte.length + i] ^ t[i]);
            }

            byte[] C3 = new byte[32];
            System.arraycopy(encryptData, encryptData.length - 32, C3, 0, 32);
            byte[] u = sm3hash(dBC1.getXCoord().toBigInteger().toByteArray(), M, dBC1.getYCoord().toBigInteger().toByteArray());
            if (Arrays.equals(u, C3)) {
                try {
                    return new String(M, "UTF8");
                } catch (UnsupportedEncodingException var14) {
                    var14.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    private static boolean between(BigInteger param, BigInteger min, BigInteger max) {
        return param.compareTo(min) >= 0 && param.compareTo(max) < 0;
    }

    private static boolean checkPublicKey(ECPoint publicKey) {
        if (!publicKey.isInfinity()) {
            BigInteger x = publicKey.getXCoord().toBigInteger();
            BigInteger y = publicKey.getYCoord().toBigInteger();
            if (between(x, new BigInteger("0"), p) && between(y, new BigInteger("0"), p)) {
                BigInteger xResult = x.pow(3).add(a.multiply(x)).add(b).mod(p);
                BigInteger yResult = y.pow(2).mod(p);
                if (yResult.equals(xResult) && publicKey.multiply(n).isInfinity()) {
                    return true;
                }
            }
        }

        return false;
    }

    private static byte[] join(byte[]... params) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] res = null;

        try {
            for(int i = 0; i < params.length; ++i) {
                baos.write(params[i]);
            }

            res = baos.toByteArray();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return res;
    }

    private static byte[] sm3hash(byte[]... params) {
        byte[] res = null;

        try {
            res = SM3.hash(join(params));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return res;
    }

    private static byte[] KDF(byte[] Z, int klen) {
        int ct = 1;
        int end = (int)Math.ceil((double)klen * 1.0D / 32.0D);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            for(int i = 1; i < end; ++i) {
                baos.write(sm3hash(Z, SM3.toByteArray(ct)));
                ++ct;
            }

            byte[] last = sm3hash(Z, SM3.toByteArray(ct));
            if (klen % 32 == 0) {
                baos.write(last);
            } else {
                baos.write(last, 0, klen % 32);
            }

            return baos.toByteArray();
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static byte[] hexStr2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];

        for(int i = 0; i < l; ++i) {
            int m = i * 2 + 1;
            int n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }

        return ret;
    }

    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0);
        b0 = (byte)(b0 << 4);
        byte b1 = Byte.decode("0x" + src1);
        byte ret = (byte)(b0 | b1);
        return ret;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String pubKeyStr = "04a7fa237c2b7b0fd08d26a7adb12363c8556a8b7d99fa134ee55d3e77d3b5317426f3423ef9463dc5d6e65ca7164070a69f9ff29de9799a38c18003048d552364";
        String priKeyStr = "00a0fcf9f83882db3f5279daafc6347dc126ca8586c996cf65dca6351d0fa465e7";
        String body = sm2Encrypt("womenyiqi", pubKeyStr);
        System.out.println("密文:" + body);
        String rawData = sm2Decrypt(body, priKeyStr);
        System.out.println("解密后明文:" + rawData);
    }

    static {
        curve = new ECCurve.Fp(p, a, b);
        G = curve.createPoint(gx, gy);
        ecc_bc_spec = new ECDomainParameters(curve, G, n);
    }
}

