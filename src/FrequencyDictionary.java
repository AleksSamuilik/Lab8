import java.io.File;
import java.io.FileReader;
import java.util.*;

public class FrequencyDictionary {
    /*
    Это по старой схеме программы.
    в первый раз 0.225 сек , во второй 0.119 сек, в третий 0.111 сек, в четвертый 0.107 сек, в пятый 0.104 сек.
    Дальнейшие тесты показали что время выполнения находятся примерно в интервале 0.095-0.145 cек.
    ***********************************************************************************************
    После изменения способа сортировки массива через Arrays.sort скорость упала.
    Тесты показали что время выполнения находятся примерно в интервале 0.12-0.15 cек.
    ***********************************************************************************************
    После оптимизации время выполнения стало 0.003-0.008 сек. Сам в шоке)))
*/
    //FreqDictFile
    //A tale two city
    private File file = new File("D:\\Intelij project\\Lab8\\src\\A tale two city.txt");

    private void addCountArray(int[] array, char letter) {
        int index = letter - 'a';
        array[index]++;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int[] countArray = new int[26];

        FrequencyDictionary frequencyDictionary = new FrequencyDictionary();
        FileReader reader = new FileReader(frequencyDictionary.file);
        for (int i = 0; i < frequencyDictionary.file.length(); i++) {
            char letter = (char) Character.toLowerCase(reader.read());
            if ('a' <= letter && letter <= 'z') {
                frequencyDictionary.addCountArray(countArray, letter);
            }
        }
        SortedSet<SymbolFrequency> sortedSet = frequencyDictionary.createMap(countArray);
        for (SymbolFrequency symbolFrequency : sortedSet) {
            System.out.println(symbolFrequency.value + " - " + symbolFrequency.key);
        }
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("Program working time: " + elapsedTimeSec + " sec.");
    }

    public SortedSet createMap(int[] array) {
        SortedSet<SymbolFrequency> sortedSet = new TreeSet<SymbolFrequency>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                continue;
            } else {
                sortedSet.add(new SymbolFrequency(array[i], (char) ('a' + i)));
            }
        }
        return sortedSet;
    }


    private static class SymbolFrequency implements Comparable<SymbolFrequency> {
        public char value;
        public int key;

        public SymbolFrequency(int key, char value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(SymbolFrequency o) {
            return key == o.key ? value - o.value : o.key - key;
        }

        public String toString() {
            return this.key + " - " + this.value;
        }
    }
}
