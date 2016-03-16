package com.weiwuu.cloud.wx.util.wx;


import com.weiwuu.cloud.wx.domain.wx.Knowledge;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/***
 * 聊天机器人工具类
 * 
 * @author hui
 *
 */
public class RobotUtil
{

	/***
	 * 获取问答知识表中所有记录
	 * 
	 * @return
	 */
	public static List<Knowledge> findAllKnowledge() {
		List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
//		String sql = "select * from knowledge";
//		DataBaseUtil db = new DataBaseUtil();
//		ResultSet rs = null;
//		try {
//			rs = db.query(sql);
//			while (rs.next()) {
//				Knowledge knowledge = new Knowledge();
//				knowledge.setId(rs.getInt("id"));
//				knowledge.setQuestion(rs.getString("question"));
//				knowledge.setAnswer(rs.getString("answer"));
//				knowledge.setCategory(rs.getInt("category"));
//				knowledgeList.add(knowledge);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			db.closeConn();
//		}
		return knowledgeList;
	}

	/***
	 * 获取上一次聊天类别
	 * 
	 * @param openId
	 *            用户标识
	 * @return
	 */
	public static int getLastCategory(String openId) {
		int chatCategory = -1;
		String sql = "select chat_category from chat_log where open_id='"
				+ openId + "' limit 0,1";
//		DataBaseUtil db = new DataBaseUtil();
//		ResultSet rs = null;
//		try {
//			rs = db.query(sql);
//			if (rs.next()) {
//				chatCategory = rs.getInt("chat_category");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			db.closeConn();
//		}
		return chatCategory;
	}

	/***
	 * 根据知识id随机获取一个答案
	 * 
	 * @param knowledgeId
	 *            知识id
	 * @return
	 */
	public static String getKnowledSub(int knowledgeId) {
		String knowAnswer = "";
		String sql = "select answer from knowledge_sub where pid="
				+ knowledgeId + " order by rand() limit 0,1";
//		DataBaseUtil db = new DataBaseUtil();
//		ResultSet rs = null;
//		try {
//			rs = db.query(sql);
//			if (rs.next()) {
//				knowAnswer = rs.getString("answer");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			db.closeConn();
//		}
		return knowAnswer;
	}

	/***
	 * 随机获取一条笑话
	 * 
	 * @return
	 */
	public static String getJoke() {
		String jokeContent = "";
		String sql = "select joke_content from joke as t1 join(select round(rand()*(select max(joke_id) from joke)) as joke_id) as t2 where t1.joke_id>=t2.joke_id order by t1.joke_id asc limit 1";
//		DataBaseUtil db = new DataBaseUtil();
//		ResultSet rs = null;
//		try {
//			rs = db.query(sql);
//			if (rs.next()) {
//				jokeContent = rs.getString("joke_content");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			db.closeConn();
//		}
		return jokeContent;
	}

	/***
	 * 保存聊天记录
	 * 
	 * @param openId
	 *            用户标识
	 * @param createTime
	 *            消息创建时间
	 * @param reqMsg
	 *            用户上行的消息
	 * @param respMsg
	 *            公众号回复的消息
	 * @param chatCategory
	 *            聊天类别
	 */
	public static void saveChatLog(String openId, String createTime,
			String reqMsg, String respMsg, int chatCategory) {
		String sql = "insert into chat_log(open_id,create_time,req_msg,resp_msg,chat_category) values(?,?,?,?,?)";
//		DataBaseUtil db = new DataBaseUtil();
//		PreparedStatement ps = null;
//		try {
//			ps = db.getPstmt(sql);
//			ps.setString(1, openId);
//			ps.setString(2, createTime);
//			ps.setString(3, reqMsg);
//			ps.setString(4, respMsg);
//			ps.setInt(5, chatCategory);
//			ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			db.closeConn();
//		}
	}

	public static void main(String[] args) {
//		File indexDir = new File(ChatService.getIndexDir());
//		System.out.println(indexDir);
//		// 如果索引目录不存在则创建索引
//		if (!indexDir.exists()) {
//			ChatService.createIndex();
//		}
	}
}
