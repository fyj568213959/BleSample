package com.jackiepenghe.blelibrary;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * BlE管理类
 *
 * @author alm
 */

public class BleManager {

    /*------------------------静态变量----------------------------*/

    /**
     * Ble连接实例
     */
    @SuppressLint("StaticFieldLeak")
    private static BleConnector bleConnector;
    /**
     * Ble扫描实例
     */
    @SuppressLint("StaticFieldLeak")
    private static BleScanner bleScanner;
    /**
     * Ble多连接实例
     */
    @SuppressLint("StaticFieldLeak")
    private static BleMultiConnector bleMultiConnector;
    /**
     * Ble广播实例
     */
    @SuppressLint("StaticFieldLeak")
    private static BleAdvertiser bleAdvertiser;
    /**
     * 重置Ble广播实例的标志（避免无限循环调用）
     */
    private static boolean resetBleBroadCastorFlag;

    /*------------------------库内静态函数----------------------------*/

    /**
     * 重置bleMultiConnector避免内存泄漏
     */
    static void resetBleMultiConnector() {
        if (bleMultiConnector != null) {
            bleMultiConnector.closeAll();
        }
        bleMultiConnector = null;
    }

    /**
     * 重置Ble广播实例
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void resetBleBroadCastor() {
        if (resetBleBroadCastorFlag) {
            return;
        }
        resetBleBroadCastorFlag = true;

        if (bleAdvertiser != null) {
            bleAdvertiser.close();
        }
        bleAdvertiser = null;
        resetBleBroadCastorFlag = false;
    }

    /*------------------------公开静态函数----------------------------*/

    /**
     * 判断手机是否支持BLE
     *
     * @param context 上下文
     * @return true表示支持BLE
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isSupportBle(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    /**
     * 创建一个新的BLE连接实例
     *
     * @param context 上下文
     * @return BleConnector
     */
    @Deprecated
    public static BleConnector newBleConnector(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }
        return new BleConnector(context);
    }

    /**
     * 获取BLE连接实例的单例
     *
     * @param context 上下文
     * @return BleConnector单例
     */
    public static BleConnector getBleConnectorInstance(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }

        if (bleConnector == null) {
            synchronized (BleManager.class) {
                if (bleConnector == null) {
                    bleConnector = new BleConnector(context);
                }
            }
        } else {
            if (bleConnector.getContext() == null) {
                bleConnector = null;
            }

            if (bleConnector == null) {
                synchronized (BleManager.class) {
                    if (bleConnector == null) {
                        bleConnector = new BleConnector(context);
                    }
                }
            }
        }
        return bleConnector;
    }

    /**
     * 创建一个新的BLE扫描实例
     *
     * @param context 上下文
     * @return BleScanner
     */
    @Deprecated
    public static BleScanner newBleScanner(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }
        return new BleScanner(context);
    }

    /**
     * 获取BLE扫描实例的单例
     *
     * @param context 上下文
     * @return BleScanner单例
     */
    public static BleScanner getBleScannerInstance(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }
        if (bleScanner == null) {
            synchronized (BleManager.class) {
                if (bleScanner == null) {
                    bleScanner = new BleScanner(context);
                }
            }
        }else {
            if (bleScanner.getContext() == null){
                bleScanner = null;
            }

            if (bleScanner == null) {
                synchronized (BleManager.class) {
                    if (bleScanner == null) {
                        bleScanner = new BleScanner(context);
                    }
                }
            }
        }
        return bleScanner;
    }

    /**
     * 获取蓝牙广播单例实例
     *
     * @param context 上下文
     * @return BleAdvertiser
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static BleAdvertiser getBleAdvertiserInstance(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }
        if (bleAdvertiser == null) {
            synchronized (BleManager.class) {
                if (bleAdvertiser == null) {
                    bleAdvertiser = new BleAdvertiser(context);
                }
            }
        }
        return bleAdvertiser;
    }

    /**
     * 获取蓝牙广播单例实例
     *
     * @param context 上下文
     * @return BleAdvertiser
     */
    @Deprecated
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static BleAdvertiser newBleAdvertiser(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }

        return new BleAdvertiser(context);
    }


    /**
     * 获取BLE多连接单例
     *
     * @param context 上下文
     * @return BleMultiConnector
     */
    public static BleMultiConnector getBleMultiConnectorInstance(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }
        if (bleMultiConnector == null) {
            synchronized (BleManager.class) {
                if (bleMultiConnector == null) {
                    bleMultiConnector = new BleMultiConnector(context.getApplicationContext());
                }
            }
        }
        return bleMultiConnector;
    }




    /**
     * 获取BLE多连接单例
     *
     * @param context 上下文
     * @return BleMultiConnector
     */
    @Deprecated
    public static BleMultiConnector newBleMultiConnector(Context context) {
        if (!isSupportBle(context)) {
            return null;
        }
        return new BleMultiConnector(context);
    }

    /**
     * 判断当前手机蓝牙是否打开
     *
     * @param context 上下文
     * @return true表示蓝牙已打开
     */
    public static boolean isBluetoothOpened(Context context) {
        if (!isSupportBle(context)) {
            return false;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        if (bluetoothManager == null) {
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        return adapter != null && adapter.isEnabled();
    }

    /**
     * 请求打开蓝牙
     *
     * @param context 上下文
     * @return true表示请求发起成功（只是发起打开蓝牙的请求成功，并不是开启蓝牙成功）
     */
    public static boolean openBluetooth(Context context) {
        if (!isSupportBle(context)) {
            return false;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        if (bluetoothManager == null) {
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();

        return adapter != null && adapter.enable();

    }

    /**
     * 请求关闭蓝牙
     *
     * @param context 上下文
     * @return true表示请求发起成功（只是发起关闭蓝牙的请求成功，并不是关闭蓝牙成功）
     */
    public static boolean closeBluetooth(Context context) {
        if (!isSupportBle(context)) {
            return false;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        if (bluetoothManager == null) {
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();

        return adapter != null && adapter.disable();
    }

    /**
     * 释放BLE连接工具的资源
     */
    @SuppressWarnings("WeakerAccess")
    public static void releaseBleConnector() {
        if (bleConnector != null) {
            if (bleConnector.getContext() != null) {
                bleConnector.close();
            }
            bleConnector = null;
        }
    }

    /**
     * 释放BLE扫描器的资源
     */
    @SuppressWarnings("WeakerAccess")
    public static void releaseBleScanner() {
        if (bleScanner != null) {
            bleScanner.close();
            bleScanner = null;
        }
    }

    /**
     * 释放BLE多连接器的资源
     */
    @SuppressWarnings("WeakerAccess")
    public static void releaseBleMultiConnector() {
        if (bleMultiConnector != null) {
            bleMultiConnector.closeAll();
            bleConnector = null;
        }
    }

    /**
     * 释放BLE广播实例的资源
     */
    @SuppressWarnings("WeakerAccess")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void releaseBleBroadCastor() {
        if (bleAdvertiser != null) {
            bleAdvertiser.close();
            bleAdvertiser = null;
        }
    }

    /**
     * 释放全部实例的资源
     */
    public static void releaseAll() {
        releaseBleConnector();
        releaseBleScanner();
        releaseBleMultiConnector();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            releaseBleBroadCastor();
        }
    }

    /**
     * 设置是否要打印出调式信息
     * @param debugFlag true表示要打印
     */
    public static void setDebugFlag(boolean debugFlag) {
        Tool.setDebugFlag(debugFlag);
    }
}
