package j;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class n implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4635a;

    public n(List list) {
        this.f4635a = list;
    }

    @Override // j.m
    public boolean b() {
        if (this.f4635a.isEmpty()) {
            return true;
        }
        return this.f4635a.size() == 1 && ((com.airbnb.lottie.value.a) this.f4635a.get(0)).h();
    }

    @Override // j.m
    public List getKeyframes() {
        return this.f4635a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f4635a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f4635a.toArray()));
        }
        return sb.toString();
    }
}
