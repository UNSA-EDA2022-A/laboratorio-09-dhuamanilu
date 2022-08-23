package com.example.project;

public class Exercise1 {

	public static void main(final String[] args) {

		Exercise1 obj = new Exercise1();
		GraphAdjacentList graph = new GraphAdjacentList();
		graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
    	int result = obj.countConnectedComponents(graph);
		
		System.out.println(result);
		
	}

	public int countConnectedComponents(Graph grafo) {

		return grafo.countConnectedComponents();
	}
}
