package miuix.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.SupportMenuInflater;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.internal.widget.PopupMenuWindow;
import miuix.popupwidget.internal.strategy.IPopupWindowStrategy;

/* JADX INFO: loaded from: classes3.dex */
public class PopupMenu {
    private final View mAnchor;
    private final Context mContext;
    private final MenuBuilder mMenu;
    private OnMenuItemClickListener mMenuItemClickListener;
    private OnDismissListener mOnDismissListener;
    private PopupMenuWindow mPopupMenu;

    public interface OnDismissListener {
        void onDismiss(PopupMenu popupMenu);
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public PopupMenu(@NonNull Context context, @NonNull View view) {
        this(context, view, 0);
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mContext);
    }

    public void dismiss() {
        this.mPopupMenu.dismiss();
    }

    public float getDimAmount() {
        return this.mPopupMenu.getDimAmount();
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public int getWindowManagerFlags() {
        return this.mPopupMenu.getWindowManagerFlags();
    }

    public void inflate(@MenuRes int i2) {
        getMenuInflater().inflate(i2, this.mMenu);
    }

    public boolean isShowing() {
        PopupMenuWindow popupMenuWindow = this.mPopupMenu;
        if (popupMenuWindow == null) {
            return false;
        }
        return popupMenuWindow.isShowing();
    }

    public void setDimAmount(float f2) {
        this.mPopupMenu.setDimAmount(f2);
    }

    public void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnMenuItemClickListener(@Nullable OnMenuItemClickListener onMenuItemClickListener) {
        this.mMenuItemClickListener = onMenuItemClickListener;
    }

    public void setStrategy(IPopupWindowStrategy iPopupWindowStrategy) {
        this.mPopupMenu.setPopupWindowStrategy(iPopupWindowStrategy);
    }

    public void setWindowManagerFlags(int i2) {
        this.mPopupMenu.setWindowManagerFlags(i2);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void show() {
        this.mPopupMenu.update(this.mMenu);
        this.mPopupMenu.showAsDropDown(this.mAnchor);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void showAsDropDown(int i2, int i3) {
        this.mPopupMenu.update(this.mMenu);
        this.mPopupMenu.setHorizontalOffset(i2);
        this.mPopupMenu.setVerticalOffset(i3);
        this.mPopupMenu.showAsDropDown(this.mAnchor);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void showAtLocation(View view, int i2, int i3, int i4) {
        this.mPopupMenu.setClippingEnabled(true);
        this.mPopupMenu.update(this.mMenu);
        this.mPopupMenu.showAtLocation(view, i2, i3, i4);
    }

    public PopupMenu(@NonNull Context context, @NonNull View view, int i2) {
        if (i2 == 0) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.miuiPopupMenu, R.attr.miuiPopupMenuStyle, 0);
            try {
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.miuiPopupMenu_miuiPopupTheme, 0);
                typedArrayObtainStyledAttributes.recycle();
                i2 = resourceId;
            } catch (Throwable th) {
                typedArrayObtainStyledAttributes.recycle();
                throw th;
            }
        }
        if (i2 != 0) {
            this.mContext = new ContextThemeWrapper(context, i2);
        } else {
            this.mContext = context;
        }
        this.mAnchor = view;
        this.mMenu = new MenuBuilder(this.mContext);
        this.mPopupMenu = new PopupMenuWindow(this.mContext) { // from class: miuix.appcompat.widget.PopupMenu.1
            @Override // miuix.internal.widget.PopupMenuWindow
            public void onDismiss() {
                if (PopupMenu.this.mOnDismissListener != null) {
                    PopupMenu.this.mOnDismissListener.onDismiss(PopupMenu.this);
                }
            }

            @Override // miuix.internal.widget.PopupMenuWindow
            public void onMenuItemClick(MenuItem menuItem) {
                if (PopupMenu.this.mMenuItemClickListener != null) {
                    PopupMenu.this.mMenuItemClickListener.onMenuItemClick(menuItem);
                }
            }
        };
    }
}
