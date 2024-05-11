package zad3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName), StandardCharsets.UTF_8));
            FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path visitedFilePath, BasicFileAttributes fileAttributes) throws IOException {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(visitedFilePath.toFile()), "Windows-1250"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            bw.write(line + "\n");
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }
            };

            FileSystem fileSystem = FileSystems.getDefault();
            Path path = fileSystem.getPath(dirName);
            Files.walkFileTree(path, fv);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
