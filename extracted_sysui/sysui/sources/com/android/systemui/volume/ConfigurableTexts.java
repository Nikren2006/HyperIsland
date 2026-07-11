package com.android.systemui.volume;

import android.content.Context;
import android.content.res.Resources;
import android.util.ArrayMap;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
public class ConfigurableTexts {
    private final Context mContext;
    private final ArrayMap<TextView, Integer> mTexts = new ArrayMap<>();
    private final ArrayMap<TextView, Integer> mTextLabels = new ArrayMap<>();
    private final Runnable mUpdateAll = new Runnable() { // from class: com.android.systemui.volume.ConfigurableTexts.1
        @Override // java.lang.Runnable
        public void run() {
            for (int i2 = 0; i2 < ConfigurableTexts.this.mTexts.size(); i2++) {
                ConfigurableTexts configurableTexts = ConfigurableTexts.this;
                configurableTexts.setTextSizeH((TextView) configurableTexts.mTexts.keyAt(i2), ((Integer) ConfigurableTexts.this.mTexts.valueAt(i2)).intValue());
            }
            for (int i3 = 0; i3 < ConfigurableTexts.this.mTextLabels.size(); i3++) {
                ConfigurableTexts configurableTexts2 = ConfigurableTexts.this;
                configurableTexts2.setTextLabelH((TextView) configurableTexts2.mTextLabels.keyAt(i3), ((Integer) ConfigurableTexts.this.mTextLabels.valueAt(i3)).intValue());
            }
        }
    };

    public ConfigurableTexts(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextLabelH(TextView textView, int i2) {
        if (i2 >= 0) {
            try {
                Util.setText(textView, this.mContext.getString(i2));
            } catch (Resources.NotFoundException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextSizeH(TextView textView, int i2) {
        textView.setTextSize(2, i2);
    }

    public int add(TextView textView) {
        return add(textView, -1);
    }

    public void update() {
        if (this.mTexts.isEmpty()) {
            return;
        }
        this.mTexts.keyAt(0).post(this.mUpdateAll);
    }

    public int add(final TextView textView, int i2) {
        if (textView == null) {
            return 0;
        }
        Resources resources = this.mContext.getResources();
        float f2 = resources.getConfiguration().fontScale;
        final int textSize = (int) ((textView.getTextSize() / f2) / resources.getDisplayMetrics().density);
        this.mTexts.put(textView, Integer.valueOf(textSize));
        textView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.volume.ConfigurableTexts.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                ConfigurableTexts.this.setTextSizeH(textView, textSize);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }
        });
        this.mTextLabels.put(textView, Integer.valueOf(i2));
        return textSize;
    }
}
