// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NewsSubscribe.proto

package com.weiwuu.protobuf;

public final class NewsSubscribeProto {
  private NewsSubscribeProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_weiwuu_protobuf_NewsSubscribe_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_weiwuu_protobuf_NewsSubscribe_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\023NewsSubscribe.proto\022\023com.weiwuu.protob" +
      "uf\"=\n\rNewsSubscribe\022\n\n\002id\030\001 \001(\005\022\016\n\006userI" +
      "d\030\002 \001(\005\022\020\n\010topicIds\030\003 \003(\005BQ\n\023com.weiwuu." +
      "protobufB\022NewsSubscribeProtoP\001\240\001\001\242\002\003WPB\252" +
      "\002\032Weiwuu.Protobuf.KnownTypesb\006proto3"
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
    internal_static_com_weiwuu_protobuf_NewsSubscribe_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_weiwuu_protobuf_NewsSubscribe_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_weiwuu_protobuf_NewsSubscribe_descriptor,
        new String[] { "Id", "UserId", "TopicIds", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
