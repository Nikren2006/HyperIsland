package miui.systemui.flashlight;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.flashlight.utils.TrackUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightNotificationService extends Service {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MiFlash_NotificationService";
    private final int notifyId = 1;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final String getActionsString() {
        Intent intent = new Intent(MiFlashlightReceiver.ACTION_CLOSE);
        intent.setPackage("miui.systemui.plugin");
        String uri = intent.toUri(1);
        return "[\n            {\n                \"type\": 2,\n                \"actionTitle\": \"" + getString(R.string.flashlight_notification_btn_close) + "\",\n                \"actionTitleColor\": \"#FFFFFF\",\n                \"actionBgColor\": \"#1EFFFFFF\",\n                \"actionBgPressColor\": \"#24FFFFFF\",\n                \"actionIntentType\": 2,\n                \"actionIntent\": \"" + uri + "\",\n                \"actionIcon\": \"miui.focus.pic_mark_v2\"\n            }\n        ]";
    }

    private final Bitmap getBitmapFromDrawable(Context context, int i2) {
        Drawable drawable = ContextCompat.getDrawable(context, i2);
        if (drawable == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private final String getExtraMiuiFocusParam() {
        String string = getResources().getString(R.string.flashlight_notification_content_title);
        String string2 = getResources().getString(R.string.flashlight_notification_content_text);
        return "{\n                \"param_v2\": {\n                    \"protocol\": 1,\n                    \"business\": \"miui_flashlight\",\n                    \"ticker\": \"" + string2 + "\",\n                    \"tickerPic\": \"miui.focus.pic_ticker_pic\",\n                    \"tickerPicDark\": \"miui.focus.pic_ticker_pic\",\n                    \"aodTitle\": \"" + string2 + "\",\n                    \"aodPic\": \"miui.focus.pic_ado_pic\",\n                    \"scene\": \"template_v2\",\n                    \"enableFloat\": false,\n                    \"updatable\": true,\n                    \"reopen\": \"reopen\",\n                    \"islandFirstFloat\": false,\n                    \"param_island\": {\n                        \"islandProperty\": 2,\n                        \"bigIslandArea\": {\"imageTextInfoLeft\": {\"type\": 1,\"picInfo\": {\"type\": 3,\"pic\": \"flash_light_icon\"} } },\n                        \"smallIslandArea\": { \"picInfo\": { \"type\": 3, \"pic\": \"flash_light_icon\" } }\n                    },\n                    \"baseInfo\": {\n                        \"title\": \"" + string + "\",\n                        \"colorTitle\": \"#FFFFFF\",\n                        \"content\": \"" + string2 + "\",\n                        \"colorContent\": \"#7FFFFFFF\",\n                        \"subContent\": \"\",\n                        \"colorSubContent\": \"#FFFFFF\",\n                        \"type\": 2\n                    },\n                    \"bgInfo\": { \"colorBg\": \"#1A1A1A\" },\n                    \"iconTextInfo\" : {\n                       \"title\": \"" + string + "\",\n                       \"content\": \"" + string2 + "\",\n                       \"subContent\": \"\",\n                       \"colorSubContent\": \"\",\n                       \"colorSubContentDark\": \"\",\n                       \"animIconInfo\": { \"src\": \"doubleCharge\",\"type\": \"2\" }\n                    },\n                    \"actions\": " + getActionsString() + "\n                }\n            }";
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Log.i(TAG, "onStartCommand");
        sendFocusNotification();
        return 1;
    }

    public final void sendFocusNotification() {
        String string = getResources().getString(R.string.flashlight_notification_channel_name);
        String string2 = getResources().getString(R.string.flashlight_notification_content_title);
        String string3 = getResources().getString(R.string.flashlight_notification_content_text);
        Object systemService = getSystemService(NotificationManager.class);
        n.f(systemService, "getSystemService(...)");
        NotificationManager notificationManager = (NotificationManager) systemService;
        notificationManager.createNotificationChannel(new NotificationChannel("flashlight_channel", string, 3));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "flashlight_channel");
        builder.setSmallIcon(R.drawable.mi_flashlight_on_small_icon);
        builder.setContentTitle(string2);
        builder.setContentText(string3);
        Bundle bundle = new Bundle();
        bundle.putString("miui.focus.param", getExtraMiuiFocusParam());
        Bundle bundle2 = new Bundle();
        Bitmap bitmapFromDrawable = getBitmapFromDrawable(this, R.drawable.mi_flashlight_on_ticker_pic);
        if (bitmapFromDrawable != null) {
            bundle2.putParcelable("miui.focus.pic_ticker_pic", Icon.createWithBitmap(bitmapFromDrawable));
            bundle.putParcelable("miui.appIcon", Icon.createWithBitmap(bitmapFromDrawable));
            bitmapFromDrawable.recycle();
        }
        Bitmap bitmapFromDrawable2 = getBitmapFromDrawable(this, R.drawable.mi_flashlight_on_notification_icon);
        if (bitmapFromDrawable2 != null) {
            bundle2.putParcelable("miui.focus.pic_ado_pic", Icon.createWithBitmap(bitmapFromDrawable2));
            bundle2.putParcelable("miui.focus.pic_mark_v2", Icon.createWithBitmap(bitmapFromDrawable2));
            bitmapFromDrawable2.recycle();
        }
        bundle.putBundle("miui.focus.pics", bundle2);
        builder.addExtras(bundle);
        Notification notificationBuild = builder.build();
        n.f(notificationBuild, "build(...)");
        Intent intent = new Intent(this, (Class<?>) MiFlashlightActivity.class);
        intent.putExtra("hide_anim", true);
        intent.putExtra(TrackUtils.ENTER_FROM, 1);
        notificationBuild.contentIntent = PendingIntent.getActivity(this, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        notificationManager.notify(this.notifyId, notificationBuild);
        startForeground(this.notifyId, notificationBuild, 64);
    }
}
