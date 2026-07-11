package com.android.systemui.miui.volume.widget;

import android.R;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;

/* JADX INFO: loaded from: classes2.dex */
public class DndToast {
    private static final String KEY_ZEN_MODE_INTERCEPT_SCENE = "zen_mode_intercepted_when_unlocked";
    private View mContentView;
    private final Context mContext;
    private final WindowManager.LayoutParams mParams;
    private boolean mShowing = false;
    private ToastHandler mToastHandler = new ToastHandler(this);
    private final WindowManager mWindowManager;

    public static class ToastHandler extends Handler {
        public static final int HIDE = 1;
        public static final int SHOW = 0;
        private WeakReference<DndToast> mDndToast;

        public ToastHandler(DndToast dndToast) {
            this.mDndToast = new WeakReference<>(dndToast);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                if (this.mDndToast.get() != null) {
                    this.mDndToast.get().handleShow(Boolean.parseBoolean(message.obj.toString()));
                }
            } else if (i2 == 1 && this.mDndToast.get() != null) {
                this.mDndToast.get().handleHide();
            }
        }
    }

    public DndToast(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2024, 16810240, -3);
        this.mParams = layoutParams;
        layoutParams.flags = SecondaryParamsKt.FROM_WIFI;
        layoutParams.windowAnimations = R.style.Animation.Toast;
        layoutParams.y = context.getResources().getDimensionPixelSize(com.android.systemui.miui.volume.R.dimen.dnd_toast_y_offset);
        layoutParams.gravity = 81;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleShow(boolean z2) {
        if (this.mToastHandler.hasMessages(1)) {
            this.mToastHandler.removeMessages(1);
            handleHide();
        }
        View viewInflate = LayoutInflater.from(this.mContext).inflate(com.android.systemui.miui.volume.R.layout.dnd_toast_view, (ViewGroup) null);
        this.mContentView = viewInflate;
        TextView textView = (TextView) viewInflate.findViewById(com.android.systemui.miui.volume.R.id.dnd_toast_text);
        ImageView imageView = (ImageView) this.mContentView.findViewById(com.android.systemui.miui.volume.R.id.call_icon);
        ImageView imageView2 = (ImageView) this.mContentView.findViewById(com.android.systemui.miui.volume.R.id.notification_icon);
        if (Settings.System.getInt(this.mContext.getContentResolver(), KEY_ZEN_MODE_INTERCEPT_SCENE, 1) == 0) {
            textView.setText(z2 ? com.android.systemui.miui.volume.R.string.miui_toast_zen_to_dnd_when_lock : com.android.systemui.miui.volume.R.string.miui_toast_zen_dnd_to_off);
        } else {
            textView.setText(z2 ? com.android.systemui.miui.volume.R.string.miui_toast_zen_to_dnd : com.android.systemui.miui.volume.R.string.miui_toast_zen_dnd_to_off);
        }
        imageView.setImageResource(z2 ? com.android.systemui.miui.volume.R.drawable.ic_miui_volume_dnd_toast_call_mute : com.android.systemui.miui.volume.R.drawable.ic_miui_volume_dnd_toast_call);
        imageView2.setImageResource(z2 ? com.android.systemui.miui.volume.R.drawable.ic_miui_volume_dnd_toast_notification_mute : com.android.systemui.miui.volume.R.drawable.ic_miui_volume_dnd_toast_notification);
        try {
            this.mWindowManager.addView(this.mContentView, this.mParams);
            this.mContentView.announceForAccessibility(textView.getText());
            this.mShowing = true;
            this.mToastHandler.sendEmptyMessageDelayed(1, 2000L);
        } catch (Exception e2) {
            Log.e("DndToast", "showCustomizeViewToast error", e2);
        }
    }

    public void handleHide() {
        View view = this.mContentView;
        if (view != null) {
            this.mWindowManager.removeViewImmediate(view);
            this.mContentView = null;
        }
        this.mShowing = false;
    }

    public void hide() {
        if (this.mToastHandler.hasMessages(1)) {
            this.mToastHandler.removeMessages(1);
        }
        this.mToastHandler.sendEmptyMessage(1);
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    public void show(boolean z2) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        messageObtain.obj = Boolean.valueOf(z2);
        this.mToastHandler.sendMessage(messageObtain);
    }
}
