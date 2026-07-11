package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import java.util.ArrayList;
import java.util.List;
import miui.systemui.notification.focus.model.MultiProgressInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.views.NotificationProgressDrawable;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleMultiProgressViewHolder extends ModuleViewHolder {
    private final String TAG;
    private View container;
    private TimerTextEffectView focusTitle;
    private final boolean island;
    private ProgressBar progressBar;
    private View topContainer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleMultiProgressViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
        this.TAG = "ModuleMultiProgressViewHolder";
    }

    private final List<NotificationProgressDrawable.Part> convertToDrawableParts(int i2, int i3, String str) {
        Integer numValueOf;
        ArrayList arrayList = new ArrayList();
        int i4 = i3 != 0 ? 100 / i3 : 100;
        int i5 = i2 / i4;
        try {
            numValueOf = Integer.valueOf(Color.parseColor(str));
        } catch (Exception unused) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
        if (i3 == 0) {
            arrayList.add(new NotificationProgressDrawable.Segment(i4 * 0.01f, iIntValue, true, i2 * 0.01f));
            return arrayList;
        }
        int i6 = 0;
        while (i6 < i3) {
            arrayList.add(new NotificationProgressDrawable.Segment(i4 * 0.01f, iIntValue, i6 >= i5, i6 == i5 ? (i2 - (i6 * i4)) * 0.01f : 0.0f));
            arrayList.add(new NotificationProgressDrawable.Point(null, iIntValue, i6 > i5, i6 - i5));
            i6++;
        }
        Log.d(this.TAG, " get Parts=" + arrayList);
        return arrayList;
    }

    private final void showMultiProgress(Template template, StatusBarNotification statusBarNotification) {
        MultiProgressInfo multiProgressInfo = template.getMultiProgressInfo();
        if (multiProgressInfo != null) {
            if (multiProgressInfo.getProgress() < 0 || multiProgressInfo.getPoints() < 0) {
                Log.e(this.TAG, " multiProgressInfo empty or values are negative !!!");
                return;
            }
            if (multiProgressInfo.getProgress() > 100) {
                Log.w(this.TAG, " multiProgressInfo progress > 100, reduced to 100");
                multiProgressInfo.setProgress(100);
            } else if (multiProgressInfo.getPoints() > 4) {
                Log.w(this.TAG, " multiProgressInfo points > 4, limited to 4");
                multiProgressInfo.setPoints(4);
            }
            ProgressBar progressBar = this.progressBar;
            if (progressBar != null) {
                Drawable drawable = getCtx().getDrawable(R.drawable.notification_progress);
                kotlin.jvm.internal.n.e(drawable, "null cannot be cast to non-null type miui.systemui.notification.focus.views.NotificationProgressDrawable");
                NotificationProgressDrawable notificationProgressDrawable = (NotificationProgressDrawable) drawable;
                notificationProgressDrawable.setParts(convertToDrawableParts(multiProgressInfo.getProgress(), multiProgressInfo.getPoints(), multiProgressInfo.getColor()));
                progressBar.setProgress(multiProgressInfo.getProgress());
                progressBar.setMax(100);
                progressBar.setVisibility(0);
                progressBar.setProgressDrawable(notificationProgressDrawable);
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getMultiProgressInfo());
        View view = this.topContainer;
        if (view != null) {
            view.setVisibility(0);
        }
        View view2 = this.container;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        if (TextUtils.isEmpty(getTitle())) {
            TimerTextEffectView timerTextEffectView = this.focusTitle;
            if (timerTextEffectView != null) {
                timerTextEffectView.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView2 = this.focusTitle;
            if (timerTextEffectView2 != null) {
                timerTextEffectView2.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView3 = this.focusTitle;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setText(Html.fromHtml(getTitle()), TextView.BufferType.SPANNABLE);
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                TimerTextEffectView timerTextEffectView4 = this.focusTitle;
                if (timerTextEffectView4 != null) {
                    timerTextEffectView4.updateTextWithNewAppearance(Html.fromHtml(getTitle()), Integer.valueOf(iIntValue));
                }
            }
        }
        showMultiProgress(template, sbn);
    }

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_multi_progress, getRootView()));
        View view = getView();
        this.topContainer = view != null ? view.findViewById(R.id.focus_container_module_multi_progress) : null;
        View view2 = getView();
        this.container = view2 != null ? view2.findViewById(R.id.focus_title_container) : null;
        View view3 = getView();
        this.focusTitle = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.focus_title) : null;
        View view4 = getView();
        this.progressBar = view4 != null ? (ProgressBar) view4.findViewById(R.id.multi_progress) : null;
        TimerTextEffectView timerTextEffectView = this.focusTitle;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.focusTitle;
        if (timerTextEffectView2 == null) {
            return;
        }
        timerTextEffectView2.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getMultiProgressInfo());
        bind(template, sbn);
    }
}
