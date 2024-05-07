package zad3;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class Futil {

    public static void processDir(String dirName, String resultFileName) {
        try {
            Path dir = Paths.get(dirName);
            Path resultFile = Paths.get(resultFileName);
            BufferedWriter writer = Files.newBufferedWriter(resultFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE);

            Files.walkFileTree(dir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".txt")) {
                        List<String> lines = Files.readAllLines(file, Charset.forName("Cp1250"));
                        for (String line : lines) {
                            writer.write(line);
                            writer.newLine();
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
