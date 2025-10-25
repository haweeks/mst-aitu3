package edu.example.mst;
public class Counter {
    public long comparisons = 0;
    public long unions = 0;
    public void addComparisons(long n){ comparisons += n; }
    public void addUnions(long n){ unions += n; }
    public long total() { return comparisons + unions; }
}
