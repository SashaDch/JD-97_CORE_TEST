package ru.netology.graphs;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class GraphTest {
    private Graph<String> graph;
    private Vertex<String> vertex1;
    private Vertex<String> vertex2;
    private Vertex<String> vertex3;
    private static long suiteStartTime;
    private long testStartTime;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running VertexTest");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("VertexTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    void initTest() {
        graph = new Graph<>();
        vertex1 = graph.createVertex("test1");
        vertex2 = graph.createVertex("test2");
        vertex3 = graph.createVertex("test3");
        System.out.println("Starting new nest");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete: " + (System.nanoTime() - testStartTime));
    }

    @Test
    @DisplayName("Добавление null вершины")
    void createVertex_null_raiseNPE() {
        String value = null;

        ThrowableAssert.ThrowingCallable nullVertexCreator = (() -> graph.createVertex(value));

        assertThatThrownBy(nullVertexCreator).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Добавление вершины")
    void createVertex_value_equalsNewVertex() {
        String value = "test value";
        Vertex<String> vertex4 = new Vertex<>(value);

        Vertex<String> vertex5 = graph.createVertex(value);

        assertThat(vertex5).isEqualTo(vertex4);
    }

    @Test
    @DisplayName("Добавление ребра с null вершиной")
    void createEdge_nullVertex_raiseNPE() {
        Vertex<String> vertex = null;

        ThrowableAssert.ThrowingCallable nullEdgeCreator1 = (() -> graph.createEdge(vertex, vertex1));
        ThrowableAssert.ThrowingCallable nullEdgeCreator2 = (() -> graph.createEdge(vertex1, vertex));

        assertThatThrownBy(nullEdgeCreator1).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(nullEdgeCreator2).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Добавление ребра с вершиной, не входящей в граф")
    void createEdge_externalVertex_raiseException() {
        Vertex<String> vertex = new Vertex<>("test");

        ThrowableAssert.ThrowingCallable externalEdgeCreator1 = (() -> graph.createEdge(vertex, vertex1));
        ThrowableAssert.ThrowingCallable externalEdgeCreator2 = (() -> graph.createEdge(vertex1, vertex));

        assertThatThrownBy(externalEdgeCreator1).isInstanceOf(Exception.class);
        assertThatThrownBy(externalEdgeCreator2).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Добавление ребра с обычными параметрами")
    void createEdge_notConnectedVertexes_isConnectedAfter() {
        Boolean resBefore = graph.isConnected(vertex1, vertex2);

        graph.createEdge(vertex1, vertex2);
        Boolean resAfter = graph.isConnected(vertex1, vertex2);

        assertThat(resBefore).isFalse();
        assertThat(resAfter).isTrue();
    }

    @Test
    @DisplayName("Проверка связности без ребер")
    void isConnected_noEdges_false() {
        Boolean res = graph.isConnected(vertex1, vertex2);

        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Проверка достижимости с ребрами")
    void isConnected_withEdges_true() {
        graph.createEdge(vertex1, vertex2);
        graph.createEdge(vertex3, vertex2);

        Boolean res1 = graph.isConnected(vertex1, vertex2);
        Boolean res2 = graph.isConnected(vertex1, vertex3);

        assertThat(res1).isTrue();
        assertThat(res2).isTrue();
    }
}
