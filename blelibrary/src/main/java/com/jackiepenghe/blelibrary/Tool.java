package com.jackiepenghe.blelibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by alm on 17-6-5.
 * 工具类
 */

@SuppressWarnings({"WeakerAccess", "unused", "SameParameterValue"})
public class Tool {
    //log部分

    /**
     * log的TAG
     */
    private static String TAG = "BleLibrary->";

    /**
     * 是否打印日志信息的标志
     */
    private static boolean mDebug = false;

    /**
     * 获取当前日志打印标志
     *
     * @return 日志打印标志
     */
    public static boolean isDebug() {
        return mDebug;
    }

    /**
     * 设置日志打印标志
     *
     * @param debug 日志打印标志
     */
    public static void setDebugFlag(boolean debug) {
        mDebug = debug;
    }

    /**
     * 设置默认的tag
     *
     * @param tag 默认的tag
     */
    public static void setDefaultTAG(String tag) {
        TAG = tag;
    }

    /**
     * 等同于Log.i
     *
     * @param tag     tag
     * @param message 日志信息
     */
    @SuppressWarnings("WeakerAccess")
    public static void infoOut(String tag, String message) {
        if (!mDebug) {
            return;
        }
        Log.i(TAG + tag, message);
    }

    /**
     * 等同于Log.i
     *
     * @param message 日志信息
     */
    public static void infoOut(String message) {
        infoOut(TAG, message);
    }

    /**
     * 等同于Log.e
     *
     * @param tag     tag
     * @param message 日志信息
     */
    @SuppressWarnings("WeakerAccess")
    public static void errorOut(String tag, String message) {
        if (!mDebug) {
            return;
        }
        Log.e(TAG + tag, message);
    }

    /**
     * 等同于Log.e
     *
     * @param message 日志信息
     */
    public static void errorOut(String message) {
        errorOut(TAG, message);
    }

    /**
     * 等同于Log.d
     *
     * @param tag     tag
     * @param message 日志信息
     */
    @SuppressWarnings("WeakerAccess")
    public static void debugOut(String tag, String message) {
        if (!mDebug) {
            return;
        }
        Log.d(TAG + tag, message);
    }

    /**
     * 等同于Log.d
     *
     * @param message 日志信息
     */
    @SuppressWarnings("SameParameterValue")
    public static void debugOut(String message) {
        debugOut(TAG, message);
    }

    /**
     * 等同于Log.w
     *
     * @param tag     tag
     * @param message 日志信息
     */
    @SuppressWarnings("WeakerAccess")
    public static void warnOut(String tag, String message) {
        if (!mDebug) {
            return;
        }
        Log.w(TAG + tag, message);
    }

    /**
     * 等同于Log.w
     *
     * @param message 日志信息
     */
    public static void warnOut(String message) {
        warnOut(TAG, message);
    }


    /**
     * 等同于Log.v
     *
     * @param tag     tag
     * @param message 日志信息
     */
    @SuppressWarnings("WeakerAccess")
    public static void verOut(String tag, String message) {
        if (!mDebug) {
            return;
        }
        Log.v(TAG + tag, message);
    }

    /**
     * 等同于Log.v
     *
     * @param message 日志信息
     */
    public static void verOut(String message) {
        verOut(TAG, message);
    }

    //Toast部分

