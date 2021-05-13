package util;

public class PermutationFactory<T> {
    private T[] elements;
    private int[] indices;
    private int len;
    private int pivot;
    private long numGenerated = 0;
    private boolean morePermutationsExist = true;


    public PermutationFactory(T[] elements) {
        this.elements = elements.clone();
        this.len = elements.length;
        this.indices = new int[len];
    }

    public boolean hasNext() {
        return morePermutationsExist;
    }

    public T[] next() {
        if(!morePermutationsExist){
            throw new RuntimeException("Attempted to retrieve more permutations than exist for the input");
        }
        T[] out = elements.clone();
        numGenerated++;
        morePermutationsExist = produceNext();
        return out;
    }

    private boolean produceNext() {
        while (pivot < len) {
            if (indices[pivot] < pivot) {
                swap(pivot % 2 == 0 ? 0 : indices[pivot], pivot);
                indices[pivot]++;
                pivot = 0;
                return true;
            }else{
                indices[pivot]=0;
                pivot++;
            }
        }
        return false;
    }

    public long getPermutationCount() {
        return numGenerated;
    }

    private void swap(int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

}
