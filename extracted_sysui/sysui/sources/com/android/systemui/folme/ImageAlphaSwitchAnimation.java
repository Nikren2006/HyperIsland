package com.android.systemui.folme;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.android.systemui.miplay.R;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.util.CommonUtils;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ValueProperty;
import miuix.animation.utils.EaseManager;
import miuix.core.util.MiuiBlurUtils;

/* JADX INFO: loaded from: classes.dex */
public final class ImageAlphaSwitchAnimation {
    public static final int MAX_BLUR_RADIUS = 100;
    public static final int MAX_DRAWABLE_ALPHA = 255;
    public static final String TAG = "ImageAlphaSwitchAnimation";
    public static final Companion Companion = new Companion(null);
    private static final ValueProperty<?> PROGRESS_ALPHA = new ValueProperty<>("progress_alpha", 0.00390625f);
    private boolean mBlurEnabled = true;
    private IStateStyle animWithValue = Folme.useValue(this);
    private final ArrayList<Point> mColorModes = new ArrayList<>();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ValueProperty<?> getPROGRESS_ALPHA() {
            return ImageAlphaSwitchAnimation.PROGRESS_ALPHA;
        }

        public final boolean isInAlphaAnim(View view) {
            n.g(view, "view");
            return view.getTag(R.id.view_is_in_alpha_anim_key) != null;
        }

        private Companion() {
        }
    }

    public final void release() {
        Folme.clean(this);
    }

    public final void reset(ImageView view) {
        n.g(view, "view");
        this.animWithValue.cancel();
        setMiSelfBlur(view, 0, this.mColorModes);
        view.setTag(R.id.view_is_in_alpha_anim_key, null);
        Drawable drawable = view.getDrawable();
        if (drawable != null) {
            drawable.setAlpha(255);
        }
        CommonUtils.INSTANCE.setAlphaEx(view, 1.0f);
    }

    public final void setBlurEnabled(boolean z2) {
        this.mBlurEnabled = z2;
    }

    public final void setMiSelfBlur(View view, int i2, ArrayList<Point> arrayList) {
        if (view == null || !this.mBlurEnabled) {
            return;
        }
        MiuiBlurUtils.setSelfBlur(view, i2, arrayList);
    }

    public final void switchWithValue(final ImageView view, final Bitmap bitmap) {
        n.g(view, "view");
        n.g(bitmap, "bitmap");
        AnimConfig animConfigAddListeners = new AnimConfig().setEase(EaseManager.getStyle(-2, 0.95f, 0.35f)).addListeners(new TransitionListener() { // from class: com.android.systemui.folme.ImageAlphaSwitchAnimation$switchWithValue$firstHalfConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                ImageAlphaSwitchAnimation imageAlphaSwitchAnimation = this.this$0;
                imageAlphaSwitchAnimation.setMiSelfBlur(view, 100, imageAlphaSwitchAnimation.mColorModes);
                Drawable drawable = view.getDrawable();
                if (drawable != null) {
                    drawable.setAlpha(0);
                }
                view.setImageBitmap(bitmap);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                ImageAlphaSwitchAnimation imageAlphaSwitchAnimation = this.this$0;
                imageAlphaSwitchAnimation.setMiSelfBlur(view, 0, imageAlphaSwitchAnimation.mColorModes);
                Drawable drawable = view.getDrawable();
                if (drawable != null) {
                    drawable.setAlpha(255);
                }
                view.setTag(R.id.view_is_in_alpha_anim_key, null);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                ImageAlphaSwitchAnimation imageAlphaSwitchAnimation = this.this$0;
                imageAlphaSwitchAnimation.setMiSelfBlur(view, 0, imageAlphaSwitchAnimation.mColorModes);
                Drawable drawable = view.getDrawable();
                if (drawable != null) {
                    drawable.setAlpha(255);
                }
                view.setTag(R.id.view_is_in_alpha_anim_key, null);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                UpdateInfo updateInfoFindBy = UpdateInfo.findBy(collection, ImageAlphaSwitchAnimation.Companion.getPROGRESS_ALPHA());
                float floatValue = updateInfoFindBy != null ? updateInfoFindBy.getFloatValue() : 0.0f;
                Drawable drawable = view.getDrawable();
                if (drawable != null) {
                    drawable.setAlpha((int) (255 * floatValue));
                }
                ImageAlphaSwitchAnimation imageAlphaSwitchAnimation = this.this$0;
                imageAlphaSwitchAnimation.setMiSelfBlur(view, (int) (100 * (1 - floatValue)), imageAlphaSwitchAnimation.mColorModes);
            }
        });
        view.setTag(R.id.view_is_in_alpha_anim_key, Boolean.TRUE);
        IStateStyle iStateStyle = this.animWithValue;
        ValueProperty<?> valueProperty = PROGRESS_ALPHA;
        IStateStyle to = iStateStyle.setTo(valueProperty, Float.valueOf(0.0f));
        if (to != null) {
            to.to(valueProperty, Float.valueOf(1.0f), animConfigAddListeners);
        }
    }
}
