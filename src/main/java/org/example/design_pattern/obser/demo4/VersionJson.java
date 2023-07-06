package org.example.design_pattern.obser.demo4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.example.design_pattern.obser.demo4.Utils.BuffUtil;

import java.io.*;

@Data
public class VersionJson {
    private String pluginName;
    private String version;
    private String manufacturer;
    private String model;
    private String driverId;
    private String name;

    @Data
    protected class Version {
        private Integer mainVersion;
        private Integer minorVersion;
        private final Integer fileType = 101;
        private String fileName;
        private String manufacturer;
        private String model;
        private String pluginId;
        private String pluginName;
        private String md5;
    }

    /**
     * @description:T0D0 No such property: code for class: Script1
     * @renturn
     * @data: 2023/7/6 16:09
     */
    public void ToJsonFile(String pluginName) {
        try {

            //解压
            Process exec = Runtime.getRuntime().exec("cmd.exe /c start D:\\7-Zip\\7z.exe x " + pluginName);
            exec.waitFor();
            BuffUtil.ClearBuff(exec.getInputStream());//当缓冲区满了不及时释放，waitFor会阻塞线程,还可以用于判断是否执行完


            //description.Json转对象
            String nowDir = new File("").getCanonicalPath();
            FileInputStream fileInputStream = new FileInputStream(nowDir + "\\description.json");
            String content = IOUtils.toString(fileInputStream, "UTF-8");
            fileInputStream.close();
            VersionJson versionJson = new Gson().fromJson(content, VersionJson.class);

            //赋值
            Version version = new Version();
            version.setMainVersion(Integer.valueOf(versionJson.getVersion().substring(0, 1)));
            version.setMinorVersion(Integer.valueOf(versionJson.getVersion().substring(2, 3)));
            version.setManufacturer(versionJson.getManufacturer());
            version.setModel(versionJson.getModel());
            version.setFileName(pluginName);
            version.setPluginId(versionJson.getDriverId());
            version.setPluginName(versionJson.getName());

//              获取md5并赋值
            Process md5Exe = Runtime.getRuntime().exec("cmd.exe /c certutil -hashfile " + pluginName + " md5 ");
            md5Exe.waitFor();
            String md5Str = BuffUtil.ReadStr(md5Exe.getInputStream());
            md5Str = md5Str.substring(md5Str.indexOf("哈希:") + 3, md5Str.indexOf("CertUtil"));
            System.out.println(md5Str);
            version.setMd5(md5Str);


            //创建并写入 version.json文件
            Gson gson = new GsonBuilder().setPrettyPrinting().create();//跟new一个Gson对象同理
            String result = gson.toJson(version);

            String canonicalPath = new File("").getCanonicalPath() + "\\version.json";
            File file = new File(canonicalPath);
            if (file.exists()) {
                file.delete();
            }
            if (file.createNewFile()) {
                FileUtils.writeStringToFile(file, result, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
