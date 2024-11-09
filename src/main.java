import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        HashMap<Character, Integer> dict = new HashMap<>();
        for (int i = 97;i<97+26;i++){
            dict.put((char) i,0);
        }
        for (int i = 65;i<65+26;i++){
            dict.put((char) i,0);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filepath to read:");
        String path = scanner.next();
        File file = new File(path);

        try {
            if (!file.exists()){
                throw new FileNotFoundException("File not found: " + path);
            }

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                for (int i=0;i<line.length();i++){
                    if (dict.get(line.charAt(i))!=null){
                        dict.compute(line.charAt(i), (k, value) -> value + 1);
                    }
                }
                line = reader.readLine();
            }
            reader.close();

            System.out.print("Enter the filepath to write results:");
            String output = scanner.next();
            File output_file = new File(output);

            String output_str = dict.toString();
            if (!output_file.exists()){
                throw new FileNotFoundException("File not found: " + output);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(output_file));
            writer.write(output_str);
            writer.close();

            System.out.println(dict);

        } catch (IOException e) {
            e.printStackTrace(); //throw new RuntimeException(e);
        }


    }
}
