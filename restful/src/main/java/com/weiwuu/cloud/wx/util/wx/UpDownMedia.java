package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.WeixinMedia;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/***
 * 上传下载多媒体文件
 * 
 * @author hui
 *
 */
public class UpDownMedia
{
	/***
	 * 新增临时素材
	 * @param accessToken
	 *            接口访问凭证
	 * @param type
	 *            媒体文件类型（image/voice/video/thumb）
	 * @param media
	 *            媒体文件的url
	 * @return
	 */
	public static WeixinMedia uploadMedia(String accessToken, String type,
			String media) {
		WeixinMedia weixinMedia = null;
		// 拼装请求地址
		String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken)
				.replace("TYPE", type);

		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl
					.openConnection();
			uploadConn.setConnectTimeout(5000);
			uploadConn.setReadTimeout(30000);
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setUseCaches(false);
			uploadConn.setRequestMethod("POST");

			uploadConn.setRequestProperty("Connection", "Keep-Alive");
			uploadConn.setRequestProperty("Cache-Control", "no-cache");
			String boundary = "-----------------------------"+System.currentTimeMillis();
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);

			// 获取媒体文件上传的输出流(往微信服务器写数据)
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(media);
			HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl
					.openConnection();
			mediaConn.setDoOutput(true);
			mediaConn.setRequestMethod("GET");
			// 从请求头中获取内容类型
			String contentType = mediaConn.getHeaderField("Content-Type");
			System.out.println("contentType:"+contentType);
			// 根据内容类型判断文件扩展名
			String fileExt = WeixinUtil.getFileExt(contentType);

			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream
					.write(String
							.format("Content-Disposition:form-data;name=\"media\";filename=\"file1%s\"\r\n",
									fileExt).getBytes());
			outputStream.write(String.format("Content-Type:%s\r\n\r\n",
					contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(
					mediaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
			outputStream.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}","title","introduction").getBytes());
			outputStream.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());

//			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.flush();
			outputStream.close();
			bis.close();
			mediaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读取数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println("jsonObject:"+jsonObject);
			weixinMedia = new WeixinMedia();
			System.out.println("type:" + jsonObject.get("type"));
			weixinMedia.setType(jsonObject.getString("type"));
			// type等于thumb时的返回结果和其他类型不一样
			if ("thumb".equals(type)) {
				weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
			} else {
				weixinMedia.setMediaId(jsonObject.getString("media_id"));
				weixinMedia.setCreateAt(jsonObject.getInt("created_at"));
			}
		} catch (Exception e) {
			weixinMedia = null;
			e.printStackTrace();
			System.out.println("上传媒体文件失败："+e);
		}
		return weixinMedia;
	}

	/***
	 * 下载媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param mediaId
	 *            媒体文件标识
	 * @param savePath
	 *            文件在服务器上 的存储路径
	 * @return 下载文件在服务器的路径
	 * 
	 * 注：该方法不支持下载视频文件，只支持下载图片、语音和缩略图文件
	 */
	public static String getMedia(String accessToken, String mediaId,
			String savePath) {
		String fileName = null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"MEDIA_ID", mediaId);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = WeixinUtil.getFileExt(conn
					.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			fileName =  mediaId + fileExt;

			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(savePath + mediaId + fileExt));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();

			conn.disconnect();
			//log.info("下载媒体文件成功，filePath=" + filePath);
			System.out.println("success，fileName=" + fileName);
		} catch (Exception e) {
			fileName = null;
			e.printStackTrace();
			//log.error("下载媒体文件失败：{}" + e);
			System.out.println("fail");
		}
		return fileName;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa33c55ef7bf07dc0";
		// 第三方用户唯一凭证密钥
		String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";
		// 获取接口访问凭证
		String accessToken = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken();
		System.out.println(accessToken);
//		// 上传多媒体文件
//		WeixinMedia weixinMedia = uploadMedia(
//				accessToken,
//				"image",
//				"http://weiwuu-images.oss-cn-beijing.aliyuncs.com/garden/2c92581d-1fba-4025-8c71-6f1397072440.png");
//		System.out.println("id:" + weixinMedia.getMediaId());
//		System.out.println(weixinMedia.getType());
//		System.out.println(weixinMedia.getCreateAt());

//		// 下载多媒体文件
//		String fileName = getMedia(accessToken, weixinMedia.getMediaId(), "d:\\download");
//		System.out.println("文件名："+fileName);
	}
}
