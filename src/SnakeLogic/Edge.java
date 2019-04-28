package SnakeLogic;

public class Edge<T extends GraphItem> {


    private T start;
    private T end;

    public Edge(T a, T b){

        start = a;
        end = b;

    }


    public Edge(T a){
        start = a;
    }

    public Edge(T b, String nothing){
        end = b;
        nothing = null;
    }

public T getStart(){return start;}
public T getEnd(){return end;}


public void setStart(T s){start = s;}
public void setEnd(T e){end = e;}

public boolean hasEnd(){if(end != null){return true;}

    return false;
    }


public String toString(){


        StringBuilder sb = new StringBuilder();
        sb.append(" Node A = " + start);
        sb.append(", Node B = " + end);

        return sb.toString();
}


}
