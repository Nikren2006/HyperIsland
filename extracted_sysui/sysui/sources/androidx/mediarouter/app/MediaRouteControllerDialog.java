package androidx.mediarouter.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.R;
import androidx.mediarouter.app.OverlayListView;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.palette.graphics.Palette;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import miui.systemui.notification.focus.Const;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteControllerDialog extends AlertDialog {
    static final int BUTTON_DISCONNECT_RES_ID = 16908314;
    private static final int BUTTON_NEUTRAL_RES_ID = 16908315;
    static final int BUTTON_STOP_RES_ID = 16908313;
    static final int CONNECTION_TIMEOUT_MILLIS;
    static final boolean DEBUG = false;
    static final String TAG = "MediaRouteCtrlDialog";
    static final int VOLUME_UPDATE_DELAY_MILLIS = 500;
    private Interpolator mAccelerateDecelerateInterpolator;
    final AccessibilityManager mAccessibilityManager;
    int mArtIconBackgroundColor;
    Bitmap mArtIconBitmap;
    boolean mArtIconIsLoaded;
    Bitmap mArtIconLoadedBitmap;
    Uri mArtIconUri;
    private ImageView mArtView;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    MediaControllerCallback mControllerCallback;
    private boolean mCreated;
    private FrameLayout mCustomControlLayout;
    private View mCustomControlView;
    FrameLayout mDefaultControlLayout;
    MediaDescriptionCompat mDescription;
    private LinearLayout mDialogAreaLayout;
    private int mDialogContentWidth;
    private Button mDisconnectButton;
    private View mDividerView;
    final boolean mEnableGroupVolumeUX;
    private FrameLayout mExpandableAreaLayout;
    private Interpolator mFastOutSlowInInterpolator;
    FetchArtTask mFetchArtTask;
    private MediaRouteExpandCollapseButton mGroupExpandCollapseButton;
    int mGroupListAnimationDurationMs;
    Runnable mGroupListFadeInAnimation;
    private int mGroupListFadeInDurationMs;
    private int mGroupListFadeOutDurationMs;
    private List<MediaRouter.RouteInfo> mGroupMemberRoutes;
    Set<MediaRouter.RouteInfo> mGroupMemberRoutesAdded;
    Set<MediaRouter.RouteInfo> mGroupMemberRoutesAnimatingWithBitmap;
    private Set<MediaRouter.RouteInfo> mGroupMemberRoutesRemoved;
    boolean mHasPendingUpdate;
    private Interpolator mInterpolator;
    boolean mIsGroupExpanded;
    boolean mIsGroupListAnimating;
    boolean mIsGroupListAnimationPending;
    private Interpolator mLinearOutSlowInInterpolator;
    MediaControllerCompat mMediaController;
    private LinearLayout mMediaMainControlLayout;
    boolean mPendingUpdateAnimationNeeded;
    private ImageButton mPlaybackControlButton;
    private RelativeLayout mPlaybackControlLayout;
    final MediaRouter.RouteInfo mRoute;
    MediaRouter.RouteInfo mRouteInVolumeSliderTouched;
    private TextView mRouteNameTextView;
    final MediaRouter mRouter;
    PlaybackStateCompat mState;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private TextView mTitleView;
    VolumeChangeListener mVolumeChangeListener;
    private boolean mVolumeControlEnabled;
    LinearLayout mVolumeControlLayout;
    VolumeGroupAdapter mVolumeGroupAdapter;
    OverlayListView mVolumeGroupList;
    private int mVolumeGroupListItemHeight;
    private int mVolumeGroupListItemIconSize;
    private int mVolumeGroupListMaxHeight;
    private final int mVolumeGroupListPaddingTop;
    SeekBar mVolumeSlider;
    Map<MediaRouter.RouteInfo, SeekBar> mVolumeSliderMap;

    public final class ClickListener implements View.OnClickListener {
        public ClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlaybackStateCompat playbackStateCompat;
            int id = view.getId();
            if (id == 16908313 || id == 16908314) {
                if (MediaRouteControllerDialog.this.mRoute.isSelected()) {
                    MediaRouteControllerDialog.this.mRouter.unselect(id == 16908313 ? 2 : 1);
                }
                MediaRouteControllerDialog.this.dismiss();
                return;
            }
            if (id != R.id.mr_control_playback_ctrl) {
                if (id == R.id.mr_close) {
                    MediaRouteControllerDialog.this.dismiss();
                    return;
                }
                return;
            }
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            if (mediaRouteControllerDialog.mMediaController == null || (playbackStateCompat = mediaRouteControllerDialog.mState) == null) {
                return;
            }
            int i2 = 0;
            int i3 = playbackStateCompat.v() != 3 ? 0 : 1;
            if (i3 != 0 && MediaRouteControllerDialog.this.isPauseActionSupported()) {
                MediaRouteControllerDialog.this.mMediaController.f().a();
                i2 = R.string.mr_controller_pause;
            } else if (i3 != 0 && MediaRouteControllerDialog.this.isStopActionSupported()) {
                MediaRouteControllerDialog.this.mMediaController.f().c();
                i2 = R.string.mr_controller_stop;
            } else if (i3 == 0 && MediaRouteControllerDialog.this.isPlayActionSupported()) {
                MediaRouteControllerDialog.this.mMediaController.f().b();
                i2 = R.string.mr_controller_play;
            }
            AccessibilityManager accessibilityManager = MediaRouteControllerDialog.this.mAccessibilityManager;
            if (accessibilityManager == null || !accessibilityManager.isEnabled() || i2 == 0) {
                return;
            }
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(16384);
            accessibilityEventObtain.setPackageName(MediaRouteControllerDialog.this.mContext.getPackageName());
            accessibilityEventObtain.setClassName(ClickListener.class.getName());
            accessibilityEventObtain.getText().add(MediaRouteControllerDialog.this.mContext.getString(i2));
            MediaRouteControllerDialog.this.mAccessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
        }
    }

    public class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private static final long SHOW_ANIM_TIME_THRESHOLD_MILLIS = 120;
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;
        private long mStartTimeMillis;

        public FetchArtTask() {
            MediaDescriptionCompat mediaDescriptionCompat = MediaRouteControllerDialog.this.mDescription;
            Bitmap bitmapR = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.r();
            if (MediaRouteControllerDialog.isBitmapRecycled(bitmapR)) {
                Log.w(MediaRouteControllerDialog.TAG, "Can't fetch the given art bitmap because it's already recycled.");
                bitmapR = null;
            }
            this.mIconBitmap = bitmapR;
            MediaDescriptionCompat mediaDescriptionCompat2 = MediaRouteControllerDialog.this.mDescription;
            this.mIconUri = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.s() : null;
        }

        private InputStream openInputStreamByScheme(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || Const.Param.CONTENT.equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = MediaRouteControllerDialog.this.mContext.getContentResolver().openInputStream(uri);
            } else {
                URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
                int i2 = MediaRouteControllerDialog.CONNECTION_TIMEOUT_MILLIS;
                uRLConnectionOpenConnection.setConnectTimeout(i2);
                uRLConnectionOpenConnection.setReadTimeout(i2);
                inputStreamOpenInputStream = uRLConnectionOpenConnection.getInputStream();
            }
            if (inputStreamOpenInputStream == null) {
                return null;
            }
            return new BufferedInputStream(inputStreamOpenInputStream);
        }

        public Bitmap getIconBitmap() {
            return this.mIconBitmap;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            this.mStartTimeMillis = SystemClock.uptimeMillis();
            MediaRouteControllerDialog.this.clearLoadedBitmap();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v1 */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.io.InputStream] */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void... voidArr) throws Throwable {
            InputStream inputStreamOpenInputStreamByScheme;
            Bitmap bitmap = this.mIconBitmap;
            ?? r4 = 0;
            if (bitmap == null) {
                Uri uri = this.mIconUri;
                try {
                    if (uri != null) {
                        try {
                            inputStreamOpenInputStreamByScheme = openInputStreamByScheme(uri);
                            try {
                                if (inputStreamOpenInputStreamByScheme == null) {
                                    Log.w(MediaRouteControllerDialog.TAG, "Unable to open: " + this.mIconUri);
                                    if (inputStreamOpenInputStreamByScheme != null) {
                                        try {
                                            inputStreamOpenInputStreamByScheme.close();
                                        } catch (IOException unused) {
                                        }
                                    }
                                    return null;
                                }
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeStream(inputStreamOpenInputStreamByScheme, null, options);
                                if (options.outWidth == 0 || options.outHeight == 0) {
                                    try {
                                        inputStreamOpenInputStreamByScheme.close();
                                    } catch (IOException unused2) {
                                    }
                                    return null;
                                }
                                try {
                                    inputStreamOpenInputStreamByScheme.reset();
                                } catch (IOException unused3) {
                                    inputStreamOpenInputStreamByScheme.close();
                                    inputStreamOpenInputStreamByScheme = openInputStreamByScheme(this.mIconUri);
                                    if (inputStreamOpenInputStreamByScheme == null) {
                                        Log.w(MediaRouteControllerDialog.TAG, "Unable to open: " + this.mIconUri);
                                        if (inputStreamOpenInputStreamByScheme != null) {
                                            try {
                                                inputStreamOpenInputStreamByScheme.close();
                                            } catch (IOException unused4) {
                                            }
                                        }
                                        return null;
                                    }
                                }
                                options.inJustDecodeBounds = false;
                                options.inSampleSize = Math.max(1, Integer.highestOneBit(options.outHeight / MediaRouteControllerDialog.this.getDesiredArtHeight(options.outWidth, options.outHeight)));
                                if (isCancelled()) {
                                    try {
                                        inputStreamOpenInputStreamByScheme.close();
                                    } catch (IOException unused5) {
                                    }
                                    return null;
                                }
                                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStreamByScheme, null, options);
                                try {
                                    inputStreamOpenInputStreamByScheme.close();
                                } catch (IOException unused6) {
                                }
                                bitmap = bitmapDecodeStream;
                            } catch (IOException e2) {
                                e = e2;
                                Log.w(MediaRouteControllerDialog.TAG, "Unable to open: " + this.mIconUri, e);
                                if (inputStreamOpenInputStreamByScheme != null) {
                                    try {
                                        inputStreamOpenInputStreamByScheme.close();
                                    } catch (IOException unused7) {
                                    }
                                }
                                bitmap = null;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            inputStreamOpenInputStreamByScheme = null;
                        } catch (Throwable th) {
                            th = th;
                            if (r4 != 0) {
                                try {
                                    r4.close();
                                } catch (IOException unused8) {
                                }
                            }
                            throw th;
                        }
                    } else {
                        bitmap = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r4 = uri;
                }
            }
            if (MediaRouteControllerDialog.isBitmapRecycled(bitmap)) {
                Log.w(MediaRouteControllerDialog.TAG, "Can't use recycled bitmap: " + bitmap);
                return null;
            }
            if (bitmap != null && bitmap.getWidth() < bitmap.getHeight()) {
                Palette paletteGenerate = new Palette.Builder(bitmap).maximumColorCount(1).generate();
                this.mBackgroundColor = paletteGenerate.getSwatches().isEmpty() ? 0 : paletteGenerate.getSwatches().get(0).getRgb();
            }
            return bitmap;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.mFetchArtTask = null;
            if (ObjectsCompat.equals(mediaRouteControllerDialog.mArtIconBitmap, this.mIconBitmap) && ObjectsCompat.equals(MediaRouteControllerDialog.this.mArtIconUri, this.mIconUri)) {
                return;
            }
            MediaRouteControllerDialog mediaRouteControllerDialog2 = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog2.mArtIconBitmap = this.mIconBitmap;
            mediaRouteControllerDialog2.mArtIconLoadedBitmap = bitmap;
            mediaRouteControllerDialog2.mArtIconUri = this.mIconUri;
            mediaRouteControllerDialog2.mArtIconBackgroundColor = this.mBackgroundColor;
            mediaRouteControllerDialog2.mArtIconIsLoaded = true;
            MediaRouteControllerDialog.this.update(SystemClock.uptimeMillis() - this.mStartTimeMillis > SHOW_ANIM_TIME_THRESHOLD_MILLIS);
        }
    }

    public final class MediaControllerCallback extends MediaControllerCompat.a {
        public MediaControllerCallback() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaRouteControllerDialog.this.mDescription = mediaMetadataCompat == null ? null : mediaMetadataCompat.t();
            MediaRouteControllerDialog.this.updateArtIconIfNeeded();
            MediaRouteControllerDialog.this.update(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.mState = playbackStateCompat;
            mediaRouteControllerDialog.update(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void onSessionDestroyed() {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            MediaControllerCompat mediaControllerCompat = mediaRouteControllerDialog.mMediaController;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.i(mediaRouteControllerDialog.mControllerCallback);
                MediaRouteControllerDialog.this.mMediaController = null;
            }
        }
    }

    public final class MediaRouterCallback extends MediaRouter.Callback {
        public MediaRouterCallback() {
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteControllerDialog.this.update(true);
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteControllerDialog.this.update(false);
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteVolumeChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            SeekBar seekBar = MediaRouteControllerDialog.this.mVolumeSliderMap.get(routeInfo);
            int volume = routeInfo.getVolume();
            if (MediaRouteControllerDialog.DEBUG) {
                Log.d(MediaRouteControllerDialog.TAG, "onRouteVolumeChanged(), route.getVolume:" + volume);
            }
            if (seekBar == null || MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched == routeInfo) {
                return;
            }
            seekBar.setProgress(volume);
        }
    }

    public class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {
        private final Runnable mStopTrackingTouch = new Runnable() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.VolumeChangeListener.1
            @Override // java.lang.Runnable
            public void run() {
                MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
                if (mediaRouteControllerDialog.mRouteInVolumeSliderTouched != null) {
                    mediaRouteControllerDialog.mRouteInVolumeSliderTouched = null;
                    if (mediaRouteControllerDialog.mHasPendingUpdate) {
                        mediaRouteControllerDialog.update(mediaRouteControllerDialog.mPendingUpdateAnimationNeeded);
                    }
                }
            }
        };

        public VolumeChangeListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
            if (z2) {
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) seekBar.getTag();
                if (MediaRouteControllerDialog.DEBUG) {
                    Log.d(MediaRouteControllerDialog.TAG, "onProgressChanged(): calling MediaRouter.RouteInfo.requestSetVolume(" + i2 + ")");
                }
                routeInfo.requestSetVolume(i2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            if (mediaRouteControllerDialog.mRouteInVolumeSliderTouched != null) {
                mediaRouteControllerDialog.mVolumeSlider.removeCallbacks(this.mStopTrackingTouch);
            }
            MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched = (MediaRouter.RouteInfo) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            MediaRouteControllerDialog.this.mVolumeSlider.postDelayed(this.mStopTrackingTouch, 500L);
        }
    }

    public class VolumeGroupAdapter extends ArrayAdapter<MediaRouter.RouteInfo> {
        final float mDisabledAlpha;

        public VolumeGroupAdapter(Context context, List<MediaRouter.RouteInfo> list) {
            super(context, 0, list);
            this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mr_controller_volume_item, viewGroup, false);
            } else {
                MediaRouteControllerDialog.this.updateVolumeGroupItemHeight(view);
            }
            MediaRouter.RouteInfo item = getItem(i2);
            if (item != null) {
                boolean zIsEnabled = item.isEnabled();
                TextView textView = (TextView) view.findViewById(R.id.mr_name);
                textView.setEnabled(zIsEnabled);
                textView.setText(item.getName());
                MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(R.id.mr_volume_slider);
                MediaRouterThemeHelper.setVolumeSliderColor(viewGroup.getContext(), mediaRouteVolumeSlider, MediaRouteControllerDialog.this.mVolumeGroupList);
                mediaRouteVolumeSlider.setTag(item);
                MediaRouteControllerDialog.this.mVolumeSliderMap.put(item, mediaRouteVolumeSlider);
                mediaRouteVolumeSlider.setHideThumb(!zIsEnabled);
                mediaRouteVolumeSlider.setEnabled(zIsEnabled);
                if (zIsEnabled) {
                    if (MediaRouteControllerDialog.this.isVolumeControlAvailable(item)) {
                        mediaRouteVolumeSlider.setMax(item.getVolumeMax());
                        mediaRouteVolumeSlider.setProgress(item.getVolume());
                        mediaRouteVolumeSlider.setOnSeekBarChangeListener(MediaRouteControllerDialog.this.mVolumeChangeListener);
                    } else {
                        mediaRouteVolumeSlider.setMax(100);
                        mediaRouteVolumeSlider.setProgress(100);
                        mediaRouteVolumeSlider.setEnabled(false);
                    }
                }
                ((ImageView) view.findViewById(R.id.mr_volume_item_icon)).setAlpha(zIsEnabled ? 255 : (int) (this.mDisabledAlpha * 255.0f));
                ((LinearLayout) view.findViewById(R.id.volume_item_container)).setVisibility(MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.contains(item) ? 4 : 0);
                Set<MediaRouter.RouteInfo> set = MediaRouteControllerDialog.this.mGroupMemberRoutesAdded;
                if (set != null && set.contains(item)) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                    alphaAnimation.setDuration(0L);
                    alphaAnimation.setFillEnabled(true);
                    alphaAnimation.setFillAfter(true);
                    view.clearAnimation();
                    view.startAnimation(alphaAnimation);
                }
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            return false;
        }
    }

    static {
        Log.isLoggable(TAG, 3);
        CONNECTION_TIMEOUT_MILLIS = (int) TimeUnit.SECONDS.toMillis(30L);
    }

    public MediaRouteControllerDialog(@NonNull Context context) {
        this(context, 0);
    }

    private void animateGroupListItems(final Map<MediaRouter.RouteInfo, Rect> map, final Map<MediaRouter.RouteInfo, BitmapDrawable> map2) {
        this.mVolumeGroupList.setEnabled(false);
        this.mVolumeGroupList.requestLayout();
        this.mIsGroupListAnimating = true;
        this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.8
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                MediaRouteControllerDialog.this.animateGroupListItemsInternal(map, map2);
            }
        });
    }

    private void animateLayoutHeight(final View view, final int i2) {
        final int layoutHeight = getLayoutHeight(view);
        Animation animation = new Animation() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.7
            @Override // android.view.animation.Animation
            public void applyTransformation(float f2, Transformation transformation) {
                MediaRouteControllerDialog.setLayoutHeight(view, layoutHeight - ((int) ((r3 - i2) * f2)));
            }
        };
        animation.setDuration(this.mGroupListAnimationDurationMs);
        animation.setInterpolator(this.mInterpolator);
        view.startAnimation(animation);
    }

    private boolean canShowPlaybackControlLayout() {
        return this.mCustomControlView == null && !(this.mDescription == null && this.mState == null);
    }

    private void fadeInAddedRoutes() {
        Animation.AnimationListener animationListener = new Animation.AnimationListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.12
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                MediaRouteControllerDialog.this.finishAnimation(true);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        int firstVisiblePosition = this.mVolumeGroupList.getFirstVisiblePosition();
        boolean z2 = false;
        for (int i2 = 0; i2 < this.mVolumeGroupList.getChildCount(); i2++) {
            View childAt = this.mVolumeGroupList.getChildAt(i2);
            if (this.mGroupMemberRoutesAdded.contains(this.mVolumeGroupAdapter.getItem(firstVisiblePosition + i2))) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(this.mGroupListFadeInDurationMs);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                if (!z2) {
                    alphaAnimation.setAnimationListener(animationListener);
                    z2 = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(alphaAnimation);
            }
        }
    }

    private static int getLayoutHeight(View view) {
        return view.getLayoutParams().height;
    }

    private int getMainControllerHeight(boolean z2) {
        if (!z2 && this.mVolumeControlLayout.getVisibility() != 0) {
            return 0;
        }
        int paddingTop = this.mMediaMainControlLayout.getPaddingTop() + this.mMediaMainControlLayout.getPaddingBottom();
        if (z2) {
            paddingTop += this.mPlaybackControlLayout.getMeasuredHeight();
        }
        if (this.mVolumeControlLayout.getVisibility() == 0) {
            paddingTop += this.mVolumeControlLayout.getMeasuredHeight();
        }
        return (z2 && this.mVolumeControlLayout.getVisibility() == 0) ? this.mDividerView.getMeasuredHeight() + paddingTop : paddingTop;
    }

    public static boolean isBitmapRecycled(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    private boolean isGroup() {
        return this.mRoute.isGroup() && this.mRoute.getMemberRoutes().size() > 1;
    }

    private boolean isIconChanged() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        Bitmap bitmapR = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.r();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        Uri uriS = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.s() : null;
        FetchArtTask fetchArtTask = this.mFetchArtTask;
        Bitmap iconBitmap = fetchArtTask == null ? this.mArtIconBitmap : fetchArtTask.getIconBitmap();
        FetchArtTask fetchArtTask2 = this.mFetchArtTask;
        Uri iconUri = fetchArtTask2 == null ? this.mArtIconUri : fetchArtTask2.getIconUri();
        if (iconBitmap != bitmapR) {
            return true;
        }
        return iconBitmap == null && !uriEquals(iconUri, uriS);
    }

    private void rebuildVolumeGroupList(boolean z2) {
        List<MediaRouter.RouteInfo> memberRoutes = this.mRoute.getMemberRoutes();
        if (memberRoutes.isEmpty()) {
            this.mGroupMemberRoutes.clear();
            this.mVolumeGroupAdapter.notifyDataSetChanged();
            return;
        }
        if (MediaRouteDialogHelper.listUnorderedEquals(this.mGroupMemberRoutes, memberRoutes)) {
            this.mVolumeGroupAdapter.notifyDataSetChanged();
            return;
        }
        HashMap itemBoundMap = z2 ? MediaRouteDialogHelper.getItemBoundMap(this.mVolumeGroupList, this.mVolumeGroupAdapter) : null;
        HashMap itemBitmapMap = z2 ? MediaRouteDialogHelper.getItemBitmapMap(this.mContext, this.mVolumeGroupList, this.mVolumeGroupAdapter) : null;
        this.mGroupMemberRoutesAdded = MediaRouteDialogHelper.getItemsAdded(this.mGroupMemberRoutes, memberRoutes);
        this.mGroupMemberRoutesRemoved = MediaRouteDialogHelper.getItemsRemoved(this.mGroupMemberRoutes, memberRoutes);
        this.mGroupMemberRoutes.addAll(0, this.mGroupMemberRoutesAdded);
        this.mGroupMemberRoutes.removeAll(this.mGroupMemberRoutesRemoved);
        this.mVolumeGroupAdapter.notifyDataSetChanged();
        if (z2 && this.mIsGroupExpanded && this.mGroupMemberRoutesAdded.size() + this.mGroupMemberRoutesRemoved.size() > 0) {
            animateGroupListItems(itemBoundMap, itemBitmapMap);
        } else {
            this.mGroupMemberRoutesAdded = null;
            this.mGroupMemberRoutesRemoved = null;
        }
    }

    public static void setLayoutHeight(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    private void setMediaSession(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.i(this.mControllerCallback);
            this.mMediaController = null;
        }
        if (token != null && this.mAttachedToWindow) {
            MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.mContext, token);
            this.mMediaController = mediaControllerCompat2;
            mediaControllerCompat2.g(this.mControllerCallback);
            MediaMetadataCompat mediaMetadataCompatB = this.mMediaController.b();
            this.mDescription = mediaMetadataCompatB != null ? mediaMetadataCompatB.t() : null;
            this.mState = this.mMediaController.c();
            updateArtIconIfNeeded();
            update(false);
        }
    }

    private void updateMediaControlVisibility(boolean z2) {
        int i2 = 0;
        this.mDividerView.setVisibility((this.mVolumeControlLayout.getVisibility() == 0 && z2) ? 0 : 8);
        LinearLayout linearLayout = this.mMediaMainControlLayout;
        if (this.mVolumeControlLayout.getVisibility() == 8 && !z2) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void updatePlaybackControlLayout() {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteControllerDialog.updatePlaybackControlLayout():void");
    }

    private void updateVolumeControlLayout() {
        if (!this.mEnableGroupVolumeUX && isGroup()) {
            this.mVolumeControlLayout.setVisibility(8);
            this.mIsGroupExpanded = true;
            this.mVolumeGroupList.setVisibility(0);
            loadInterpolator();
            updateLayoutHeight(false);
            return;
        }
        if ((this.mIsGroupExpanded && !this.mEnableGroupVolumeUX) || !isVolumeControlAvailable(this.mRoute)) {
            this.mVolumeControlLayout.setVisibility(8);
        } else if (this.mVolumeControlLayout.getVisibility() == 8) {
            this.mVolumeControlLayout.setVisibility(0);
            this.mVolumeSlider.setMax(this.mRoute.getVolumeMax());
            this.mVolumeSlider.setProgress(this.mRoute.getVolume());
            this.mGroupExpandCollapseButton.setVisibility(isGroup() ? 0 : 8);
        }
    }

    private static boolean uriEquals(Uri uri, Uri uri2) {
        if (uri == null || !uri.equals(uri2)) {
            return uri == null && uri2 == null;
        }
        return true;
    }

    public void animateGroupListItemsInternal(Map<MediaRouter.RouteInfo, Rect> map, Map<MediaRouter.RouteInfo, BitmapDrawable> map2) {
        OverlayListView.OverlayObject animationEndListener;
        Set<MediaRouter.RouteInfo> set = this.mGroupMemberRoutesAdded;
        if (set == null || this.mGroupMemberRoutesRemoved == null) {
            return;
        }
        int size = set.size() - this.mGroupMemberRoutesRemoved.size();
        Animation.AnimationListener animationListener = new Animation.AnimationListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.9
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                MediaRouteControllerDialog.this.mVolumeGroupList.startAnimationAll();
                MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
                mediaRouteControllerDialog.mVolumeGroupList.postDelayed(mediaRouteControllerDialog.mGroupListFadeInAnimation, mediaRouteControllerDialog.mGroupListAnimationDurationMs);
            }
        };
        int firstVisiblePosition = this.mVolumeGroupList.getFirstVisiblePosition();
        boolean z2 = false;
        for (int i2 = 0; i2 < this.mVolumeGroupList.getChildCount(); i2++) {
            View childAt = this.mVolumeGroupList.getChildAt(i2);
            MediaRouter.RouteInfo item = this.mVolumeGroupAdapter.getItem(firstVisiblePosition + i2);
            Rect rect = map.get(item);
            int top = childAt.getTop();
            int i3 = rect != null ? rect.top : (this.mVolumeGroupListItemHeight * size) + top;
            AnimationSet animationSet = new AnimationSet(true);
            Set<MediaRouter.RouteInfo> set2 = this.mGroupMemberRoutesAdded;
            if (set2 != null && set2.contains(item)) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                alphaAnimation.setDuration(this.mGroupListFadeInDurationMs);
                animationSet.addAnimation(alphaAnimation);
                i3 = top;
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, i3 - top, 0.0f);
            translateAnimation.setDuration(this.mGroupListAnimationDurationMs);
            animationSet.addAnimation(translateAnimation);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            animationSet.setInterpolator(this.mInterpolator);
            if (!z2) {
                animationSet.setAnimationListener(animationListener);
                z2 = true;
            }
            childAt.clearAnimation();
            childAt.startAnimation(animationSet);
            map.remove(item);
            map2.remove(item);
        }
        for (Map.Entry<MediaRouter.RouteInfo, BitmapDrawable> entry : map2.entrySet()) {
            final MediaRouter.RouteInfo key = entry.getKey();
            BitmapDrawable value = entry.getValue();
            Rect rect2 = map.get(key);
            if (this.mGroupMemberRoutesRemoved.contains(key)) {
                animationEndListener = new OverlayListView.OverlayObject(value, rect2).setAlphaAnimation(1.0f, 0.0f).setDuration(this.mGroupListFadeOutDurationMs).setInterpolator(this.mInterpolator);
            } else {
                animationEndListener = new OverlayListView.OverlayObject(value, rect2).setTranslateYAnimation(this.mVolumeGroupListItemHeight * size).setDuration(this.mGroupListAnimationDurationMs).setInterpolator(this.mInterpolator).setAnimationEndListener(new OverlayListView.OverlayObject.OnAnimationEndListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.10
                    @Override // androidx.mediarouter.app.OverlayListView.OverlayObject.OnAnimationEndListener
                    public void onAnimationEnd() {
                        MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.remove(key);
                        MediaRouteControllerDialog.this.mVolumeGroupAdapter.notifyDataSetChanged();
                    }
                });
                this.mGroupMemberRoutesAnimatingWithBitmap.add(key);
            }
            this.mVolumeGroupList.addOverlayObject(animationEndListener);
        }
    }

    public void clearGroupListAnimation(boolean z2) {
        Set<MediaRouter.RouteInfo> set;
        int firstVisiblePosition = this.mVolumeGroupList.getFirstVisiblePosition();
        for (int i2 = 0; i2 < this.mVolumeGroupList.getChildCount(); i2++) {
            View childAt = this.mVolumeGroupList.getChildAt(i2);
            MediaRouter.RouteInfo item = this.mVolumeGroupAdapter.getItem(firstVisiblePosition + i2);
            if (!z2 || (set = this.mGroupMemberRoutesAdded) == null || !set.contains(item)) {
                ((LinearLayout) childAt.findViewById(R.id.volume_item_container)).setVisibility(0);
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
                alphaAnimation.setDuration(0L);
                animationSet.addAnimation(alphaAnimation);
                new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f).setDuration(0L);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                childAt.clearAnimation();
                childAt.startAnimation(animationSet);
            }
        }
        this.mVolumeGroupList.stopAnimationAll();
        if (z2) {
            return;
        }
        finishAnimation(false);
    }

    public void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    public void finishAnimation(boolean z2) {
        this.mGroupMemberRoutesAdded = null;
        this.mGroupMemberRoutesRemoved = null;
        this.mIsGroupListAnimating = false;
        if (this.mIsGroupListAnimationPending) {
            this.mIsGroupListAnimationPending = false;
            updateLayoutHeight(z2);
        }
        this.mVolumeGroupList.setEnabled(true);
    }

    public int getDesiredArtHeight(int i2, int i3) {
        float f2;
        float f3;
        if (i2 >= i3) {
            f2 = this.mDialogContentWidth * i3;
            f3 = i2;
        } else {
            f2 = this.mDialogContentWidth * 9.0f;
            f3 = 16.0f;
        }
        return (int) ((f2 / f3) + 0.5f);
    }

    @Nullable
    public View getMediaControlView() {
        return this.mCustomControlView;
    }

    @Nullable
    public MediaSessionCompat.Token getMediaSession() {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        if (mediaControllerCompat == null) {
            return null;
        }
        return mediaControllerCompat.e();
    }

    @NonNull
    public MediaRouter.RouteInfo getRoute() {
        return this.mRoute;
    }

    public boolean isPauseActionSupported() {
        return (this.mState.r() & 514) != 0;
    }

    public boolean isPlayActionSupported() {
        return (this.mState.r() & 516) != 0;
    }

    public boolean isStopActionSupported() {
        return (this.mState.r() & 1) != 0;
    }

    public boolean isVolumeControlAvailable(MediaRouter.RouteInfo routeInfo) {
        return this.mVolumeControlEnabled && routeInfo.getVolumeHandling() == 1;
    }

    public boolean isVolumeControlEnabled() {
        return this.mVolumeControlEnabled;
    }

    public void loadInterpolator() {
        this.mInterpolator = this.mIsGroupExpanded ? this.mLinearOutSlowInInterpolator : this.mFastOutSlowInInterpolator;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(MediaRouteSelector.EMPTY, this.mCallback, 2);
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    @Override // androidx.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.mr_controller_material_dialog_b);
        findViewById(16908315).setVisibility(8);
        ClickListener clickListener = new ClickListener();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mr_expandable_area);
        this.mExpandableAreaLayout = frameLayout;
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaRouteControllerDialog.this.dismiss();
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mr_dialog_area);
        this.mDialogAreaLayout = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        int buttonTextColor = MediaRouterThemeHelper.getButtonTextColor(this.mContext);
        Button button = (Button) findViewById(16908314);
        this.mDisconnectButton = button;
        button.setText(R.string.mr_controller_disconnect);
        this.mDisconnectButton.setTextColor(buttonTextColor);
        this.mDisconnectButton.setOnClickListener(clickListener);
        Button button2 = (Button) findViewById(16908313);
        this.mStopCastingButton = button2;
        button2.setText(R.string.mr_controller_stop_casting);
        this.mStopCastingButton.setTextColor(buttonTextColor);
        this.mStopCastingButton.setOnClickListener(clickListener);
        this.mRouteNameTextView = (TextView) findViewById(R.id.mr_name);
        ImageButton imageButton = (ImageButton) findViewById(R.id.mr_close);
        this.mCloseButton = imageButton;
        imageButton.setOnClickListener(clickListener);
        this.mCustomControlLayout = (FrameLayout) findViewById(R.id.mr_custom_control);
        this.mDefaultControlLayout = (FrameLayout) findViewById(R.id.mr_default_control);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PendingIntent pendingIntentD;
                MediaControllerCompat mediaControllerCompat = MediaRouteControllerDialog.this.mMediaController;
                if (mediaControllerCompat == null || (pendingIntentD = mediaControllerCompat.d()) == null) {
                    return;
                }
                try {
                    pendingIntentD.send();
                    MediaRouteControllerDialog.this.dismiss();
                } catch (PendingIntent.CanceledException unused) {
                    Log.e(MediaRouteControllerDialog.TAG, pendingIntentD + " was not sent, it had been canceled.");
                }
            }
        };
        ImageView imageView = (ImageView) findViewById(R.id.mr_art);
        this.mArtView = imageView;
        imageView.setOnClickListener(onClickListener);
        findViewById(R.id.mr_control_title_container).setOnClickListener(onClickListener);
        this.mMediaMainControlLayout = (LinearLayout) findViewById(R.id.mr_media_main_control);
        this.mDividerView = findViewById(R.id.mr_control_divider);
        this.mPlaybackControlLayout = (RelativeLayout) findViewById(R.id.mr_playback_control);
        this.mTitleView = (TextView) findViewById(R.id.mr_control_title);
        this.mSubtitleView = (TextView) findViewById(R.id.mr_control_subtitle);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.mr_control_playback_ctrl);
        this.mPlaybackControlButton = imageButton2;
        imageButton2.setOnClickListener(clickListener);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.mr_volume_control);
        this.mVolumeControlLayout = linearLayout2;
        linearLayout2.setVisibility(8);
        SeekBar seekBar = (SeekBar) findViewById(R.id.mr_volume_slider);
        this.mVolumeSlider = seekBar;
        seekBar.setTag(this.mRoute);
        VolumeChangeListener volumeChangeListener = new VolumeChangeListener();
        this.mVolumeChangeListener = volumeChangeListener;
        this.mVolumeSlider.setOnSeekBarChangeListener(volumeChangeListener);
        this.mVolumeGroupList = (OverlayListView) findViewById(R.id.mr_volume_group_list);
        this.mGroupMemberRoutes = new ArrayList();
        VolumeGroupAdapter volumeGroupAdapter = new VolumeGroupAdapter(this.mVolumeGroupList.getContext(), this.mGroupMemberRoutes);
        this.mVolumeGroupAdapter = volumeGroupAdapter;
        this.mVolumeGroupList.setAdapter((ListAdapter) volumeGroupAdapter);
        this.mGroupMemberRoutesAnimatingWithBitmap = new HashSet();
        MediaRouterThemeHelper.setMediaControlsBackgroundColor(this.mContext, this.mMediaMainControlLayout, this.mVolumeGroupList, isGroup());
        MediaRouterThemeHelper.setVolumeSliderColor(this.mContext, (MediaRouteVolumeSlider) this.mVolumeSlider, this.mMediaMainControlLayout);
        HashMap map = new HashMap();
        this.mVolumeSliderMap = map;
        map.put(this.mRoute, this.mVolumeSlider);
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = (MediaRouteExpandCollapseButton) findViewById(R.id.mr_group_expand_collapse);
        this.mGroupExpandCollapseButton = mediaRouteExpandCollapseButton;
        mediaRouteExpandCollapseButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
                boolean z2 = mediaRouteControllerDialog.mIsGroupExpanded;
                mediaRouteControllerDialog.mIsGroupExpanded = !z2;
                if (!z2) {
                    mediaRouteControllerDialog.mVolumeGroupList.setVisibility(0);
                }
                MediaRouteControllerDialog.this.loadInterpolator();
                MediaRouteControllerDialog.this.updateLayoutHeight(true);
            }
        });
        loadInterpolator();
        this.mGroupListAnimationDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_animation_duration_ms);
        this.mGroupListFadeInDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_fade_in_duration_ms);
        this.mGroupListFadeOutDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_fade_out_duration_ms);
        View viewOnCreateMediaControlView = onCreateMediaControlView(bundle);
        this.mCustomControlView = viewOnCreateMediaControlView;
        if (viewOnCreateMediaControlView != null) {
            this.mCustomControlLayout.addView(viewOnCreateMediaControlView);
            this.mCustomControlLayout.setVisibility(0);
        }
        this.mCreated = true;
        updateLayout();
    }

    @Nullable
    public View onCreateMediaControlView(@Nullable Bundle bundle) {
        return null;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mRouter.removeCallback(this.mCallback);
        setMediaSession(null);
        this.mAttachedToWindow = false;
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.AlertDialog, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, @NonNull KeyEvent keyEvent) {
        if (i2 != 25 && i2 != 24) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (this.mEnableGroupVolumeUX || !this.mIsGroupExpanded) {
            this.mRoute.requestUpdateVolume(i2 == 25 ? -1 : 1);
        }
        return true;
    }

    @Override // androidx.appcompat.app.AlertDialog, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, @NonNull KeyEvent keyEvent) {
        if (i2 == 25 || i2 == 24) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public void setVolumeControlEnabled(boolean z2) {
        if (this.mVolumeControlEnabled != z2) {
            this.mVolumeControlEnabled = z2;
            if (this.mCreated) {
                update(false);
            }
        }
    }

    public void startGroupListFadeInAnimation() {
        clearGroupListAnimation(true);
        this.mVolumeGroupList.requestLayout();
        this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.11
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                MediaRouteControllerDialog.this.startGroupListFadeInAnimationInternal();
            }
        });
    }

    public void startGroupListFadeInAnimationInternal() {
        Set<MediaRouter.RouteInfo> set = this.mGroupMemberRoutesAdded;
        if (set == null || set.size() == 0) {
            finishAnimation(true);
        } else {
            fadeInAddedRoutes();
        }
    }

    public void update(boolean z2) {
        if (this.mRouteInVolumeSliderTouched != null) {
            this.mHasPendingUpdate = true;
            this.mPendingUpdateAnimationNeeded = z2 | this.mPendingUpdateAnimationNeeded;
            return;
        }
        this.mHasPendingUpdate = false;
        this.mPendingUpdateAnimationNeeded = false;
        if (!this.mRoute.isSelected() || this.mRoute.isDefaultOrBluetooth()) {
            dismiss();
            return;
        }
        if (this.mCreated) {
            this.mRouteNameTextView.setText(this.mRoute.getName());
            this.mDisconnectButton.setVisibility(this.mRoute.canDisconnect() ? 0 : 8);
            if (this.mCustomControlView == null && this.mArtIconIsLoaded) {
                if (isBitmapRecycled(this.mArtIconLoadedBitmap)) {
                    Log.w(TAG, "Can't set artwork image with recycled bitmap: " + this.mArtIconLoadedBitmap);
                } else {
                    this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
                    this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
                }
                clearLoadedBitmap();
            }
            updateVolumeControlLayout();
            updatePlaybackControlLayout();
            updateLayoutHeight(z2);
        }
    }

    public void updateArtIconIfNeeded() {
        if (this.mCustomControlView == null && isIconChanged()) {
            if (!isGroup() || this.mEnableGroupVolumeUX) {
                FetchArtTask fetchArtTask = this.mFetchArtTask;
                if (fetchArtTask != null) {
                    fetchArtTask.cancel(true);
                }
                FetchArtTask fetchArtTask2 = new FetchArtTask();
                this.mFetchArtTask = fetchArtTask2;
                fetchArtTask2.execute(new Void[0]);
            }
        }
    }

    public void updateLayout() {
        int dialogWidth = MediaRouteDialogHelper.getDialogWidth(this.mContext);
        getWindow().setLayout(dialogWidth, -2);
        View decorView = getWindow().getDecorView();
        this.mDialogContentWidth = (dialogWidth - decorView.getPaddingLeft()) - decorView.getPaddingRight();
        Resources resources = this.mContext.getResources();
        this.mVolumeGroupListItemIconSize = resources.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_item_icon_size);
        this.mVolumeGroupListItemHeight = resources.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_item_height);
        this.mVolumeGroupListMaxHeight = resources.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_max_height);
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        updateArtIconIfNeeded();
        update(false);
    }

    public void updateLayoutHeight(final boolean z2) {
        this.mDefaultControlLayout.requestLayout();
        this.mDefaultControlLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.6
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MediaRouteControllerDialog.this.mDefaultControlLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
                if (mediaRouteControllerDialog.mIsGroupListAnimating) {
                    mediaRouteControllerDialog.mIsGroupListAnimationPending = true;
                } else {
                    mediaRouteControllerDialog.updateLayoutHeightInternal(z2);
                }
            }
        });
    }

    public void updateLayoutHeightInternal(boolean z2) {
        int desiredArtHeight;
        Bitmap bitmap;
        int layoutHeight = getLayoutHeight(this.mMediaMainControlLayout);
        setLayoutHeight(this.mMediaMainControlLayout, -1);
        updateMediaControlVisibility(canShowPlaybackControlLayout());
        View decorView = getWindow().getDecorView();
        decorView.measure(View.MeasureSpec.makeMeasureSpec(getWindow().getAttributes().width, BasicMeasure.EXACTLY), 0);
        setLayoutHeight(this.mMediaMainControlLayout, layoutHeight);
        if (this.mCustomControlView == null && (this.mArtView.getDrawable() instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) this.mArtView.getDrawable()).getBitmap()) != null) {
            desiredArtHeight = getDesiredArtHeight(bitmap.getWidth(), bitmap.getHeight());
            this.mArtView.setScaleType(bitmap.getWidth() >= bitmap.getHeight() ? ImageView.ScaleType.FIT_XY : ImageView.ScaleType.FIT_CENTER);
        } else {
            desiredArtHeight = 0;
        }
        int mainControllerHeight = getMainControllerHeight(canShowPlaybackControlLayout());
        int size = this.mGroupMemberRoutes.size();
        int size2 = isGroup() ? this.mVolumeGroupListItemHeight * this.mRoute.getMemberRoutes().size() : 0;
        if (size > 0) {
            size2 += this.mVolumeGroupListPaddingTop;
        }
        int iMin = Math.min(size2, this.mVolumeGroupListMaxHeight);
        if (!this.mIsGroupExpanded) {
            iMin = 0;
        }
        int iMax = Math.max(desiredArtHeight, iMin) + mainControllerHeight;
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int iHeight = rect.height() - (this.mDialogAreaLayout.getMeasuredHeight() - this.mDefaultControlLayout.getMeasuredHeight());
        if (this.mCustomControlView != null || desiredArtHeight <= 0 || iMax > iHeight) {
            if (getLayoutHeight(this.mVolumeGroupList) + this.mMediaMainControlLayout.getMeasuredHeight() >= this.mDefaultControlLayout.getMeasuredHeight()) {
                this.mArtView.setVisibility(8);
            }
            iMax = iMin + mainControllerHeight;
            desiredArtHeight = 0;
        } else {
            this.mArtView.setVisibility(0);
            setLayoutHeight(this.mArtView, desiredArtHeight);
        }
        if (!canShowPlaybackControlLayout() || iMax > iHeight) {
            this.mPlaybackControlLayout.setVisibility(8);
        } else {
            this.mPlaybackControlLayout.setVisibility(0);
        }
        updateMediaControlVisibility(this.mPlaybackControlLayout.getVisibility() == 0);
        int mainControllerHeight2 = getMainControllerHeight(this.mPlaybackControlLayout.getVisibility() == 0);
        int iMax2 = Math.max(desiredArtHeight, iMin) + mainControllerHeight2;
        if (iMax2 > iHeight) {
            iMin -= iMax2 - iHeight;
        } else {
            iHeight = iMax2;
        }
        this.mMediaMainControlLayout.clearAnimation();
        this.mVolumeGroupList.clearAnimation();
        this.mDefaultControlLayout.clearAnimation();
        if (z2) {
            animateLayoutHeight(this.mMediaMainControlLayout, mainControllerHeight2);
            animateLayoutHeight(this.mVolumeGroupList, iMin);
            animateLayoutHeight(this.mDefaultControlLayout, iHeight);
        } else {
            setLayoutHeight(this.mMediaMainControlLayout, mainControllerHeight2);
            setLayoutHeight(this.mVolumeGroupList, iMin);
            setLayoutHeight(this.mDefaultControlLayout, iHeight);
        }
        setLayoutHeight(this.mExpandableAreaLayout, rect.height());
        rebuildVolumeGroupList(z2);
    }

    public void updateVolumeGroupItemHeight(View view) {
        setLayoutHeight((LinearLayout) view.findViewById(R.id.volume_item_container), this.mVolumeGroupListItemHeight);
        View viewFindViewById = view.findViewById(R.id.mr_volume_item_icon);
        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
        int i2 = this.mVolumeGroupListItemIconSize;
        layoutParams.width = i2;
        layoutParams.height = i2;
        viewFindViewById.setLayoutParams(layoutParams);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteControllerDialog(@NonNull Context context, int i2) {
        Context contextCreateThemedDialogContext = MediaRouterThemeHelper.createThemedDialogContext(context, i2, true);
        super(contextCreateThemedDialogContext, MediaRouterThemeHelper.createThemedDialogStyle(contextCreateThemedDialogContext));
        this.mVolumeControlEnabled = true;
        this.mGroupListFadeInAnimation = new Runnable() { // from class: androidx.mediarouter.app.MediaRouteControllerDialog.1
            @Override // java.lang.Runnable
            public void run() {
                MediaRouteControllerDialog.this.startGroupListFadeInAnimation();
            }
        };
        this.mContext = getContext();
        this.mControllerCallback = new MediaControllerCallback();
        MediaRouter mediaRouter = MediaRouter.getInstance(this.mContext);
        this.mRouter = mediaRouter;
        this.mEnableGroupVolumeUX = MediaRouter.isGroupVolumeUxEnabled();
        this.mCallback = new MediaRouterCallback();
        this.mRoute = mediaRouter.getSelectedRoute();
        setMediaSession(mediaRouter.getMediaSessionToken());
        this.mVolumeGroupListPaddingTop = this.mContext.getResources().getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_padding_top);
        this.mAccessibilityManager = (AccessibilityManager) this.mContext.getSystemService("accessibility");
        this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(contextCreateThemedDialogContext, R.interpolator.mr_linear_out_slow_in);
        this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(contextCreateThemedDialogContext, R.interpolator.mr_fast_out_slow_in);
        this.mAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    }
}
