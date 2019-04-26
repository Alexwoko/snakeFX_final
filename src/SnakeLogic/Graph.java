package SnakeLogic;

public class Graph<T extends GraphItem> {

    T rootNode;
int graphSize;

    public void Graph(){

graphSize = 0;

    }

    public boolean addNode(T node){

        if(rootNode == null){
            rootNode = node;
            rootNode.setVisited(true);
            graphSize++;
            return true;
        } else{
            return addNeighbourNode(rootNode, node);

        }

    }

    public boolean addNeighbourNode(T rootNode, T node){

        if(rootNode.getNumOfEdges() < 4){
            Edge e = new Edge(rootNode, node);
            node.setVisited(true);
            node.setFrom(rootNode);
            rootNode.addEdge(e);
            graphSize++;
            return true;

        }else if(rootNode.getNumOfEdges() > 0){
            for(Edge e : rootNode.getEdges()){
                addNeighbourNode((T)e.getEnd(), node);

            }
        }
        return false;
    }

    public boolean containsNode(int index){

        if(rootNode == null){
            return false;
        } else if(rootNode.getGridIndex() == index){
            return true;
        } else{
           return containsNeighbours(rootNode, index);
        }


    }

public boolean containsNeighbours(T rootNode, int index){


        if(rootNode.getNumOfEdges() <= 4 && rootNode.getNumOfEdges() > 0){
            for(Edge e : rootNode.getEdges()){
                if(e.getEnd() != null && e.getEnd().getGridIndex() == index){
                    return true;
                }
            }

        }else if(rootNode.getNumOfEdges() > 0){
            for(Edge e : rootNode.getEdges()){
                containsNeighbours((T)e.getEnd(), index);

            }

        }

return false;

}

public void emptyGraph(){

        rootNode = null;
        graphSize =  0;

}

public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Root node = " + rootNode);
        sb.append(", Graph size = " + graphSize);

        return sb.toString();

}


}
