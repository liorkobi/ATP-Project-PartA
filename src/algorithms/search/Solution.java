package algorithms.search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> sol;

    public void setSol(AState state) {
        sol.add(state);
    }

    public Solution() {
        sol=new ArrayList<AState>();
    }
    public ArrayList<AState> getSolutionPath() {
        return sol;
    }
}
