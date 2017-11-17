package cn.almsound.www.myblesample.callback;

import android.bluetooth.BluetoothGatt;
import android.graphics.Color;

import com.jackiepenghe.baselibrary.Tool;
import com.jackiepenghe.blelibrary.BaseConnectCallback;

import cn.almsound.www.myblesample.wideget.CustomTextCircleView;

/**
 *
 * @author alm
 * @date 2017/11/15
 */

public class Device3Callback extends BaseConnectCallback {

    private static final String TAG = "Device3Callback";

    private CustomTextCircleView customTextCircleView;
    public Device3Callback(CustomTextCircleView customTextCircleView) {
        this.customTextCircleView = customTextCircleView;
    }
    /**
     * 蓝牙连接后无法正常进行服务发现时回调
     *
     * @param gatt BluetoothGatt
     */
    @Override
    public void onDiscoverServicesFailed(BluetoothGatt gatt) {
        Tool.warnOut(TAG,"onDiscoverServicesFailed");
        customTextCircleView.setColor(Color.RED);
    }

    /**
     * 蓝牙GATT被关闭时回调
     * @param address 设备地址
     */
    @Override
    public void onGattClosed(String address) {
        Tool.warnOut(TAG,"onGattClosed");
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt) {
        Tool.warnOut(TAG,"设备3 onServicesDiscovered");
        customTextCircleView.setColor(Color.GREEN);
    }

    @Override
    public void onDisConnected(BluetoothGatt gatt) {
        customTextCircleView.setColor(Color.RED);
    }

    @Override
    public void onConnected(BluetoothGatt gatt) {
        customTextCircleView.setColor(Color.BLUE);
    }
}
