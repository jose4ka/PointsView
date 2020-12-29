package ru.ecostudiovl.pointsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class PointsView extends View {

    public PointsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2, p);
        canvas.drawLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight(), p);

        Point point = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);

        p.setColor(Color.BLACK);
        point.isFirstPoint = true;

        float x = 0;
        float y = 0;

        for (int i = 0; i < 16; i++) {
//            x = new Random().nextInt(canvas.getWidth() / 2) * (new Random().nextBoolean() ? 1 : -1);
//            y = new Random().nextInt(canvas.getHeight() / 2) * (new Random().nextBoolean() ? 1 : -1);

            x = (point.x + (new Random().nextInt(300) * (new Random().nextBoolean() ? 1 : -1)));
            y = (point.y + (new Random().nextInt(300)* (new Random().nextBoolean() ? 1 : -1)));
            point.addPoint(new Point(x, y), canvas, p);

        }

    }
}
