import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The classMark program reads a text file into a list
 * and then converts it into an array and then randomly
 * generate marks for each student in the array.
 *
 * @author Ketia Gaelle Kaze
 * @version 1.0
 * @since 2022-03-22
 */

class ClassMarks {
    /**
     * Creating private constructor due to
     * public/default constructor error.
     *
     * @throws IllegalStateException
     *
     */

    private ClassMarks() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generate random marks for each student.
     *
     * @param students passed in
     * @param assignments passed in
     * @return markArray
     */

    public static String[][] generateMarks(String[] students,
        String[] assignments) {
        Random random = new Random();
        String[][] markArray =
            new String[assignments.length + 1][students.length];

        for (int namesCount = 0; namesCount < students.length; namesCount++) {
            markArray[0][namesCount] = students[namesCount];
        }

        for (int studentsCount = 0; studentsCount
            < students.length; studentsCount++) {
            for (int assignmentCount = 1; assignmentCount
                < assignments.length + 1; assignmentCount++) {
                markArray[assignmentCount][studentsCount] =
                    String.valueOf(Math.round(random.nextGaussian() * 10 + 75));
            }
        }
        // Return the array.
        return markArray;
    }
    /**
     * Main entry into the statistics program.
     *
     * @param args nothing passed in
     *
     * @throws IOException if an issue occurs
     */

    public static void main(String[] args) throws IOException {
        // Declaring Variables
        List<String> listOfStudents = new ArrayList<String>();
        List<String> listOfAssigns = new ArrayList<String>();
        String[] studentArray;
        String[] assignArray;
        String[][] marks;

        // Read the assignments and names from their text file into their list.
        try {
            listOfStudents = Files.readAllLines(Paths.get("students.txt"));
            listOfAssigns = Files.readAllLines(Paths.get("assignments.txt"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Transfer the assignments and names from
        // their lists to their respective arrays.
        studentArray = listOfStudents.toArray(new String[0]);
        assignArray = listOfAssigns.toArray(new String[0]);

        try {
            // Call generateMarks.
            marks = generateMarks(studentArray, assignArray);

            // build a string containing the elements of the 2d array
            StringBuilder builder = new StringBuilder();
            for (int rows = 0; rows < marks[0].length; ++rows) {
                for (int columns = 0; columns < marks.length; ++columns) {
                    builder.append(marks[columns][rows]);
                    builder.append("  ");
                }
                builder.append("\n");

            }

            // create new file called marks.csv
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                "/home/ubuntu/environment"
                + "/Unit1/Unit1-07/Unit1-07-Java/marks.csv"));
            writer.write(builder.toString());
            writer.close();
            System.out.println("Assigned marks to the csv file");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
