package miui.systemui.devicecontrols.controller;

import android.content.ComponentName;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes3.dex */
final class Key {
    private final ComponentName component;
    private final UserHandle user;

    public Key(ComponentName component, UserHandle user) {
        kotlin.jvm.internal.n.g(component, "component");
        kotlin.jvm.internal.n.g(user, "user");
        this.component = component;
        this.user = user;
    }

    public static /* synthetic */ Key copy$default(Key key, ComponentName componentName, UserHandle userHandle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            componentName = key.component;
        }
        if ((i2 & 2) != 0) {
            userHandle = key.user;
        }
        return key.copy(componentName, userHandle);
    }

    public final ComponentName component1() {
        return this.component;
    }

    public final UserHandle component2() {
        return this.user;
    }

    public final Key copy(ComponentName component, UserHandle user) {
        kotlin.jvm.internal.n.g(component, "component");
        kotlin.jvm.internal.n.g(user, "user");
        return new Key(component, user);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Key)) {
            return false;
        }
        Key key = (Key) obj;
        return kotlin.jvm.internal.n.c(this.component, key.component) && kotlin.jvm.internal.n.c(this.user, key.user);
    }

    public final ComponentName getComponent() {
        return this.component;
    }

    public final UserHandle getUser() {
        return this.user;
    }

    public int hashCode() {
        return (this.component.hashCode() * 31) + this.user.hashCode();
    }

    public String toString() {
        return "Key(component=" + this.component + ", user=" + this.user + ")";
    }
}
