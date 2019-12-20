package com.aihuishou.xposedcopy;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Data: 2019-12-13
 * author: lewis
 */
public class AccessibilitySampleService extends AccessibilityService {

    private static final String LOG_NAME = AccessibilitySampleService.class.getSimpleName();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // 此方法是在主线程中回调过来的，所以消息是阻塞执行的
        // 获取包名
        String pkgName = event.getPackageName().toString();
        int eventType = event.getEventType();
        //       AccessibilityOperator.getInstance().updateEvent(this, event);
//        try {
//            Thread.sleep(200);
//        } catch (Exception e) {}
        Log.i("lgq", "eventType: " + eventType + " pkgName: " + pkgName);

        System.out.println("eventType: " + eventType + " pkgName: " + pkgName);
        //------------------模拟点击事件
        FloatWindowManager.getInStance().findByText(this);
    }

    @Override
    public void onInterrupt() {

    }
}
