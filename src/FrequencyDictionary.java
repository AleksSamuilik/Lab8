import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrequencyDictionary {
    private File file = new File("D:\\Intelij project\\Lab8\\src\\FreqDictFile.txt");

    public static void main(String[] args) throws Exception {
        FrequencyDictionary frequencyDictionary = new FrequencyDictionary();
        String stringRead = frequencyDictionary.openAndReadFile(frequencyDictionary.file);
        stringRead = frequencyDictionary.deletesNonStandardCharacters(stringRead);
        String stringWithoutDuplicates = frequencyDictionary.removeDuplicates(stringRead);
        String[][] map = frequencyDictionary.createArray(stringWithoutDuplicates);
        map = frequencyDictionary.countsFrequencyDuplicate(map, stringRead);
        frequencyDictionary.bubbleSort(map);
        for (int i = 0; i < map.length; i++) {
            System.out.println(map[i][0] + " - " + map[i][1]);
        }
    }

    public String removeDuplicates(String input) {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            if (!result.contains(String.valueOf(input.charAt(i)))) {
                result += String.valueOf(input.charAt(i));
            }
        }
        return result;
    }

    public String[][] createArray(String string) {
        String[][] map = new String[string.length()][2];
        for (int i = 0; i < string.length(); i++) {
            map[i][0] = String.valueOf(string.charAt(i));
            map[i][1] = "";
        }
        return map;
    }

    public String[][] countsFrequencyDuplicate(String[][] array, String string) {
        for (int i = 0; i < array.length; i++) {
            char letter = array[i][0].charAt(0);
            int counts = (int) string.chars().filter(ch -> ch == letter).count();
            array[i][1] = String.valueOf(counts);
        }
        return array;
    }

    public void bubbleSort(String[][] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            boolean valid = true;
            for (int j = 0; j < i; j++) {
                if (checksNumbers (arr [j][1],arr[j+1][1]) < 0) {
                    valid = false;
                    String tmp = arr[j][1];
                    String letter = arr[j][0];
                    arr[j][1] = arr[j + 1][1];
                    arr[j][0] = arr[j + 1][0];
                    arr[j + 1][1] = tmp;
                    arr[j + 1][0] = letter;
                } else if (checksNumbers (arr [j][1],arr[j+1][1]) ==0 && arr[j][0].compareTo(arr[j + 1][0]) > 0) {
                    valid = false;
                    String letter = arr[j][0];
                    arr[j][0] = arr[j + 1][0];
                    arr[j + 1][0] = letter;
                }
            }
            if (valid) {
                break;
            }
        }
    }

    public static int checksNumbers(String firstNumber, String secondNumber ){
int firstDigit = Integer.parseInt(firstNumber);
int secondDigit = Integer.parseInt(secondNumber);
if (firstDigit>secondDigit){
    return 1;
} else if (firstDigit<secondDigit){
    return -1;
} else {
    return 0;
}
    }

    public String deletesNonStandardCharacters(String stringRead) {
        return stringRead.replaceAll("[^a-z]", "");
    }

    public String openAndReadFile(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while (fileScanner.hasNextLine()) {
            String string = fileScanner.nextLine();
            string = string.toLowerCase();
            builder.insert(builder.length(), string);
        }
        return builder.toString();
    }
}