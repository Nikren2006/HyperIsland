package miui.systemui.notification.focus.moduleV3;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.Q;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.K;
import java.io.IOException;
import java.net.URISyntaxException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusIconCache;
import miui.systemui.notification.focus.model.ActionInfo;
import miui.systemui.notification.focus.model.BgInfo;
import miui.systemui.notification.focus.model.HighlightInfoV3;
import miui.systemui.notification.focus.model.HintInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.model.TextAndColorInfo;
import miui.systemui.notification.focus.model.TimerInfo;
import miui.systemui.notification.focus.moduleV3.ModuleViewHolder;
import miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt;
import miui.systemui.notification.focus.templateV3.TemplateFactoryV3;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ContextUtils;
import miui.systemui.widget.StatusProgressLayout;
import miuix.colorful.texteffect.CharChangeProcessor;
import miuix.colorful.texteffect.HyperChronometer;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes4.dex */
public class ModuleViewHolder implements AutoDensityController.OnDensityChangeListener {
    private static final long INTERNAL = 100;
    public static final String TAG = "ModuleViewHolder";
    public static final int TYPE_HIDE_TIMER = 0;
    public static final int TYPE_NEGATIVE_PAUSE = -2;
    public static final int TYPE_NEGATIVE_RUN = -1;
    public static final int TYPE_POSITIVE_PAUSE = 2;
    public static final int TYPE_POSITIVE_RUN = 1;
    private static long lastClickTime;
    private Bundle actionBundle;
    private ActionInfo actionInfo;
    private Bundle bitmapBundle;
    private Integer colorContentBg;
    private final InterfaceC0418f compressChanged;
    private String content;
    private Integer contentColor;
    private Integer contentColorDark;
    private final Context ctx;
    private final j1.u dataChanged;
    private String extraTitle;
    private Integer extraTitleColor;
    private Integer extraTitleColorDark;
    private String highLightText;
    private Integer highLightTextColor;
    private Integer highLightTextColorDark;
    private Integer highLightbgColor;
    private Integer highLightbgColorDark;
    private boolean ignoreTextColor;
    private final boolean isDark;
    private int lastWidth;
    private String module;
    private View.OnAttachStateChangeListener onAttachStateChangeListener;
    private ViewTreeObserver.OnPreDrawListener onPreDrawListener;
    private Integer primaryColor;
    private Integer primaryColorDark;
    private String primaryText;
    private final ViewGroup rootView;
    private StatusBarNotification sbn;
    private E scope;
    private Integer secondaryColor;
    private Integer secondaryColorDark;
    private String secondaryText;
    private Boolean showSecondaryLine;
    private String specialTitle;
    private Integer specialTitleColor;
    private Integer specialTitleColorDark;
    private String subContent;
    private Integer subContentColor;
    private Integer subContentColorDark;
    private Integer subTitleColor;
    private Integer subTitleColorDark;
    private String subtitle;
    private final Context sysuiCtx;
    private Template template;
    private Long timerCurrent;
    private boolean timerInitialized;
    private Long timerSystemCurrent;
    private Long timerTotal;
    private Integer timerType;
    private Long timerWhen;
    private String title;
    private Integer titleColor;
    private Integer titleColorDark;
    private View view;
    private final j1.u widthChanged;
    public static final Companion Companion = new Companion(null);
    private static String lastClickNotif = "";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isContinuousClick(String str) {
            if (str == null) {
                return true;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j2 = jCurrentTimeMillis - ModuleViewHolder.lastClickTime;
            if (0 <= j2 && j2 < ModuleViewHolder.INTERNAL && TextUtils.equals(ModuleViewHolder.lastClickNotif, str)) {
                return true;
            }
            ModuleViewHolder.lastClickNotif = str;
            ModuleViewHolder.lastClickTime = jCurrentTimeMillis;
            return false;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleViewHolder$listenWidthChanged$1, reason: invalid class name */
    public static final class AnonymousClass1 implements View.OnAttachStateChangeListener {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean onViewAttachedToWindow$lambda$0(ModuleViewHolder this$0) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            View view = this$0.getView();
            if (view != null && this$0.getLastWidth() == view.getMeasuredWidth()) {
                this$0.getWidthChanged().setValue(Boolean.FALSE);
                return true;
            }
            this$0.getWidthChanged().setValue(Boolean.TRUE);
            View view2 = this$0.getView();
            this$0.setLastWidth(view2 != null ? view2.getMeasuredWidth() : 0);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View p02) {
            ViewTreeObserver viewTreeObserver;
            ViewTreeObserver viewTreeObserver2;
            kotlin.jvm.internal.n.g(p02, "p0");
            View view = ModuleViewHolder.this.getView();
            if (view != null && (viewTreeObserver2 = view.getViewTreeObserver()) != null) {
                viewTreeObserver2.removeOnPreDrawListener(ModuleViewHolder.this.onPreDrawListener);
            }
            final ModuleViewHolder moduleViewHolder = ModuleViewHolder.this;
            moduleViewHolder.onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: miui.systemui.notification.focus.moduleV3.C
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public final boolean onPreDraw() {
                    return ModuleViewHolder.AnonymousClass1.onViewAttachedToWindow$lambda$0(moduleViewHolder);
                }
            };
            View view2 = ModuleViewHolder.this.getView();
            if (view2 == null || (viewTreeObserver = view2.getViewTreeObserver()) == null) {
                return;
            }
            viewTreeObserver.addOnPreDrawListener(ModuleViewHolder.this.onPreDrawListener);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View p02) {
            ViewTreeObserver viewTreeObserver;
            kotlin.jvm.internal.n.g(p02, "p0");
            View view = ModuleViewHolder.this.getView();
            if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null) {
                return;
            }
            viewTreeObserver.removeOnPreDrawListener(ModuleViewHolder.this.onPreDrawListener);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleViewHolder$registerCompressChanged$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleViewHolder$registerCompressChanged$1", f = "ModuleViewHolder.kt", l = {162}, m = "invokeSuspend")
    public static final class C06801 extends N0.l implements Function2 {
        final /* synthetic */ Function0 $action;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06801(Function0 function0, L0.d dVar) {
            super(2, dVar);
            this.$action = function0;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleViewHolder.this.new C06801(this.$action, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06801) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                InterfaceC0418f compressChanged = ModuleViewHolder.this.getCompressChanged();
                final Function0 function0 = this.$action;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.notification.focus.moduleV3.ModuleViewHolder.registerCompressChanged.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        function0.invoke();
                        return H0.s.f314a;
                    }
                };
                this.label = 1;
                if (compressChanged.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return H0.s.f314a;
        }
    }

