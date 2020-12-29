package ru.ecostudiovl.pointsview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Point {

    boolean isFirstPoint = false;
    float x, y;
    Point parentPoint;
    Point lD, rD, lU, rU;
    int index = 0;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void addPoint(Point p, Canvas c, Paint paint){
        p.parentPoint = this;
        p.index = index + 1;
        if (p.x > x && p.y > y){
            if (rU == null){
                rU = p;
                draw(p.x, p.y, c, paint);
            }
            else {
                rU.addPoint(p, c, paint);
            }
        }
        else if(p.x < x && p.y < y){
            if (lD == null){
                lD = p;
                draw(p.x, p.y, c, paint);
            }
            else {
                lD.addPoint(p, c, paint);
            }
        }
        else if(p.x < x && p.y > y){
            if (lU == null){
                lU = p;
                draw(p.x, p.y, c, paint);
            }
            else {
                lU.addPoint(p, c, paint);
            }
        }
        else if(p.x > x && p.y < y){
            if (rD == null){
                rD = p;
                draw(p.x, p.y, c, paint);
            }
            else {
                rD.addPoint(p, c, paint);
            }
        }
        if (isFirstPoint){
            Paint nP = new Paint();
            nP.setColor(Color.RED);
            c.drawCircle(x, y, 3, paint);
        }
    }

    void  draw(float nX, float nY, Canvas c, Paint paint){
        c.drawCircle(x, y, 3, paint);
        c.drawLine(x, y, nX, nY, paint);
        c.drawCircle(nX, nY, 3, paint);
        c.drawText(x +" : "+y, x + 10, y, paint);
        c.drawText(nX +" : "+nY, nX + 10, nY, paint);
    }
    
    Point find(float x, float y){

        if (this.x == x && this.y == y){

            return this;
        }
        else {
            System.out.println("NOT FINDED!");
            for (int i = 0; i < 4; i++) {
                switch (i){
                    case 0:
                        if (rU != null){
                            return rU.find(x, y);
                        }
                        else continue;
                    case 1:
                        if (lU != null){
                            return lU.find(x, y);
                        }
                        else continue;

                    case 2:
                        if (rD != null){
                            return rD.find(x, y);
                        }
                        else continue;
                    case 3:
                        if (lD != null){
                            return lD.find(x, y);
                        }
                        else continue;
                }
            }
        }
        return null;
    }

    void showPoints(){
        System.out.println("Show "+x+ " : "+y);
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    if (rU != null){
                        System.out.print("ru " );
                        rU.showPoints();
                    }
                    else continue;
                case 1:
                    if (lU != null){
                        System.out.print("lu " );
                        lU.showPoints();
                    }
                    else continue;

                case 2:
                    if (rD != null){
                        System.out.print("rd " );
                        rD.showPoints();
                    }
                    else continue;
                case 3:
                    if (lD != null){
                        System.out.print("ld " );
                        lD.showPoints();
                    }
                    else continue;
            }
        }

    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
