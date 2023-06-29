//R.M.S.J Bandara
//w1866979
//20200649
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //Array to store the Edges read from the txt file
        ArrayList<String []> edges = new ArrayList<>();
        //Array to store the visited vertices when Graph Traversal
        ArrayList<Vertex> visitedVertices = new ArrayList<>();
        //To store the Vertices data used a set to stop duplicate values
        Set<String> verticesString;

        //Read from the txt file
        readFile(edges);
        //Add the array of vertices got from the Edges
        verticesString = addDataFromFile(edges);
        //System.out.println("Vertices" + verticesString);

        // Create Graph
        Graph graph = new Graph();
        addVertexToGraph(verticesString, graph);
        addEdgesOfTheGraph(edges, graph);

        //Adding the Depth first search starting vertex to the visitedVertices Array
        visitedVertices.add(graph.vertices.get(0));
        //Do the Depth First Search
        depthFirstSearch(graph.vertices.get(0), visitedVertices);

        //Check for cycle in the graph and display the cycle
        if(isCycle(visitedVertices)){
            System.out.println("\n---Graph does have a cycle---");
        }else {
            System.out.println("\n---Graph does not have a cycle---");
        }

        //Check if the Graph is an Acyclic
        if(isAcyclic(graph)){
            System.out.println("\n---Graph is an Acyclic---");
        }else {
            System.out.println("\n---Graph is not an Acyclic---");
        }

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("Time taken to run program: " + timeElapsed + " milliseconds");

    }

    // Display the Cycle
    public static void drawCycle(Vertex start){
        ArrayList <Vertex> cycleVertices = new ArrayList<>();

        //Add the Vertices of the cycle to cycleVertices
        cycleVertices.add(start);
        getCycleVertices(start, start, cycleVertices);

        //Variable to store the Starting and ending Vertex of the cycle
        Vertex cycleVertex = null;

        //Create a graph using the cycleVertices
        Graph cycle = new Graph();
        for(Vertex v : cycleVertices){
            if(start.getData().equals(v.getData())){
                cycleVertex = cycle.addVertex(v.getData());
            }
            else {
                cycle.addVertex(v.getData());
            }
        }
        for(int i = 0; i < (cycle.vertices.size()) ; i++){
            if(cycle.vertices.size()-1 == i){
                cycle.addEdge(cycle.vertices.get(i), cycleVertex);
            }
            else {
                cycle.addEdge(cycle.vertices.get(i), cycle.vertices.get(i + 1));
            }
        }

        //Display the cycle graph
        System.out.println();
        cycle.print();
    }

    //Method to get the cycle Vertices
    public static void getCycleVertices(Vertex cycleVertex, Vertex start, ArrayList <Vertex> cycleVertices){

        //Start from the cycle starting and ending vertex and look for edge of that vertex
        for(Edge e: start.getEdges()){
            Vertex neighbor = e.getEnd();

            
            if (!neighbor.equals(cycleVertex)) {
                cycleVertices.add(neighbor);
                getCycleVertices(cycleVertex, neighbor, cycleVertices);
            }
            break;
        }
    }

    //Check if there are any cycles and display the cycle by passing starting and ending vertex of cycle
    public static boolean isCycle(ArrayList<Vertex>visitedVertices){
        ArrayList<Vertex> checkedVertex = new ArrayList<>();
        for (Vertex v:visitedVertices) {
            checkedVertex.add(v);
            for (Edge e:v.edges) {
                if (checkedVertex.contains(e.getEnd())){
                    drawCycle(v);
                    return true;
                }
            }
        }
        return false;
    }

    //Method to the depth first search
    public static void depthFirstSearch(Vertex start, ArrayList<Vertex>visitedVertices){

        for(Edge e: start.getEdges()){
            Vertex neighbor = e.getEnd();

            if(!visitedVertices.contains(neighbor)){
                visitedVertices.add(neighbor);
                depthFirstSearch(neighbor, visitedVertices);
            }
        }
    }

    //Method to check if the graph is an Acyclic or not
    public static boolean isAcyclic(Graph graph){

        ArrayList <Vertex> sinkVertices = new ArrayList<>();

        int size = graph.getVertices().size();

        //Loop over and check for a sink and remove until all the vertices are 0
        //if there are no sink in the graph then the graph is no an acyclic
        for (int i = 0 ; i < size; i++) {
            Vertex sink = graph.findSink();
            if(sink != null) {
                sinkVertices.add(sink);
                graph.removeVertex(sink);
            }
            else {
                return false;
            }
        }

        //print out the removed vertices if the graph is an acyclic
        for (int i = 0; i < sinkVertices.size(); i++) {
            if(i == 0){
                System.out.print("\nRemoved vertices : " + sinkVertices.get(i).getData());
            }else {
                System.out.print("," + sinkVertices.get(i).getData());
            }
        }
        System.out.println();
        return true;
    }

    //Method to add vertex to the graph
    public static void addVertexToGraph(Set<String> verticesString, Graph graph ){
        for (String d: verticesString) {
            graph.addVertex(d);
        }
    }

    //Method to add edges to the graph
    public static void addEdgesOfTheGraph(ArrayList<String []> edges, Graph graph){
        for (String [] e:edges) {

            Vertex start = null;
            Vertex end = null;

            for (Vertex v: graph.vertices) {
                if(v.getData().equalsIgnoreCase(e[0])){
                    start = v;
                }
                if(v.getData().equalsIgnoreCase(e[1])){
                    end = v;
                }
            }
            graph.addEdge(start, end);
        }
    }

    //Method to get the vertices from the edge array and store in a set
    public static Set<String>  addDataFromFile(ArrayList <String []> edges){

        Set<String> vertices = new LinkedHashSet<>();

        for (String[] edge : edges) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }
        return vertices;
    }

    //Read the file line by line and get the edges and store them in the edges Array List
    public static void readFile(ArrayList <String []> edges){

        //ArrayList <String []> edges = new ArrayList<>();
        try {
            File myObj = new File("test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] parts = data.split(" ");
                edges.add(parts);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
