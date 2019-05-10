import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
//FreqDictFile
    private File file = new File("D:\\Intelij project\\Lab8\\src\\A tale two city.txt");


    private void addCountArray(int[] array, char letter) {
        int index = letter - LETTER_A;
        array[index]++;
    }


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int[] countArray = new int[26];

        FrequencyDictionary frequencyDictionary = new FrequencyDictionary();
        FileReader reader = new FileReader(frequencyDictionary.file);
        for (int i = 0; i < frequencyDictionary.file.length(); i++) {
            char letter = (char) Character.toLowerCase(reader.read());
            if (LETTER_A <= letter && letter <= 122) {
                frequencyDictionary.addCountArray(countArray, letter);
            }
        }
        frequencyDictionary.printMessage(frequencyDictionary.createMap(countArray));
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("Program working time: " + elapsedTimeSec + " sec.");
    }

    public List createMap(int[] array) {
        List<SymbolFrequency> list = new ArrayList<>();
        HashMap<Character, Integer> h = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                continue;
            } else {
                h.put((char) (LETTER_A + i), array[i]);
            }
        }

        for (Entry<Character, Integer> value : h.entrySet()) {
            SymbolFrequency a = new SymbolFrequency(value.getKey(),
                    value.getValue());
            list.add(a);
        }

        Collections.sort(list);
        return list;
    }

    private void printMessage(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}