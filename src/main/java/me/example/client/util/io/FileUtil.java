package me.example.client.util.io;

import lombok.experimental.UtilityClass;

import java.io.*;

@UtilityClass
public class FileUtil {

    public static void write(String content, File file) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            printWriter.println(content);
            printWriter.close();

        } catch (IOException ignored) {
        }
    }

    public static BufferedReader read(File file) {
        try {
            return new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException ignored) {
        }

        return null;
    }

}
