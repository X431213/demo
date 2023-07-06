package org.example.design_pattern.obser.demo4.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BuffUtil {
    /**
     * @description:T0D0
     * 清空缓冲区
     * @renturn
     * @data: 2023/7/6 16:10
     */
    public static void ClearBuff(InputStream in) {
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(in, "GBK"));
            while ((buff.readLine()) != null) ;
            in.close();
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
      * @description:T0D0
      * 读取缓冲区的数据
      * @renturn String类型数据
      * @data: 2023/7/6 16:10
    */
    public static String ReadStr(InputStream in) {
        String len;
        String result = "";
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(in, "GBK"));
            while ((len = buff.readLine()) != null) {
                result += len;
            }
            in.close();
            buff.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
