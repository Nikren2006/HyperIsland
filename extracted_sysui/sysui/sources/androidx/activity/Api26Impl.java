package androidx.activity;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.graphics.Rect;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
public final class Api26Impl {
    public static final Api26Impl INSTANCE = new Api26Impl();

    private Api26Impl() {
    }

    public final void setPipParamsSourceRectHint(Activity activity, Rect rect) {
        activity.setPictureInPictureParams(new PictureInPictureParams.Builder().setSourceRectHint(rect).build());
    }
}
