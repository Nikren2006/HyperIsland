package androidx.core.widget;

import H0.s;
import V0.n;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class TextViewKt {

    /* JADX INFO: renamed from: androidx.core.widget.TextViewKt$addTextChangedListener$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements n {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(4);
        }

        public final void invoke(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // V0.n
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((CharSequence) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue());
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.widget.TextViewKt$addTextChangedListener$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements n {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(4);
        }

        public final void invoke(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // V0.n
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((CharSequence) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue());
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.widget.TextViewKt$addTextChangedListener$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements Function1 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final void invoke(Editable editable) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Editable) obj);
            return s.f314a;
        }
    }

    public static final TextWatcher addTextChangedListener(TextView textView, n nVar, n nVar2, Function1 function1) {
        TextViewKt$addTextChangedListener$textWatcher$1 textViewKt$addTextChangedListener$textWatcher$1 = new TextViewKt$addTextChangedListener$textWatcher$1(function1, nVar, nVar2);
        textView.addTextChangedListener(textViewKt$addTextChangedListener$textWatcher$1);
        return textViewKt$addTextChangedListener$textWatcher$1;
    }

    public static /* synthetic */ TextWatcher addTextChangedListener$default(TextView textView, n nVar, n nVar2, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            nVar = AnonymousClass1.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            nVar2 = AnonymousClass2.INSTANCE;
        }
        if ((i2 & 4) != 0) {
            function1 = AnonymousClass3.INSTANCE;
        }
        TextViewKt$addTextChangedListener$textWatcher$1 textViewKt$addTextChangedListener$textWatcher$1 = new TextViewKt$addTextChangedListener$textWatcher$1(function1, nVar, nVar2);
        textView.addTextChangedListener(textViewKt$addTextChangedListener$textWatcher$1);
        return textViewKt$addTextChangedListener$textWatcher$1;
    }

    public static final TextWatcher doAfterTextChanged(TextView textView, final Function1 function1) {
        TextWatcher textWatcher = new TextWatcher() { // from class: androidx.core.widget.TextViewKt$doAfterTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                function1.invoke(editable);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }

    public static final TextWatcher doBeforeTextChanged(TextView textView, final n nVar) {
        TextWatcher textWatcher = new TextWatcher() { // from class: androidx.core.widget.TextViewKt$doBeforeTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                nVar.invoke(charSequence, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }

    public static final TextWatcher doOnTextChanged(TextView textView, final n nVar) {
        TextWatcher textWatcher = new TextWatcher() { // from class: androidx.core.widget.TextViewKt$doOnTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                nVar.invoke(charSequence, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }
}
