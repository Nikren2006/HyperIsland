package miuix.internal.util;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miuix.internal.util.AsyncLayoutInflater;

/* JADX INFO: loaded from: classes3.dex */
public class AsyncInflateLayoutManager {
    private static final AsyncInflateLayoutManager MANAGER = new AsyncInflateLayoutManager();
    private static final String TAG = "AsyncInflateManager";
    private AsyncLayoutInflater mInflater;
    private final HashMap<Integer, List<View>> mListTypeMap = new HashMap<>();
    private boolean isEnabled = true;
    private boolean isEnabledLog = false;
    private boolean isCachedEnable = false;
    private final AsyncLayoutInflater.OnInflateFinishedListener mListener = new AsyncLayoutInflater.OnInflateFinishedListener() { // from class: miuix.internal.util.AsyncInflateLayoutManager.1
        @Override // miuix.internal.util.AsyncLayoutInflater.OnInflateFinishedListener
        public void onInflateFinished(@Nullable View view, int i2, @Nullable ViewGroup viewGroup) {
            List arrayList = (List) AsyncInflateLayoutManager.this.mListTypeMap.get(Integer.valueOf(i2));
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            if (AsyncInflateLayoutManager.this.isEnabledLog) {
                Log.i(AsyncInflateLayoutManager.TAG, "async create view is success.");
            }
            arrayList.add(view);
            AsyncInflateLayoutManager.this.mListTypeMap.put(Integer.valueOf(i2), arrayList);
        }
    };

    public static class InflateRequest {
        int count;
        int layoutId;

        public InflateRequest(int i2, int i3) {
            this.layoutId = i2;
            this.count = i3;
        }
    }

    private void asyncInflate(int[] iArr, ViewGroup viewGroup) {
        for (int i2 : iArr) {
            this.mInflater.inflate(i2, viewGroup, this.mListener);
        }
    }

    public static AsyncInflateLayoutManager getInstance() {
        return MANAGER;
    }

    private boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public View inflateViewById(Context context, Integer num) {
        List<View> list = this.mListTypeMap.get(num);
        if (list == null || list.isEmpty()) {
            return LayoutInflater.from(context).inflate(num.intValue(), (ViewGroup) null);
        }
        View viewRemove = list.remove(0);
        if (list.isEmpty()) {
            this.mListTypeMap.remove(num);
        }
        return viewRemove;
    }

    public void release() {
        if (this.isEnabled) {
            AsyncLayoutInflater asyncLayoutInflater = this.mInflater;
            if (asyncLayoutInflater != null) {
                asyncLayoutInflater.removeAllMessages();
            }
            this.mListTypeMap.clear();
            this.isCachedEnable = false;
        }
    }

    public void setEnableAsyncInflate(boolean z2) {
        this.isEnabled = z2;
    }

    public void setEnableAsyncInflateLog(boolean z2) {
        this.isEnabledLog = z2;
    }

    public void startAsyncInflateLayout(@NonNull ViewGroup viewGroup, @NonNull int[] iArr) {
        if (isMainThread() && this.isEnabled && iArr.length != 0) {
            if (this.mInflater == null) {
                this.mInflater = new AsyncLayoutInflater();
            }
            if (this.isEnabledLog) {
                Log.i(TAG, "async create view is start.");
            }
            asyncInflate(iArr, viewGroup);
        }
    }

    public View inflateViewById(Integer num, ViewGroup viewGroup, boolean z2) {
        if (viewGroup == null) {
            return null;
        }
        List<View> list = this.mListTypeMap.get(num);
        if (list != null && !list.isEmpty()) {
            View viewRemove = list.remove(0);
            if (list.isEmpty()) {
                this.mListTypeMap.remove(num);
            }
            if (this.isEnabledLog) {
                Log.i(TAG, "inflateCacheById is ok.");
            }
            return viewRemove;
        }
        if (this.isEnabledLog) {
            Log.i(TAG, "inflateCacheById is null.");
        }
        return LayoutInflater.from(viewGroup.getContext()).inflate(num.intValue(), viewGroup, z2);
    }

    public void startAsyncInflateLayout(@NonNull ViewGroup viewGroup, @NonNull int[] iArr, boolean z2) {
        if (isMainThread() && this.isEnabled && iArr.length != 0) {
            this.isCachedEnable = z2;
            if (this.mInflater == null) {
                this.mInflater = new AsyncLayoutInflater();
            }
            if (this.isEnabledLog) {
                Log.i(TAG, "async create view is start.");
            }
            asyncInflate(iArr, viewGroup);
        }
    }

    public void startAsyncInflateLayout(@NonNull ViewGroup viewGroup, int i2, int i3) {
        if (isMainThread() && this.isEnabled && i3 > 0) {
            if (this.mInflater == null) {
                this.mInflater = new AsyncLayoutInflater();
            }
            if (this.isEnabledLog) {
                Log.i(TAG, "async create view is start.");
            }
            for (int i4 = 0; i4 < i3; i4++) {
                this.mInflater.inflate(i2, viewGroup, this.mListener);
            }
        }
    }

    public View inflateViewById(Integer num, Context context, ViewGroup viewGroup, boolean z2) {
        View viewRemove;
        if (viewGroup == null || context == null) {
            return null;
        }
        List<View> list = this.mListTypeMap.get(num);
        if (list != null && !list.isEmpty()) {
            if (this.isCachedEnable) {
                viewRemove = list.get(0);
            } else {
                viewRemove = list.remove(0);
            }
            if (list.isEmpty()) {
                this.mListTypeMap.remove(num);
            }
            if (this.isEnabledLog) {
                Log.i(TAG, "inflateCacheById is ok.");
            }
            return viewRemove;
        }
        if (this.isEnabledLog) {
            Log.i(TAG, "inflateCacheById is null.");
        }
        return LayoutInflater.from(context).inflate(num.intValue(), viewGroup, z2);
    }

    public void startAsyncInflateLayout(@NonNull ViewGroup viewGroup, @NonNull InflateRequest[] inflateRequestArr) {
        if (isMainThread() && this.isEnabled && inflateRequestArr.length != 0) {
            if (this.mInflater == null) {
                this.mInflater = new AsyncLayoutInflater();
            }
            if (this.isEnabledLog) {
                Log.i(TAG, "async create view is start.");
            }
            for (InflateRequest inflateRequest : inflateRequestArr) {
                if (inflateRequest != null) {
                    for (int i2 = 0; i2 < inflateRequest.count; i2++) {
                        this.mInflater.inflate(inflateRequest.layoutId, viewGroup, this.mListener);
                    }
                }
            }
        }
    }
}
