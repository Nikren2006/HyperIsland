package miui.systemui.dynamicisland.module;

import H0.s;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.TextInfo;
import miui.systemui.util.CommonUtils;
import miuix.animation.Folme;
import miuix.animation.FolmeObject;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public class BaseIslandModuleViewHolder implements FolmeObject {
    public static final Companion Companion = new Companion(0 == true ? 1 : 0);
    private static final Typeface FIXED_FONT;
    private static final String FONT_MI_SANS;
    public static final float MINI_VISIBLE_CHANGE = 0.01f;
    private static final String TAG = "BaseIslandModuleViewHolder";
    private final /* synthetic */ FolmeObject $$delegate_0;
    private Bundle bitmapBundle;
    private final Context context;
    private final Function2 emitEvent;
    private String module;
    private final ViewGroup rootView;
    private View view;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Typeface typefaceBuild = null;
        String str = SystemProperties.get("ro.miui.ui.font.mi_font_path", "/system/fonts/MiSansVF.ttf");
        FONT_MI_SANS = str;
        try {
            typefaceBuild = new Typeface.Builder(str).setFontVariationSettings("'wght' 450").build();
        } catch (Exception unused) {
        }
        FIXED_FONT = typefaceBuild;
    }

    public BaseIslandModuleViewHolder(Context context, ViewGroup rootView, Function2 emitEvent) {
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        this.context = context;
        this.rootView = rootView;
        this.emitEvent = emitEvent;
        this.$$delegate_0 = FolmeKt.FolmeObject();
    }

    private final void updateBundle(DynamicIslandData dynamicIslandData) {
        Bundle extras;
        this.bitmapBundle = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : extras.getBundle("miui.focus.pics");
    }

    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        updateBundle(dynamicIslandData);
    }

    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
    }

    @Override // miuix.animation.FolmeObject
    public Folme.ObjectFolmeImpl folme() {
        return this.$$delegate_0.folme();
    }

    public final Bundle getBitmapBundle() {
        return this.bitmapBundle;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Function2 getEmitEvent() {
        return this.emitEvent;
    }

    public final String getModule() {
        return this.module;
    }

    public final ViewGroup getRootView() {
        return this.rootView;
    }

    public final int getTextViewWidth(TextView textView, String str) {
        if (textView == null || str == null) {
            return 0;
        }
        Log.e(TAG, "getTextViewWidth: " + ((int) textView.getPaint().measureText(str)) + " " + textView + " " + str);
        return (int) textView.getPaint().measureText(str);
    }

    public final View getView() {
        return this.view;
    }

    public void initLayout(View view) {
        n.g(view, "view");
        this.view = view;
    }

    public void initView(String module) {
        n.g(module, "module");
        this.module = module;
    }

    public void onDetach() {
    }

    public void onHidden() {
    }

    public void onShow() {
    }

    public final void setBitmapBundle(Bundle bundle) {
        this.bitmapBundle = bundle;
    }

    public final void setContentHighlightColor(IslandTemplate template, Boolean bool, TimerTextEffectView timerTextEffectView, String str) {
        n.g(template, "template");
        Log.e(TAG, "setContentHighlightColor");
        Integer numValueOf = template.getHighlightColor() != null ? Integer.valueOf(ColorUtils.setAlphaComponent(Color.parseColor(template.getHighlightColor()), (int) (((double) Color.alpha(Color.parseColor(template.getHighlightColor()))) * 0.8d))) : null;
        if (timerTextEffectView != null) {
            timerTextEffectView.updateTextWithNewAppearance(str, Integer.valueOf((numValueOf == null || !n.c(bool, Boolean.TRUE)) ? this.context.getResources().getColor(R.color.content_color) : numValueOf.intValue()));
        }
    }

    public final s setFixedFont(TextView textView) {
        Typeface typeface = FIXED_FONT;
        if (typeface == null) {
            return null;
        }
        if (textView != null) {
            textView.setTypeface(typeface);
        }
        return s.f314a;
    }

    @Override // miuix.animation.FolmeObject
    public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
        this.$$delegate_0.setFolmeImpl(objectFolmeImpl);
    }

    public final void setFont(TextInfo textInfo, TextView textView) {
        if ((textInfo != null ? textInfo.getNarrowFont() : null) != null) {
            if (!n.c(textInfo.getNarrowFont(), Boolean.TRUE)) {
                Typeface typefaceCreate = Typeface.create("mipro-demibold", 0);
                if (textView != null) {
                    textView.setTypeface(typefaceCreate);
                    return;
                }
                return;
            }
            Typeface typefaceCreate2 = Typeface.create("misans-condensed", 0);
            if (textView != null) {
                textView.setTypeface(typefaceCreate2);
            }
            if (textView != null) {
                textView.setFontVariationSettings("'wght' 450, 'wdth' 30");
            }
        }
    }

    public final void setModule(String str) {
        this.module = str;
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void setTextShade(View view) {
        if (CommonUtils.isLayoutRtl(this.context)) {
            if (view == null) {
                return;
            }
            view.setBackground(this.context.getResources().getDrawable(R.drawable.dynamic_island_shade_rtl));
        } else {
            if (view == null) {
                return;
            }
            view.setBackground(this.context.getResources().getDrawable(R.drawable.dynamic_island_shade));
        }
    }

    public final void setTitleHighlightColor(IslandTemplate template, Boolean bool, TimerTextEffectView timerTextEffectView, String str) {
        n.g(template, "template");
        Log.e(TAG, "setTitleHighlightColor");
        if (timerTextEffectView != null) {
            timerTextEffectView.updateTextWithNewAppearance(str, Integer.valueOf((template.getHighlightColor() == null || !n.c(bool, Boolean.TRUE)) ? -1 : Color.parseColor(template.getHighlightColor())));
        }
    }

    public final void setView(View view) {
        this.view = view;
    }

    public final void setupTimer(DynamicIslandData dynamicIslandData, Integer num, Long l2, Long l3, Long l4) {
        Bundle extras;
        if (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) {
            return;
        }
        if (num != null) {
            extras.putInt("timerType", num.intValue());
        }
        if (l2 != null) {
            extras.putLong("timerWhen", l2.longValue());
        }
        if (l3 != null) {
            extras.putLong("timerTotal", l3.longValue());
        }
        if (l4 != null) {
            extras.putLong("timerSystemCurrent", l4.longValue());
        }
    }

    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        updateBundle(dynamicIslandData);
    }

    public void updateWidth(int i2) {
    }
}
