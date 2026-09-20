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

public class MainActivity extends Activity {

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private TextView text(String value, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);

        if (bold) {
            t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }

        return t;
    }

    private LinearLayout card(String title, String value) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(18), dp(14), dp(18), dp(14));
        box.setBackgroundColor(Color.rgb(20, 20, 30));

        TextView titleView = text(title, 12, Color.rgb(160, 160, 175), false);
        TextView valueView = text(value, 20, Color.WHITE, true);

        box.addView(titleView);
        box.addView(valueView);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(0, dp(90), 1);

        params.setMargins(dp(6), dp(6), dp(6), dp(6));
        box.setLayoutParams(params);

        return box;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(8, 8, 13));
        getWindow().setNavigationBarColor(Color.rgb(8, 8, 13));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(20), dp(18), dp(12));
        root.setBackgroundColor(Color.rgb(8, 8, 13));

        TextView brand = text(
                "AFYONA",
                28,
                Color.WHITE,
                true
        );

        root.addView(brand, new LinearLayout.LayoutParams(
                -1, dp(45)
        ));

        TextView subtitle = text(
                "PRE,IU,  •  PREMIUM PERFORMANCE",
                12,
                Color.rgb(139, 92, 246),
                true
        );

        root.addView(subtitle, new LinearLayout.LayoutParams(
                -1, dp(30)
        ));

        LinearLayout status = new LinearLayout(this);
        status.setPadding(dp(14), dp(10), dp(14), dp(10));
        status.setBackgroundColor(Color.rgb(18, 28, 24));

        TextView statusText = text(
                "●  SHIZUKU  •  CHECKING",
                13,
                Color.rgb(74, 222, 128),
                true
        );

        status.addView(statusText);

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(-1, dp(48));

        statusParams.setMargins(0, dp(10), 0, dp(14));
        root.addView(status, statusParams);

        TextView boost = text(
                "⚡  BOOST",
                20,
                Color.WHITE,
                true
        );

        boost.setGravity(Gravity.CENTER);
        boost.setBackgroundColor(Color.rgb(124, 58, 237));

        root.addView(boost, new LinearLayout.LayoutParams(
                -1, dp(62)
        ));

        TextView overview = text(
                "DEVICE OVERVIEW",
                13,
                Color.rgb(170, 170, 185),
                true
        );

        LinearLayout.LayoutParams overviewParams =
                new LinearLayout.LayoutParams(-1, dp(35));

        overviewParams.setMargins(0, dp(18), 0, dp(2));
        root.addView(overview, overviewParams);

        LinearLayout row1 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);

        row1.addView(card("RAM", "--"));
        row1.addView(card("CPU", "--"));

        root.addView(row1, new LinearLayout.LayoutParams(
                -1, dp(102)
        ));

        LinearLayout row2 = new LinearLayout(this);
        row2.setOrientation(LinearLayout.HORIZONTAL);

        row2.addView(card("DPI", "--"));
        row2.addView(card("REFRESH", "--"));

        root.addView(row2, new LinearLayout.LayoutParams(
                -1, dp(102)
        ));

        TextView footer = text(
                "Gaming • Touch • Display • Performance",
                11,
                Color.rgb(110, 110, 125),
                false
        );

        footer.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams footerParams =
                new LinearLayout.LayoutParams(-1, dp(40));

        footerParams.setMargins(0, dp(10), 0, 0);
        root.addView(footer, footerParams);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.addView(root);

        setContentView(scroll);
    }
}
