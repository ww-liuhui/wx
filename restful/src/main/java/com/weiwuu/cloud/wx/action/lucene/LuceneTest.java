package com.weiwuu.cloud.wx.action.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * Lucene的基本使用示例
 * 
 * @author hui
 *
 */
public class LuceneTest {
	// 索引存储的位置
	private String indexDir = "d:/indexDir";
	// Field名称
	private String fieldName = "content";

	/***
	 * 创建索引
	 * 
	 * @param analyzer
	 *            分词器
	 * @throws IOException
	 */
	public void createIndex(Analyzer analyzer) throws IOException {
		// 待索引的文本数据
		String[] contentArr = { "考进清华北大是许多人的梦 想", "清华是中国著名的高等学府",
				"清华是世界上最美丽的大学之一", "一个上进的人一定要有梦想", "梦想上清华" };
		// 创建或打开索引目录
		Directory directory = FSDirectory.open(new File(indexDir));
		// 创建IndexWriter
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46,
				analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, config);
		// 遍历数组创建索引
		for (String text : contentArr) {
			// 创建document并添加field
			Document document = new Document();
			document.add(new TextField(fieldName, text, Field.Store.YES));
			// 将document添加到索引中
			indexWriter.addDocument(document);
		}
		indexWriter.commit();
		indexWriter.close();
		directory.close();
	}

	/***
	 * 从索引中检索
	 * 
	 * @param sentence
	 *            检索语句
	 * @param analyzer
	 *            分词器
	 * @throws Exception
	 */
	public List<String> searchIndex(String sentence, Analyzer analyzer)
			throws Exception {
		// 检索结果列表
		List<String> list = new ArrayList<String>();

		// 创建或打开索引目录
		Directory directory = FSDirectory.open(new File(indexDir));
		IndexReader reader = IndexReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		// 使用检查解析器创建Query
		QueryParser parser = new QueryParser(Version.LUCENE_46, fieldName,
				analyzer);
		Query query = parser.parse(sentence);
		// 输出解析后的查询语句
		System.out.println("查询语句：" + query.toString());
		// 从索引中搜索得分排名前10的文档
		TopDocs topDocs = searcher.search(query, 10);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		System.out.println("共检索到：" + topDocs.totalHits + "条匹配结果");
		for (ScoreDoc sd : scoreDocs) {
			// 根据id获取document
			Document d = searcher.doc(sd.doc);
			System.out.println(d.get(fieldName) + "  score:" + sd.score);
			// 添加检索结果
			list.add(d.get(fieldName));
			// 查看文档得分解析
			// System.out.println(searcher.explain(query, sd.doc));
		}
		reader.close();
		directory.close();
		return list;
	}

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

	public static void main(String[] args) {
		// 创建分词器
		Analyzer analyzer = new IKAnalyzer(true);

		LuceneTest luceneTest = new LuceneTest();

		try {
			// 创建索引目录
			File indexDir = new File("d:/indexDir");
			if (!indexDir.exists()) {
				System.out.println(11);
				luceneTest.createIndex(analyzer);
			}
			// 从搜索中检索
			luceneTest.searchIndex("梦想上清华", analyzer);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
