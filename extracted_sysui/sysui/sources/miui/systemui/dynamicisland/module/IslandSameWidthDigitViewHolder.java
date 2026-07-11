package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.Collection;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.IslandParamsException;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.SameWidthDigitInfo;
import miui.systemui.dynamicisland.model.TimerInfo;
import miui.systemui.util.CommonUtils;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.colorful.texteffect.HyperChronometer;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public class IslandSameWidthDigitViewHolder extends BaseIslandModuleViewHolder {
    private static final String TAG = "IslandSameWidthDigitViewHolder";
    private Choreographer choreographer;
    private TimerTextEffectView content;
    private float contentTransX;
    private float digitScaleX;
    private float digitScaleY;
    private float digitTransX;
    private final Choreographer.FrameCallback frameCallback;
    private DynamicIslandData lastData;
    private int lastLength;
    private HyperChronometer sameWidthDigit;
    private SameWidthDigitInfo sameWidthDigitInfo;
    private View textShade;
    private final IslandSameWidthDigitViewHolder$textWatcher$1 textWatcher;
    private boolean timerInitialized;
    private TimerTextEffectView title;
    private ViewGroup titleContainer;
    private boolean updateScheduled;
    public static final Companion Companion = new Companion(null);
    private static final IslandSameWidthDigitViewHolder$Companion$DIGIT_TRANS_X$1 DIGIT_TRANS_X = new FloatProperty<IslandSameWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder$Companion$DIGIT_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandSameWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.digitTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandSameWidthDigitViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.digitTransX = f2;
        }
    };
    private static final IslandSameWidthDigitViewHolder$Companion$CONTENT_TRANS_X$1 CONTENT_TRANS_X = new FloatProperty<IslandSameWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder$Companion$CONTENT_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandSameWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.contentTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandSameWidthDigitViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.contentTransX = f2;
        }
    };
    private static final IslandSameWidthDigitViewHolder$Companion$DIGIT_SCALE_X$1 DIGIT_SCALE_X = new FloatProperty<IslandSameWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder$Companion$DIGIT_SCALE_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandSameWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.digitScaleX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandSameWidthDigitViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.digitScaleX = f2;
        }
    };
    private static final IslandSameWidthDigitViewHolder$Companion$DIGIT_SCALE_Y$1 DIGIT_SCALE_Y = new FloatProperty<IslandSameWidthDigitViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder$Companion$DIGIT_SCALE_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandSameWidthDigitViewHolder holder) {
            n.g(holder, "holder");
            return holder.digitScaleY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandSameWidthDigitViewHolder holder, float f2) {
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
        IslandSameWidthDigitViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder$textWatcher$1] */
    public IslandSameWidthDigitViewHolder(Context context, ViewGroup rootView, Function2 emitEvent) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        this.textWatcher = new TextWatcher() { // from class: miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder$textWatcher$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (this.this$0.timerInitialized) {
                    if (this.this$0.lastLength != (editable != null ? editable.length() : 0)) {
                        super/*miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder*/.getEmitEvent().invoke(this.this$0.lastData, DynamicIslandEvent.UpdateDynamicIsland.INSTANCE);
                    }
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                this.this$0.lastLength = charSequence != null ? charSequence.length() : 0;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        };
        this.digitScaleX = 1.0f;
        this.digitScaleY = 1.0f;
        this.choreographer = Choreographer.getInstance();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.dynamicisland.module.i
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                IslandSameWidthDigitViewHolder.frameCallback$lambda$1(this.f5728a, j2);
            }
        };
    }

    private final void contentAnimation(float f2) {
        FolmeKt.getFolme(this).to(new AnimState().add(CONTENT_TRANS_X, f2, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder.contentAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandSameWidthDigitViewHolder.this.scheduleUpdate();
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
        FolmeKt.getFolme(this).to(new AnimState().add(DIGIT_SCALE_X, f2, new long[0]).add(DIGIT_SCALE_Y, f2, new long[0]).add(DIGIT_TRANS_X, f3, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder.digitAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandSameWidthDigitViewHolder.this.scheduleUpdate();
            }
        }));
    }

    private final void digitScheduleUpdate() {
        TimerTextEffectView timerTextEffectView = this.title;
        TextView textView = (timerTextEffectView == null || timerTextEffectView.getVisibility() != 0) ? this.sameWidthDigit : this.title;
        if (textView != null) {
            textView.setTranslationX(this.digitTransX);
        }
        if (textView != null) {
            textView.setScaleX(this.digitScaleX);
        }
        if (textView == null) {
            return;
        }
        textView.setScaleY(this.digitScaleY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$1(IslandSameWidthDigitViewHolder this$0, long j2) {
        n.g(this$0, "this$0");
        this$0.updateScheduled = false;
        this$0.digitScheduleUpdate();
        this$0.contentScheduleUpdate();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) throws IslandParamsException {
        HyperChronometer hyperChronometer;
        Boolean turnAnim;
        n.g(template, "template");
        super.bind(template, dynamicIslandData);
        this.lastData = dynamicIslandData;
        BigIslandArea bigIslandArea = template.getBigIslandArea();
        SameWidthDigitInfo sameWidthDigitInfo = bigIslandArea != null ? bigIslandArea.getSameWidthDigitInfo() : null;
        this.sameWidthDigitInfo = sameWidthDigitInfo;
        TimerInfo timerInfo = sameWidthDigitInfo != null ? sameWidthDigitInfo.getTimerInfo() : null;
        setFixedFont(this.sameWidthDigit);
        SameWidthDigitInfo sameWidthDigitInfo2 = this.sameWidthDigitInfo;
        String digit = sameWidthDigitInfo2 != null ? sameWidthDigitInfo2.getDigit() : null;
        SameWidthDigitInfo sameWidthDigitInfo3 = this.sameWidthDigitInfo;
        String content = sameWidthDigitInfo3 != null ? sameWidthDigitInfo3.getContent() : null;
        SameWidthDigitInfo sameWidthDigitInfo4 = this.sameWidthDigitInfo;
        if ((sameWidthDigitInfo4 != null ? sameWidthDigitInfo4.getDigit() : null) == null) {
            SameWidthDigitInfo sameWidthDigitInfo5 = this.sameWidthDigitInfo;
            if ((sameWidthDigitInfo5 != null ? sameWidthDigitInfo5.getTimerInfo() : null) == null) {
                throw new IslandParamsException("digit is empty");
            }
        }
        if (digit != null) {
            HyperChronometer hyperChronometer2 = this.sameWidthDigit;
            if (hyperChronometer2 != null) {
                hyperChronometer2.setBase(System.currentTimeMillis());
            }
            HyperChronometer hyperChronometer3 = this.sameWidthDigit;
            if (hyperChronometer3 != null) {
                hyperChronometer3.setVisibility(8);
            }
            TimerTextEffectView timerTextEffectView = this.title;
            if (timerTextEffectView != null) {
                timerTextEffectView.setVisibility(0);
            }
            SameWidthDigitInfo sameWidthDigitInfo6 = this.sameWidthDigitInfo;
            setTitleHighlightColor(template, sameWidthDigitInfo6 != null ? sameWidthDigitInfo6.getShowHighlightColor() : null, this.title, digit);
        } else if (timerInfo != null) {
            TimerTextEffectView timerTextEffectView2 = this.title;
            if (timerTextEffectView2 != null) {
                timerTextEffectView2.setVisibility(8);
            }
            HyperChronometer hyperChronometer4 = this.sameWidthDigit;
            if (hyperChronometer4 != null) {
                hyperChronometer4.setVisibility(0);
            }
            Long timerSystemCurrent = timerInfo.getTimerSystemCurrent();
            Long timerWhen = timerInfo.getTimerWhen();
            if (timerSystemCurrent == null || timerWhen == null) {
                HyperChronometer hyperChronometer5 = this.sameWidthDigit;
                if (hyperChronometer5 != null) {
                    hyperChronometer5.setVisibility(8);
                }
            } else {
                boolean z2 = timerInfo.getTimerType() < 0;
                HyperChronometer hyperChronometer6 = this.sameWidthDigit;
                if ((hyperChronometer6 == null || z2 != hyperChronometer6.isCountDown()) && (hyperChronometer = this.sameWidthDigit) != null) {
                    hyperChronometer.setCountDown(z2);
                }
                if (!this.timerInitialized) {
                    HyperChronometer hyperChronometer7 = this.sameWidthDigit;
                    if (hyperChronometer7 != null) {
                        hyperChronometer7.setBase((SystemClock.elapsedRealtime() + timerWhen.longValue()) - timerSystemCurrent.longValue());
                    }
                    this.timerInitialized = true;
                }
            }
            setupTimer(dynamicIslandData, Integer.valueOf(timerInfo.getTimerType()), timerInfo.getTimerWhen(), Long.valueOf(timerInfo.getTimerTotal()), timerInfo.getTimerSystemCurrent());
        }
        if (content != null) {
            TimerTextEffectView timerTextEffectView3 = this.content;
            if (timerTextEffectView3 != null) {
                SameWidthDigitInfo sameWidthDigitInfo7 = this.sameWidthDigitInfo;
                timerTextEffectView3.enableEffect((sameWidthDigitInfo7 == null || (turnAnim = sameWidthDigitInfo7.getTurnAnim()) == null) ? false : turnAnim.booleanValue());
            }
            SameWidthDigitInfo sameWidthDigitInfo8 = this.sameWidthDigitInfo;
            setContentHighlightColor(template, sameWidthDigitInfo8 != null ? sameWidthDigitInfo8.getShowHighlightColor() : null, this.content, content);
        } else {
            TimerTextEffectView timerTextEffectView4 = this.content;
            if (timerTextEffectView4 != null) {
                timerTextEffectView4.setText((CharSequence) null);
            }
        }
        TimerTextEffectView timerTextEffectView5 = this.content;
        if (timerTextEffectView5 == null) {
            return;
        }
        timerTextEffectView5.setVisibility(content != null ? 0 : 8);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.diff(template, dynamicIslandData);
    }

    public final SameWidthDigitInfo getSameWidthDigitInfo() {
        return this.sameWidthDigitInfo;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void initView(String module) {
        n.g(module, "module");
        super.initView(module);
        View view = getView();
        this.titleContainer = view != null ? (ViewGroup) view.findViewById(R.id.island_container_module_same_width_digit) : null;
        View view2 = getView();
        this.sameWidthDigit = view2 != null ? (HyperChronometer) view2.findViewById(R.id.island_chronometer) : null;
        View view3 = getView();
        this.title = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.island_title) : null;
        View view4 = getView();
        this.content = view4 != null ? (TimerTextEffectView) view4.findViewById(R.id.island_content) : null;
        View view5 = getView();
        this.textShade = view5 != null ? view5.findViewById(R.id.island_text_shade) : null;
        TimerTextEffectView timerTextEffectView = this.title;
        if (timerTextEffectView != null) {
            timerTextEffectView.setEnableEffectWithInit(false);
        }
        HyperChronometer hyperChronometer = this.sameWidthDigit;
        if (hyperChronometer != null) {
            hyperChronometer.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView2 = this.content;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView3 = this.title;
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
        HyperChronometer hyperChronometer2 = this.sameWidthDigit;
        if (hyperChronometer2 != null) {
            hyperChronometer2.addTextChangedListener(this.textWatcher);
        }
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onDetach() {
        super.onDetach();
        HyperChronometer hyperChronometer = this.sameWidthDigit;
        if (hyperChronometer != null) {
            hyperChronometer.removeTextChangedListener(this.textWatcher);
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

    public final void setSameWidthDigitInfo(SameWidthDigitInfo sameWidthDigitInfo) {
        this.sameWidthDigitInfo = sameWidthDigitInfo;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) throws IslandParamsException {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        TimerTextEffectView timerTextEffectView = this.title;
        ViewGroup.LayoutParams layoutParams = timerTextEffectView != null ? timerTextEffectView.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.width = -2;
        }
        HyperChronometer hyperChronometer = this.sameWidthDigit;
        ViewGroup.LayoutParams layoutParams2 = hyperChronometer != null ? hyperChronometer.getLayoutParams() : null;
        if (layoutParams2 != null) {
            layoutParams2.width = -2;
        }
        TimerTextEffectView timerTextEffectView2 = this.content;
        ViewGroup.LayoutParams layoutParams3 = timerTextEffectView2 != null ? timerTextEffectView2.getLayoutParams() : null;
        if (layoutParams3 != null) {
            layoutParams3.width = -2;
        }
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
        TimerTextEffectView timerTextEffectView;
        Log.d(TAG, "updateWidth: " + i2);
        int dimensionPixelSize = ((i2 - getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding)) - getContext().getResources().getDimensionPixelSize(R.dimen.text_padding)) - getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding_cutout);
        float dimensionPixelSize2 = (float) getContext().getResources().getDimensionPixelSize(R.dimen.island_text_padding_inner);
        TimerTextEffectView timerTextEffectView2 = this.title;
        TextView textView = (timerTextEffectView2 == null || timerTextEffectView2.getVisibility() != 0) ? this.sameWidthDigit : this.title;
        setFixedFont(this.sameWidthDigit);
        int textViewWidth = getTextViewWidth(textView, String.valueOf(textView != null ? textView.getText() : null));
        TimerTextEffectView timerTextEffectView3 = this.content;
        SameWidthDigitInfo sameWidthDigitInfo = this.sameWidthDigitInfo;
        int textViewWidth2 = getTextViewWidth(timerTextEffectView3, sameWidthDigitInfo != null ? sameWidthDigitInfo.getContent() : null);
        float f2 = textViewWidth + textViewWidth2 + (textViewWidth2 > 0 ? dimensionPixelSize2 : 0.0f);
        Log.d(TAG, "totalWidth: " + f2);
        if (textView != null) {
            textView.setPivotX(!CommonUtils.isLayoutRtl(getContext()) ? textViewWidth : 0.0f);
        }
        if (textView == null || textView.getVisibility() != 0 || ((timerTextEffectView = this.content) != null && timerTextEffectView.getVisibility() == 0)) {
            float height = textView != null ? textView.getHeight() : getContext().getResources().getDimensionPixelSize(R.dimen.island_title_height);
            if (textView != null) {
                textView.setPivotY(height - getContext().getResources().getDimensionPixelSize(R.dimen.island_text_content_margin_bottom));
            }
        } else {
            textView.setPivotY(textView.getHeight() / 2.0f);
        }
        float f3 = dimensionPixelSize;
        if (f2 <= f3) {
            digitAnimation(1.0f, 0.0f);
            contentAnimation(0.0f);
            View view = this.textShade;
            if (view != null) {
                view.setVisibility(8);
            }
            TimerTextEffectView timerTextEffectView4 = this.content;
            ViewGroup.LayoutParams layoutParams = timerTextEffectView4 != null ? timerTextEffectView4.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.width = textViewWidth2;
            }
            ViewGroup.LayoutParams layoutParams2 = textView != null ? textView.getLayoutParams() : null;
            if (layoutParams2 != null) {
                layoutParams2.width = textViewWidth;
            }
            TimerTextEffectView timerTextEffectView5 = this.content;
            if (timerTextEffectView5 != null) {
                timerTextEffectView5.requestLayout();
            }
            if (textView != null) {
                textView.requestLayout();
                return;
            }
            return;
        }
        float f4 = 0;
        digitAnimation(0.79f, f4);
        contentAnimation(f4);
        if (textView != null) {
            textView.setPivotX(!CommonUtils.isLayoutRtl(getContext()) ? textViewWidth : 0.0f);
        }
        if (textView != null) {
            textView.setPivotY(textView.getHeight() - getContext().getResources().getDimensionPixelSize(R.dimen.island_text_content_margin_bottom));
        }
        float f5 = (textViewWidth * 0.79f) + textViewWidth2;
        if (textViewWidth2 <= 0) {
            dimensionPixelSize2 = 0.0f;
        }
        float fFloor = (float) Math.floor(f5 + dimensionPixelSize2);
        Log.d(TAG, "newTotal: " + fFloor + " " + dimensionPixelSize);
        if (fFloor > f3) {
            float f6 = fFloor - f3;
            if (CommonUtils.isLayoutRtl(getContext())) {
                f6 = -f6;
            }
            digitAnimation(0.79f, f6);
            contentAnimation(f6);
            View view2 = this.textShade;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            setTextShade(this.textShade);
        } else {
            View view3 = this.textShade;
            if (view3 != null) {
                view3.setVisibility(8);
            }
        }
        TimerTextEffectView timerTextEffectView6 = this.content;
        ViewGroup.LayoutParams layoutParams3 = timerTextEffectView6 != null ? timerTextEffectView6.getLayoutParams() : null;
        if (layoutParams3 != null) {
            layoutParams3.width = textViewWidth2;
        }
        ViewGroup.LayoutParams layoutParams4 = textView != null ? textView.getLayoutParams() : null;
        if (layoutParams4 != null) {
            layoutParams4.width = textViewWidth;
        }
        TimerTextEffectView timerTextEffectView7 = this.content;
        if (timerTextEffectView7 != null) {
            timerTextEffectView7.requestLayout();
        }
        if (textView != null) {
            textView.requestLayout();
        }
        View view4 = getView();
        Integer numValueOf = view4 != null ? Integer.valueOf(view4.getWidth()) : null;
        ViewGroup viewGroup = this.titleContainer;
        Log.d(TAG, "updateWidth: " + dimensionPixelSize + " " + numValueOf + " " + (viewGroup != null ? Integer.valueOf(viewGroup.getWidth()) : null));
        Log.d(TAG, "updateWidth1: " + textViewWidth + " " + textViewWidth2 + " " + DynamicIslandUtils.INSTANCE.dpToPx(8, getContext()));
        super.updateWidth(dimensionPixelSize);
    }
}
