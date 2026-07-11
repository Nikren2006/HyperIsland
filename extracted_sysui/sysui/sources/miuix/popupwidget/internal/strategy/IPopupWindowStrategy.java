package miuix.popupwidget.internal.strategy;

/* JADX INFO: loaded from: classes5.dex */
public interface IPopupWindowStrategy {
    int getXInWindow(PopupWindowSpec popupWindowSpec);

    int getYInWindow(PopupWindowSpec popupWindowSpec);

    boolean isNeedScroll(int i2, PopupWindowSpec popupWindowSpec);

    void measureContentSize(PopupWindowSpec popupWindowSpec);
}
