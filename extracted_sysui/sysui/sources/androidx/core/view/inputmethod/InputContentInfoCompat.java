package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class InputContentInfoCompat {
    private final InputContentInfoCompatImpl mImpl;

    public static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
        private final Uri mContentUri;
        private final ClipDescription mDescription;
        private final Uri mLinkUri;

        public InputContentInfoCompatBaseImpl(Uri uri, ClipDescription clipDescription, Uri uri2) {
            this.mContentUri = uri;
            this.mDescription = clipDescription;
            this.mLinkUri = uri2;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Object getInputContentInfo() {
            return null;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void releasePermission() {
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
        }
    }

    public interface InputContentInfoCompatImpl {
        Uri getContentUri();

        ClipDescription getDescription();

        Object getInputContentInfo();

        Uri getLinkUri();

        void releasePermission();

        void requestPermission();
    }

    public InputContentInfoCompat(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.mImpl = new InputContentInfoCompatApi25Impl(uri, clipDescription, uri2);
    }

    public static InputContentInfoCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(obj));
    }

    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    public void releasePermission() {
        this.mImpl.releasePermission();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    @RequiresApi(25)
    public static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        final InputContentInfo mObject;

        public InputContentInfoCompatApi25Impl(Object obj) {
            this.mObject = (InputContentInfo) obj;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Object getInputContentInfo() {
            return this.mObject;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void releasePermission() {
            this.mObject.releasePermission();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
            this.mObject.requestPermission();
        }

        public InputContentInfoCompatApi25Impl(Uri uri, ClipDescription clipDescription, Uri uri2) {
            this.mObject = new InputContentInfo(uri, clipDescription, uri2);
        }
    }

    private InputContentInfoCompat(InputContentInfoCompatImpl inputContentInfoCompatImpl) {
        this.mImpl = inputContentInfoCompatImpl;
    }
}
