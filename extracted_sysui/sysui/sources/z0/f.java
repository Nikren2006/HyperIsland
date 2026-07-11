package z0;

import android.content.Context;
import android.media.AudioManager;
import com.miui.miplay.audio.data.DeviceInfo;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f7131b = "f";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static f f7132c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AudioManager f7133a;

    public f(Context context) {
        this.f7133a = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
    }

    public static synchronized f a(Context context) {
        try {
            if (f7132c == null) {
                f7132c = new f(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return f7132c;
    }

    public void b(boolean z2) {
        if (z2) {
            this.f7133a.adjustStreamVolume(3, -100, 0);
        } else {
            this.f7133a.adjustStreamVolume(3, 100, 0);
        }
        e.c(f7131b, "adjustStreamVolume.");
    }
}
