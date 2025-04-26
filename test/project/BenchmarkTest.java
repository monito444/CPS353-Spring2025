package project;

public class BenchmarkTest {

    public static void main(String[] args) {
        ComputeEngine original = new ComputeEngineImp();
        //ComputeEngine faster = new ComputeEngineImpImproved();

        int testValue = 20;

        long startOriginal = System.nanoTime();
        original.compute(testValue);
        long endOriginal = System.nanoTime();
        long durationOriginal = endOriginal - startOriginal;

        long startFaster = System.nanoTime();
        //faster.compute(testValue);
        long endFaster = System.nanoTime();
        long durationFaster = endFaster - startFaster;

        System.out.println("Original time: " + durationOriginal + " ns");
        System.out.println("Faster time: " + durationFaster + " ns");

        double improvement = ((double)(durationOriginal - durationFaster) / durationOriginal) * 100;

        if(improvement >= 10.0) {
            System.out.println("Benchmark passed! Faster version is at least 10% faster.");
        } else {
            System.out.println("Benchmark failed. Faster version is not fast enough.");
        }
    }
}
