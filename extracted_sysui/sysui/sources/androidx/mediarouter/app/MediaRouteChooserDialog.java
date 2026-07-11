package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteChooserDialog extends AppCompatDialog {
    static final int MSG_UPDATE_ROUTES = 1;
    static final String TAG = "MediaRouteChooserDialog";
    private static final long UPDATE_ROUTES_DELAY_MS = 300;
    private RouteAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private final Handler mHandler;
    private long mLastUpdateTime;
    private ListView mListView;
    private final MediaRouter mRouter;
    private ArrayList<MediaRouter.RouteInfo> mRoutes;
    private MediaRouteSelector mSelector;
    private TextView mTitleView;

    public final class MediaRouterCallback extends MediaRouter.Callback {
        public MediaRouterCallback() {
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            MediaRouteChooserDialog.this.dismiss();
        }
    }

    public static final class RouteAdapter extends ArrayAdapter<MediaRouter.RouteInfo> implements AdapterView.OnItemClickListener {
        private final Drawable mDefaultIcon;
        private final LayoutInflater mInflater;
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;

        public RouteAdapter(Context context, List<MediaRouter.RouteInfo> list) {
            super(context, 0, list);
            this.mInflater = LayoutInflater.from(context);
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R.attr.mediaRouteDefaultIconDrawable, R.attr.mediaRouteTvIconDrawable, R.attr.mediaRouteSpeakerIconDrawable, R.attr.mediaRouteSpeakerGroupIconDrawable});
            this.mDefaultIcon = AppCompatResources.getDrawable(context, typedArrayObtainStyledAttributes.getResourceId(0, 0));
            this.mTvIcon = AppCompatResources.getDrawable(context, typedArrayObtainStyledAttributes.getResourceId(1, 0));
            this.mSpeakerIcon = AppCompatResources.getDrawable(context, typedArrayObtainStyledAttributes.getResourceId(2, 0));
            this.mSpeakerGroupIcon = AppCompatResources.getDrawable(context, typedArrayObtainStyledAttributes.getResourceId(3, 0));
            typedArrayObtainStyledAttributes.recycle();
        }

        private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo routeInfo) {
            int deviceType = routeInfo.getDeviceType();
            return deviceType != 1 ? deviceType != 2 ? routeInfo.isGroup() ? this.mSpeakerGroupIcon : this.mDefaultIcon : this.mSpeakerIcon : this.mTvIcon;
        }

        private Drawable getIconDrawable(MediaRouter.RouteInfo routeInfo) {
            Uri iconUri = routeInfo.getIconUri();
            if (iconUri != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(getContext().getContentResolver().openInputStream(iconUri), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException e2) {
                    Log.w(MediaRouteChooserDialog.TAG, "Failed to load " + iconUri, e2);
                }
            }
            return getDefaultIconDrawable(routeInfo);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mInflater.inflate(R.layout.mr_chooser_list_item, viewGroup, false);
            }
            MediaRouter.RouteInfo item = getItem(i2);
            TextView textView = (TextView) view.findViewById(R.id.mr_chooser_route_name);
            TextView textView2 = (TextView) view.findViewById(R.id.mr_chooser_route_desc);
            textView.setText(item.getName());
            String description = item.getDescription();
            if ((item.getConnectionState() == 2 || item.getConnectionState() == 1) && !TextUtils.isEmpty(description)) {
                textView.setGravity(80);
                textView2.setVisibility(0);
                textView2.setText(description);
            } else {
                textView.setGravity(16);
                textView2.setVisibility(8);
                textView2.setText("");
            }
            view.setEnabled(item.isEnabled());
            ImageView imageView = (ImageView) view.findViewById(R.id.mr_chooser_route_icon);
            if (imageView != null) {
                imageView.setImageDrawable(getIconDrawable(item));
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            return getItem(i2).isEnabled();
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            MediaRouter.RouteInfo item = getItem(i2);
            if (item.isEnabled()) {
                ImageView imageView = (ImageView) view.findViewById(R.id.mr_chooser_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_chooser_route_progress_bar);
                if (imageView != null && progressBar != null) {
                    imageView.setVisibility(8);
                    progressBar.setVisibility(0);
                }
                item.select();
            }
        }
    }

    public static final class RouteComparator implements Comparator<MediaRouter.RouteInfo> {
        public static final RouteComparator sInstance = new RouteComparator();

        @Override // java.util.Comparator
        public int compare(MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2) {
            return routeInfo.getName().compareToIgnoreCase(routeInfo2.getName());
        }
    }

    public MediaRouteChooserDialog(@NonNull Context context) {
        this(context, 0);
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
        refreshRoutes();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mr_chooser_dialog);
        this.mRoutes = new ArrayList<>();
        this.mAdapter = new RouteAdapter(getContext(), this.mRoutes);
        ListView listView = (ListView) findViewById(R.id.mr_chooser_list);
        this.mListView = listView;
        listView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(this.mAdapter);
        this.mListView.setEmptyView(findViewById(android.R.id.empty));
        this.mTitleView = (TextView) findViewById(R.id.mr_chooser_title);
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeMessages(1);
        super.onDetachedFromWindow();
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
        if (this.mAttachedToWindow) {
            ArrayList arrayList = new ArrayList(this.mRouter.getRoutes());
            onFilterRoutes(arrayList);
            Collections.sort(arrayList, RouteComparator.sInstance);
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= 300) {
                updateRoutes(arrayList);
                return;
            }
            this.mHandler.removeMessages(1);
            Handler handler = this.mHandler;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + 300);
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

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(@Nullable CharSequence charSequence) {
        this.mTitleView.setText(charSequence);
    }

    public void updateLayout() {
        getWindow().setLayout(MediaRouteDialogHelper.getDialogWidth(getContext()), -2);
    }

    public void updateRoutes(List<MediaRouter.RouteInfo> list) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(list);
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteChooserDialog(@NonNull Context context, int i2) {
        Context contextCreateThemedDialogContext = MediaRouterThemeHelper.createThemedDialogContext(context, i2, false);
        super(contextCreateThemedDialogContext, MediaRouterThemeHelper.createThemedDialogStyle(contextCreateThemedDialogContext));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mHandler = new Handler() { // from class: androidx.mediarouter.app.MediaRouteChooserDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                MediaRouteChooserDialog.this.updateRoutes((List) message.obj);
            }
        };
        this.mRouter = MediaRouter.getInstance(getContext());
        this.mCallback = new MediaRouterCallback();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(int i2) {
        this.mTitleView.setText(i2);
    }
}
