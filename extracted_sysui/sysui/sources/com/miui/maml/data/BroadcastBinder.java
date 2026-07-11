package com.miui.maml.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.miui.maml.ScreenElementRoot;
import com.miui.maml.data.VariableBinder;
import com.miui.maml.util.MamlLog;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes2.dex */
public class BroadcastBinder extends VariableBinder {
    private static final boolean DBG = true;
    private static final String LOG_TAG = "BroadcastBinder";
    private static final int RECEIVER_EXPORTED = 2;
    public static final String TAG_NAME = "BroadcastBinder";
    private String mAction;
    private IntentFilter mIntentFilter;
    private MyBroadcastReceiver mIntentReceiver;
    private boolean mRegistered;

    public static class MyBroadcastReceiver extends BroadcastReceiver {
        private WeakReference<BroadcastBinder> mBinder;

        public MyBroadcastReceiver(BroadcastBinder broadcastBinder) {
            this.mBinder = new WeakReference<>(broadcastBinder);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BroadcastBinder broadcastBinder = this.mBinder.get();
            if (broadcastBinder == null) {
                MamlLog.w("BroadcastBinder", "ContentQueryTask: resolver or binder is null");
                return;
            }
            MamlLog.i("BroadcastBinder", "onNotify: " + broadcastBinder.toString());
            broadcastBinder.onNotify(context, intent, null);
        }
    }

    public static class Variable extends VariableBinder.Variable {
        public String mExtraName;

        public Variable(Element element, Variables variables) {
            super(element, variables);
            this.mExtraName = element.getAttribute("extra");
        }
    }

    public BroadcastBinder(Element element, ScreenElementRoot screenElementRoot) {
        super(element, screenElementRoot);
        load(element);
    }

    private void load(Element element) {
        if (element == null) {
            MamlLog.e("BroadcastBinder", "ContentProviderBinder node is null");
            throw new NullPointerException("node is null");
        }
        String attribute = element.getAttribute(com.xiaomi.onetrack.api.a.f2741a);
        this.mAction = attribute;
        if (TextUtils.isEmpty(attribute)) {
            MamlLog.e("BroadcastBinder", "no action in broadcast binder");
            throw new IllegalArgumentException("no action in broadcast binder element");
        }
        this.mIntentFilter = new IntentFilter(this.mAction);
        this.mIntentReceiver = new MyBroadcastReceiver(this);
        loadVariables(element);
    }

    private void updateVariables(Intent intent) {
        String stringExtra;
        double intExtra;
        if (intent == null) {
            return;
        }
        MamlLog.d("BroadcastBinder", "updateVariables: " + intent);
        Iterator<VariableBinder.Variable> it = this.mVariables.iterator();
        while (it.hasNext()) {
            Variable variable = (Variable) it.next();
            int i2 = variable.mType;
            if (i2 != 2) {
                if (i2 == 3) {
                    intExtra = intent.getIntExtra(variable.mExtraName, (int) variable.mDefNumberValue);
                } else if (i2 == 4) {
                    intExtra = intent.getLongExtra(variable.mExtraName, (long) variable.mDefNumberValue);
                } else if (i2 == 5) {
                    intExtra = intent.getFloatExtra(variable.mExtraName, (float) variable.mDefNumberValue);
                } else if (i2 != 6) {
                    MamlLog.w("BroadcastBinder", "invalide type" + variable.mTypeStr);
                    intExtra = 0.0d;
                } else {
                    intExtra = intent.getDoubleExtra(variable.mExtraName, variable.mDefNumberValue);
                }
                variable.set(intExtra);
                stringExtra = String.format("%f", Double.valueOf(intExtra));
            } else {
                stringExtra = intent.getStringExtra(variable.mExtraName);
                variable.set(stringExtra == null ? variable.mDefStringValue : stringExtra);
            }
            MamlLog.d("BroadcastBinder", "updateVariables: " + String.format("name:%s type:%s value:%s", variable.mName, variable.mTypeStr, stringExtra));
        }
    }

    public void addVariable(Variable variable) {
        this.mVariables.add(variable);
    }

    @Override // com.miui.maml.data.VariableBinder
    public void finish() {
        super.finish();
        unregister();
    }

    @Override // com.miui.maml.data.VariableBinder
    public void init() {
        super.init();
        register();
    }

    public void onNotify(Context context, Intent intent, Object obj) {
        updateVariables(intent);
        onUpdateComplete();
    }

    public void onRegister() {
        if (this.mIntentReceiver != null) {
            updateVariables(getContext().mContext.registerReceiver(this.mIntentReceiver, this.mIntentFilter, 2));
        }
        onUpdateComplete();
    }

    public void onUnregister() {
        if (this.mIntentReceiver != null) {
            getContext().mContext.unregisterReceiver(this.mIntentReceiver);
        }
    }

    public void register() {
        if (this.mRegistered) {
            return;
        }
        onRegister();
        this.mRegistered = true;
    }

    public void unregister() {
        if (this.mRegistered) {
            try {
                onUnregister();
            } catch (IllegalArgumentException unused) {
            }
            this.mRegistered = false;
        }
    }

    @Override // com.miui.maml.data.VariableBinder
    public Variable onLoadVariable(Element element) {
        return new Variable(element, getContext().mVariables);
    }
}
