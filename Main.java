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
}
