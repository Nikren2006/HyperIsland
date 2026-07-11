package miui.systemui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import miui.systemui.broadcast.BroadcastDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public class CurrentUserObservable {
    private final MutableLiveData<Integer> mCurrentUser = new MutableLiveData<Integer>() { // from class: miui.systemui.settings.CurrentUserObservable.1
        @Override // androidx.lifecycle.LiveData
        public void onActive() {
            super.onActive();
            CurrentUserObservable.this.mTracker.startTracking();
        }

        @Override // androidx.lifecycle.LiveData
        public void onInactive() {
            super.onInactive();
            CurrentUserObservable.this.mTracker.stopTracking();
        }
    };
    private final CurrentUserTracker mTracker;

    public CurrentUserObservable(BroadcastDispatcher broadcastDispatcher) {
        this.mTracker = new CurrentUserTracker(broadcastDispatcher) { // from class: miui.systemui.settings.CurrentUserObservable.2
            @Override // miui.systemui.settings.CurrentUserTracker
            public void onUserSwitched(int i2) {
                CurrentUserObservable.this.mCurrentUser.setValue(Integer.valueOf(i2));
            }
        };
    }

    public LiveData<Integer> getCurrentUser() {
        if (this.mCurrentUser.getValue() == null) {
            this.mCurrentUser.setValue(Integer.valueOf(this.mTracker.getCurrentUserId()));
        }
        return this.mCurrentUser;
    }

    public int getCurrentUserId() {
        return getCurrentUser().getValue().intValue();
    }
}
