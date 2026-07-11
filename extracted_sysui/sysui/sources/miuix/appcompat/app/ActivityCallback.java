package miuix.appcompat.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
interface ActivityCallback {
    void onConfigurationChanged(Configuration configuration);

    void onCreate(@Nullable Bundle bundle);

    boolean onCreatePanelMenu(int i2, Menu menu);

    View onCreatePanelView(int i2);

    boolean onMenuItemSelected(int i2, @NonNull MenuItem menuItem);

    void onPanelClosed(int i2, Menu menu);

    void onPanelViewAdded(int i2, @Nullable View view, @Nullable Menu menu, @Nullable Menu menu2);

    void onPostResume();

    boolean onPreparePanel(int i2, @Nullable View view, Menu menu);

    void onRestoreInstanceState(Bundle bundle);

    void onSaveInstanceState(Bundle bundle);

    void onStop();
}
