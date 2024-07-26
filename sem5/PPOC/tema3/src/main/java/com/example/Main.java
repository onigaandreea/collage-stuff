package com.example;

import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.api.collection.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;
import org.openjdk.jmh.runner.options.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(1)
@State(Scope.Benchmark)
public class Main {

    @State(Scope.Benchmark)
    public static class ObjectState {
        public Collection<Double> elems = new ArrayList<>();

        public Collection<Double> addElemOrd(){
            Collection<Double> elems = new ArrayList<>();
            for(int i = 1; i<=50000000; i++){
                elems.add((double) i);
            }
            return elems;
        }

        public Collection<Double> addElemRand(){
            Collection<Double> elems = new ArrayList<>();
            for(int i = 1; i<=50000000; i++){
                double elem = Math.random() *100;
                elems.add(elem);
            }
            return elems;
        }

        @Setup(Level.Invocation)
        public void doSetup() {
            //elems = addElemOrd();
            elems = addElemRand();
        }

    }

    @Benchmark
    public void objectSum(ObjectState state){
        state.elems.stream().mapToDouble(Double::doubleValue).sum();
    }

    @Benchmark
    public void objectAvg(ObjectState state){
        state.elems.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }


    @Benchmark
    public void objectMaxVal(ObjectState state){
        int topPercent = (int) (state.elems.size() * 0.1);
        state.elems.stream().sorted(Comparator.reverseOrder())
                .limit(topPercent)
                .collect(Collectors.toList());
    }

    @State(Scope.Benchmark)
    public static class PrimitiveState {
        double[] elems = new double[50000002];

        public double[] addElemOrd(){
            double[] elems = new double[50000002];
            for(int i = 1; i<=50000000; i++){
                elems[i]=i;
            }
            return elems;
        }

        public double[] addElemRand(){
            double[] elems = new double[50000002];
            for(int i = 1; i<=50000000; i++){
                double elem = Math.random() *100;
                elems[i] = elem;
            }
            return elems;
        }

        @Setup(Level.Invocation)
        public void doSetup() {
            //elems = addElemOrd();
            elems = addElemRand();
        }

    }

    @Benchmark
    public void primitiveSum(PrimitiveState state){
        Arrays.stream(state.elems).sum();
    }

    @Benchmark
    public void primitiveAvg(PrimitiveState state){
        Arrays.stream(state.elems).average();
    }


    @Benchmark
    public void primitiveMaxVal(PrimitiveState state){
        int topPercent = (int) (state.elems.length * 0.1);
        Arrays.stream(state.elems).boxed().sorted(Comparator.reverseOrder()).limit(topPercent);

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Main.class.getSimpleName())
                .jvmArgs("-Xmx8G")
                .forks(1)
                .build();

        new Runner(options).run();
    }

}