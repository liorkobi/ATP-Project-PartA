
package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        Comparator<AState> comparator = Comparator.comparingInt(AState::getCost);

     this.VQ =new PriorityQueue<AState>(comparator);

    }


    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
