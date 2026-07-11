package miui.systemui.devicecontrols.controller;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class AuxiliaryPersistenceWrapper {
    public static final String AUXILIARY_FILE_NAME = "aux_controls_favorites.xml";
    public static final Companion Companion = new Companion(null);
    private List<StructureInfo> favorites;
    private ControlsFavoritePersistenceWrapper persistenceWrapper;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class DeletionJobService extends JobService {
        public static final Companion Companion = new Companion(null);
        private static final int DELETE_FILE_JOB_ID = 1000;
        private static final long WEEK_IN_MILLIS = TimeUnit.DAYS.toMillis(7);

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @VisibleForTesting
            public static /* synthetic */ void getDELETE_FILE_JOB_ID$miui_devicecontrols_release$annotations() {
            }

            public final int getDELETE_FILE_JOB_ID$miui_devicecontrols_release() {
                return DeletionJobService.DELETE_FILE_JOB_ID;
            }

            public final JobInfo getJobForContext(Context context) {
                kotlin.jvm.internal.n.g(context, "context");
                JobInfo jobInfoBuild = new JobInfo.Builder(getDELETE_FILE_JOB_ID$miui_devicecontrols_release() + context.getUserId(), new ComponentName(context, (Class<?>) DeletionJobService.class)).setMinimumLatency(DeletionJobService.WEEK_IN_MILLIS).setPersisted(true).build();
                kotlin.jvm.internal.n.f(jobInfoBuild, "build(...)");
                return jobInfoBuild;
            }

            private Companion() {
            }
        }

        @VisibleForTesting
        public final void attachContext(Context context) {
            kotlin.jvm.internal.n.g(context, "context");
            attachBaseContext(context);
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters params) {
            kotlin.jvm.internal.n.g(params, "params");
            return false;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            return true;
        }
    }

    @VisibleForTesting
    public AuxiliaryPersistenceWrapper(ControlsFavoritePersistenceWrapper wrapper) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        this.persistenceWrapper = wrapper;
        this.favorites = I0.m.h();
        initialize();
    }

    public final void changeFile(File file) {
        kotlin.jvm.internal.n.g(file, "file");
        this.persistenceWrapper.changeFileAndBackupManager(file, null);
        initialize();
    }

    public final List<StructureInfo> getCachedFavoritesAndRemoveFor(ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        if (!this.persistenceWrapper.getFileExists()) {
            return I0.m.h();
        }
        List<StructureInfo> list = this.favorites;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list) {
            if (kotlin.jvm.internal.n.c(((StructureInfo) obj).getComponentName(), componentName)) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        H0.i iVar = new H0.i(arrayList, arrayList2);
        List<StructureInfo> list2 = (List) iVar.a();
        List<StructureInfo> list3 = (List) iVar.b();
        this.favorites = list3;
        if (list3.isEmpty()) {
            this.persistenceWrapper.deleteFile();
        } else {
            this.persistenceWrapper.storeFavorites(list3);
        }
        return list2;
    }

    public final List<StructureInfo> getFavorites() {
        return this.favorites;
    }

    public final void initialize() {
        this.favorites = this.persistenceWrapper.getFileExists() ? this.persistenceWrapper.readFavorites() : I0.m.h();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AuxiliaryPersistenceWrapper(File file, Executor executor) {
        this(new ControlsFavoritePersistenceWrapper(file, executor, null, 4, null));
        kotlin.jvm.internal.n.g(file, "file");
        kotlin.jvm.internal.n.g(executor, "executor");
    }
}
