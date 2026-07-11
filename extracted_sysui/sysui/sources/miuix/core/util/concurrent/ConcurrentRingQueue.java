package miuix.core.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import miuix.core.util.concurrent.Queue;

/* JADX INFO: loaded from: classes3.dex */
public class ConcurrentRingQueue<T> implements Queue<T> {
    private volatile int mAdditional;
    private final boolean mAllowExtendCapacity;
    private final boolean mAutoReleaseCapacity;
    private int mCapacity;
    private volatile Node<T> mReadCursor;
    private volatile Node<T> mWriteCursor;
    private final AtomicInteger mReadLock = new AtomicInteger(0);
    private final AtomicInteger mWriteLock = new AtomicInteger(0);

    public static class Node<T> {
        T element;
        Node<T> next;

        private Node() {
        }
    }

    public ConcurrentRingQueue(int i2, boolean z2, boolean z3) {
        this.mCapacity = i2;
        this.mAllowExtendCapacity = z2;
        this.mAutoReleaseCapacity = z3;
        int i3 = 0;
        this.mReadCursor = new Node<>();
        this.mWriteCursor = this.mReadCursor;
        Node<T> node = this.mReadCursor;
        while (i3 < i2) {
            Node<T> node2 = new Node<>();
            node.next = node2;
            i3++;
            node = node2;
        }
        node.next = this.mReadCursor;
    }

    @Override // miuix.core.util.concurrent.Queue
    public int clear() {
        while (true) {
            if (this.mReadLock.get() == 0 && this.mReadLock.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.mReadCursor;
        int i2 = 0;
        while (node != this.mWriteCursor) {
            node.element = null;
            i2++;
            node = node.next;
        }
        this.mReadCursor = node;
        this.mReadLock.set(0);
        return i2;
    }

    public void decreaseCapacity(int i2) {
        if (!this.mAutoReleaseCapacity || i2 <= 0) {
            return;
        }
        while (true) {
            if (this.mWriteLock.get() == 0 && this.mWriteLock.compareAndSet(0, -1)) {
                this.mCapacity -= i2;
                this.mAdditional = i2;
                this.mWriteLock.set(0);
                return;
            }
            Thread.yield();
        }
    }

    @Override // miuix.core.util.concurrent.Queue
    public T get() {
        while (true) {
            if (this.mReadLock.get() == 0 && this.mReadLock.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.mReadCursor;
        Node<T> node2 = this.mWriteCursor;
        T t2 = null;
        while (t2 == null && node != node2) {
            t2 = node.element;
            node.element = null;
            node = node.next;
            node2 = this.mWriteCursor;
        }
        if (t2 != null) {
            this.mReadCursor = node;
        }
        this.mReadLock.set(0);
        return t2;
    }

    @Override // miuix.core.util.concurrent.Queue
    public int getCapacity() {
        int i2 = this.mAdditional;
        int i3 = this.mCapacity;
        return i2 > 0 ? i3 + i2 : i3;
    }

    public void increaseCapacity(int i2) {
        if (this.mAllowExtendCapacity || i2 <= 0) {
            return;
        }
        while (true) {
            if (this.mWriteLock.get() == 0 && this.mWriteLock.compareAndSet(0, -1)) {
                this.mAdditional = -i2;
                this.mCapacity += i2;
                this.mWriteLock.set(0);
                return;
            }
            Thread.yield();
        }
    }

    @Override // miuix.core.util.concurrent.Queue
    public boolean isEmpty() {
        return this.mWriteCursor == this.mReadCursor;
    }

    @Override // miuix.core.util.concurrent.Queue
    public boolean put(T t2) {
        if (t2 == null) {
            return false;
        }
        while (true) {
            if (this.mWriteLock.get() == 0 && this.mWriteLock.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.mReadCursor;
        Node<T> node2 = this.mWriteCursor;
        int i2 = this.mAdditional;
        Node<T> node3 = node2.next;
        boolean z2 = true;
        if (node3 != node) {
            node2.element = t2;
            Node<T> node4 = node3.next;
            if (node4 != node && this.mAutoReleaseCapacity && i2 > 0) {
                node2.next = node4;
                this.mAdditional = i2 - 1;
            }
            this.mWriteCursor = node2.next;
        } else if (this.mAllowExtendCapacity || i2 < 0) {
            Node<T> node5 = new Node<>();
            node2.next = node5;
            node5.next = node;
            node2.element = t2;
            this.mAdditional = i2 + 1;
            this.mWriteCursor = node2.next;
        } else {
            z2 = false;
        }
        this.mWriteLock.set(0);
        return z2;
    }

    @Override // miuix.core.util.concurrent.Queue
    public boolean remove(T t2) {
        boolean z2;
        if (t2 == null) {
            return false;
        }
        while (true) {
            if (this.mReadLock.get() == 0 && this.mReadLock.compareAndSet(0, -1)) {
                break;
            }
            Thread.yield();
        }
        Node<T> node = this.mReadCursor;
        while (true) {
            if (node == this.mWriteCursor) {
                z2 = false;
                break;
            }
            if (t2.equals(node.element)) {
                node.element = null;
                z2 = true;
                break;
            }
            node = node.next;
        }
        this.mReadLock.set(0);
        return z2;
    }

    @Override // miuix.core.util.concurrent.Queue
    public int remove(Queue.Predicate<T> predicate) {
        if (predicate == null) {
            return 0;
        }
        while (true) {
            if (this.mReadLock.get() == 0 && this.mReadLock.compareAndSet(0, -1)) {
                try {
                    break;
                } catch (Throwable th) {
                    this.mReadLock.set(0);
                    throw th;
                }
            }
            Thread.yield();
        }
        int i2 = 0;
        for (Node<T> node = this.mReadCursor; node != this.mWriteCursor; node = node.next) {
            if (predicate.apply(node.element)) {
                node.element = null;
                i2++;
            }
        }
        this.mReadLock.set(0);
        return i2;
    }
}