    public ModuleViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.ctx = ctx;
        this.sysuiCtx = sysuiCtx;
        this.rootView = rootView;
        this.isDark = z2;
        Boolean bool = Boolean.FALSE;
        this.showSecondaryLine = bool;
        this.timerType = 0;
        this.timerWhen = Long.valueOf(System.currentTimeMillis());
        this.timerTotal = 0L;
        this.timerSystemCurrent = Long.valueOf(System.currentTimeMillis());
        this.scope = F.a(Q.c().z());
        j1.u uVarA = K.a(bool);
        this.dataChanged = uVarA;
        j1.u uVarA2 = K.a(bool);
        this.widthChanged = uVarA2;
        this.compressChanged = AbstractC0420h.l(uVarA, uVarA2, new ModuleViewHolder$compressChanged$1(null));
    }

    private final Notification.Action buildAction(Template template, StatusBarNotification statusBarNotification, boolean z2) throws URISyntaxException {
        String actionIntent;
        Icon icon;
        Integer actionIntentType;
        ActionInfo actionInfo;
        Bundle extras;
        ActionInfo actionInfo2;
        HintInfo hintInfo = template.getHintInfo();
        Notification.Action action = null;
        action = null;
        String action2 = (hintInfo == null || (actionInfo2 = hintInfo.getActionInfo()) == null) ? null : actionInfo2.getAction();
        if (action2 != null) {
            Bundle bundle = this.actionBundle;
            if (bundle != null) {
                action = (Notification.Action) bundle.getParcelable(action2);
            }
        } else {
            HintInfo hintInfo2 = template.getHintInfo();
            if (((hintInfo2 == null || (actionInfo = hintInfo2.getActionInfo()) == null) ? null : actionInfo.getActionTitle()) != null) {
                ActionInfo actionInfo3 = template.getHintInfo().getActionInfo();
                int iIntValue = (actionInfo3 == null || (actionIntentType = actionInfo3.getActionIntentType()) == null) ? 1 : actionIntentType.intValue();
                ActionInfo actionInfo4 = template.getHintInfo().getActionInfo();
                if (actionInfo4 == null || (actionIntent = actionInfo4.getActionIntent()) == null) {
                    actionIntent = "";
                }
                Intent uri = Intent.parseUri(actionIntent, 1);
                PendingIntent service = iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? null : PendingIntent.getService(this.sysuiCtx, (int) statusBarNotification.getPostTime(), uri, 201326592) : PendingIntent.getBroadcast(this.sysuiCtx, (int) statusBarNotification.getPostTime(), uri, 201326592) : PendingIntent.getActivity(this.sysuiCtx, (int) statusBarNotification.getPostTime(), uri, 201326592);
                if (z2) {
                    ActionInfo actionInfo5 = template.getHintInfo().getActionInfo();
                    icon = getIcon(actionInfo5 != null ? actionInfo5.getActionIconDark() : null, statusBarNotification);
                    if (icon == null) {
                        ActionInfo actionInfo6 = template.getHintInfo().getActionInfo();
                        icon = getIcon(actionInfo6 != null ? actionInfo6.getActionIcon() : null, statusBarNotification);
                    }
                } else {
                    ActionInfo actionInfo7 = template.getHintInfo().getActionInfo();
                    icon = getIcon(actionInfo7 != null ? actionInfo7.getActionIcon() : null, statusBarNotification);
                }
                ActionInfo actionInfo8 = template.getHintInfo().getActionInfo();
                Notification.Action actionBuild = new Notification.Action.Builder(icon, actionInfo8 != null ? actionInfo8.getActionTitle() : null, service).build();
                try {
                    ActionInfo actionInfo9 = template.getHintInfo().getActionInfo();
                    actionBuild.title = actionInfo9 != null ? actionInfo9.getActionTitle() : null;
                    Bundle extras2 = actionBuild.getExtras();
                    if (extras2 != null) {
                        ActionInfo actionInfo10 = template.getHintInfo().getActionInfo();
                        extras2.putString(Const.Param.COLOR_TITLE, actionInfo10 != null ? actionInfo10.getActionTitleColor() : null);
                    }
                    Bundle extras3 = actionBuild.getExtras();
                    if (extras3 != null) {
                        ActionInfo actionInfo11 = template.getHintInfo().getActionInfo();
                        extras3.putString(Const.Param.COLOR_BG, actionInfo11 != null ? actionInfo11.getActionBgColor() : null);
                    }
                    Bundle extras4 = actionBuild.getExtras();
                    if (extras4 != null) {
                        ActionInfo actionInfo12 = template.getHintInfo().getActionInfo();
                        extras4.putString(Const.Param.COLOR_TITLE_DARK, actionInfo12 != null ? actionInfo12.getActionTitleColorDark() : null);
                    }
                    Bundle extras5 = actionBuild.getExtras();
                    if (extras5 != null) {
                        ActionInfo actionInfo13 = template.getHintInfo().getActionInfo();
                        extras5.putString(Const.Param.COLOR_BG_DARK, actionInfo13 != null ? actionInfo13.getActionBgColorDark() : null);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                action = actionBuild;
            }
        }
        ActionInfo actionInfo14 = this.actionInfo;
        if (actionInfo14 != null) {
            boolean clickWithCollapse = actionInfo14.getClickWithCollapse();
            if (action != null && (extras = action.getExtras()) != null) {
                extras.putBoolean("click_with_collapse", clickWithCollapse);
            }
        }
        return action;
    }

    public static /* synthetic */ Notification.Action buildAction$default(ModuleViewHolder moduleViewHolder, Template template, StatusBarNotification statusBarNotification, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: buildAction");
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return moduleViewHolder.buildAction(template, statusBarNotification, z2);
    }

    private final void calculateTimer() {
        Long lValueOf;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l2 = this.timerSystemCurrent;
        kotlin.jvm.internal.n.d(l2);
        if (jCurrentTimeMillis - l2.longValue() > 1000) {
            Log.d(TAG, "Timer delay too large, current:" + jCurrentTimeMillis + " timerSystemCurrent:" + this.timerSystemCurrent);
            this.timerSystemCurrent = Long.valueOf(jCurrentTimeMillis);
            Integer num = this.timerType;
            kotlin.jvm.internal.n.d(num);
            if (num.intValue() > 0) {
                Long l3 = this.timerSystemCurrent;
                kotlin.jvm.internal.n.d(l3);
                long jLongValue = l3.longValue();
                Long l4 = this.timerWhen;
                kotlin.jvm.internal.n.d(l4);
                lValueOf = Long.valueOf(jLongValue - l4.longValue());
            } else {
                Long l5 = this.timerWhen;
                kotlin.jvm.internal.n.d(l5);
                long jLongValue2 = l5.longValue();
                Long l6 = this.timerSystemCurrent;
                kotlin.jvm.internal.n.d(l6);
                lValueOf = Long.valueOf(jLongValue2 - l6.longValue());
            }
            this.timerCurrent = lValueOf;
            Log.d(TAG, "normalRemoteViewsBuild: calculateTimer :timerCurrent:" + lValueOf);
        }
    }

    public static /* synthetic */ Context getContext$default(ModuleViewHolder moduleViewHolder, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContext");
        }
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        return moduleViewHolder.getContext(z2);
    }

    public static /* synthetic */ void handleBtnClick$default(ModuleViewHolder moduleViewHolder, PendingIntent pendingIntent, Notification.Action action, Context context, View view, boolean z2, StatusBarNotification statusBarNotification, boolean z3, int i2, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleBtnClick");
        }
        moduleViewHolder.handleBtnClick(pendingIntent, action, context, view, z2, statusBarNotification, (i2 & 64) != 0 ? true : z3);
    }

    private final void listenWidthChanged() {
        View view = this.view;
        if (view != null) {
            view.removeOnAttachStateChangeListener(this.onAttachStateChangeListener);
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.onAttachStateChangeListener = anonymousClass1;
        View view2 = this.view;
        if (view2 != null) {
            view2.addOnAttachStateChangeListener(anonymousClass1);
        }
    }

    private final Integer parseColorFromExtras(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            String string = bundle.getString(str);
            if (string != null) {
                return Integer.valueOf(Color.parseColor(string));
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAction$lambda$15(Notification.Action action, ModuleViewHolder this$0, boolean z2, StatusBarNotification sbn, View view) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            try {
                View view2 = this$0.view;
                if (view2 != null) {
                    ModuleViewHolderExtKt.sendWithCollapse(pendingIntent, action, this$0.sysuiCtx, view2, z2, sbn);
                }
            } catch (PendingIntent.CanceledException e2) {
                Log.e(TAG, "PendingIntent.send() failed", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAction$lambda$18(Notification.Action action, ModuleViewHolder this$0, boolean z2, StatusBarNotification sbn, View view) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            try {
                View view2 = this$0.view;
                if (view2 != null) {
                    ModuleViewHolderExtKt.sendWithCollapse(pendingIntent, action, this$0.sysuiCtx, view2, z2, sbn);
                }
            } catch (PendingIntent.CanceledException e2) {
                Log.e(TAG, "PendingIntent.send() failed", e2);
            }
        }
    }

    public static /* synthetic */ void setActionData$default(ModuleViewHolder moduleViewHolder, Template template, StatusBarNotification statusBarNotification, boolean z2, int i2, Object obj) throws URISyntaxException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setActionData");
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        moduleViewHolder.setActionData(template, statusBarNotification, z2);
    }

    private final void setButtonContainerBackground(Notification.Action action, boolean z2) {
        Integer numValueOf;
        int color;
        try {
            if (z2) {
                Bundle extras = action.getExtras();
                color = Color.parseColor(extras != null ? extras.getString(Const.Param.COLOR_BG_DARK) : null);
            } else {
                Bundle extras2 = action.getExtras();
                color = Color.parseColor(extras2 != null ? extras2.getString(Const.Param.COLOR_BG) : null);
            }
            numValueOf = Integer.valueOf(color);
        } catch (Exception unused) {
            numValueOf = null;
        }
        View view = this.view;
        FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(R.id.focus_button_container) : null;
        if (numValueOf != null) {
            if (frameLayout != null) {
                frameLayout.setBackgroundResource(R.drawable.focus_button_background_no_alpha);
            }
            if (frameLayout == null) {
                return;
            }
            frameLayout.setBackgroundTintList(ColorStateList.valueOf(numValueOf.intValue()));
        }
    }

    public static /* synthetic */ void setButtonContainerBackground$default(ModuleViewHolder moduleViewHolder, Notification.Action action, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setButtonContainerBackground");
        }
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        moduleViewHolder.setButtonContainerBackground(action, z2);
    }

    public static /* synthetic */ void setTimerData$default(ModuleViewHolder moduleViewHolder, int i2, StatusBarNotification statusBarNotification, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setTimerData");
        }
        if ((i3 & 1) != 0) {
            i2 = R.id.chronometer;
        }
        moduleViewHolder.setTimerData(i2, statusBarNotification);
    }

    private final void updateBundle(StatusBarNotification statusBarNotification) {
        this.actionBundle = statusBarNotification.getNotification().extras.getBundle(Const.Param.PARAM_ACTION_BUNDLE);
        this.bitmapBundle = statusBarNotification.getNotification().extras.getBundle("miui.focus.pics");
    }

    private final void updateButtonView(Notification.Action action, boolean z2, StatusBarNotification statusBarNotification, Template template) {
        Icon icon;
        ActionInfo actionInfo;
        ViewGroup.LayoutParams layoutParams;
        ActionInfo actionInfo2;
        if (z2) {
            HintInfo hintInfo = template.getHintInfo();
            icon = getIcon((hintInfo == null || (actionInfo2 = hintInfo.getActionInfo()) == null) ? null : actionInfo2.getActionIconDark(), statusBarNotification);
            if (icon == null) {
                icon = action.getIcon();
            }
        } else {
            HintInfo hintInfo2 = template.getHintInfo();
            icon = getIcon((hintInfo2 == null || (actionInfo = hintInfo2.getActionInfo()) == null) ? null : actionInfo.getActionIcon(), statusBarNotification);
            if (icon == null) {
                icon = action.getIcon();
            }
        }
        View view = this.view;
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.button_icon) : null;
        View view2 = this.view;
        TextView textView = view2 != null ? (TextView) view2.findViewById(R.id.focus_button_title) : null;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams2 = textView != null ? textView.getLayoutParams() : null;
            if (icon != null) {
                imageView.setVisibility(0);
                imageView.setImageIcon(icon);
                ViewGroup.LayoutParams layoutParams3 = layoutParams2;
                if ((textView != null ? textView.getLayoutParams() : null) instanceof LinearLayout.LayoutParams) {
                    ViewGroup.LayoutParams layoutParams4 = textView.getLayoutParams();
                    kotlin.jvm.internal.n.e(layoutParams4, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                    LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) layoutParams4;
                    layoutParams5.setMarginStart((int) this.ctx.getResources().getDimension(R.dimen.focus_notify_button_with_icon_margin_start));
                    layoutParams3 = layoutParams5;
                }
                layoutParams = layoutParams3;
                if ((textView != null ? textView.getLayoutParams() : null) instanceof FrameLayout.LayoutParams) {
                    ViewGroup.LayoutParams layoutParams6 = textView.getLayoutParams();
                    kotlin.jvm.internal.n.e(layoutParams6, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                    FrameLayout.LayoutParams layoutParams7 = (FrameLayout.LayoutParams) layoutParams6;
                    layoutParams7.setMarginStart((int) this.ctx.getResources().getDimension(R.dimen.focus_notify_button_with_icon_margin_start));
                    layoutParams = layoutParams7;
                }
            } else {
                imageView.setVisibility(8);
                ViewGroup.LayoutParams layoutParams8 = layoutParams2;
                if ((textView != null ? textView.getLayoutParams() : null) instanceof LinearLayout.LayoutParams) {
                    ViewGroup.LayoutParams layoutParams9 = textView.getLayoutParams();
                    kotlin.jvm.internal.n.e(layoutParams9, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                    LinearLayout.LayoutParams layoutParams10 = (LinearLayout.LayoutParams) layoutParams9;
                    layoutParams10.setMarginStart((int) this.ctx.getResources().getDimension(R.dimen.focus_notify_button_without_icon_margin_start));
                    layoutParams8 = layoutParams10;
                }
                layoutParams = layoutParams8;
                if ((textView != null ? textView.getLayoutParams() : null) instanceof FrameLayout.LayoutParams) {
                    ViewGroup.LayoutParams layoutParams11 = textView.getLayoutParams();
                    kotlin.jvm.internal.n.e(layoutParams11, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                    FrameLayout.LayoutParams layoutParams12 = (FrameLayout.LayoutParams) layoutParams11;
                    layoutParams12.setMarginStart((int) this.ctx.getResources().getDimension(R.dimen.focus_notify_button_without_icon_margin_start));
                    layoutParams = layoutParams12;
                }
            }
            if (textView != null) {
                textView.setLayoutParams(layoutParams);
            }
        }
        setButtonContainerBackground(action, z2);
    }

    public static /* synthetic */ void updateButtonView$default(ModuleViewHolder moduleViewHolder, Notification.Action action, boolean z2, StatusBarNotification statusBarNotification, Template template, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateButtonView");
        }
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        moduleViewHolder.updateButtonView(action, z2, statusBarNotification, template);
    }

    public final void adaptTimerDelay() {
        Bundle bundle = this.actionBundle;
        Notification.Action action = bundle != null ? (Notification.Action) bundle.getParcelable(Const.Param.ACTION_VId_1) : null;
        if (action != null) {
            String string = action.getExtras().getString("icon_name");
            if (kotlin.jvm.internal.n.c(string, Const.ACTIONS.ACTION_PAUSE) ? true : kotlin.jvm.internal.n.c(string, Const.ACTIONS.ACTION_PAUSE_TIMER)) {
                calculateTimer();
            }
        }
    }

    public void bind(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        this.template = template;
        this.sbn = sbn;
        updateBundle(sbn);
        this.ignoreTextColor = TemplateFactoryV3.Companion.isBgPicDownloadFailStatic(template, sbn);
        this.dataChanged.setValue(Boolean.FALSE);
        listenWidthChanged();
    }

    public void checkParams(Template template) {
        kotlin.jvm.internal.n.g(template, "template");
    }

    public void diff(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
    }

    public final Bundle getActionBundle() {
        return this.actionBundle;
    }

    public final ActionInfo getActionInfo() {
        return this.actionInfo;
    }

    public final Bundle getBitmapBundle() {
        return this.bitmapBundle;
    }

    public final Integer getColorContentBg() {
        return this.colorContentBg;
    }

    public final InterfaceC0418f getCompressChanged() {
        return this.compressChanged;
    }

    public final String getContent() {
        return this.content;
    }

    public final Integer getContentColor() {
        return this.contentColor;
    }

    public final Integer getContentColorDark() {
        return this.contentColorDark;
    }

    public final Context getContext(boolean z2) {
        return z2 ? ContextUtils.INSTANCE.getNightContext(this.ctx, true) : this.isDark ? ContextUtils.getNightContext$default(ContextUtils.INSTANCE, this.ctx, false, 2, null) : ContextUtils.INSTANCE.getDayContext(this.ctx);
    }

    public final Context getCtx() {
        return this.ctx;
    }

    public final j1.u getDataChanged() {
        return this.dataChanged;
    }

    public final String getExtraTitle() {
        return this.extraTitle;
    }

    public final Integer getExtraTitleColor() {
        return this.extraTitleColor;
    }

    public final Integer getExtraTitleColorDark() {
        return this.extraTitleColorDark;
    }

    public final String getHighLightText() {
        return this.highLightText;
    }

    public final Integer getHighLightTextColor() {
        return this.highLightTextColor;
    }

    public final Integer getHighLightTextColorDark() {
        return this.highLightTextColorDark;
    }

    public final Integer getHighLightbgColor() {
        return this.highLightbgColor;
    }

    public final Integer getHighLightbgColorDark() {
        return this.highLightbgColorDark;
    }

    public final Icon getIcon(String str, StatusBarNotification sbn) {
        Icon icon;
        kotlin.jvm.internal.n.g(sbn, "sbn");
        Bundle bundle = this.bitmapBundle;
        if (bundle != null && (icon = (Icon) bundle.getParcelable(str)) != null) {
            return icon;
        }
        FocusIconCache focusIconCache = FocusIconCache.INSTANCE;
        String key = sbn.getKey();
        kotlin.jvm.internal.n.f(key, "getKey(...)");
        return focusIconCache.get(key, str);
    }

    public final boolean getIgnoreTextColor() {
        return this.ignoreTextColor;
    }

    public final int getLastWidth() {
        return this.lastWidth;
    }

    public final String getModule() {
        return this.module;
    }

    public final Integer getPrimaryColor() {
        return this.primaryColor;
    }

    public final Integer getPrimaryColorDark() {
        return this.primaryColorDark;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final ViewGroup getRootView() {
        return this.rootView;
    }

    public final StatusBarNotification getSbn() {
        return this.sbn;
    }

    public final E getScope() {
        return this.scope;
    }

    public final Integer getSecondaryColor() {
        return this.secondaryColor;
    }

    public final Integer getSecondaryColorDark() {
        return this.secondaryColorDark;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }

    public final Boolean getShowSecondaryLine() {
        return this.showSecondaryLine;
    }

    public final String getSpecialTitle() {
        return this.specialTitle;
    }

    public final Integer getSpecialTitleColor() {
        return this.specialTitleColor;
    }

    public final Integer getSpecialTitleColorDark() {
        return this.specialTitleColorDark;
    }

    public final String getSubContent() {
        return this.subContent;
    }

    public final Integer getSubContentColor() {
        return this.subContentColor;
    }

    public final Integer getSubContentColorDark() {
        return this.subContentColorDark;
    }

    public final Integer getSubTitleColor() {
        return this.subTitleColor;
    }

    public final Integer getSubTitleColorDark() {
        return this.subTitleColorDark;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final Context getSysuiCtx() {
        return this.sysuiCtx;
    }

    public final int getTextViewWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        Log.e(TAG, "getTextViewWidth " + textView + " " + ((int) textView.getPaint().measureText(textView.getText().toString())) + " " + Integer.valueOf(textView.getWidth()));
        return (int) textView.getPaint().measureText(textView.getText().toString());
    }

    public final Long getTimerCurrent() {
        return this.timerCurrent;
    }

    public final boolean getTimerInitialized() {
        return this.timerInitialized;
    }

    public final Long getTimerSystemCurrent() {
        return this.timerSystemCurrent;
    }

    public final Long getTimerTotal() {
        return this.timerTotal;
    }

    public final Integer getTimerType() {
        return this.timerType;
    }

    public final Long getTimerWhen() {
        return this.timerWhen;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getTitleColor() {
        return this.titleColor;
    }

    public final Integer getTitleColorDark() {
        return this.titleColorDark;
    }

    public final View getView() {
        return this.view;
    }

    public final j1.u getWidthChanged() {
        return this.widthChanged;
    }

    public final void handleBtnClick(PendingIntent intent, Notification.Action action, Context context, View view, boolean z2, StatusBarNotification sbn, boolean z3) throws IOException {
        kotlin.jvm.internal.n.g(intent, "intent");
        kotlin.jvm.internal.n.g(action, "action");
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        try {
            if (!z3) {
                intent.send();
            } else if (view != null) {
                ModuleViewHolderExtKt.sendWithCollapse(intent, action, context, view, z2, sbn);
            }
        } catch (PendingIntent.CanceledException e2) {
            Log.e(TAG, "PendingIntent.send() failed", e2);
        }
    }

    public final boolean hasCustomBackground(Template template, StatusBarNotification sbn) {
        Integer numValueOf;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        try {
            BgInfo bgInfo = template.getBgInfo();
            numValueOf = Integer.valueOf(Color.parseColor(bgInfo != null ? bgInfo.getColorBg() : null));
        } catch (Exception unused) {
            numValueOf = null;
        }
        BgInfo bgInfo2 = template.getBgInfo();
        Icon icon = getIcon(bgInfo2 != null ? bgInfo2.getPicBg() : null, sbn);
        Bundle bundle = this.bitmapBundle;
        if (bundle != null) {
            BgInfo bgInfo3 = template.getBgInfo();
            if (bundle.containsKey(bgInfo3 != null ? bgInfo3.getPicBg() : null) && icon != null) {
                return true;
            }
        }
        return numValueOf != null;
    }

    public final void initTextAndColor(TextAndColorInfo textAndColorInfo) {
        String colorTitle;
        Integer numValueOf;
        String colorTitleDark;
        Integer numValueOf2;
        String colorSubTitle;
        Integer numValueOf3;
        String colorSubTitleDark;
        Integer numValueOf4;
        String colorExtraTitle;
        Integer numValueOf5;
        String colorExtraTitleDark;
        Integer numValueOf6;
        String colorSpecialTitle;
        Integer numValueOf7;
        String colorSpecialTitleDark;
        Integer numValueOf8;
        String colorContent;
        Integer numValueOf9;
        String colorContentDark;
        Integer numValueOf10;
        String colorSubContent;
        Integer numValueOf11;
        String colorSubContentDark;
        Integer numValueOf12;
        String primaryColor;
        Integer numValueOf13;
        String primaryColorDark;
        Integer numValueOf14;
        String secondaryColor;
        Integer numValueOf15;
        String secondaryColorDark;
        Integer numValueOf16;
        String highLightTextColor;
        Integer numValueOf17;
        String highLightTextColorDark;
        Integer numValueOf18;
        String highLightbgColor;
        Integer numValueOf19;
        Integer numValueOf20;
        String highLightbgColorDark = null;
        HighlightInfoV3 highlightInfoV3 = textAndColorInfo instanceof HighlightInfoV3 ? (HighlightInfoV3) textAndColorInfo : null;
        this.title = textAndColorInfo != null ? textAndColorInfo.getTitle() : null;
        this.subtitle = textAndColorInfo != null ? textAndColorInfo.getSubTitle() : null;
        this.extraTitle = textAndColorInfo != null ? textAndColorInfo.getExtraTitle() : null;
        this.specialTitle = textAndColorInfo != null ? textAndColorInfo.getSpecialTitle() : null;
        this.content = textAndColorInfo != null ? textAndColorInfo.getContent() : null;
        this.subContent = textAndColorInfo != null ? textAndColorInfo.getSubContent() : null;
        this.primaryText = highlightInfoV3 != null ? highlightInfoV3.getPrimaryText() : null;
        this.secondaryText = highlightInfoV3 != null ? highlightInfoV3.getSecondaryText() : null;
        this.showSecondaryLine = highlightInfoV3 != null ? highlightInfoV3.getShowSecondaryLine() : null;
        this.highLightText = highlightInfoV3 != null ? highlightInfoV3.getHighLightText() : null;
        if (this.ignoreTextColor) {
            return;
        }
        if (textAndColorInfo != null) {
            try {
                colorTitle = textAndColorInfo.getColorTitle();
            } catch (Exception unused) {
                numValueOf = null;
            }
        } else {
            colorTitle = null;
        }
        numValueOf = Integer.valueOf(Color.parseColor(colorTitle));
        this.titleColor = numValueOf;
        if (textAndColorInfo != null) {
            try {
                colorTitleDark = textAndColorInfo.getColorTitleDark();
            } catch (Exception unused2) {
                numValueOf2 = this.titleColor;
            }
        } else {
            colorTitleDark = null;
        }
        numValueOf2 = Integer.valueOf(Color.parseColor(colorTitleDark));
        this.titleColorDark = numValueOf2;
        if (textAndColorInfo != null) {
            try {
                colorSubTitle = textAndColorInfo.getColorSubTitle();
            } catch (Exception unused3) {
                numValueOf3 = null;
            }
        } else {
            colorSubTitle = null;
        }
        numValueOf3 = Integer.valueOf(Color.parseColor(colorSubTitle));
        this.subTitleColor = numValueOf3;
        if (textAndColorInfo != null) {
            try {
                colorSubTitleDark = textAndColorInfo.getColorSubTitleDark();
            } catch (Exception unused4) {
                numValueOf4 = this.subTitleColor;
            }
        } else {
            colorSubTitleDark = null;
        }
        numValueOf4 = Integer.valueOf(Color.parseColor(colorSubTitleDark));
        this.subTitleColorDark = numValueOf4;
        if (textAndColorInfo != null) {
            try {
                colorExtraTitle = textAndColorInfo.getColorExtraTitle();
            } catch (Exception unused5) {
                numValueOf5 = null;
            }
        } else {
            colorExtraTitle = null;
        }
        numValueOf5 = Integer.valueOf(Color.parseColor(colorExtraTitle));
        this.extraTitleColor = numValueOf5;
        if (textAndColorInfo != null) {
            try {
                colorExtraTitleDark = textAndColorInfo.getColorExtraTitleDark();
            } catch (Exception unused6) {
                numValueOf6 = this.extraTitleColor;
            }
        } else {
            colorExtraTitleDark = null;
        }
        numValueOf6 = Integer.valueOf(Color.parseColor(colorExtraTitleDark));
        this.extraTitleColorDark = numValueOf6;
        if (textAndColorInfo != null) {
            try {
                colorSpecialTitle = textAndColorInfo.getColorSpecialTitle();
            } catch (Exception unused7) {
                numValueOf7 = null;
            }
        } else {
            colorSpecialTitle = null;
        }
        numValueOf7 = Integer.valueOf(Color.parseColor(colorSpecialTitle));
        this.specialTitleColor = numValueOf7;
        if (textAndColorInfo != null) {
            try {
                colorSpecialTitleDark = textAndColorInfo.getColorSpecialTitleDark();
            } catch (Exception unused8) {
                numValueOf8 = this.specialTitleColor;
            }
        } else {
            colorSpecialTitleDark = null;
        }
        numValueOf8 = Integer.valueOf(Color.parseColor(colorSpecialTitleDark));
        this.specialTitleColorDark = numValueOf8;
        if (textAndColorInfo != null) {
            try {
                colorContent = textAndColorInfo.getColorContent();
            } catch (Exception unused9) {
                numValueOf9 = null;
            }
        } else {
            colorContent = null;
        }
        numValueOf9 = Integer.valueOf(Color.parseColor(colorContent));
        this.contentColor = numValueOf9;
        if (textAndColorInfo != null) {
            try {
                colorContentDark = textAndColorInfo.getColorContentDark();
            } catch (Exception unused10) {
                numValueOf10 = this.contentColor;
            }
        } else {
            colorContentDark = null;
        }
        numValueOf10 = Integer.valueOf(Color.parseColor(colorContentDark));
        this.contentColorDark = numValueOf10;
        if (textAndColorInfo != null) {
            try {
                colorSubContent = textAndColorInfo.getColorSubContent();
            } catch (Exception unused11) {
                numValueOf11 = null;
            }
        } else {
            colorSubContent = null;
        }
        numValueOf11 = Integer.valueOf(Color.parseColor(colorSubContent));
        this.subContentColor = numValueOf11;
        if (textAndColorInfo != null) {
            try {
                colorSubContentDark = textAndColorInfo.getColorSubContentDark();
            } catch (Exception unused12) {
                numValueOf12 = this.subContentColor;
            }
        } else {
            colorSubContentDark = null;
        }
        numValueOf12 = Integer.valueOf(Color.parseColor(colorSubContentDark));
        this.subContentColorDark = numValueOf12;
        if (highlightInfoV3 != null) {
            try {
                primaryColor = highlightInfoV3.getPrimaryColor();
            } catch (Exception unused13) {
                numValueOf13 = null;
            }
        } else {
            primaryColor = null;
        }
        numValueOf13 = Integer.valueOf(Color.parseColor(primaryColor));
        this.primaryColor = numValueOf13;
        if (highlightInfoV3 != null) {
            try {
                primaryColorDark = highlightInfoV3.getPrimaryColorDark();
            } catch (Exception unused14) {
                numValueOf14 = this.primaryColor;
            }
        } else {
            primaryColorDark = null;
        }
        numValueOf14 = Integer.valueOf(Color.parseColor(primaryColorDark));
        this.primaryColorDark = numValueOf14;
        if (highlightInfoV3 != null) {
            try {
                secondaryColor = highlightInfoV3.getSecondaryColor();
            } catch (Exception unused15) {
                numValueOf15 = null;
            }
        } else {
            secondaryColor = null;
        }
        numValueOf15 = Integer.valueOf(Color.parseColor(secondaryColor));
        this.secondaryColor = numValueOf15;
        if (highlightInfoV3 != null) {
            try {
                secondaryColorDark = highlightInfoV3.getSecondaryColorDark();
            } catch (Exception unused16) {
                numValueOf16 = this.secondaryColor;
            }
        } else {
            secondaryColorDark = null;
        }
        numValueOf16 = Integer.valueOf(Color.parseColor(secondaryColorDark));
        this.secondaryColorDark = numValueOf16;
        if (highlightInfoV3 != null) {
            try {
                highLightTextColor = highlightInfoV3.getHighLightTextColor();
            } catch (Exception unused17) {
                numValueOf17 = null;
            }
        } else {
            highLightTextColor = null;
        }
        numValueOf17 = Integer.valueOf(Color.parseColor(highLightTextColor));
        this.highLightTextColor = numValueOf17;
        if (highlightInfoV3 != null) {
            try {
                highLightTextColorDark = highlightInfoV3.getHighLightTextColorDark();
            } catch (Exception unused18) {
                numValueOf18 = this.highLightTextColor;
            }
        } else {
            highLightTextColorDark = null;
        }
        numValueOf18 = Integer.valueOf(Color.parseColor(highLightTextColorDark));
        this.highLightTextColorDark = numValueOf18;
        if (highlightInfoV3 != null) {
            try {
                highLightbgColor = highlightInfoV3.getHighLightbgColor();
            } catch (Exception unused19) {
                numValueOf19 = null;
            }
        } else {
            highLightbgColor = null;
        }
        numValueOf19 = Integer.valueOf(Color.parseColor(highLightbgColor));
        this.highLightbgColor = numValueOf19;
        if (highlightInfoV3 != null) {
            try {
                highLightbgColorDark = highlightInfoV3.getHighLightbgColorDark();
            } catch (Exception unused20) {
                numValueOf20 = this.highLightbgColor;
            }
        }
        numValueOf20 = Integer.valueOf(Color.parseColor(highLightbgColorDark));
        this.highLightbgColorDark = numValueOf20;
        boolean z2 = this.isDark;
        this.titleColor = z2 ? this.titleColorDark : this.titleColor;
        this.subTitleColor = z2 ? this.subTitleColorDark : this.subTitleColor;
        this.extraTitleColor = z2 ? this.extraTitleColorDark : this.extraTitleColor;
        this.specialTitleColor = z2 ? this.specialTitleColorDark : this.specialTitleColor;
        this.contentColor = z2 ? this.contentColorDark : this.contentColor;
        this.subContentColor = z2 ? this.subContentColorDark : this.subContentColor;
        this.primaryColor = z2 ? this.primaryColorDark : this.primaryColor;
        this.secondaryColor = z2 ? this.secondaryColorDark : this.secondaryColor;
        this.highLightTextColor = z2 ? this.highLightTextColorDark : this.highLightTextColor;
        if (!z2) {
            numValueOf20 = this.highLightbgColor;
        }
        this.highLightbgColor = numValueOf20;
    }

    public final void initTimerData(TimerInfo timerInfo) {
        Long lValueOf;
        if (timerInfo != null) {
            this.timerType = Integer.valueOf(timerInfo.getTimerType());
            Long timerWhen = timerInfo.getTimerWhen();
            if (timerWhen == null) {
                timerWhen = Long.valueOf(System.currentTimeMillis());
            }
            this.timerWhen = timerWhen;
            this.timerTotal = Long.valueOf(timerInfo.getTimerTotal());
            Long timerSystemCurrent = timerInfo.getTimerSystemCurrent();
            if (timerSystemCurrent == null) {
                timerSystemCurrent = Long.valueOf(System.currentTimeMillis());
            }
            this.timerSystemCurrent = timerSystemCurrent;
            Integer num = this.timerType;
            kotlin.jvm.internal.n.d(num);
            if (num.intValue() > 0) {
                Long l2 = this.timerSystemCurrent;
                kotlin.jvm.internal.n.d(l2);
                long jLongValue = l2.longValue();
                Long l3 = this.timerWhen;
                kotlin.jvm.internal.n.d(l3);
                lValueOf = Long.valueOf(jLongValue - l3.longValue());
            } else {
                Long l4 = this.timerWhen;
                kotlin.jvm.internal.n.d(l4);
                long jLongValue2 = l4.longValue();
                Long l5 = this.timerSystemCurrent;
                kotlin.jvm.internal.n.d(l5);
                lValueOf = Long.valueOf(jLongValue2 - l5.longValue());
            }
            this.timerCurrent = lValueOf;
        }
    }

    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        AutoDensityController.Companion.addOnDensityChangeListener(this);
    }

    public final boolean isDark() {
        return this.isDark;
    }

    public final void notifyDataChanged() {
        this.dataChanged.setValue(Boolean.TRUE);
    }

    @Override // miui.systemui.autodensity.AutoDensityController.OnDensityChangeListener
    public void onConfigChanged(Configuration config) {
        StatusBarNotification statusBarNotification;
        kotlin.jvm.internal.n.g(config, "config");
        Template template = this.template;
        if (template == null || (statusBarNotification = this.sbn) == null) {
            return;
        }
        bind(template, statusBarNotification);
    }

    public void onDetach() {
        ViewTreeObserver viewTreeObserver;
        AutoDensityController.Companion.removeOnDensityChangeListener(this);
        View view = this.view;
        if (view != null) {
            view.removeOnAttachStateChangeListener(this.onAttachStateChangeListener);
        }
        View view2 = this.view;
        if (view2 != null && (viewTreeObserver = view2.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnPreDrawListener(this.onPreDrawListener);
        }
        F.e(this.scope, null, 1, null);
    }

    public final void registerCompressChanged(Function0 action) {
        kotlin.jvm.internal.n.g(action, "action");
        AbstractC0369g.b(this.scope, null, null, new C06801(action, null), 3, null);
    }

    public final void setAction(View view, final Notification.Action action, final StatusBarNotification sbn, boolean z2, final boolean z3, Template template) {
        kotlin.jvm.internal.n.g(sbn, "sbn");
        kotlin.jvm.internal.n.g(template, "template");
        View viewFindViewById = view != null ? view.findViewById(R.id.focus_button_container) : null;
        if (action == null) {
            if (z2) {
                if (viewFindViewById == null) {
                    return;
                }
                viewFindViewById.setVisibility(4);
                return;
            } else {
                if (view == null) {
                    return;
                }
                view.setVisibility(4);
                return;
            }
        }
        Integer colorFromExtras = this.isDark ? parseColorFromExtras(action.getExtras(), Const.Param.COLOR_TITLE_DARK) : parseColorFromExtras(action.getExtras(), Const.Param.COLOR_TITLE);
        TextView textView = view != null ? (TextView) view.findViewById(R.id.focus_button_title) : null;
        if (textView != null) {
            textView.setVisibility(0);
        }
        if (textView != null) {
            textView.setText(Html.fromHtml(action.title.toString()));
        }
        if ((textView instanceof TimerTextEffectView) && !TextUtils.isEmpty(action.title)) {
            TimerTextEffectView timerTextEffectView = (TimerTextEffectView) textView;
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
            timerTextEffectView.setEnableEffectWithInit(false);
            timerTextEffectView.setText(Html.fromHtml(action.title.toString()), TextView.BufferType.SPANNABLE);
            if (this.isDark) {
                Integer num = this.titleColorDark;
                if (num != null) {
                    timerTextEffectView.updateTextWithNewAppearance(Html.fromHtml(action.title.toString()), Integer.valueOf(num.intValue()));
                }
            } else if (colorFromExtras != null) {
                timerTextEffectView.updateTextWithNewAppearance(Html.fromHtml(action.title.toString()), Integer.valueOf(colorFromExtras.intValue()));
            }
        }
        if (colorFromExtras != null) {
            int iIntValue = colorFromExtras.intValue();
            if (textView != null) {
                textView.setTextColor(iIntValue);
            }
        }
        sbn.getNotification().extras.putInt(Const.Param.MODULE_BUTTON, R.id.focus_button_container);
        if (z2) {
            if (viewFindViewById != null) {
                viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.A
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) throws IOException {
                        ModuleViewHolder.setAction$lambda$15(action, this, z3, sbn, view2);
                    }
                });
            }
            if (viewFindViewById != null) {
                viewFindViewById.setVisibility(0);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setContentDescription(action.title);
            }
            updateButtonView(action, this.isDark, sbn, template);
            return;
        }
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.B
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleViewHolder.setAction$lambda$18(action, this, z3, sbn, view2);
                }
            });
        }
        if (view != null) {
            view.setVisibility(0);
        }
        if (view == null) {
            return;
        }
        view.setContentDescription(action.title);
    }

    public final void setActionBundle(Bundle bundle) {
        this.actionBundle = bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setActionData(miui.systemui.notification.focus.model.Template r15, android.service.notification.StatusBarNotification r16, boolean r17) throws java.net.URISyntaxException {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleViewHolder.setActionData(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification, boolean):void");
    }

    public final void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    public final void setBitmapBundle(Bundle bundle) {
        this.bitmapBundle = bundle;
    }

    public final void setColorContentBg(Integer num) {
        this.colorContentBg = num;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final void setContentColor(Integer num) {
        this.contentColor = num;
    }

    public final void setContentColorDark(Integer num) {
        this.contentColorDark = num;
    }

    public final void setExtraTitle(String str) {
        this.extraTitle = str;
    }

    public final void setExtraTitleColor(Integer num) {
        this.extraTitleColor = num;
    }

    public final void setExtraTitleColorDark(Integer num) {
        this.extraTitleColorDark = num;
    }

    public final void setHighLightText(String str) {
        this.highLightText = str;
    }

    public final void setHighLightTextColor(Integer num) {
        this.highLightTextColor = num;
    }

    public final void setHighLightTextColorDark(Integer num) {
        this.highLightTextColorDark = num;
    }

    public final void setHighLightbgColor(Integer num) {
        this.highLightbgColor = num;
    }

    public final void setHighLightbgColorDark(Integer num) {
        this.highLightbgColorDark = num;
    }

    public final void setIgnoreTextColor(boolean z2) {
        this.ignoreTextColor = z2;
    }

    public final void setLastWidth(int i2) {
        this.lastWidth = i2;
    }

    public final void setModule(String str) {
        this.module = str;
    }

    public final void setPrimaryColor(Integer num) {
        this.primaryColor = num;
    }

    public final void setPrimaryColorDark(Integer num) {
        this.primaryColorDark = num;
    }

    public final void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public final void setProgressData(int i2, StatusBarNotification sbn, Boolean bool) {
        kotlin.jvm.internal.n.g(sbn, "sbn");
        if (kotlin.jvm.internal.n.c(bool, Boolean.TRUE)) {
            View view = this.view;
            if ((view != null ? (StatusProgressLayout) view.findViewById(i2) : null) != null) {
                sbn.getNotification().extras.putInt("StatusProgressLayout", i2);
                sbn.getNotification().extras.putBoolean("isAutoProgress", bool.booleanValue());
            }
        }
    }

    public final void setSbn(StatusBarNotification statusBarNotification) {
        this.sbn = statusBarNotification;
    }

    public final void setScope(E e2) {
        kotlin.jvm.internal.n.g(e2, "<set-?>");
        this.scope = e2;
    }

    public final void setSecondaryColor(Integer num) {
        this.secondaryColor = num;
    }

    public final void setSecondaryColorDark(Integer num) {
        this.secondaryColorDark = num;
    }

    public final void setSecondaryText(String str) {
        this.secondaryText = str;
    }

    public final void setShowSecondaryLine(Boolean bool) {
        this.showSecondaryLine = bool;
    }

    public final void setSpecialTitle(String str) {
        this.specialTitle = str;
    }

    public final void setSpecialTitleColor(Integer num) {
        this.specialTitleColor = num;
    }

    public final void setSpecialTitleColorDark(Integer num) {
        this.specialTitleColorDark = num;
    }

    public final void setSubContent(String str) {
        this.subContent = str;
    }

    public final void setSubContentColor(Integer num) {
        this.subContentColor = num;
    }

    public final void setSubContentColorDark(Integer num) {
        this.subContentColorDark = num;
    }

    public final void setSubTitleColor(Integer num) {
        this.subTitleColor = num;
    }

    public final void setSubTitleColorDark(Integer num) {
        this.subTitleColorDark = num;
    }

    public final void setSubtitle(String str) {
        this.subtitle = str;
    }

    public final void setTimerCurrent(Long l2) {
        this.timerCurrent = l2;
    }

    public final void setTimerData(int i2, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(sbn, "sbn");
        Integer num = this.timerType;
        if (num != null && num.intValue() == 0) {
            return;
        }
        View view = this.view;
        HyperChronometer hyperChronometer = view != null ? (HyperChronometer) view.findViewById(i2) : null;
        if (hyperChronometer != null) {
            hyperChronometer.setEnableEffectWithInit(false);
        }
        Integer num2 = this.timerType;
        if (num2 != null) {
            int iIntValue = num2.intValue();
            sbn.getNotification().extras.putInt("timerType", iIntValue);
            if (hyperChronometer != null) {
                boolean z2 = iIntValue < 0;
                if (z2 != hyperChronometer.isCountDown()) {
                    hyperChronometer.setCountDown(z2);
                }
                if (z2) {
                    if (iIntValue == -1) {
                        hyperChronometer.enableEffect(!CommonUtils.NOT_SUPPORT_TEXT_EFFECT);
                        hyperChronometer.setTextChangeProcessor(new CharChangeProcessor());
                    } else {
                        hyperChronometer.enableEffect(false);
                    }
                }
            }
        }
        Long l2 = this.timerWhen;
        if (l2 != null) {
            sbn.getNotification().extras.putLong("timerWhen", l2.longValue());
        }
        Long l3 = this.timerCurrent;
        if (l3 != null) {
            sbn.getNotification().extras.putLong("timerCurrent", l3.longValue());
        }
        Long l4 = this.timerTotal;
        if (l4 != null) {
            sbn.getNotification().extras.putLong("timerTotal", l4.longValue());
        }
        Long l5 = this.timerSystemCurrent;
        if (l5 != null) {
            sbn.getNotification().extras.putLong("timerSystemCurrent", l5.longValue());
        }
        sbn.getNotification().extras.putInt("miui.focus.chronometerId", i2);
        if (this.timerInitialized) {
            return;
        }
        if (hyperChronometer != null) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            Long l6 = this.timerWhen;
            kotlin.jvm.internal.n.d(l6);
            long jLongValue = jElapsedRealtime + l6.longValue();
            Long l7 = this.timerSystemCurrent;
            kotlin.jvm.internal.n.d(l7);
            hyperChronometer.setBase(jLongValue - l7.longValue());
        }
        if (hyperChronometer != null) {
            hyperChronometer.setFontFeatureSettings("ss01");
        }
        this.timerInitialized = true;
    }

    public final void setTimerInitialized(boolean z2) {
        this.timerInitialized = z2;
    }

    public final void setTimerSystemCurrent(Long l2) {
        this.timerSystemCurrent = l2;
    }

    public final void setTimerTotal(Long l2) {
        this.timerTotal = l2;
    }

    public final void setTimerType(Integer num) {
        this.timerType = num;
    }

    public final void setTimerWhen(Long l2) {
        this.timerWhen = l2;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final void setTitleColor(Integer num) {
        this.titleColor = num;
    }

    public final void setTitleColorDark(Integer num) {
        this.titleColorDark = num;
    }

    public final void setView(View view) {
        this.view = view;
    }

    public final void setViewWidth(TextView textView, int i2, int i3) {
        Log.i(TAG, "setViewWidth " + i2 + " " + textView + " " + i3);
        int iMax = Math.max(i2, i3);
        if (textView == null || textView.getWidth() == iMax) {
            return;
        }
        textView.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = iMax;
        textView.setLayoutParams(layoutParams);
        textView.requestLayout();
    }

    public final void showAppIcon(ImageView imageView, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(sbn, "sbn");
        String sbnTargetPkg = NotificationUtil.getSbnTargetPkg(this.sysuiCtx, sbn);
        if (imageView != null) {
            imageView.setImageDrawable(DynamicIslandUtils.INSTANCE.getAppIcon(this.ctx, sbnTargetPkg, Integer.valueOf(sbn.getUser().getIdentifier())));
        }
    }

    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        updateBundle(sbn);
    }

    public static /* synthetic */ Notification.Action buildAction$default(ModuleViewHolder moduleViewHolder, ActionInfo actionInfo, StatusBarNotification statusBarNotification, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: buildAction");
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return moduleViewHolder.buildAction(actionInfo, statusBarNotification, z2);
    }

    private final Notification.Action buildAction(ActionInfo actionInfo, StatusBarNotification statusBarNotification, boolean z2) throws URISyntaxException {
        Icon icon;
        Bundle extras;
        String action = actionInfo.getAction();
        Notification.Action actionBuild = null;
        actionBuild = null;
        PendingIntent activity = null;
        if (action != null) {
            Bundle bundle = this.actionBundle;
            if (bundle != null) {
                actionBuild = (Notification.Action) bundle.getParcelable(action);
            }
        } else if (actionInfo.getActionTitle() != null) {
            Integer actionIntentType = actionInfo.getActionIntentType();
            int iIntValue = actionIntentType != null ? actionIntentType.intValue() : 1;
            String actionIntent = actionInfo.getActionIntent();
            if (actionIntent == null) {
                actionIntent = "";
            }
            Intent uri = Intent.parseUri(actionIntent, 1);
            if (iIntValue == 1) {
                activity = PendingIntent.getActivity(this.sysuiCtx, (int) statusBarNotification.getPostTime(), uri, 201326592);
            } else if (iIntValue == 2) {
                activity = PendingIntent.getBroadcast(this.sysuiCtx, (int) statusBarNotification.getPostTime(), uri, 201326592);
            } else if (iIntValue == 3) {
                activity = PendingIntent.getService(this.sysuiCtx, (int) statusBarNotification.getPostTime(), uri, 201326592);
            }
            if (!z2 || (icon = getIcon(actionInfo.getActionIconDark(), statusBarNotification)) == null) {
                icon = getIcon(actionInfo.getActionIcon(), statusBarNotification);
            }
            actionBuild = new Notification.Action.Builder(icon, actionInfo.getActionTitle(), activity).build();
            try {
                actionBuild.title = actionInfo.getActionTitle();
                Bundle extras2 = actionBuild.getExtras();
                if (extras2 != null) {
                    extras2.putString(Const.Param.COLOR_TITLE, actionInfo.getActionTitleColor());
                }
                Bundle extras3 = actionBuild.getExtras();
                if (extras3 != null) {
                    extras3.putString(Const.Param.COLOR_BG, actionInfo.getActionBgColor());
                }
                Bundle extras4 = actionBuild.getExtras();
                if (extras4 != null) {
                    extras4.putString(Const.Param.COLOR_TITLE_DARK, actionInfo.getActionTitleColorDark());
                }
                Bundle extras5 = actionBuild.getExtras();
                if (extras5 != null) {
                    extras5.putString(Const.Param.COLOR_BG_DARK, actionInfo.getActionBgColorDark());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        boolean clickWithCollapse = actionInfo.getClickWithCollapse();
        if (actionBuild != null && (extras = actionBuild.getExtras()) != null) {
            extras.putBoolean("click_with_collapse", clickWithCollapse);
        }
        return actionBuild;
    }
}
