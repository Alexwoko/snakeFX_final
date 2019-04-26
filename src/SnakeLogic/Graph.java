package SnakeLogic;

public class Graph<T extends GraphItem> {

    T rootNode;


    public void Graph(){



    }

    public boolean addNode(T node){

        if(rootNode == null){
            return true;
        } else{
            return addNeighbourNode(rootNode, node);

        }


    }

    public boolean addNeighbourNode(T rootNode, T node){

        if(rootNode.getEdges().length < 4){
            Edge e = new Edge(rootNode, node);
            rootNode.addEdge(e);
            return true;

        }else {
            for(Edge e : rootNode.getEdges()){
                addNeighbourNode((T)e, node);

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
           return askNeighbours(rootNode, index);
        }


    }

public boolean askNeighbours(T rootNode, int index){


        if(rootNode.getEdges().length <= 4){
            for(Edge e : rootNode.getEdges()){
                if(e.getEnd().getGridIndex() == index){
                    return true;
                }

            }

        }else{
            for(Edge e : rootNode.getEdges()){
                askNeighbours((T)e.getEnd(), index);

            }

        }

return false;

}


}
