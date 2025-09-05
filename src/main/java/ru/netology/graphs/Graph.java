package ru.netology.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> a, Vertex<T> b) {
        if (!a.getAdjacent().contains(b)) {
            a.getAdjacent().add(b);
        }
        if (!b.getAdjacent().contains(a)) {
            b.getAdjacent().add(a);
        }
    }

    public boolean isConnected(Vertex<T> a, Vertex<T> b) {
        return dfsFind(a, b, new HashSet<>()); // рекурсивный обход в глубину
    }

    private boolean dfsFind(Vertex<T> v, Vertex<T> target, Set<Vertex<T>> visited) {
        if (v.equals(target)) {
            return true;
        }
        visited.add(v);

        for (Vertex i : v.getAdjacent()) {
            if (!visited.contains(i) && dfsFind(i, target, visited)) {
                return true;
            }
        }
        return false;
    }

}