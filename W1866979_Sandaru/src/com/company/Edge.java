//R.M.S.J Bandara
//w1866979
//20200649
package com.company;

public class Edge {

    //the variable source and destination represent the vertices
    Vertex start, end;
    //creating a constructor of the class Edge
    Edge(Vertex start, Vertex end)
    {
        this.start = start;
        this.end = end;
    }

    public Vertex getStart(){
        return this.start;
    }

    public Vertex getEnd() {
        return end;
    }
}
