package com.cunoraz.continuouscrollable;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cunoraz.library.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by cuneytcarikci on 17/08/2017.
 */

public class ContinuousScrollableImageView extends LinearLayout {

    //<editor-fold desc="DEFAULT_DIRECTION = LEFT">
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;


    @IntDef({UP, RIGHT, DOWN, LEFT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Directions {
    }

    @Directions
    int DEFAULT_DIRECTION = LEFT;
    //</editor-fold>


    //<editor-fold desc="DEFAULT_ASYMPTOTE = HORIZONTAL">
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    @interface Asymptote {
    }

    @Asymptote
    int DEFAULT_ASYMPTOTE = HORIZONTAL;
    //</editor-fold>


    //<editor-fold desc="DEFAULT_SCALE_TYPE = CENTER">
    public static final int MATRIX = 0;

    public static final int FIT_XY = 1;

    public static final int FIT_START = 2;

    public static final int FIT_CENTER = 3;

    public static final int FIT_END = 4;

    public static final int CENTER = 5;

    public static final int CENTER_CROP = 6;

    public static final int CENTER_INSIDE = 7;


    @IntDef({MATRIX, FIT_XY, FIT_START, FIT_CENTER, FIT_END, CENTER, CENTER_CROP, CENTER_INSIDE})
    @Retention(RetentionPolicy.SOURCE)
    @interface ScaleType {
    }

    @ScaleType
    int DEFAULT_SCALE_TYPE = FIT_CENTER;
    //</editor-fold>

    private final int DEFAULT_RESOURCE_ID = -1;
    private final int DEFAULT_DURATION = 3000;
    private int DIRECTION_MULTIPLIER = -1;

    private int duration;
    private int resourceId;
    private int direction;
    private int scaleType;

    private ValueAnimator animator;
    private ImageView firstImage;
    private ImageView secondImage;

    private boolean isBuilt = false;


    public static final String TAG = ContinuousScrollableImageView.class.getSimpleName();

    public ContinuousScrollableImageView(Context context) {
        super(context);
        init(context);
    }

    public ContinuousScrollableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContinuousScrollableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setViewAttributes(context, attrs, defStyleAttr);
        init(context);
    }

    private void setViewAttributes(Context context, AttributeSet attrs, int defStyleAttr) {


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ContinuousScrollableImageView, defStyleAttr, 0);

        resourceId = array.getResourceId(R.styleable.ContinuousScrollableImageView_imageSrc, DEFAULT_RESOURCE_ID);
        direction = array.getInt(R.styleable.ContinuousScrollableImageView_direction, DEFAULT_DIRECTION);
        duration = array.getInt(R.styleable.ContinuousScrollableImageView_duration, DEFAULT_DURATION);
        scaleType = array.getInt(R.styleable.ContinuousScrollableImageView_scaleType, DEFAULT_SCALE_TYPE);
        setDirectionFlags(direction);
        array.recycle();


    }

    private void setDirectionFlags(int direction) {
        switch (direction) {

            case UP:
                DIRECTION_MULTIPLIER = 1;
                DEFAULT_ASYMPTOTE = VERTICAL;
                break;
            case RIGHT:
                DIRECTION_MULTIPLIER = -1;
                DEFAULT_ASYMPTOTE = HORIZONTAL;
                break;
            case DOWN:
                DIRECTION_MULTIPLIER = -1;
                DEFAULT_ASYMPTOTE = VERTICAL;
                break;
            case LEFT:
                DIRECTION_MULTIPLIER = 1;
                DEFAULT_ASYMPTOTE = HORIZONTAL;
                break;
        }

    }

    /**
     * @param context
     */
    private void init(Context context) {
        inflate(context, R.layout.continuos_scrollable_imageview_layout, this);
        build();
    }


    /**
     * Set duration in ms
     *
     * @param duration
     */

    public void setDuration(int duration) {
        this.duration = duration;
        isBuilt = false;
        build();
    }


