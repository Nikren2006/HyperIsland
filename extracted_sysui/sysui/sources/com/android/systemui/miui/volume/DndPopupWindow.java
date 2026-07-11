package com.android.systemui.miui.volume;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;
import com.android.systemui.volume.VolumePrefs;

/* JADX INFO: loaded from: classes2.dex */
public class DndPopupWindow {
    private static final String KEY_DND_GUIDE_DIALOG_ALREADY_SHOW = "dnd_guide_dialog_already_show";
    private View mContentView;
    private Context mContext;
    private WindowManager.LayoutParams mParams;
    private SharedPreferences mSharedPreferences;
    private boolean mShowing = false;
    private WindowManager mWindowManager;

    public DndPopupWindow(Context context) {
        this.mContext = context;
        this.mSharedPreferences = VolumePrefs.get(context);
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPositionY(int i2) {
        Resources resources = this.mContext.getResources();
        return (((MiuiVolumeDialogRes.getMarginTop(this.mContext) + VolumeColumnRes.getHeight(this.mContext)) + ((int) (((double) resources.getDimensionPixelSize(R.dimen.miui_volume_silence_button_height)) * 1.5d))) + (resources.getDimensionPixelSize(R.dimen.miui_volume_footer_margin_top) * 2)) - ((int) (i2 * 0.8f));
    }

    private void init() {
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        Resources resources = this.mContext.getResources();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(resources.getDimensionPixelSize(R.dimen.miui_volume_dnd_popup_width), -2, 2020, 17072384, -3);
        this.mParams = layoutParams;
        layoutParams.gravity = 53;
        layoutParams.x = VolumeColumnRes.getWidth(this.mContext) + (MiuiVolumeDialogRes.getMarginRight(this.mContext, true, false) * 2);
        this.mParams.y = getPositionY(resources.getDimensionPixelSize(R.dimen.miui_volume_dnd_popup_height));
    }

    private boolean isUserSetup() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return (Settings.Global.getInt(contentResolver, "device_provisioned", 0) == 0 || Settings.Secure.getInt(contentResolver, "user_setup_complete", 0) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$show$0(View view) {
        dismiss();
    }

    private boolean shouldShowDndPopup(boolean z2) {
        return !this.mSharedPreferences.getBoolean(KEY_DND_GUIDE_DIALOG_ALREADY_SHOW, false) && !z2 && this.mContext.getResources().getConfiguration().orientation == 1 && isUserSetup();
    }

    public void dismiss() {
        this.mShowing = false;
        View view = this.mContentView;
        if (view != null) {
            this.mWindowManager.removeViewImmediate(view);
            this.mContentView = null;
        }
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    public void show(boolean z2) {
        if (shouldShowDndPopup(z2)) {
            this.mShowing = true;
            this.mSharedPreferences.edit().putBoolean(KEY_DND_GUIDE_DIALOG_ALREADY_SHOW, true).apply();
            View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.miui_volume_dnd_popup, (ViewGroup) null);
            this.mContentView = viewInflate;
            ((TextView) viewInflate.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.miui.volume.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f1483a.lambda$show$0(view);
                }
            });
            this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.miui.volume.DndPopupWindow.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    DndPopupWindow.this.mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    WindowManager.LayoutParams layoutParams = DndPopupWindow.this.mParams;
                    DndPopupWindow dndPopupWindow = DndPopupWindow.this;
                    layoutParams.y = dndPopupWindow.getPositionY(dndPopupWindow.mContentView.getMeasuredHeight());
                    DndPopupWindow.this.mWindowManager.updateViewLayout(DndPopupWindow.this.mContentView, DndPopupWindow.this.mParams);
                }
            });
            this.mWindowManager.addView(this.mContentView, this.mParams);
            this.mContentView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.miui.volume.DndPopupWindow.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int x2 = (int) motionEvent.getX();
                    int y2 = (int) motionEvent.getY();
                    if (motionEvent.getAction() == 0 && (x2 < 0 || x2 >= DndPopupWindow.this.mContentView.getWidth() || y2 < 0 || y2 >= DndPopupWindow.this.mContentView.getHeight())) {
                        DndPopupWindow.this.dismiss();
                        return true;
                    }
                    if (motionEvent.getAction() != 4) {
                        return false;
                    }
                    DndPopupWindow.this.dismiss();
                    return true;
                }
            });
        }
    }
}
