package com.github.jinatonic.confetti.sample;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.github.jinatonic.confetti.ConfettiManager;
import com.github.jinatonic.confetti.Utils;
import com.github.jinatonic.confetti.confetti.BitmapConfetti;
import com.github.jinatonic.confetti.confetti.Confetti;

import java.util.List;
import java.util.Random;

public abstract class AbstractActivity extends AppCompatActivity implements
        ConfettiManager.ConfettiGenerator, View.OnClickListener {
    protected ViewGroup container;

    private int confettiSize;
    protected float velocitySlow, velocityNormal, velocityFast;
    private int[] colors;
    private List<Bitmap> bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (ViewGroup) findViewById(R.id.container);
        findViewById(R.id.generate_confetti_btn).setOnClickListener(this);

        final Resources res = getResources();
        confettiSize = res.getDimensionPixelSize(R.dimen.default_confetti_size);
        velocitySlow = res.getDimensionPixelSize(R.dimen.default_velocity_slow);
        velocityNormal = res.getDimensionPixelSize(R.dimen.default_velocity_normal);
        velocityFast = res.getDimensionPixelSize(R.dimen.default_velocity_fast);

        colors = new int[] {
                res.getColor(R.color.gold_dark),
                res.getColor(R.color.gold_med),
                res.getColor(R.color.gold),
                res.getColor(R.color.gold_light),
        };
        bitmaps = Utils.generateConfettiBitmaps(colors, confettiSize);
    }

    @Override
    public Confetti generateConfetti(Random random) {
        return new BitmapConfetti(bitmaps.get(random.nextInt(bitmaps.size())));
    }
}
