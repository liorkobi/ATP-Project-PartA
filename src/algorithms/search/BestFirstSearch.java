
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

}
