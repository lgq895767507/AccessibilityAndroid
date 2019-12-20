package com.aihuishou.xposedcopy;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Data: 2019-12-13
 * author: lewis
 */
public class FloatWindowManager {

    private static final String LOG_NAME = FloatWindowManager.class.getSimpleName();
    private ThreadPoolExecutor threadPoolExecutor;

    private FloatWindowManager() {

    }

    private volatile static FloatWindowManager INSTANCE;

    public static FloatWindowManager getInStance() {
        if (INSTANCE == null) {
            synchronized (FloatWindowManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FloatWindowManager();
                }
            }
        }
        return INSTANCE;
    }

    //模拟点击
    public void perforClick(AccessibilityService mService) {
        AccessibilityNodeInfo rootNodeInfo;
        rootNodeInfo = mService.getRootInActiveWindow();
        if (rootNodeInfo != null) {

            List<AccessibilityNodeInfo> nodeInfos = rootNodeInfo.findAccessibilityNodeInfosByViewId("android:id/summary");
            AccessibilityNodeInfo node;
            if (nodeInfos != null) {
                for (int i = 0; i < nodeInfos.size(); i++) {
                    node = nodeInfos.get(i);
                    Log.i(LOG_NAME, "view的text:" + node.getText());
                  /*  if (node.isEnabled()) {
                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }*/
                }
            }
        }
    }

    public void findByText(AccessibilityService mService) {
        AccessibilityNodeInfo rootNodeInfo;
        rootNodeInfo = mService.getRootInActiveWindow();
        if (rootNodeInfo != null) {

            List<AccessibilityNodeInfo> nodeInfos = rootNodeInfo.findAccessibilityNodeInfosByText("IMEI1");
            Log.i(LOG_NAME, "nodeInfos:" + nodeInfos);
            AccessibilityNodeInfo node;
            if (nodeInfos != null) {
                for (int i = 0; i < nodeInfos.size(); i++) {
                    node = nodeInfos.get(i);
                    Log.i(LOG_NAME, "view的text:" + node.getText());
                  /*  if (node.isEnabled()) {
                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }*/
                }
            }
        }
    }

    /**
     * 需要提供自动按键的功能
     * 每个功能单独一个方法
     * 统一管理的方法来分别处理
     */


   /* public void createFullScreenView(Context context) {
        WindowManager windowManager = getWindowManager(context);
        if (fullScreenView == null) {
            fullScreenView = new FloatWindowFullScreenView(context);
            WindowManager.LayoutParams fullScreenParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                fullScreenParams.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
            } else {
                fullScreenParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
            fullScreenParams.format = PixelFormat.TRANSLUCENT;
            fullScreenParams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            fullScreenParams.gravity = Gravity.CENTER;
            windowManager.addView(fullScreenView, fullScreenParams);
        }
    }*/
}
