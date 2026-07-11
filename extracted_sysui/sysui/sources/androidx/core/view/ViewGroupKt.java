package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Px;
import c1.C0232d;
import e1.InterfaceC0340e;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
public final class ViewGroupKt {

    /* JADX INFO: renamed from: androidx.core.view.ViewGroupKt$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<View>, W0.a {
        final /* synthetic */ ViewGroup $this_iterator;
        private int index;

        public AnonymousClass1(ViewGroup viewGroup) {
            this.$this_iterator = viewGroup;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.$this_iterator.getChildCount();
        }

        @Override // java.util.Iterator
        public void remove() {
            ViewGroup viewGroup = this.$this_iterator;
            int i2 = this.index - 1;
            this.index = i2;
            viewGroup.removeViewAt(i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public View next() {
            ViewGroup viewGroup = this.$this_iterator;
            int i2 = this.index;
            this.index = i2 + 1;
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != null) {
                return childAt;
            }
            throw new IndexOutOfBoundsException();
        }
    }

    public static final boolean contains(ViewGroup viewGroup, View view) {
        return viewGroup.indexOfChild(view) != -1;
    }

    public static final void forEach(ViewGroup viewGroup, Function1 function1) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            function1.invoke(viewGroup.getChildAt(i2));
        }
    }

    public static final void forEachIndexed(ViewGroup viewGroup, Function2 function2) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            function2.invoke(Integer.valueOf(i2), viewGroup.getChildAt(i2));
        }
    }

    public static final View get(ViewGroup viewGroup, int i2) {
        View childAt = viewGroup.getChildAt(i2);
        if (childAt != null) {
            return childAt;
        }
        throw new IndexOutOfBoundsException("Index: " + i2 + ", Size: " + viewGroup.getChildCount());
    }

    public static final InterfaceC0340e getChildren(final ViewGroup viewGroup) {
        return new InterfaceC0340e() { // from class: androidx.core.view.ViewGroupKt$children$1
            @Override // e1.InterfaceC0340e
            public Iterator<View> iterator() {
                return ViewGroupKt.iterator(viewGroup);
            }
        };
    }

    public static final InterfaceC0340e getDescendants(final ViewGroup viewGroup) {
        return new InterfaceC0340e() { // from class: androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1
            @Override // e1.InterfaceC0340e
            public Iterator<View> iterator() {
                return new TreeIterator(ViewGroupKt.getChildren(viewGroup).iterator(), ViewGroupKt$descendants$1$1.INSTANCE);
            }
        };
    }

    public static final C0232d getIndices(ViewGroup viewGroup) {
        return c1.f.l(0, viewGroup.getChildCount());
    }

    public static final int getSize(ViewGroup viewGroup) {
        return viewGroup.getChildCount();
    }

    public static final boolean isEmpty(ViewGroup viewGroup) {
        return viewGroup.getChildCount() == 0;
    }

    public static final boolean isNotEmpty(ViewGroup viewGroup) {
        return viewGroup.getChildCount() != 0;
    }

    public static final Iterator<View> iterator(ViewGroup viewGroup) {
        return new AnonymousClass1(viewGroup);
    }

    public static final void minusAssign(ViewGroup viewGroup, View view) {
        viewGroup.removeView(view);
    }

    public static final void plusAssign(ViewGroup viewGroup, View view) {
        viewGroup.addView(view);
    }

    public static final void setMargins(ViewGroup.MarginLayoutParams marginLayoutParams, @Px int i2) {
        marginLayoutParams.setMargins(i2, i2, i2, i2);
    }

    public static final void updateMargins(ViewGroup.MarginLayoutParams marginLayoutParams, @Px int i2, @Px int i3, @Px int i4, @Px int i5) {
        marginLayoutParams.setMargins(i2, i3, i4, i5);
    }

    public static /* synthetic */ void updateMargins$default(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = marginLayoutParams.leftMargin;
        }
        if ((i6 & 2) != 0) {
            i3 = marginLayoutParams.topMargin;
        }
        if ((i6 & 4) != 0) {
            i4 = marginLayoutParams.rightMargin;
        }
        if ((i6 & 8) != 0) {
            i5 = marginLayoutParams.bottomMargin;
        }
        marginLayoutParams.setMargins(i2, i3, i4, i5);
    }

    public static final void updateMarginsRelative(ViewGroup.MarginLayoutParams marginLayoutParams, @Px int i2, @Px int i3, @Px int i4, @Px int i5) {
        marginLayoutParams.setMarginStart(i2);
        marginLayoutParams.topMargin = i3;
        marginLayoutParams.setMarginEnd(i4);
        marginLayoutParams.bottomMargin = i5;
    }

    public static /* synthetic */ void updateMarginsRelative$default(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = marginLayoutParams.getMarginStart();
        }
        if ((i6 & 2) != 0) {
            i3 = marginLayoutParams.topMargin;
        }
        if ((i6 & 4) != 0) {
            i4 = marginLayoutParams.getMarginEnd();
        }
        if ((i6 & 8) != 0) {
            i5 = marginLayoutParams.bottomMargin;
        }
        marginLayoutParams.setMarginStart(i2);
        marginLayoutParams.topMargin = i3;
        marginLayoutParams.setMarginEnd(i4);
        marginLayoutParams.bottomMargin = i5;
    }
}
