import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

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

    private File file = new File("D:\\Intelij project\\Lab8\\src\\FreqDictFile.txt");


    public boolean checksArray(String[][] array, char letter) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0].compareTo(String.valueOf(letter)) == 0) {
                return true;
            }
        }
        return false;
    }

    public void addsCountArray(String[][] array, char letter) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0].compareTo(String.valueOf(letter)) == 0) {
                int count = Integer.parseInt(array[i][1]) + 1;
                array[i][1] = Integer.toString(count);
            }
        }
    }

    public String[][] addsNewLetter(String[][] array, char letter) {
        String[][] arrs = new String[array.length + 1][2];
        for (int i = 0; i < array.length; i++) {
            arrs[i][0] = array[i][0];
            arrs[i][1] = array[i][1];
        }
        arrs[array.length][0] = String.valueOf(letter);
        arrs[array.length][1] = String.valueOf(1);
        return arrs;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String[][] array = new String[0][2];

        FrequencyDictionary frequencyDictionary = new FrequencyDictionary();
        FileReader reader = new FileReader(frequencyDictionary.file);
        for (int i = 0; i < frequencyDictionary.file.length(); i++) {
            char letter = (char) Character.toLowerCase(reader.read());
            if (Character.isLetter(letter)) {
                if (frequencyDictionary.checksArray(array, letter)) {
                    frequencyDictionary.addsCountArray(array, letter);
                } else {
                    array = frequencyDictionary.addsNewLetter(array, letter);
                }
            }
        }

        frequencyDictionary.arraysSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i][0] + " - " + array[i][1]);
        }

        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("Program working time: " + elapsedTimeSec + " sec.");
    }

    private void arraysSort(String[][] array) {
        Arrays.sort(array, new someComparator());
    }

    public class someComparator implements Comparator<String[]> {
        public int compare(String[] row1, String[] row2) {
            int value1 = Integer.parseInt(row1[1]);
            int value2 = Integer.parseInt(row2[1]);
            String key1 = row1[0];
            String key2 = row2[0];
            if (value1 == value2 && key1.compareTo(key2) < 0) {
                return key1.compareTo(key2);
            }
            return Integer.compare(value2, value1);
        }
    }
}