package ru.netology.graphs;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class VertexTest {

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
        System.out.println("Starting new nest");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete: " + (System.nanoTime() - testStartTime));
    }

    @Test
    @DisplayName("Сравнение с собой дает true")
    void equals_self_true() {
        Vertex<String> vertex1 = new Vertex<>("test1");

        boolean result = vertex1.equals(vertex1);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Сравнение с null дает false")
    void equals_null_false() {
        Vertex<String> vertex1 = new Vertex<>("test1");
        Vertex<String> vertex2 = null;

        boolean result = vertex1.equals(vertex2);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Сравнение вершин с одинаковым значением дает true")
    void equals_vertexWithSameValue_true() {
        Vertex<String> vertex1 = new Vertex<>("test1");
        Vertex<String> vertex2 = new Vertex<>("test1");

        boolean result = vertex1.equals(vertex2);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Сравнение вершин с разными значениями дает false")
    void equals_vertexWithOtherValue_false() {
        Vertex<String> vertex1 = new Vertex<>("test1");
        Vertex<String> vertex2 = new Vertex<>("test2");

        boolean result = vertex1.equals(vertex2);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("hashCode вершины совпадает с Objects.hash() от значения")
    void hashCode_equalsValuesHashCode_true() {
        String value = "test";
        Vertex<String> vertex = new Vertex<>(value);

        int hash = vertex.hashCode();

        assertThat(hash).isEqualTo(Objects.hash(value));
    }

    @Test
    @DisplayName("getAdjacent возвращает List")
    void getAdjacent_called_list() {
        Vertex<String> vertex = new Vertex<>("test");

        Object res = vertex.getAdjacent();

        assertThat(res).isNotNull();
        assertThat(res).isInstanceOf(List.class);
    }

}
