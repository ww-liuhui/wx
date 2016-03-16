// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WXUser.proto

package com.weiwuu.protobuf;

public interface WXUserOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.weiwuu.protobuf.WXUser)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string unionid = 1;</code>
   *
   * <pre>
   *unionid
   * </pre>
   */
  String getUnionid();
  /**
   * <code>optional string unionid = 1;</code>
   *
   * <pre>
   *unionid
   * </pre>
   */
  com.google.protobuf.ByteString
      getUnionidBytes();

  /**
   * <code>optional int32 subscribe = 2;</code>
   *
   * <pre>
   * 关注状态
   * </pre>
   */
  int getSubscribe();

  /**
   * <code>optional string subscribeTime = 3;</code>
   *
   * <pre>
   * 关注时间
   * </pre>
   */
  String getSubscribeTime();
  /**
   * <code>optional string subscribeTime = 3;</code>
   *
   * <pre>
   * 关注时间
   * </pre>
   */
  com.google.protobuf.ByteString
      getSubscribeTimeBytes();

  /**
   * <code>optional string nickname = 4;</code>
   *
   * <pre>
   * 昵称
   * </pre>
   */
  String getNickname();
  /**
   * <code>optional string nickname = 4;</code>
   *
   * <pre>
   * 昵称
   * </pre>
   */
  com.google.protobuf.ByteString
      getNicknameBytes();

  /**
   * <code>optional int32 sex = 5;</code>
   *
   * <pre>
   * 性别 1男，2女，0未知
   * </pre>
   */
  int getSex();

  /**
   * <code>optional string country = 6;</code>
   *
   * <pre>
   * 所在国家
   * </pre>
   */
  String getCountry();
  /**
   * <code>optional string country = 6;</code>
   *
   * <pre>
   * 所在国家
   * </pre>
   */
  com.google.protobuf.ByteString
      getCountryBytes();

  /**
   * <code>optional string province = 7;</code>
   *
   * <pre>
   * 所在省份
   * </pre>
   */
  String getProvince();
  /**
   * <code>optional string province = 7;</code>
   *
   * <pre>
   * 所在省份
   * </pre>
   */
  com.google.protobuf.ByteString
      getProvinceBytes();

  /**
   * <code>optional string city = 8;</code>
   *
   * <pre>
   * 所在城市
   * </pre>
   */
  String getCity();
  /**
   * <code>optional string city = 8;</code>
   *
   * <pre>
   * 所在城市
   * </pre>
   */
  com.google.protobuf.ByteString
      getCityBytes();

  /**
   * <code>optional string languag = 9;</code>
   *
   * <pre>
   * 用户的语言，简体中文为：zh_CN
   * </pre>
   */
  String getLanguag();
  /**
   * <code>optional string languag = 9;</code>
   *
   * <pre>
   * 用户的语言，简体中文为：zh_CN
   * </pre>
   */
  com.google.protobuf.ByteString
      getLanguagBytes();

  /**
   * <code>optional string headImgUrl = 10;</code>
   *
   * <pre>
   * 用户头像
   * </pre>
   */
  String getHeadImgUrl();
  /**
   * <code>optional string headImgUrl = 10;</code>
   *
   * <pre>
   * 用户头像
   * </pre>
   */
  com.google.protobuf.ByteString
      getHeadImgUrlBytes();

  /**
   * <code>optional string remark = 11;</code>
   *
   * <pre>
   * 备注
   * </pre>
   */
  String getRemark();
  /**
   * <code>optional string remark = 11;</code>
   *
   * <pre>
   * 备注
   * </pre>
   */
  com.google.protobuf.ByteString
      getRemarkBytes();

  /**
   * <code>optional string groupid = 12;</code>
   *
   * <pre>
   * 所在分组ID
   * </pre>
   */
  String getGroupid();
  /**
   * <code>optional string groupid = 12;</code>
   *
   * <pre>
   * 所在分组ID
   * </pre>
   */
  com.google.protobuf.ByteString
      getGroupidBytes();

  /**
   * <code>optional string openId = 13;</code>
   *
   * <pre>
   * 每个公众号下用户的标识
   * </pre>
   */
  String getOpenId();
  /**
   * <code>optional string openId = 13;</code>
   *
   * <pre>
   * 每个公众号下用户的标识
   * </pre>
   */
  com.google.protobuf.ByteString
      getOpenIdBytes();

  /**
   * <code>optional string userid = 14;</code>
   *
   * <pre>
   *userid
   * </pre>
   */
  String getUserid();
  /**
   * <code>optional string userid = 14;</code>
   *
   * <pre>
   *userid
   * </pre>
   */
  com.google.protobuf.ByteString
      getUseridBytes();

  /**
   * <code>optional string name = 15;</code>
   *
   * <pre>
   *用户姓名
   * </pre>
   */
  String getName();
  /**
   * <code>optional string name = 15;</code>
   *
   * <pre>
   *用户姓名
   * </pre>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>optional string tel = 16;</code>
   *
   * <pre>
   *用户电话
   * </pre>
   */
  String getTel();
  /**
   * <code>optional string tel = 16;</code>
   *
   * <pre>
   *用户电话
   * </pre>
   */
  com.google.protobuf.ByteString
      getTelBytes();

  /**
   * <code>optional int32 type = 21;</code>
   *
   * <pre>
   *用户类型：1：买房人；2：帮帮
   * </pre>
   */
  int getType();
}
