package U;

import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Field f699a;

    public a(Field field) {
        Objects.requireNonNull(field);
        this.f699a = field;
    }

    public String toString() {
        return this.f699a.toString();
    }
}
