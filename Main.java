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
    }
}
