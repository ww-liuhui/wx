// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GardenPriceItem.proto

package com.weiwuu.protobuf;

public interface GardenPriceItemOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.weiwuu.protobuf.GardenPriceItem)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string month = 1;</code>
   *
   * <pre>
   * 年月: yyyy-MM  (2015-12)
   * </pre>
   */
  String getMonth();
  /**
   * <code>optional string month = 1;</code>
   *
   * <pre>
   * 年月: yyyy-MM  (2015-12)
   * </pre>
   */
  com.google.protobuf.ByteString
      getMonthBytes();

  /**
   * <code>optional int32 price = 2;</code>
   *
   * <pre>
   * 价格: 9999999  (7位整数: &gt;= 0 and &lt;= 9999999)
   * </pre>
   */
  int getPrice();
}