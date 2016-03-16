package com.weiwuu.cloud.wx.action.lucene;

import com.weiwuu.cloud.wx.action.translate.BaiduTranslateService;
import com.weiwuu.cloud.wx.domain.wx.Knowledge;
import com.weiwuu.cloud.wx.util.wx.RobotUtil;
import net.sf.json.JSONObject;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Random;

/***
 * 聊天服务类
 * 
 * @author hui
 *
 */
public class ChatService {

	/***
	 * 获得索引存储目录
	 * 
	 * @return
	 */
	public static String getIndexDir() {
		// 得到.calss文件所在路径（web-inf/classes/）
		String classpath = ChatService.class.getResource("/").getPath();
		// 将classpath中的%20替换为空格
		classpath = classpath.replace("%20", "");
		// 索引存储位置：WEB-INF/classes/index/
		return classpath + "index/";
	}

	public static void createIndex() {
		// 获得问答知识库张的所有记录
		List<Knowledge> knowledgeList = RobotUtil.findAllKnowledge();
		Directory directory = null;
		IndexWriter indexWriter = null;
		try {
			directory = FSDirectory.open(new File(getIndexDir()));
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_47, new IKAnalyzer(true));
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			Document doc = null;
			// 遍历问答知识库创建索引
			for (Knowledge knowledge : knowledgeList) {
				doc = new Document();
				// 对question进行分词存储
				doc.add(new TextField("question", knowledge.getQuestion(),
						Store.YES));
				// 对id、answer和category不分词存储
				doc.add(new IntField("id", knowledge.getId(), Store.YES));
				doc.add(new StringField("answer", knowledge.getAnswer(),
						Store.YES));
				doc.add(new IntField("category", knowledge.getCategory(),
						Store.YES));
				indexWriter.addDocument(doc);
			}
			indexWriter.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 从索引文件中根据问题检索答案
	 * 
	 * @param content
	 * @return
	 */
	private static Knowledge searchIndex(String content) {
		Knowledge knowledge = null;
		try {
			Directory directory = FSDirectory.open(new File(getIndexDir()));
			IndexReader indexReader = IndexReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			// 使用查询解析器创建Query
			QueryParser queryParser = new QueryParser(Version.LUCENE_47,
					"question", new IKAnalyzer(true));
			Query query = queryParser.parse(QueryParser.escape(content));
			// 检索得分最高的文档
			TopDocs topDocs = indexSearcher.search(query, 1);
			if (topDocs.totalHits > 0) {
				knowledge = new Knowledge();
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
				for (ScoreDoc sd : scoreDocs) {
					Document doc = indexSearcher.doc(sd.doc);
					knowledge.setId(doc.getField("id").numericValue()
							.intValue());
					knowledge.setQuestion(doc.get("question"));
					knowledge.setAnswer(doc.get("answer"));
					knowledge.setCategory(doc.getField("category")
							.numericValue().intValue());
				}
			}
			indexReader.close();
			directory.close();

		} catch (Exception e) {
			knowledge = null;
			e.printStackTrace();
		}
		return knowledge;
	}

	/***
	 * 聊天方法
	 * 
	 * @param openId
	 *            用户标识
	 * @param createTime
	 *            消息创建时间
	 * @param question
	 *            用户上行的问题
	 * @return
	 */
	public static String chat(String openId, String createTime, String question) {
		String answer = null;
		int chatCategory = 0;
		Knowledge knowledge = null;
//		knowledge = searchIndex(question);
		// 找到匹配项
		if (null != knowledge) {
			// 笑话
			if (2 == knowledge.getCategory()) {
				answer = RobotUtil.getJoke();
				chatCategory = 2;
			}
			// 上下文
			else if (3 == knowledge.getCategory()) {
				// 判断上一次聊天类别
				int category = RobotUtil.getLastCategory(openId);
				if (2 == category) {
					answer = RobotUtil.getJoke();
					chatCategory = 2;
				} else {
					answer = knowledge.getAnswer();
					chatCategory = knowledge.getCategory();
				}
			}
			// 普通对话
			else {
				answer = knowledge.getAnswer();
				// 如果答案为空，根据知识id从问答知识分表中随机获取一条
				if ("".equals(answer)) {
					answer = RobotUtil.getKnowledSub(knowledge.getId());
				}
				chatCategory = 1;
			}
		}
		// 未找到匹配项
		else {
			// 组装地址 :调用小黄鸭接口
			String requestUrl = "http://sandbox.api.simsimi.com/request.p?key=4c870d7f-0104-4db4-a899-41d4111f7ac8&lc=ch&ft=1.0&text=TEXT";
			requestUrl = requestUrl.replace("TEXT",
					BaiduTranslateService.urlEncodeUTF8(question));
			System.out.println(requestUrl);
			try {
				// 通过simsimi小黄鸭接口获取答案
				URL ur = new URL(requestUrl);
				InputStream inputStream = ur.openStream();

				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream, "utf-8");
				String s;
				BufferedReader in = new BufferedReader(inputStreamReader);
				StringBuffer sb = new StringBuffer();
				while ((s = in.readLine()) != null) {
					sb.append(s);
				}
				in.close();
				inputStreamReader.close();
				inputStream.close();

				// 获取返回结果
				JSONObject jsonObject = JSONObject.fromObject(sb.toString());
				System.out.println(jsonObject);
				answer = jsonObject.getString("response");
				System.out.println(answer);
				if (null == answer || "".equals(answer)) {
					answer = getDefaultAnswer();
					chatCategory = 1;
				}
			} catch (Exception e) {
				answer = getDefaultAnswer();
				//e.printStackTrace();
			}
		}
		// 保存聊天记录

		RobotUtil.saveChatLog(openId, createTime, question, answer,
				chatCategory);
		return answer;
	}

	/***
	 * 随机获取一个默认的答案
	 * 
	 * @return
	 */
	private static String getDefaultAnswer() {
		String[] answer = { "我们聊点别的吧", "听的我一头雾水，说点在我智商范围内的", "智商不足，充值中......",
				"我语文是体育老师教的，理解不了哦", "虽然听不懂你的意思，但我却能用心感受", "额..啊..这个..",
				"你这个问题差点让我直接死机哦", "亲，不要这么难为人家嘛" };
		return answer[getRandomNumber(answer.length)];
	}

	/***
	 * 随机生成0~length-1之间的某个值
	 * 
	 * @param length
	 * @return
	 */
	private static int getRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}

	public static void main(String[] args) {
		String rs = chat("1", "2015.11.24", "你好");
		System.out.println(rs);
	}
}
