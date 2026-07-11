package b;

import F0.e;
import com.android.systemui.settings.UserContextProvider;

/* JADX INFO: loaded from: classes.dex */
public final class b implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G0.a f1303a;

    public b(G0.a aVar) {
        this.f1303a = aVar;
    }

    public static b a(G0.a aVar) {
        return new b(aVar);
    }

    public static BinderC0222a c(UserContextProvider userContextProvider) {
        return new BinderC0222a(userContextProvider);
    }

    @Override // G0.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public BinderC0222a get() {
        return c((UserContextProvider) this.f1303a.get());
    }
}
