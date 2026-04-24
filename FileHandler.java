import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "siswa.csv";

    public static List<Siswa> readFile() {
        List<Siswa> list = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile(); // handle file tidak ada
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    list.add(new Siswa(data[0], data[1], data[2]));
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void writeFile(List<Siswa> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
            for (Siswa s : list) {
                bw.write(s.toCSV());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}