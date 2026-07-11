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
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.TextInfo;
import miui.systemui.util.CommonUtils;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public class IslandRightTextViewHolder extends BaseIslandModuleViewHolder {
    public static final String TAG = "IslandRightTextViewHolder";
    private Choreographer choreographer;
    private TimerTextEffectView content;
    private float contentTransX;
    private String curHighlightColor;
    private final Choreographer.FrameCallback frameCallback;
    private TimerTextEffectView frontTitle;
    private float frontTitleTransX;
    private boolean lastRtl;
    private int lastWidth;
    private Boolean narrowFont;
    private boolean scaleChanged;
    private Boolean showHighlightInfo;
    private boolean textChanged;
    private TextInfo textInfo;
    private View textShade;
    private TimerTextEffectView title;
    private ViewGroup titleContainer;
    private float titleScaleX;
    private float titleScaleY;
    private float titleTransX;
    private boolean updateScheduled;
    public static final Companion Companion = new Companion(null);
    private static final IslandRightTextViewHolder$Companion$TITLE_TRANS_X$1 TITLE_TRANS_X = new FloatProperty<IslandRightTextViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder$Companion$TITLE_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandRightTextViewHolder holder) {
            n.g(holder, "holder");
            return holder.titleTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandRightTextViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.titleTransX = f2;
        }
    };
    private static final IslandRightTextViewHolder$Companion$CONTENT_TRANS_X$1 CONTENT_TRANS_X = new FloatProperty<IslandRightTextViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder$Companion$CONTENT_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandRightTextViewHolder holder) {
            n.g(holder, "holder");
            return holder.contentTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandRightTextViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.contentTransX = f2;
        }
    };
    private static final IslandRightTextViewHolder$Companion$FRONT_TITLE_TRANS_X$1 FRONT_TITLE_TRANS_X = new FloatProperty<IslandRightTextViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder$Companion$FRONT_TITLE_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandRightTextViewHolder holder) {
            n.g(holder, "holder");
            return holder.frontTitleTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandRightTextViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.frontTitleTransX = f2;
        }
    };
    private static final IslandRightTextViewHolder$Companion$TITLE_SCALE_X$1 TITLE_SCALE_X = new FloatProperty<IslandRightTextViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder$Companion$TITLE_SCALE_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandRightTextViewHolder holder) {
            n.g(holder, "holder");
            return holder.titleScaleX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandRightTextViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.titleScaleX = f2;
        }
    };
    private static final IslandRightTextViewHolder$Companion$TITLE_SCALE_Y$1 TITLE_SCALE_Y = new FloatProperty<IslandRightTextViewHolder>() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder$Companion$TITLE_SCALE_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandRightTextViewHolder holder) {
            n.g(holder, "holder");
            return holder.titleScaleY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandRightTextViewHolder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.titleScaleY = f2;
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
        IslandRightTextViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandRightTextViewHolder(Context context, ViewGroup rootView, Function2 emitEvent) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        this.titleScaleX = 1.0f;
        this.titleScaleY = 1.0f;
        this.choreographer = Choreographer.getInstance();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.dynamicisland.module.h
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                IslandRightTextViewHolder.frameCallback$lambda$2(this.f5727a, j2);
            }
        };
    }

    private final void attach() {
        ViewGroup viewGroup = this.titleContainer;
        if (viewGroup != null) {
            viewGroup.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder.attach.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View p02) {
                    n.g(p02, "p0");
                    Log.d(IslandRightTextViewHolder.TAG, "onViewAttachedToWindow");
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View p02) {
                    n.g(p02, "p0");
                    Log.d(IslandRightTextViewHolder.TAG, "onViewDetachedFromWindow");
                    FolmeKt.getFolme(IslandRightTextViewHolder.this).end("dynamic_title_trans_x", "dynamic_content_trans_x", "dynamic_front_title_trans_x", "dynamic_title_scale_x", "dynamic_title_scale_y");
                    FolmeKt.getFolme(IslandRightTextViewHolder.this).clean();
                    IslandRightTextViewHolder.this.titleScheduleUpdate();
                    IslandRightTextViewHolder.this.contentScheduleUpdate();
                    IslandRightTextViewHolder.this.frontTitleScheduleUpdate();
                }
            });
        }
    }

    private final void contentAnimation(float f2) {
        FolmeKt.getFolme(this).to(new AnimState().add(CONTENT_TRANS_X, f2, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder.contentAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandRightTextViewHolder.this.scheduleUpdate();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void contentScheduleUpdate() {
        TimerTextEffectView timerTextEffectView = this.content;
        if (timerTextEffectView == null) {
            return;
        }
        timerTextEffectView.setTranslationX(this.contentTransX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$2(IslandRightTextViewHolder this$0, long j2) {
        n.g(this$0, "this$0");
        this$0.updateScheduled = false;
        this$0.titleScheduleUpdate();
        this$0.contentScheduleUpdate();
        this$0.frontTitleScheduleUpdate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void frontTitleScheduleUpdate() {
        TimerTextEffectView timerTextEffectView = this.frontTitle;
        if (timerTextEffectView == null) {
            return;
        }
        timerTextEffectView.setTranslationX(this.frontTitleTransX);
    }

    private final void frontTitleTranXAnimation(float f2) {
        FolmeKt.getFolme(this).to(new AnimState().add(FRONT_TITLE_TRANS_X, f2, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder.frontTitleTranXAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandRightTextViewHolder.this.scheduleUpdate();
            }
        }));
    }

    private final int getSpaceWidth(int i2, int i3, int i4) {
        if (i2 > 0 && i3 > 0) {
            return i4 * 2;
        }
        if (i2 > 0 || i3 > 0) {
            return i4;
        }
        return 0;
    }

    private final void resetPosition() {
        frontTitleTranXAnimation(0.0f);
        titleAnimation(1.0f, 0.0f);
        contentAnimation(0.0f);
    }

    private final void scaleChanged(float f2) {
        TimerTextEffectView timerTextEffectView = this.title;
        if (n.a(f2, timerTextEffectView != null ? Float.valueOf(timerTextEffectView.getScaleX()) : null)) {
            return;
        }
        this.scaleChanged = true;
    }

    private final void scaledPositionAdjust(int i2) {
        if (CommonUtils.isLayoutRtl(getContext())) {
            frontTitleTranXAnimation(i2 * (-0.21f));
        } else {
            frontTitleTranXAnimation(i2 * 0.21f);
        }
        titleAnimation(0.79f, 0.0f);
        contentAnimation(0.0f);
    }

    private final void setTextVisibility(boolean z2, boolean z3, boolean z4, boolean z5) {
        TextInfo textInfo;
        String content;
        TimerTextEffectView timerTextEffectView;
        TextInfo textInfo2;
        String frontTitle;
        TimerTextEffectView timerTextEffectView2;
        TextInfo textInfo3;
        String title;
        if (!z3 || (textInfo3 = this.textInfo) == null || (title = textInfo3.getTitle()) == null || title.length() <= 0) {
            TimerTextEffectView timerTextEffectView3 = this.title;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView4 = this.title;
            if (timerTextEffectView4 != null) {
                timerTextEffectView4.setVisibility(0);
            }
        }
        if (!z2 || (textInfo2 = this.textInfo) == null || (frontTitle = textInfo2.getFrontTitle()) == null || frontTitle.length() <= 0 || (timerTextEffectView2 = this.title) == null || timerTextEffectView2.getVisibility() != 0) {
            TimerTextEffectView timerTextEffectView5 = this.frontTitle;
            if (timerTextEffectView5 != null) {
                timerTextEffectView5.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView6 = this.frontTitle;
            if (timerTextEffectView6 != null) {
                timerTextEffectView6.setVisibility(0);
            }
        }
        if (!z4 || (textInfo = this.textInfo) == null || (content = textInfo.getContent()) == null || content.length() <= 0 || (timerTextEffectView = this.title) == null || timerTextEffectView.getVisibility() != 0) {
            TimerTextEffectView timerTextEffectView7 = this.content;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView8 = this.content;
            if (timerTextEffectView8 != null) {
                timerTextEffectView8.setVisibility(0);
            }
        }
        if (z5) {
            View view = this.textShade;
            if (view == null) {
                return;
            }
            view.setVisibility(0);
            return;
        }
        View view2 = this.textShade;
        if (view2 == null) {
            return;
        }
        view2.setVisibility(8);
    }

    private final void setTitlePivot(int i2) {
        TimerTextEffectView timerTextEffectView;
        TimerTextEffectView timerTextEffectView2;
        TimerTextEffectView timerTextEffectView3 = this.title;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setPivotX(!CommonUtils.isLayoutRtl(getContext()) ? i2 : 0.0f);
        }
        TimerTextEffectView timerTextEffectView4 = this.title;
        if (timerTextEffectView4 != null && timerTextEffectView4.getVisibility() == 0 && (((timerTextEffectView = this.content) == null || timerTextEffectView.getVisibility() != 0) && ((timerTextEffectView2 = this.frontTitle) == null || timerTextEffectView2.getVisibility() != 0))) {
            TimerTextEffectView timerTextEffectView5 = this.title;
            if (timerTextEffectView5 == null) {
                return;
            }
            timerTextEffectView5.setPivotY(timerTextEffectView5 != null ? timerTextEffectView5.getHeight() / 2.0f : 0.0f);
            return;
        }
        TimerTextEffectView timerTextEffectView6 = this.title;
        float height = timerTextEffectView6 != null ? timerTextEffectView6.getHeight() : getContext().getResources().getDimensionPixelSize(R.dimen.island_title_height);
        TimerTextEffectView timerTextEffectView7 = this.title;
        if (timerTextEffectView7 == null) {
            return;
        }
        timerTextEffectView7.setPivotY(height - getContext().getResources().getDimensionPixelSize(R.dimen.island_text_content_margin_bottom));
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean textChanged(miui.systemui.dynamicisland.model.IslandTemplate r5) {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.module.IslandRightTextViewHolder.textChanged(miui.systemui.dynamicisland.model.IslandTemplate):boolean");
    }

    private final void titleAnimation(float f2, float f3) {
        FolmeKt.getFolme(this).to(new AnimState().add(TITLE_SCALE_X, f2, new long[0]).add(TITLE_SCALE_Y, f2, new long[0]).add(TITLE_TRANS_X, f3, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandRightTextViewHolder.titleAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandRightTextViewHolder.this.scheduleUpdate();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void titleScheduleUpdate() {
        TimerTextEffectView timerTextEffectView = this.title;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTranslationX(this.titleTransX);
        }
        TimerTextEffectView timerTextEffectView2 = this.title;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setScaleX(this.titleScaleX);
        }
        TimerTextEffectView timerTextEffectView3 = this.title;
        if (timerTextEffectView3 == null) {
            return;
        }
        timerTextEffectView3.setScaleY(this.titleScaleY);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        Boolean narrowFont;
        Boolean showHighlightColor;
        TimerTextEffectView timerTextEffectView;
        TimerTextEffectView timerTextEffectView2;
        n.g(template, "template");
        super.bind(template, dynamicIslandData);
        this.curHighlightColor = template.getHighlightColor();
        if (n.c(getModule(), DynamicIslandConstants.MODULE_TEXT)) {
            BigIslandArea bigIslandArea = template.getBigIslandArea();
            this.textInfo = bigIslandArea != null ? bigIslandArea.getTextInfo() : null;
        }
        TextInfo textInfo = this.textInfo;
        if (textInfo == null || (narrowFont = textInfo.getNarrowFont()) == null) {
            narrowFont = Boolean.FALSE;
        }
        this.narrowFont = narrowFont;
        TextInfo textInfo2 = this.textInfo;
        if (textInfo2 == null || (showHighlightColor = textInfo2.getShowHighlightColor()) == null) {
            showHighlightColor = Boolean.FALSE;
        }
        this.showHighlightInfo = showHighlightColor;
        setFont(this.textInfo, this.title);
        setFont(this.textInfo, this.frontTitle);
        setFont(this.textInfo, this.content);
        TextInfo textInfo3 = this.textInfo;
        String title = textInfo3 != null ? textInfo3.getTitle() : null;
        TextInfo textInfo4 = this.textInfo;
        String frontTitle = textInfo4 != null ? textInfo4.getFrontTitle() : null;
        TextInfo textInfo5 = this.textInfo;
        String content = textInfo5 != null ? textInfo5.getContent() : null;
        if (title == null || title.length() <= 0) {
            TimerTextEffectView timerTextEffectView3 = this.title;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setVisibility(8);
            }
            TimerTextEffectView timerTextEffectView4 = this.title;
            if (timerTextEffectView4 != null) {
                timerTextEffectView4.setText(title);
            }
        } else {
            TimerTextEffectView timerTextEffectView5 = this.title;
            if (timerTextEffectView5 != null) {
                timerTextEffectView5.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView6 = this.title;
            if (timerTextEffectView6 != null) {
                TextInfo textInfo6 = this.textInfo;
                timerTextEffectView6.enableEffect(textInfo6 != null ? n.c(textInfo6.getTurnAnim(), Boolean.TRUE) : false);
            }
            TextInfo textInfo7 = this.textInfo;
            setTitleHighlightColor(template, textInfo7 != null ? textInfo7.getShowHighlightColor() : null, this.title, title);
        }
        if (frontTitle == null || frontTitle.length() <= 0 || (timerTextEffectView2 = this.title) == null || timerTextEffectView2.getVisibility() != 0) {
            TimerTextEffectView timerTextEffectView7 = this.frontTitle;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.setVisibility(8);
            }
            TimerTextEffectView timerTextEffectView8 = this.frontTitle;
            if (timerTextEffectView8 != null) {
                timerTextEffectView8.setText(frontTitle);
            }
        } else {
            TimerTextEffectView timerTextEffectView9 = this.frontTitle;
            if (timerTextEffectView9 != null) {
                timerTextEffectView9.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView10 = this.frontTitle;
            if (timerTextEffectView10 != null) {
                TextInfo textInfo8 = this.textInfo;
                timerTextEffectView10.enableEffect(textInfo8 != null ? n.c(textInfo8.getTurnAnim(), Boolean.TRUE) : false);
            }
            TextInfo textInfo9 = this.textInfo;
            setContentHighlightColor(template, textInfo9 != null ? textInfo9.getShowHighlightColor() : null, this.frontTitle, frontTitle);
        }
        if (content == null || content.length() <= 0 || (timerTextEffectView = this.title) == null || timerTextEffectView.getVisibility() != 0) {
            TimerTextEffectView timerTextEffectView11 = this.content;
            if (timerTextEffectView11 != null) {
                timerTextEffectView11.setVisibility(8);
            }
            TimerTextEffectView timerTextEffectView12 = this.content;
            if (timerTextEffectView12 == null) {
                return;
            }
            timerTextEffectView12.setText(content);
            return;
        }
        TimerTextEffectView timerTextEffectView13 = this.content;
        if (timerTextEffectView13 != null) {
            timerTextEffectView13.setVisibility(0);
        }
        TimerTextEffectView timerTextEffectView14 = this.content;
        if (timerTextEffectView14 != null) {
            TextInfo textInfo10 = this.textInfo;
            timerTextEffectView14.enableEffect(textInfo10 != null ? n.c(textInfo10.getTurnAnim(), Boolean.TRUE) : false);
        }
        TextInfo textInfo11 = this.textInfo;
        setContentHighlightColor(template, textInfo11 != null ? textInfo11.getShowHighlightColor() : null, this.content, content);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.diff(template, dynamicIslandData);
    }

    public final Choreographer getChoreographer() {
        return this.choreographer;
    }

    public final TextInfo getTextInfo() {
        return this.textInfo;
    }

    public final boolean getUpdateScheduled() {
        return this.updateScheduled;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void initView(String module) {
        n.g(module, "module");
        super.initView(module);
        View view = getView();
        this.titleContainer = view != null ? (ViewGroup) view.findViewById(R.id.island_container_module_right_text) : null;
        View view2 = getView();
        this.title = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.island_title) : null;
        View view3 = getView();
        this.frontTitle = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.island_front_title) : null;
        View view4 = getView();
        this.content = view4 != null ? (TimerTextEffectView) view4.findViewById(R.id.island_content) : null;
        TimerTextEffectView timerTextEffectView = this.title;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.frontTitle;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView3 = this.content;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView4 = this.title;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView5 = this.content;
        if (timerTextEffectView5 != null) {
            timerTextEffectView5.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView6 = this.frontTitle;
        if (timerTextEffectView6 != null) {
            timerTextEffectView6.setEnableEffectWithInit(false);
        }
        View view5 = getView();
        this.textShade = view5 != null ? view5.findViewById(R.id.island_text_shade) : null;
        attach();
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

    public final void setChoreographer(Choreographer choreographer) {
        this.choreographer = choreographer;
    }

    public final void setTextInfo(TextInfo textInfo) {
        this.textInfo = textInfo;
    }

    public final void setUpdateScheduled(boolean z2) {
        this.updateScheduled = z2;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        if (n.c(getModule(), DynamicIslandConstants.MODULE_TEXT)) {
            BigIslandArea bigIslandArea = template.getBigIslandArea();
            this.textInfo = bigIslandArea != null ? bigIslandArea.getTextInfo() : null;
        }
        if (!textChanged(template)) {
            this.textChanged = false;
            return;
        }
        this.textChanged = true;
        TimerTextEffectView timerTextEffectView = this.frontTitle;
        ViewGroup.LayoutParams layoutParams = timerTextEffectView != null ? timerTextEffectView.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.width = -2;
        }
        TimerTextEffectView timerTextEffectView2 = this.content;
        ViewGroup.LayoutParams layoutParams2 = timerTextEffectView2 != null ? timerTextEffectView2.getLayoutParams() : null;
        if (layoutParams2 != null) {
            layoutParams2.width = -2;
        }
        TimerTextEffectView timerTextEffectView3 = this.title;
        ViewGroup.LayoutParams layoutParams3 = timerTextEffectView3 != null ? timerTextEffectView3.getLayoutParams() : null;
        if (layoutParams3 != null) {
            layoutParams3.width = -2;
        }
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
        int dimensionPixelSize = ((i2 - getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding)) - getContext().getResources().getDimensionPixelSize(R.dimen.text_padding)) - getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding_cutout);
        TimerTextEffectView timerTextEffectView = this.frontTitle;
        TextInfo textInfo = this.textInfo;
        int textViewWidth = getTextViewWidth(timerTextEffectView, textInfo != null ? textInfo.getFrontTitle() : null);
        TimerTextEffectView timerTextEffectView2 = this.title;
        TextInfo textInfo2 = this.textInfo;
        int textViewWidth2 = getTextViewWidth(timerTextEffectView2, textInfo2 != null ? textInfo2.getTitle() : null);
        TimerTextEffectView timerTextEffectView3 = this.content;
        TextInfo textInfo3 = this.textInfo;
        int textViewWidth3 = getTextViewWidth(timerTextEffectView3, textInfo3 != null ? textInfo3.getContent() : null);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.island_text_padding_inner);
        int i3 = textViewWidth + textViewWidth2;
        int i4 = i3 + textViewWidth3;
        int spaceWidth = getSpaceWidth(textViewWidth, textViewWidth, dimensionPixelSize2) + i4;
        Log.d(TAG, "originWidth:" + i2 + ", updateWidth: " + dimensionPixelSize + ", totalTextWidth: " + spaceWidth);
        scaleChanged(spaceWidth > dimensionPixelSize ? 0.79f : 1.0f);
        if (dimensionPixelSize != this.lastWidth || this.textChanged || CommonUtils.isLayoutRtl(getContext()) != this.lastRtl || this.scaleChanged) {
            this.textChanged = false;
            this.scaleChanged = false;
            this.lastWidth = dimensionPixelSize;
            this.lastRtl = CommonUtils.isLayoutRtl(getContext());
            if (dimensionPixelSize >= i4 + getSpaceWidth(textViewWidth, textViewWidth, dimensionPixelSize2)) {
                Log.d(TAG, "case1");
                setTextVisibility(true, true, true, false);
                setTitlePivot(textViewWidth2);
                resetPosition();
            } else {
                float f2 = dimensionPixelSize;
                float f3 = textViewWidth2 * 0.79f;
                float f4 = textViewWidth + f3;
                if (f2 >= textViewWidth3 + f4 + getSpaceWidth(textViewWidth, textViewWidth, dimensionPixelSize2)) {
                    Log.d(TAG, "case2");
                    setTextVisibility(true, true, true, false);
                    setTitlePivot(textViewWidth2);
                    scaledPositionAdjust(textViewWidth2);
                } else if (dimensionPixelSize >= i3 + getSpaceWidth(textViewWidth, textViewWidth, dimensionPixelSize2)) {
                    Log.d(TAG, "case3");
                    setTextVisibility(true, true, false, false);
                    setTitlePivot(textViewWidth2);
                    resetPosition();
                } else if (f2 >= f4 + getSpaceWidth(textViewWidth, textViewWidth, dimensionPixelSize2)) {
                    Log.d(TAG, "case4");
                    setTextVisibility(true, true, false, false);
                    setTitlePivot(textViewWidth2);
                    scaledPositionAdjust(textViewWidth2);
                } else if (dimensionPixelSize >= textViewWidth2) {
                    Log.d(TAG, "case5");
                    setTextVisibility(false, true, false, false);
                    setTitlePivot(textViewWidth2);
                    resetPosition();
                } else if (f2 >= f3) {
                    Log.d(TAG, "case6");
                    setTextVisibility(false, true, false, false);
                    setTitlePivot(textViewWidth2);
                    scaledPositionAdjust(textViewWidth2);
                } else {
                    Log.d(TAG, "case7");
                    setTextVisibility(false, true, false, true);
                    setTitlePivot(textViewWidth2);
                    if (CommonUtils.isLayoutRtl(getContext())) {
                        View view = this.textShade;
                        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
                        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
                        if (layoutParams2 != null) {
                            layoutParams2.gravity = 8388627;
                            View view2 = this.textShade;
                            if (view2 != null) {
                                view2.setLayoutParams(layoutParams2);
                            }
                        }
                        titleAnimation(0.79f, 0.0f);
                    } else {
                        View view3 = this.textShade;
                        ViewGroup.LayoutParams layoutParams3 = view3 != null ? view3.getLayoutParams() : null;
                        FrameLayout.LayoutParams layoutParams4 = layoutParams3 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams3 : null;
                        if (layoutParams4 != null) {
                            layoutParams4.gravity = 8388629;
                            View view4 = this.textShade;
                            if (view4 != null) {
                                view4.setLayoutParams(layoutParams4);
                            }
                        }
                        titleAnimation(0.79f, f3 - f2);
                    }
                }
            }
            TimerTextEffectView timerTextEffectView4 = this.frontTitle;
            ViewGroup.LayoutParams layoutParams5 = timerTextEffectView4 != null ? timerTextEffectView4.getLayoutParams() : null;
            if (layoutParams5 != null) {
                layoutParams5.width = textViewWidth;
            }
            TimerTextEffectView timerTextEffectView5 = this.content;
            ViewGroup.LayoutParams layoutParams6 = timerTextEffectView5 != null ? timerTextEffectView5.getLayoutParams() : null;
            if (layoutParams6 != null) {
                layoutParams6.width = textViewWidth3;
            }
            TimerTextEffectView timerTextEffectView6 = this.title;
            ViewGroup.LayoutParams layoutParams7 = timerTextEffectView6 != null ? timerTextEffectView6.getLayoutParams() : null;
            if (layoutParams7 != null) {
                layoutParams7.width = textViewWidth2;
            }
            TimerTextEffectView timerTextEffectView7 = this.frontTitle;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.requestLayout();
            }
            TimerTextEffectView timerTextEffectView8 = this.content;
            if (timerTextEffectView8 != null) {
                timerTextEffectView8.requestLayout();
            }
            TimerTextEffectView timerTextEffectView9 = this.title;
            if (timerTextEffectView9 != null) {
                timerTextEffectView9.requestLayout();
            }
            super.updateWidth(dimensionPixelSize);
        }
    }
}
