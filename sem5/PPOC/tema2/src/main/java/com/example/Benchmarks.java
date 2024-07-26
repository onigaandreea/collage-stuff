package com.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;
import org.openjdk.jmh.runner.options.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(1)
@State(Scope.Benchmark)
public class Benchmarks {
    private final static Collection<Order> orders = List.of(new Order(1, 8, 4),
            new Order(2, 3, 6), new Order(3, 17, 7));
    @State(Scope.Benchmark)
    public static class ArrayListState {
        public ArrayListBasedRepository<Order> arrayListBasedRepository = new ArrayListBasedRepository<>();
        public Collection<Order> ordersS;
        @Setup(Level.Invocation)
        public void doSetup() {
            arrayListBasedRepository.clear();
            arrayListBasedRepository.addAll(orders);
            ordersS = Arrays.asList(new Order(4, 19, 14), new Order(5, 10, 13),
                    new Order(6, 17, 7));
        }
    }
    @Benchmark
    public void arrayListAdd(ArrayListState state){
        state.arrayListBasedRepository.addAll(state.ordersS);
    }

    @Benchmark
    public void arrayListRemove(ArrayListState state) {
        Order order = new Order(1, 8, 4);
        state.arrayListBasedRepository.remove(order);
    }

    @Benchmark
    public void arrayListContains(ArrayListState state) {
        Order order = new Order(2, 3, 6);
        state.arrayListBasedRepository.contains(order);
    }

    @State(Scope.Benchmark)
    public static class HashSetState {
        public HashSetBasedRepository<Order> hashSetBasedRepository = new HashSetBasedRepository<>();
        public Collection<Order> ordersS;
        @Setup(Level.Invocation)
        public void doSetup() {
            hashSetBasedRepository.clear();
            hashSetBasedRepository.addAll(orders);
            ordersS = Arrays.asList(new Order(4, 20, 4), new Order(5, 15, 13),
                    new Order(6, 17, 13));
        }
    }
    @Benchmark
    public void hashSetAdd(HashSetState state){
        state.hashSetBasedRepository.addAll(state.ordersS);
    }

    @Benchmark
    public void hashSetRemove(HashSetState state) {
        Order order = new Order(1, 8, 4);
        state.hashSetBasedRepository.remove(order);
    }

    @Benchmark
    public void hashSetContains(HashSetState state) {
        Order order = new Order(2, 3, 6);
        state.hashSetBasedRepository.contains(order);
    }

    @State(Scope.Benchmark)
    public static class TreeSetState {
        public TreeSetBasedRepository<Order> treeSetBasedRepository = new TreeSetBasedRepository<>();
        public Collection<Order> ordersS;
        @Setup(Level.Invocation)
        public void doSetup() {
            treeSetBasedRepository.clear();
            treeSetBasedRepository.addAll(orders);
            ordersS = Arrays.asList(new Order(4, 13, 14), new Order(5, 10, 23),
                    new Order(6, 27, 17));
        }
    }
    @Benchmark
    public void treeSetAdd(TreeSetState state){
        state.treeSetBasedRepository.addAll(state.ordersS);
    }

    @Benchmark
    public void treeSetRemove(TreeSetState state) {
        Order order = new Order(1, 8, 4);
        state.treeSetBasedRepository.remove(order);
    }

    @Benchmark
    public void treeSetContains(TreeSetState state) {
        Order order = new Order(2, 3, 6);
        state.treeSetBasedRepository.contains(order);
    }

    @State(Scope.Benchmark)
    public static class ConcurrentHashMapState {
        public ConcurrentHashMapBasedRepository<Order> concurrentHashMapBasedRepository = new ConcurrentHashMapBasedRepository<>();
        public Collection<Order> ordersS;
        @Setup(Level.Invocation)
        public void doSetup() {
            concurrentHashMapBasedRepository.clear();
            for (Order o: orders) {
                concurrentHashMapBasedRepository.add(o);
            }
            ordersS = Arrays.asList(new Order(4, 13, 14), new Order(5, 10, 23),
                    new Order(6, 27, 17));
        }
    }
    @Benchmark
    public void concurrentHashMapAdd(ConcurrentHashMapState state){
        for (Order o: state.ordersS) {
            state.concurrentHashMapBasedRepository.add(o);
        }
    }

    @Benchmark
    public void concurrentHashMapRemove(ConcurrentHashMapState state) {
        Order order = new Order(1, 8, 4);
        state.concurrentHashMapBasedRepository.remove(order);
    }

    @Benchmark
    public void concurrentHashMapContains(ConcurrentHashMapState state) {
        Order order = new Order(2, 3, 6);
        state.concurrentHashMapBasedRepository.contains(order);
    }

    @State(Scope.Benchmark)
    public static class EclipseState {
        public EclipseBasedRepository<Order> eclipsedBasedRepository = new EclipseBasedRepository<>();
        public Collection<Order> ordersS;
        @Setup(Level.Invocation)
        public void doSetup() {
            eclipsedBasedRepository.clear();
            eclipsedBasedRepository.addAll(orders);
            ordersS = Arrays.asList(new Order(4, 13, 14), new Order(5, 10, 23),
                    new Order(6, 27, 17));
        }
    }
    @Benchmark
    public void ElipseAdd(EclipseState state){
        state.eclipsedBasedRepository.addAll(state.ordersS);
    }

    @Benchmark
    public void eclipseRemove(EclipseState state) {
        Order order = new Order(1, 8, 4);
        state.eclipsedBasedRepository.remove(order);
    }

    @Benchmark
    public void eclipseContains(EclipseState state) {
        Order order = new Order(2, 3, 6);
        state.eclipsedBasedRepository.contains(order);
    }

    @State(Scope.Benchmark)
    public static class KolobokeState {
        public KolobokeBasedRepository<Order> kolobokeBasedRepository = new KolobokeBasedRepository<>();
        public Collection<Order> ordersS;
        @Setup(Level.Invocation)
        public void doSetup() {
            kolobokeBasedRepository.clear();
            kolobokeBasedRepository.addAll(orders);
            ordersS = Arrays.asList(new Order(4, 13, 14), new Order(5, 10, 23),
                    new Order(6, 27, 17));
        }
    }
    @Benchmark
    public void KolobokeAdd(KolobokeState state){
        state.kolobokeBasedRepository.addAll(state.ordersS);
    }

    @Benchmark
    public void KolobokeRemove(KolobokeState state) {
        Order order = new Order(1, 8, 4);
        state.kolobokeBasedRepository.remove(order);
    }

    @Benchmark
    public void KolobokeContains(KolobokeState state) {
        Order order = new Order(2, 3, 6);
        state.kolobokeBasedRepository.contains(order);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Benchmarks.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();
    }
}

