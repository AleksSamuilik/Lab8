import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

public class FrequencyDictionary {
    private static final int LETTER_A = 97;
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

    private File file = new File("D:\\Intelij project\\Lab8\\src\\A tale two city.txt");


    private void addCountArray(long[] array, char letter) {
        int index = letter - LETTER_A;
        array[index]+=1;
    }


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        long[] countArray = new long[26];

        FrequencyDictionary frequencyDictionary = new FrequencyDictionary();
        FileReader reader = new FileReader(frequencyDictionary.file);
        for (int i = 0; i < frequencyDictionary.file.length(); i++) {
            char letter = (char) Character.toLowerCase(reader.read());
            if (97<=letter&&letter>=122) {
                frequencyDictionary.addCountArray(countArray, letter);
            }
        }
        frequencyDictionary.printMessage(frequencyDictionary.createArrayLetterNumber(countArray));
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("Program working time: " + elapsedTimeSec + " sec.");
    }

    private Object[][] createArrayLetterNumber(long[] countArray) {
        Object[][] finalArray = new Object[countArray.length][2];
        for (int i = 0; i < finalArray.length; i++) {
            finalArray[i][0] = Character.valueOf((char) (LETTER_A + i));
            finalArray[i][1] = countArray[i];
        }
        return finalArray;
    }

    private void arraysSort(Object[][] array) {
        Arrays.sort(array, new someComparator());
    }

    private class someComparator implements Comparator<Object[]> {
        public int compare(Object[] row1, Object[] row2) {
            long value1 = (long) row1[1];
            long value2 = (long) row2[1];
            char key1 = (char) row1[0];
            char key2 = (char) row2[0];
            if (value1 == value2 && key1 < key2) {
                return Character.compare(key1, key2);
            }
            return Long.compare(value2, value1);
        }
    }

    private void printMessage(Object[][] finalArray) {
        arraysSort(finalArray);
        for (int i = 0; i < finalArray.length; i++) {
            if ((long) finalArray[i][1] != 0) {
                System.out.println(finalArray[i][0] + " - " + finalArray[i][1]);
            }
        }
    }
}