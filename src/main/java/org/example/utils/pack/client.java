package org.example.utils.pack;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

public class client {
    private static String fileName;
    public static void main(String[] args) {
        decompression();
        MakeVersion.pack(fileName);
    }
    private static void decompression() {
        try {
            String nowDir = new File("").getCanonicalPath();
            File file = new File(nowDir);
            if(file.isDirectory()) {
                File[] fs = file.listFiles();
                if(fs != null) {
                    for (File f : fs) {
                        if (f.isDirectory()) {
                            continue;
                        }
                         fileName = f.getName();
                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                        if (suffix.equals("jar")&&!fileName.equals("Pack.jar")) {
                            ZipFile zipFile=new ZipFile(f);
                            zipFile.extractAll(nowDir);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
