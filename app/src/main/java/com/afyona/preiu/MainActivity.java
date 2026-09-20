package com.afyona.preiu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.provider.Settings;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import rikka.shizuku.Shizuku;

public class MainActivity extends Activity {

    private final int BG = Color.rgb(7, 7, 11);
    private final int CARD = Color.rgb(18, 18, 26);
    private final int PURPLE = Color.rgb(139, 92, 246);
    private final int PURPLE2 = Color.rgb(167, 139, 250);
    private final int WHITE = Color.WHITE;
    private final int MUTED = Color.rgb(150, 150, 168);
    private final int GREEN = Color.rgb(74, 222, 128);
    private final int RED = Color.rgb(248, 113, 113);

    private LinearLayout content;
    private TextView shizukuStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildUI();
    }

    private void buildUI() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(BG);

        TextView header = text("AFYONA", 30, WHITE);
        header.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        header.setPadding(24, 30, 24, 4);
        root.addView(header);

        TextView premium = text("PREMIUM PERFORMANCE CENTER", 12, PURPLE2);
        premium.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        premium.setPadding(24, 0, 24, 20);
        root.addView(premium);

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(16, 4, 16, 30);

        addStatusCard();
        addSection("🎮", "Gaming Mode",
                "Performance profile and gaming tools.");
        addSection("🎯", "Touch",
                "Touch response and gaming sensitivity tools.");
        addSection("🖥", "Display",
                "Screen information and display controls.");
        addSection("⚡", "Performance",
                "Device performance information and profiles.");
        addSection("📱", "Device Info",
                "CPU, RAM, Android and device information.");
        addShizukuCard();
        addSection("💾", "Profiles",
                "Switch between your AFYONA profiles.");

        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        setContentView(root);
    }

    private void addStatusCard() {
        LinearLayout box = card();

        TextView title = text("SYSTEM STATUS", 13, MUTED);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView device = text(
                Build.MANUFACTURER + " " + Build.MODEL,
                21, WHITE);
        device.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView android = text(
                "Android " + Build.VERSION.RELEASE +
                "  •  SDK " + Build.VERSION.SDK_INT,
                13, MUTED);

        TextView ready = text("● AFYONA READY", 13, GREEN);
        ready.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        box.addView(title);
        box.addView(device);
        box.addView(android);
        box.addView(ready);
    }

    private void addSection(String icon, String title, String description) {
        LinearLayout box = card();

        TextView heading = text(icon + "  " + title, 20, WHITE);
        heading.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView desc = text(description, 14, MUTED);

        TextView button = text("OPEN  ›", 13, WHITE);
        button.setGravity(Gravity.CENTER);
        button.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        button.setBackgroundColor(PURPLE);

        button.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    title + " • Ready",
                    Toast.LENGTH_SHORT
            ).show();
        });

        box.addView(heading);
        box.addView(desc);

        LinearLayout.LayoutParams bp =
                new LinearLayout.LayoutParams(-1, 52);
        bp.topMargin = 14;
        box.addView(button, bp);
    }

    private void addShizukuCard() {
        LinearLayout box = card();

        TextView heading = text("🔗  Shizuku", 20, WHITE);
        heading.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        shizukuStatus = text("", 14, MUTED);

        TextView refresh = text("CHECK STATUS", 13, WHITE);
        refresh.setGravity(Gravity.CENTER);
        refresh.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        refresh.setBackgroundColor(PURPLE);

        refresh.setOnClickListener(v -> updateShizukuStatus());

        box.addView(heading);
        box.addView(shizukuStatus);

        LinearLayout.LayoutParams bp =
                new LinearLayout.LayoutParams(-1, 52);
        bp.topMargin = 14;
        box.addView(refresh, bp);

        updateShizukuStatus();
    }

    private void updateShizukuStatus() {
        try {
            if (Shizuku.pingBinder()) {
                shizukuStatus.setText("● Shizuku connected");
                shizukuStatus.setTextColor(GREEN);
            } else {
                shizukuStatus.setText(
                        "● Shizuku not running — start Shizuku to enable advanced controls."
                );
                shizukuStatus.setTextColor(RED);
            }
        } catch (Throwable e) {
            shizukuStatus.setText("● Shizuku unavailable");
            shizukuStatus.setTextColor(RED);
        }
    }

    private LinearLayout card() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(20, 18, 20, 18);
        box.setBackgroundColor(CARD);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, -2);
        p.bottomMargin = 14;

        content.addView(box, p);
        return box;
    }

    private TextView text(String value, float size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setPadding(0, 4, 0, 4);
        return t;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (shizukuStatus != null) {
            updateShizukuStatus();
        }
    }
}
