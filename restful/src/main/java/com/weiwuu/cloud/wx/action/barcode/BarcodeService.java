package com.weiwuu.cloud.wx.action.barcode;

import com.weiwuu.cloud.wx.util.wx.ImageUtils;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 条形码查询
 * 
 * @author liuhui
 * @date 2014-01-21
 * 
 */
public class BarcodeService
{

	/**
	 * 发起http get请求获取网页源代码
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = null;

		try {
			// 建立连接
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");

			// 获取输入流
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			// 读取返回结果
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 封装根据条形码查询产品的方法，供外部调用
	 * 
	 * @return
	 */
	public static JSONObject getFromBarcode(String code) {
		// 获取网页源代码
		String html = httpRequest("http://www.liantu.com/tiaoma/query.php?ean="
				+ code);
		// System.out.println("hrml:"+html);
		JSONObject jsonObj = null;
		if (!html.equals("false")) {
			jsonObj = JSONObject.fromObject(html);
		}
		//
		// String name = jsonObj.getString("name");
		// String fac_name = jsonObj.getString("fac_name");
		// String ean = jsonObj.getString("ean");
		// String guobie = jsonObj.getString("guobie");
		// String price = jsonObj.getString("price");
		// String supplier = jsonObj.getString("supplier");
		// String fac_status = jsonObj.getString("fac_status");
		// String titleSrc = jsonObj.getString("titleSrc");//商品名称和规格的图片
		//
		// System.out.println(html);
		// System.out.println(name);
		// System.out.println(ean);
		// System.out.println(guobie);
		// System.out.println(price);
		// System.out.println(supplier);
		// System.out.println(fac_status);
		// System.out.println(fac_name);
		// System.out.println("titleSrc:"+titleSrc);

		return jsonObj;
	}

	/**
	 * 通过main在本地测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long code = 6900000123071l;
		while (code < 6999999999999l) {
			JSONObject jsonObj = null;
			jsonObj = getFromBarcode(code + "");
			String ean = "";
			String guobie = "中国";
			String price = "0";
			String supplier = "";
			String fac_status = "";
			String titleSrc = "";
			if (jsonObj != null) {
				try {
					ean = jsonObj.getString("ean");
					guobie = jsonObj.getString("guobie");
					price = jsonObj.getString("price");
					supplier = jsonObj.getString("supplier");
					fac_status = jsonObj.getString("fac_status");
					titleSrc = jsonObj.getString("titleSrc");// 商品名称和规格的图片

					if (!titleSrc.equals("")) {
						// 图片名称
						String picName = ean + ".png";
						try {
							ImageUtils.download(titleSrc, picName, "d:/codepic/");
						} catch (Exception e) {
						} finally {
							// 放大图片
							String picUrl = "d:/codepic/" + picName;
							ImageUtils.scale(picUrl, picUrl, 3 / 2, true);
							// 6-给图片添加图片水印：
							ImageUtils.pressImage(picUrl, "d:/test/2.jpg",
									picUrl, 0, 0, 1);// 测试OK
						}
					}

				} catch (Exception e) {
					// e.printStackTrace();
				} finally {

				}
			}
			code++;
			System.out.println(code);
		}

	}
}