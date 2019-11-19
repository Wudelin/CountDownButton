package com.wdl.lib;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

/**
 * Create by: wdl at 2019/11/19 8:52
 * 倒计时按钮
 */
@SuppressWarnings("unused")
public class CountDownButton extends AppCompatButton
{
    /**
     * 倒计时主要类
     */
    private CountDownTimer mTimer;

    /**
     * 计时间隔-默认1S
     */
    public static final long COUNT_DOWN_INTERVAL = 1000L;

    /**
     * 默认文字(未开始倒计时、倒计时结束显示的文字)
     */
    private final String mDefaultText;

    /**
     * 倒计时回调
     */
    private CountDownCallback mCountDownCallback;

    public CountDownButton(Context context)
    {
        this(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // 禁止默认小写转大写
        setAllCaps(false);
        // 获取默认文本值
        this.mDefaultText = this.getText().toString();
        // 转交单击事件
        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mCountDownCallback != null)
                {
                    mCountDownCallback.onClick();
                }
            }
        });

    }

    /**
     * 开始倒计时-不可点击
     *
     * @param millsInFuture 执行周期
     */
    public void startTimer(final long millsInFuture)
    {
        if (millsInFuture <= 0)
        {
            return;
        }
        if (mTimer == null)
        {
            mTimer = new CountDownTimer(millsInFuture, COUNT_DOWN_INTERVAL)
            {
                /**
                 * 每次间隔指定时间的回调
                 *
                 * @param millisUntilFinished 剩余的时间
                 */
                @Override
                public void onTick(long millisUntilFinished)
                {
                    if (mCountDownCallback != null)
                    {
                        mCountDownCallback.onTick(millisUntilFinished, CountDownButton.this);
                    }
                }

                /**
                 * 倒计时完成
                 */
                @Override
                public void onFinish()
                {
                    stopTimer();
                    if (mCountDownCallback != null)
                    {
                        mCountDownCallback.onFinish(mDefaultText, CountDownButton.this);
                    }
                }
            };
            mTimer.start();
            setEnabled(false);
        }
    }

    /**
     * 结束计时-资源回收-重置是否可点击状态
     */
    public void stopTimer()
    {
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
            setEnabled(true);
        }
    }

    public void setCountDownCallback(CountDownCallback mCountDownCallback)
    {
        this.mCountDownCallback = mCountDownCallback;
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        stopTimer();
    }

    /**
     * 计时状态回调
     */
    public abstract static class CountDownCallback
    {
        /**
         * 每隔一个计时周期的回调
         *
         * @param millisUntilFinished 剩余时间
         * @param button              Button
         */
        public void onTick(long millisUntilFinished, @NonNull Button button)
        {
            try
            {
                button.setText(String.format("%ss", String.valueOf((millisUntilFinished / 1000))));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        /**
         * 计时结束
         *
         * @param defaultText 结束后显示的文本
         * @param button      Button
         */
        public void onFinish(@NonNull final String defaultText, @NonNull Button button)
        {
            if (TextUtils.isEmpty(defaultText))
            {
                return;
            }
            button.setText(defaultText);
        }

        /**
         * 单击事件
         */
        public abstract void onClick();
    }
}
