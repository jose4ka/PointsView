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

    private boolean isDrawStroke;
    private boolean isDrawInternalNetworks;

    public PointsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        isDrawStroke = false;
        isDrawInternalNetworks = false;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        final Paint p = new Paint();
        p.setColor(Color.RED);


        canvas.drawLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2, p);
        canvas.drawLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight(), p);



        final Point firstPoint = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);

        p.setColor(Color.BLACK);
        firstPoint.setFirstPoint(true);

        float x = 0;
        float y = 0;

        for (int i = 0; i < 4; i++) {
            x = (firstPoint.getX() + (new Random().nextInt(300) * (new Random().nextBoolean() ? 1 : -1)));
            y = (firstPoint.getY() + (new Random().nextInt(300)* (new Random().nextBoolean() ? 1 : -1)));
            firstPoint.addPoint(new Point(x, y));
        }


        firstPoint.readPoints(new Point.PointsReader() {
            Point lastPoint = firstPoint;
            int items = 0;
            @Override
            public void onPointReaded(Point point) {

                p.setColor(Color.BLACK);
                canvas.drawCircle(point.getX(), point.getY(), 3, p);

                if (isDrawStroke){

                    items++;

                    if (point.getParentPoint() != null){


                        p.setColor(Color.BLUE);
                        canvas.drawLine(lastPoint.getX(),
                                lastPoint.getY(),
                                point.getX(),
                                point.getY(),
                                p);

                        lastPoint = point;

                        if (items == firstPoint.size()){

                            canvas.drawLine(lastPoint.getX(),
                                    lastPoint.getY(),
                                    firstPoint.getX(),
                                    firstPoint.getY(),
                                    p);

                        }


                    }
                }


            }
        });

    }


    public boolean isDrawStroke() {
        return isDrawStroke;
    }

    public void setDrawStroke(boolean drawStroke) {
        isDrawStroke = drawStroke;
    }

    public boolean isDrawInternalNetworks() {
        return isDrawInternalNetworks;
    }

    public void setDrawInternalNetworks(boolean drawInternalNetworks) {
        isDrawInternalNetworks = drawInternalNetworks;
    }
}
