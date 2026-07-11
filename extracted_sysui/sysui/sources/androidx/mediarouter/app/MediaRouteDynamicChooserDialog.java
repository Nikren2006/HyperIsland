package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatDialog;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class MediaRouteDynamicChooserDialog extends AppCompatDialog {
    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_ROUTE = 2;
    private static final int MSG_UPDATE_ROUTES = 1;
    private RecyclerAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    private final Handler mHandler;
    private long mLastUpdateTime;
    private RecyclerView mRecyclerView;
    final MediaRouter mRouter;
    List<MediaRouter.RouteInfo> mRoutes;
    MediaRouter.RouteInfo mSelectingRoute;
    private MediaRouteSelector mSelector;
    private long mUpdateRoutesDelayMs;

    public final class MediaRouterCallback extends MediaRouter.Callback {
        public MediaRouterCallback() {
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicChooserDialog.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicChooserDialog.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicChooserDialog.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteDynamicChooserDialog.this.dismiss();
        }
    }

    public final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final String TAG = "RecyclerAdapter";
        private final Drawable mDefaultIcon;
        private final LayoutInflater mInflater;
        private final ArrayList<Item> mItems = new ArrayList<>();
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;

        public class HeaderViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public HeaderViewHolder(View view) {
                super(view);
                this.mTextView = (TextView) view.findViewById(R.id.mr_picker_header_name);
            }

            public void bindHeaderView(Item item) {
                this.mTextView.setText(item.getData().toString());
            }
        }

        public class Item {
            private final Object mData;
            private final int mType;

            public Item(Object obj) {
                this.mData = obj;
                if (obj instanceof String) {
                    this.mType = 1;
                } else {
                    if (!(obj instanceof MediaRouter.RouteInfo)) {
                        throw new IllegalArgumentException();
                    }
                    this.mType = 2;
                }
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        public class RouteViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            final TextView mTextView;

            public RouteViewHolder(View view) {
                super(view);
                this.mItemView = view;
                this.mImageView = (ImageView) view.findViewById(R.id.mr_picker_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_picker_route_progress_bar);
                this.mProgressBar = progressBar;
                this.mTextView = (TextView) view.findViewById(R.id.mr_picker_route_name);
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(MediaRouteDynamicChooserDialog.this.mContext, progressBar);
            }

            public void bindRouteView(Item item) {
                final MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) item.getData();
                this.mItemView.setVisibility(0);
                this.mProgressBar.setVisibility(4);
                this.mItemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicChooserDialog.RecyclerAdapter.RouteViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MediaRouteDynamicChooserDialog mediaRouteDynamicChooserDialog = MediaRouteDynamicChooserDialog.this;
                        MediaRouter.RouteInfo routeInfo2 = routeInfo;
                        mediaRouteDynamicChooserDialog.mSelectingRoute = routeInfo2;
                        routeInfo2.select();
                        RouteViewHolder.this.mImageView.setVisibility(4);
                        RouteViewHolder.this.mProgressBar.setVisibility(0);
                    }
                });
                this.mTextView.setText(routeInfo.getName());
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
            }
        }

        public RecyclerAdapter() {
            this.mInflater = LayoutInflater.from(MediaRouteDynamicChooserDialog.this.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(MediaRouteDynamicChooserDialog.this.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(MediaRouteDynamicChooserDialog.this.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(MediaRouteDynamicChooserDialog.this.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGroupDrawableIcon(MediaRouteDynamicChooserDialog.this.mContext);
            rebuildItems();
        }

        private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo routeInfo) {
            int deviceType = routeInfo.getDeviceType();
            return deviceType != 1 ? deviceType != 2 ? routeInfo.isGroup() ? this.mSpeakerGroupIcon : this.mDefaultIcon : this.mSpeakerIcon : this.mTvIcon;
        }

        public Drawable getIconDrawable(MediaRouter.RouteInfo routeInfo) {
            Uri iconUri = routeInfo.getIconUri();
            if (iconUri != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(MediaRouteDynamicChooserDialog.this.mContext.getContentResolver().openInputStream(iconUri), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException e2) {
                    Log.w(TAG, "Failed to load " + iconUri, e2);
                }
            }
            return getDefaultIconDrawable(routeInfo);
        }

        public Item getItem(int i2) {
            return this.mItems.get(i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            return this.mItems.get(i2).getType();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
            int itemViewType = getItemViewType(i2);
            Item item = getItem(i2);
            if (itemViewType == 1) {
                ((HeaderViewHolder) viewHolder).bindHeaderView(item);
            } else if (itemViewType != 2) {
                Log.w(TAG, "Cannot bind item to ViewHolder because of wrong view type");
            } else {
                ((RouteViewHolder) viewHolder).bindRouteView(item);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            if (i2 == 1) {
                return new HeaderViewHolder(this.mInflater.inflate(R.layout.mr_picker_header_item, viewGroup, false));
            }
            if (i2 == 2) {
                return new RouteViewHolder(this.mInflater.inflate(R.layout.mr_picker_route_item, viewGroup, false));
            }
            throw new IllegalStateException();
        }

        public void rebuildItems() {
            this.mItems.clear();
            this.mItems.add(new Item(MediaRouteDynamicChooserDialog.this.mContext.getString(R.string.mr_chooser_title)));
            Iterator<MediaRouter.RouteInfo> it = MediaRouteDynamicChooserDialog.this.mRoutes.iterator();
            while (it.hasNext()) {
                this.mItems.add(new Item(it.next()));
            }
            notifyDataSetChanged();
        }
    }

    public static final class RouteComparator implements Comparator<MediaRouter.RouteInfo> {
        public static final RouteComparator sInstance = new RouteComparator();

        @Override // java.util.Comparator
        public int compare(MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2) {
            return routeInfo.getName().compareToIgnoreCase(routeInfo2.getName());
        }
    }

    public MediaRouteDynamicChooserDialog(@NonNull Context context) {
        this(context, 0);
    }

    @NonNull
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        refreshRoutes();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mr_picker_dialog);
        MediaRouterThemeHelper.setDialogBackgroundColor(this.mContext, this);
        this.mRoutes = new ArrayList();
        ImageButton imageButton = (ImageButton) findViewById(R.id.mr_picker_close_button);
        this.mCloseButton = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicChooserDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaRouteDynamicChooserDialog.this.dismiss();
            }
        });
        this.mAdapter = new RecyclerAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mr_picker_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    @CallSuper
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeMessages(1);
    }

    public boolean onFilterRoute(@NonNull MediaRouter.RouteInfo routeInfo) {
        return !routeInfo.isDefaultOrBluetooth() && routeInfo.isEnabled() && routeInfo.matchesSelector(this.mSelector);
    }

    public void onFilterRoutes(@NonNull List<MediaRouter.RouteInfo> list) {
        int size = list.size();
        while (true) {
            int i2 = size - 1;
            if (size <= 0) {
                return;
            }
            if (!onFilterRoute(list.get(i2))) {
                list.remove(i2);
            }
            size = i2;
        }
    }

    public void refreshRoutes() {
        if (this.mSelectingRoute == null && this.mAttachedToWindow) {
            ArrayList arrayList = new ArrayList(this.mRouter.getRoutes());
            onFilterRoutes(arrayList);
            Collections.sort(arrayList, RouteComparator.sInstance);
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= this.mUpdateRoutesDelayMs) {
                updateRoutes(arrayList);
                return;
            }
            this.mHandler.removeMessages(1);
            Handler handler = this.mHandler;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + this.mUpdateRoutesDelayMs);
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
        }
        refreshRoutes();
    }

    public void updateLayout() {
        getWindow().setLayout(MediaRouteDialogHelper.getDialogWidthForDynamicGroup(this.mContext), MediaRouteDialogHelper.getDialogHeight(this.mContext));
    }

    public void updateRoutes(List<MediaRouter.RouteInfo> list) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(list);
        this.mAdapter.rebuildItems();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteDynamicChooserDialog(@NonNull Context context, int i2) {
        Context contextCreateThemedDialogContext = MediaRouterThemeHelper.createThemedDialogContext(context, i2, false);
        super(contextCreateThemedDialogContext, MediaRouterThemeHelper.createThemedDialogStyle(contextCreateThemedDialogContext));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mHandler = new Handler() { // from class: androidx.mediarouter.app.MediaRouteDynamicChooserDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                MediaRouteDynamicChooserDialog.this.updateRoutes((List) message.obj);
            }
        };
        Context context2 = getContext();
        this.mRouter = MediaRouter.getInstance(context2);
        this.mCallback = new MediaRouterCallback();
        this.mContext = context2;
        this.mUpdateRoutesDelayMs = context2.getResources().getInteger(R.integer.mr_update_routes_delay_ms);
    }
}
