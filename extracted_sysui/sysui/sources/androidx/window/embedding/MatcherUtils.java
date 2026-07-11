package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.window.core.ExperimentalWindowApi;
import f1.o;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
public final class MatcherUtils {
    public static final MatcherUtils INSTANCE = new MatcherUtils();
    public static final boolean sDebugMatchers = false;
    public static final String sMatchersTag = "SplitRuleResolution";

    private MatcherUtils() {
    }

    private final boolean wildcardMatch(String str, String str2) {
        if (!o.v(str2, "*", false, 2, null)) {
            return false;
        }
        if (n.c(str2, "*")) {
            return true;
        }
        if (o.E(str2, "*", 0, false, 6, null) != o.J(str2, "*", 0, false, 6, null) || !f1.n.k(str2, "*", false, 2, null)) {
            throw new IllegalArgumentException("Name pattern with a wildcard must only contain a single wildcard in the end");
        }
        String strSubstring = str2.substring(0, str2.length() - 1);
        n.f(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return f1.n.s(str, strSubstring, false, 2, null);
    }

    public final boolean areActivityOrIntentComponentsMatching$window_release(Activity activity, ComponentName ruleComponent) {
        ComponentName component;
        n.g(activity, "activity");
        n.g(ruleComponent, "ruleComponent");
        if (areComponentsMatching$window_release(activity.getComponentName(), ruleComponent)) {
            return true;
        }
        Intent intent = activity.getIntent();
        if (intent == null || (component = intent.getComponent()) == null) {
            return false;
        }
        return INSTANCE.areComponentsMatching$window_release(component, ruleComponent);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean areComponentsMatching$window_release(android.content.ComponentName r7, android.content.ComponentName r8) {
        /*
            r6 = this;
            java.lang.String r0 = "ruleComponent"
            kotlin.jvm.internal.n.g(r8, r0)
            java.lang.String r0 = "*"
            r1 = 1
            r2 = 0
            if (r7 != 0) goto L22
            java.lang.String r6 = r8.getPackageName()
            boolean r6 = kotlin.jvm.internal.n.c(r6, r0)
            if (r6 == 0) goto L20
            java.lang.String r6 = r8.getClassName()
            boolean r6 = kotlin.jvm.internal.n.c(r6, r0)
            if (r6 == 0) goto L20
            goto L21
        L20:
            r1 = r2
        L21:
            return r1
        L22:
            java.lang.String r3 = r7.toString()
            java.lang.String r4 = "activityComponent.toString()"
            kotlin.jvm.internal.n.f(r3, r4)
            r4 = 2
            r5 = 0
            boolean r0 = f1.o.v(r3, r0, r2, r4, r5)
            if (r0 != 0) goto L8e
            java.lang.String r0 = r7.getPackageName()
            java.lang.String r3 = r8.getPackageName()
            boolean r0 = kotlin.jvm.internal.n.c(r0, r3)
            if (r0 != 0) goto L5c
            java.lang.String r0 = r7.getPackageName()
            java.lang.String r3 = "activityComponent.packageName"
            kotlin.jvm.internal.n.f(r0, r3)
            java.lang.String r3 = r8.getPackageName()
            java.lang.String r4 = "ruleComponent.packageName"
            kotlin.jvm.internal.n.f(r3, r4)
            boolean r0 = r6.wildcardMatch(r0, r3)
            if (r0 == 0) goto L5a
            goto L5c
        L5a:
            r0 = r2
            goto L5d
        L5c:
            r0 = r1
        L5d:
            java.lang.String r3 = r7.getClassName()
            java.lang.String r4 = r8.getClassName()
            boolean r3 = kotlin.jvm.internal.n.c(r3, r4)
            if (r3 != 0) goto L86
            java.lang.String r7 = r7.getClassName()
            java.lang.String r3 = "activityComponent.className"
            kotlin.jvm.internal.n.f(r7, r3)
            java.lang.String r8 = r8.getClassName()
            java.lang.String r3 = "ruleComponent.className"
            kotlin.jvm.internal.n.f(r8, r3)
            boolean r6 = r6.wildcardMatch(r7, r8)
            if (r6 == 0) goto L84
            goto L86
        L84:
            r6 = r2
            goto L87
        L86:
            r6 = r1
        L87:
            if (r0 == 0) goto L8c
            if (r6 == 0) goto L8c
            goto L8d
        L8c:
            r1 = r2
        L8d:
            return r1
        L8e:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Wildcard can only be part of the rule."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.MatcherUtils.areComponentsMatching$window_release(android.content.ComponentName, android.content.ComponentName):boolean");
    }
}
