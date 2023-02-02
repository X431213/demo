package org.example.utils.pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedMap;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class test {
    //    获取哈希值
    public static void main(String[] args) {
        /*try {
            Process p = Runtime.getRuntime().exec("cmd.exe /c cd C:\\Users\\Administrator\\Desktop\\物模型打包\\bbs\\bss-processor-blu103 && c: && certutil -hashfile plugin-audio-bss-processor-blu103-0.0.1-SNAPSHOT.jar md5 " );
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            String[] split = new String(sb).split("\n");
            System.out.println("\n"+split[1]);

            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        try {
            System.out.println(new File("").getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    public static void main(String[] args) {
//        try {
//            ZipFile zipFile=new ZipFile("C:\\Users\\Administrator\\Desktop\\物模型打包\\bbs\\bss-processor-blu103\\plugin-audio-bss-processor-blu103-0.0.1-SNAPSHOT.jar");
//            zipFile.extractAll("C:\\Users\\Administrator\\Desktop\\物模型打包\\bbs\\bss-processor-blu103\\1");
//        } catch (ZipException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

