package androidx.activity;

import android.view.View;
import e1.AbstractC0345j;
import e1.l;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeOnBackPressedDispatcherOwner {
    public static final OnBackPressedDispatcherOwner get(View view) {
        n.g(view, "<this>");
        return (OnBackPressedDispatcherOwner) l.m(l.p(AbstractC0345j.e(view, ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$1.INSTANCE), ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$2.INSTANCE));
    }

    public static final void set(View view, OnBackPressedDispatcherOwner onBackPressedDispatcherOwner) {
        n.g(view, "<this>");
        n.g(onBackPressedDispatcherOwner, "onBackPressedDispatcherOwner");
        view.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, onBackPressedDispatcherOwner);
    }
}
