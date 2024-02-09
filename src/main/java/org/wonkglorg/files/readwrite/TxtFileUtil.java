package com.wonkglorg.FortressUtility.parser;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TxtFileUtil {

    private final File file;

    public TxtFileUtil(File file) {
        this.file = file;
    }

    public List<String> readAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            return lines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> read(int amount) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            int count = 0;
            String line;
            line = reader.readLine();
            while (line != null && amount > count) {
                count++;
                lines.add(line);
                line = reader.readLine();
            }

            return lines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void write(String... text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : text) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Path scanForPath(String path, String name) {
        File[] files = new File(path).listFiles();
        if (files == null) {
            throw new RuntimeException("No files found in " + path);
        }
        for (File file : files) {
            if (file.getName().equals(name)) {
                return file.toPath();
            }
        }
        throw new RuntimeException("No file found with name " + name + " in " + path);
    }

    public File scanForFile(String path, String name) {
        File[] files = new File(path).listFiles();
        if (files == null) {
            throw new RuntimeException("No files found in " + path);
        }
        for (File file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        throw new RuntimeException("No file found with name " + name + " in " + path);
    }


    public static void writeToFile(String path, String... text) {
        new TxtFileUtil(new File(path)).write(text);
    }

    public static void writeToFile(File file, String... text) {
        new TxtFileUtil(file).write(text);
    }

    public static void writeToFile(String path, List<String> text) {
        new TxtFileUtil(new File(path)).write(text.toArray(new String[0]));
    }

    public static void reeadFromFile(String path) {
        new TxtFileUtil(new File(path)).readAll();
    }

    public static void reeadFromFile(File file) {
        new TxtFileUtil(file).readAll();
    }

    public static void reeadFromFile(String path, int amount) {
        new TxtFileUtil(new File(path)).read(amount);
    }
}
