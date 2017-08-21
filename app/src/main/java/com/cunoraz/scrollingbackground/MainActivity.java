package com.cunoraz.scrollingbackground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cunoraz.continuouscrollable.ContinuousScrollableImageView;


public class MainActivity extends AppCompatActivity {


    LinearLayout rootLayout;
    ContinuousScrollableImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);

        /**
         *  before uncomment these lines remove ContinuousScrollableImageView in activity_main layout
         */

       /* rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        image = new ContinuousScrollableImageView(this);
        image.setResourceId(R.drawable.bg_sample);
        image.setDirection(ContinuousScrollableImageView.DOWN);
        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        image.setScaleType(ContinuousScrollableImageView.FIT_XY);
        image.setDuration(3000);
        rootLayout.addView(image);*/

 /*       image = new ContinuousScrollableImageView.Builder(MainActivity.this)
                .setResourceId(R.drawable.bg_sample)
                .setDirection(ContinuousScrollableImageView.UP)
                .setDuration(3000)
                .setScaleType(ContinuousScrollableImageView.FIT_XY)
                .build();
                rootLayout.addView(image);
        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        rootLayout.addView(image);*/


    }
}
