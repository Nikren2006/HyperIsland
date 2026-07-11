package miuix.appcompat.app;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import androidx.annotation.Nullable;
import miui.securityspace.CrossUserUtils;
import miuix.core.compat.ContextCompat;
import miuix.core.compat.UserHandleCompat;
import miuix.core.util.PackageHelper;

/* JADX INFO: loaded from: classes2.dex */
public class CrossUserPickerActivity extends AppCompatActivity {
    private static final String EXTRA_PICKED_USER_ID = "android.intent.extra.picked_user_id";
    private static final String TAG = "CrossUserPickerActivity";
    public static final int USER_ID_INVALID = -1;
    private volatile ContentResolver mCrossUserContentResolver;
    private volatile ContextWrapper mCrossUserContextWrapper;
    private final Object mLockObject = new Object();

    public class CrossUserContextWrapper extends ContextWrapper {
        Context mBase;
        UserHandle mCrossUser;

        public CrossUserContextWrapper(Context context, UserHandle userHandle) {
            super(context);
            this.mBase = context;
            this.mCrossUser = userHandle;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public ContentResolver getContentResolver() {
            return ContextCompat.getContentResolverForUser(this.mBase, this.mCrossUser);
        }
    }

    private boolean validateCallingPackage() {
        return getPackageName().equals(getCallingPackage()) || CrossUserUtils.checkUidPermission(this, getCallingPackage());
    }

    private int validateCrossUser() {
        if (getIntent() == null) {
            return -1;
        }
        int intExtra = getIntent().getIntExtra(EXTRA_PICKED_USER_ID, -1);
        if (validateCallingPackage()) {
            return intExtra;
        }
        return -1;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        if (!isCrossUserPick() || !PackageHelper.isMiuiSystem()) {
            Log.d(TAG, "getApplicationContext: NormalApplication");
            return super.getApplicationContext();
        }
        if (this.mCrossUserContextWrapper == null) {
            synchronized (this.mLockObject) {
                try {
                    if (this.mCrossUserContextWrapper == null) {
                        this.mCrossUserContextWrapper = new CrossUserContextWrapper(super.getApplicationContext(), UserHandleCompat.createNew(validateCrossUser()));
                    }
                } finally {
                }
            }
        }
        Log.d(TAG, "getApplicationContext: WrapperedApplication");
        return this.mCrossUserContextWrapper;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ContentResolver getContentResolver() {
        if (!isCrossUserPick() || !PackageHelper.isMiuiSystem()) {
            Log.d(TAG, "getContentResolver: NormalContentResolver");
            return super.getContentResolver();
        }
        if (this.mCrossUserContentResolver == null) {
            synchronized (this.mLockObject) {
                try {
                    if (this.mCrossUserContentResolver == null) {
                        this.mCrossUserContentResolver = ContextCompat.getContentResolverForUser(this, UserHandleCompat.createNew(validateCrossUser()));
                    }
                } finally {
                }
            }
        }
        Log.d(TAG, "getContentResolver: CrossUserContentResolver");
        return this.mCrossUserContentResolver;
    }

    public boolean isCrossUserPick() {
        return validateCrossUser() != -1;
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        if (isCrossUserPick()) {
            intent.putExtra(EXTRA_PICKED_USER_ID, validateCrossUser());
        }
        super.startActivity(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i2) {
        if (isCrossUserPick()) {
            intent.putExtra(EXTRA_PICKED_USER_ID, validateCrossUser());
        }
        super.startActivityForResult(intent, i2);
    }

    @Override // android.app.Activity
    public void startActivityFromFragment(android.app.Fragment fragment, Intent intent, int i2, Bundle bundle) {
        if (isCrossUserPick()) {
            intent.putExtra(EXTRA_PICKED_USER_ID, validateCrossUser());
        }
        super.startActivityFromFragment(fragment, intent, i2, bundle);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, @Nullable Bundle bundle) {
        if (isCrossUserPick()) {
            intent.putExtra(EXTRA_PICKED_USER_ID, validateCrossUser());
        }
        super.startActivity(intent, bundle);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i2, @Nullable Bundle bundle) {
        if (isCrossUserPick()) {
            intent.putExtra(EXTRA_PICKED_USER_ID, validateCrossUser());
        }
        super.startActivityForResult(intent, i2, bundle);
    }
}
