import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        String[] TextFromFile = ReadFile();
        String LongWord = FindingLongWord(TextFromFile);
        HashMap<String, Integer> CountWords = ReturnCountWords(TextFromFile);
        ArrayList<String> PopularProducts = FindingTheMostFrequentProducts(TextFromFile, CountWords);

        System.out.println("\nКоличество слов в файле: " + TextFromFile.length +
                "\nСамое длинное слово: " + LongWord +
                "\nСколько раз встречаются слова: " + CountWords +
                "\nСамые популярные продукты на пикнике: " + PopularProducts);

    }


    // МЕТОД ПО ПРОЧТЕНИЮ ДАННЫХ ИЗ ФАЙЛА И ВОЗВРАЩАЕТ МАССИВ СТРОК
    private static String[] ReadFile() {

        String line = new String(); // БЕЗ ЭТОГО new String() НЕ РАБОТАЕТ СПЛИТТЕР ДЛЯ ТЕКСТА

        try(FileInputStream file = new FileInputStream("PATH\\input.txt")) {   // ВМЕСТО Path НАДО НАПИСАТЬ ПУТЬ ДО ФАЙЛА input.txt (НАПРИМЕР: "C:\\PROGRAMS\\VIRTUALPICNIC\\SRC\\INPUT.TXT")
            System.out.print("Продукты на пикник: [");
            byte[] buffer = new byte[1024];
            int LenBytesCount;
            while ((LenBytesCount = file.read(buffer)) != -1) {
                line = new String(buffer, 0, LenBytesCount);
                System.out.print(line);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("]");


        String[] text = new String[]{};
        text = line.split("\\s+"); // РАЗДЕЛЕНИЕ СЛОВ ПО ПРОБЕЛУ ИЛИ ПРОБЕЛАМ

        return text;
    }


    // МЕТОД ПО НАХОЖДЕНИЮ САМОГО ДЛИННОГО СЛОВА В ФАЙЛЕ
    private static String FindingLongWord(String[] InputText) {

        HashMap<String, Integer> LengthWord = new HashMap<>();
        for (String item : InputText) {
            LengthWord.putIfAbsent(item, item.length());    // ДОБАВЛЯЕТ НОВУЮ ПАРУ В MAP ЕСЛИ ЕЕ ТАМ НЕТ
        }

        // НАХОЖДЕНИЕ САМОГО ДЛИННОГО СЛОВА В ТЕКСТЕ
        ArrayList<Integer> ArrayValuesLength = new ArrayList<>(LengthWord.values());
        int MaxLength = Collections.max(ArrayValuesLength);
        String MaxWordLen = null;
        for(String word : InputText) {
            if(word.length() == MaxLength) MaxWordLen = word;
        }

        return MaxWordLen;
    }


    // ДАННЫЙ МЕТОД НУЖЕН, ЧТОБЫ МОЖНО БЫЛО В КОНСОЛИ ВЫВЕСТИ НАЗВАНИЯ ПРОДУКТОВ С ИХ КОЛИЧЕСТВОМ ПОВТОРЕНИЯ
    private static HashMap<String, Integer> ReturnCountWords(String[] InputText) {
        // HASHMAP ДЛЯ ПАРЫ ПРОДУКТА И КОЛИЧЕСТВА РАЗ КОТОРОЕ ОНО ВСТРЕЧАЕТСЯ
        HashMap<String, Integer> CountWords = new HashMap<>();
        for(String item : InputText) {
            if(CountWords.containsKey(item)) CountWords.put(item, CountWords.get(item) + 1);
            else CountWords.put(item, 1);
        }

        return CountWords;
    }


    // МЕТОД ПО НАХОЖДЕНИЮ САМЫХ ПОПУЛЯРНЫХ ПРОДУКТОВ НА ПИКНИКЕ
    private static ArrayList<String> FindingTheMostFrequentProducts(String[] InputText, HashMap<String, Integer> CountWords) {

        // НАХОЖДЕНИЕ ПРОДУКТ(A\ОВ), КОТОРЫЙ ПОВТОРЯЕТСЯ БОЛЬШЕ ВСЕХ И ДОБАВЛЕНИЕ ЕГО В ArrayList
        ArrayList<Integer> ArrayValuesCountWords = new ArrayList<>(CountWords.values());
        int MaxCount = Collections.max(ArrayValuesCountWords);
        ArrayList<String> MaxWordCount = new ArrayList<>();
        for(var word : CountWords.entrySet()) {
            if(word.getValue().equals(MaxCount)) MaxWordCount.add( word.getKey());
        }

        return MaxWordCount;
    }


}
