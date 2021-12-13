# OOP_Ex2

this project is about directed weighted graph.

classes: 
  * GeoLocation: represents a point in a plane <x,y,z>
  * NodeData: represents a vertex in a graph and contains it's GeoLocation, key and weight.
  * EdgeData: represents an edge in a graph and contains source, destination and the weight of the edge.
  * DirectedWeightedGraph: represents the whole graph. we implemented it using a HashMap<Integer,NodeData> of nodes and a HashMap<Integer, HashMap<Integer, EdgeData>> of edges.     The keys of the nodes HashMap are the id's nodes and the values are the nodes according to their id. 
    The keys of the edges HashMap are the sources (on which vertex the edge starts) and the values contains HashMaps in which the keys are the destinations (on which vertex the     edge ends) and the values are the Edges.
  * DirectedWeightedGraphAlgorithms:
    Functions explanation: 
    - copy: gets a graph and make a deep copy of it. 
    - isConnected: in this function we represented our graph as List<List<Integer>> which is an adjacency list, then we iterated through the list and used DFS by                     recursion to check whether we were able to visit each vertex of the graph.
    - shortestPathDist: in this function we used Floyd Warshall Algorithm which gives us an adjacency matrix, then the answer is matrix[src][dest].
    - shortestPath: in this function we used Dijkstra's Algorithm with the help of another matrix so we can use her to save our path in the list and then return it. 
    - center: in this function we also used Floyd Warshall Algorithm to get our matrix of shortest paths from each vertex to each vertex, then we saved the shortest distance of       each vertex in a temp array and returned the max value in this array.
    - tsp: in this function we also used Floyd Warshall Algorithm.
    - save: saves our graph object into a json file.
    - load: reads a json file and loads it as graph.
 
 Algorithm run time tests:
 
 *Too much time
 
 G1:Center-2ms, Isconnected-1ms, ShoretstPath-1ms, ShoretstPathDist-1ms, TSP-1ms.
 
 G2: Center-3ms, Isconnected-3ms, ShoretstPath-9ms, ShoretstPathDist-5ms, TSP-16ms.
 
 G3: Center-5ms, Isconnected-4ms, ShoretstPath-8ms, ShoretstPathDist-6ms, TSP-7ms.
 
 1,000 nodes: Center-3447ms, Isconnected-12ms, ShoretstPath-3769ms, ShoretstPathDist-3350ms, TSP-4128ms.
 
 10,000 nodes: Center- *, Isconnected-23550ms, ShoretstPath-*, ShoretstPathDist-*, TSP-*.
 
 100,000 nodes: Center- *, Isconnected-*, ShoretstPath-*, ShoretstPathDist-*, TSP-*.
 
 1,000,000 nodes: Center- *, Isconnected-*, ShoretstPath-*, ShoretstPathDist-*, TSP-*.
 
