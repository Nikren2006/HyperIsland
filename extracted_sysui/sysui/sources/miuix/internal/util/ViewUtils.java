package miuix.internal.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.lang.reflect.Method;
import miuix.core.R;
import miuix.reflect.Reflects;

/* JADX INFO: loaded from: classes3.dex */
public class ViewUtils {
    private static final int PADDING_KEY = Integer.MIN_VALUE;
    static final String TAG = "ViewUtils";
    private static boolean sSetFrameFetched;
    private static Method sSetFrameMethod;

    public interface OnApplyWindowInsetsListener {
        WindowInsetsCompat onApplyWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull RelativePadding relativePadding);
    }

    private ViewUtils() {
    }

    public static boolean containsPoint(View view, int i2, int i3) {
        return i2 > view.getLeft() && i2 < view.getRight() && i3 > view.getTop() && i3 < view.getBottom();
    }

    public static void doOnApplyWindowInsets(@NonNull View view, @Nullable AttributeSet attributeSet, int i2, int i3) {
        doOnApplyWindowInsets(view, attributeSet, i2, i3, null);
    }

    @SuppressLint({"PrivateApi", "SoonBlockedPrivateApi"})
    private static void fetchSetFrame() {
        if (sSetFrameFetched) {
            return;
        }
        Class cls = Integer.TYPE;
        Method declaredMethod = Reflects.getDeclaredMethod((Class<?>) View.class, "setFrame", (Class<?>[]) new Class[]{cls, cls, cls, cls});
        sSetFrameMethod = declaredMethod;
        declaredMethod.setAccessible(true);
        sSetFrameFetched = true;
    }

    public static int getBackgroundHeight(View view) {
        Drawable background = view.getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return -1;
    }

    public static int getBackgroundWidth(View view) {
        Drawable background = view.getBackground();
        if (background != null) {
            return background.getIntrinsicWidth();
        }
        return -1;
    }

    public static void getBoundsInWindow(@NonNull View view, @NonNull Rect rect) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        rect.set(i2, iArr[1], view.getWidth() + i2, iArr[1] + view.getHeight());
    }

    public static void getContentRect(View view, Rect rect) {
        rect.left = view.getScrollX() + view.getPaddingLeft();
        rect.top = view.getScrollY() + view.getPaddingTop();
        rect.right = (view.getWidth() - view.getPaddingRight()) - rect.left;
        rect.bottom = (view.getHeight() - view.getPaddingBottom()) - rect.top;
    }

    public static int getMeasuredHeightWithMargin(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return measuredHeight;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public static boolean intersectsWith(View view, Rect rect) {
        return rect != null && view.getLeft() < rect.right && view.getTop() < rect.bottom && view.getRight() > rect.left && view.getBottom() > rect.top;
    }

    public static boolean isLayoutRtl(View view) {
        return view.getLayoutDirection() == 1;
    }

    public static boolean isNightMode(Context context) {
        return isNightMode(context.getResources().getConfiguration());
    }

    public static void layoutChildView(ViewGroup viewGroup, View view, int i2, int i3, int i4, int i5) {
        boolean zIsLayoutRtl = isLayoutRtl(viewGroup);
        int width = viewGroup.getWidth();
        int i6 = zIsLayoutRtl ? width - i4 : i2;
        if (zIsLayoutRtl) {
            i4 = width - i2;
        }
        view.layout(i6, i3, i4, i5);
    }

    public static void requestApplyInsetsWhenAttached(@NonNull View view) {
        if (ViewCompat.isAttachedToWindow(view)) {
            ViewCompat.requestApplyInsets(view);
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miuix.internal.util.ViewUtils.3
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(@NonNull View view2) {
                    view2.removeOnAttachStateChangeListener(this);
                    ViewCompat.requestApplyInsets(view2);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view2) {
                }
            });
        }
    }

    public static void resetPaddingBottom(View view, int i2) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i2);
    }

    public static void resetPaddingTop(View view, int i2) {
        view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setLeftTopRightBottom(View view, int i2, int i3, int i4, int i5) {
        view.setLeftTopRightBottom(i2, i3, i4, i5);
    }

    public static void doOnApplyWindowInsets(@NonNull View view, @Nullable AttributeSet attributeSet, int i2, int i3, @Nullable final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.MiuixInsets, i2, i3);
        final boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MiuixInsets_miuixPaddingBottomSystemWindowInsets, false);
        final boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MiuixInsets_miuixPaddingLeftSystemWindowInsets, false);
        final boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MiuixInsets_miuixPaddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        doOnApplyWindowInsets(view, new OnApplyWindowInsetsListener() { // from class: miuix.internal.util.ViewUtils.1
            @Override // miuix.internal.util.ViewUtils.OnApplyWindowInsetsListener
            @NonNull
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View view2, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull RelativePadding relativePadding) {
                if (z2) {
                    relativePadding.bottom += windowInsetsCompat.getSystemWindowInsetBottom();
                }
                boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(view2);
                if (z3) {
                    if (zIsLayoutRtl) {
                        relativePadding.end += windowInsetsCompat.getSystemWindowInsetLeft();
                    } else {
                        relativePadding.start += windowInsetsCompat.getSystemWindowInsetLeft();
                    }
                }
                if (z4) {
                    if (zIsLayoutRtl) {
                        relativePadding.start += windowInsetsCompat.getSystemWindowInsetRight();
                    } else {
                        relativePadding.end += windowInsetsCompat.getSystemWindowInsetRight();
                    }
                }
                relativePadding.applyToView(view2);
                OnApplyWindowInsetsListener onApplyWindowInsetsListener2 = onApplyWindowInsetsListener;
                return onApplyWindowInsetsListener2 != null ? onApplyWindowInsetsListener2.onApplyWindowInsets(view2, windowInsetsCompat, relativePadding) : windowInsetsCompat;
            }
        });
    }

    public static boolean isNightMode(Configuration configuration) {
        return configuration.isNightModeActive();
    }

    public static class RelativePadding {
        public int bottom;
        public boolean clipToPadding = false;
        public int end;
        public int start;
        public int top;

        public RelativePadding(View view) {
            this.start = ViewCompat.getPaddingStart(view);
            this.top = view.getPaddingTop();
            this.end = ViewCompat.getPaddingEnd(view);
            this.bottom = view.getPaddingBottom();
        }

        public void applyToScrollingView(ViewGroup viewGroup) {
            applyToView(viewGroup);
            viewGroup.setClipToPadding(true);
        }

        public void applyToView(View view) {
            ViewCompat.setPaddingRelative(view, this.start, this.top, this.end, this.bottom);
        }

        public RelativePadding(int i2, int i3, int i4, int i5) {
            this.start = i2;
            this.top = i3;
            this.end = i4;
            this.bottom = i5;
        }

        public RelativePadding(@NonNull RelativePadding relativePadding) {
            this.start = relativePadding.start;
            this.top = relativePadding.top;
            this.end = relativePadding.end;
            this.bottom = relativePadding.bottom;
        }
    }

    public static void doOnApplyWindowInsets(@NonNull View view, @NonNull final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        final RelativePadding relativePadding = new RelativePadding(ViewCompat.getPaddingStart(view), view.getPaddingTop(), ViewCompat.getPaddingEnd(view), view.getPaddingBottom());
        ViewCompat.setOnApplyWindowInsetsListener(view, new androidx.core.view.OnApplyWindowInsetsListener() { // from class: miuix.internal.util.ViewUtils.2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            @NonNull
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View view2, @NonNull WindowInsetsCompat windowInsetsCompat) {
                return onApplyWindowInsetsListener.onApplyWindowInsets(view2, windowInsetsCompat, new RelativePadding(relativePadding));
            }
        });
        requestApplyInsetsWhenAttached(view);
    }
}