    /**
     * set scrolling direction
     *
     * @param direction
     */

    public void setDirection(@Directions int direction) {
        this.direction = direction;
        isBuilt = false;
        setDirectionFlags(direction);
        build();
    }

    /**
     * @param resourceId
     */

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
        firstImage.setImageResource(this.resourceId);
        secondImage.setImageResource(this.resourceId);
    }


    /**
     * set image scale type
     *
     * @param scaleType
     */

    public void setScaleType(@ScaleType int scaleType) {
        if (firstImage == null || secondImage == null) {
            throw new NullPointerException();
        }
        ImageView.ScaleType type = ImageView.ScaleType.CENTER;
        switch (scaleType) {

            case MATRIX:
                type = ImageView.ScaleType.MATRIX;
                break;

            case FIT_XY:
                type = ImageView.ScaleType.FIT_XY;
                break;

            case FIT_START:
                type = ImageView.ScaleType.FIT_START;
                break;

            case FIT_CENTER:
                type = ImageView.ScaleType.FIT_CENTER;
                break;

            case FIT_END:
                type = ImageView.ScaleType.FIT_END;
                break;

            case CENTER:
                type = ImageView.ScaleType.CENTER;
                break;

            case CENTER_CROP:
                type = ImageView.ScaleType.CENTER_CROP;
                break;

            case CENTER_INSIDE:
                type = ImageView.ScaleType.CENTER_INSIDE;
                break;

        }
        this.scaleType = scaleType;
        firstImage.setScaleType(type);
        secondImage.setScaleType(type);
    }

    private void build() {
        if (isBuilt)
            return;

        isBuilt = true;
        setImages();

        if (animator != null)
            animator.removeAllUpdateListeners();

        animator = ValueAnimator.ofFloat(0.0f, DIRECTION_MULTIPLIER * (-1.0f));
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(duration);

        switch (DEFAULT_ASYMPTOTE) {
            case HORIZONTAL:
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        {
                            float progress = DIRECTION_MULTIPLIER * (-(float) animation.getAnimatedValue());
                            float width = DIRECTION_MULTIPLIER * (-firstImage.getWidth());
                            float translationX = width * progress;
                            firstImage.setTranslationX(translationX);
                            secondImage.setTranslationX(translationX - width);
                        }
                    }
                });
                break;

            case VERTICAL:

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        {
                            float progress = DIRECTION_MULTIPLIER * (-(float) animation.getAnimatedValue());
                            float height = DIRECTION_MULTIPLIER * (-firstImage.getHeight());
                            float translationY = height * progress;
                            firstImage.setTranslationY(translationY);
                            secondImage.setTranslationY(translationY - height);
                        }
                    }
                });

                break;
        }

        animator.start();


    }

    private void setImages() {
        if (resourceId == -1) {
            Log.e(TAG, "image must be initialized before it can be used. You can use in XML like this: (app:imageSrc=\"@drawable/yourImage\") ");
            return;
        }
        firstImage = this.findViewById(R.id.first_image);
        secondImage = this.findViewById(R.id.second_image);
        firstImage.setImageResource(resourceId);
        secondImage.setImageResource(resourceId);
        setScaleType(scaleType);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null)
            animator.cancel();
    }


    public static final class Builder {

        private ContinuousScrollableImageView scrollableImageView;

        public Builder(Activity activity) {
            scrollableImageView = new ContinuousScrollableImageView(activity);
        }

        public Builder setDuration(int duration) {
            scrollableImageView.setDuration(duration);
            return this;
        }

        public Builder setResourceId(int resourceId) {
            scrollableImageView.setResourceId(resourceId);
            return this;
        }

        public Builder setDirection(@Directions int direction) {
            scrollableImageView.setDirection(direction);
            return this;
        }

        public Builder setScaleType(@ScaleType int scaleType) {
            scrollableImageView.setScaleType(scaleType);
            return this;
        }

        public ContinuousScrollableImageView build() {

            return scrollableImageView;
        }
    }
}
