// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NewsList.proto

package com.weiwuu.protobuf;

public interface NewsListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.weiwuu.protobuf.NewsList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .com.weiwuu.protobuf.NewsType newsTypeList = 1;</code>
   *
   * <pre>
   * 分类列表
   * </pre>
   */
  java.util.List<NewsType>
      getNewsTypeListList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsType newsTypeList = 1;</code>
   *
   * <pre>
   * 分类列表
   * </pre>
   */
  NewsType getNewsTypeList(int index);
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsType newsTypeList = 1;</code>
   *
   * <pre>
   * 分类列表
   * </pre>
   */
  int getNewsTypeListCount();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsType newsTypeList = 1;</code>
   *
   * <pre>
   * 分类列表
   * </pre>
   */
  java.util.List<? extends NewsTypeOrBuilder>
      getNewsTypeListOrBuilderList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsType newsTypeList = 1;</code>
   *
   * <pre>
   * 分类列表
   * </pre>
   */
  NewsTypeOrBuilder getNewsTypeListOrBuilder(int index);

  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTag newsTagList = 2;</code>
   *
   * <pre>
   * 标签列表
   * </pre>
   */
  java.util.List<NewsTag>
      getNewsTagListList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTag newsTagList = 2;</code>
   *
   * <pre>
   * 标签列表
   * </pre>
   */
  NewsTag getNewsTagList(int index);
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTag newsTagList = 2;</code>
   *
   * <pre>
   * 标签列表
   * </pre>
   */
  int getNewsTagListCount();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTag newsTagList = 2;</code>
   *
   * <pre>
   * 标签列表
   * </pre>
   */
  java.util.List<? extends NewsTagOrBuilder>
      getNewsTagListOrBuilderList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTag newsTagList = 2;</code>
   *
   * <pre>
   * 标签列表
   * </pre>
   */
  NewsTagOrBuilder getNewsTagListOrBuilder(int index);

  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTopic newsTopicList = 3;</code>
   *
   * <pre>
   * 主题列表
   * </pre>
   */
  java.util.List<NewsTopic>
      getNewsTopicListList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTopic newsTopicList = 3;</code>
   *
   * <pre>
   * 主题列表
   * </pre>
   */
  NewsTopic getNewsTopicList(int index);
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTopic newsTopicList = 3;</code>
   *
   * <pre>
   * 主题列表
   * </pre>
   */
  int getNewsTopicListCount();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTopic newsTopicList = 3;</code>
   *
   * <pre>
   * 主题列表
   * </pre>
   */
  java.util.List<? extends NewsTopicOrBuilder>
      getNewsTopicListOrBuilderList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsTopic newsTopicList = 3;</code>
   *
   * <pre>
   * 主题列表
   * </pre>
   */
  NewsTopicOrBuilder getNewsTopicListOrBuilder(int index);

  /**
   * <code>repeated .com.weiwuu.protobuf.News newsList = 4;</code>
   *
   * <pre>
   * 资讯列表
   * </pre>
   */
  java.util.List<News>
      getNewsListList();
  /**
   * <code>repeated .com.weiwuu.protobuf.News newsList = 4;</code>
   *
   * <pre>
   * 资讯列表
   * </pre>
   */
  News getNewsList(int index);
  /**
   * <code>repeated .com.weiwuu.protobuf.News newsList = 4;</code>
   *
   * <pre>
   * 资讯列表
   * </pre>
   */
  int getNewsListCount();
  /**
   * <code>repeated .com.weiwuu.protobuf.News newsList = 4;</code>
   *
   * <pre>
   * 资讯列表
   * </pre>
   */
  java.util.List<? extends NewsOrBuilder>
      getNewsListOrBuilderList();
  /**
   * <code>repeated .com.weiwuu.protobuf.News newsList = 4;</code>
   *
   * <pre>
   * 资讯列表
   * </pre>
   */
  NewsOrBuilder getNewsListOrBuilder(int index);

  /**
   * <code>repeated .com.weiwuu.protobuf.NewsFollow newsFollowList = 5;</code>
   *
   * <pre>
   * 评论列表
   * </pre>
   */
  java.util.List<NewsFollow>
      getNewsFollowListList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsFollow newsFollowList = 5;</code>
   *
   * <pre>
   * 评论列表
   * </pre>
   */
  NewsFollow getNewsFollowList(int index);
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsFollow newsFollowList = 5;</code>
   *
   * <pre>
   * 评论列表
   * </pre>
   */
  int getNewsFollowListCount();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsFollow newsFollowList = 5;</code>
   *
   * <pre>
   * 评论列表
   * </pre>
   */
  java.util.List<? extends NewsFollowOrBuilder>
      getNewsFollowListOrBuilderList();
  /**
   * <code>repeated .com.weiwuu.protobuf.NewsFollow newsFollowList = 5;</code>
   *
   * <pre>
   * 评论列表
   * </pre>
   */
  NewsFollowOrBuilder getNewsFollowListOrBuilder(int index);
}
