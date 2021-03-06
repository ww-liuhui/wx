// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NewsTopic.proto

package com.weiwuu.protobuf;

public interface NewsTopicOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.weiwuu.protobuf.NewsTopic)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int32 id = 1;</code>
   *
   * <pre>
   * 主题ID
   * </pre>
   */
  int getId();

  /**
   * <code>optional string topic = 2;</code>
   *
   * <pre>
   * 主题: (限制字数 &lt;= 16)
   * </pre>
   */
  String getTopic();
  /**
   * <code>optional string topic = 2;</code>
   *
   * <pre>
   * 主题: (限制字数 &lt;= 16)
   * </pre>
   */
  com.google.protobuf.ByteString
      getTopicBytes();

  /**
   * <code>optional string notes = 3;</code>
   *
   * <pre>
   * 说明: (限制字数 &lt;= 256)
   * </pre>
   */
  String getNotes();
  /**
   * <code>optional string notes = 3;</code>
   *
   * <pre>
   * 说明: (限制字数 &lt;= 256)
   * </pre>
   */
  com.google.protobuf.ByteString
      getNotesBytes();
}
