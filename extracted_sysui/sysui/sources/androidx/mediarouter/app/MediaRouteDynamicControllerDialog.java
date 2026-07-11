package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miui.systemui.notification.focus.Const;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class MediaRouteDynamicControllerDialog extends AppCompatDialog {
    private static final int BLUR_RADIUS = 10;
    private static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
    private static final int CONNECTION_TIMEOUT_MS = 30000;
    static final boolean DEBUG = false;
    private static final int MIN_UNMUTED_VOLUME = 1;
    private static final int MSG_UPDATE_ROUTES_VIEW = 1;
    private static final int MSG_UPDATE_ROUTE_VOLUME_BY_USER = 2;
    private static final int MUTED_VOLUME = 0;
    private static final String TAG = "MediaRouteCtrlDialog";
    private static final int UPDATE_ROUTES_VIEW_DELAY_MS = 300;
    private static final int UPDATE_VOLUME_DELAY_MS = 500;
    RecyclerAdapter mAdapter;
    int mArtIconBackgroundColor;
    Bitmap mArtIconBitmap;
    boolean mArtIconIsLoaded;
    Bitmap mArtIconLoadedBitmap;
    Uri mArtIconUri;
    ImageView mArtView;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    MediaControllerCallback mControllerCallback;
    private boolean mCreated;
    MediaDescriptionCompat mDescription;
    final boolean mEnableGroupVolumeUX;
    FetchArtTask mFetchArtTask;
    final List<MediaRouter.RouteInfo> mGroupableRoutes;
    final Handler mHandler;
    boolean mIsAnimatingVolumeSliderLayout;
    boolean mIsSelectingRoute;
    private long mLastUpdateTime;
    MediaControllerCompat mMediaController;
    final List<MediaRouter.RouteInfo> mMemberRoutes;
    private ImageView mMetadataBackground;
    private View mMetadataBlackScrim;
    RecyclerView mRecyclerView;
    MediaRouter.RouteInfo mRouteForVolumeUpdatingByUser;
    final MediaRouter mRouter;
    MediaRouter.RouteInfo mSelectedRoute;
    private MediaRouteSelector mSelector;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private String mTitlePlaceholder;
    private TextView mTitleView;
    final List<MediaRouter.RouteInfo> mTransferableRoutes;
    final List<MediaRouter.RouteInfo> mUngroupableRoutes;
    Map<String, Integer> mUnmutedVolumeMap;
    private boolean mUpdateMetadataViewsDeferred;
    private boolean mUpdateRoutesViewDeferred;
    VolumeChangeListener mVolumeChangeListener;
    Map<String, MediaRouteVolumeSliderHolder> mVolumeSliderHolderMap;

    public class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;

        public FetchArtTask() {
            MediaDescriptionCompat mediaDescriptionCompat = MediaRouteDynamicControllerDialog.this.mDescription;
            Bitmap bitmapR = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.r();
            if (MediaRouteDynamicControllerDialog.isBitmapRecycled(bitmapR)) {
                Log.w(MediaRouteDynamicControllerDialog.TAG, "Can't fetch the given art bitmap because it's already recycled.");
                bitmapR = null;
            }
            this.mIconBitmap = bitmapR;
            MediaDescriptionCompat mediaDescriptionCompat2 = MediaRouteDynamicControllerDialog.this.mDescription;
            this.mIconUri = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.s() : null;
        }

        private InputStream openInputStreamByScheme(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || Const.Param.CONTENT.equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = MediaRouteDynamicControllerDialog.this.mContext.getContentResolver().openInputStream(uri);
            } else {
                URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(30000);
                uRLConnectionOpenConnection.setReadTimeout(30000);
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
            MediaRouteDynamicControllerDialog.this.clearLoadedBitmap();
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
                                    Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri);
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
                                        Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri);
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
                                options.inSampleSize = Math.max(1, Integer.highestOneBit(options.outHeight / MediaRouteDynamicControllerDialog.this.mContext.getResources().getDimensionPixelSize(R.dimen.mr_cast_meta_art_size)));
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
                                Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri, e);
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
            if (MediaRouteDynamicControllerDialog.isBitmapRecycled(bitmap)) {
                Log.w(MediaRouteDynamicControllerDialog.TAG, "Can't use recycled bitmap: " + bitmap);
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
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.mFetchArtTask = null;
            if (ObjectsCompat.equals(mediaRouteDynamicControllerDialog.mArtIconBitmap, this.mIconBitmap) && ObjectsCompat.equals(MediaRouteDynamicControllerDialog.this.mArtIconUri, this.mIconUri)) {
                return;
            }
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog2 = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog2.mArtIconBitmap = this.mIconBitmap;
            mediaRouteDynamicControllerDialog2.mArtIconLoadedBitmap = bitmap;
            mediaRouteDynamicControllerDialog2.mArtIconUri = this.mIconUri;
            mediaRouteDynamicControllerDialog2.mArtIconBackgroundColor = this.mBackgroundColor;
            mediaRouteDynamicControllerDialog2.mArtIconIsLoaded = true;
            mediaRouteDynamicControllerDialog2.updateMetadataViews();
        }
    }

    public final class MediaControllerCallback extends MediaControllerCompat.a {
        public MediaControllerCallback() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaRouteDynamicControllerDialog.this.mDescription = mediaMetadataCompat == null ? null : mediaMetadataCompat.t();
            MediaRouteDynamicControllerDialog.this.reloadIconIfNeeded();
            MediaRouteDynamicControllerDialog.this.updateMetadataViews();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void onSessionDestroyed() {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            MediaControllerCompat mediaControllerCompat = mediaRouteDynamicControllerDialog.mMediaController;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.i(mediaRouteDynamicControllerDialog.mControllerCallback);
                MediaRouteDynamicControllerDialog.this.mMediaController = null;
            }
        }
    }

    public abstract class MediaRouteVolumeSliderHolder extends RecyclerView.ViewHolder {
        final ImageButton mMuteButton;
        MediaRouter.RouteInfo mRoute;
        final MediaRouteVolumeSlider mVolumeSlider;

        public MediaRouteVolumeSliderHolder(View view, ImageButton imageButton, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
            super(view);
            this.mMuteButton = imageButton;
            this.mVolumeSlider = mediaRouteVolumeSlider;
            imageButton.setImageDrawable(MediaRouterThemeHelper.getMuteButtonDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext));
            MediaRouterThemeHelper.setVolumeSliderColor(MediaRouteDynamicControllerDialog.this.mContext, mediaRouteVolumeSlider);
        }

        @CallSuper
        public void bindRouteVolumeSliderHolder(MediaRouter.RouteInfo routeInfo) {
            this.mRoute = routeInfo;
            int volume = routeInfo.getVolume();
            this.mMuteButton.setActivated(volume == 0);
            this.mMuteButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.MediaRouteVolumeSliderHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
                    if (mediaRouteDynamicControllerDialog.mRouteForVolumeUpdatingByUser != null) {
                        mediaRouteDynamicControllerDialog.mHandler.removeMessages(2);
                    }
                    MediaRouteVolumeSliderHolder mediaRouteVolumeSliderHolder = MediaRouteVolumeSliderHolder.this;
                    MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser = mediaRouteVolumeSliderHolder.mRoute;
                    boolean zIsActivated = view.isActivated();
                    boolean z2 = !zIsActivated;
                    int unmutedVolume = !zIsActivated ? 0 : MediaRouteVolumeSliderHolder.this.getUnmutedVolume();
                    MediaRouteVolumeSliderHolder.this.setMute(z2);
                    MediaRouteVolumeSliderHolder.this.mVolumeSlider.setProgress(unmutedVolume);
                    MediaRouteVolumeSliderHolder.this.mRoute.requestSetVolume(unmutedVolume);
                    MediaRouteDynamicControllerDialog.this.mHandler.sendEmptyMessageDelayed(2, 500L);
                }
            });
            this.mVolumeSlider.setTag(this.mRoute);
            this.mVolumeSlider.setMax(routeInfo.getVolumeMax());
            this.mVolumeSlider.setProgress(volume);
            this.mVolumeSlider.setOnSeekBarChangeListener(MediaRouteDynamicControllerDialog.this.mVolumeChangeListener);
        }

        public int getUnmutedVolume() {
            Integer num = MediaRouteDynamicControllerDialog.this.mUnmutedVolumeMap.get(this.mRoute.getId());
            if (num == null) {
                return 1;
            }
            return Math.max(1, num.intValue());
        }

        public void setMute(boolean z2) {
            if (this.mMuteButton.isActivated() == z2) {
                return;
            }
            this.mMuteButton.setActivated(z2);
            if (z2) {
                MediaRouteDynamicControllerDialog.this.mUnmutedVolumeMap.put(this.mRoute.getId(), Integer.valueOf(this.mVolumeSlider.getProgress()));
            } else {
                MediaRouteDynamicControllerDialog.this.mUnmutedVolumeMap.remove(this.mRoute.getId());
            }
        }

        public void updateVolume() {
            int volume = this.mRoute.getVolume();
            setMute(volume == 0);
            this.mVolumeSlider.setProgress(volume);
        }
    }

    public final class MediaRouterCallback extends MediaRouter.Callback {
        public MediaRouterCallback() {
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicControllerDialog.this.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouter.RouteInfo.DynamicGroupState dynamicGroupState;
            if (routeInfo == MediaRouteDynamicControllerDialog.this.mSelectedRoute && routeInfo.getDynamicGroupController() != null) {
                for (MediaRouter.RouteInfo routeInfo2 : routeInfo.getProvider().getRoutes()) {
                    if (!MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes().contains(routeInfo2) && (dynamicGroupState = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getDynamicGroupState(routeInfo2)) != null && dynamicGroupState.isGroupable() && !MediaRouteDynamicControllerDialog.this.mGroupableRoutes.contains(routeInfo2)) {
                        MediaRouteDynamicControllerDialog.this.updateViewsIfNeeded();
                        MediaRouteDynamicControllerDialog.this.updateRoutes();
                        return;
                    }
                }
            }
            MediaRouteDynamicControllerDialog.this.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicControllerDialog.this.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.mSelectedRoute = routeInfo;
            mediaRouteDynamicControllerDialog.mIsSelectingRoute = false;
            mediaRouteDynamicControllerDialog.updateViewsIfNeeded();
            MediaRouteDynamicControllerDialog.this.updateRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicControllerDialog.this.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteVolumeChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteVolumeSliderHolder mediaRouteVolumeSliderHolder;
            int volume = routeInfo.getVolume();
            if (MediaRouteDynamicControllerDialog.DEBUG) {
                Log.d(MediaRouteDynamicControllerDialog.TAG, "onRouteVolumeChanged(), route.getVolume:" + volume);
            }
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (mediaRouteDynamicControllerDialog.mRouteForVolumeUpdatingByUser == routeInfo || (mediaRouteVolumeSliderHolder = mediaRouteDynamicControllerDialog.mVolumeSliderHolderMap.get(routeInfo.getId())) == null) {
                return;
            }
            mediaRouteVolumeSliderHolder.updateVolume();
        }
    }

    public final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int ITEM_TYPE_GROUP = 4;
        private static final int ITEM_TYPE_GROUP_VOLUME = 1;
        private static final int ITEM_TYPE_HEADER = 2;
        private static final int ITEM_TYPE_ROUTE = 3;
        private final Drawable mDefaultIcon;
        private Item mGroupVolumeItem;
        private final LayoutInflater mInflater;
        private final int mLayoutAnimationDurationMs;
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;
        private final ArrayList<Item> mItems = new ArrayList<>();
        private final Interpolator mAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();

        public class GroupViewHolder extends RecyclerView.ViewHolder {
            final float mDisabledAlpha;
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            MediaRouter.RouteInfo mRoute;
            final TextView mTextView;

            public GroupViewHolder(View view) {
                super(view);
                this.mItemView = view;
                this.mImageView = (ImageView) view.findViewById(R.id.mr_cast_group_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_cast_group_progress_bar);
                this.mProgressBar = progressBar;
                this.mTextView = (TextView) view.findViewById(R.id.mr_cast_group_name);
                this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(MediaRouteDynamicControllerDialog.this.mContext);
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(MediaRouteDynamicControllerDialog.this.mContext, progressBar);
            }

            private boolean isEnabled(MediaRouter.RouteInfo routeInfo) {
                List<MediaRouter.RouteInfo> memberRoutes = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes();
                return (memberRoutes.size() == 1 && memberRoutes.get(0) == routeInfo) ? false : true;
            }

            public void bindGroupViewHolder(Item item) {
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) item.getData();
                this.mRoute = routeInfo;
                this.mImageView.setVisibility(0);
                this.mProgressBar.setVisibility(4);
                this.mItemView.setAlpha(isEnabled(routeInfo) ? 1.0f : this.mDisabledAlpha);
                this.mItemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.GroupViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        GroupViewHolder groupViewHolder = GroupViewHolder.this;
                        MediaRouteDynamicControllerDialog.this.mRouter.transferToRoute(groupViewHolder.mRoute);
                        GroupViewHolder.this.mImageView.setVisibility(4);
                        GroupViewHolder.this.mProgressBar.setVisibility(0);
                    }
                });
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
                this.mTextView.setText(routeInfo.getName());
            }
        }

        public class GroupVolumeViewHolder extends MediaRouteVolumeSliderHolder {
            private final int mExpandedHeight;
            private final TextView mTextView;

            public GroupVolumeViewHolder(View view) {
                super(view, (ImageButton) view.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R.id.mr_cast_volume_slider));
                this.mTextView = (TextView) view.findViewById(R.id.mr_group_volume_route_name);
                Resources resources = MediaRouteDynamicControllerDialog.this.mContext.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R.dimen.mr_dynamic_volume_group_list_item_height, typedValue, true);
                this.mExpandedHeight = (int) typedValue.getDimension(displayMetrics);
            }

            public void bindGroupVolumeViewHolder(Item item) {
                MediaRouteDynamicControllerDialog.setLayoutHeight(this.itemView, RecyclerAdapter.this.isGroupVolumeNeeded() ? this.mExpandedHeight : 0);
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) item.getData();
                super.bindRouteVolumeSliderHolder(routeInfo);
                this.mTextView.setText(routeInfo.getName());
            }

            public int getExpandedHeight() {
                return this.mExpandedHeight;
            }
        }

        public class HeaderViewHolder extends RecyclerView.ViewHolder {
            private final TextView mTextView;

            public HeaderViewHolder(View view) {
                super(view);
                this.mTextView = (TextView) view.findViewById(R.id.mr_cast_header_name);
            }

            public void bindHeaderViewHolder(Item item) {
                this.mTextView.setText(item.getData().toString());
            }
        }

        public class Item {
            private final Object mData;
            private final int mType;

            public Item(Object obj, int i2) {
                this.mData = obj;
                this.mType = i2;
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        public class RouteViewHolder extends MediaRouteVolumeSliderHolder {
            final CheckBox mCheckBox;
            final int mCollapsedLayoutHeight;
            final float mDisabledAlpha;
            final int mExpandedLayoutHeight;
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            final TextView mTextView;
            final View.OnClickListener mViewClickListener;
            final RelativeLayout mVolumeSliderLayout;

            public RouteViewHolder(View view) {
                super(view, (ImageButton) view.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R.id.mr_cast_volume_slider));
                this.mViewClickListener = new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.RouteViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        RouteViewHolder routeViewHolder = RouteViewHolder.this;
                        boolean zIsSelected = routeViewHolder.isSelected(routeViewHolder.mRoute);
                        boolean z2 = !zIsSelected;
                        boolean zIsGroup = RouteViewHolder.this.mRoute.isGroup();
                        if (zIsSelected) {
                            RouteViewHolder routeViewHolder2 = RouteViewHolder.this;
                            MediaRouteDynamicControllerDialog.this.mRouter.removeMemberFromDynamicGroup(routeViewHolder2.mRoute);
                        } else {
                            RouteViewHolder routeViewHolder3 = RouteViewHolder.this;
                            MediaRouteDynamicControllerDialog.this.mRouter.addMemberToDynamicGroup(routeViewHolder3.mRoute);
                        }
                        RouteViewHolder.this.showSelectingProgress(z2, !zIsGroup);
                        if (zIsGroup) {
                            List<MediaRouter.RouteInfo> memberRoutes = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes();
                            for (MediaRouter.RouteInfo routeInfo : RouteViewHolder.this.mRoute.getMemberRoutes()) {
                                if (memberRoutes.contains(routeInfo) != z2) {
                                    MediaRouteVolumeSliderHolder mediaRouteVolumeSliderHolder = MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.get(routeInfo.getId());
                                    if (mediaRouteVolumeSliderHolder instanceof RouteViewHolder) {
                                        ((RouteViewHolder) mediaRouteVolumeSliderHolder).showSelectingProgress(z2, true);
                                    }
                                }
                            }
                        }
                        RouteViewHolder routeViewHolder4 = RouteViewHolder.this;
                        RecyclerAdapter.this.mayUpdateGroupVolume(routeViewHolder4.mRoute, z2);
                    }
                };
                this.mItemView = view;
                this.mImageView = (ImageView) view.findViewById(R.id.mr_cast_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_cast_route_progress_bar);
                this.mProgressBar = progressBar;
                this.mTextView = (TextView) view.findViewById(R.id.mr_cast_route_name);
                this.mVolumeSliderLayout = (RelativeLayout) view.findViewById(R.id.mr_cast_volume_layout);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.mr_cast_checkbox);
                this.mCheckBox = checkBox;
                checkBox.setButtonDrawable(MediaRouterThemeHelper.getCheckBoxDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext));
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(MediaRouteDynamicControllerDialog.this.mContext, progressBar);
                this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(MediaRouteDynamicControllerDialog.this.mContext);
                Resources resources = MediaRouteDynamicControllerDialog.this.mContext.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R.dimen.mr_dynamic_dialog_row_height, typedValue, true);
                this.mExpandedLayoutHeight = (int) typedValue.getDimension(displayMetrics);
                this.mCollapsedLayoutHeight = 0;
            }

            private boolean isEnabled(MediaRouter.RouteInfo routeInfo) {
                if (MediaRouteDynamicControllerDialog.this.mUngroupableRoutes.contains(routeInfo)) {
                    return false;
                }
                if (isSelected(routeInfo) && MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes().size() < 2) {
                    return false;
                }
                if (!isSelected(routeInfo)) {
                    return true;
                }
                MediaRouter.RouteInfo.DynamicGroupState dynamicGroupState = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getDynamicGroupState(routeInfo);
                return dynamicGroupState != null && dynamicGroupState.isUnselectable();
            }

            public void bindRouteViewHolder(Item item) {
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) item.getData();
                if (routeInfo == MediaRouteDynamicControllerDialog.this.mSelectedRoute && routeInfo.getMemberRoutes().size() > 0) {
                    Iterator<MediaRouter.RouteInfo> it = routeInfo.getMemberRoutes().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        MediaRouter.RouteInfo next = it.next();
                        if (!MediaRouteDynamicControllerDialog.this.mGroupableRoutes.contains(next)) {
                            routeInfo = next;
                            break;
                        }
                    }
                }
                bindRouteVolumeSliderHolder(routeInfo);
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
                this.mTextView.setText(routeInfo.getName());
                this.mCheckBox.setVisibility(0);
                boolean zIsSelected = isSelected(routeInfo);
                boolean zIsEnabled = isEnabled(routeInfo);
                this.mCheckBox.setChecked(zIsSelected);
                this.mProgressBar.setVisibility(4);
                this.mImageView.setVisibility(0);
                this.mItemView.setEnabled(zIsEnabled);
                this.mCheckBox.setEnabled(zIsEnabled);
                this.mMuteButton.setEnabled(zIsEnabled || zIsSelected);
                this.mVolumeSlider.setEnabled(zIsEnabled || zIsSelected);
                this.mItemView.setOnClickListener(this.mViewClickListener);
                this.mCheckBox.setOnClickListener(this.mViewClickListener);
                MediaRouteDynamicControllerDialog.setLayoutHeight(this.mVolumeSliderLayout, (!zIsSelected || this.mRoute.isGroup()) ? this.mCollapsedLayoutHeight : this.mExpandedLayoutHeight);
                float f2 = 1.0f;
                this.mItemView.setAlpha((zIsEnabled || zIsSelected) ? 1.0f : this.mDisabledAlpha);
                CheckBox checkBox = this.mCheckBox;
                if (!zIsEnabled && zIsSelected) {
                    f2 = this.mDisabledAlpha;
                }
                checkBox.setAlpha(f2);
            }

            public boolean isSelected(MediaRouter.RouteInfo routeInfo) {
                if (routeInfo.isSelected()) {
                    return true;
                }
                MediaRouter.RouteInfo.DynamicGroupState dynamicGroupState = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getDynamicGroupState(routeInfo);
                return dynamicGroupState != null && dynamicGroupState.getSelectionState() == 3;
            }

            public void showSelectingProgress(boolean z2, boolean z3) {
                this.mCheckBox.setEnabled(false);
                this.mItemView.setEnabled(false);
                this.mCheckBox.setChecked(z2);
                if (z2) {
                    this.mImageView.setVisibility(4);
                    this.mProgressBar.setVisibility(0);
                }
                if (z3) {
                    RecyclerAdapter.this.animateLayoutHeight(this.mVolumeSliderLayout, z2 ? this.mExpandedLayoutHeight : this.mCollapsedLayoutHeight);
                }
            }
        }

        public RecyclerAdapter() {
            this.mInflater = LayoutInflater.from(MediaRouteDynamicControllerDialog.this.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGroupDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mLayoutAnimationDurationMs = MediaRouteDynamicControllerDialog.this.mContext.getResources().getInteger(R.integer.mr_cast_volume_slider_layout_animation_duration_ms);
            updateItems();
        }

        private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo routeInfo) {
            int deviceType = routeInfo.getDeviceType();
            return deviceType != 1 ? deviceType != 2 ? routeInfo.isGroup() ? this.mSpeakerGroupIcon : this.mDefaultIcon : this.mSpeakerIcon : this.mTvIcon;
        }

        public void animateLayoutHeight(final View view, final int i2) {
            final int i3 = view.getLayoutParams().height;
            Animation animation = new Animation() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.1
                @Override // android.view.animation.Animation
                public void applyTransformation(float f2, Transformation transformation) {
                    int i4 = i2;
                    MediaRouteDynamicControllerDialog.setLayoutHeight(view, i3 + ((int) ((i4 - r0) * f2)));
                }
            };
            animation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
                    mediaRouteDynamicControllerDialog.mIsAnimatingVolumeSliderLayout = false;
                    mediaRouteDynamicControllerDialog.updateViewsIfNeeded();
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                    MediaRouteDynamicControllerDialog.this.mIsAnimatingVolumeSliderLayout = true;
                }
            });
            animation.setDuration(this.mLayoutAnimationDurationMs);
            animation.setInterpolator(this.mAccelerateDecelerateInterpolator);
            view.startAnimation(animation);
        }

        public Drawable getIconDrawable(MediaRouter.RouteInfo routeInfo) {
            Uri iconUri = routeInfo.getIconUri();
            if (iconUri != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(MediaRouteDynamicControllerDialog.this.mContext.getContentResolver().openInputStream(iconUri), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException e2) {
                    Log.w(MediaRouteDynamicControllerDialog.TAG, "Failed to load " + iconUri, e2);
                }
            }
            return getDefaultIconDrawable(routeInfo);
        }

        public Item getItem(int i2) {
            return i2 == 0 ? this.mGroupVolumeItem : this.mItems.get(i2 - 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            return getItem(i2).getType();
        }

        public boolean isGroupVolumeNeeded() {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            return mediaRouteDynamicControllerDialog.mEnableGroupVolumeUX && mediaRouteDynamicControllerDialog.mSelectedRoute.getMemberRoutes().size() > 1;
        }

        public void mayUpdateGroupVolume(MediaRouter.RouteInfo routeInfo, boolean z2) {
            List<MediaRouter.RouteInfo> memberRoutes = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes();
            int iMax = Math.max(1, memberRoutes.size());
            if (routeInfo.isGroup()) {
                Iterator<MediaRouter.RouteInfo> it = routeInfo.getMemberRoutes().iterator();
                while (it.hasNext()) {
                    if (memberRoutes.contains(it.next()) != z2) {
                        iMax += z2 ? 1 : -1;
                    }
                }
            } else {
                iMax += z2 ? 1 : -1;
            }
            boolean zIsGroupVolumeNeeded = isGroupVolumeNeeded();
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            boolean z3 = mediaRouteDynamicControllerDialog.mEnableGroupVolumeUX && iMax >= 2;
            if (zIsGroupVolumeNeeded != z3) {
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = mediaRouteDynamicControllerDialog.mRecyclerView.findViewHolderForAdapterPosition(0);
                if (viewHolderFindViewHolderForAdapterPosition instanceof GroupVolumeViewHolder) {
                    GroupVolumeViewHolder groupVolumeViewHolder = (GroupVolumeViewHolder) viewHolderFindViewHolderForAdapterPosition;
                    animateLayoutHeight(groupVolumeViewHolder.itemView, z3 ? groupVolumeViewHolder.getExpandedHeight() : 0);
                }
            }
        }

        public void notifyAdapterDataSetChanged() {
            MediaRouteDynamicControllerDialog.this.mUngroupableRoutes.clear();
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.mUngroupableRoutes.addAll(MediaRouteDialogHelper.getItemsRemoved(mediaRouteDynamicControllerDialog.mGroupableRoutes, mediaRouteDynamicControllerDialog.getCurrentGroupableRoutes()));
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
            int itemViewType = getItemViewType(i2);
            Item item = getItem(i2);
            if (itemViewType == 1) {
                MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.put(((MediaRouter.RouteInfo) item.getData()).getId(), (MediaRouteVolumeSliderHolder) viewHolder);
                ((GroupVolumeViewHolder) viewHolder).bindGroupVolumeViewHolder(item);
            } else {
                if (itemViewType == 2) {
                    ((HeaderViewHolder) viewHolder).bindHeaderViewHolder(item);
                    return;
                }
                if (itemViewType != 3) {
                    if (itemViewType != 4) {
                        throw new IllegalStateException();
                    }
                    ((GroupViewHolder) viewHolder).bindGroupViewHolder(item);
                } else {
                    MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.put(((MediaRouter.RouteInfo) item.getData()).getId(), (MediaRouteVolumeSliderHolder) viewHolder);
                    ((RouteViewHolder) viewHolder).bindRouteViewHolder(item);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            if (i2 == 1) {
                return new GroupVolumeViewHolder(this.mInflater.inflate(R.layout.mr_cast_group_volume_item, viewGroup, false));
            }
            if (i2 == 2) {
                return new HeaderViewHolder(this.mInflater.inflate(R.layout.mr_cast_header_item, viewGroup, false));
            }
            if (i2 == 3) {
                return new RouteViewHolder(this.mInflater.inflate(R.layout.mr_cast_route_item, viewGroup, false));
            }
            if (i2 == 4) {
                return new GroupViewHolder(this.mInflater.inflate(R.layout.mr_cast_group_item, viewGroup, false));
            }
            throw new IllegalStateException();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
            super.onViewRecycled(viewHolder);
            MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.values().remove(viewHolder);
        }

        public void updateItems() {
            this.mItems.clear();
            this.mGroupVolumeItem = new Item(MediaRouteDynamicControllerDialog.this.mSelectedRoute, 1);
            if (MediaRouteDynamicControllerDialog.this.mMemberRoutes.isEmpty()) {
                this.mItems.add(new Item(MediaRouteDynamicControllerDialog.this.mSelectedRoute, 3));
            } else {
                Iterator<MediaRouter.RouteInfo> it = MediaRouteDynamicControllerDialog.this.mMemberRoutes.iterator();
                while (it.hasNext()) {
                    this.mItems.add(new Item(it.next(), 3));
                }
            }
            boolean z2 = false;
            if (!MediaRouteDynamicControllerDialog.this.mGroupableRoutes.isEmpty()) {
                boolean z3 = false;
                for (MediaRouter.RouteInfo routeInfo : MediaRouteDynamicControllerDialog.this.mGroupableRoutes) {
                    if (!MediaRouteDynamicControllerDialog.this.mMemberRoutes.contains(routeInfo)) {
                        if (!z3) {
                            MediaRouteProvider.DynamicGroupRouteController dynamicGroupController = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getDynamicGroupController();
                            String groupableSelectionTitle = dynamicGroupController != null ? dynamicGroupController.getGroupableSelectionTitle() : null;
                            if (TextUtils.isEmpty(groupableSelectionTitle)) {
                                groupableSelectionTitle = MediaRouteDynamicControllerDialog.this.mContext.getString(R.string.mr_dialog_groupable_header);
                            }
                            this.mItems.add(new Item(groupableSelectionTitle, 2));
                            z3 = true;
                        }
                        this.mItems.add(new Item(routeInfo, 3));
                    }
                }
            }
            if (!MediaRouteDynamicControllerDialog.this.mTransferableRoutes.isEmpty()) {
                for (MediaRouter.RouteInfo routeInfo2 : MediaRouteDynamicControllerDialog.this.mTransferableRoutes) {
                    MediaRouter.RouteInfo routeInfo3 = MediaRouteDynamicControllerDialog.this.mSelectedRoute;
                    if (routeInfo3 != routeInfo2) {
                        if (!z2) {
                            MediaRouteProvider.DynamicGroupRouteController dynamicGroupController2 = routeInfo3.getDynamicGroupController();
                            String transferableSectionTitle = dynamicGroupController2 != null ? dynamicGroupController2.getTransferableSectionTitle() : null;
                            if (TextUtils.isEmpty(transferableSectionTitle)) {
                                transferableSectionTitle = MediaRouteDynamicControllerDialog.this.mContext.getString(R.string.mr_dialog_transferable_header);
                            }
                            this.mItems.add(new Item(transferableSectionTitle, 2));
                            z2 = true;
                        }
                        this.mItems.add(new Item(routeInfo2, 4));
                    }
                }
            }
            notifyAdapterDataSetChanged();
        }
    }

    public static final class RouteComparator implements Comparator<MediaRouter.RouteInfo> {
        static final RouteComparator sInstance = new RouteComparator();

        @Override // java.util.Comparator
        public int compare(MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2) {
            return routeInfo.getName().compareToIgnoreCase(routeInfo2.getName());
        }
    }

    public class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {
        public VolumeChangeListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
            if (z2) {
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) seekBar.getTag();
                MediaRouteVolumeSliderHolder mediaRouteVolumeSliderHolder = MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.get(routeInfo.getId());
                if (mediaRouteVolumeSliderHolder != null) {
                    mediaRouteVolumeSliderHolder.setMute(i2 == 0);
                }
                routeInfo.requestSetVolume(i2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (mediaRouteDynamicControllerDialog.mRouteForVolumeUpdatingByUser != null) {
                mediaRouteDynamicControllerDialog.mHandler.removeMessages(2);
            }
            MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser = (MediaRouter.RouteInfo) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            MediaRouteDynamicControllerDialog.this.mHandler.sendEmptyMessageDelayed(2, 500L);
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRouteDynamicControllerDialog(@NonNull Context context) {
        this(context, 0);
    }

    @RequiresApi(17)
    private static Bitmap blurBitmap(Bitmap bitmap, float f2, Context context) {
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        scriptIntrinsicBlurCreate.setRadius(f2);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        Bitmap bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
        allocationCreateTyped.copyTo(bitmapCopy);
        allocationCreateFromBitmap.destroy();
        allocationCreateTyped.destroy();
        scriptIntrinsicBlurCreate.destroy();
        renderScriptCreate.destroy();
        return bitmapCopy;
    }

    public static boolean isBitmapRecycled(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
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
            reloadIconIfNeeded();
            updateMetadataViews();
        }
    }

    private boolean shouldDeferUpdateViews() {
        if (this.mRouteForVolumeUpdatingByUser != null || this.mIsSelectingRoute || this.mIsAnimatingVolumeSliderLayout) {
            return true;
        }
        return !this.mCreated;
    }

    public void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    public List<MediaRouter.RouteInfo> getCurrentGroupableRoutes() {
        ArrayList arrayList = new ArrayList();
        for (MediaRouter.RouteInfo routeInfo : this.mSelectedRoute.getProvider().getRoutes()) {
            MediaRouter.RouteInfo.DynamicGroupState dynamicGroupState = this.mSelectedRoute.getDynamicGroupState(routeInfo);
            if (dynamicGroupState != null && dynamicGroupState.isGroupable()) {
                arrayList.add(routeInfo);
            }
        }
        return arrayList;
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
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        updateRoutes();
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mr_cast_dialog);
        MediaRouterThemeHelper.setDialogBackgroundColor(this.mContext, this);
        ImageButton imageButton = (ImageButton) findViewById(R.id.mr_cast_close_button);
        this.mCloseButton = imageButton;
        imageButton.setColorFilter(-1);
        this.mCloseButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaRouteDynamicControllerDialog.this.dismiss();
            }
        });
        Button button = (Button) findViewById(R.id.mr_cast_stop_button);
        this.mStopCastingButton = button;
        button.setTextColor(-1);
        this.mStopCastingButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MediaRouteDynamicControllerDialog.this.mSelectedRoute.isSelected()) {
                    MediaRouteDynamicControllerDialog.this.mRouter.unselect(2);
                }
                MediaRouteDynamicControllerDialog.this.dismiss();
            }
        });
        this.mAdapter = new RecyclerAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mr_cast_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mVolumeChangeListener = new VolumeChangeListener();
        this.mVolumeSliderHolderMap = new HashMap();
        this.mUnmutedVolumeMap = new HashMap();
        this.mMetadataBackground = (ImageView) findViewById(R.id.mr_cast_meta_background);
        this.mMetadataBlackScrim = findViewById(R.id.mr_cast_meta_black_scrim);
        this.mArtView = (ImageView) findViewById(R.id.mr_cast_meta_art);
        TextView textView = (TextView) findViewById(R.id.mr_cast_meta_title);
        this.mTitleView = textView;
        textView.setTextColor(-1);
        TextView textView2 = (TextView) findViewById(R.id.mr_cast_meta_subtitle);
        this.mSubtitleView = textView2;
        textView2.setTextColor(-1);
        this.mTitlePlaceholder = this.mContext.getResources().getString(R.string.mr_cast_dialog_title_view_placeholder);
        this.mCreated = true;
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeCallbacksAndMessages(null);
        setMediaSession(null);
    }

    public boolean onFilterRoute(@NonNull MediaRouter.RouteInfo routeInfo) {
        return !routeInfo.isDefaultOrBluetooth() && routeInfo.isEnabled() && routeInfo.matchesSelector(this.mSelector) && this.mSelectedRoute != routeInfo;
    }

    public void onFilterRoutes(@NonNull List<MediaRouter.RouteInfo> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!onFilterRoute(list.get(size))) {
                list.remove(size);
            }
        }
    }

    public void reloadIconIfNeeded() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        Bitmap bitmapR = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.r();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        Uri uriS = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.s() : null;
        FetchArtTask fetchArtTask = this.mFetchArtTask;
        Bitmap iconBitmap = fetchArtTask == null ? this.mArtIconBitmap : fetchArtTask.getIconBitmap();
        FetchArtTask fetchArtTask2 = this.mFetchArtTask;
        Uri iconUri = fetchArtTask2 == null ? this.mArtIconUri : fetchArtTask2.getIconUri();
        if (iconBitmap != bitmapR || (iconBitmap == null && !ObjectsCompat.equals(iconUri, uriS))) {
            FetchArtTask fetchArtTask3 = this.mFetchArtTask;
            if (fetchArtTask3 != null) {
                fetchArtTask3.cancel(true);
            }
            FetchArtTask fetchArtTask4 = new FetchArtTask();
            this.mFetchArtTask = fetchArtTask4;
            fetchArtTask4.execute(new Void[0]);
        }
    }

    public void setRouteSelector(@NonNull MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.mSelector.equals(mediaRouteSelector)) {
            return;
        }
        this.mSelector = mediaRouteSelector;
        if (this.mAttachedToWindow) {
            this.mRouter.removeCallback(this.mCallback);
            this.mRouter.addCallback(mediaRouteSelector, this.mCallback, 1);
            updateRoutes();
        }
    }

    public void updateLayout() {
        getWindow().setLayout(MediaRouteDialogHelper.getDialogWidthForDynamicGroup(this.mContext), MediaRouteDialogHelper.getDialogHeight(this.mContext));
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        reloadIconIfNeeded();
        updateMetadataViews();
        updateRoutesView();
    }

    public void updateMetadataViews() {
        if (shouldDeferUpdateViews()) {
            this.mUpdateMetadataViewsDeferred = true;
            return;
        }
        this.mUpdateMetadataViewsDeferred = false;
        if (!this.mSelectedRoute.isSelected() || this.mSelectedRoute.isDefaultOrBluetooth()) {
            dismiss();
        }
        if (!this.mArtIconIsLoaded || isBitmapRecycled(this.mArtIconLoadedBitmap) || this.mArtIconLoadedBitmap == null) {
            if (isBitmapRecycled(this.mArtIconLoadedBitmap)) {
                Log.w(TAG, "Can't set artwork image with recycled bitmap: " + this.mArtIconLoadedBitmap);
            }
            this.mArtView.setVisibility(8);
            this.mMetadataBlackScrim.setVisibility(8);
            this.mMetadataBackground.setImageBitmap(null);
        } else {
            this.mArtView.setVisibility(0);
            this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
            this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
            this.mMetadataBlackScrim.setVisibility(0);
            this.mMetadataBackground.setImageBitmap(blurBitmap(this.mArtIconLoadedBitmap, 10.0f, this.mContext));
        }
        clearLoadedBitmap();
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        CharSequence charSequenceV = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.v();
        boolean zIsEmpty = TextUtils.isEmpty(charSequenceV);
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        CharSequence charSequenceU = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.u() : null;
        boolean zIsEmpty2 = TextUtils.isEmpty(charSequenceU);
        if (zIsEmpty) {
            this.mTitleView.setText(this.mTitlePlaceholder);
        } else {
            this.mTitleView.setText(charSequenceV);
        }
        if (zIsEmpty2) {
            this.mSubtitleView.setVisibility(8);
        } else {
            this.mSubtitleView.setText(charSequenceU);
            this.mSubtitleView.setVisibility(0);
        }
    }

    public void updateRoutes() {
        this.mMemberRoutes.clear();
        this.mGroupableRoutes.clear();
        this.mTransferableRoutes.clear();
        this.mMemberRoutes.addAll(this.mSelectedRoute.getMemberRoutes());
        for (MediaRouter.RouteInfo routeInfo : this.mSelectedRoute.getProvider().getRoutes()) {
            MediaRouter.RouteInfo.DynamicGroupState dynamicGroupState = this.mSelectedRoute.getDynamicGroupState(routeInfo);
            if (dynamicGroupState != null) {
                if (dynamicGroupState.isGroupable()) {
                    this.mGroupableRoutes.add(routeInfo);
                }
                if (dynamicGroupState.isTransferable()) {
                    this.mTransferableRoutes.add(routeInfo);
                }
            }
        }
        onFilterRoutes(this.mGroupableRoutes);
        onFilterRoutes(this.mTransferableRoutes);
        List<MediaRouter.RouteInfo> list = this.mMemberRoutes;
        RouteComparator routeComparator = RouteComparator.sInstance;
        Collections.sort(list, routeComparator);
        Collections.sort(this.mGroupableRoutes, routeComparator);
        Collections.sort(this.mTransferableRoutes, routeComparator);
        this.mAdapter.updateItems();
    }

    public void updateRoutesView() {
        if (this.mAttachedToWindow) {
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime < 300) {
                this.mHandler.removeMessages(1);
                this.mHandler.sendEmptyMessageAtTime(1, this.mLastUpdateTime + 300);
            } else {
                if (shouldDeferUpdateViews()) {
                    this.mUpdateRoutesViewDeferred = true;
                    return;
                }
                this.mUpdateRoutesViewDeferred = false;
                if (!this.mSelectedRoute.isSelected() || this.mSelectedRoute.isDefaultOrBluetooth()) {
                    dismiss();
                }
                this.mLastUpdateTime = SystemClock.uptimeMillis();
                this.mAdapter.notifyAdapterDataSetChanged();
            }
        }
    }

    public void updateViewsIfNeeded() {
        if (this.mUpdateRoutesViewDeferred) {
            updateRoutesView();
        }
        if (this.mUpdateMetadataViewsDeferred) {
            updateMetadataViews();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteDynamicControllerDialog(@NonNull Context context, int i2) {
        Context contextCreateThemedDialogContext = MediaRouterThemeHelper.createThemedDialogContext(context, i2, false);
        super(contextCreateThemedDialogContext, MediaRouterThemeHelper.createThemedDialogStyle(contextCreateThemedDialogContext));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mMemberRoutes = new ArrayList();
        this.mGroupableRoutes = new ArrayList();
        this.mTransferableRoutes = new ArrayList();
        this.mUngroupableRoutes = new ArrayList();
        this.mHandler = new Handler() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i3 = message.what;
                if (i3 == 1) {
                    MediaRouteDynamicControllerDialog.this.updateRoutesView();
                    return;
                }
                if (i3 != 2) {
                    return;
                }
                MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
                if (mediaRouteDynamicControllerDialog.mRouteForVolumeUpdatingByUser != null) {
                    mediaRouteDynamicControllerDialog.mRouteForVolumeUpdatingByUser = null;
                    mediaRouteDynamicControllerDialog.updateViewsIfNeeded();
                }
            }
        };
        Context context2 = getContext();
        this.mContext = context2;
        MediaRouter mediaRouter = MediaRouter.getInstance(context2);
        this.mRouter = mediaRouter;
        this.mEnableGroupVolumeUX = MediaRouter.isGroupVolumeUxEnabled();
        this.mCallback = new MediaRouterCallback();
        this.mSelectedRoute = mediaRouter.getSelectedRoute();
        this.mControllerCallback = new MediaControllerCallback();
        setMediaSession(mediaRouter.getMediaSessionToken());
    }
}
