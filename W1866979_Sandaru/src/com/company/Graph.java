//R.M.S.J Bandara
//w1866979
//20200649

package com.company;

import java.util.ArrayList;

public class Graph {
    // -- Store the vertices of the Graph --
    ArrayList<Vertex> vertices;

    // -- Constructor of the Graph class --
    public Graph(){
        this.vertices = new ArrayList<Vertex>();
    }

    public Vertex addVertex(String data){
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2){
        vertex1.addEdge(vertex2);
    }

    public void removeEdge(Vertex vertex1, Vertex vertex2){
        vertex1.removeEdge(vertex2);
    }

    public void removeVertex(Vertex vertex){
        for (Vertex v: vertices) {
            v.removeEdge(vertex);
        }
        this.vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices(){
        return this.vertices;
    }

    public Vertex getVertexByValue(String value){
        for(Vertex v: this.vertices){
            if(v.getData().equals(value)){
                return v;
            }
        }
        return null;
    }

    public void print(){
        for(Vertex v: this.vertices){
            v.print();
        }
    }

    public Vertex findSink(){
        for (Vertex v: this.vertices) {
            if (v.edges.size() == 0){
                return v;
            }
        }
        return null;
    }
}
