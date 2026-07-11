package I0;

import java.util.AbstractList;
import java.util.List;

/* JADX INFO: renamed from: I0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0175c extends AbstractList implements List, W0.d {
    public abstract int c();

    public abstract Object d(int i2);

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i2) {
        return d(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return c();
    }
}
