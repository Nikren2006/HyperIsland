package androidx.fragment.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentKt {
    public static final void clearFragmentResult(Fragment clearFragmentResult, String requestKey) {
        n.g(clearFragmentResult, "$this$clearFragmentResult");
        n.g(requestKey, "requestKey");
        clearFragmentResult.getParentFragmentManager().clearFragmentResult(requestKey);
    }

    public static final void clearFragmentResultListener(Fragment clearFragmentResultListener, String requestKey) {
        n.g(clearFragmentResultListener, "$this$clearFragmentResultListener");
        n.g(requestKey, "requestKey");
        clearFragmentResultListener.getParentFragmentManager().clearFragmentResultListener(requestKey);
    }

    public static final void setFragmentResult(Fragment setFragmentResult, String requestKey, Bundle result) {
        n.g(setFragmentResult, "$this$setFragmentResult");
        n.g(requestKey, "requestKey");
        n.g(result, "result");
        setFragmentResult.getParentFragmentManager().setFragmentResult(requestKey, result);
    }

    public static final void setFragmentResultListener(Fragment setFragmentResultListener, String requestKey, final Function2 listener) {
        n.g(setFragmentResultListener, "$this$setFragmentResultListener");
        n.g(requestKey, "requestKey");
        n.g(listener, "listener");
        setFragmentResultListener.getParentFragmentManager().setFragmentResultListener(requestKey, setFragmentResultListener, new FragmentResultListener() { // from class: androidx.fragment.app.FragmentKt$sam$androidx_fragment_app_FragmentResultListener$0
            @Override // androidx.fragment.app.FragmentResultListener
            public final /* synthetic */ void onFragmentResult(@NonNull String p02, @NonNull Bundle p12) {
                n.g(p02, "p0");
                n.g(p12, "p1");
                n.f(listener.invoke(p02, p12), "invoke(...)");
            }
        });
    }
}
