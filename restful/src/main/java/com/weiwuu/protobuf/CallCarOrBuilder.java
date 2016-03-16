// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CallCar.proto

package com.weiwuu.protobuf;

public interface CallCarOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.weiwuu.protobuf.CallCar)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int32 id = 1;</code>
   *
   * <pre>
   * 约车ID
   * </pre>
   */
  int getId();

  /**
   * <code>optional string time = 2;</code>
   *
   * <pre>
   * 约车时间
   * </pre>
   */
  String getTime();
  /**
   * <code>optional string time = 2;</code>
   *
   * <pre>
   * 约车时间
   * </pre>
   */
  com.google.protobuf.ByteString
      getTimeBytes();

  /**
   * <code>optional string addrFrom = 3;</code>
   *
   * <pre>
   * 上车地点
   * </pre>
   */
  String getAddrFrom();
  /**
   * <code>optional string addrFrom = 3;</code>
   *
   * <pre>
   * 上车地点
   * </pre>
   */
  com.google.protobuf.ByteString
      getAddrFromBytes();

  /**
   * <code>optional string addrTo = 4;</code>
   *
   * <pre>
   * 目的地点
   * </pre>
   */
  String getAddrTo();
  /**
   * <code>optional string addrTo = 4;</code>
   *
   * <pre>
   * 目的地点
   * </pre>
   */
  com.google.protobuf.ByteString
      getAddrToBytes();

  /**
   * <code>optional string wxuid = 11;</code>
   *
   * <pre>
   * 用户微信的unionid
   * </pre>
   */
  String getWxuid();
  /**
   * <code>optional string wxuid = 11;</code>
   *
   * <pre>
   * 用户微信的unionid
   * </pre>
   */
  com.google.protobuf.ByteString
      getWxuidBytes();

  /**
   * <code>optional string name = 12;</code>
   *
   * <pre>
   * 客户姓名
   * </pre>
   */
  String getName();
  /**
   * <code>optional string name = 12;</code>
   *
   * <pre>
   * 客户姓名
   * </pre>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>optional string tel = 13;</code>
   *
   * <pre>
   * 客户电话
   * </pre>
   */
  String getTel();
  /**
   * <code>optional string tel = 13;</code>
   *
   * <pre>
   * 客户电话
   * </pre>
   */
  com.google.protobuf.ByteString
      getTelBytes();

  /**
   * <code>optional int32 status = 21;</code>
   *
   * <pre>
   * 约车状态  0：未派车，1：已派车，2：已取消
   * </pre>
   */
  int getStatus();

  /**
   * <code>optional int32 cityId = 31;</code>
   *
   * <pre>
   *所在城市ID
   * </pre>
   */
  int getCityId();

  /**
   * <code>optional string bbid = 41;</code>
   *
   * <pre>
   *服务帮帮信息
   * 帮帮ID
   * </pre>
   */
  String getBbid();
  /**
   * <code>optional string bbid = 41;</code>
   *
   * <pre>
   *服务帮帮信息
   * 帮帮ID
   * </pre>
   */
  com.google.protobuf.ByteString
      getBbidBytes();

  /**
   * <code>optional string bbtel = 42;</code>
   *
   * <pre>
   * 帮帮电话
   * </pre>
   */
  String getBbtel();
  /**
   * <code>optional string bbtel = 42;</code>
   *
   * <pre>
   * 帮帮电话
   * </pre>
   */
  com.google.protobuf.ByteString
      getBbtelBytes();

  /**
   * <code>optional string bbname = 43;</code>
   *
   * <pre>
   * 帮帮姓名
   * </pre>
   */
  String getBbname();
  /**
   * <code>optional string bbname = 43;</code>
   *
   * <pre>
   * 帮帮姓名
   * </pre>
   */
  com.google.protobuf.ByteString
      getBbnameBytes();

  /**
   * <code>optional string carnum = 51;</code>
   *
   * <pre>
   *专车信息
   * 车牌号
   * </pre>
   */
  String getCarnum();
  /**
   * <code>optional string carnum = 51;</code>
   *
   * <pre>
   *专车信息
   * 车牌号
   * </pre>
   */
  com.google.protobuf.ByteString
      getCarnumBytes();

  /**
   * <code>optional string cartel = 52;</code>
   *
   * <pre>
   * 司机电话
   * </pre>
   */
  String getCartel();
  /**
   * <code>optional string cartel = 52;</code>
   *
   * <pre>
   * 司机电话
   * </pre>
   */
  com.google.protobuf.ByteString
      getCartelBytes();

  /**
   * <code>optional string carname = 53;</code>
   *
   * <pre>
   * 司机姓名
   * </pre>
   */
  String getCarname();
  /**
   * <code>optional string carname = 53;</code>
   *
   * <pre>
   * 司机姓名
   * </pre>
   */
  com.google.protobuf.ByteString
      getCarnameBytes();

  /**
   * <code>optional string cartype = 54;</code>
   *
   * <pre>
   * 专车型号
   * </pre>
   */
  String getCartype();
  /**
   * <code>optional string cartype = 54;</code>
   *
   * <pre>
   * 专车型号
   * </pre>
   */
  com.google.protobuf.ByteString
      getCartypeBytes();
}
