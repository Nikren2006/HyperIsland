package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.DialogFragment;
import androidx.mediarouter.media.MediaRouteSelector;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteChooserDialogFragment extends DialogFragment {
    private static final String ARGUMENT_SELECTOR = "selector";
    private Dialog mDialog;
    private MediaRouteSelector mSelector;
    private boolean mUseDynamicGroup = false;

    public MediaRouteChooserDialogFragment() {
        setCancelable(true);
    }

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

    @NonNull
    public MediaRouteSelector getRouteSelector() {
        ensureRouteSelector();
        return this.mSelector;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = this.mDialog;
        if (dialog == null) {
            return;
        }
        if (this.mUseDynamicGroup) {
            ((MediaRouteDynamicChooserDialog) dialog).updateLayout();
        } else {
            ((MediaRouteChooserDialog) dialog).updateLayout();
        }
    }

    @NonNull
    public MediaRouteChooserDialog onCreateChooserDialog(@NonNull Context context, @Nullable Bundle bundle) {
        return new MediaRouteChooserDialog(context);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        if (this.mUseDynamicGroup) {
            MediaRouteDynamicChooserDialog mediaRouteDynamicChooserDialogOnCreateDynamicChooserDialog = onCreateDynamicChooserDialog(getContext());
            this.mDialog = mediaRouteDynamicChooserDialogOnCreateDynamicChooserDialog;
            mediaRouteDynamicChooserDialogOnCreateDynamicChooserDialog.setRouteSelector(getRouteSelector());
        } else {
            MediaRouteChooserDialog mediaRouteChooserDialogOnCreateChooserDialog = onCreateChooserDialog(getContext(), bundle);
            this.mDialog = mediaRouteChooserDialogOnCreateChooserDialog;
            mediaRouteChooserDialogOnCreateChooserDialog.setRouteSelector(getRouteSelector());
        }
        return this.mDialog;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public MediaRouteDynamicChooserDialog onCreateDynamicChooserDialog(@NonNull Context context) {
        return new MediaRouteDynamicChooserDialog(context);
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
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (this.mUseDynamicGroup) {
                ((MediaRouteDynamicChooserDialog) dialog).setRouteSelector(mediaRouteSelector);
            } else {
                ((MediaRouteChooserDialog) dialog).setRouteSelector(mediaRouteSelector);
            }
        }
    }

    public void setUseDynamicGroup(boolean z2) {
        if (this.mDialog != null) {
            throw new IllegalStateException("This must be called before creating dialog");
        }
        this.mUseDynamicGroup = z2;
    }
}
