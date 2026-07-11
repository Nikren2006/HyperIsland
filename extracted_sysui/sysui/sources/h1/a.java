package h1;

import android.os.Looper;
import g1.w0;
import java.util.List;
import l1.t;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements t {
    @Override // l1.t
    public String a() {
        return "For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used";
    }

    @Override // l1.t
    public w0 b(List list) {
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            return new c(e.a(mainLooper, true), null, 2, null);
        }
        throw new IllegalStateException("The main looper is not available");
    }

    @Override // l1.t
    public int c() {
        return 1073741823;
    }
}
