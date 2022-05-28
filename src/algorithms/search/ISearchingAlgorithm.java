package algorithms.search;

/**
 * declaring methods that searching algorithms must implement
 */
public interface ISearchingAlgorithm {

    Solution solve(ISearchable domain);

    String getName();

    int getNumberOfNodesEvaluated();
}
