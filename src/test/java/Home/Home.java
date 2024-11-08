package Home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Home {
    private final WebDriver driver;

    public Home(WebDriver driver) { //Metodo Constructor
        this.driver = driver;
    }

    /**
     * Go to the url: https://wordcounter.net/
     */
    public void goToUrl(){
        driver.get("https://wordcounter.net/");
    }

    /**
     * Sends Keys to the tex box.
     * @param text text to be sent
     */
    public void sendKeys(String text){
        try {
            driver.findElement(By.id("box")).sendKeys(text);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Counts amount of word in the text box.
     * @param expectedWordCount amount of words expected.
     */
    public void countWord(int expectedWordCount){
        String wordCountText = driver.findElement(By.id("box")).getAttribute("value");
        assert wordCountText != null;
        String[] words = wordCountText.trim().split("\\s+");
        int actualWordCount = words.length;
        assertEquals(expectedWordCount, actualWordCount);
    }

    /**
     * Counts amount of characters in the text box.
     * @param expectedCharCount amount of characters expected.
     */
    public void countCharacter(int expectedCharCount){
        String charCountText = driver.findElement(By.id("box")).getAttribute("value");
        assert charCountText != null;
        int actualCharCount = charCountText.length();
        assertEquals(expectedCharCount, actualCharCount);
    }

    /**
     * Frecuency of each word in the text.
     */
    public void density(){
        String keywordDensityText = driver.findElement(By.id("box")).getAttribute("value");

        assert keywordDensityText != null;
        Map<String, Integer> wordCount = countWords(keywordDensityText);

        List<Map.Entry<String, Integer>> sortedWords = wordCount.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))  // Ordenar de mayor a menor
                .toList();

        System.out.println("Las 3 palabras más repetidas con sus repeticiones:");
        for (int i = 0; i < Math.min(3, sortedWords.size()); i++) {
            Map.Entry<String, Integer> entry = sortedWords.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue() + " veces");
        }
    }

    /**
     * Counts word in the text frequency.
     * @param text
     * @return amount of times that each word is present.
     */
    private static Map<String, Integer> countWords(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {  // Contar la frecuencia de cada palabra
            word = word.replaceAll("[^a-zA-Z]", "");  // Eliminar signos de puntuación
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }
}
