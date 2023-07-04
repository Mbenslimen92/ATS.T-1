package de.tritux.db.MotCle;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;



@Component
public class ExtractionMotCle{
    private static final List<String> stopWords = Arrays.asList("et", "le", "de"); // Ajoutez vos propres mots vides

    public List<String> extractKeywords(String text) {
        // Prétraitement du texte
        List<String> tokens = preprocessText(text);

        // Calcul du TF-IDF pour les mots
        Map<String, Double> tfIdfScores = calculateTfIdf(tokens);

        // Tri des mots-clés par score TF-IDF décroissant
        List<String> keywords = tfIdfScores.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return keywords;
    }

    private List<String> preprocessText(String text) {
        // Suppression des caractères spéciaux
        text = text.replaceAll("[^a-zA-Z ]", "");

        // Tokenization
        String[] tokens = text.split("\\s+");

        // Suppression des mots vides et normalisation
        List<String> filteredTokens = Arrays.stream(tokens)
                .map(String::toLowerCase)
                .filter(token -> !stopWords.contains(token))
                .collect(Collectors.toList());

        return filteredTokens;
    }

    private Map<String, Double> calculateTfIdf(List<String> tokens) {
        // Calcul des fréquences des termes (TF)
        Map<String, Long> termFrequencies = tokens.stream()
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        // Calcul de l'inverse de la fréquence des documents (IDF)
        double idfNormalizationFactor = Math.log(tokens.size());
        Map<String, Double> inverseDocumentFrequencies = termFrequencies.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> idfNormalizationFactor / entry.getValue()));

        // Calcul du score TF-IDF
        Map<String, Double> tfIdfScores = termFrequencies.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() * inverseDocumentFrequencies.get(entry.getKey())));

        return tfIdfScores;
    }
}