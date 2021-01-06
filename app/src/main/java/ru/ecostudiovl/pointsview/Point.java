package ru.ecostudiovl.pointsview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Point {

    private enum Position{
        LD,
        RD,
        LU,
        RU
    }

    private boolean isFirstPoint = false;
    private float x, y;
    private Position position;
    private Point parentPoint;
    private Point lD, rD, lU, rU;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void addPoint(Point p){
        p.parentPoint = this;

        if (p.x > x && p.y > y){
            if (rU == null){
                p.position = Position.RU;
                rU = p;
            }
            else {
                rU.addPoint(p);
            }
        }
        else if(p.x < x && p.y < y){
            if (lD == null){
                p.position = Position.LD;
                lD = p;
            }
            else {
                lD.addPoint(p);
            }
        }
        else if(p.x < x && p.y > y){
            if (lU == null){
                p.position = Position.LU;
                lU = p;
            }
            else {
                lU.addPoint(p);
            }
        }
        else if(p.x > x && p.y < y){
            if (rD == null){
                p.position = Position.RD;
                rD = p;
            }
            else {
                rD.addPoint(p);
            }
        }

    }

    public Point find(float x, float y){

        if (this.x == x && this.y == y){
            return this;
        }
        else {
            if (lD != null && lD.find(x, y) != null){
                return lD.find(x, y);
            }
            if (rD != null && rD.find(x, y) != null){
                return rD.find(x, y);
            }
            if (lU != null && lU.find(x, y) != null){
                return lU.find(x, y);
            }
            if (rU != null && rU.find(x, y) != null){
                return rU.find(x, y);
            }
        }
        return null;
    }


    public Point findByDelta(float x, float y, float delta){

        if (Math.abs(this.x - x) <= delta && Math.abs(this.y - y) <= delta){
            return this;
        }
        else {
            if (lD != null && lD.findByDelta(x, y, delta) != null){
                return lD.find(x, y);
            }
            if (rD != null && rD.findByDelta(x, y, delta) != null){
                return rD.find(x, y);
            }
            if (lU != null && lU.findByDelta(x, y, delta) != null){
                return lU.find(x, y);
            }
            if (rU != null && rU.findByDelta(x, y, delta) != null){
                return rU.find(x, y);
            }
        }
        return null;
    }

    public void remove(Point pointToDelete){
        if (this.x == pointToDelete.x && this.y == pointToDelete.y){

            readChildPoints(new PointsReader() {
                @Override
                public void onPointReaded(Point point) {
                    parentPoint.addPoint(point);
                }
            });

            switch (position){
                case LD:
                    parentPoint.lD = null;
                    break;
                case LU:
                    parentPoint.lU = null;
                    break;
                case RD:
                    parentPoint.rD = null;
                    break;
                case RU:
                    parentPoint.rU = null;
                    break;
            }
        }
        else {
            if (lD != null){
                lD.remove(pointToDelete);
            }
            if (rD != null){
                rD.remove(pointToDelete);
            }
            if (lU != null){
                lU.remove(pointToDelete);
            }
            if (rU != null){
                rU.remove(pointToDelete);
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

    public void readPoints(PointsReader pointsReader){
        pointsReader.onPointReaded(this);
        if (lD != null){
            lD.readPoints(pointsReader);
        }
        if (rD != null){
            rD.readPoints(pointsReader);
        }
        if (lU != null){
            lU.readPoints(pointsReader);
        }
        if (rU != null){
            rU.readPoints(pointsReader);
        }
    }

    public void readChildPoints(PointsReader pointsReader){

        if (lD != null){
            pointsReader.onPointReaded(lD);
            lD.readPoints(pointsReader);
        }
        if (rD != null){
            pointsReader.onPointReaded(rD);
            rD.readPoints(pointsReader);
        }
        if (lU != null){
            pointsReader.onPointReaded(lU);
            lU.readPoints(pointsReader);
        }
        if (rU != null){
            pointsReader.onPointReaded(rU);
            rU.readPoints(pointsReader);
        }
    }

    public boolean isCore(){
        if (lD != null){
            return false;
        }
        if (rD != null){
            return false;
        }
        if (lU != null){
            return false;
        }
        if (rU != null){
            return false;
        }

        return true;
    }

    public int size(){

        final int[] size = new int[]{0};

        readPoints(new PointsReader() {
            @Override
            public void onPointReaded(Point point) {
                size[0]++;
            }
        });

        return size[0];
    }

    public interface PointsReader{
        void onPointReaded(Point point);
    }

    public boolean isFirstPoint() {
        return isFirstPoint;
    }

    public void setFirstPoint(boolean firstPoint) {
        isFirstPoint = firstPoint;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Point getParentPoint() {
        return parentPoint;
    }

    public void setParentPoint(Point parentPoint) {
        this.parentPoint = parentPoint;
    }

    public Point getlD() {
        return lD;
    }

    public void setlD(Point lD) {
        this.lD = lD;
    }

    public Point getrD() {
        return rD;
    }

    public void setrD(Point rD) {
        this.rD = rD;
    }

    public Point getlU() {
        return lU;
    }

    public void setlU(Point lU) {
        this.lU = lU;
    }

    public Point getrU() {
        return rU;
    }

    public void setrU(Point rU) {
        this.rU = rU;
    }

}
