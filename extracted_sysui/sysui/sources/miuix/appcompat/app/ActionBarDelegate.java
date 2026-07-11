package miuix.appcompat.app;

import android.content.res.Configuration;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
interface ActionBarDelegate extends IContentInsetState {
    ActionBar createActionBar();

    int getBottomMenuMode();

    void invalidateOptionsMenu();

    void onActionModeFinished(ActionMode actionMode);

    void onActionModeStarted(ActionMode actionMode);

    void onConfigurationChanged(Configuration configuration);

    boolean onCreatePanelMenu(int i2, Menu menu);

    View onCreatePanelView(int i2);

    void onDestroy();

    boolean onMenuItemSelected(int i2, MenuItem menuItem);

    void onPanelClosed(int i2, Menu menu);

    void onPanelViewAdded(int i2, @Nullable View view, @Nullable Menu menu, @Nullable Menu menu2);

    void onPostResume();

    boolean onPreparePanel(int i2, @Nullable View view, Menu menu);

    void onStop();

    ActionMode onWindowStartingActionMode(ActionMode.Callback callback);

    void registerCoordinateScrollView(View view);

    boolean requestWindowFeature(int i2);

    default void setBottomExtraInset(int i2) {
    }

    default void setBottomMenuMode(int i2) {
    }

    @Override // miuix.appcompat.app.IContentInsetState
    void setCorrectNestedScrollMotionEventEnabled(boolean z2);

    ActionMode startActionMode(ActionMode.Callback callback);

    void unregisterCoordinateScrollView(View view);
}
