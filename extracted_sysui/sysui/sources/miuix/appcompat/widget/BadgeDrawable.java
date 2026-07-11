package miuix.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import miuix.appcompat.R;
import miuix.core.util.MiuixUIUtils;
import miuix.internal.util.AttributeResolver;

/* JADX INFO: loaded from: classes3.dex */
public class BadgeDrawable {
    private static final String CEILING = "99+";
    private static final int CORNER_RADIUS = Integer.MAX_VALUE;
    public static final int EXPAND_INSIDE = 4;
    private static final int EXPAND_INSIDE_BADGE_HEIGHT = 24;
    private static final int EXPAND_INSIDE_BADGE_TEXT_SIZE = 14;
    private static final int EXPAND_INSIDE_LARGE_BADGE_WIDTH = 38;
    private static final int EXPAND_INSIDE_MEDIUM_BADGE_WIDTH = 29;
    private static final int EXPAND_INSIDE_SMALL_BADGE_WIDTH = 24;
    public static final int EXPAND_OUTSIDE = 3;
    private static final int EXPAND_OUTSIDE_BADGE_HEIGHT = 16;
    private static final int EXPAND_OUTSIDE_BADGE_TEXT_SIZE = 12;
    private static final int EXPAND_OUTSIDE_LARGE_BADGE_WIDTH = 31;
    private static final int EXPAND_OUTSIDE_MEDIUM_BADGE_WIDTH = 21;
    private static final int EXPAND_OUTSIDE_SMALL_BADGE_WIDTH = 16;
    public static final int GRAVITY_END_BOTTOM = 3;
    public static final int GRAVITY_END_TOP = 2;
    public static final int GRAVITY_START_BOTTOM = 1;
    public static final int GRAVITY_START_TOP = 0;
    private static final int LARGE_BADGE_SIZE = 12;
    private static final int MAJOR_EXTRA_SPACE = 10;
    private static final int MEDIUM_BADGE_SIZE = 8;
    private static final int MINOR_EXTRA_SPACE = 2;
    public static final int SIZE_LARGE = 2;
    public static final int SIZE_MEDIUM = 1;
    public static final int SIZE_SMALL = 0;
    private static final String TAG = "BadgeDrawable";
    private View mAnchor;
    private Drawable mBadgeDrawable;
    private Canvas mCanvas;
    private int mColor;
    private BadgeConfig mConfig;
    private Context mContext;
    private int mGravity;
    private int mNumber;
    private Paint mPaint;

