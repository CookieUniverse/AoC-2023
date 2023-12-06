public class Range {
    long start;
    long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public Range overlap(Range r) {
        long overlapStart = Math.max(start, r.start);
        long overlapEnd = Math.min(end, r.end);
        if (overlapStart < overlapEnd) {
            return new Range(overlapStart, overlapEnd);
        }
        return null;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}