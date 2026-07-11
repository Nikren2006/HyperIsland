package androidx.lifecycle;

import android.app.Application;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public class AndroidViewModel extends ViewModel {
    private final Application application;

    public AndroidViewModel(Application application) {
        n.g(application, "application");
        this.application = application;
    }

    public <T extends Application> T getApplication() {
        T t2 = (T) this.application;
        n.e(t2, "null cannot be cast to non-null type T of androidx.lifecycle.AndroidViewModel.getApplication");
        return t2;
    }
}
