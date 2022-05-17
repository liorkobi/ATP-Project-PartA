
package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        Comparator<AState> comparator = (o1, o2) -> {
            if (o1.getCost() < o2.getCost())
                return -1;
            if (o1.getCost() > o2.getCost())
                return 1;

            return 0;
        };
     this.VQ =new PriorityQueue<AState>(comparator);

    }


    @Override
    public String getName() {
        return "BestFirstSearch";
    }
/*
    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return (i * 2) + 1;
    }

    private int rightChild(int i) {
        return (i * 2) + 2;
    }

    private boolean isLeaf(int i) {
        if (rightChild(i) >= VQ.size() || leftChild(i) >= VQ.size()) {
            return true;
        }
        return false;
    }

    public void insert(AState element) {
        if (idx >= VQ.size()) {
            return;
        }
        VQ.add(idx,element);
        int current = idx;

        while (VQ.get(current).compareTo(VQ.get(parent(current))) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
        idx++;
    }

    // removes and returns the minimum element from the heap
    public AState remove() {
        // since its a min heap, so root = minimum
        AState popped = VQ.remove(0);
        VQ.add(VQ.get(--idx));
        minHeapify(0);
        return popped;
    }

    // heapify the node at i
    private void minHeapify(int i) {
        // If the node is a non-leaf node and any of its child is smaller
        if (!isLeaf(i)) {
            if (VQ.get(i).compareTo(VQ.get(leftChild(i)))==-1 ||
                    VQ.get(i).compareTo(VQ.get(rightChild(i)))==-1) {
                if (VQ.get(leftChild(i)).compareTo(VQ.get(rightChild(i)))==-1) {
                    swap(i, leftChild(i));
                    minHeapify(leftChild(i));
                } else {
                    swap(i, rightChild(i));
                    minHeapify(rightChild(i));
                }
            }
        }
    }

    // builds the min-heap using the minHeapify
    public void minHeap() {
        for (int i = (idx - 1 / 2); i >= 1; i--) {
            minHeapify(i);
        }
    }

    // swaps two nodes of the heap
    private void swap(int x, int y) {
        AState tmp;
        tmp = VQ.get(x);
        VQ.add(x,VQ.get(y));
        VQ.add(y,tmp);
    }

 */
}
