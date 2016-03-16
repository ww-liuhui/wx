package com.weiwuu.cloud.wx.util.wx;

import org.apache.http.client.ClientProtocolException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hui on 2015/11/26.
 */
public class Test
{
    public static String postFile(String url, String filePath,
                                  String title,String introduction) {
        File file = new File(filePath);
        if(!file.exists())
            return null;
        String result = null;
        try {
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            String boundary = "-----------------------------"+System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);

            OutputStream output = conn.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
            output.write(String.format("Content-Type: %s \r\n\r\n",WeixinUtil.getContentType(file.getName())).getBytes());

            System.out.println("str:"+String.format("Content-Type: %s \r\n\r\n", WeixinUtil.getContentType(file.getName
                    ())));

            byte[] data = new byte[1024];
            int len =0;
            FileInputStream input = new FileInputStream(file);
            while((len=input.read(data))>-1){
                output.write(data, 0, len);
            }
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
            output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}",title,introduction).getBytes());
            output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
            output.flush();
            output.close();
            input.close();
            InputStream resp = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            while((len= resp.read(data))>-1)
                sb.append(new String(data,0,len,"utf-8"));
            resp.close();
            result = sb.toString();
            System.out.println(result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
//            logger.error("postFile，不支持http协议",e);
        } catch (IOException e) {
//            logger.error("postFile数据传输失败",e);
            e.printStackTrace();
        }
//        logger.info("{}: result={}",url,result);
        return result;
    }

    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
                + "ZV2Oma39wkm1Ion9YXx91Csa_m-VaTfaQf2gR-gY0f_vKwj729DemM8lwYhbOiSR20BwsxiR7trKMpDCFT_inYk5yiTujsJ39ZUJd5FBV1UCTUcACAXOP&type=image";
        String rs = postFile(url, "d:\\test\\4.jpg","","");
        System.out.println(rs);
    }
}
