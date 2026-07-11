package androidx.mediarouter.media;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouterParams {
    public static final int DIALOG_TYPE_DEFAULT = 1;
    public static final int DIALOG_TYPE_DYNAMIC_GROUP = 2;
    public static final String ENABLE_GROUP_VOLUME_UX = "androidx.mediarouter.media.MediaRouterParams.ENABLE_GROUP_VOLUME_UX";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String EXTRAS_KEY_FIXED_CAST_ICON = "androidx.mediarouter.media.MediaRouterParams.FIXED_CAST_ICON";
    final int mDialogType;
    final Bundle mExtras;
    final boolean mMediaTransferReceiverEnabled;
    final boolean mOutputSwitcherEnabled;
    final boolean mTransferToLocalEnabled;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface DialogType {
    }

    public MediaRouterParams(@NonNull Builder builder) {
        this.mDialogType = builder.mDialogType;
        this.mMediaTransferReceiverEnabled = builder.mMediaTransferEnabled;
        this.mOutputSwitcherEnabled = builder.mOutputSwitcherEnabled;
        this.mTransferToLocalEnabled = builder.mTransferToLocalEnabled;
        Bundle bundle = builder.mExtras;
        this.mExtras = bundle == null ? Bundle.EMPTY : new Bundle(bundle);
    }

    public int getDialogType() {
        return this.mDialogType;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Bundle getExtras() {
        return this.mExtras;
    }

    public boolean isMediaTransferReceiverEnabled() {
        return this.mMediaTransferReceiverEnabled;
    }

    public boolean isOutputSwitcherEnabled() {
        return this.mOutputSwitcherEnabled;
    }

    public boolean isTransferToLocalEnabled() {
        return this.mTransferToLocalEnabled;
    }

    public static final class Builder {
        int mDialogType;
        Bundle mExtras;
        boolean mMediaTransferEnabled;
        boolean mOutputSwitcherEnabled;
        boolean mTransferToLocalEnabled;

        public Builder() {
            this.mDialogType = 1;
            this.mMediaTransferEnabled = true;
        }

        @NonNull
        public MediaRouterParams build() {
            return new MediaRouterParams(this);
        }

        @NonNull
        public Builder setDialogType(int i2) {
            this.mDialogType = i2;
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public Builder setExtras(@Nullable Bundle bundle) {
            this.mExtras = bundle == null ? null : new Bundle(bundle);
            return this;
        }

        @NonNull
        public Builder setMediaTransferReceiverEnabled(boolean z2) {
            this.mMediaTransferEnabled = z2;
            return this;
        }

        @NonNull
        public Builder setOutputSwitcherEnabled(boolean z2) {
            this.mOutputSwitcherEnabled = z2;
            return this;
        }

        @NonNull
        public Builder setTransferToLocalEnabled(boolean z2) {
            this.mTransferToLocalEnabled = z2;
            return this;
        }

        public Builder(@NonNull MediaRouterParams mediaRouterParams) {
            this.mDialogType = 1;
            this.mMediaTransferEnabled = true;
            if (mediaRouterParams != null) {
                this.mDialogType = mediaRouterParams.mDialogType;
                this.mOutputSwitcherEnabled = mediaRouterParams.mOutputSwitcherEnabled;
                this.mTransferToLocalEnabled = mediaRouterParams.mTransferToLocalEnabled;
                this.mMediaTransferEnabled = mediaRouterParams.mMediaTransferReceiverEnabled;
                this.mExtras = mediaRouterParams.mExtras == null ? null : new Bundle(mediaRouterParams.mExtras);
                return;
            }
            throw new NullPointerException("params should not be null!");
        }
    }
}