    /* JADX INFO: renamed from: miuix.appcompat.widget.BadgeDrawable$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$miuix$appcompat$widget$BadgeDrawable$BadgeConfig;

        static {
            int[] iArr = new int[BadgeConfig.values().length];
            $SwitchMap$miuix$appcompat$widget$BadgeDrawable$BadgeConfig = iArr;
            try {
                iArr[BadgeConfig.EXPAND_INSIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$miuix$appcompat$widget$BadgeDrawable$BadgeConfig[BadgeConfig.EXPAND_OUTSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$miuix$appcompat$widget$BadgeDrawable$BadgeConfig[BadgeConfig.SIZE_MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$miuix$appcompat$widget$BadgeDrawable$BadgeConfig[BadgeConfig.SIZE_LARGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$miuix$appcompat$widget$BadgeDrawable$BadgeConfig[BadgeConfig.SIZE_SMALL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum BadgeConfig {
        SIZE_SMALL,
        SIZE_MEDIUM,
        SIZE_LARGE,
        EXPAND_INSIDE,
        EXPAND_OUTSIDE
    }

    public BadgeDrawable(Context context) {
        this(context, 2);
    }

    private Drawable drawTextOnBadge(int i2, Drawable drawable) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        this.mCanvas = canvas;
        drawable.setBounds(0, 0, canvas.getWidth(), this.mCanvas.getHeight());
        drawable.draw(this.mCanvas);
        initPaint(i2);
        this.mCanvas.drawText(normalizeText(), this.mCanvas.getWidth() / 2.0f, (this.mCanvas.getHeight() / 2.0f) - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f), this.mPaint);
        return new BitmapDrawable(this.mContext.getResources(), bitmapCreateBitmap);
    }

    private Drawable getBadgeDrawable() {
        return AttributeResolver.resolveDrawable(this.mContext, R.attr.actionBarTabBadgeIcon);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Rect getBadgeRect(android.view.View r12) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.widget.BadgeDrawable.getBadgeRect(android.view.View):android.graphics.Rect");
    }

    private void initPaint(int i2) {
        if (this.mPaint == null) {
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setColor(-1);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setTextAlign(Paint.Align.CENTER);
        }
        this.mPaint.setTextSize(i2 == 3 ? MiuixUIUtils.dp2px(this.mContext, 12.0f) : MiuixUIUtils.dp2px(this.mContext, 14.0f));
    }

    private String normalizeText() {
        int i2 = this.mNumber;
        return (i2 <= 0 || i2 >= 100) ? CEILING : Integer.toString(i2);
    }

    private int normalizeWidth(int i2) {
        int i3 = this.mNumber;
        if (i3 >= 0 && i3 < 10) {
            return i2 == 4 ? MiuixUIUtils.dp2px(this.mContext, 24.0f) : MiuixUIUtils.dp2px(this.mContext, 16.0f);
        }
        if (i3 >= 10 && i3 < 100) {
            return i2 == 4 ? MiuixUIUtils.dp2px(this.mContext, 29.0f) : MiuixUIUtils.dp2px(this.mContext, 21.0f);
        }
        if (i3 >= 100) {
            return i2 == 4 ? MiuixUIUtils.dp2px(this.mContext, 38.0f) : MiuixUIUtils.dp2px(this.mContext, 31.0f);
        }
        return 0;
    }

    public void attachBadgeDrawable(View view) {
        attachBadgeDrawable(view, this.mGravity);
    }

    public void detachBadgeDrawable() {
        View view = this.mAnchor;
        if (view != null) {
            view.getOverlay().clear();
        }
    }

    @NonNull
    public Drawable getCurrentBadgeDrawable() {
        return this.mBadgeDrawable;
    }

    public void setGravity(int i2) {
        if (i2 >= 0 && i2 <= 3) {
            this.mGravity = i2;
        } else {
            Log.d(TAG, "set invalid gravity value.");
            this.mGravity = 2;
        }
    }

    public void updateNumberOnBadge(int i2, View view) {
        if (view == null) {
            return;
        }
        detachBadgeDrawable(view);
        this.mNumber = i2;
        Drawable badgeDrawable = getBadgeDrawable(this.mConfig);
        this.mBadgeDrawable = badgeDrawable;
        if (badgeDrawable == null) {
            return;
        }
        attachBadgeDrawable(view);
    }

    public BadgeDrawable(Context context, int i2) {
        this(context, i2, BadgeConfig.SIZE_SMALL, 0);
    }

    private Drawable getBadgeDrawable(int i2, int i3, float f2, int i4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setSize(i2, i3);
        gradientDrawable.setCornerRadius(f2);
        gradientDrawable.setColor(i4);
        return gradientDrawable;
    }

    public void attachBadgeDrawable(View view, int i2) {
        setGravity(i2);
        Rect badgeRect = getBadgeRect(view);
        if (badgeRect == null) {
            Log.d(TAG, "attach failed.");
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
        this.mBadgeDrawable.setBounds(badgeRect);
        view.getOverlay().add(this.mBadgeDrawable);
        this.mAnchor = view;
    }

    public BadgeDrawable(Context context, BadgeConfig badgeConfig) {
        this(context, 2, badgeConfig, 0);
    }

    public void detachBadgeDrawable(View view) {
        if (view != null) {
            view.getOverlay().remove(this.mBadgeDrawable);
        }
    }

    public BadgeDrawable(Context context, int i2, int i3) {
        this(context, i2, BadgeConfig.EXPAND_OUTSIDE, i3);
    }

    public BadgeDrawable(Context context, int i2, BadgeConfig badgeConfig, int i3) {
        setGravity(i2);
        this.mContext = context;
        this.mConfig = badgeConfig;
        this.mNumber = i3;
        this.mColor = context.getResources().getColor(R.color.miuix_appcompat_badge_drawable_color);
        this.mBadgeDrawable = getBadgeDrawable(badgeConfig);
    }

    private Drawable getBadgeDrawable(BadgeConfig badgeConfig) {
        int i2 = AnonymousClass1.$SwitchMap$miuix$appcompat$widget$BadgeDrawable$BadgeConfig[badgeConfig.ordinal()];
        if (i2 == 1) {
            return drawTextOnBadge(4, getBadgeDrawable(normalizeWidth(4), MiuixUIUtils.dp2px(this.mContext, 24.0f), 2.14748365E9f, this.mColor));
        }
        if (i2 == 2) {
            return drawTextOnBadge(3, getBadgeDrawable(normalizeWidth(3), MiuixUIUtils.dp2px(this.mContext, 16.0f), 2.14748365E9f, this.mColor));
        }
        if (i2 == 3) {
            int iDp2px = MiuixUIUtils.dp2px(this.mContext, 8.0f);
            return getBadgeDrawable(iDp2px, iDp2px, 2.14748365E9f, this.mColor);
        }
        if (i2 != 4) {
            return getBadgeDrawable();
        }
        int iDp2px2 = MiuixUIUtils.dp2px(this.mContext, 12.0f);
        return getBadgeDrawable(iDp2px2, iDp2px2, 2.14748365E9f, this.mColor);
    }
}