    /**
     * 弹出Toast
     *
     * @param context  上下文
     * @param message  信息
     * @param duration 持续时间
     */
    private static void showToast(Context context, String message, int duration) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            CustomToast.makeText(context, message, duration).show();
            return;
        }

        switch (duration) {
            case CustomToast.LENGTH_LONG:
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                break;
            case CustomToast.LENGTH_SHORT:
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * 弹出Toast
     *
     * @param context    上下文
     * @param messageRes 信息
     * @param duration   持续时间
     */
    private static void showToast(Context context, @StringRes int messageRes, int duration) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            CustomToast.makeText(context, messageRes, duration).show();
            return;
        }
        switch (duration) {
            case CustomToast.LENGTH_LONG:
                Toast.makeText(context, messageRes, Toast.LENGTH_LONG).show();
                break;
            case CustomToast.LENGTH_SHORT:
                Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, messageRes, Toast.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * 长时间的吐司
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void toastL(Context context, String message) {
        showToast(context, message, CustomToast.LENGTH_LONG);
    }

    /**
     * 长时间的吐司
     *
     * @param context    上下文
     * @param messageRes 信息
     */
    public static void toastL(Context context, @StringRes int messageRes) {
        showToast(context, messageRes, CustomToast.LENGTH_LONG);
    }

    /**
     * 短时间的吐司
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void toastS(Context context, String message) {
        showToast(context, message, CustomToast.LENGTH_SHORT);
    }

    /**
     * 短时间的吐司
     *
     * @param context    上下文
     * @param messageRes 信息
     */
    public static void toastS(Context context, @StringRes int messageRes) {
        showToast(context, messageRes, CustomToast.LENGTH_SHORT);
    }

    /**
     * 自定义时间的吐司
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void toast(Context context, String message, int duration) {
        showToast(context, message, duration);
    }

    /**
     * 自定义时间的吐司
     *
     * @param context    上下文
     * @param messageRes 信息
     */
    public static void toast(Context context, @StringRes int messageRes, int duration) {
        showToast(context, messageRes, duration);
    }

    //数据转换部分

    /**
     * 字符串转换成十六进制字符串
     *
     * @param str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String strToHexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }


    /**
     * 字符串转换成byte数组（数组长度最长为bytesLength）
     *
     * @param s           要转换成byte[]的字符串
     * @param bytesLength 数组长度的最大值
     * @return 转换后获得的byte[]
     */
    public static byte[] getBytes(String s, int bytesLength) {
        byte[] data;

        if (s.length() > bytesLength) {
            data = new byte[bytesLength];
            System.arraycopy(s.getBytes(), 0, data, 0, bytesLength);
        } else {
            data = s.getBytes();
        }
        return data;
    }

    /**
     * 十六进制转换字符串
     *
     * @param hexStr Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStrToStr(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     *
     * @param bytes byte数组
     * @return String 每个Byte值之间空格分隔
     */
    @SuppressWarnings("WeakerAccess")
    public static String bytesToHexStr(byte[] bytes) {
        String stmp;
        StringBuilder sb = new StringBuilder("");
        for (byte aByte : bytes) {
            stmp = Integer.toHexString(aByte & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * bytes字符串转换为Byte值
     *
     * @param src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStrToBytes(String src) {
        int m, n;
        int l = src.length() / 2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int integer = Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
            ret[i] = (byte) integer;
        }
        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     *
     * @param strText 全角字符串
     * @return String 每个unicode之间无分隔符
     */
    public static String strToUnicode(String strText)
            throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                str.append("\\u").append(strHex);
            }
            // 低位在前面补00
            else {
                str.append("\\u00").append(strHex);
            }
        }
        return str.toString();
    }

    /**
     * unicode的String转换成String的字符串
     *
     * @param hex 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     */
    public static String unicodeToString(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    /**
     * 将高位的byte和低位的byte拼接成一个int
     *
     * @param highByte 高位的byte
     * @param lowByte  低位的byte
     * @return 拼接好的int
     */
    public static int bytesToInt(byte highByte, byte lowByte) {
        byte[] bytes = new byte[2];
        bytes[0] = highByte;
        bytes[1] = lowByte;
        String s = bytesToHexStr(bytes);
        String[] split = s.split(" ");
        int high = Integer.parseInt(split[0], 16);
        int low = Integer.parseInt(split[1], 16);
        return high * 256 + low;
    }

    /**
     * 将一个整数转换成16进制的分为高低位的数组
     *
     * @param i 整数
     * @return 16进制的分为高低位的数组
     */
    public static byte[] intToBytes(int i) {
        warnOut("ConversionUtil", "i = " + i);
        String hexString = intToHexStr(i);
        warnOut("ConversionUtil", "hexString = " + hexString);
        byte highByte;
        byte lowByte;
        if (hexString.length() > 2) {
            String substring = hexString.substring(0, hexString.length() - 2);
            warnOut("ConversionUtil", "highByteHex = " + substring);
            highByte = (byte) Integer.parseInt(substring, 16);
            substring = hexString.substring(hexString.length() - 2, hexString.length());
            warnOut("ConversionUtil", "lowByteHex = " + substring);
            lowByte = (byte) Integer.parseInt(substring, 16);
        } else {
            highByte = 0;
            warnOut("ConversionUtil", "highByteHex = " + 0);
            lowByte = (byte) Integer.parseInt(hexString, 16);
            warnOut("ConversionUtil", "lowByteHex = " + hexString);
        }
        return new byte[]{highByte, lowByte};
    }

    /**
     * 将整数转换成16进制字符串
     *
     * @param i 整数
     * @return 16进制字符串
     */
    @SuppressWarnings("WeakerAccess")
    public static String intToHexStr(int i) {
        return Integer.toHexString(i);
    }

    /*其他*/

    public static void sleep(long timeMillis) {
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - currentTimeMillis >= timeMillis) {
                break;
            }
        }
    }

    /**
     * 将字节型数据转换为0~255 (0xFF 即BYTE)
     *
     * @param data data字节型数据
     *             *
     * @return 无符号的整型
     */
    public static int getUnsignedByte(byte data) {
        return data & 0x0FF;
    }

    /**
     * 将字节型数据转换为0~65535 (0xFFFF 即 WORD)
     *
     * @param data 字节型数据
     *             *
     * @return 无符号的整型
     */
    public static int getUnsignedByte(short data) {
        return data & 0x0FFFF;
    }

    /**
     * 将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)。
     *
     * @param data int数据
     *             *
     * @return 无符号的长整型
     */
    public static long getUnsignedInt(int data) {

        //noinspection PointlessBitwiseExpression
        return 0x0FFFFFFFF & data;
    }

    /**
     * 将int转为boolean(0 = false ,1 = true)
     *
     * @param value int值
     *              *
     * @return 对应的结果
     */
    public static boolean intToBoolean(int value) {
        switch (value) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                throw new RuntimeException("The error value " + value);
        }
    }

    /**
     * 将boolean转为int(true = 1,false = 0)
     *
     * @param b boolean值
     *          *
     * @return 对应的int值
     */
    public static int booleanToInt(boolean b) {
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 使用代码触发home键的效果
     *
     * @param context 上下文
     */
    public static void pressHomeButton(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        // 注意:必须加上这句代码，否则就不是单例了
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }
    /**
     * 对象转byte
     * @param obj Object
     * @return byte[]
     */
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * byte转对象
     * @param bytes byte[]
     * @return Object
     */
    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }
}