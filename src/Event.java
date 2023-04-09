public class Event implements Comparable<Event> {
    public final double time; // time that event is scheduled to occur
    public Particle a, b; // particles involved in event, possibly null
    private int countA, countB; // collision counts at event creation

    public Event(double t, Particle A, Particle B) {
        this.time = t;
        this.a = A;
        this.b = B;
        if (A != null)
            this.countA = A.count();
        else
            this.countA = -1;
        if (B != null)
            this.countB = B.count();
        else
            this.countB = -1;
    }

    public int compareTo(Event that) {
        return Double.compare(this.time, that.time);
    }

    public boolean isValid() {
        if (a != null && a.count() != countA)
            return false;
        if (b != null && b.count() != countB)
            return false;
        return true;
    }
}
