// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GardenPriceView.proto

package com.weiwuu.protobuf;

public interface GardenPriceViewOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.weiwuu.protobuf.GardenPriceView)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int32 gardenId = 1;</code>
   *
   * <pre>
   * 楼盘 ID
   * </pre>
   */
  int getGardenId();

  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceItem priceList = 2;</code>
   *
   * <pre>
   * 价格跟踪清单
   * </pre>
   */
  java.util.List<GardenPriceItem>
      getPriceListList();
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceItem priceList = 2;</code>
   *
   * <pre>
   * 价格跟踪清单
   * </pre>
   */
  GardenPriceItem getPriceList(int index);
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceItem priceList = 2;</code>
   *
   * <pre>
   * 价格跟踪清单
   * </pre>
   */
  int getPriceListCount();
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceItem priceList = 2;</code>
   *
   * <pre>
   * 价格跟踪清单
   * </pre>
   */
  java.util.List<? extends GardenPriceItemOrBuilder>
      getPriceListOrBuilderList();
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceItem priceList = 2;</code>
   *
   * <pre>
   * 价格跟踪清单
   * </pre>
   */
  GardenPriceItemOrBuilder getPriceListOrBuilder(int index);

  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceNews priceNews = 3;</code>
   *
   * <pre>
   * 变动信息清单
   * </pre>
   */
  java.util.List<GardenPriceNews>
      getPriceNewsList();
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceNews priceNews = 3;</code>
   *
   * <pre>
   * 变动信息清单
   * </pre>
   */
  GardenPriceNews getPriceNews(int index);
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceNews priceNews = 3;</code>
   *
   * <pre>
   * 变动信息清单
   * </pre>
   */
  int getPriceNewsCount();
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceNews priceNews = 3;</code>
   *
   * <pre>
   * 变动信息清单
   * </pre>
   */
  java.util.List<? extends GardenPriceNewsOrBuilder>
      getPriceNewsOrBuilderList();
  /**
   * <code>repeated .com.weiwuu.protobuf.GardenPriceNews priceNews = 3;</code>
   *
   * <pre>
   * 变动信息清单
   * </pre>
   */
  GardenPriceNewsOrBuilder getPriceNewsOrBuilder(int index);
}
