import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KruskalAlgorithm {
    static class Edge {
        int source, destination, weight = 0;

        Edge(int source, int destination, int weight) {
            this.destination = destination;
            this.source = source;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // List<Edge> edges = new ArrayList<>();
        System.out.println("Enter number of Edges");
        int numberOfEdges = sc.nextInt();
        System.out.println("Enter number of vertices");
        int numberOfVertices = sc.nextInt();
        PriorityQueue<Edge> edges = new PriorityQueue<>(new Comparator<Edge>() {
            public int compare(Edge e1, Edge e2) {
                return e1.weight - e2.weight;
            }
        });
        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println("Enter data for edge: ");
            System.out.println("Enter source:");
            int source = sc.nextInt();
            System.out.println("Enter destination:");
            int destination = sc.nextInt();
            System.out.println("Enter weight:");
            int weight = sc.nextInt();
            Edge edge = new Edge(source, destination, weight);
            edges.add(edge);
        }
        edges.forEach(edge -> System.out.println(edge));
        int count = 0;
        List<Integer> parent = new ArrayList<>();
        List<Edge> mst = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            parent.add(i);
        }
        for (Edge edge : edges) {
            if (count != numberOfVertices - 1) {
                if (checkParent(parent, edge.source) != checkParent(parent, edge.destination)) {
                    mst.add(edge);
                    parent.set(edge.source, edge.destination);
                    count++;
                }
            }
        }
        for (Edge edge : mst) {
            if (edge.source < edge.destination)
                System.out.println("Source -> " + edge.source + " Destination -> " + edge.destination + " Weight -> "
                        + edge.weight);
            else
                System.out.println("Source -> " + edge.destination + " Destination -> " + edge.source + " Weight -> "
                        + edge.weight);
        }
        sc.close();
    }

    static int checkParent(List<Integer> parent, int v) {
        if (parent.get(v) == v) {
            return v;
        } else
            return checkParent(parent, parent.get(v));
    }
}
