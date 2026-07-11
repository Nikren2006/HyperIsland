package m0;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
public abstract class x extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5335a;

    public x(Handler handler) {
        super(handler.getLooper());
    }

    public void a(int i2, Object obj) {
        Message messageObtainMessage = obtainMessage(i2, obj);
        messageObtainMessage.setAsynchronous(true);
        messageObtainMessage.sendToTarget();
    }

    public void b(int i2, Object obj, int i3) {
        Message messageObtainMessage = obtainMessage(i2, obj);
        messageObtainMessage.arg1 = i3;
        messageObtainMessage.setAsynchronous(true);
        messageObtainMessage.sendToTarget();
    }
}
