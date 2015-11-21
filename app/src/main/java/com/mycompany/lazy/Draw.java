package com.mycompany.lazy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Draw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyDraw(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    ArrayList<Float> pointX = new ArrayList<Float>();
    ArrayList<Float>  pointY = new ArrayList<Float>();
    ArrayList<PointF> points = new ArrayList<PointF>();
    Paint paint = new Paint();

    public class MyDraw extends View{
        public MyDraw(Context ctx){
            super(ctx);
        }

        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            setPaint();
            int j=0;
            for(int i=j;i<pointX.size();i++){
                if(i!=pointX.size()-1) {
                    canvas.drawLine(pointX.get(i), pointY.get(i), pointX.get(i + 1), pointY.get(i + 1), paint);
                    j++;
                }
                else {
                    break;
                }
            }
        }

        public void setPaint(){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
        }

        public boolean onTouchEvent(MotionEvent e) {
            Float tempPx,tempPy;
            tempPx = e.getX();
            tempPy = e.getY();
            pointX.add(tempPx);
            pointY.add(tempPy);


            switch(e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    tempPx = e.getX();
                    tempPy = e.getY();
                    pointX.add(tempPx);
                    pointY.add(tempPy);
                    return true;
                case MotionEvent.ACTION_MOVE: //finger dragged
                    tempPx = e.getX();
                    tempPy = e.getY();
                    pointX.add(tempPx);
                    pointY.add(tempPy);
                    break;
                case MotionEvent.ACTION_UP:
                    break; //finger lifted
                default:
                    return false;
            }
            //repaint
            invalidate();
            return true;
        }
    }

}
