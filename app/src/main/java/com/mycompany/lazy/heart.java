package com.mycompany.lazy;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class heart extends View {

    Movie movie,movie1;
    InputStream is = null,is1 = null;
    long moviestart;


    public heart(Context context)
    {
        super(context);

    }
    public heart(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }
    public heart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        is = getResources().openRawResource(+R.drawable.forwardrun);
        movie = Movie.decodeStream(is);

        long now=android.os.SystemClock.uptimeMillis();
        System.out.println("now="+now);
        if (moviestart == 0) { // first time
            moviestart = now;

        }
        System.out.println("\tmoviestart=" + moviestart);
        int relTime = (int)((now - moviestart) % movie.duration()) ;
        System.out.println("time=" + relTime + "\treltime=" + movie.duration());
        movie.setTime(relTime);

        movie.draw(canvas, this.getWidth() / 4, this.getHeight() / 3);
        this.invalidate();

    }

}









