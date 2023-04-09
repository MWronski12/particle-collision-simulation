import edu.princeton.cs.algs4.StdDraw;

public class App {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(600, 600);
        StdDraw.enableDoubleBuffering();

        int N = Integer.parseInt(args[0]);
        Particle[] particles = new Particle[N];
        for (int i = 0; i < N; i++) {
            particles[i] = new Particle();
        }

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate();
    }
}
