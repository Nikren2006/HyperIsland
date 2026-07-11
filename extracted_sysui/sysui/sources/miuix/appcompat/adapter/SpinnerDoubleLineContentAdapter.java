package miuix.appcompat.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.appcompat.R;

/* JADX INFO: loaded from: classes2.dex */
public class SpinnerDoubleLineContentAdapter extends ArrayAdapter {
    private static final int TAG_VIEW = R.id.tag_spinner_dropdown_view_double_line;
    protected CharSequence[] mEntries;
    protected boolean mIconOnlyEnabled;
    protected Drawable[] mIcons;
    private LayoutInflater mInflater;
    protected CharSequence[] mSummaries;

    public static class ViewHolder {
        ImageView icon;
        TextView summary;
        TextView title;

        private ViewHolder() {
        }
    }

    public SpinnerDoubleLineContentAdapter(Context context, int i2) {
        super(context, i2);
        this.mIconOnlyEnabled = false;
        this.mInflater = LayoutInflater.from(context);
    }

    private CharSequence getEntry(int i2) {
        CharSequence[] charSequenceArr = this.mEntries;
        if (charSequenceArr == null || i2 >= charSequenceArr.length) {
            return null;
        }
        return charSequenceArr[i2];
    }

    private Drawable getIcon(int i2) {
        Drawable[] drawableArr = this.mIcons;
        if (drawableArr == null || i2 >= drawableArr.length) {
            return null;
        }
        return drawableArr[i2];
    }

    private CharSequence getSummary(int i2) {
        CharSequence[] charSequenceArr = this.mSummaries;
        if (charSequenceArr == null || i2 >= charSequenceArr.length) {
            return null;
        }
        return charSequenceArr[i2];
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        CharSequence[] charSequenceArr = this.mEntries;
        if (charSequenceArr == null) {
            return 0;
        }
        return charSequenceArr.length;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
        if (view == null || view.getTag(TAG_VIEW) == null) {
            view = this.mInflater.inflate(R.layout.miuix_appcompat_spiner_dropdown_view_double_line, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(android.R.id.icon);
            viewHolder.title = (TextView) view.findViewById(android.R.id.title);
            viewHolder.summary = (TextView) view.findViewById(android.R.id.summary);
            view.setTag(TAG_VIEW, viewHolder);
        }
        CharSequence entry = getEntry(i2);
        CharSequence summary = getSummary(i2);
        Drawable icon = getIcon(i2);
        Object tag = view.getTag(TAG_VIEW);
        if (tag != null) {
            ViewHolder viewHolder2 = (ViewHolder) tag;
            if (TextUtils.isEmpty(entry) || this.mIconOnlyEnabled) {
                viewHolder2.title.setText("");
                viewHolder2.title.setVisibility(8);
            } else {
                viewHolder2.title.setText(entry);
                viewHolder2.title.setVisibility(0);
            }
            if (TextUtils.isEmpty(summary) || this.mIconOnlyEnabled) {
                viewHolder2.summary.setText("");
                viewHolder2.summary.setVisibility(8);
            } else {
                viewHolder2.summary.setText(summary);
                viewHolder2.summary.setVisibility(0);
            }
            if (icon != null) {
                viewHolder2.icon.setImageDrawable(icon);
                viewHolder2.icon.setVisibility(0);
                if (!TextUtils.isEmpty(entry) && this.mIconOnlyEnabled) {
                    viewHolder2.icon.setContentDescription(entry);
                }
            } else {
                viewHolder2.icon.setVisibility(8);
            }
        }
        return view;
    }

    public CharSequence[] getEntries() {
        return this.mEntries;
    }

    public Drawable[] getEntryIcons() {
        return this.mIcons;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @Nullable
    public Object getItem(int i2) {
        CharSequence[] charSequenceArr = this.mEntries;
        if (charSequenceArr == null || i2 < 0 || i2 >= charSequenceArr.length) {
            return null;
        }
        return charSequenceArr[i2];
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    public CharSequence[] getSummaries() {
        return this.mSummaries;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public boolean isIconOnlyEnabled() {
        return this.mIconOnlyEnabled;
    }

    public void setEntries(CharSequence[] charSequenceArr) {
        this.mEntries = charSequenceArr;
    }

    public void setEntryIcons(int[] iArr) {
        if (iArr == null) {
            setEntryIcons((Drawable[]) null);
            return;
        }
        Drawable[] drawableArr = new Drawable[iArr.length];
        Resources resources = getContext().getResources();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 > 0) {
                drawableArr[i2] = resources.getDrawable(i3);
            } else {
                drawableArr[i2] = null;
            }
        }
        setEntryIcons(drawableArr);
    }

    public void setIconOnlyEnabled(boolean z2) {
        this.mIconOnlyEnabled = z2;
    }

    public void setSummaries(CharSequence[] charSequenceArr) {
        this.mSummaries = charSequenceArr;
    }

    public SpinnerDoubleLineContentAdapter(Context context, CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, int[] iArr) {
        this(context, 0);
        this.mEntries = charSequenceArr;
        this.mSummaries = charSequenceArr2;
        setEntryIcons(iArr);
    }

    public SpinnerDoubleLineContentAdapter(Context context, CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, int[] iArr, boolean z2) {
        this(context, 0);
        this.mEntries = charSequenceArr;
        this.mSummaries = charSequenceArr2;
        setEntryIcons(iArr);
        this.mIconOnlyEnabled = z2;
    }

    public void setEntryIcons(Drawable[] drawableArr) {
        this.mIcons = drawableArr;
    }
}
