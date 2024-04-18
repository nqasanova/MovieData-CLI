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
}
