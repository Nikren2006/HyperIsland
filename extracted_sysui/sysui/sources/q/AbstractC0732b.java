package q;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import java.util.List;
import q.AbstractC0731a;

/* JADX INFO: renamed from: q.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0732b extends AbstractC0731a {
    public static AbstractC0731a.C0165a d(Context context, String str, int i2) {
        return e(context, str, i2);
    }

    public static AbstractC0731a.C0165a e(Context context, String str, int i2) {
        if (((DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return null;
        }
        UserManager userManager = UserManager.get(context);
        UserHandle userHandleOf = UserHandle.of(i2);
        List userRestrictionSources = userManager.getUserRestrictionSources(str, userHandleOf);
        if (userRestrictionSources.isEmpty()) {
            return null;
        }
        if (userRestrictionSources.size() > 1) {
            AbstractC0731a.C0165a c0165aA = AbstractC0731a.C0165a.a(str);
            c0165aA.f6416c = userHandleOf;
            return c0165aA;
        }
        UserManager.EnforcingUser enforcingUser = (UserManager.EnforcingUser) userRestrictionSources.get(0);
        if (enforcingUser.getUserRestrictionSource() == 1) {
            return null;
        }
        AbstractC0731a.C0165a c0165aA2 = AbstractC0731a.a(context, enforcingUser.getUserHandle());
        return c0165aA2 != null ? c0165aA2 : AbstractC0731a.C0165a.a(str);
    }
}
