// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GardenPriceNews.proto

package com.weiwuu.protobuf;

/**
 * Protobuf type {@code com.weiwuu.protobuf.GardenPriceNews}
 */
public  final class GardenPriceNews extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:com.weiwuu.protobuf.GardenPriceNews)
    GardenPriceNewsOrBuilder {
  // Use GardenPriceNews.newBuilder() to construct.
  private GardenPriceNews(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private GardenPriceNews() {
    date_ = "";
    caption_ = "";
    content_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GardenPriceNews(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            String s = input.readStringRequireUtf8();

            date_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            caption_ = s;
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();

            content_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return GardenPriceNewsProto.internal_static_com_weiwuu_protobuf_GardenPriceNews_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return GardenPriceNewsProto.internal_static_com_weiwuu_protobuf_GardenPriceNews_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GardenPriceNews.class, Builder.class);
  }

  public static final int DATE_FIELD_NUMBER = 1;
  private volatile Object date_;
  /**
   * <code>optional string date = 1;</code>
   *
   * <pre>
   * 日期: yyyy-MM-dd  (2015-12-17)
   * </pre>
   */
  public String getDate() {
    Object ref = date_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      date_ = s;
      return s;
    }
  }
  /**
   * <code>optional string date = 1;</code>
   *
   * <pre>
   * 日期: yyyy-MM-dd  (2015-12-17)
   * </pre>
   */
  public com.google.protobuf.ByteString
      getDateBytes() {
    Object ref = date_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      date_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CAPTION_FIELD_NUMBER = 2;
  private volatile Object caption_;
  /**
   * <code>optional string caption = 2;</code>
   *
   * <pre>
   * 标题: (限制字数 &lt;= 64)
   * </pre>
   */
  public String getCaption() {
    Object ref = caption_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      caption_ = s;
      return s;
    }
  }
  /**
   * <code>optional string caption = 2;</code>
   *
   * <pre>
   * 标题: (限制字数 &lt;= 64)
   * </pre>
   */
  public com.google.protobuf.ByteString
      getCaptionBytes() {
    Object ref = caption_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      caption_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONTENT_FIELD_NUMBER = 3;
  private volatile Object content_;
  /**
   * <code>optional string content = 3;</code>
   *
   * <pre>
   * 内容: (限制字数 &lt;= 4096)
   * </pre>
   */
  public String getContent() {
    Object ref = content_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      content_ = s;
      return s;
    }
  }
  /**
   * <code>optional string content = 3;</code>
   *
   * <pre>
   * 内容: (限制字数 &lt;= 4096)
   * </pre>
   */
  public com.google.protobuf.ByteString
      getContentBytes() {
    Object ref = content_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      content_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getDateBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, date_);
    }
    if (!getCaptionBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, caption_);
    }
    if (!getContentBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 3, content_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getDateBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, date_);
    }
    if (!getCaptionBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, caption_);
    }
    if (!getContentBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(3, content_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof GardenPriceNews)) {
      return super.equals(obj);
    }
    GardenPriceNews other = (GardenPriceNews) obj;

    boolean result = true;
    result = result && getDate()
        .equals(other.getDate());
    result = result && getCaption()
        .equals(other.getCaption());
    result = result && getContent()
        .equals(other.getContent());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + DATE_FIELD_NUMBER;
    hash = (53 * hash) + getDate().hashCode();
    hash = (37 * hash) + CAPTION_FIELD_NUMBER;
    hash = (53 * hash) + getCaption().hashCode();
    hash = (37 * hash) + CONTENT_FIELD_NUMBER;
    hash = (53 * hash) + getContent().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GardenPriceNews parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GardenPriceNews parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GardenPriceNews parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GardenPriceNews parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GardenPriceNews parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static GardenPriceNews parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static GardenPriceNews parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static GardenPriceNews parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static GardenPriceNews parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static GardenPriceNews parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(GardenPriceNews prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.weiwuu.protobuf.GardenPriceNews}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.weiwuu.protobuf.GardenPriceNews)
      GardenPriceNewsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return GardenPriceNewsProto.internal_static_com_weiwuu_protobuf_GardenPriceNews_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return GardenPriceNewsProto.internal_static_com_weiwuu_protobuf_GardenPriceNews_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GardenPriceNews.class, Builder.class);
    }

    // Construct using com.weiwuu.protobuf.GardenPriceNews.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      date_ = "";

      caption_ = "";

      content_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return GardenPriceNewsProto.internal_static_com_weiwuu_protobuf_GardenPriceNews_descriptor;
    }

    public GardenPriceNews getDefaultInstanceForType() {
      return GardenPriceNews.getDefaultInstance();
    }

    public GardenPriceNews build() {
      GardenPriceNews result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public GardenPriceNews buildPartial() {
      GardenPriceNews result = new GardenPriceNews(this);
      result.date_ = date_;
      result.caption_ = caption_;
      result.content_ = content_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof GardenPriceNews) {
        return mergeFrom((GardenPriceNews)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GardenPriceNews other) {
      if (other == GardenPriceNews.getDefaultInstance()) return this;
      if (!other.getDate().isEmpty()) {
        date_ = other.date_;
        onChanged();
      }
      if (!other.getCaption().isEmpty()) {
        caption_ = other.caption_;
        onChanged();
      }
      if (!other.getContent().isEmpty()) {
        content_ = other.content_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      GardenPriceNews parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (GardenPriceNews) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object date_ = "";
    /**
     * <code>optional string date = 1;</code>
     *
     * <pre>
     * 日期: yyyy-MM-dd  (2015-12-17)
     * </pre>
     */
    public String getDate() {
      Object ref = date_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        date_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string date = 1;</code>
     *
     * <pre>
     * 日期: yyyy-MM-dd  (2015-12-17)
     * </pre>
     */
    public com.google.protobuf.ByteString
        getDateBytes() {
      Object ref = date_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        date_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string date = 1;</code>
     *
     * <pre>
     * 日期: yyyy-MM-dd  (2015-12-17)
     * </pre>
     */
    public Builder setDate(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      date_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string date = 1;</code>
     *
     * <pre>
     * 日期: yyyy-MM-dd  (2015-12-17)
     * </pre>
     */
    public Builder clearDate() {
      
      date_ = getDefaultInstance().getDate();
      onChanged();
      return this;
    }
    /**
     * <code>optional string date = 1;</code>
     *
     * <pre>
     * 日期: yyyy-MM-dd  (2015-12-17)
     * </pre>
     */
    public Builder setDateBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      date_ = value;
      onChanged();
      return this;
    }

    private Object caption_ = "";
    /**
     * <code>optional string caption = 2;</code>
     *
     * <pre>
     * 标题: (限制字数 &lt;= 64)
     * </pre>
     */
    public String getCaption() {
      Object ref = caption_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        caption_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string caption = 2;</code>
     *
     * <pre>
     * 标题: (限制字数 &lt;= 64)
     * </pre>
     */
    public com.google.protobuf.ByteString
        getCaptionBytes() {
      Object ref = caption_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        caption_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string caption = 2;</code>
     *
     * <pre>
     * 标题: (限制字数 &lt;= 64)
     * </pre>
     */
    public Builder setCaption(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      caption_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string caption = 2;</code>
     *
     * <pre>
     * 标题: (限制字数 &lt;= 64)
     * </pre>
     */
    public Builder clearCaption() {
      
      caption_ = getDefaultInstance().getCaption();
      onChanged();
      return this;
    }
    /**
     * <code>optional string caption = 2;</code>
     *
     * <pre>
     * 标题: (限制字数 &lt;= 64)
     * </pre>
     */
    public Builder setCaptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      caption_ = value;
      onChanged();
      return this;
    }

    private Object content_ = "";
    /**
     * <code>optional string content = 3;</code>
     *
     * <pre>
     * 内容: (限制字数 &lt;= 4096)
     * </pre>
     */
    public String getContent() {
      Object ref = content_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        content_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string content = 3;</code>
     *
     * <pre>
     * 内容: (限制字数 &lt;= 4096)
     * </pre>
     */
    public com.google.protobuf.ByteString
        getContentBytes() {
      Object ref = content_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        content_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string content = 3;</code>
     *
     * <pre>
     * 内容: (限制字数 &lt;= 4096)
     * </pre>
     */
    public Builder setContent(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      content_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string content = 3;</code>
     *
     * <pre>
     * 内容: (限制字数 &lt;= 4096)
     * </pre>
     */
    public Builder clearContent() {
      
      content_ = getDefaultInstance().getContent();
      onChanged();
      return this;
    }
    /**
     * <code>optional string content = 3;</code>
     *
     * <pre>
     * 内容: (限制字数 &lt;= 4096)
     * </pre>
     */
    public Builder setContentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      content_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.weiwuu.protobuf.GardenPriceNews)
  }

  // @@protoc_insertion_point(class_scope:com.weiwuu.protobuf.GardenPriceNews)
  private static final GardenPriceNews DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GardenPriceNews();
  }

  public static GardenPriceNews getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GardenPriceNews>
      PARSER = new com.google.protobuf.AbstractParser<GardenPriceNews>() {
    public GardenPriceNews parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new GardenPriceNews(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<GardenPriceNews> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<GardenPriceNews> getParserForType() {
    return PARSER;
  }

  public GardenPriceNews getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
