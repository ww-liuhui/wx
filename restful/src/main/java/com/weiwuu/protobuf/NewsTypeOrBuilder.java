// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NewsType.proto

package com.weiwuu.protobuf;

public interface NewsTypeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.weiwuu.protobuf.NewsType)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int32 typeId = 1;</code>
   *
   * <pre>
   * ID
   * </pre>
   */
  int getTypeId();

  /**
   * <code>optional string typeName = 2;</code>
   *
   * <pre>
   * 名称
   * </pre>
   */
  String getTypeName();
  /**
   * <code>optional string typeName = 2;</code>
   *
   * <pre>
   * 名称
   * </pre>
   */
  com.google.protobuf.ByteString
      getTypeNameBytes();

  /**
   * <code>optional string typeIconUrl = 3;</code>
   *
   * <pre>
   * 图标
   * </pre>
   */
  String getTypeIconUrl();
  /**
   * <code>optional string typeIconUrl = 3;</code>
   *
   * <pre>
   * 图标
   * </pre>
   */
  com.google.protobuf.ByteString
      getTypeIconUrlBytes();

  /**
   * <code>optional int32 orderNum = 4;</code>
   *
   * <pre>
   * 排序
   * </pre>
   */
  int getOrderNum();
}