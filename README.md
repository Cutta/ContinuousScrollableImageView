# ContinuousScrollableImageView
Library for animating images with continuous scrolling effects


Simple android view to display images with continuous scrolling effects efficiently. 
You can set image source, scaleType, duration and direction.
Example usages can be found in sample project.

### SCREENS


![](https://raw.githubusercontent.com/Cutta/ContinuousScrollableImageView/master/gifs/bus.gif?token=AGY3KeEx21hh_AntxZBgwhLv9L3zaTYAks5ZpH8fwA%3D%3D)  |  ![](https://raw.githubusercontent.com/Cutta/ContinuousScrollableImageView/master/gifs/plane.gif?token=AGY3KeydGmvTnBrToMTTuw4wsIpyS9Dzks5ZpH9hwA%3D%3D)  |  ![](https://raw.githubusercontent.com/Cutta/ContinuousScrollableImageView/master/gifs/clouds.gif?token=AGY3KeblVnwY9luH9bUIyr3o_32r68k8ks5ZpIDBwA%3D%3D)

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

## IMPORT

Project build.gradle
```
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```
Module build.gradle

``` compile 'com.github.Cutta:ContinuousScrollableImageView:1.0' ```
# Credits
<a href = "https://plus.google.com/u/0/116948443141721480957"><img src = "https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png"/></a>
<a href = "https://twitter.com/Cuneyt_Carikci"><img src = "https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png"/></a>
<a href = "https://www.linkedin.com/in/c%C3%BCneyt-%C3%A7ar%C4%B1k%C3%A7i-b4619161?trk=nav_responsive_tab_profile_pic"><img src = "https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png"/></a>

# License
Copyright 2017 Cüneyt Çarıkçi.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
