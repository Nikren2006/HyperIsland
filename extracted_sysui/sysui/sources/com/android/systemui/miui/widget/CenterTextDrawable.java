package com.android.systemui.miui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.android.systemui.miui.volume.R;
import com.android.systemui.miui.volume.Util;

/* JADX INFO: loaded from: classes2.dex */
public class CenterTextDrawable extends Drawable {
    private boolean mIsCustomed;
    private Paint mPaint;
    private int mParentSize;
    private String mText;
    private float mTextSize;

    public CenterTextDrawable(Context context) {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        this.mText = "";
        this.mTextSize = 0.0f;
        paint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setFakeBoldText(false);
        this.mParentSize = context.getResources().getDimensionPixelSize(Util.sIsNotificationSingle ? R.dimen.miui_volume_timer_seekbar_width_4stream : R.dimen.miui_volume_timer_seekbar_width);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (TextUtils.isEmpty(this.mText)) {
            return;
        }
        int iWidth = getBounds().width() / 2;
        int iHeight = (int) ((getBounds().height() / 2) - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f));
        if (this.mIsCustomed) {
            float textSize = this.mPaint.getTextSize();
            while (this.mParentSize < this.mPaint.measureText(this.mText)) {
                textSize -= 1.0f;
                this.mPaint.setTextSize(textSize);
            }
        } else {
            this.mPaint.setTextSize(this.mTextSize);
        }
        canvas.drawText(this.mText, iWidth, iHeight, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setText(String str, boolean z2) {
        this.mText = str;
        invalidateSelf();
        this.mIsCustomed = z2;
    }

    public void setTextColor(int i2) {
        this.mPaint.setColor(i2);
    }

    public void setTextSize(float f2) {
        this.mPaint.setTextSize(f2);
        this.mTextSize = f2;
    }

    public void setTypeface(Typeface typeface) {
        this.mPaint.setTypeface(typeface);
    }

    public void setText(String str) {
        setText(str, false);
    }
}
