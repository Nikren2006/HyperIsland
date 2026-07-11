package q;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import java.util.Objects;

/* JADX INFO: renamed from: q.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0731a {
    public static C0165a a(Context context, UserHandle userHandle) {
        return b(context, null, userHandle);
    }

    public static C0165a b(Context context, String str, UserHandle userHandle) {
        DevicePolicyManager devicePolicyManager;
        ComponentName deviceOwnerComponentOnAnyUser;
        if (userHandle == null || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return null;
        }
        try {
            ComponentName profileOwner = ((DevicePolicyManager) context.createPackageContextAsUser(context.getPackageName(), 0, userHandle).getSystemService(DevicePolicyManager.class)).getProfileOwner();
            if (profileOwner != null) {
                return new C0165a(profileOwner, str, userHandle);
            }
            if (!Objects.equals(devicePolicyManager.getDeviceOwnerUser(), userHandle) || (deviceOwnerComponentOnAnyUser = devicePolicyManager.getDeviceOwnerComponentOnAnyUser()) == null) {
                return null;
            }
            return new C0165a(deviceOwnerComponentOnAnyUser, str, userHandle);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Intent c(Context context, C0165a c0165a) {
        Intent intent = new Intent("android.settings.SHOW_ADMIN_SUPPORT_DETAILS");
        if (c0165a != null) {
            ComponentName componentName = c0165a.f6414a;
            if (componentName != null) {
                intent.putExtra("android.app.extra.DEVICE_ADMIN", componentName);
            }
            intent.putExtra("android.intent.extra.USER", c0165a.f6416c);
        }
        return intent;
    }

    /* JADX INFO: renamed from: q.a$a, reason: collision with other inner class name */
    public static class C0165a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final C0165a f6413d = new C0165a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ComponentName f6414a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f6415b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public UserHandle f6416c;

        public C0165a(ComponentName componentName, String str, UserHandle userHandle) {
            this.f6414a = componentName;
            this.f6415b = str;
            this.f6416c = userHandle;
        }

        public static C0165a a(String str) {
            C0165a c0165a = new C0165a();
            c0165a.f6415b = str;
            return c0165a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0165a c0165a = (C0165a) obj;
            return Objects.equals(this.f6416c, c0165a.f6416c) && Objects.equals(this.f6414a, c0165a.f6414a) && Objects.equals(this.f6415b, c0165a.f6415b);
        }

        public int hashCode() {
            return Objects.hash(this.f6414a, this.f6415b, this.f6416c);
        }

        public String toString() {
            return "EnforcedAdmin{component=" + this.f6414a + ", enforcedRestriction='" + this.f6415b + ", user=" + this.f6416c + '}';
        }

        public C0165a() {
            this.f6414a = null;
            this.f6415b = null;
            this.f6416c = null;
        }
    }
}
