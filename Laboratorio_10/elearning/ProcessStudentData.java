import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessStudentData {
    public static void main(String[] args) {
        String inputFile = "student_data.csv";
        String outputFile = "processed_data.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Calculate average grade (assuming grades start from index 3 in parts)
                double sum = 0.0;
                for (int i = 3; i < parts.length; i++) {
                    sum += Double.parseDouble(parts[i].trim());
                }
                double average = sum / (parts.length - 3);

                // Write to output file
                writer.write(line + "," + average + "\n");
            }
            System.out.println("Processing completed. Results saved in " + outputFile);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

