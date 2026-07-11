package androidx.core.widget;

import V0.n;
import android.text.Editable;
import android.text.TextWatcher;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class TextViewKt$addTextChangedListener$textWatcher$1 implements TextWatcher {
    final /* synthetic */ Function1 $afterTextChanged;
    final /* synthetic */ n $beforeTextChanged;
    final /* synthetic */ n $onTextChanged;

    public TextViewKt$addTextChangedListener$textWatcher$1(Function1 function1, n nVar, n nVar2) {
        this.$afterTextChanged = function1;
        this.$beforeTextChanged = nVar;
        this.$onTextChanged = nVar2;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        this.$afterTextChanged.invoke(editable);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.$beforeTextChanged.invoke(charSequence, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.$onTextChanged.invoke(charSequence, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
    }
}
