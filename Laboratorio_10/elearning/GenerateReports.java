import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateReports {
    public static void main(String[] args) {
        String inputFile = "processed_data.csv";
        String outputFile = "course_summary.csv";

        Map<String, Double> courseAverages = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String courseId = parts[1].trim(); // Assuming courseId is in parts[1]
                double grade = Double.parseDouble(parts[parts.length - 1].trim()); // Last column is average grade
                
                if (!courseAverages.containsKey(courseId)) {
                    courseAverages.put(courseId, 0.0);
                }
                courseAverages.put(courseId, courseAverages.get(courseId) + grade);
            }

            // Write course averages to output file
            for (Map.Entry<String, Double> entry : courseAverages.entrySet()) {
                double average = entry.getValue() / courseAverages.size();
                writer.write(entry.getKey() + "," + average + "\n");
            }
            System.out.println("Report generation completed. Summary saved in " + outputFile);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

