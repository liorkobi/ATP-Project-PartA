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

    public Solution() {
        sol=new ArrayList<AState>();
    }

    public ArrayList<AState> getSolutionPath() {
        Collections.reverse(sol);
        return sol;
    }

    public void setSol(AState state) {
        sol.add(state);
    }

}
