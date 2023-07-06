package org.example.utils.pack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

@Data
public class MakeVersion {
    private String version;
    private String manufacturer;
    private String model;
    private String driverId;
    private String name;
    @Data
    private static   class Version{
        private Integer mainVersion;
        private Integer minorVersion;
        private final Integer fileType=101;
        private String fileName;
        private String manufacturer;
        private String model;
        private String pluginId;
        private String pluginName;
        private String md5;
    }

    public static void pack(String fileName) {
        try {
            String nowDir = new File("").getCanonicalPath();
            FileInputStream fileInputStream=new FileInputStream(new File("").getCanonicalPath()+"\\description.json");
            String content = IOUtils.toString(fileInputStream,"UTF-8");
            MakeVersion makeVersion = new Gson().fromJson(content, MakeVersion.class);
            Version ve=new Version();
            ve.setMainVersion(Integer.valueOf(makeVersion.getVersion().substring(0,1)));
            ve.setMinorVersion(Integer.valueOf(makeVersion.getVersion().substring(2,3)));

            Process p = Runtime.getRuntime().exec("cmd.exe /c cd "+nowDir+" && c: && certutil -hashfile "+ fileName+" md5 " );
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
            String line ;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            String[] split = new String(sb).split("\n");

            reader.close();

            String[] s = makeVersion.getDriverId().split("_");
            String filename="";
            for (String a:s) {
                filename+=a+"-";
            }
            ve.setFileName("plugin-"+filename+"0.0.1-SNAPSHOT.jar");//x
            ve.setManufacturer(makeVersion.getManufacturer());
            ve.setModel(makeVersion.getModel());
            ve.setPluginId(makeVersion.getDriverId());
            ve.setPluginName(makeVersion.getName());
            ve.setMd5(split[1]);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();//跟new一个Gson对象同理
            String result = gson.toJson(ve);

            File file = new File(nowDir+"\\version.json");
            if(file.exists()){
                file.delete();
            }
            if(file.createNewFile()){
                FileUtils.writeStringToFile(file,result,"UTF-8");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void postprocessor() {
        //删除一个目录，需要先删文件
    }

}
