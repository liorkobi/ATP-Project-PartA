package algorithms.search;

import java.io.Serializable;

/**
 * state of a searchable problem
 * color represents the status of the state during the solving process.
 */
public abstract class AState implements Serializable {

     public enum color {white, gray, black}

    protected  color Color;
    protected AState Parent;
    protected  int cost;

    public AState() {
        Parent = null;
        cost = 0;
        Color = color.white;
    }

    public color getColor() {
        return Color;
    }

    public void setColor(color color) {
        Color = color;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    //the AState's ancestor
    public AState getParent() {
        return Parent;
    }

    public void setParent(AState parent) {
        if (parent != null)
            Parent = parent;
    }

}

