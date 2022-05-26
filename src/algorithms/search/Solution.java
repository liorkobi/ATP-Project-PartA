package algorithms.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Solution {
    ArrayList<AState> sol;

    public void setSol(AState state) {
        sol.add(state);
    }

    public Solution() {
        sol=new ArrayList<AState>();
    }
    public ArrayList<AState> getSolutionPath() {
        Collections.reverse(sol);
        return sol;
    }
}
