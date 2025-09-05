package ru.netology.graphs;

import java.util.*;

public class DAG<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> from, Vertex<T> to) {
        from.getAdjacent().add(to);
    }

    public int path(Vertex<T> from, Vertex<T> to) {
        Map<Vertex<T>, Integer> paths = new HashMap<>();
        paths.put(from, 0);

        Queue<Vertex<T>> queue = new ArrayDeque<>();
        Set<Vertex<T>> added = new HashSet<>();
        queue.add(from);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll();

            if (v.equals(to)) {
                return paths.get(v);
            }

            for (Vertex v2 : v.getAdjacent()) {
                if (!added.contains(v2)) {
                    added.add(v2);
                    paths.put(v2, paths.get(v) + 1);
                    queue.add(v2);
                }
            }
        }

        return -1;
    }

}
