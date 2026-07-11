package miui.systemui;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.AppComponentFactory;
import miui.systemui.PluginAppComponentFactory;
import miui.systemui.dagger.ContextComponentHelper;
import miui.systemui.dagger.PluginComponentInitializer;

/* JADX INFO: loaded from: classes2.dex */
public class PluginAppComponentFactory extends AppComponentFactory {
    private static final String TAG = "AppComponentFactory";
    public ContextComponentHelper mComponentHelper;

    public interface ContextAvailableCallback {
        void onContextAvailable(Context context);
    }

    public interface ContextInitializer {
        void setContextAvailableCallback(ContextAvailableCallback contextAvailableCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$instantiateApplicationCompat$0(Context context) {
        PluginComponentInitializer.create(context);
        PluginComponentInitializer.getPluginComponent().inject(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$instantiateProviderCompat$1(ContentProvider contentProvider, Context context) {
        PluginComponentInitializer.create(context);
        PluginComponentInitializer.getPluginComponent().inject(contentProvider);
    }

    @Override // androidx.core.app.AppComponentFactory
    @NonNull
    public Activity instantiateActivityCompat(@NonNull ClassLoader classLoader, @NonNull String str, @Nullable Intent intent) {
        if (this.mComponentHelper == null) {
            PluginComponentInitializer.getPluginComponent().inject(this);
        }
        Activity activityResolveActivity = this.mComponentHelper.resolveActivity(str);
        return activityResolveActivity != null ? activityResolveActivity : super.instantiateActivityCompat(classLoader, str, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.core.app.AppComponentFactory
    @NonNull
    public Application instantiateApplicationCompat(@NonNull ClassLoader classLoader, @NonNull String str) {
        Application applicationInstantiateApplicationCompat = super.instantiateApplicationCompat(classLoader, str);
        Log.d(TAG, "instantiateApplicationCompat");
        if (applicationInstantiateApplicationCompat instanceof ContextInitializer) {
            ((ContextInitializer) applicationInstantiateApplicationCompat).setContextAvailableCallback(new ContextAvailableCallback() { // from class: z1.b
                @Override // miui.systemui.PluginAppComponentFactory.ContextAvailableCallback
                public final void onContextAvailable(Context context) {
                    this.f7136a.lambda$instantiateApplicationCompat$0(context);
                }
            });
        }
        return applicationInstantiateApplicationCompat;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.core.app.AppComponentFactory
    @NonNull
    public ContentProvider instantiateProviderCompat(@NonNull ClassLoader classLoader, @NonNull String str) {
        final ContentProvider contentProviderInstantiateProviderCompat = super.instantiateProviderCompat(classLoader, str);
        if (contentProviderInstantiateProviderCompat instanceof ContextInitializer) {
            ((ContextInitializer) contentProviderInstantiateProviderCompat).setContextAvailableCallback(new ContextAvailableCallback() { // from class: z1.c
                @Override // miui.systemui.PluginAppComponentFactory.ContextAvailableCallback
                public final void onContextAvailable(Context context) {
                    PluginAppComponentFactory.lambda$instantiateProviderCompat$1(contentProviderInstantiateProviderCompat, context);
                }
            });
        }
        return contentProviderInstantiateProviderCompat;
    }

    @Override // androidx.core.app.AppComponentFactory
    @NonNull
    public BroadcastReceiver instantiateReceiverCompat(@NonNull ClassLoader classLoader, @NonNull String str, @Nullable Intent intent) {
        if (this.mComponentHelper == null) {
            PluginComponentInitializer.getPluginComponent().inject(this);
        }
        BroadcastReceiver broadcastReceiverResolveBroadcastReceiver = this.mComponentHelper.resolveBroadcastReceiver(str);
        return broadcastReceiverResolveBroadcastReceiver != null ? broadcastReceiverResolveBroadcastReceiver : super.instantiateReceiverCompat(classLoader, str, intent);
    }
}
