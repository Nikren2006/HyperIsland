package miui.systemui.notification.focus.moduleV3.ext;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.service.notification.StatusBarNotification;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.R;
import miui.systemui.notification.focus.moduleV3.ModuleViewHolder;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.FocusNotificationUtil;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleViewHolderExtKt {
    public static final String TAG = "ActionClickLog";

    public static final boolean containsHTML(String str) {
        n.g(str, "<this>");
        if (f1.n.n(str)) {
            return false;
        }
        Spanned spannedFromHtml = HtmlCompat.fromHtml(str, 0);
        n.f(spannedFromHtml, "fromHtml(...)");
        Object[] spans = spannedFromHtml.getSpans(0, spannedFromHtml.length(), Object.class);
        n.f(spans, "getSpans(...)");
        return ((spans.length == 0) && n.c(spannedFromHtml.toString(), str)) ? false : true;
    }

    public static final int customLength(String str) {
        n.g(str, "<this>");
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            int i4 = 1;
            if (!Character.isDigit(cCharAt) && ((!Character.isLetter(cCharAt) || cCharAt >= 128) && 19968 <= cCharAt && cCharAt < 40960)) {
                i4 = 2;
            }
            i2 += i4;
        }
        return i2;
    }

    public static final int dpToPx(Context context, int i2) {
        if (context == null) {
            return (int) (Resources.getSystem().getDisplayMetrics().density * i2);
        }
        return (int) (i2 * context.getResources().getDisplayMetrics().density);
    }

    public static final boolean findViewByTagId(View view, int i2) {
        n.g(view, "view");
        Object tag = view.getTag(i2);
        if (tag != null && (tag instanceof Boolean)) {
            return ((Boolean) tag).booleanValue() || view.getVisibility() != 0 || view.getAlpha() == 0.0f;
        }
        if (view.getParent() == null || !(view.getParent() instanceof View)) {
            return false;
        }
        Object parent = view.getParent();
        n.e(parent, "null cannot be cast to non-null type android.view.View");
        return findViewByTagId((View) parent, i2);
    }

    public static final void sendCollapseBroadcast(Context ctx) {
        n.g(ctx, "ctx");
        Intent intent = new Intent();
        intent.setAction(DynamicIslandConstants.ACTION_COLLAPSE_ISLAND);
        ctx.sendBroadcast(intent);
    }

    public static final void sendWithCollapse(PendingIntent pendingIntent, Notification.Action action, Context ctx, View view, boolean z2, StatusBarNotification sbn) throws PendingIntent.CanceledException, IOException {
        n.g(pendingIntent, "<this>");
        n.g(action, "action");
        n.g(ctx, "ctx");
        n.g(view, "view");
        n.g(sbn, "sbn");
        Log.d(TAG, "island action clicked: " + z2 + ", isActivity:" + pendingIntent.isActivity() + ", vis:" + view.getVisibility() + " alpha:" + view.getAlpha());
        if (ModuleViewHolder.Companion.isContinuousClick(sbn.getKey())) {
            return;
        }
        if (z2) {
            boolean zFindViewByTagId = findViewByTagId(view, R.id.dynamic_island_animating_tag);
            Log.e(TAG, "island animating: " + zFindViewByTagId);
            if (zFindViewByTagId) {
                return;
            }
        } else if (findViewByTagId(view, R.id.dynamic_island_modal_tag)) {
            CommonUtils.INSTANCE.exitModal();
        }
        FocusNotificationUtil.onActionClick(pendingIntent);
        if (pendingIntent.isActivity() || action.getExtras().getBoolean("click_with_collapse", false)) {
            CommonUtils.INSTANCE.collapseControlCenter();
            sendCollapseBroadcast(ctx);
        }
    }

    public static /* synthetic */ void sendWithCollapse$default(PendingIntent pendingIntent, Notification.Action action, Context context, View view, boolean z2, StatusBarNotification statusBarNotification, int i2, Object obj) throws PendingIntent.CanceledException, IOException {
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        sendWithCollapse(pendingIntent, action, context, view, z2, statusBarNotification);
    }
}
