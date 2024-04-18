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
            switch(select) {
                case "1":
                    List_Entities(movie_list);
                    break;
//                case "2":
//                    Sort_Entities(movie_list); // +
//                    break;
//                case "3":
//                    updated_list = Search_Entities(movie_list); //+
//                    List_Entities(updated_list);
//                    break;
//                case "4":
//                    List_Column_Names(movie_list); // +
//                    break;
//                case "5":
//                    updated_list = Filter(movie_list);
//                    List_Entities(updated_list);
//                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unvalid operation");
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
                for(int m = 0; m < 100; m++) {
                    System.out.println(movies.get(m));
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
            case "b":
                System.out.print("Enter the field names to list:");
                String input = scan.nextLine();
                for(int i=0; i<100; i++) {
                    if(input.contains("Release_Date")) {
                        System.out.print(movies.get(i).getRelease_Date() + ",");
                    }
                    if(input.contains("Title")) {
                        System.out.print(movies.get(i).getTitle() + ",");
                    }
                    if(input.contains("Overview")) {
                        System.out.print(movies.get(i).getOverview() + ",");
                    }
                    if(input.contains("Popularity")) {
                        System.out.print(movies.get(i).getPopularity() + ",");
                    }
                    if(input.contains("Vote_Count")) {
                        System.out.print(movies.get(i).getVote_Count());
                    }
                    if(input.contains("Vote_Average")) {
                        System.out.print(movies.get(i).getVote_Average());
                    }
                    if(input.contains("Original_Language")) {
                        System.out.print(movies.get(i).getOriginal_Language() + ",");
                    }
                    if(input.contains("Genre")) {
                        System.out.print(movies.get(i).getGenre() + ",");
                    }
                    if(input.contains("Poster_Url")) {
                        System.out.print(movies.get(i).getPoster_Url() + ",");
                    }
                }
                System.out.println("Total number of records: " + (100));
                break;
            default:
                System.out.println("Invalid operation");
        }
    }

    public static void Sort_Entities(ArrayList<Movie> lMovies) {
        System.out.println("\t-------------Sort-------------");
        System.out.println("\ta - to sort based on Release_Date");
        System.out.println("\tb - to sort based on Title");
        System.out.println("\tc - to sort based on Overview");
        System.out.println("\td - to sort based on Popularity");
        System.out.println("\te - to sort based on Vote_Count");
        System.out.println("\tf - to sort based on Vote_Average");
        System.out.println("\tg - to sort based on Original_Language");
        System.out.println("\th - to sort based on Genre");
        System.out.println("\tj- to sort based on Poster_Url");

        String select_sortString = scan.nextLine();

        switch(select_sortString) {
            case "a":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str1 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str1)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            int x = a.getDay() - b.getDay();
                            int y = a.getMonth() - b.getMonth();
                            int z = a.getYear() - b.getYear();
                            if(z != 0) {
                                return z;
                            } else if(z == 0 && y != 0) {
                                return y;
                            }
                            return x;
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str1)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            int x = b.getDay() - a.getDay();
                            int y = b.getMonth() - a.getMonth();
                            int z = b.getYear() - a.getYear();
                            if(z != 0) {
                                return z;
                            } else if(z == 0 && y != 0) {
                                return y;
                            }
                            return x;
                        }
                    });
                } else {
                    System.out.println("");
                }
                break;
            case "b":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str2 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str2)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return a.getTitle().compareTo(b.getTitle());
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str2)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return b.getTitle().compareTo(a.getTitle());
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            case "c":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str3 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str3)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return a.getOverview().compareTo(b.getOverview());
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str3)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return b.getOverview().compareTo(a.getOverview());
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            case "d":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str4 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str4)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            double x = Double.valueOf(a.getPopularity());
                            double y = Double.valueOf(b.getPopularity());
                            if(x - y > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str4)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            double x = Double.valueOf(a.getPopularity());
                            double y = Double.valueOf(b.getPopularity());
                            if(y - x > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            case "e":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str5 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str5)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            int x = Integer.valueOf(a.getVote_Count());
                            int y = Integer.valueOf(b.getVote_Count());
                            return x - y;
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str5)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            int x = Integer.valueOf(a.getVote_Count());
                            int y = Integer.valueOf(b.getVote_Count());
                            return y - x;
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            case "f":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str6 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str6)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            double x = Double.valueOf(a.getVote_Average());
                            double y = Double.valueOf(b.getVote_Average());
                            if(x - y > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str6)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            double x = Double.valueOf(a.getVote_Average());
                            double y = Double.valueOf(b.getVote_Average());
                            if(y - x > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            case "g":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str7 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str7)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return a.getOriginal_Language().compareTo(b.getOriginal_Language());
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str7)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return b.getOriginal_Language().compareTo(a.getOriginal_Language());
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            case "h":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str8 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str8)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return a.getGenre().compareTo(b.getGenre());
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str8)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return b.getGenre().compareTo(a.getGenre());
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            case "j":
                System.out.print("Would you like to sort in DESC or ASC order? ");
                String str9 = scan.nextLine();
                if("ASC".equalsIgnoreCase(str9)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return a.getPoster_Url().compareTo(b.getPoster_Url());
                        }
                    });
                } else if("DESC".equalsIgnoreCase(str9)) {
                    Collections.sort(lMovies, new Comparator<Movie>() {
                        public int compare(Movie a, Movie b) {
                            return b.getPoster_Url().compareTo(a.getPoster_Url());
                        }
                    });
                } else {
                    System.out.println("Invalid operation!");
                }
                break;
            default:
                System.out.println("Invalid operation!");
                break;
        }
    }

    public static ArrayList<Movie> Search_Entities(ArrayList<Movie> lMovies) {
        System.out.print("FOR STRING FIELDS -> Enter parametersfield name first\nFOR NON-STRING FIELDS Enter the exact values\n>");
        ArrayList<Movie> selected_entities = new ArrayList<Movie>();
        String string = scan.nextLine();
        if(string.contains("Title") || string.toLowerCase().contains("Overview") || string.toLowerCase().contains("Original_Language") || string.toLowerCase().contains("Poster_Url")) {
            System.out.print("Contains part that you want to search: ");
            String c = scan.nextLine();
            if(string.contains("Title")) {
                for(int i=0; i<lMovies.size(); i++) {
                    if(lMovies.get(i).getTitle().contains(c)) {
                        selected_entities.add(lMovies.get(i));
                    }
                }
            }
            if(string.contains("Overview")) {
                for(int i=0; i<lMovies.size(); i++) {
                    if(lMovies.get(i).getOverview().contains(c)) {
                        selected_entities.add(lMovies.get(i));
                    }
                }
            }
            if(string.contains("Original_Language")) {
                for(int i=0; i<lMovies.size(); i++) {
                    if(lMovies.get(i).getOriginal_Language().contains(c)) {
                        selected_entities.add(lMovies.get(i));
                    }
                }
            }
            if(string.contains("Poster_Url")) {
                for(int i=0; i<lMovies.size(); i++) {
                    if(lMovies.get(i).getPoster_Url().contains(c)) {
                        selected_entities.add(lMovies.get(i));
                    }
                }
            }
            if(selected_entities.size() != 0) {
                System.out.println("Would you like to save this data in the file 'selected_entities.csv'?");
                System.out.print("Type YES/NO: ");
                String answer = scan.nextLine();
                if(answer.equalsIgnoreCase("YES")) {
                    try(FileWriter fw= new FileWriter(new File("selected_entities.csv"));
                        BufferedWriter bw = new BufferedWriter(fw))
                    {
                        for(int i=0; i<selected_entities.size(); i++) {
                            bw.write(selected_entities.get(i).toString());
                        }
                        System.out.println("Successfully written!");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if(string.contains("Release_Date") || string.contains("Popularity") || string.contains("Vote_Count") ||
                string.contains("Vote_Average"))
        {
            for(int i=0; i<lMovies.size(); i++) {
                if(string.contains("Release_Date") && ("Release_Date " + lMovies.get(i).getRelease_Date()).equals(string)) {
                    selected_entities.add(lMovies.get(i));
                }
                if(string.contains("Popularity") && ("Popularity " + lMovies.get(i).getPopularity()).equals(string)) {
                    selected_entities.add(lMovies.get(i));
                }
                if(string.contains("Vote_Count") && ("Vote_Count " + lMovies.get(i).getVote_Count()).equals(string)) {
                    selected_entities.add(lMovies.get(i));
                }
                if(string.contains("Vote_Average") && ("Vote_Average " + lMovies.get(i).getVote_Average()).equals(string)) {
                    selected_entities.add(lMovies.get(i));
                }
            }
            if(selected_entities.size() != 0) {
                System.out.println("Would you like to save this data in the file 'selected_entities.csv'?");
                System.out.print("Type YES/NO: ");
                String answer = scan.nextLine();
                if(answer.equalsIgnoreCase("YES")) {
                    try(FileWriter fw= new FileWriter(new File("selected_entities.csv"));
                        BufferedWriter bw = new BufferedWriter(fw))
                    {
                        for(int i=0; i<selected_entities.size(); i++) {
                            bw.write(selected_entities.get(i).toString());
                        }
                        System.out.println("Successfully written!");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if(string.contains("Genre")) {
            System.out.print("Genre Types: ");
            System.out.print("Music, Drama, History, Science Fiction, War, Mystery, Thriller, Horror, Crime, Fantasy, Family");
            System.out.println(" Adventure, Romance, Animation, Comedy, TV Movie, Documentary, Action");
            for(int i=0; i<lMovies.size(); i++) {
                if(string.contains("Genre") && lMovies.get(i).getGenre().equals("Action") ||
                        lMovies.get(i).getGenre().equals("Music") || lMovies.get(i).getGenre().equals("Drama") ||
                        lMovies.get(i).getGenre().equals("History") || lMovies.get(i).getGenre().equals("Science Fiction") ||
                        lMovies.get(i).getGenre().equals("War") || lMovies.get(i).getGenre().equals("Mystery") ||
                        lMovies.get(i).getGenre().equals("Thriller") || lMovies.get(i).getGenre().equals("Horror") ||
                        lMovies.get(i).getGenre().equals("Crime") || lMovies.get(i).getGenre().equals("Fantasy") ||
                        lMovies.get(i).getGenre().equals("Family") || lMovies.get(i).getGenre().equals("Adventure") ||
                        lMovies.get(i).getGenre().equals("Romance") || lMovies.get(i).getGenre().equals("Animation") ||
                        lMovies.get(i).getGenre().equals("Comedy") || lMovies.get(i).getGenre().equals("TV Movie") ||
                        lMovies.get(i).getGenre().equals("Documentary")) {
                    selected_entities.add(lMovies.get(i));
                }
                System.out.println("Would you like to store this data in 'entities.csv' file?");
                System.out.print("Type YES/NO: ");
                String answer = scan.nextLine();
                if(answer.equalsIgnoreCase("YES")) {
                    try(FileWriter fw= new FileWriter(new File("selected_entities.csv"));
                        BufferedWriter bw = new BufferedWriter(fw))
                    {
                        for(int k=0; k<selected_entities.size(); k++) {
                            bw.write(selected_entities.get(k).toString());
                        }
                        System.out.println("Successfully written!");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            System.out.println("Invalid operation!");
        }
        return selected_entities;
    }

    public static void List_Column_Names(ArrayList<Movie> lMovies) {
        try(FileReader fr = new FileReader(new File("mymoviedb.csv"));
            BufferedReader br = new BufferedReader(fr)) {

            String lString = br.readLine();
            String columns[] = lString.split(",");
            System.out.println("[" + columns[0] + ", " + columns[1] + ", " + columns[2] + ", " + columns[3] + ", " + columns[4] +
                    ", " + columns[5] + ", " + columns[6] + ", " + columns[7] + ", " + columns[8] + "]");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Movie> Filter(ArrayList<Movie> lMovies) throws NoSuchFieldException, IllegalAccessException, SecurityException {

        System.out.println("How many fields would you like to filter?");
        String n = scan.nextLine();
        int num = Integer.valueOf(n);
        ArrayList<Movie> filter_field = new ArrayList<Movie>(); //

        for(int k=0; k<=num; k++) {
            System.out.print("Enter field name: ");
            String filter_parameter = scan.nextLine();
            try {
                Field field = Movie.class.getDeclaredField(filter_parameter);
                field.setAccessible(true);

                if(field.getName().equals(filter_parameter) && field.getName().equals("Title") ||
                        field.getName().equals("Overview") || field.getName().equals("Genre") ||
                        field.getName().equals("Original_Language") || field.getName().equals("Poster_Url")) {
                    filter_field = Filter_String_Fields(lMovies, filter_parameter);
                } else if(field.getName().equals(filter_parameter) && field.getName().equals("Release_Date")) {
                    filter_field = Filter_Date_Fields(lMovies, filter_parameter);
                } else if(field.getName().equals(filter_parameter) && field.getName().equals("Vote_Count")) {
                    filter_field = Filter_Numeric_Fields(lMovies, filter_parameter);
                } else if(field.getName().equals(filter_parameter) && field.getName().equals("Popularity") || field.getName().equals("Vote_Average")) {
                    filter_field = Filter_Double_Fields(lMovies, filter_parameter);
                } else {
                    System.out.println("Invalid Operation");
                }
            } catch (Exception e) {
                //System.out.println("\nERROR!");
            }
        }

        if (filter_field.size() != 0) {
            System.out.println("Would you like to store this data in 'selected_entities_2.csv' file?");
            System.out.print("Type YES/NO: ");
            String ans = scan.nextLine();
            if (ans.equalsIgnoreCase("YES")) {
                try (FileWriter fw = new FileWriter(new File("selected_entities.csv"))) {
                    for (int i = 0; i < filter_field.size(); i++) {
                        fw.write(filter_field.get(i).toString() + '\n');
                    }
                    System.out.println("Successfully written!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Unfortunately, nothing is found.");
        }
        return filter_field;
    }

    public static ArrayList<Movie> Filter_String_Fields(ArrayList<Movie> lMovies, String filter) throws NoSuchFieldException, IllegalAccessException, SecurityException {
        ArrayList<Movie> string_fields = new ArrayList<Movie>();

        System.out.println("\ti. string fields starts with");
        System.out.println("\tii. string fields ends with");
        System.out.println("\tiii. string fields contains");
        System.out.println("\tiv. string fields null (missing values)");

        String string = scan.nextLine();
        if(string.equals("i") || string.equals("1")) {
            System.out.print("Enter the starting of fields: ");
            String starting = scan.nextLine();
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                if(name.startsWith(starting)) {
                    string_fields.add(lMovies.get(i));
                }
            }
        } else if(string.equals("ii") || string.equals("2")) {
            System.out.print("Enter the ending of fields: ");
            String ending = scan.nextLine();
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                if(name.endsWith(ending)) {
                    string_fields.add(lMovies.get(i));
                }
            }
        } else if(string.equals("iii") || string.equals("3")) {
            System.out.println("Enter the part of contains field");
            String contain = scan.nextLine();
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                if(name.contains(contain)) {
                    string_fields.add(lMovies.get(i));
                }
            }
        } else if(string.equals("iv") || string.equals("4")) {
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                if(name.length() == 0) {
                    string_fields.add(lMovies.get(i));
                }
            }
        } else {
            System.out.println("Invalid operation");
        }
        return string_fields;
    }

    public static ArrayList<Movie> Filter_Date_Fields(ArrayList<Movie> lMovies, String filter) throws NoSuchFieldException, SecurityException, IllegalAccessException{
        ArrayList<Movie> date_field = new ArrayList<Movie>();

        System.out.println("\ti. equal (eq)");
        System.out.println("\tii. greater than (gt)");
        System.out.println("\tiii. less than (lt)");
        System.out.println("\tiv. greater and equal to (ge)");
        System.out.println("\tv. less and equal to (le)");
        System.out.println("\tvi. between (bt)");
        System.out.println("\tvii. null (missing values)");
        System.out.println("\tviii. in a specific year (y)");
        System.out.println("\tix. in a specific month (m)");
        System.out.println("\tx. in a specific day (d)");


        String string = scan.nextLine();

        if(string.equalsIgnoreCase("eq") || string.equals("i") || string.equals("1")) {
            System.out.print("Enter the EQUAL value that you want to filter: ");
            String string2 = scan.nextLine();
            String equalString[] = string2.split("/");
            int month = Integer.valueOf(equalString[0]);
            int day = Integer.valueOf(equalString[1]);
            int year = Integer.valueOf(equalString[2]);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int first_date;
                int second_date;
                int third_date;
                if(!name.equals("")) {
                    String[] string_2 = name.split("/");
                    first_date = Integer.valueOf(string_2[0]);
                    second_date = Integer.valueOf(string_2[1]);
                    third_date = Integer.valueOf(string_2[2]);
                } else {
                    first_date = 0;
                    second_date = 0;
                    third_date = 0;
                }
                if(first_date == month && second_date == day && third_date == year) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("gt") || string.equals("ii") || string.equals("2")) {
            System.out.print("Enter the GREATER value that you want to filter: ");
            String string3 = scan.nextLine();
            String[] greaterString = string3.split("/");
            int month = Integer.valueOf(greaterString[0]);
            int day = Integer.valueOf(greaterString[1]);
            int year = Integer.valueOf(greaterString[2]);
            int total_date  = (365 * year) + (31 * month) + day;
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int first_date;
                int second_date;
                int third_date;
                if(!name.equals("")) {
                    String[] string_3 = name.split("/");
                    first_date = Integer.valueOf(string_3[0]);
                    second_date = Integer.valueOf(string_3[1]);
                    third_date = Integer.valueOf(string_3[2]);
                } else {
                    first_date = 0;
                    second_date = 0;
                    third_date = 0;
                }
                int total = (third_date * 365) + (first_date * 31) + second_date ;
                if(total > total_date) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("lt") || string.equals("iii") || string.equals("3")) {
            System.out.print("Enter the LESS value that you want to filter: ");
            String string3 = scan.nextLine();
            String[] greaterString = string3.split("/");
            int month = Integer.valueOf(greaterString[0]);
            int day = Integer.valueOf(greaterString[1]);
            int year = Integer.valueOf(greaterString[2]);
            int total_date  = (365 * year) + (31 * month) + day;
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int first_date;
                int second_date;
                int third_date;
                if(!name.equals("")) {
                    String[] string_3 = name.split("/");
                    first_date = Integer.valueOf(string_3[0]);
                    second_date = Integer.valueOf(string_3[1]);
                    third_date = Integer.valueOf(string_3[2]);
                } else {
                    first_date = 0;
                    second_date = 0;
                    third_date = 0;
                }
                int total = (third_date * 365) + (first_date * 31) + second_date ;
                if(total < total_date) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("ge") || string.equals("iv") || string.equals("4")) {
            System.out.print("Enter the GREATER and EQUAL value that you want to filter: ");
            String string3 = scan.nextLine();
            String[] greaterString = string3.split("/");
            int month = Integer.valueOf(greaterString[0]);
            int day = Integer.valueOf(greaterString[1]);
            int year = Integer.valueOf(greaterString[2]);
            int total_date  = (365 * year) + (31 * month) + day;
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int first_date;
                int second_date;
                int third_date;
                if(!name.equals("")) {
                    String[] string_3 = name.split("/");
                    first_date = Integer.valueOf(string_3[0]);
                    second_date = Integer.valueOf(string_3[1]);
                    third_date = Integer.valueOf(string_3[2]);
                } else {
                    first_date = 0;
                    second_date = 0;
                    third_date = 0;
                }
                int total = (third_date * 365) + (first_date * 31) + second_date ;
                if(total >= total_date) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("le") || string.equals("v") || string.equals("5")) {
            System.out.print("Enter the LESS and EQUAL value that you want to filter: ");
            String string3 = scan.nextLine();
            String[] greaterString = string3.split("/");
            int month = Integer.valueOf(greaterString[0]);
            int day = Integer.valueOf(greaterString[1]);
            int year = Integer.valueOf(greaterString[2]);
            int total_date  = (365 * year) + (31 * month) + day;
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int first_date;
                int second_date;
                int third_date;
                if(!name.equals("")) {
                    String[] string_3 = name.split("/");
                    first_date = Integer.valueOf(string_3[0]);
                    second_date = Integer.valueOf(string_3[1]);
                    third_date = Integer.valueOf(string_3[2]);
                } else {
                    first_date = 0;
                    second_date = 0;
                    third_date = 0;
                }
                int total = (third_date * 365) + (first_date * 31) + second_date ;
                if(total < total_date) {
                    date_field.add(lMovies.get(i));
                }
            }


        } else if(string.equalsIgnoreCase("ge") || string.equals("iv") || string.equals("4")) {
            System.out.println("Enter the LOW DATE boundary that you want to filter");
            String string3 = scan.nextLine();
            String[] greaterString = string3.split("/");
            int low_month = Integer.valueOf(greaterString[0]);
            int low_day = Integer.valueOf(greaterString[1]);
            int low_year = Integer.valueOf(greaterString[2]);
            int low_date  = (365 * low_year) + (31 * low_month) + low_day;
            System.out.println("Enter the HIGH DATE boundary that you want to filter");
            String string4 = scan.nextLine();
            String[] greaterHigh = string4.split("/");
            int high_month = Integer.valueOf(greaterHigh[0]);
            int high_day = Integer.valueOf(greaterHigh[1]);
            int high_year = Integer.valueOf(greaterHigh[2]);
            int high_date  = (365 * high_year) + (31 * high_month) + high_day;
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int first_date;
                int second_date;
                int third_date;
                if(!name.equals("")) {
                    String[] string_3 = name.split("/");
                    first_date = Integer.valueOf(string_3[0]);
                    second_date = Integer.valueOf(string_3[1]);
                    third_date = Integer.valueOf(string_3[2]);
                } else {
                    first_date = 0;
                    second_date = 0;
                    third_date = 0;
                }
                int total = (third_date * 365) + (first_date * 31) + second_date ;
                if(total > low_date && high_date > total) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("null") || string.equals("vii") || string.equals("7")) {
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                if(name.length() == 0) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("y") || string.equals("viii") || string.equals("8")) {
            System.out.print("Enter the EXACT YEAR value that you want to filter: ");
            String string3 = scan.nextLine();
            int year = Integer.valueOf(string3);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int third_date;
                if(!name.equals("")) {
                    String[] aStrings = name.split("/");
                    third_date = Integer.valueOf(aStrings[2]);
                } else {
                    third_date = 0;
                }
                if(year == third_date) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("m") || string.equals("ix") || string.equals("9")) {
            System.out.print("Enter the EXACT MONTH value that you want to filter: ");
            String string3 = scan.nextLine();
            int month = Integer.valueOf(string3);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int first_date;
                if(!name.equals("")) {
                    String[] aStrings = name.split("/");
                    first_date = Integer.valueOf(aStrings[0]);
                } else {
                    first_date = 0;
                }
                if(month == first_date) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else if(string.equalsIgnoreCase("d") || string.equals("x") || string.equals("10")) {
            System.out.print("Enter the EXACT DAY value that you want to filter: ");
            String string3 = scan.nextLine();
            int day = Integer.valueOf(string3);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int second_date;
                if(!name.equals("")) {
                    String[] aStrings = name.split("/");
                    second_date = Integer.valueOf(aStrings[1]);
                } else {
                    second_date = 0;
                }
                if(day == second_date) {
                    date_field.add(lMovies.get(i));
                }
            }
        } else {
            System.out.println("Invalid operation");
        }
        return date_field;
    }

    public static ArrayList<Movie> Filter_Numeric_Fields(ArrayList<Movie> lMovies, String filter) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException {
        ArrayList<Movie> filter_numeric = new ArrayList<Movie>();

        System.out.println("\ti. equal (eq)");
        System.out.println("\tii. greater than (gt)");
        System.out.println("\tiii. less than (lt)");
        System.out.println("\tiv. greater and equal to (ge)");
        System.out.println("\tv. less and equal to (le)");
        System.out.println("\tvi. between (bt)");
        System.out.println("\tvii. null (missing values)");

        String numeric = scan.nextLine();
        if(numeric.equalsIgnoreCase("i") || numeric.equalsIgnoreCase("1") || numeric.equalsIgnoreCase("eq")) {
            System.out.print("Enter EQUAL value that you want to filter: ");
            String equalString = scan.nextLine();
            int answer = Integer.valueOf(equalString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int num = Integer.valueOf(name);
                if(num == answer) {
                    filter_numeric.add(lMovies.get(i));
                }
            }
        } else if(numeric.equalsIgnoreCase("ii") || numeric.equalsIgnoreCase("2") || numeric.equalsIgnoreCase("gt")) {
            System.out.print("Enter GREATER value that you want to filter: ");
            String equalString = scan.nextLine();
            int answer = Integer.valueOf(equalString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int num = Integer.valueOf(name);
                if(num > answer) {
                    filter_numeric.add(lMovies.get(i));
                }
            }
        } else if(numeric.equalsIgnoreCase("iii") || numeric.equalsIgnoreCase("3") || numeric.equalsIgnoreCase("lt")) {
            System.out.print("Enter LESS value that you want to filter: ");
            String equalString = scan.nextLine();
            int answer = Integer.valueOf(equalString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int num = Integer.valueOf(name);
                if(num < answer) {
                    filter_numeric.add(lMovies.get(i));
                }
            }
        } else if(numeric.equalsIgnoreCase("iv") || numeric.equalsIgnoreCase("4") || numeric.equalsIgnoreCase("ge")) {
            System.out.print("Enter GREATER and EQUAL value that you want to filter: ");
            String equalString = scan.nextLine();
            int answer = Integer.valueOf(equalString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int num = Integer.valueOf(name);
                if(num >= answer) {
                    filter_numeric.add(lMovies.get(i));
                }
            }
        } else if(numeric.equalsIgnoreCase("v") || numeric.equalsIgnoreCase("5") || numeric.equalsIgnoreCase("le")) {
            System.out.print("Enter LESS and EQUAL value that you want to filter: ");
            String equalString = scan.nextLine();
            int answer = Integer.valueOf(equalString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                int num = Integer.valueOf(name);
                if(num <= answer) {
                    filter_numeric.add(lMovies.get(i));
                }
            }
        } else if(numeric.equalsIgnoreCase("vi") || numeric.equalsIgnoreCase("6") || numeric.equalsIgnoreCase("bt")) {
            System.out.print("Enter the LOW boundary of the range: ");
            int low_boundary = scan.nextInt();
            System.out.print("Enter the HIGH boundary of the range: ");
            int high_boundary = scan.nextInt();
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String)field.get(lMovies.get(i));
                int num = Integer.valueOf(name);
                if(num > low_boundary && num < high_boundary) {
                    filter_numeric.add(lMovies.get(i));
                }
            }
        } else if(numeric.equalsIgnoreCase("null") || numeric.equalsIgnoreCase("vii") || numeric.equalsIgnoreCase("7")) {
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String)field.get(lMovies.get(i));
                if(name.length() == 0) {
                    filter_numeric.add(lMovies.get(i));
                }
            }
        } else {
            System.out.println("Invalid operation");
        }

        return filter_numeric;
    }

    public static ArrayList<Movie> Filter_Double_Fields(ArrayList<Movie> lMovies, String filter) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException {
        ArrayList<Movie> double_fields = new ArrayList<Movie>();

        System.out.println("\ti. equal (eq)");
        System.out.println("\tii. greater than (gt)");
        System.out.println("\tiii. less than (lt)");
        System.out.println("\tiv. greater and equal to (ge)");
        System.out.println("\tv. less and equal to (le)");
        System.out.println("\tvi. between (bt)");
        System.out.println("\tvii. null (missing values)");

        String doubleString = scan.nextLine();
        if(doubleString.equals("i") || doubleString.equals("1") || doubleString.equalsIgnoreCase("eq")) {
            System.out.print("Enter EQUAL value that you want to filter: ");
            String dString = scan.nextLine();
            double answer = Double.valueOf(dString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                double double_num = Double.valueOf(name);
                if(double_num == answer) {
                    double_fields.add(lMovies.get(i));
                }
            }
        } else if(doubleString.equals("ii") || doubleString.equals("2") || doubleString.equalsIgnoreCase("gt")) {
            System.out.print("Enter GREATER THAN value that you want to filter: ");
            String dString = scan.nextLine();
            double answer = Double.valueOf(dString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                double double_num = Double.valueOf(name);
                if(double_num > answer) {
                    double_fields.add(lMovies.get(i));
                }
            }
        } else if(doubleString.equals("iii") || doubleString.equals("3") || doubleString.equalsIgnoreCase("lt")) {
            System.out.print("Enter LESS THAN value that you want to filter: ");
            String dString = scan.nextLine();
            double answer = Double.valueOf(dString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                double double_num = Double.valueOf(name);
                if(double_num < answer) {
                    double_fields.add(lMovies.get(i));
                }
            }
        } else if(doubleString.equals("iv") || doubleString.equals("4") || doubleString.equalsIgnoreCase("ge")) {
            System.out.print("Enter GREATER and EQUAL to value that you want to filter: ");
            String dString = scan.nextLine();
            double answer = Double.valueOf(dString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                double double_num = Double.valueOf(name);
                if(double_num >= answer) {
                    double_fields.add(lMovies.get(i));
                }
            }
        } else if(doubleString.equals("v") || doubleString.equals("5") || doubleString.equalsIgnoreCase("le")) {
            System.out.print("Enter LESS and EQUAL to value that you want to filter: ");
            String dString = scan.nextLine();
            double answer = Double.valueOf(dString);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                double double_num = Double.valueOf(name);
                if(double_num <= answer) {
                    double_fields.add(lMovies.get(i));
                }
            }
        } else if(doubleString.equals("vi") || doubleString.equals("6") || doubleString.equalsIgnoreCase("bt")) {
            System.out.print("Enter LOW value that you want to filter: ");
            String dString1 = scan.nextLine();
            double answer1 = Double.valueOf(dString1);
            System.out.print("Enter HIGH value that you want to filter: ");
            String dString2 = scan.nextLine();
            double answer2 = Double.valueOf(dString2);
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                double double_num = Double.valueOf(name);
                if(double_num > answer1 && double_num < answer2) {
                    double_fields.add(lMovies.get(i));
                }
            }
        } else if(doubleString.equals("vii") || doubleString.equals("7") || doubleString.equalsIgnoreCase("null")) {
            for(int i=0; i<lMovies.size(); i++) {
                Field field = Movie.class.getDeclaredField(filter);
                field.setAccessible(true);
                String name = (String) field.get(lMovies.get(i));
                if(name.length() == 0) {
                    double_fields.add(lMovies.get(i));
                }
            }
        } else {
            System.out.println("Invalid Operation");
        }


        return double_fields;
    }
}
