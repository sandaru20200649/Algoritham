//R.M.S.J Bandara
//w1866979
//20200649

package com.company;

import java.util.ArrayList;

public class Vertex {
    //Store the data of the vertex
    String data;
    //Store the edges that start from the vertex
    ArrayList<Edge> edges;

    //Constructor for the vertex class
    Vertex(String data) {
        this.data = data;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Vertex endVertex){
        this.edges.add(new Edge(this, endVertex));
    }

    public void removeEdge(Vertex endVertex){
        this.edges.removeIf(edge -> edge.getEnd().equals(endVertex));
    }

    public String getData(){
        return this.data;
    }

    public ArrayList<Edge> getEdges(){
        return this.edges;
    }

    //Print out the Adjacency list of the vertex --
    public void print(){

        String message = "";
        for(int i = 0; i < this.edges.size(); i++ ){
            if(i == 0){
                message += this.edges.get(i).getStart().data + " --> ";
            }
            message += this.edges.get(i).getEnd().data;

            if (i != this.edges.size() - 1){
                message += ", ";
            }
        }
        System.out.println(message);
    }
}
