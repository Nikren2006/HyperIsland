package miuix.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.NumberFormat;
import miuix.androidbasewidget.widget.ProgressBar;
import miuix.appcompat.R;
import miuix.core.util.MiuixUIUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ProgressDialog extends AlertDialog {
    public static final int STYLE_HORIZONTAL = 1;
    public static final int STYLE_SPINNER = 0;
    private boolean mHasStarted;
    private int mIncrementBy;
    private int mIncrementSecondaryBy;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private int mMax;
    private CharSequence mMessage;
    private TextView mMessageView;
    private ProgressBar mProgress;
    private Drawable mProgressDrawable;
    private String mProgressNumberFormat;
    private NumberFormat mProgressPercentFormat;
    private TextView mProgressPercentView;
    private int mProgressStyle;
    private int mProgressVal;
    private int mSecondaryProgressVal;
    private Handler mViewUpdateHandler;

    public ProgressDialog(Context context) {
        super(context);
        this.mProgressStyle = 0;
        initFormats();
    }

    private void initFormats() {
        this.mProgressNumberFormat = "%1d/%2d";
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        this.mProgressPercentFormat = percentInstance;
        percentInstance.setMaximumFractionDigits(0);
    }

    private void onProgressChanged() {
        Handler handler;
        if (this.mProgressStyle != 1 || (handler = this.mViewUpdateHandler) == null || handler.hasMessages(0)) {
            return;
        }
        this.mViewUpdateHandler.sendEmptyMessage(0);
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2) {
        return show(context, charSequence, charSequence2, false);
    }

    public int getMax() {
        ProgressBar progressBar = this.mProgress;
        return progressBar != null ? progressBar.getMax() : this.mMax;
    }

    public int getProgress() {
        ProgressBar progressBar = this.mProgress;
        return progressBar != null ? progressBar.getProgress() : this.mProgressVal;
    }

    public int getSecondaryProgress() {
        ProgressBar progressBar = this.mProgress;
        return progressBar != null ? progressBar.getSecondaryProgress() : this.mSecondaryProgressVal;
    }

    public void incrementProgressBy(int i2) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mIncrementBy += i2;
        } else {
            progressBar.incrementProgressBy(i2);
            onProgressChanged();
        }
    }

    public void incrementSecondaryProgressBy(int i2) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mIncrementSecondaryBy += i2;
        } else {
            progressBar.incrementSecondaryProgressBy(i2);
            onProgressChanged();
        }
    }

    public boolean isIndeterminate() {
        ProgressBar progressBar = this.mProgress;
        return progressBar != null ? progressBar.isIndeterminate() : this.mIndeterminate;
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        View viewInflate;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R.styleable.AlertDialog, android.R.attr.alertDialogStyle, 0);
        TypedArray typedArrayObtainStyledAttributes2 = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.dialogProgressPercentColor});
        final int color = typedArrayObtainStyledAttributes2.getColor(0, getContext().getResources().getColor(R.color.miuix_appcompat_dialog_default_progress_percent_color));
        typedArrayObtainStyledAttributes2.recycle();
        boolean z2 = MiuixUIUtils.getFontLevel(getContext()) == 2;
        if (this.mProgressStyle == 1) {
            this.mViewUpdateHandler = new Handler() { // from class: miuix.appcompat.app.ProgressDialog.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    ProgressDialog.this.mMessageView.setText(ProgressDialog.this.mMessage);
                    if (ProgressDialog.this.mProgressPercentFormat == null || ProgressDialog.this.mProgressPercentView == null) {
                        return;
                    }
                    double max = ((double) ProgressDialog.this.mProgressVal) / ((double) ProgressDialog.this.mProgress.getMax());
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    String str = ProgressDialog.this.mProgressPercentFormat.format(max);
                    spannableStringBuilder.append((CharSequence) str);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(color), 0, str.length(), 34);
                    ProgressDialog.this.mProgress.setProgress(ProgressDialog.this.mProgressVal);
                    ProgressDialog.this.mProgressPercentView.setText(spannableStringBuilder);
                }
            };
            viewInflate = layoutInflaterFrom.inflate(typedArrayObtainStyledAttributes.getResourceId(R.styleable.AlertDialog_horizontalProgressLayout, z2 ? R.layout.miuix_appcompat_alert_dialog_progress_xl_font : R.layout.miuix_appcompat_alert_dialog_progress), (ViewGroup) null);
            this.mProgressPercentView = (TextView) viewInflate.findViewById(R.id.progress_percent);
        } else {
            viewInflate = layoutInflaterFrom.inflate(typedArrayObtainStyledAttributes.getResourceId(R.styleable.AlertDialog_progressLayout, R.layout.miuix_appcompat_progress_dialog), (ViewGroup) null);
        }
        this.mProgress = (ProgressBar) viewInflate.findViewById(android.R.id.progress);
        this.mMessageView = (TextView) viewInflate.findViewById(R.id.message);
        setView(viewInflate);
        typedArrayObtainStyledAttributes.recycle();
        int i2 = this.mMax;
        if (i2 > 0) {
            setMax(i2);
        }
        int i3 = this.mProgressVal;
        if (i3 > 0) {
            setProgress(i3);
        }
        int i4 = this.mSecondaryProgressVal;
        if (i4 > 0) {
            setSecondaryProgress(i4);
        }
        int i5 = this.mIncrementBy;
        if (i5 > 0) {
            incrementProgressBy(i5);
        }
        int i6 = this.mIncrementSecondaryBy;
        if (i6 > 0) {
            incrementSecondaryProgressBy(i6);
        }
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            setProgressDrawable(drawable);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            setIndeterminateDrawable(drawable2);
        }
        CharSequence charSequence = this.mMessage;
        if (charSequence != null) {
            setMessage(charSequence);
        }
        setIndeterminate(this.mIndeterminate);
        onProgressChanged();
        super.onCreate(bundle);
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onStart() {
        super.onStart();
        this.mHasStarted = true;
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onStop() {
        super.onStop();
        this.mHasStarted = false;
    }

    public void setIndeterminate(boolean z2) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setIndeterminate(z2);
        } else {
            this.mIndeterminate = z2;
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setIndeterminateDrawable(drawable);
        } else {
            this.mIndeterminateDrawable = drawable;
        }
    }

    public void setMax(int i2) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mMax = i2;
        } else {
            progressBar.setMax(i2);
            onProgressChanged();
        }
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setMessage(CharSequence charSequence) {
        if (this.mProgress == null) {
            this.mMessage = charSequence;
            return;
        }
        if (this.mProgressStyle == 1) {
            this.mMessage = charSequence;
        }
        this.mMessageView.setText(charSequence);
    }

    public void setProgress(int i2) {
        this.mProgressVal = i2;
        if (this.mHasStarted) {
            onProgressChanged();
        }
    }

    public void setProgressDrawable(Drawable drawable) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setProgressDrawable(drawable);
        } else {
            this.mProgressDrawable = drawable;
        }
    }

    public void setProgressNumberFormat(String str) {
        this.mProgressNumberFormat = str;
        onProgressChanged();
    }

    public void setProgressPercentFormat(NumberFormat numberFormat) {
        this.mProgressPercentFormat = numberFormat;
        onProgressChanged();
    }

    public void setProgressStyle(int i2) {
        this.mProgressStyle = i2;
    }

    public void setSecondaryProgress(int i2) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mSecondaryProgressVal = i2;
        } else {
            progressBar.setSecondaryProgress(i2);
            onProgressChanged();
        }
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z2) {
        return show(context, charSequence, charSequence2, z2, false, null);
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z2, boolean z3) {
        return show(context, charSequence, charSequence2, z2, z3, null);
    }

    public ProgressDialog(Context context, int i2) {
        super(context, i2);
        this.mProgressStyle = 0;
        initFormats();
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z2, boolean z3, DialogInterface.OnCancelListener onCancelListener) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(charSequence);
        progressDialog.setMessage(charSequence2);
        progressDialog.setIndeterminate(z2);
        progressDialog.setCancelable(z3);
        progressDialog.setOnCancelListener(onCancelListener);
        progressDialog.show();
        return progressDialog;
    }
}
