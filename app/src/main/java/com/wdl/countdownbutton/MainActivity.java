package com.wdl.countdownbutton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wdl.lib.CountDownButton;

public class MainActivity extends AppCompatActivity
{

    private CountDownButton mCountDownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCountDownButton = findViewById(R.id.btn_count_down);
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
    }
}
