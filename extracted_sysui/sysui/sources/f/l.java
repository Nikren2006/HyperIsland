package f;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import k.i;

/* JADX INFO: loaded from: classes.dex */
public class l implements m, j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f4162d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k.i f4164f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f4159a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f4160b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Path f4161c = new Path();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f4163e = new ArrayList();

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4165a;

        static {
            int[] iArr = new int[i.a.values().length];
            f4165a = iArr;
            try {
                iArr[i.a.MERGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4165a[i.a.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4165a[i.a.SUBTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4165a[i.a.INTERSECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4165a[i.a.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public l(k.i iVar) {
        this.f4162d = iVar.c();
        this.f4164f = iVar;
    }

    public final void a() {
        for (int i2 = 0; i2 < this.f4163e.size(); i2++) {
            this.f4161c.addPath(((m) this.f4163e.get(i2)).d());
        }
    }

    @Override // f.c
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < this.f4163e.size(); i2++) {
            ((m) this.f4163e.get(i2)).b(list, list2);
        }
    }

    @Override // f.m
    public Path d() {
        this.f4161c.reset();
        if (this.f4164f.d()) {
            return this.f4161c;
        }
        int i2 = a.f4165a[this.f4164f.b().ordinal()];
        if (i2 == 1) {
            a();
        } else if (i2 == 2) {
            e(Path.Op.UNION);
        } else if (i2 == 3) {
            e(Path.Op.REVERSE_DIFFERENCE);
        } else if (i2 == 4) {
            e(Path.Op.INTERSECT);
        } else if (i2 == 5) {
            e(Path.Op.XOR);
        }
        return this.f4161c;
    }

    public final void e(Path.Op op) {
        this.f4160b.reset();
        this.f4159a.reset();
        for (int size = this.f4163e.size() - 1; size >= 1; size--) {
            m mVar = (m) this.f4163e.get(size);
            if (mVar instanceof d) {
                d dVar = (d) mVar;
                List listK = dVar.k();
                for (int size2 = listK.size() - 1; size2 >= 0; size2--) {
                    Path pathD = ((m) listK.get(size2)).d();
                    pathD.transform(dVar.l());
                    this.f4160b.addPath(pathD);
                }
            } else {
                this.f4160b.addPath(mVar.d());
            }
        }
        m mVar2 = (m) this.f4163e.get(0);
        if (mVar2 instanceof d) {
            d dVar2 = (d) mVar2;
            List listK2 = dVar2.k();
            for (int i2 = 0; i2 < listK2.size(); i2++) {
                Path pathD2 = ((m) listK2.get(i2)).d();
                pathD2.transform(dVar2.l());
                this.f4159a.addPath(pathD2);
            }
        } else {
            this.f4159a.set(mVar2.d());
        }
        this.f4161c.op(this.f4159a, this.f4160b, op);
    }

    @Override // f.j
    public void g(ListIterator listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            c cVar = (c) listIterator.previous();
            if (cVar instanceof m) {
                this.f4163e.add((m) cVar);
                listIterator.remove();
            }
        }
    }
}
