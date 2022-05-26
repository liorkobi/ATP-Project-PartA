package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.zip.Inflater;
public abstract class AState {

    public enum color {white, gray, black}

    color Color;
    AState Parent;
    int cost;

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

    public String toString() {
        return String.format("MY color : %s", this.Color);
    }
}

