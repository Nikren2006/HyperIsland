package miuix.animation.utils;

import miuix.animation.utils.LinkNode;

/* JADX INFO: loaded from: classes4.dex */
public class LinkNode<T extends LinkNode> {
    public T next;

    public void addToTail(T t2) {
        while (this != t2) {
            LinkNode<T> linkNode = this.next;
            if (linkNode == null) {
                this.next = t2;
                return;
            }
            this = linkNode;
        }
    }

    public T destroy() {
        while (remove() != null) {
        }
        return null;
    }

    public T remove() {
        T t2 = this.next;
        this.next = null;
        return t2;
    }

    public int size() {
        int i2 = 0;
        while (true) {
            this = this.next;
            if (this == null) {
                return i2;
            }
            i2++;
        }
    }
}
