package w0;

import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaSessionManager;
import android.os.UserHandle;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import z0.e;

/* JADX INFO: renamed from: w0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0768d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f6980b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f6981c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaSessionManager f6982a;

    public C0768d(Context context) {
        this.f6982a = (MediaSessionManager) context.getSystemService("media_session");
    }

    public void a(ComponentName componentName, UserHandle userHandle, Executor executor, MediaSessionManager.OnActiveSessionsChangedListener onActiveSessionsChangedListener) {
        try {
            if (f6980b == null) {
                Method declaredMethod = MediaSessionManager.class.getDeclaredMethod("addOnActiveSessionsChangedListener", ComponentName.class, UserHandle.class, Executor.class, MediaSessionManager.OnActiveSessionsChangedListener.class);
                f6980b = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            f6980b.invoke(this.f6982a, componentName, userHandle, executor, onActiveSessionsChangedListener);
        } catch (Exception e2) {
            e.b("MediaSessionManagerCompat", "addOnActiveSessionsChangedListener ", e2);
        }
    }

    public List b(ComponentName componentName, UserHandle userHandle) {
        try {
            if (f6981c == null) {
                Method declaredMethod = MediaSessionManager.class.getDeclaredMethod("getActiveSessionsForUser", ComponentName.class, UserHandle.class);
                f6981c = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            return (List) f6981c.invoke(this.f6982a, componentName, userHandle);
        } catch (Exception e2) {
            e.b("MediaSessionManagerCompat", "getActiveSessionsForUser ", e2);
            return Collections.emptyList();
        }
    }

    public void c(MediaSessionManager.OnActiveSessionsChangedListener onActiveSessionsChangedListener) {
        this.f6982a.removeOnActiveSessionsChangedListener(onActiveSessionsChangedListener);
    }
}
