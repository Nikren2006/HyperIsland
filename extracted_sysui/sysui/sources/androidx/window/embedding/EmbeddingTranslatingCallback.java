package androidx.window.embedding;

import android.annotation.SuppressLint;
import androidx.window.core.ExperimentalWindowApi;
import androidx.window.embedding.EmbeddingInterfaceCompat;
import java.util.List;
import java.util.function.Consumer;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
@SuppressLint({"NewApi"})
public final class EmbeddingTranslatingCallback implements Consumer<List<? extends androidx.window.extensions.embedding.SplitInfo>> {
    private final EmbeddingAdapter adapter;
    private final EmbeddingInterfaceCompat.EmbeddingCallbackInterface callback;

    public EmbeddingTranslatingCallback(EmbeddingInterfaceCompat.EmbeddingCallbackInterface callback, EmbeddingAdapter adapter) {
        n.g(callback, "callback");
        n.g(adapter, "adapter");
        this.callback = callback;
        this.adapter = adapter;
    }

    @Override // java.util.function.Consumer
    public void accept(List<? extends androidx.window.extensions.embedding.SplitInfo> splitInfoList) {
        n.g(splitInfoList, "splitInfoList");
        this.callback.onSplitInfoChanged(this.adapter.translate(splitInfoList));
    }
}
