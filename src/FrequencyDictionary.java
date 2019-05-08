import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Arrays.sort;

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

    private File file = new File("D:\\Intelij project\\Lab8\\src\\FreqDictFile.txt");


    public void addCountArray(int[] array, char letter) {
        int index = letter - LETTER_A;
        array[index]++;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int[] array = new int[26];

        FrequencyDictionary frequencyDictionary = new FrequencyDictionary();
        FileReader reader = new FileReader(frequencyDictionary.file);
        for (int i = 0; i < frequencyDictionary.file.length(); i++) {
            char letter = (char) Character.toLowerCase(reader.read());
            if (Character.isLetter(letter)) {
                frequencyDictionary.addCountArray(array, letter);
            }
        }
        frequencyDictionary.printMessage(array);
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("Program working time: " + elapsedTimeSec + " sec.");
    }

    public int getIndex(int number, int[] array, int count) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return i + count;
            }
        }
        return 0;
    }

    private void printMessage(int[] array) {
        int[] sortArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortArray);
        int count = 0;
        for (int j = sortArray.length - 1; j > 0; j--) {
            if (sortArray[j] != 0) {
                int index = getIndex(sortArray[j], array, count);
                count++;
                char letter = Character.valueOf((char) (LETTER_A + index));
                System.out.println(letter + " - " + sortArray[j]);
            } else {
                count=0;
            }
        }
               /* System.out.println(letter + " - " + array[j]);
                System.out.println(sortArray[j]);*/
    }
}