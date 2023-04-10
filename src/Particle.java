import java.awt.Color;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Particle {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius
    private final double mass; // mass
    private Color color; // color
    private int count; // number of collisions

    public Particle() {
        rx = StdRandom.uniformDouble();
        ry = StdRandom.uniformDouble();
        vx = StdRandom.uniformDouble(-0.025, 0.025);
        vy = StdRandom.uniformDouble(-0.025, 0.025);
        radius = 0.01;
        mass = 0.5;
        color = Color.BLACK;
        this.count = 0;
    }

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
        this.color = color;
        this.count = 0;
    }

    public void move(double dt) {
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public int count() {
        return count;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    public double timeToHit(Particle that) {
        if (this == that)
            return Double.POSITIVE_INFINITY;

        // relative velocity
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0)
            return Double.POSITIVE_INFINITY;

        // relative speed
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        if (dvdv == 0)
            return Double.POSITIVE_INFINITY;

        // distance between particle centers at collision
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);

        if (d < 0)
            return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitHorizontalWall() {
        if (vy > 0)
            return (1.0 - ry - radius) / vy;
        else if (vy < 0)
            return (radius - ry) / vy;
        else
            return Double.POSITIVE_INFINITY;
    }

    public double timeToHitVerticalWall() {
        if (vx > 0)
            return (1.0 - rx - radius) / vx;
        else if (vx < 0)
            return (radius - rx) / vx;
        else
            return Double.POSITIVE_INFINITY;
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy; // dv dot dr
        double dist = this.radius + that.radius; // distance between particle centers at collision

        // magnitude of normal force
        double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

        // normal force, and in x and y directions
        double fx = magnitude * dx / dist;
        double fy = magnitude * dy / dist;

        // update velocities according to normal force
        this.vx += fx / this.mass;
        this.vy += fy / this.mass;
        that.vx -= fx / that.mass;
        that.vy -= fy / that.mass;

        // update collision counts
        this.count++;
        that.count++;
    }

    public void bounceOffHorizontalWall() {
        this.vy = -vy;
        this.count++;
    }

    public void bounceOffVerticalWall() {
        this.vx = -vx;
        this.count++;
    }

}
