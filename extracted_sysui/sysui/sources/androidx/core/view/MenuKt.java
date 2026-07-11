package androidx.core.view;

import H0.s;
import android.view.Menu;
import android.view.MenuItem;
import e1.InterfaceC0340e;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
public final class MenuKt {

    /* JADX INFO: renamed from: androidx.core.view.MenuKt$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<MenuItem>, W0.a {
        final /* synthetic */ Menu $this_iterator;
        private int index;

        public AnonymousClass1(Menu menu) {
            this.$this_iterator = menu;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.$this_iterator.size();
        }

        @Override // java.util.Iterator
        public void remove() {
            s sVar;
            Menu menu = this.$this_iterator;
            int i2 = this.index - 1;
            this.index = i2;
            MenuItem item = menu.getItem(i2);
            if (item != null) {
                menu.removeItem(item.getItemId());
                sVar = s.f314a;
            } else {
                sVar = null;
            }
            if (sVar == null) {
                throw new IndexOutOfBoundsException();
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public MenuItem next() {
            Menu menu = this.$this_iterator;
            int i2 = this.index;
            this.index = i2 + 1;
            MenuItem item = menu.getItem(i2);
            if (item != null) {
                return item;
            }
            throw new IndexOutOfBoundsException();
        }
    }

    public static final boolean contains(Menu menu, MenuItem menuItem) {
        int size = menu.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (kotlin.jvm.internal.n.c(menu.getItem(i2), menuItem)) {
                return true;
            }
        }
        return false;
    }

    public static final void forEach(Menu menu, Function1 function1) {
        int size = menu.size();
        for (int i2 = 0; i2 < size; i2++) {
            function1.invoke(menu.getItem(i2));
        }
    }

    public static final void forEachIndexed(Menu menu, Function2 function2) {
        int size = menu.size();
        for (int i2 = 0; i2 < size; i2++) {
            function2.invoke(Integer.valueOf(i2), menu.getItem(i2));
        }
    }

    public static final MenuItem get(Menu menu, int i2) {
        return menu.getItem(i2);
    }

    public static final InterfaceC0340e getChildren(final Menu menu) {
        return new InterfaceC0340e() { // from class: androidx.core.view.MenuKt$children$1
            @Override // e1.InterfaceC0340e
            public Iterator<MenuItem> iterator() {
                return MenuKt.iterator(menu);
            }
        };
    }

    public static final int getSize(Menu menu) {
        return menu.size();
    }

    public static final boolean isEmpty(Menu menu) {
        return menu.size() == 0;
    }

    public static final boolean isNotEmpty(Menu menu) {
        return menu.size() != 0;
    }

    public static final Iterator<MenuItem> iterator(Menu menu) {
        return new AnonymousClass1(menu);
    }

    public static final void minusAssign(Menu menu, MenuItem menuItem) {
        menu.removeItem(menuItem.getItemId());
    }

    public static final void removeItemAt(Menu menu, int i2) {
        s sVar;
        MenuItem item = menu.getItem(i2);
        if (item != null) {
            menu.removeItem(item.getItemId());
            sVar = s.f314a;
        } else {
            sVar = null;
        }
        if (sVar == null) {
            throw new IndexOutOfBoundsException();
        }
    }
}
