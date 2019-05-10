public class SymbolFrequency implements Comparable<SymbolFrequency> {
    public int value;
    public char key;

    public SymbolFrequency(char key, int value) {
        this.value = value;
        this.key = key;
    }

    @Override
    public int compareTo(SymbolFrequency arg0) {
        return this.value < arg0.value ? 1 : this.value > arg0.value ? -1
                : 0;
    }

    public String toString() {
        return this.key + " - " + this.value;
    }

}