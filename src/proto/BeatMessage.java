package connector.proto;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protofiles/beat.proto

public final class BeatMessage {
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_Beat_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_Beat_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\025protofiles/beat.proto\"1\n\004Beat\022\023\n\013reque" +
                        "sttime\030\002 \001(\003\022\024\n\014responsetime\030\003 \001(\003B\rB\013Be" +
                        "atMessageb\006proto3"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        });
        internal_static_Beat_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_Beat_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_Beat_descriptor,
                new java.lang.String[]{"Requesttime", "Responsetime",});
    }

    private BeatMessage() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface BeatOrBuilder extends
            // @@protoc_insertion_point(interface_extends:Beat)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int64 requesttime = 2;</code>
         *
         * @return The requesttime.
         */
        long getRequesttime();

        /**
         * <code>int64 responsetime = 3;</code>
         *
         * @return The responsetime.
         */
        long getResponsetime();
    }

    /**
     * Protobuf type {@code Beat}
     */
    public static final class Beat extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:Beat)
            BeatOrBuilder {
        public static final int REQUESTTIME_FIELD_NUMBER = 2;
        public static final int RESPONSETIME_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0L;
        // @@protoc_insertion_point(class_scope:Beat)
        private static final BeatMessage.Beat DEFAULT_INSTANCE;
        private static final com.google.protobuf.Parser<Beat>
                PARSER = new com.google.protobuf.AbstractParser<Beat>() {
            @java.lang.Override
            public Beat parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Beat(input, extensionRegistry);
            }
        };

        static {
            DEFAULT_INSTANCE = new BeatMessage.Beat();
        }

        private long requesttime_;
        private long responsetime_;
        private byte memoizedIsInitialized = -1;
        // Use Beat.newBuilder() to construct.
        private Beat(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Beat() {
        }

        private Beat(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 16: {

                            requesttime_ = input.readInt64();
                            break;
                        }
                        case 24: {

                            responsetime_ = input.readInt64();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return BeatMessage.internal_static_Beat_descriptor;
        }

        public static BeatMessage.Beat parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BeatMessage.Beat parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BeatMessage.Beat parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BeatMessage.Beat parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BeatMessage.Beat parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BeatMessage.Beat parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BeatMessage.Beat parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static BeatMessage.Beat parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static BeatMessage.Beat parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static BeatMessage.Beat parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static BeatMessage.Beat parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static BeatMessage.Beat parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BeatMessage.Beat prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public static BeatMessage.Beat getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static com.google.protobuf.Parser<Beat> parser() {
            return PARSER;
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new Beat();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return BeatMessage.internal_static_Beat_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            BeatMessage.Beat.class, BeatMessage.Beat.Builder.class);
        }

        /**
         * <code>int64 requesttime = 2;</code>
         *
         * @return The requesttime.
         */
        @java.lang.Override
        public long getRequesttime() {
            return requesttime_;
        }

        /**
         * <code>int64 responsetime = 3;</code>
         *
         * @return The responsetime.
         */
        @java.lang.Override
        public long getResponsetime() {
            return responsetime_;
        }

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (requesttime_ != 0L) {
                output.writeInt64(2, requesttime_);
            }
            if (responsetime_ != 0L) {
                output.writeInt64(3, responsetime_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (requesttime_ != 0L) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt64Size(2, requesttime_);
            }
            if (responsetime_ != 0L) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt64Size(3, responsetime_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BeatMessage.Beat)) {
                return super.equals(obj);
            }
            BeatMessage.Beat other = (BeatMessage.Beat) obj;

            if (getRequesttime()
                    != other.getRequesttime()) return false;
            if (getResponsetime()
                    != other.getResponsetime()) return false;
            if (!unknownFields.equals(other.unknownFields)) return false;
            return true;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + REQUESTTIME_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
                    getRequesttime());
            hash = (37 * hash) + RESPONSETIME_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
                    getResponsetime());
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Beat> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public BeatMessage.Beat getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        /**
         * Protobuf type {@code Beat}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:Beat)
                BeatMessage.BeatOrBuilder {
            private long requesttime_;
            private long responsetime_;

            // Construct using BeatMessage.Beat.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return BeatMessage.internal_static_Beat_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return BeatMessage.internal_static_Beat_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                BeatMessage.Beat.class, BeatMessage.Beat.Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                requesttime_ = 0L;

                responsetime_ = 0L;

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return BeatMessage.internal_static_Beat_descriptor;
            }

            @java.lang.Override
            public BeatMessage.Beat getDefaultInstanceForType() {
                return BeatMessage.Beat.getDefaultInstance();
            }

            @java.lang.Override
            public BeatMessage.Beat build() {
                BeatMessage.Beat result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public BeatMessage.Beat buildPartial() {
                BeatMessage.Beat result = new BeatMessage.Beat(this);
                result.requesttime_ = requesttime_;
                result.responsetime_ = responsetime_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof BeatMessage.Beat) {
                    return mergeFrom((BeatMessage.Beat) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(BeatMessage.Beat other) {
                if (other == BeatMessage.Beat.getDefaultInstance()) return this;
                if (other.getRequesttime() != 0L) {
                    setRequesttime(other.getRequesttime());
                }
                if (other.getResponsetime() != 0L) {
                    setResponsetime(other.getResponsetime());
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                BeatMessage.Beat parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (BeatMessage.Beat) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            /**
             * <code>int64 requesttime = 2;</code>
             *
             * @return The requesttime.
             */
            @java.lang.Override
            public long getRequesttime() {
                return requesttime_;
            }

            /**
             * <code>int64 requesttime = 2;</code>
             *
             * @param value The requesttime to set.
             * @return This builder for chaining.
             */
            public Builder setRequesttime(long value) {

                requesttime_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int64 requesttime = 2;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearRequesttime() {

                requesttime_ = 0L;
                onChanged();
                return this;
            }

            /**
             * <code>int64 responsetime = 3;</code>
             *
             * @return The responsetime.
             */
            @java.lang.Override
            public long getResponsetime() {
                return responsetime_;
            }

            /**
             * <code>int64 responsetime = 3;</code>
             *
             * @param value The responsetime to set.
             * @return This builder for chaining.
             */
            public Builder setResponsetime(long value) {

                responsetime_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int64 responsetime = 3;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearResponsetime() {

                responsetime_ = 0L;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:Beat)
        }

    }

    // @@protoc_insertion_point(outer_class_scope)
}