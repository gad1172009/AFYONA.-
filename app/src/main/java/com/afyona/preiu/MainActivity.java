package com.afyona.preiu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.provider.Settings;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {

    private int dp(float v) {
        return (int)(v * getResources().getDisplayMetrics().density + 0.5f);
    }

    private TextView text(String s, float size, int color, boolean bold) {
        TextView v = new TextView(this);
        v.setText(s);
        v.setTextSize(size);
        v.setTextColor(color);
        if (bold) v.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return v;
    }

    private LinearLayout card() {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(18), dp(15), dp(18), dp(15));
        c.setBackgroundColor(Color.rgb(18,18,26));
        return c;
    }

    private Button action(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setTextColor(Color.WHITE);
        b.setTextSize(13);
        return b;
    }

    private void addSpace(LinearLayout root, int h) {
        Space s = new Space(this);
        root.addView(s, new LinearLayout.LayoutParams(1, dp(h)));
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        getWindow().setStatusBarColor(Color.rgb(7,7,11));
        getWindow().setNavigationBarColor(Color.rgb(7,7,11));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(20), dp(18), dp(18));
        root.setBackgroundColor(Color.rgb(7,7,11));

        TextView logo = text("AFYONA", 31, Color.WHITE, true);
        root.addView(logo);

        root.addView(text(
                "PRE,IU,  •  GAMING CONTROL CENTER",
                11, Color.rgb(167,139,250), true));

        addSpace(root, 16);

        LinearLayout device = card();

        device.addView(text("DEVICE", 11,
                Color.rgb(150,150,165), true));

        String model = Build.MANUFACTURER + " " + Build.MODEL;
        String android = "Android " + Build.VERSION.RELEASE;

        device.addView(text(model, 18, Color.WHITE, true));
        device.addView(text(android, 12,
                Color.rgb(150,150,165), false));

        root.addView(device);

        addSpace(root, 12);

        LinearLayout shizuku = card();

        shizuku.addView(text("SHIZUKU", 11,
                Color.rgb(150,150,165), true));

        TextView shizukuStatus = text(
                "Checking service...",
                16, Color.rgb(251,191,36), true);

        shizuku.addView(shizukuStatus);

        Button shizukuButton = action("OPEN SHIZUKU");

        shizukuButton.setOnClickListener(v -> {
            try {
                Intent i = getPackageManager()
                        .getLaunchIntentForPackage("moe.shizuku.privileged.api");
                if (i != null) {
                    startActivity(i);
                } else {
                    Toast.makeText(this,
                            "Shizuku غير مثبت على الجهاز",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this,
                        "تعذر فتح Shizuku",
                        Toast.LENGTH_SHORT).show();
            }
        });

        shizuku.addView(shizukuButton);

        root.addView(shizuku);

        addSpace(root, 12);

        TextView title = text(
                "CONTROL CENTER",
                12, Color.rgb(150,150,165), true);

        root.addView(title);

        LinearLayout row1 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);

        Button gaming = action("🎮  GAMING");
        Button touch = action("🎯  TOUCH");

        row1.addView(gaming,
                new LinearLayout.LayoutParams(0, dp(58), 1));
        row1.addView(touch,
                new LinearLayout.LayoutParams(0, dp(58), 1));

        root.addView(row1);

        LinearLayout row2 = new LinearLayout(this);
        row2.setOrientation(LinearLayout.HORIZONTAL);

        Button display = action("🖥  DISPLAY");
        Button performance = action("⚡  PERFORMANCE");

        row2.addView(display,
                new LinearLayout.LayoutParams(0, dp(58), 1));
        row2.addView(performance,
                new LinearLayout.LayoutParams(0, dp(58), 1));

        root.addView(row2);

        touch.setOnClickListener(v ->
                Toast.makeText(this,
                        "Touch Center: Android والجهاز يحددان القيم الفعلية المتاحة.",
                        Toast.LENGTH_LONG).show());

        display.setOnClickListener(v ->
                Toast.makeText(this,
                        "Display: قراءة إعدادات الشاشة قبل تطبيق أي تغيير.",
                        Toast.LENGTH_SHORT).show());

        gaming.setOnClickListener(v ->
                Toast.makeText(this,
                        "Gaming Profile جاهز لإعدادات الجهاز الآمنة.",
                        Toast.LENGTH_SHORT).show());

        performance.setOnClickListener(v ->
                Toast.makeText(this,
                        "Performance: لا يتم تغيير إعدادات حساسة بدون توافق.",
                        Toast.LENGTH_SHORT).show());

        addSpace(root, 14);

        LinearLayout footer = card();
        footer.setGravity(Gravity.CENTER);

        footer.addView(text(
                "AFYONA PRE,IU,",
                13, Color.rgb(167,139,250), true));

        footer.addView(text(
                "Premium Gaming Utility",
                10, Color.rgb(120,120,135), false));

        root.addView(footer);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(7,7,11));
        scroll.addView(root);

        setContentView(scroll);
    }
}
