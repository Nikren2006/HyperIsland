package androidx.mediarouter.app;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteButton extends View {
    private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
    private static final int CONNECTION_STATE_CONNECTED = 2;
    private static final int CONNECTION_STATE_CONNECTING = 1;
    private static final int CONNECTION_STATE_DISCONNECTED = 0;
    private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
    private static final String TAG = "MediaRouteButton";
    private static ConnectivityReceiver sConnectivityReceiver;
    private boolean mAlwaysVisible;
    private boolean mAttachedToWindow;
    private ColorStateList mButtonTint;
    private final MediaRouterCallback mCallback;
    private boolean mCheatSheetEnabled;
    private int mConnectionState;
    private MediaRouteDialogFactory mDialogFactory;
    boolean mIsFixedIcon;
    private int mLastConnectionState;
    private int mMinHeight;
    private int mMinWidth;
    private Drawable mRemoteIndicator;
    RemoteIndicatorLoader mRemoteIndicatorLoader;
    private int mRemoteIndicatorResIdToLoad;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;
    private int mVisibility;
    static final SparseArray<Drawable.ConstantState> sRemoteIndicatorCache = new SparseArray<>(2);
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] CHECKABLE_STATE_SET = {R.attr.state_checkable};

    @RequiresApi(33)
    public static class Api33 {
        private Api33() {
        }

        @DoNotInline
        public static void registerReceiver(@NonNull Context context, @NonNull BroadcastReceiver broadcastReceiver, @NonNull IntentFilter intentFilter, int i2) {
            context.registerReceiver(broadcastReceiver, intentFilter, i2);
        }
    }

    public static final class ConnectivityReceiver extends BroadcastReceiver {
        private final Context mContext;
        private boolean mIsConnected = true;
        private List<MediaRouteButton> mButtons = new ArrayList();

        public ConnectivityReceiver(Context context) {
            this.mContext = context;
        }

        public boolean isConnected() {
            return this.mIsConnected;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z2;
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || this.mIsConnected == (!intent.getBooleanExtra("noConnectivity", false))) {
                return;
            }
            this.mIsConnected = z2;
            Iterator<MediaRouteButton> it = this.mButtons.iterator();
            while (it.hasNext()) {
                it.next().refreshVisibility();
            }
        }

        public void registerReceiver(MediaRouteButton mediaRouteButton) {
            if (this.mButtons.size() == 0) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                Api33.registerReceiver(this.mContext, this, intentFilter, 4);
            }
            this.mButtons.add(mediaRouteButton);
        }

        public void unregisterReceiver(MediaRouteButton mediaRouteButton) {
            this.mButtons.remove(mediaRouteButton);
            if (this.mButtons.size() == 0) {
                this.mContext.unregisterReceiver(this);
            }
        }
    }

    public final class MediaRouterCallback extends MediaRouter.Callback {
        public MediaRouterCallback() {
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderAdded(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderRemoved(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouterParamsChanged(@NonNull MediaRouter mediaRouter, MediaRouterParams mediaRouterParams) {
            boolean z2 = mediaRouterParams != null ? mediaRouterParams.getExtras().getBoolean(MediaRouterParams.EXTRAS_KEY_FIXED_CAST_ICON) : false;
            MediaRouteButton mediaRouteButton = MediaRouteButton.this;
            if (mediaRouteButton.mIsFixedIcon != z2) {
                mediaRouteButton.mIsFixedIcon = z2;
                mediaRouteButton.refreshDrawableState();
            }
        }
    }

    public final class RemoteIndicatorLoader extends AsyncTask<Void, Void, Drawable> {
        private final Context mContext;
        private final int mResId;

        public RemoteIndicatorLoader(int i2, Context context) {
            this.mResId = i2;
            this.mContext = context;
        }

        private void cacheAndReset(Drawable drawable) {
            if (drawable != null) {
                MediaRouteButton.sRemoteIndicatorCache.put(this.mResId, drawable.getConstantState());
            }
            MediaRouteButton.this.mRemoteIndicatorLoader = null;
        }

        @Override // android.os.AsyncTask
        public Drawable doInBackground(Void... voidArr) {
            if (MediaRouteButton.sRemoteIndicatorCache.get(this.mResId) == null) {
                return AppCompatResources.getDrawable(this.mContext, this.mResId);
            }
            return null;
        }

        @Override // android.os.AsyncTask
        public void onCancelled(Drawable drawable) {
            cacheAndReset(drawable);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Drawable drawable) {
            if (drawable != null) {
                cacheAndReset(drawable);
            } else {
                Drawable.ConstantState constantState = MediaRouteButton.sRemoteIndicatorCache.get(this.mResId);
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                MediaRouteButton.this.mRemoteIndicatorLoader = null;
            }
            MediaRouteButton.this.setRemoteIndicatorDrawableInternal(drawable);
        }
    }

    public MediaRouteButton(@NonNull Context context) {
        this(context, null);
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private FragmentManager getFragmentManager() {
        Activity activity = getActivity();
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).getSupportFragmentManager();
        }
        return null;
    }

    private void loadRemoteIndicatorIfNeeded() {
        if (this.mRemoteIndicatorResIdToLoad > 0) {
            RemoteIndicatorLoader remoteIndicatorLoader = this.mRemoteIndicatorLoader;
            if (remoteIndicatorLoader != null) {
                remoteIndicatorLoader.cancel(false);
            }
            RemoteIndicatorLoader remoteIndicatorLoader2 = new RemoteIndicatorLoader(this.mRemoteIndicatorResIdToLoad, getContext());
            this.mRemoteIndicatorLoader = remoteIndicatorLoader2;
            this.mRemoteIndicatorResIdToLoad = 0;
            remoteIndicatorLoader2.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    private boolean showDialogForType(int i2) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
        }
        if (this.mRouter.getSelectedRoute().isDefaultOrBluetooth()) {
            if (fragmentManager.findFragmentByTag(CHOOSER_FRAGMENT_TAG) != null) {
                Log.w(TAG, "showDialog(): Route chooser dialog already showing!");
                return false;
            }
            MediaRouteChooserDialogFragment mediaRouteChooserDialogFragmentOnCreateChooserDialogFragment = this.mDialogFactory.onCreateChooserDialogFragment();
            mediaRouteChooserDialogFragmentOnCreateChooserDialogFragment.setRouteSelector(this.mSelector);
            if (i2 == 2) {
                mediaRouteChooserDialogFragmentOnCreateChooserDialogFragment.setUseDynamicGroup(true);
            }
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction.add(mediaRouteChooserDialogFragmentOnCreateChooserDialogFragment, CHOOSER_FRAGMENT_TAG);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } else {
            if (fragmentManager.findFragmentByTag(CONTROLLER_FRAGMENT_TAG) != null) {
                Log.w(TAG, "showDialog(): Route controller dialog already showing!");
                return false;
            }
            MediaRouteControllerDialogFragment mediaRouteControllerDialogFragmentOnCreateControllerDialogFragment = this.mDialogFactory.onCreateControllerDialogFragment();
            mediaRouteControllerDialogFragmentOnCreateControllerDialogFragment.setRouteSelector(this.mSelector);
            if (i2 == 2) {
                mediaRouteControllerDialogFragmentOnCreateControllerDialogFragment.setUseDynamicGroup(true);
            }
            FragmentTransaction fragmentTransactionBeginTransaction2 = fragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction2.add(mediaRouteControllerDialogFragmentOnCreateControllerDialogFragment, CONTROLLER_FRAGMENT_TAG);
            fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
        }
        return true;
    }

    private void updateContentDescription() {
        int i2 = this.mConnectionState;
        String string = getContext().getString(i2 != 1 ? i2 != 2 ? androidx.mediarouter.R.string.mr_cast_button_disconnected : androidx.mediarouter.R.string.mr_cast_button_connected : androidx.mediarouter.R.string.mr_cast_button_connecting);
        setContentDescription(string);
        if (!this.mCheatSheetEnabled || TextUtils.isEmpty(string)) {
            string = null;
        }
        TooltipCompat.setTooltipText(this, string);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mRemoteIndicator != null) {
            this.mRemoteIndicator.setState(getDrawableState());
            if (this.mRemoteIndicator.getCurrent() instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) this.mRemoteIndicator.getCurrent();
                int i2 = this.mConnectionState;
                if (i2 == 1 || this.mLastConnectionState != i2) {
                    if (!animationDrawable.isRunning()) {
                        animationDrawable.start();
                    }
                } else if (i2 == 2 && !animationDrawable.isRunning()) {
                    animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
                }
            }
            invalidate();
        }
        this.mLastConnectionState = this.mConnectionState;
    }

    @Deprecated
    public void enableDynamicGroup() {
        MediaRouterParams routerParams = this.mRouter.getRouterParams();
        MediaRouterParams.Builder builder = routerParams == null ? new MediaRouterParams.Builder() : new MediaRouterParams.Builder(routerParams);
        builder.setDialogType(2);
        this.mRouter.setRouterParams(builder.build());
    }

    @NonNull
    public MediaRouteDialogFactory getDialogFactory() {
        return this.mDialogFactory;
    }

    @NonNull
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mRemoteIndicator;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.mAttachedToWindow = true;
        if (!this.mSelector.isEmpty()) {
            this.mRouter.addCallback(this.mSelector, this.mCallback);
        }
        refreshRoute();
        sConnectivityReceiver.registerReceiver(this);
    }

    @Override // android.view.View
    @NonNull
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (this.mRouter == null || this.mIsFixedIcon) {
            return iArrOnCreateDrawableState;
        }
        int i3 = this.mConnectionState;
        if (i3 == 1) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, CHECKABLE_STATE_SET);
        } else if (i3 == 2) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.mAttachedToWindow = false;
            if (!this.mSelector.isEmpty()) {
                this.mRouter.removeCallback(this.mCallback);
            }
            sConnectivityReceiver.unregisterReceiver(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRemoteIndicator != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int intrinsicWidth = this.mRemoteIndicator.getIntrinsicWidth();
            int intrinsicHeight = this.mRemoteIndicator.getIntrinsicHeight();
            int i2 = paddingLeft + (((width - paddingLeft) - intrinsicWidth) / 2);
            int i3 = paddingTop + (((height - paddingTop) - intrinsicHeight) / 2);
            this.mRemoteIndicator.setBounds(i2, i3, intrinsicWidth + i2, intrinsicHeight + i3);
            this.mRemoteIndicator.draw(canvas);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int i4 = this.mMinWidth;
        Drawable drawable = this.mRemoteIndicator;
        int iMax = Math.max(i4, drawable != null ? drawable.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight() : 0);
        int i5 = this.mMinHeight;
        Drawable drawable2 = this.mRemoteIndicator;
        int iMax2 = Math.max(i5, drawable2 != null ? drawable2.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom() : 0);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, iMax);
        } else if (mode != 1073741824) {
            size = iMax;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, iMax2);
        } else if (mode2 != 1073741824) {
            size2 = iMax2;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean zPerformClick = super.performClick();
        if (!zPerformClick) {
            playSoundEffect(0);
        }
        loadRemoteIndicatorIfNeeded();
        return showDialog() || zPerformClick;
    }

    public void refreshRoute() {
        MediaRouter.RouteInfo selectedRoute = this.mRouter.getSelectedRoute();
        boolean zIsDefaultOrBluetooth = selectedRoute.isDefaultOrBluetooth();
        int connectionState = !zIsDefaultOrBluetooth ? selectedRoute.getConnectionState() : 0;
        if (this.mConnectionState != connectionState) {
            this.mConnectionState = connectionState;
            updateContentDescription();
            refreshDrawableState();
        }
        if (connectionState == 1) {
            loadRemoteIndicatorIfNeeded();
        }
        if (this.mAttachedToWindow) {
            setEnabled(this.mAlwaysVisible || !zIsDefaultOrBluetooth || this.mRouter.isRouteAvailable(this.mSelector, 1));
        }
    }

    public void refreshVisibility() {
        super.setVisibility((this.mVisibility != 0 || this.mAlwaysVisible || sConnectivityReceiver.isConnected()) ? this.mVisibility : 4);
        Drawable drawable = this.mRemoteIndicator;
        if (drawable != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    public void setAlwaysVisible(boolean z2) {
        if (z2 != this.mAlwaysVisible) {
            this.mAlwaysVisible = z2;
            refreshVisibility();
            refreshRoute();
        }
    }

    public void setCheatSheetEnabled(boolean z2) {
        if (z2 != this.mCheatSheetEnabled) {
            this.mCheatSheetEnabled = z2;
            updateContentDescription();
        }
    }

    public void setDialogFactory(@NonNull MediaRouteDialogFactory mediaRouteDialogFactory) {
        if (mediaRouteDialogFactory == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        this.mDialogFactory = mediaRouteDialogFactory;
    }

    public void setRemoteIndicatorDrawable(@Nullable Drawable drawable) {
        this.mRemoteIndicatorResIdToLoad = 0;
        setRemoteIndicatorDrawableInternal(drawable);
    }

    public void setRemoteIndicatorDrawableInternal(Drawable drawable) {
        RemoteIndicatorLoader remoteIndicatorLoader = this.mRemoteIndicatorLoader;
        if (remoteIndicatorLoader != null) {
            remoteIndicatorLoader.cancel(false);
        }
        Drawable drawable2 = this.mRemoteIndicator;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mRemoteIndicator);
        }
        if (drawable != null) {
            if (this.mButtonTint != null) {
                drawable = DrawableCompat.wrap(drawable.mutate());
                DrawableCompat.setTintList(drawable, this.mButtonTint);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            drawable.setVisible(getVisibility() == 0, false);
        }
        this.mRemoteIndicator = drawable;
        refreshDrawableState();
    }

    public void setRouteSelector(@NonNull MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.mSelector.equals(mediaRouteSelector)) {
            return;
        }
        if (this.mAttachedToWindow) {
            if (!this.mSelector.isEmpty()) {
                this.mRouter.removeCallback(this.mCallback);
            }
            if (!mediaRouteSelector.isEmpty()) {
                this.mRouter.addCallback(mediaRouteSelector, this.mCallback);
            }
        }
        this.mSelector = mediaRouteSelector;
        refreshRoute();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        this.mVisibility = i2;
        refreshVisibility();
    }

    public boolean showDialog() {
        if (!this.mAttachedToWindow) {
            return false;
        }
        MediaRouterParams routerParams = this.mRouter.getRouterParams();
        if (routerParams == null) {
            return showDialogForType(1);
        }
        if (routerParams.isOutputSwitcherEnabled() && MediaRouter.isMediaTransferEnabled() && SystemOutputSwitcherDialogController.showDialog(getContext())) {
            return true;
        }
        return showDialogForType(routerParams.getDialogType());
    }

    @Override // android.view.View
    public boolean verifyDrawable(@NonNull Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mRemoteIndicator;
    }

    public MediaRouteButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, androidx.mediarouter.R.attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        Drawable.ConstantState constantState;
        super(MediaRouterThemeHelper.createThemedButtonContext(context), attributeSet, i2);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mDialogFactory = MediaRouteDialogFactory.getDefault();
        this.mVisibility = 0;
        Context context2 = getContext();
        int[] iArr = androidx.mediarouter.R.styleable.MediaRouteButton;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context2, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        if (isInEditMode()) {
            this.mRouter = null;
            this.mCallback = null;
            this.mRemoteIndicator = AppCompatResources.getDrawable(context2, typedArrayObtainStyledAttributes.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0));
            return;
        }
        MediaRouter mediaRouter = MediaRouter.getInstance(context2);
        this.mRouter = mediaRouter;
        this.mCallback = new MediaRouterCallback();
        MediaRouter.RouteInfo selectedRoute = mediaRouter.getSelectedRoute();
        int connectionState = !selectedRoute.isDefaultOrBluetooth() ? selectedRoute.getConnectionState() : 0;
        this.mConnectionState = connectionState;
        this.mLastConnectionState = connectionState;
        if (sConnectivityReceiver == null) {
            sConnectivityReceiver = new ConnectivityReceiver(context2.getApplicationContext());
        }
        this.mButtonTint = typedArrayObtainStyledAttributes.getColorStateList(androidx.mediarouter.R.styleable.MediaRouteButton_mediaRouteButtonTint);
        this.mMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.mediarouter.R.styleable.MediaRouteButton_android_minWidth, 0);
        this.mMinHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.mediarouter.R.styleable.MediaRouteButton_android_minHeight, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0);
        this.mRemoteIndicatorResIdToLoad = typedArrayObtainStyledAttributes.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        typedArrayObtainStyledAttributes.recycle();
        int i3 = this.mRemoteIndicatorResIdToLoad;
        if (i3 != 0 && (constantState = sRemoteIndicatorCache.get(i3)) != null) {
            setRemoteIndicatorDrawable(constantState.newDrawable());
        }
        if (this.mRemoteIndicator == null) {
            if (resourceId != 0) {
                Drawable.ConstantState constantState2 = sRemoteIndicatorCache.get(resourceId);
                if (constantState2 != null) {
                    setRemoteIndicatorDrawableInternal(constantState2.newDrawable());
                } else {
                    RemoteIndicatorLoader remoteIndicatorLoader = new RemoteIndicatorLoader(resourceId, getContext());
                    this.mRemoteIndicatorLoader = remoteIndicatorLoader;
                    remoteIndicatorLoader.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                }
            } else {
                loadRemoteIndicatorIfNeeded();
            }
        }
        updateContentDescription();
        setClickable(true);
    }
}
