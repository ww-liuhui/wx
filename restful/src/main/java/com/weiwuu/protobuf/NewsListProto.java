// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NewsList.proto

package com.weiwuu.protobuf;

public final class NewsListProto {
  private NewsListProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_weiwuu_protobuf_NewsList_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_weiwuu_protobuf_NewsList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\016NewsList.proto\022\023com.weiwuu.protobuf\032\016N" +
      "ewsType.proto\032\rNewsTag.proto\032\017NewsTopic." +
      "proto\032\nNews.proto\032\020NewsFollow.proto\"\217\002\n\010" +
      "NewsList\0223\n\014newsTypeList\030\001 \003(\0132\035.com.wei" +
      "wuu.protobuf.NewsType\0221\n\013newsTagList\030\002 \003" +
      "(\0132\034.com.weiwuu.protobuf.NewsTag\0225\n\rnews" +
      "TopicList\030\003 \003(\0132\036.com.weiwuu.protobuf.Ne" +
      "wsTopic\022+\n\010newsList\030\004 \003(\0132\031.com.weiwuu.p" +
      "rotobuf.News\0227\n\016newsFollowList\030\005 \003(\0132\037.c" +
      "om.weiwuu.protobuf.NewsFollowBL\n\023com.wei",
      "wuu.protobufB\rNewsListProtoP\001\240\001\001\242\002\003WPB\252\002" +
      "\032Weiwuu.Protobuf.KnownTypesb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          NewsTypeProto.getDescriptor(),
          NewsTagProto.getDescriptor(),
          NewsTopicProto.getDescriptor(),
          NewsProto.getDescriptor(),
          NewsFollowProto.getDescriptor(),
        }, assigner);
    internal_static_com_weiwuu_protobuf_NewsList_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_weiwuu_protobuf_NewsList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_weiwuu_protobuf_NewsList_descriptor,
        new String[] { "NewsTypeList", "NewsTagList", "NewsTopicList", "NewsList", "NewsFollowList", });
    NewsTypeProto.getDescriptor();
    NewsTagProto.getDescriptor();
    NewsTopicProto.getDescriptor();
    NewsProto.getDescriptor();
    NewsFollowProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}