package com.example.project;

import java.util.ArrayList;

public class GraphAdjacentList implements Graph {

    private ArrayList<Vertex> vertices;
    private int numVertices;

    public GraphAdjacentList() {
        vertices = new ArrayList<>();
    }

    // Agregar una arista desde un vertice 'from' a un vertice 'to'
    public boolean addEdge(int from, int to) {
        Vertex fromV = null, toV = null;
        for (Vertex v : vertices) {
            if (from == v.data) { // verificando si 'from' existe
                fromV = v;
            } else if (to == v.data) { // verificando si 'to' existe
                toV = v;
            }
            if (fromV != null && toV != null) {
                break; // ambos nodos deben existir, si no paramos
            }
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            vertices.add(fromV);
            numVertices++;
        }
        if (toV == null) {
            toV = new Vertex(to);
            vertices.add(toV);
            numVertices++;
        }        
        return fromV.addAdjacentVertex(toV);
    }

    // Eliminamos la arista del vertice 'from' al vertice 'to'
    public boolean removeEdge(int from, int to) {
        Vertex fromV = null;
        for (Vertex v : vertices) {
            if (from == v.data) {
                fromV = v;
                break;
            }
        }
        if (fromV == null) {
            return false;
        }
        return fromV.removeAdjacentVertex(to);
    }
    public ArrayList<Vertex> depthFirstSearch(Vertex v,ArrayList<Vertex> visited){
        visited.add(v);
        for(Vertex a : v.adjacentVertices){
            if(!visited.contains(a))
                depthFirstSearch(a, visited);
        }
        return visited;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent vertices: ");
            for (Vertex v2 : v.adjacentVertices) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getNumEdges(){
        int count = 0;
        for(int i = 0; i < this.vertices.size(); i++){
            count += this.vertices.get(i).adjacentVertices.size();
        }
        return count;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int countConnectedComponents(){
        int cont=0;
        ArrayList<Vertex> vertex=new ArrayList<Vertex>();
        vertex.addAll(this.vertices);
        while(vertex.size()>0){
            for(Vertex a : depthFirstSearch(vertex.get(vertex.size()-1),new ArrayList<Vertex>()))
                vertex.remove(a);
            cont++;
        }
        return cont;
    }
    
    public boolean removeVertex(int vertex){
        Vertex auxi=null;
        for(Vertex a : vertices){
            if(a.data==vertex)
                auxi=a;
        }
        if(auxi==null) return false;
        //borrar el propio vertice
        vertices.remove(auxi);
        //borrar las aristas adyacentes a el
        for(Vertex a : vertices){
        	//removeAdjacentVertex ya lo hace en la clase Vertex
            a.removeAdjacentVertex(vertex);
        }
        numVertices=vertices.size();
        return true;
    }

    public static void main(String args[]) {
        GraphAdjacentList graph = new GraphAdjacentList();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);        
        System.out.println(graph);
        /*ArrayList<Vertex> prueba=new ArrayList<Vertex> ();
        prueba.add(new Vertex(5));
        graph.depthFirstSearch(prueba.get(0), new ArrayList<Vertex>());*/
    }
}
