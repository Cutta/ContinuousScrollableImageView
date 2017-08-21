# ContinuousScrollableImageView
Library for animating images with continuous scrolling effects


Simple android view to display images with continuous scrolling effects efficiently. 
You can set image source, scaleType, duration and direction.
Example usages can be found in sample project.

### SCREENS

![](https://raw.githubusercontent.com/Cutta/ContinuousScrollableImageView/master/gifs/bus.gif?token=AGY3KeEx21hh_AntxZBgwhLv9L3zaTYAks5ZpH8fwA%3D%3D)  |  ![](https://raw.githubusercontent.com/Cutta/ContinuousScrollableImageView/master/gifs/plane.gif?token=AGY3KeydGmvTnBrToMTTuw4wsIpyS9Dzks5ZpH9hwA%3D%3D) ![](https://raw.githubusercontent.com/Cutta/ContinuousScrollableImageView/master/gifs/buildings.gif?token=AGY3KbY_7tkwoAGL3c_Px-YqHptLUZBDks5ZpIDSwA%3D%3D)
  |  ![](https://raw.githubusercontent.com/Cutta/ContinuousScrollableImageView/master/gifs/clouds.gif?token=AGY3KeblVnwY9luH9bUIyr3o_32r68k8ks5ZpIDBwA%3D%3D)

### JAVA USAGE

#### Classic Way
``` java

 image = new ContinuousScrollableImageView(this);
        image.setResourceId(R.drawable.bg_sample);
        image.setDirection(ContinuousScrollableImageView.DOWN);
        image.setScaleType(ContinuousScrollableImageView.FIT_XY);
        image.setDuration(3000);
        rootLayout.addView(image);

```
#### Builder Way
``` java

image = new ContinuousScrollableImageView.Builder(MainActivity.this)
                .setResourceId(R.drawable.bg_sample)
                .setDirection(ContinuousScrollableImageView.UP)
                .setDuration(3000)
                .setScaleType(ContinuousScrollableImageView.FIT_XY)
                .build();
                rootLayout.addView(image);

```



### XML USAGE
``` xml
       <com.cunoraz.continuouscrollable.ContinuousScrollableImageView
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            app:direction="right"
            app:scaleType="centerInside"
            app:duration="2500"
            app:imageSrc="@drawable/plane"/>
```
