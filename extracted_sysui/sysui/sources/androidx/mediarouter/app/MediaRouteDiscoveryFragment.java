package androidx.mediarouter.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteDiscoveryFragment extends Fragment {
    private static final String ARGUMENT_SELECTOR = "selector";
    private MediaRouter.Callback mCallback;
    private MediaRouter mRouter;
    private MediaRouteSelector mSelector;

    private void ensureRouteSelector() {
        if (this.mSelector == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.mSelector = MediaRouteSelector.fromBundle(arguments.getBundle(ARGUMENT_SELECTOR));
            }
            if (this.mSelector == null) {
                this.mSelector = MediaRouteSelector.EMPTY;
            }
        }
    }

    private void ensureRouter() {
        if (this.mRouter == null) {
            this.mRouter = MediaRouter.getInstance(getContext());
        }
    }

    @NonNull
    public MediaRouter getMediaRouter() {
        ensureRouter();
        return this.mRouter;
    }

    @NonNull
    public MediaRouteSelector getRouteSelector() {
        ensureRouteSelector();
        return this.mSelector;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ensureRouteSelector();
        ensureRouter();
        MediaRouter.Callback callbackOnCreateCallback = onCreateCallback();
        this.mCallback = callbackOnCreateCallback;
        if (callbackOnCreateCallback != null) {
            this.mRouter.addCallback(this.mSelector, callbackOnCreateCallback, 0);
        }
    }

    @Nullable
    public MediaRouter.Callback onCreateCallback() {
        return new MediaRouter.Callback() { // from class: androidx.mediarouter.app.MediaRouteDiscoveryFragment.1
        };
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        MediaRouter.Callback callback = this.mCallback;
        if (callback != null) {
            this.mRouter.removeCallback(callback);
        }
        super.onDestroy();
    }

    public int onPrepareCallbackFlags() {
        return 4;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        MediaRouter.Callback callback = this.mCallback;
        if (callback != null) {
            this.mRouter.addCallback(this.mSelector, callback, onPrepareCallbackFlags());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        MediaRouter.Callback callback = this.mCallback;
        if (callback != null) {
            this.mRouter.addCallback(this.mSelector, callback, 0);
        }
        super.onStop();
    }

    public void setRouteSelector(@NonNull MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        ensureRouteSelector();
        if (this.mSelector.equals(mediaRouteSelector)) {
            return;
        }
        this.mSelector = mediaRouteSelector;
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putBundle(ARGUMENT_SELECTOR, mediaRouteSelector.asBundle());
        setArguments(arguments);
        MediaRouter.Callback callback = this.mCallback;
        if (callback != null) {
            this.mRouter.removeCallback(callback);
            this.mRouter.addCallback(this.mSelector, this.mCallback, onPrepareCallbackFlags());
        }
    }
}
