package miui.systemui.controlcenter.widget;

import H0.d;
import H0.e;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.reflect.Method;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView", "SoonBlockedPrivateApi"})
public final class FocusedTextView extends TextView {
    public static final Companion Companion = new Companion(null);
    private static final d startMarqueeMethod$delegate = e.b(FocusedTextView$Companion$startMarqueeMethod$2.INSTANCE);
    private static final d stopMarqueeMethod$delegate = e.b(FocusedTextView$Companion$stopMarqueeMethod$2.INSTANCE);
    private int marqueeLimit;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Method getStartMarqueeMethod() {
            return (Method) FocusedTextView.startMarqueeMethod$delegate.getValue();
        }

        private final Method getStopMarqueeMethod() {
            return (Method) FocusedTextView.stopMarqueeMethod$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void startMarqueeCompat(TextView textView) {
            if (textView.getEllipsize() != TextUtils.TruncateAt.MARQUEE) {
                return;
            }
            try {
                Method startMarqueeMethod = getStartMarqueeMethod();
                if (startMarqueeMethod != null) {
                    startMarqueeMethod.invoke(textView, null);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void stopMarqueeCompat(TextView textView) {
            if (textView.getEllipsize() != TextUtils.TruncateAt.MARQUEE) {
                return;
            }
            try {
                Method stopMarqueeMethod = getStopMarqueeMethod();
                if (stopMarqueeMethod != null) {
                    stopMarqueeMethod.invoke(textView, null);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        private Companion() {
        }
    }

    public FocusedTextView(Context context) {
        this(context, null, 0, 6, null);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView
    public void setMarqueeRepeatLimit(int i2) {
        this.marqueeLimit = i2;
        super.setMarqueeRepeatLimit(0);
    }

    public final void startMarqueeLocal() {
        super.setMarqueeRepeatLimit(this.marqueeLimit);
        Companion.startMarqueeCompat(this);
    }

    public final void stopMarqueeLocal() {
        super.setMarqueeRepeatLimit(0);
        Companion.stopMarqueeCompat(this);
    }

    public FocusedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ FocusedTextView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    public FocusedTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
