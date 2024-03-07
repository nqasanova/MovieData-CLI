import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.lang.reflect.Field;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException, SecurityException {
        ArrayList<Movie> movie_list = new ArrayList<Movie>();
        ArrayList<Movie> updated_list = new ArrayList<Movie>();

        try(FileReader fr = new FileReader(new File("mymoviedb.csv"));
            BufferedReader br = new BufferedReader(fr)) {

            String line = null; String[] reg; int i = 0;

            while((line = br.readLine()) != null) {
                i++;
                if(i == 1) {
                    continue;
                }
                reg = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Movie movie = new Movie(reg[0], reg[1], reg[2], reg[3], reg[4], reg[5], reg[6], reg[7], reg[8]);
                movie_list.add(movie);
            }
        } catch(Exception exception) {
            System.out.println("Error in opening file!");
        }

        System.out.println("*********************** My Movie ***********************\n");
        System.out.println("\t1 - List all the entities");  //+
        System.out.println("\t2 - Sort the entities");
        System.out.println("\t3 - Search the entities");
        System.out.println("\t4 - List column names"); //+
        System.out.println("\t5 - Filter entities");
        System.out.println("\t0 - Exit");  //+

        String select;

        while((select = scan.nextLine()) != null) {
            switch (select) {
                case "1":
                    List_Entities(movie_list);
                    break;
                case "2":
                    Sort_Entities(movie_list); // +
                    break;
                case "3":
                    updated_list = Search_Entities(movie_list); //+
                    List_Entities(updated_list);
                    break;
                case "4":
                    List_Column_Names(movie_list); // +
                    break;
                case "5":
                    updated_list = Filter(movie_list);
                    List_Entities(updated_list);
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid operation...");
            }
            System.out.println("*********************** My Movie ***********************\n");
            System.out.println("\t1 - List all the entities");
            System.out.println("\t2 - Sort the entities");
            System.out.println("\t3 - Search the entities");
            System.out.println("\t4 - List column names");
            System.out.println("\t5 - Filter entities");
            System.out.println("\t0 - Exit");
        }
    }

    public static void List_Entities(ArrayList<Movie> movies) {
        System.out.println("\t\ta: List all the fields");
        System.out.println("\t\tb: List only the selected fields");
        System.out.println("\t\tc: List entities based on the range of rows");
        String select_list = scan.nextLine();

        switch(select_list) {
            case "a":
                for (int m = 0; m < 100; m++) {
                    System.out.println(movies.get(m));
                }
                System.out.println("Total number of records: " + (100));
                break;

            case "b":
                System.out.println("Enter the field names to list:");
                String input = scan.nextLine();
                for (int i = 0; i < 100; i++) {
                    if (input.contains("Release_Date")) {
                        System.out.print(movies.get(i).getRelease_Date() + ",");
                    }
                    if (input.contains("Title")) {
                        System.out.print(movies.get(i).getTitle() + ",");
                    }
                    if (input.contains("Overview")) {
                        System.out.print(movies.get(i).getOverview() + ",");
                    }
                    if (input.contains("Popularity")) {
                        System.out.print(movies.get(i).getPopularity() + ",");
                    }
                    if (input.contains("Vote_Count")) {
                        System.out.print(movies.get(i).getVote_Count());
                    }
                    if (input.contains("Vote_Average")) {
                        System.out.print(movies.get(i).getVote_Average());
                    }
                    if (input.contains("Original_Language")) {
                        System.out.print(movies.get(i).getOriginal_Language() + ",");
                    }
                    if (input.contains("Genre")) {
                        System.out.print(movies.get(i).getGenre() + ",");
                    }
                    if (input.contains("Poster_Url")) {
                        System.out.print(movies.get(i).getPoster_Url() + ",");
                    }
                }
                System.out.println("Total number of records: " + (100));
                break;

            case "c":
                System.out.print("Enter LOWER boundary: ");
                int startin1 = scan.nextInt();
                System.out.print("Enter UPPER boundary: ");
                int ending1 = scan.nextInt();
                for(int i = startin1; i<ending1; i++) {
                    System.out.println(movies.get(i).toString());
                }
                System.out.println("Total number of records: " + (ending1 - startin1));
                break;
            default:
                System.out.println("Invalid operation");
        }
    }
}
