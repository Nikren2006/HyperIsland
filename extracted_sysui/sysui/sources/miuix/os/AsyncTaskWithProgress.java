package miuix.os;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.HashMap;
import miuix.appcompat.app.ProgressDialog;

/* JADX INFO: loaded from: classes.dex */
public abstract class AsyncTaskWithProgress<Params, Result> extends AsyncTask<Params, Integer, Result> {
    private static final HashMap<String, AsyncTaskWithProgress<?, ?>> sAllTasks = new HashMap<>();
    private final FragmentManager mFragmentManager;
    private int mTheme = 0;
    private int mTitleId = 0;
    private CharSequence mTitle = null;
    private int mMessageId = 0;
    private CharSequence mMessage = null;
    private boolean mCancelable = false;
    private boolean mIndeterminate = false;
    private int mMaxProgress = 0;
    private int mProgressStyle = 0;
    private int mCurrentProgress = 0;
    private volatile ProgressDialogFragment mFragment = null;
    private final AsyncTaskWithProgress<Params, Result>.Listeners mListeners = new Listeners();

    public class Listeners implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
        private Listeners() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            onClick(dialogInterface, -2);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Dialog dialog;
            if (AsyncTaskWithProgress.this.mFragment == null || (dialog = AsyncTaskWithProgress.this.mFragment.getDialog()) == null || dialogInterface != dialog || i2 != -2) {
                return;
            }
            AsyncTaskWithProgress.this.cancel(true);
        }
    }

    public static class ProgressDialogFragment extends DialogFragment {
        private AsyncTaskWithProgress<?, ?> mTask;

        public static ProgressDialogFragment newInstance(String str) {
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("task", str);
            progressDialogFragment.setArguments(bundle);
            return progressDialogFragment;
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            AsyncTaskWithProgress<?, ?> asyncTaskWithProgress = this.mTask;
            if (asyncTaskWithProgress != null && ((AsyncTaskWithProgress) asyncTaskWithProgress).mCancelable) {
                ((AsyncTaskWithProgress) this.mTask).mListeners.onCancel(dialogInterface);
            }
            super.onCancel(dialogInterface);
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            AsyncTaskWithProgress<?, ?> asyncTaskWithProgress = (AsyncTaskWithProgress) AsyncTaskWithProgress.sAllTasks.get(getArguments().getString("task"));
            this.mTask = asyncTaskWithProgress;
            if (asyncTaskWithProgress == null) {
                FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.remove(this);
                fragmentTransactionBeginTransaction.commit();
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            if (this.mTask == null) {
                return super.onCreateDialog(bundle);
            }
            ProgressDialog progressDialog = new ProgressDialog(getActivity(), ((AsyncTaskWithProgress) this.mTask).mTheme);
            if (((AsyncTaskWithProgress) this.mTask).mTitleId != 0) {
                progressDialog.setTitle(((AsyncTaskWithProgress) this.mTask).mTitleId);
            } else {
                progressDialog.setTitle(((AsyncTaskWithProgress) this.mTask).mTitle);
            }
            if (((AsyncTaskWithProgress) this.mTask).mMessageId != 0) {
                progressDialog.setMessage(getActivity().getText(((AsyncTaskWithProgress) this.mTask).mMessageId));
            } else {
                progressDialog.setMessage(((AsyncTaskWithProgress) this.mTask).mMessage);
            }
            progressDialog.setProgressStyle(((AsyncTaskWithProgress) this.mTask).mProgressStyle);
            progressDialog.setIndeterminate(((AsyncTaskWithProgress) this.mTask).mIndeterminate);
            if (!((AsyncTaskWithProgress) this.mTask).mIndeterminate) {
                progressDialog.setMax(((AsyncTaskWithProgress) this.mTask).mMaxProgress);
                progressDialog.setProgress(((AsyncTaskWithProgress) this.mTask).mCurrentProgress);
            }
            if (((AsyncTaskWithProgress) this.mTask).mCancelable) {
                progressDialog.setButton(-2, progressDialog.getContext().getText(R.string.cancel), ((AsyncTaskWithProgress) this.mTask).mListeners);
                progressDialog.setCancelable(true);
            } else {
                progressDialog.setButton(-2, (CharSequence) null, (DialogInterface.OnClickListener) null);
                progressDialog.setCancelable(false);
            }
            return progressDialog;
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onStart() {
            super.onStart();
            AsyncTaskWithProgress<?, ?> asyncTaskWithProgress = this.mTask;
            if (asyncTaskWithProgress != null) {
                ((AsyncTaskWithProgress) asyncTaskWithProgress).mFragment = this;
            }
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onStop() {
            AsyncTaskWithProgress<?, ?> asyncTaskWithProgress = this.mTask;
            if (asyncTaskWithProgress != null) {
                ((AsyncTaskWithProgress) asyncTaskWithProgress).mFragment = null;
            }
            super.onStop();
        }

        public void setProgress(int i2) {
            Dialog dialog = getDialog();
            if (dialog instanceof ProgressDialog) {
                ((ProgressDialog) dialog).setProgress(i2);
            }
        }
    }

    public AsyncTaskWithProgress(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    private void dismissDialog() {
        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) this.mFragmentManager.findFragmentByTag("AsyncTaskWithProgress@" + hashCode());
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
        } else if (this.mFragment != null) {
            this.mFragment.dismissAllowingStateLoss();
        }
    }

    public Activity getActivity() {
        if (this.mFragment != null) {
            return this.mFragment.getActivity();
        }
        return null;
    }

    @Override // android.os.AsyncTask
    public void onCancelled() {
        sAllTasks.remove("AsyncTaskWithProgress@" + hashCode());
        dismissDialog();
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(Result result) {
        sAllTasks.remove("AsyncTaskWithProgress@" + hashCode());
        dismissDialog();
    }

    @Override // android.os.AsyncTask
    public void onPreExecute() {
        String str = "AsyncTaskWithProgress@" + hashCode();
        sAllTasks.put(str, this);
        if (this.mFragmentManager != null) {
            this.mFragment = ProgressDialogFragment.newInstance(str);
            this.mFragment.setCancelable(this.mCancelable);
            this.mFragment.show(this.mFragmentManager, str);
        }
    }

    public AsyncTaskWithProgress<Params, Result> setCancelable(boolean z2) {
        this.mCancelable = z2;
        return this;
    }

    public AsyncTaskWithProgress<Params, Result> setIndeterminate(boolean z2) {
        this.mIndeterminate = z2;
        return this;
    }

    public AsyncTaskWithProgress<Params, Result> setMaxProgress(int i2) {
        this.mMaxProgress = i2;
        return this;
    }

    public AsyncTaskWithProgress<Params, Result> setMessage(int i2) {
        this.mMessageId = i2;
        this.mMessage = null;
        return this;
    }

    public AsyncTaskWithProgress<Params, Result> setProgressStyle(int i2) {
        this.mProgressStyle = i2;
        return this;
    }

    public AsyncTaskWithProgress<Params, Result> setTheme(int i2) {
        this.mTheme = i2;
        return this;
    }

    public AsyncTaskWithProgress<Params, Result> setTitle(int i2) {
        this.mTitleId = i2;
        this.mTitle = null;
        return this;
    }

    @Override // android.os.AsyncTask
    public void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate((Object[]) numArr);
        this.mCurrentProgress = numArr[0].intValue();
        if (this.mFragment != null) {
            this.mFragment.setProgress(this.mCurrentProgress);
        }
    }

    public AsyncTaskWithProgress<Params, Result> setMessage(CharSequence charSequence) {
        this.mMessageId = 0;
        this.mMessage = charSequence;
        return this;
    }

    public AsyncTaskWithProgress<Params, Result> setTitle(CharSequence charSequence) {
        this.mTitleId = 0;
        this.mTitle = charSequence;
        return this;
    }
}
