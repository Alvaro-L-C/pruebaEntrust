import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class text_formatter {

    private static List<String> format_file(int line_length, String path) throws IOException{
        List<String> formatted_content = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String text_line = reader.readLine();
        int start_idx = 0;
        int end_idx = start_idx + line_length -1;
        while(end_idx < text_line.length()){
            while(end_idx < text_line.length() && (text_line.charAt(end_idx) != ' ')){
                end_idx++;
            }
            int last_space = 0;
            if(end_idx < text_line.length() && end_idx == start_idx + line_length - 1
                    && text_line.charAt(end_idx) == ' ') last_space = 1;
            formatted_content.add(text_line.substring(start_idx,end_idx + last_space));
            start_idx = end_idx + last_space;
            end_idx = start_idx + line_length - 1;
        }
        if(start_idx < text_line.length()){
            formatted_content.add(text_line.substring(start_idx));
        }
        reader.close();
        return formatted_content;
    }

    private static void write_file(String path, int lines_page, List<String> formatted_content) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        int page_count = 0;
        for(int i = 0; i < formatted_content.size(); i++){
            writer.write(formatted_content.get(i));
            writer.newLine();
            if((i + 1) % lines_page  == 0){
                writer.write("-".repeat(20) + "Page " + ++page_count + "-".repeat(20));
                writer.newLine();
            }
        }
        writer.close();

    }

    public static void main(String[] args) {
        try {
            List<String> formatted_content = format_file(80, "./document.txt");
            write_file("./output.txt", 25, formatted_content);
        } catch (FileNotFoundException e) {
            System.err.println("No se ha encontrado el archivo ./document.txt");
        } catch (IOException e) {
            System.err.println("Error al llegir/escriure");
        }
    }



}
