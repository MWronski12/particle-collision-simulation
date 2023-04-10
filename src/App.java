import java.awt.Color;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class App {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(600, 600);
        StdDraw.enableDoubleBuffering();
        Particle[] particles;

        // create n random particles
        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            particles = new Particle[n];
            for (int i = 0; i < n; i++)
                particles[i] = new Particle();
        }

        // or read from standard input
        else {
            int n = StdIn.readInt();
            particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                double rx = StdIn.readDouble();
                double ry = StdIn.readDouble();
                double vx = StdIn.readDouble();
                double vy = StdIn.readDouble();
                double radius = StdIn.readDouble();
                double mass = StdIn.readDouble();
                int r = StdIn.readInt();
                int g = StdIn.readInt();
                int b = StdIn.readInt();
                Color color = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        }

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate();
    }
}
