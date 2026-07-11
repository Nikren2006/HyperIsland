package miuix.view;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: loaded from: classes5.dex */
public class DensityChangedHelper {
    public static void updateTextSize(TextView textView, int i2) {
        updateTextSize(textView, i2, 2);
    }

    public static void updateTextSizeDefaultUnit(TextView textView, float f2) {
        textView.setTextSize(textView.getTextSizeUnit(), f2);
    }

    public static void updateTextSizeDpUnit(TextView textView, float f2) {
        textView.setTextSize(1, f2);
    }

    public static void updateTextSizeSpUnit(TextView textView, float f2) {
        textView.setTextSize(2, f2);
    }

    public static void updateView(View view, int i2) {
        float f2 = i2;
        updateView(view, f2 / 160.0f, (view.getResources().getConfiguration().densityDpi * 1.0f) / f2);
    }

    public static void updateViewMargin(View view, int i2) {
        updateViewMargin(view, (view.getResources().getConfiguration().densityDpi * 1.0f) / i2);
    }

    public static void updateViewMarginByResource(Resources resources, View view, int i2) {
        if (i2 == -1) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int dimensionPixelSize = resources.getDimensionPixelSize(i2);
            marginLayoutParams.leftMargin = dimensionPixelSize;
            marginLayoutParams.topMargin = dimensionPixelSize;
            marginLayoutParams.rightMargin = dimensionPixelSize;
            marginLayoutParams.bottomMargin = dimensionPixelSize;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void updateViewPadding(View view, int i2) {
        updateViewPadding(view, (view.getResources().getConfiguration().densityDpi * 1.0f) / i2);
    }

    public static void updateViewPaddingByResource(Resources resources, View view, int i2) {
        if (i2 == -1) {
            return;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(i2);
        view.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    public static void updateViewSize(View view, int i2) {
        updateViewSize(view, (view.getResources().getConfiguration().densityDpi * 1.0f) / i2);
    }

    public static void updateViewSizeByResource(Resources resources, View view, int i2) {
        if (i2 == -1) {
            return;
        }
        updateViewSizeByResource(resources, view, i2, i2);
    }

    public static void updateTextSize(TextView textView, float f2) {
        updateTextSize(textView, f2, 2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0037 A[PHI: r3
      0x0037: PHI (r3v3 boolean) = (r3v1 boolean), (r3v1 boolean), (r3v4 boolean) binds: [B:15:0x002a, B:17:0x002e, B:9:0x0018] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void updateViewSizeByResource(android.content.res.Resources r5, android.view.View r6, int r7, int r8) {
        /*
            android.view.ViewGroup$LayoutParams r0 = r6.getLayoutParams()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r7 != r8) goto L1d
            if (r7 == r1) goto L1d
            int r5 = r5.getDimensionPixelSize(r7)
            int r7 = r0.width
            if (r7 <= 0) goto L16
            r0.width = r5
            r3 = r2
        L16:
            int r7 = r0.height
            if (r7 <= 0) goto L37
            r0.height = r5
            goto L38
        L1d:
            if (r7 == r1) goto L2a
            int r4 = r0.width
            if (r4 <= 0) goto L2a
            int r7 = r5.getDimensionPixelSize(r7)
            r0.width = r7
            r3 = r2
        L2a:
            if (r8 == r1) goto L37
            int r7 = r0.height
            if (r7 <= 0) goto L37
            int r5 = r5.getDimensionPixelSize(r8)
            r0.height = r5
            goto L38
        L37:
            r2 = r3
        L38:
            if (r2 == 0) goto L3d
            r6.setLayoutParams(r0)
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.view.DensityChangedHelper.updateViewSizeByResource(android.content.res.Resources, android.view.View, int, int):void");
    }

    public static void updateTextSize(TextView textView, int i2, int i3) {
        updateTextSize(textView, i2 / 160.0f, i3);
    }

    public static void updateView(View view, int i2, float f2) {
        updateView(view, i2 / 160.0f, f2);
    }

    public static void updateViewMargin(View view, float f2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = (int) (marginLayoutParams.leftMargin * f2);
            marginLayoutParams.topMargin = (int) (marginLayoutParams.topMargin * f2);
            marginLayoutParams.rightMargin = (int) (marginLayoutParams.rightMargin * f2);
            marginLayoutParams.bottomMargin = (int) (marginLayoutParams.bottomMargin * f2);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void updateViewPadding(View view, float f2) {
        view.setPadding((int) (view.getPaddingLeft() * f2), (int) (view.getPaddingTop() * f2), (int) (view.getPaddingRight() * f2), (int) (view.getPaddingBottom() * f2));
    }

    public static void updateViewPaddingByResource(Resources resources, View view, int i2, int i3, int i4, int i5) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        int dimensionPixelSize3;
        int paddingTop;
        if (i2 == i4 && i2 != -1) {
            dimensionPixelSize = resources.getDimensionPixelSize(i2);
            dimensionPixelSize2 = dimensionPixelSize;
        } else {
            dimensionPixelSize = i2 != -1 ? resources.getDimensionPixelSize(i2) : 0;
            dimensionPixelSize2 = i4 != -1 ? resources.getDimensionPixelSize(i4) : 0;
        }
        if (i3 == i5 && i3 != -1) {
            paddingTop = resources.getDimensionPixelSize(i3);
            dimensionPixelSize3 = paddingTop;
        } else {
            int dimensionPixelSize4 = i3 != -1 ? resources.getDimensionPixelSize(i3) : 0;
            dimensionPixelSize3 = i5 != -1 ? resources.getDimensionPixelSize(i5) : 0;
            paddingTop = dimensionPixelSize4;
        }
        if (i2 == -1) {
            dimensionPixelSize = view.getPaddingLeft();
        }
        if (i3 == -1) {
            paddingTop = view.getPaddingTop();
        }
        if (i4 == -1) {
            dimensionPixelSize2 = view.getPaddingLeft();
        }
        if (i5 == -1) {
            dimensionPixelSize3 = view.getPaddingBottom();
        }
        view.setPadding(dimensionPixelSize, paddingTop, dimensionPixelSize2, dimensionPixelSize3);
    }

    public static void updateViewSize(View view, float f2) {
        boolean z2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int i2 = layoutParams.width;
        boolean z3 = true;
        if (i2 > 0) {
            layoutParams.width = (int) (i2 * f2);
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = layoutParams.height;
        if (i3 > 0) {
            layoutParams.height = (int) (i3 * f2);
        } else {
            z3 = z2;
        }
        if (z3) {
            view.setLayoutParams(layoutParams);
        }
    }

    public static void updateTextSize(TextView textView, float f2, int i2) {
        textView.setTextSize(textView.getTextSizeUnit(), textView.getTextSize() / f2);
    }

    public static void updateView(View view, float f2, float f3) {
        updateViewSize(view, f3);
        updateViewPadding(view, f3);
        updateViewMargin(view, f3);
        if (view instanceof TextView) {
            updateTextSize((TextView) view, f2);
        }
    }

    public static void updateViewMarginByResource(Resources resources, View view, int i2, int i3, int i4, int i5) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        int dimensionPixelSize3;
        int dimensionPixelSize4;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (i2 == i4 && i2 != -1) {
                dimensionPixelSize = resources.getDimensionPixelSize(i2);
                dimensionPixelSize2 = dimensionPixelSize;
            } else {
                dimensionPixelSize = i2 != -1 ? resources.getDimensionPixelSize(i2) : 0;
                dimensionPixelSize2 = i4 != -1 ? resources.getDimensionPixelSize(i4) : 0;
            }
            if (i3 == i5 && i3 != -1) {
                dimensionPixelSize4 = resources.getDimensionPixelSize(i3);
                dimensionPixelSize3 = dimensionPixelSize4;
            } else {
                int dimensionPixelSize5 = i3 != -1 ? resources.getDimensionPixelSize(i3) : 0;
                dimensionPixelSize3 = i5 != -1 ? resources.getDimensionPixelSize(i5) : 0;
                dimensionPixelSize4 = dimensionPixelSize5;
            }
            if (i2 != -1) {
                marginLayoutParams.leftMargin = dimensionPixelSize;
            }
            if (i3 != -1) {
                marginLayoutParams.topMargin = dimensionPixelSize4;
            }
            if (i4 != -1) {
                marginLayoutParams.rightMargin = dimensionPixelSize2;
            }
            if (i5 != -1) {
                marginLayoutParams.bottomMargin = dimensionPixelSize3;
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }
}
