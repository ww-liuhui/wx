// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GardenPriceItem.proto

package com.weiwuu.protobuf;

public final class GardenPriceItemProto {
  private GardenPriceItemProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_weiwuu_protobuf_GardenPriceItem_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_weiwuu_protobuf_GardenPriceItem_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\025GardenPriceItem.proto\022\023com.weiwuu.prot" +
      "obuf\"/\n\017GardenPriceItem\022\r\n\005month\030\001 \001(\t\022\r" +
      "\n\005price\030\002 \001(\005BS\n\023com.weiwuu.protobufB\024Ga" +
      "rdenPriceItemProtoP\001\240\001\001\242\002\003WPB\252\002\032Weiwuu.P" +
      "rotobuf.KnownTypesb\006proto3"
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
        }, assigner);
    internal_static_com_weiwuu_protobuf_GardenPriceItem_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_weiwuu_protobuf_GardenPriceItem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_weiwuu_protobuf_GardenPriceItem_descriptor,
        new String[] { "Month", "Price", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}