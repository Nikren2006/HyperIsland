package miui.systemui.devicecontrols.controller;

import android.content.Context;
import android.os.UserHandle;
import java.io.File;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class UserStructure {
    private final File auxiliaryFile;
    private final File file;
    private final Context sysUIUserContext;
    private final Context userContext;

    public UserStructure(Context sysUIContext, Context context, UserHandle user) {
        kotlin.jvm.internal.n.g(sysUIContext, "sysUIContext");
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(user, "user");
        this.userContext = context.createContextAsUser(user, 0).createDeviceProtectedStorageContext();
        Context contextCreateDeviceProtectedStorageContext = sysUIContext.createContextAsUser(user, 0).createDeviceProtectedStorageContext();
        this.sysUIUserContext = contextCreateDeviceProtectedStorageContext;
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        File filesDir = contextCreateDeviceProtectedStorageContext.getFilesDir();
        kotlin.jvm.internal.n.f(filesDir, "getFilesDir(...)");
        this.file = commonUtils.buildPath(filesDir, ControlsFavoritePersistenceWrapper.FILE_NAME);
        File filesDir2 = contextCreateDeviceProtectedStorageContext.getFilesDir();
        kotlin.jvm.internal.n.f(filesDir2, "getFilesDir(...)");
        this.auxiliaryFile = commonUtils.buildPath(filesDir2, AuxiliaryPersistenceWrapper.AUXILIARY_FILE_NAME);
    }

    public final File getAuxiliaryFile() {
        return this.auxiliaryFile;
    }

    public final File getFile() {
        return this.file;
    }

    public final Context getUserContext() {
        return this.userContext;
    }
}
