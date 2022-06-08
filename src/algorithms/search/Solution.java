package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * final result of the solution.
 */
public class Solution implements Serializable {
    ArrayList<AState> sol;

    public Solution(AState start, AState goal) {

        sol = new ArrayList<AState>();
        sol.add(goal);
        AState tmp = goal.getParent();
        while(tmp != null){
            sol.add(tmp);
            tmp = tmp.getParent();
        }
        Collections.reverse(sol);

    }

    public ArrayList<AState> getSolutionPath() {
        return sol;
    }

    public void setSol(AState state) {
        sol.add(state);
    }

    public ArrayList<AState> getSol() {
        return sol;
    }
}
