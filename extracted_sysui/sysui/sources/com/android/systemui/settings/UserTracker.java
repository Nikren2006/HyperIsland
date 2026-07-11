package com.android.systemui.settings;

import android.content.Context;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public interface UserTracker extends UserContentResolverProvider, UserContextProvider {

    public interface Callback {
        default void onProfilesChanged(List<? extends UserInfo> profiles) {
            n.g(profiles, "profiles");
        }

        default void onUserChanged(int i2, Context userContext) {
            n.g(userContext, "userContext");
        }
    }

    void addCallback(Callback callback, Executor executor);

    UserHandle getUserHandle();

    int getUserId();

    UserInfo getUserInfo();

    List<UserInfo> getUserProfiles();

    void removeCallback(Callback callback);
}
