package com.aihuishou.xposedcopy;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION = "action";
    private static final String ACTION_START_ACCESSIBILITY_SETTING = "action_start_accessibility_setting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        Log.i("lgq","辅助功能是否开启" + hasServicePermission(this, AccessibilitySampleService.class));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //有些系统的辅助功能界面不是这个action，需要适配
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Throwable e) {
                    Toast.makeText(MainActivity.this, "无法打开系统设置", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

               /* try {
                    Intent intent = new Intent(this,  AccessibilityOpenHelperActivity.class);
                    intent.putExtra(ACTION, ACTION_START_ACCESSIBILITY_SETTING);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception ignore) {}*/

            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"toast", Toast.LENGTH_SHORT).show();
                //跳到手机显示界面
                toDeviceIntent();
            }
        });
    }

    private void toDeviceIntent() {
        Intent intent =  new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
        startActivity(intent);
    }


    /**
     * 判断辅助权限是否开启
     * @param ct
     * @param serviceClass
     * @return
     */
    public static boolean hasServicePermission(Context ct, Class serviceClass) {
        int ok = 0;
        try {
            ok = Settings.Secure.getInt(ct.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        TextUtils.SimpleStringSplitter ms = new TextUtils.SimpleStringSplitter(':');
        if (ok == 1) {
            String settingValue = Settings.Secure.getString(ct.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                ms.setString(settingValue);
                while (ms.hasNext()) {
                    String accessibilityService = ms.next();
                    if (accessibilityService.contains(serviceClass.getSimpleName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
