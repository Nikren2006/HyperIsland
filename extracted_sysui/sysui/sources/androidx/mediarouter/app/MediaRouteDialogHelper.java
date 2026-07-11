package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.mediarouter.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class MediaRouteDialogHelper {
    private MediaRouteDialogHelper() {
    }

    public static int getDialogHeight(Context context) {
        return !context.getResources().getBoolean(R.bool.is_tablet) ? -1 : -2;
    }

    public static int getDialogWidth(Context context) {
        float fraction;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        boolean z2 = displayMetrics.widthPixels < displayMetrics.heightPixels;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(z2 ? R.dimen.mr_dialog_fixed_width_minor : R.dimen.mr_dialog_fixed_width_major, typedValue, true);
        int i2 = typedValue.type;
        if (i2 == 5) {
            fraction = typedValue.getDimension(displayMetrics);
        } else {
            if (i2 != 6) {
                return -2;
            }
            int i3 = displayMetrics.widthPixels;
            fraction = typedValue.getFraction(i3, i3);
        }
        return (int) fraction;
    }

    public static int getDialogWidthForDynamicGroup(Context context) {
        if (context.getResources().getBoolean(R.bool.is_tablet)) {
            return getDialogWidth(context);
        }
        return -1;
    }

    public static <E> HashMap<E, BitmapDrawable> getItemBitmapMap(Context context, ListView listView, ArrayAdapter<E> arrayAdapter) {
        HashMap<E, BitmapDrawable> map = new HashMap<>();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        for (int i2 = 0; i2 < listView.getChildCount(); i2++) {
            map.put(arrayAdapter.getItem(firstVisiblePosition + i2), getViewBitmap(context, listView.getChildAt(i2)));
        }
        return map;
    }

    public static <E> HashMap<E, Rect> getItemBoundMap(ListView listView, ArrayAdapter<E> arrayAdapter) {
        HashMap<E, Rect> map = new HashMap<>();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        for (int i2 = 0; i2 < listView.getChildCount(); i2++) {
            E item = arrayAdapter.getItem(firstVisiblePosition + i2);
            View childAt = listView.getChildAt(i2);
            map.put(item, new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom()));
        }
        return map;
    }

    public static <E> Set<E> getItemsAdded(List<E> list, List<E> list2) {
        HashSet hashSet = new HashSet(list2);
        hashSet.removeAll(list);
        return hashSet;
    }

    public static <E> Set<E> getItemsRemoved(List<E> list, List<E> list2) {
        HashSet hashSet = new HashSet(list);
        hashSet.removeAll(list2);
        return hashSet;
    }

    private static BitmapDrawable getViewBitmap(Context context, View view) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmapCreateBitmap));
        return new BitmapDrawable(context.getResources(), bitmapCreateBitmap);
    }

    public static <E> boolean listUnorderedEquals(List<E> list, List<E> list2) {
        return new HashSet(list).equals(new HashSet(list2));
    }
}
