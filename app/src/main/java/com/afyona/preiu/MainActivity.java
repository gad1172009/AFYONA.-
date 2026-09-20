package com.afyona.preiu;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    int bg = Color.rgb(10, 10, 18);
    int card = Color.rgb(22, 22, 34);
    int purple = Color.rgb(142, 68, 255);
    int white = Color.WHITE;
    int gray = Color.rgb(170, 170, 185);

    LinearLayout root;
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);

        TextView header = new TextView(this);
        header.setText("AFYONA");
        header.setTextColor(white);
        header.setTextSize(28);
        header.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(24, 32, 24, 20);
        root.addView(header, new LinearLayout.LayoutParams(-1, 90));

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(18, 5, 18, 30);

        addHero();
        addSection("⚡ Performance", "Boost performance and manage your device profile.");
        addSection("🎮 Gaming Mode", "Gaming profile • Performance boost • Game tools");
        addSection("🎯 Touch", "Touch response • Sensitivity • Gaming touch profile");
        addSection("🖥 Display", "DPI • Screen information • Display profile");
        addSection("📱 Device Info", "CPU • RAM • Android version • Device information");
        addSection("🔗 Shizuku", "Connection status and service information");
        addSection("💾 Profiles", "Create and switch between AFYONA profiles");

        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        setContentView(root);
    }

    void addHero() {
        LinearLayout box = card();

        TextView title = text("AFYONA PREMIUM", 24, white);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView sub = text(
                "Performance Control Center",
                15,
                gray
        );

        TextView status = text(
                "● SYSTEM READY",
                13,
                Color.rgb(80, 220, 140)
        );

        box.addView(title);
        box.addView(sub);
        box.addView(status);

        content.addView(box);
    }

    void addSection(String title, String description) {
        LinearLayout box = card();

        TextView t = text(title, 19, white);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView d = text(description, 14, gray);

        TextView button = text("OPEN  ›", 13, Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setBackgroundColor(purple);
        button.setPadding(18, 12, 18, 12);

        button.setOnClickListener(v ->
                Toast.makeText(
                        MainActivity.this,
                        title + " opened",
                        Toast.LENGTH_SHORT
                ).show()
        );

        box.addView(t);
        box.addView(d);

        LinearLayout.LayoutParams bp =
                new LinearLayout.LayoutParams(-1, 52);
        bp.topMargin = 14;
        box.addView(button, bp);

        content.addView(box);
    }

    LinearLayout card() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(22, 20, 22, 20);
        box.setBackgroundColor(card);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, -2);
        p.bottomMargin = 14;

        content.addView(box, p);
        return box;
    }

    TextView text(String value, float size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setPadding(0, 5, 0, 5);
        return t;
    }
}
