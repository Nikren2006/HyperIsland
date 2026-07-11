package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;
import com.android.systemui.miui.notification.R;
import com.xiaomi.onetrack.util.aa;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.BgInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.PaletteUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyBackgroundViewHolder extends ModuleViewHolder {
    private static final int COLOR_ALPHA = 255;
    public static final Companion Companion = new Companion(null);
    private BgInfo bgInfo;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleTinyBackgroundViewHolder$initView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m147invoke();
            return H0.s.f314a;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m147invoke() {
            BgInfo bgInfo = ModuleTinyBackgroundViewHolder.this.bgInfo;
            if (bgInfo == null || bgInfo.getType() != 2) {
                return;
            }
            StatusBarNotification sbn = ModuleTinyBackgroundViewHolder.this.getSbn();
            Icon icon = null;
            if (sbn != null) {
                ModuleTinyBackgroundViewHolder moduleTinyBackgroundViewHolder = ModuleTinyBackgroundViewHolder.this;
                BgInfo bgInfo2 = moduleTinyBackgroundViewHolder.bgInfo;
                icon = moduleTinyBackgroundViewHolder.getIcon(bgInfo2 != null ? bgInfo2.getPicBg() : null, sbn);
            }
            StatusBarNotification sbn2 = ModuleTinyBackgroundViewHolder.this.getSbn();
            if (sbn2 != null) {
                ModuleTinyBackgroundViewHolder.this.createPartBg(icon, sbn2);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyBackgroundViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$1(ImageView imageView, Icon icon) {
        ViewGroup.LayoutParams layoutParams;
        Integer numValueOf = imageView != null ? Integer.valueOf(imageView.getMeasuredHeight()) : null;
        if (imageView != null && (layoutParams = imageView.getLayoutParams()) != null && numValueOf != null && numValueOf.intValue() > 0) {
            layoutParams.height = numValueOf.intValue();
        }
        if (imageView != null) {
            imageView.setImageIcon(icon);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createPartBg(Icon icon, StatusBarNotification statusBarNotification) {
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.HAS_CUSTOM_BG, true);
        View view = getView();
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.focus_notify_bg_image) : null;
        Drawable drawableLoadDrawable = icon != null ? icon.loadDrawable(getCtx()) : null;
        Bitmap bitmapCreateBitmap = drawableLoadDrawable != null ? Bitmap.createBitmap(drawableLoadDrawable.getIntrinsicWidth(), drawableLoadDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888) : null;
        Canvas canvas = bitmapCreateBitmap != null ? new Canvas(bitmapCreateBitmap) : null;
        if (canvas != null) {
            drawableLoadDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        if (canvas != null) {
            drawableLoadDrawable.draw(canvas);
        }
        Bitmap bitmapResizeAndCrop = bitmapCreateBitmap != null ? resizeAndCrop(bitmapCreateBitmap) : null;
        if (bitmapResizeAndCrop == null) {
            return;
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapResizeAndCrop.getWidth(), bitmapResizeAndCrop.getHeight(), bitmapResizeAndCrop.getConfig());
        Palette.Swatch swatchFindBackgroundSwatch = PaletteUtils.findBackgroundSwatch(bitmapCreateBitmap);
        int iDarkenColor$default = darkenColor$default(this, ColorUtils.setAlphaComponent(swatchFindBackgroundSwatch.getRgb(), 255), 0.3f, 0.0f, 4, null);
        if (canvas != null) {
            canvas.drawColor(iDarkenColor$default);
        }
        if (imageView != null) {
            imageView.setImageBitmap(bitmapCreateBitmap);
        }
        Canvas canvas2 = bitmapCreateBitmap2 != null ? new Canvas(bitmapCreateBitmap2) : null;
        if (canvas2 != null) {
            canvas2.drawBitmap(bitmapResizeAndCrop, 0.0f, 0.0f, (Paint) null);
        }
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, 0.0f, bitmapResizeAndCrop.getWidth(), 0.0f, iDarkenColor$default, darkenColor(ColorUtils.setAlphaComponent(swatchFindBackgroundSwatch.getRgb(), 255), 0.3f, 0.5f), Shader.TileMode.CLAMP));
        float width = bitmapResizeAndCrop.getWidth();
        if (canvas2 != null) {
            canvas2.drawRect(0.0f, 0.0f, width, bitmapResizeAndCrop.getHeight(), paint);
        }
        kotlin.jvm.internal.n.d(imageView);
        kotlin.jvm.internal.n.d(bitmapCreateBitmap2);
        setPartBg(imageView, bitmapCreateBitmap2, bitmapCreateBitmap, statusBarNotification);
    }

    private final int darkenColor(int i2, float f2, float f3) {
        return Color.argb((int) (Color.alpha(i2) * f3), Math.max((int) (Color.red(i2) * f2), 0), Math.max((int) (Color.green(i2) * f2), 0), Math.max((int) (Color.blue(i2) * f2), 0));
    }

    public static /* synthetic */ int darkenColor$default(ModuleTinyBackgroundViewHolder moduleTinyBackgroundViewHolder, int i2, float f2, float f3, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            f3 = 1.0f;
        }
        return moduleTinyBackgroundViewHolder.darkenColor(i2, f2, f3);
    }

    private final Bitmap resizeAndCrop(Bitmap bitmap) {
        int dimension = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_tiny_part_bg_width_v2);
        int dimension2 = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_tiny_extend_height);
        if (bitmap.getWidth() < bitmap.getHeight()) {
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, dimension, (int) (bitmap.getHeight() * (dimension / bitmap.getWidth())), true);
            kotlin.jvm.internal.n.f(bitmapCreateScaledBitmap, "createScaledBitmap(...)");
            return Bitmap.createBitmap(bitmapCreateScaledBitmap, 0, (bitmapCreateScaledBitmap.getHeight() - dimension2) / 2, dimension, dimension2);
        }
        Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * (dimension2 / bitmap.getHeight())), dimension2, true);
        kotlin.jvm.internal.n.f(bitmapCreateScaledBitmap2, "createScaledBitmap(...)");
        if (bitmapCreateScaledBitmap2.getWidth() < dimension) {
            return null;
        }
        return Bitmap.createBitmap(bitmapCreateScaledBitmap2, (bitmapCreateScaledBitmap2.getWidth() - dimension) / 2, 0, dimension, dimension2);
    }

    private final void setPartBg(ImageView imageView, Bitmap bitmap, Bitmap bitmap2, StatusBarNotification statusBarNotification) {
        if (imageView.getWidth() > getScreenWidth()) {
            return;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        int width3 = bitmap2.getWidth();
        int height3 = bitmap2.getHeight();
        int i2 = width - width2;
        if (width <= 0 || height <= 0 || width2 <= 0 || height2 <= 0 || width3 <= 0 || height3 <= 0 || i2 <= 0) {
            Log.w(ModuleViewHolder.TAG, "setPartBg Invalid sizes: bg=(" + width + aa.f3429b + height + "), part=(" + width2 + aa.f3429b + height2 + "), color=(" + width3 + aa.f3429b + height3 + ") ");
            imageView.setImageBitmap(null);
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.HAS_CUSTOM_BG, false);
            return;
        }
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, height, bitmap2.getConfig());
            Matrix matrix = new Matrix();
            float f2 = i2;
            float f3 = height;
            matrix.setScale(f2 / width3, f3 / height3);
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmap2, matrix, null);
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width2, height, bitmap.getConfig());
            Matrix matrix2 = new Matrix();
            matrix2.setScale(1.0f, f3 / height2);
            new Canvas(bitmapCreateBitmap2).drawBitmap(bitmap, matrix2, null);
            Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap3);
            canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.drawBitmap(bitmapCreateBitmap2, f2, 0.0f, (Paint) null);
            imageView.setImageBitmap(bitmapCreateBitmap3);
        } catch (Exception e2) {
            Log.e(ModuleViewHolder.TAG, "setPartBg Error creating background bitmap", e2);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        Integer numValueOf;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        BgInfo bgInfo = template.getBgInfo();
        this.bgInfo = bgInfo;
        final Icon icon = getIcon(bgInfo != null ? bgInfo.getPicBg() : null, sbn);
        try {
            BgInfo bgInfo2 = template.getBgInfo();
            numValueOf = Integer.valueOf(Color.parseColor(bgInfo2 != null ? bgInfo2.getColorBg() : null));
        } catch (Exception unused) {
            numValueOf = null;
        }
        Bundle bitmapBundle = getBitmapBundle();
        if (bitmapBundle != null) {
            BgInfo bgInfo3 = this.bgInfo;
            if (bitmapBundle.containsKey(bgInfo3 != null ? bgInfo3.getPicBg() : null) && icon == null) {
                return;
            }
        }
        View view = getView();
        final ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.focus_notify_bg_image) : null;
        BgInfo bgInfo4 = this.bgInfo;
        if (bgInfo4 != null && bgInfo4.getType() == 2) {
            notifyDataChanged();
            return;
        }
        if (icon != null) {
            sbn.getNotification().extras.putBoolean(Const.Param.HAS_CUSTOM_BG, true);
            if (imageView != null) {
                imageView.setImageIcon(null);
            }
            if (imageView != null) {
                imageView.post(new Runnable() { // from class: miui.systemui.notification.focus.moduleV3.u
                    @Override // java.lang.Runnable
                    public final void run() {
                        ModuleTinyBackgroundViewHolder.bind$lambda$1(imageView, icon);
                    }
                });
            }
            sbn.getNotification().extras.putInt(Const.Param.BG_IMAGE_VID, R.id.focus_notify_bg_image);
            return;
        }
        if (numValueOf != null) {
            try {
                sbn.getNotification().extras.putBoolean(Const.Param.HAS_CUSTOM_BG, true);
                if (imageView != null) {
                    imageView.setBackgroundColor(numValueOf.intValue());
                }
                if (imageView != null) {
                    imageView.setImageIcon(null);
                }
                sbn.getNotification().extras.putInt(Const.Param.BG_IMAGE_VID, R.id.focus_notify_bg_image);
            } catch (Error e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public final int getScreenWidth() {
        return getCtx().getResources().getDisplayMetrics().widthPixels;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_background, getRootView()));
        registerCompressChanged(new AnonymousClass1());
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
