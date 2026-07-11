package miui.systemui.settings;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.internal.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import miui.systemui.broadcast.BroadcastDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CurrentUserTracker {
    private Consumer<Integer> mCallback;
    private final UserReceiver mUserReceiver;

    @VisibleForTesting
    public static class UserReceiver extends BroadcastReceiver {
        private static UserReceiver sInstance;
        private final BroadcastDispatcher mBroadcastDispatcher;
        private List<Consumer<Integer>> mCallbacks = new ArrayList();
        private int mCurrentUserId;
        private boolean mReceiverRegistered;

        @VisibleForTesting
        public UserReceiver(BroadcastDispatcher broadcastDispatcher) {
            this.mBroadcastDispatcher = broadcastDispatcher;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addTracker(Consumer<Integer> consumer) {
            if (!this.mCallbacks.contains(consumer)) {
                this.mCallbacks.add(consumer);
            }
            if (this.mReceiverRegistered) {
                return;
            }
            this.mCurrentUserId = ActivityManager.getCurrentUser();
            this.mBroadcastDispatcher.registerReceiver(this, new IntentFilter("android.intent.action.USER_SWITCHED"), null, UserHandle.ALL);
            this.mReceiverRegistered = true;
        }

        public static UserReceiver getInstance(BroadcastDispatcher broadcastDispatcher) {
            if (sInstance == null) {
                sInstance = new UserReceiver(broadcastDispatcher);
            }
            return sInstance;
        }

        private void notifyUserSwitched(int i2) {
            if (this.mCurrentUserId != i2) {
                this.mCurrentUserId = i2;
                for (Consumer consumer : new ArrayList(this.mCallbacks)) {
                    if (this.mCallbacks.contains(consumer)) {
                        consumer.accept(Integer.valueOf(i2));
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeTracker(Consumer<Integer> consumer) {
            if (this.mCallbacks.contains(consumer)) {
                this.mCallbacks.remove(consumer);
                if (this.mCallbacks.size() == 0 && this.mReceiverRegistered) {
                    this.mBroadcastDispatcher.unregisterReceiver(this);
                    this.mReceiverRegistered = false;
                }
            }
        }

        public int getCurrentUserId() {
            return this.mCurrentUserId;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                notifyUserSwitched(intent.getIntExtra("android.intent.extra.user_handle", 0));
            }
        }
    }

    public CurrentUserTracker(BroadcastDispatcher broadcastDispatcher) {
        this(UserReceiver.getInstance(broadcastDispatcher));
    }

    public int getCurrentUserId() {
        return this.mUserReceiver.getCurrentUserId();
    }

    public abstract void onUserSwitched(int i2);

    public void startTracking() {
        this.mUserReceiver.addTracker(this.mCallback);
    }

    public void stopTracking() {
        this.mUserReceiver.removeTracker(this.mCallback);
    }

    @VisibleForTesting
    public CurrentUserTracker(UserReceiver userReceiver) {
        this.mCallback = new Consumer() { // from class: miui.systemui.settings.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f5889a.onUserSwitched(((Integer) obj).intValue());
            }
        };
        this.mUserReceiver = userReceiver;
    }
}
