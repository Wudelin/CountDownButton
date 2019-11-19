# CountDownButton
倒计时按钮

How to use?
--------
* 1.XML中引用<br>
* 2.设置Callback<br>
* 3.调用内部startTimer方法<br>

```
1. 
    <com.wdl.lib.CountDownButton
        android:id="@+id/btn_count_down"
        android:layout_width="150dp"
        android:layout_height="wrap_content"
        android:text="点击开始计时"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        
2/3.
mCountDownButton.setCountDownCallback(new CountDownButton.CountDownCallback(){
            @Override
            public void onFinish(@NonNull String defaultText, @NonNull Button button)
            {
                super.onFinish(defaultText, button);
            }

            @Override
            public void onClick()
            {
                mCountDownButton.startTimer(10 * 1000L);
            }

            @Override
            public void onTick(long millisUntilFinished, @NonNull Button button)
            {
                super.onTick(millisUntilFinished, button);
            }
        });

```



Download
--------

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
	implementation 'com.github.Wudelin:CountDownButton:1.0.0'
}
```



