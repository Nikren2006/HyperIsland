package miuix.view.inputmethod;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.NonNull;
import miuix.core.util.SoftReferenceSingleton;
import miuix.reflect.ReflectionHelper;

/* JADX INFO: loaded from: classes5.dex */
public class InputMethodHelper {
    private static final SoftReferenceSingleton<InputMethodHelper> INSTANCE = new SoftReferenceSingleton<InputMethodHelper>() { // from class: miuix.view.inputmethod.InputMethodHelper.1
        @Override // miuix.core.util.SoftReferenceSingleton
        public InputMethodHelper createInstance(Object obj) {
            return new InputMethodHelper((Context) obj);
        }
    };
    private static Boolean sSupportViewServedCallback;
    private InputMethodManager mManager;

    public static InputMethodHelper getInstance(Context context) {
        return INSTANCE.get(context);
    }

    public static boolean removeViewServedCallback(@NonNull Context context, @NonNull View view) {
        Boolean bool = sSupportViewServedCallback;
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        try {
            ReflectionHelper.invoke(InputMethodManager.class, getInstance(context).getManager(), "removeViewServedCallback", new Class[]{View.class}, view);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean setViewServedCallback(@NonNull Context context, @NonNull View view, @NonNull Runnable runnable) {
        Boolean bool = sSupportViewServedCallback;
        if (bool != null && !bool.booleanValue()) {
            return false;
        }
        try {
            ReflectionHelper.invoke(InputMethodManager.class, getInstance(context).getManager(), "setViewServedCallback", new Class[]{View.class, Runnable.class}, view, runnable);
            sSupportViewServedCallback = Boolean.TRUE;
            return true;
        } catch (Exception unused) {
            sSupportViewServedCallback = Boolean.FALSE;
            return false;
        }
    }

    public InputMethodManager getManager() {
        return this.mManager;
    }

    public void hideKeyBoard(EditText editText) {
        this.mManager.hideSoftInputFromInputMethod(editText.getWindowToken(), 0);
    }

    public void showKeyBoard(EditText editText) {
        editText.requestFocus();
        this.mManager.viewClicked(editText);
        this.mManager.showSoftInput(editText, 0);
    }

    private InputMethodHelper(Context context) {
        this.mManager = (InputMethodManager) context.getApplicationContext().getSystemService("input_method");
    }
}
