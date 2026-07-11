package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.Collection;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.IslandParamsException;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.FixedWidthDigitInfo;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.util.CommonUtils;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public class IslandFixedWidthDigitViewHolder extends BaseIslandModuleViewHolder {
    private static final String PLACE_HOLDER = "---";
    private static final String TAG = "IslandFixedWidthDigitViewHolder";
    private Choreographer choreographer;
    private TimerTextEffectView content;
    private float contentTransX;
    private float digitScaleX;
    private float digitScaleY;
    private float digitTransX;
    private TimerTextEffectView fixedWidthDigit;
    private FixedWidthDigitInfo fixedWidthDigitInfo;
    private final Choreographer.FrameCallback frameCallback;
    private View textShade;
    private ViewGroup titleContainer;
    private boolean updateScheduled;
    public static final Companion Companion = new Companion(null);
    private static final IslandFixedWidthDigitViewHolder$Companion$DIGIT_TRANS_X$1 DIGIT_TRANS_X = new FloatProperty<IslandFixedWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder$Companion$DIGIT_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandFixedWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.digitTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandFixedWidthDigitViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.digitTransX = f2;
        }
    };
    private static final IslandFixedWidthDigitViewHolder$Companion$CONTENT_TRANS_X$1 CONTENT_TRANS_X = new FloatProperty<IslandFixedWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder$Companion$CONTENT_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandFixedWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.contentTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandFixedWidthDigitViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.contentTransX = f2;
        }
    };
    private static final IslandFixedWidthDigitViewHolder$Companion$DIGIT_SCALE_X$1 DIGIT_SCALE_X = new FloatProperty<IslandFixedWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder$Companion$DIGIT_SCALE_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandFixedWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.digitScaleX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandFixedWidthDigitViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.digitScaleX = f2;
        }
    };
    private static final IslandFixedWidthDigitViewHolder$Companion$DIGIT_SCALE_Y$1 DIGIT_SCALE_Y = new FloatProperty<IslandFixedWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder$Companion$DIGIT_SCALE_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandFixedWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.digitScaleY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandFixedWidthDigitViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.digitScaleY = f2;
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        IslandFixedWidthDigitViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandFixedWidthDigitViewHolder(Context context, ViewGroup rootView, Function2 emitEvent) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        this.digitScaleX = 1.0f;
        this.digitScaleY = 1.0f;
        this.choreographer = Choreographer.getInstance();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.dynamicisland.module.a
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                IslandFixedWidthDigitViewHolder.frameCallback$lambda$6(this.f5720a, j2);
            }
        };
    }

    private final void contentAnimation(float f2) {
        FolmeKt.getFolme(this).to(new AnimState().add(CONTENT_TRANS_X, f2, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder.contentAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandFixedWidthDigitViewHolder.this.scheduleUpdate();
            }
        }));
    }

    private final void contentScheduleUpdate() {
        TimerTextEffectView timerTextEffectView = this.content;
        if (timerTextEffectView == null) {
            return;
        }
        timerTextEffectView.setTranslationX(this.contentTransX);
    }

    private final void digitAnimation(float f2, float f3) {
        FolmeKt.getFolme(this).to(new AnimState().add(DIGIT_SCALE_X, f2, new long[0]).add(DIGIT_SCALE_Y, f2, new long[0]).add(DIGIT_TRANS_X, f3, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder.digitAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandFixedWidthDigitViewHolder.this.scheduleUpdate();
            }
        }));
    }

    private final void digitScheduleUpdate() {
        TimerTextEffectView timerTextEffectView = this.fixedWidthDigit;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTranslationX(this.digitTransX);
        }
        TimerTextEffectView timerTextEffectView2 = this.fixedWidthDigit;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setScaleX(this.digitScaleX);
        }
        TimerTextEffectView timerTextEffectView3 = this.fixedWidthDigit;
        if (timerTextEffectView3 == null) {
            return;
        }
        timerTextEffectView3.setScaleY(this.digitScaleY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$6(IslandFixedWidthDigitViewHolder this$0, long j2) {
        n.g(this$0, "this$0");
        this$0.updateScheduled = false;
        this$0.digitScheduleUpdate();
        this$0.contentScheduleUpdate();
    }

    private final boolean isValidNumber(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (!Character.isDigit(cCharAt) && cCharAt != '.') {
                return false;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < str.length(); i4++) {
            if (Character.isDigit(str.charAt(i4))) {
                i3++;
            }
        }
        if (i3 > 3) {
            return false;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < str.length(); i6++) {
            if (str.charAt(i6) == '.') {
                i5++;
            }
        }
        return i5 <= 1;
    }

    private final String validateAndFormat(String str) {
        return (str == null || !isValidNumber(str)) ? PLACE_HOLDER : str;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) throws IslandParamsException {
        FixedWidthDigitInfo fixedWidthDigitInfo;
        FixedWidthDigitInfo fixedWidthDigitInfo2;
        n.g(template, "template");
        super.bind(template, dynamicIslandData);
        setFixedFont(this.fixedWidthDigit);
        BigIslandArea bigIslandArea = template.getBigIslandArea();
        this.fixedWidthDigitInfo = bigIslandArea != null ? bigIslandArea.getFixedWidthDigitInfo() : null;
        BigIslandArea bigIslandArea2 = template.getBigIslandArea();
        String digit = (bigIslandArea2 == null || (fixedWidthDigitInfo2 = bigIslandArea2.getFixedWidthDigitInfo()) == null) ? null : fixedWidthDigitInfo2.getDigit();
        BigIslandArea bigIslandArea3 = template.getBigIslandArea();
        String content = (bigIslandArea3 == null || (fixedWidthDigitInfo = bigIslandArea3.getFixedWidthDigitInfo()) == null) ? null : fixedWidthDigitInfo.getContent();
        if (digit == null) {
            Log.d(TAG, "digitText is null");
            throw new IslandParamsException("digitText is null");
        }
        FixedWidthDigitInfo fixedWidthDigitInfo3 = this.fixedWidthDigitInfo;
        setTitleHighlightColor(template, fixedWidthDigitInfo3 != null ? fixedWidthDigitInfo3.getShowHighlightColor() : null, this.fixedWidthDigit, validateAndFormat(digit));
        FixedWidthDigitInfo fixedWidthDigitInfo4 = this.fixedWidthDigitInfo;
        setContentHighlightColor(template, fixedWidthDigitInfo4 != null ? fixedWidthDigitInfo4.getShowHighlightColor() : null, this.content, content);
        TimerTextEffectView timerTextEffectView = this.fixedWidthDigit;
        if (timerTextEffectView != null) {
            timerTextEffectView.setVisibility(0);
        }
        TimerTextEffectView timerTextEffectView2 = this.content;
        if (timerTextEffectView2 == null) {
            return;
        }
        timerTextEffectView2.setVisibility(content == null ? 8 : 0);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.diff(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void initView(String module) {
        n.g(module, "module");
        super.initView(module);
        View view = getView();
        this.titleContainer = view != null ? (ViewGroup) view.findViewById(R.id.island_container_module_fix_width_digit) : null;
        View view2 = getView();
        this.fixedWidthDigit = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.island_digit) : null;
        View view3 = getView();
        this.content = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.island_content) : null;
        View view4 = getView();
        this.textShade = view4 != null ? view4.findViewById(R.id.island_text_shade) : null;
        TimerTextEffectView timerTextEffectView = this.fixedWidthDigit;
        if (timerTextEffectView != null) {
            timerTextEffectView.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView2 = this.content;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView3 = this.fixedWidthDigit;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView4 = this.content;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.setTextChangeProcessor(new TextChangeHelper());
        }
        ViewGroup viewGroup = this.titleContainer;
        if (viewGroup != null) {
            FolmeKt.cleanWhenViewDetached(this, viewGroup);
        }
    }

    public final void scheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        Choreographer choreographer = this.choreographer;
        if (choreographer != null) {
            choreographer.postFrameCallback(this.frameCallback);
        }
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) throws IslandParamsException {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        TimerTextEffectView timerTextEffectView = this.fixedWidthDigit;
        ViewGroup.LayoutParams layoutParams = timerTextEffectView != null ? timerTextEffectView.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.island_fixed_width);
        }
        TimerTextEffectView timerTextEffectView2 = this.content;
        ViewGroup.LayoutParams layoutParams2 = timerTextEffectView2 != null ? timerTextEffectView2.getLayoutParams() : null;
        if (layoutParams2 != null) {
            layoutParams2.width = -2;
        }
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
        FrameLayout.LayoutParams layoutParams;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.text_padding);
        float dimensionPixelSize3 = getContext().getResources().getDimensionPixelSize(R.dimen.island_text_padding_inner);
        TimerTextEffectView timerTextEffectView = this.content;
        FixedWidthDigitInfo fixedWidthDigitInfo = this.fixedWidthDigitInfo;
        int textViewWidth = getTextViewWidth(timerTextEffectView, fixedWidthDigitInfo != null ? fixedWidthDigitInfo.getContent() : null);
        int dimensionPixelSize4 = getContext().getResources().getDimensionPixelSize(R.dimen.island_fixed_width);
        float f2 = dimensionPixelSize4 + textViewWidth + (textViewWidth > 0 ? dimensionPixelSize3 : 0.0f) + dimensionPixelSize + dimensionPixelSize2;
        TimerTextEffectView timerTextEffectView2 = this.fixedWidthDigit;
        FixedWidthDigitInfo fixedWidthDigitInfo2 = this.fixedWidthDigitInfo;
        getTextViewWidth(timerTextEffectView2, fixedWidthDigitInfo2 != null ? fixedWidthDigitInfo2.getDigit() : null);
        setFixedFont(this.fixedWidthDigit);
        if (f2 > i2) {
            View view = this.textShade;
            if (view != null) {
                view.setVisibility(0);
            }
            if (CommonUtils.isLayoutRtl(getContext())) {
                View view2 = this.textShade;
                ViewGroup.LayoutParams layoutParams2 = view2 != null ? view2.getLayoutParams() : null;
                layoutParams = layoutParams2 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams2 : null;
                if (layoutParams != null) {
                    layoutParams.gravity = 8388627;
                    View view3 = this.textShade;
                    if (view3 != null) {
                        view3.setLayoutParams(layoutParams);
                    }
                }
                View view4 = this.textShade;
                if (view4 != null) {
                    view4.setTranslationX(-(dimensionPixelSize3 + dimensionPixelSize4));
                }
            } else {
                View view5 = this.textShade;
                ViewGroup.LayoutParams layoutParams3 = view5 != null ? view5.getLayoutParams() : null;
                layoutParams = layoutParams3 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams3 : null;
                if (layoutParams != null) {
                    layoutParams.gravity = 8388629;
                    View view6 = this.textShade;
                    if (view6 != null) {
                        view6.setLayoutParams(layoutParams);
                    }
                }
                View view7 = this.textShade;
                if (view7 != null) {
                    view7.setTranslationX(0.0f);
                }
            }
        } else {
            View view8 = this.textShade;
            if (view8 != null) {
                view8.setVisibility(8);
            }
        }
        TimerTextEffectView timerTextEffectView3 = this.content;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.requestLayout();
        }
        TimerTextEffectView timerTextEffectView4 = this.fixedWidthDigit;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.requestLayout();
        }
        super.updateWidth(i2);
    }
}
