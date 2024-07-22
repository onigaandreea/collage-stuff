package org.example.protobuf;

public final class CompetitionProtobufs {
    private CompetitionProtobufs() {}
    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }
    public interface UserOrBuilder extends
            // @@protoc_insertion_point(interface_extends:User)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        int getId();

        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        java.lang.String getName();
        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        com.google.protobuf.ByteString
        getNameBytes();

        /**
         * <code>string username = 3;</code>
         * @return The username.
         */
        java.lang.String getUsername();
        /**
         * <code>string username = 3;</code>
         * @return The bytes for username.
         */
        com.google.protobuf.ByteString
        getUsernameBytes();

        /**
         * <code>string password = 4;</code>
         * @return The password.
         */
        java.lang.String getPassword();
        /**
         * <code>string password = 4;</code>
         * @return The bytes for password.
         */
        com.google.protobuf.ByteString
        getPasswordBytes();

        /**
         * <code>int32 officeNo = 5;</code>
         * @return The officeNo.
         */
        int getOfficeNo();
    }
    /**
     * Protobuf type {@code User}
     */
    public static final class User extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:User)
            UserOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use User.newBuilder() to construct.
        private User(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private User() {
            name_ = "";
            username_ = "";
            password_ = "";
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new User();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private User(
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
                        case 8: {

                            id_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            name_ = s;
                            break;
                        }
                        case 26: {
                            java.lang.String s = input.readStringRequireUtf8();

                            username_ = s;
                            break;
                        }
                        case 34: {
                            java.lang.String s = input.readStringRequireUtf8();

                            password_ = s;
                            break;
                        }
                        case 40: {

                            officeNo_ = input.readInt32();
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
            return org.example.protobuf.CompetitionProtobufs.internal_static_User_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_User_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.example.protobuf.CompetitionProtobufs.User.class, org.example.protobuf.CompetitionProtobufs.User.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        @java.lang.Override
        public int getId() {
            return id_;
        }

        public static final int NAME_FIELD_NUMBER = 2;
        private volatile java.lang.Object name_;
        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            }
        }
        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int USERNAME_FIELD_NUMBER = 3;
        private volatile java.lang.Object username_;
        /**
         * <code>string username = 3;</code>
         * @return The username.
         */
        @java.lang.Override
        public java.lang.String getUsername() {
            java.lang.Object ref = username_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                username_ = s;
                return s;
            }
        }
        /**
         * <code>string username = 3;</code>
         * @return The bytes for username.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getUsernameBytes() {
            java.lang.Object ref = username_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                username_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int PASSWORD_FIELD_NUMBER = 4;
        private volatile java.lang.Object password_;
        /**
         * <code>string password = 4;</code>
         * @return The password.
         */
        @java.lang.Override
        public java.lang.String getPassword() {
            java.lang.Object ref = password_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                password_ = s;
                return s;
            }
        }
        /**
         * <code>string password = 4;</code>
         * @return The bytes for password.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getPasswordBytes() {
            java.lang.Object ref = password_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                password_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int OFFICENO_FIELD_NUMBER = 5;
        private int officeNo_;
        /**
         * <code>int32 officeNo = 5;</code>
         * @return The officeNo.
         */
        @java.lang.Override
        public int getOfficeNo() {
            return officeNo_;
        }

        private byte memoizedIsInitialized = -1;
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
            if (id_ != 0) {
                output.writeInt32(1, id_);
            }
            if (!getNameBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
            }
            if (!getUsernameBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 3, username_);
            }
            if (!getPasswordBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 4, password_);
            }
            if (officeNo_ != 0) {
                output.writeInt32(5, officeNo_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, id_);
            }
            if (!getNameBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
            }
            if (!getUsernameBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, username_);
            }
            if (!getPasswordBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, password_);
            }
            if (officeNo_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(5, officeNo_);
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
            if (!(obj instanceof org.example.protobuf.CompetitionProtobufs.User)) {
                return super.equals(obj);
            }
            org.example.protobuf.CompetitionProtobufs.User other = (org.example.protobuf.CompetitionProtobufs.User) obj;

            if (getId()
                    != other.getId()) return false;
            if (!getName()
                    .equals(other.getName())) return false;
            if (!getUsername()
                    .equals(other.getUsername())) return false;
            if (!getPassword()
                    .equals(other.getPassword())) return false;
            if (getOfficeNo()
                    != other.getOfficeNo()) return false;
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
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
            hash = (37 * hash) + USERNAME_FIELD_NUMBER;
            hash = (53 * hash) + getUsername().hashCode();
            hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
            hash = (53 * hash) + getPassword().hashCode();
            hash = (37 * hash) + OFFICENO_FIELD_NUMBER;
            hash = (53 * hash) + getOfficeNo();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.User parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.example.protobuf.CompetitionProtobufs.User prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
        /**
         * Protobuf type {@code User}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:User)
                org.example.protobuf.CompetitionProtobufs.UserOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_User_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_User_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                org.example.protobuf.CompetitionProtobufs.User.class, org.example.protobuf.CompetitionProtobufs.User.Builder.class);
            }

            // Construct using org.example.protobuf.CompetitionProtobufs.User.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                name_ = "";

                username_ = "";

                password_ = "";

                officeNo_ = 0;

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_User_descriptor;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.User getDefaultInstanceForType() {
                return org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance();
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.User build() {
                org.example.protobuf.CompetitionProtobufs.User result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.User buildPartial() {
                org.example.protobuf.CompetitionProtobufs.User result = new org.example.protobuf.CompetitionProtobufs.User(this);
                result.id_ = id_;
                result.name_ = name_;
                result.username_ = username_;
                result.password_ = password_;
                result.officeNo_ = officeNo_;
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
                if (other instanceof org.example.protobuf.CompetitionProtobufs.User) {
                    return mergeFrom((org.example.protobuf.CompetitionProtobufs.User)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(org.example.protobuf.CompetitionProtobufs.User other) {
                if (other == org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getName().isEmpty()) {
                    name_ = other.name_;
                    onChanged();
                }
                if (!other.getUsername().isEmpty()) {
                    username_ = other.username_;
                    onChanged();
                }
                if (!other.getPassword().isEmpty()) {
                    password_ = other.password_;
                    onChanged();
                }
                if (other.getOfficeNo() != 0) {
                    setOfficeNo(other.getOfficeNo());
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
                org.example.protobuf.CompetitionProtobufs.User parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (org.example.protobuf.CompetitionProtobufs.User) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_ ;
            /**
             * <code>int32 id = 1;</code>
             * @return The id.
             */
            @java.lang.Override
            public int getId() {
                return id_;
            }
            /**
             * <code>int32 id = 1;</code>
             * @param value The id to set.
             * @return This builder for chaining.
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 id = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object name_ = "";
            /**
             * <code>string name = 2;</code>
             * @return The name.
             */
            public java.lang.String getName() {
                java.lang.Object ref = name_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    name_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <code>string name = 2;</code>
             * @return The bytes for name.
             */
            public com.google.protobuf.ByteString
            getNameBytes() {
                java.lang.Object ref = name_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    name_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>string name = 2;</code>
             * @param value The name to set.
             * @return This builder for chaining.
             */
            public Builder setName(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                name_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>string name = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearName() {

                name_ = getDefaultInstance().getName();
                onChanged();
                return this;
            }
            /**
             * <code>string name = 2;</code>
             * @param value The bytes for name to set.
             * @return This builder for chaining.
             */
            public Builder setNameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                name_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object username_ = "";
            /**
             * <code>string username = 3;</code>
             * @return The username.
             */
            public java.lang.String getUsername() {
                java.lang.Object ref = username_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    username_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <code>string username = 3;</code>
             * @return The bytes for username.
             */
            public com.google.protobuf.ByteString
            getUsernameBytes() {
                java.lang.Object ref = username_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    username_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>string username = 3;</code>
             * @param value The username to set.
             * @return This builder for chaining.
             */
            public Builder setUsername(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                username_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>string username = 3;</code>
             * @return This builder for chaining.
             */
            public Builder clearUsername() {

                username_ = getDefaultInstance().getUsername();
                onChanged();
                return this;
            }
            /**
             * <code>string username = 3;</code>
             * @param value The bytes for username to set.
             * @return This builder for chaining.
             */
            public Builder setUsernameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                username_ = value;
                onChanged();
                return this;
            }

            private java.lang.Object password_ = "";
            /**
             * <code>string password = 4;</code>
             * @return The password.
             */
            public java.lang.String getPassword() {
                java.lang.Object ref = password_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    password_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <code>string password = 4;</code>
             * @return The bytes for password.
             */
            public com.google.protobuf.ByteString
            getPasswordBytes() {
                java.lang.Object ref = password_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    password_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>string password = 4;</code>
             * @param value The password to set.
             * @return This builder for chaining.
             */
            public Builder setPassword(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                password_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>string password = 4;</code>
             * @return This builder for chaining.
             */
            public Builder clearPassword() {

                password_ = getDefaultInstance().getPassword();
                onChanged();
                return this;
            }
            /**
             * <code>string password = 4;</code>
             * @param value The bytes for password to set.
             * @return This builder for chaining.
             */
            public Builder setPasswordBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                password_ = value;
                onChanged();
                return this;
            }

            private int officeNo_ ;
            /**
             * <code>int32 officeNo = 5;</code>
             * @return The officeNo.
             */
            @java.lang.Override
            public int getOfficeNo() {
                return officeNo_;
            }
            /**
             * <code>int32 officeNo = 5;</code>
             * @param value The officeNo to set.
             * @return This builder for chaining.
             */
            public Builder setOfficeNo(int value) {

                officeNo_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 officeNo = 5;</code>
             * @return This builder for chaining.
             */
            public Builder clearOfficeNo() {

                officeNo_ = 0;
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


            // @@protoc_insertion_point(builder_scope:User)
        }

        // @@protoc_insertion_point(class_scope:User)
        private static final org.example.protobuf.CompetitionProtobufs.User DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new org.example.protobuf.CompetitionProtobufs.User();
        }

        public static org.example.protobuf.CompetitionProtobufs.User getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<User>
                PARSER = new com.google.protobuf.AbstractParser<User>() {
            @java.lang.Override
            public User parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new User(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<User> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<User> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.User getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface ChildOrBuilder extends
            // @@protoc_insertion_point(interface_extends:Child)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        int getId();

        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        java.lang.String getName();
        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        com.google.protobuf.ByteString
        getNameBytes();

        /**
         * <code>int32 age = 3;</code>
         * @return The age.
         */
        int getAge();
    }
    /**
     * Protobuf type {@code Child}
     */
    public static final class Child extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:Child)
            ChildOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use Child.newBuilder() to construct.
        private Child(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private Child() {
            name_ = "";
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new Child();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private Child(
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
                        case 8: {

                            id_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            name_ = s;
                            break;
                        }
                        case 24: {

                            age_ = input.readInt32();
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
            return org.example.protobuf.CompetitionProtobufs.internal_static_Child_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_Child_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.example.protobuf.CompetitionProtobufs.Child.class, org.example.protobuf.CompetitionProtobufs.Child.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        @java.lang.Override
        public int getId() {
            return id_;
        }

        public static final int NAME_FIELD_NUMBER = 2;
        private volatile java.lang.Object name_;
        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            }
        }
        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int AGE_FIELD_NUMBER = 3;
        private int age_;
        /**
         * <code>int32 age = 3;</code>
         * @return The age.
         */
        @java.lang.Override
        public int getAge() {
            return age_;
        }

        private byte memoizedIsInitialized = -1;
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
            if (id_ != 0) {
                output.writeInt32(1, id_);
            }
            if (!getNameBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
            }
            if (age_ != 0) {
                output.writeInt32(3, age_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, id_);
            }
            if (!getNameBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
            }
            if (age_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, age_);
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
            if (!(obj instanceof org.example.protobuf.CompetitionProtobufs.Child)) {
                return super.equals(obj);
            }
            org.example.protobuf.CompetitionProtobufs.Child other = (org.example.protobuf.CompetitionProtobufs.Child) obj;

            if (getId()
                    != other.getId()) return false;
            if (!getName()
                    .equals(other.getName())) return false;
            if (getAge()
                    != other.getAge()) return false;
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
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
            hash = (37 * hash) + AGE_FIELD_NUMBER;
            hash = (53 * hash) + getAge();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Child parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.example.protobuf.CompetitionProtobufs.Child prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
        /**
         * Protobuf type {@code Child}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:Child)
                org.example.protobuf.CompetitionProtobufs.ChildOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Child_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Child_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                org.example.protobuf.CompetitionProtobufs.Child.class, org.example.protobuf.CompetitionProtobufs.Child.Builder.class);
            }

            // Construct using org.example.protobuf.CompetitionProtobufs.Child.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                name_ = "";

                age_ = 0;

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Child_descriptor;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Child getDefaultInstanceForType() {
                return org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance();
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Child build() {
                org.example.protobuf.CompetitionProtobufs.Child result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Child buildPartial() {
                org.example.protobuf.CompetitionProtobufs.Child result = new org.example.protobuf.CompetitionProtobufs.Child(this);
                result.id_ = id_;
                result.name_ = name_;
                result.age_ = age_;
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
                if (other instanceof org.example.protobuf.CompetitionProtobufs.Child) {
                    return mergeFrom((org.example.protobuf.CompetitionProtobufs.Child)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(org.example.protobuf.CompetitionProtobufs.Child other) {
                if (other == org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getName().isEmpty()) {
                    name_ = other.name_;
                    onChanged();
                }
                if (other.getAge() != 0) {
                    setAge(other.getAge());
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
                org.example.protobuf.CompetitionProtobufs.Child parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (org.example.protobuf.CompetitionProtobufs.Child) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_ ;
            /**
             * <code>int32 id = 1;</code>
             * @return The id.
             */
            @java.lang.Override
            public int getId() {
                return id_;
            }
            /**
             * <code>int32 id = 1;</code>
             * @param value The id to set.
             * @return This builder for chaining.
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 id = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object name_ = "";
            /**
             * <code>string name = 2;</code>
             * @return The name.
             */
            public java.lang.String getName() {
                java.lang.Object ref = name_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    name_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <code>string name = 2;</code>
             * @return The bytes for name.
             */
            public com.google.protobuf.ByteString
            getNameBytes() {
                java.lang.Object ref = name_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    name_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>string name = 2;</code>
             * @param value The name to set.
             * @return This builder for chaining.
             */
            public Builder setName(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                name_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>string name = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearName() {

                name_ = getDefaultInstance().getName();
                onChanged();
                return this;
            }
            /**
             * <code>string name = 2;</code>
             * @param value The bytes for name to set.
             * @return This builder for chaining.
             */
            public Builder setNameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                name_ = value;
                onChanged();
                return this;
            }

            private int age_ ;
            /**
             * <code>int32 age = 3;</code>
             * @return The age.
             */
            @java.lang.Override
            public int getAge() {
                return age_;
            }
            /**
             * <code>int32 age = 3;</code>
             * @param value The age to set.
             * @return This builder for chaining.
             */
            public Builder setAge(int value) {

                age_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 age = 3;</code>
             * @return This builder for chaining.
             */
            public Builder clearAge() {

                age_ = 0;
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


            // @@protoc_insertion_point(builder_scope:Child)
        }

        // @@protoc_insertion_point(class_scope:Child)
        private static final org.example.protobuf.CompetitionProtobufs.Child DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new org.example.protobuf.CompetitionProtobufs.Child();
        }

        public static org.example.protobuf.CompetitionProtobufs.Child getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Child>
                PARSER = new com.google.protobuf.AbstractParser<Child>() {
            @java.lang.Override
            public Child parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Child(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Child> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Child> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Child getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface TaskOrBuilder extends
            // @@protoc_insertion_point(interface_extends:Task)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        int getId();

        /**
         * <code>string description = 2;</code>
         * @return The description.
         */
        java.lang.String getDescription();
        /**
         * <code>string description = 2;</code>
         * @return The bytes for description.
         */
        com.google.protobuf.ByteString
        getDescriptionBytes();

        /**
         * <code>int32 ageCatStart = 3;</code>
         * @return The ageCatStart.
         */
        int getAgeCatStart();

        /**
         * <code>int32 ageCatEnd = 4;</code>
         * @return The ageCatEnd.
         */
        int getAgeCatEnd();
    }
    /**
     * Protobuf type {@code Task}
     */
    public static final class Task extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:Task)
            TaskOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use Task.newBuilder() to construct.
        private Task(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private Task() {
            description_ = "";
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new Task();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private Task(
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
                        case 8: {

                            id_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            description_ = s;
                            break;
                        }
                        case 24: {

                            ageCatStart_ = input.readInt32();
                            break;
                        }
                        case 32: {

                            ageCatEnd_ = input.readInt32();
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
            return org.example.protobuf.CompetitionProtobufs.internal_static_Task_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_Task_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.example.protobuf.CompetitionProtobufs.Task.class, org.example.protobuf.CompetitionProtobufs.Task.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        @java.lang.Override
        public int getId() {
            return id_;
        }

        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        private volatile java.lang.Object description_;
        /**
         * <code>string description = 2;</code>
         * @return The description.
         */
        @java.lang.Override
        public java.lang.String getDescription() {
            java.lang.Object ref = description_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                description_ = s;
                return s;
            }
        }
        /**
         * <code>string description = 2;</code>
         * @return The bytes for description.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getDescriptionBytes() {
            java.lang.Object ref = description_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                description_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int AGECATSTART_FIELD_NUMBER = 3;
        private int ageCatStart_;
        /**
         * <code>int32 ageCatStart = 3;</code>
         * @return The ageCatStart.
         */
        @java.lang.Override
        public int getAgeCatStart() {
            return ageCatStart_;
        }

        public static final int AGECATEND_FIELD_NUMBER = 4;
        private int ageCatEnd_;
        /**
         * <code>int32 ageCatEnd = 4;</code>
         * @return The ageCatEnd.
         */
        @java.lang.Override
        public int getAgeCatEnd() {
            return ageCatEnd_;
        }

        private byte memoizedIsInitialized = -1;
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
            if (id_ != 0) {
                output.writeInt32(1, id_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, description_);
            }
            if (ageCatStart_ != 0) {
                output.writeInt32(3, ageCatStart_);
            }
            if (ageCatEnd_ != 0) {
                output.writeInt32(4, ageCatEnd_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, id_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, description_);
            }
            if (ageCatStart_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, ageCatStart_);
            }
            if (ageCatEnd_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(4, ageCatEnd_);
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
            if (!(obj instanceof org.example.protobuf.CompetitionProtobufs.Task)) {
                return super.equals(obj);
            }
            org.example.protobuf.CompetitionProtobufs.Task other = (org.example.protobuf.CompetitionProtobufs.Task) obj;

            if (getId()
                    != other.getId()) return false;
            if (!getDescription()
                    .equals(other.getDescription())) return false;
            if (getAgeCatStart()
                    != other.getAgeCatStart()) return false;
            if (getAgeCatEnd()
                    != other.getAgeCatEnd()) return false;
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
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
            hash = (53 * hash) + getDescription().hashCode();
            hash = (37 * hash) + AGECATSTART_FIELD_NUMBER;
            hash = (53 * hash) + getAgeCatStart();
            hash = (37 * hash) + AGECATEND_FIELD_NUMBER;
            hash = (53 * hash) + getAgeCatEnd();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Task parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.example.protobuf.CompetitionProtobufs.Task prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
        /**
         * Protobuf type {@code Task}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:Task)
                org.example.protobuf.CompetitionProtobufs.TaskOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Task_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Task_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                org.example.protobuf.CompetitionProtobufs.Task.class, org.example.protobuf.CompetitionProtobufs.Task.Builder.class);
            }

            // Construct using org.example.protobuf.CompetitionProtobufs.Task.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                description_ = "";

                ageCatStart_ = 0;

                ageCatEnd_ = 0;

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Task_descriptor;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Task getDefaultInstanceForType() {
                return org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance();
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Task build() {
                org.example.protobuf.CompetitionProtobufs.Task result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Task buildPartial() {
                org.example.protobuf.CompetitionProtobufs.Task result = new org.example.protobuf.CompetitionProtobufs.Task(this);
                result.id_ = id_;
                result.description_ = description_;
                result.ageCatStart_ = ageCatStart_;
                result.ageCatEnd_ = ageCatEnd_;
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
                if (other instanceof org.example.protobuf.CompetitionProtobufs.Task) {
                    return mergeFrom((org.example.protobuf.CompetitionProtobufs.Task)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(org.example.protobuf.CompetitionProtobufs.Task other) {
                if (other == org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getDescription().isEmpty()) {
                    description_ = other.description_;
                    onChanged();
                }
                if (other.getAgeCatStart() != 0) {
                    setAgeCatStart(other.getAgeCatStart());
                }
                if (other.getAgeCatEnd() != 0) {
                    setAgeCatEnd(other.getAgeCatEnd());
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
                org.example.protobuf.CompetitionProtobufs.Task parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (org.example.protobuf.CompetitionProtobufs.Task) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_ ;
            /**
             * <code>int32 id = 1;</code>
             * @return The id.
             */
            @java.lang.Override
            public int getId() {
                return id_;
            }
            /**
             * <code>int32 id = 1;</code>
             * @param value The id to set.
             * @return This builder for chaining.
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 id = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object description_ = "";
            /**
             * <code>string description = 2;</code>
             * @return The description.
             */
            public java.lang.String getDescription() {
                java.lang.Object ref = description_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    description_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <code>string description = 2;</code>
             * @return The bytes for description.
             */
            public com.google.protobuf.ByteString
            getDescriptionBytes() {
                java.lang.Object ref = description_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    description_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>string description = 2;</code>
             * @param value The description to set.
             * @return This builder for chaining.
             */
            public Builder setDescription(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                description_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>string description = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearDescription() {

                description_ = getDefaultInstance().getDescription();
                onChanged();
                return this;
            }
            /**
             * <code>string description = 2;</code>
             * @param value The bytes for description to set.
             * @return This builder for chaining.
             */
            public Builder setDescriptionBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                description_ = value;
                onChanged();
                return this;
            }

            private int ageCatStart_ ;
            /**
             * <code>int32 ageCatStart = 3;</code>
             * @return The ageCatStart.
             */
            @java.lang.Override
            public int getAgeCatStart() {
                return ageCatStart_;
            }
            /**
             * <code>int32 ageCatStart = 3;</code>
             * @param value The ageCatStart to set.
             * @return This builder for chaining.
             */
            public Builder setAgeCatStart(int value) {

                ageCatStart_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 ageCatStart = 3;</code>
             * @return This builder for chaining.
             */
            public Builder clearAgeCatStart() {

                ageCatStart_ = 0;
                onChanged();
                return this;
            }

            private int ageCatEnd_ ;
            /**
             * <code>int32 ageCatEnd = 4;</code>
             * @return The ageCatEnd.
             */
            @java.lang.Override
            public int getAgeCatEnd() {
                return ageCatEnd_;
            }
            /**
             * <code>int32 ageCatEnd = 4;</code>
             * @param value The ageCatEnd to set.
             * @return This builder for chaining.
             */
            public Builder setAgeCatEnd(int value) {

                ageCatEnd_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 ageCatEnd = 4;</code>
             * @return This builder for chaining.
             */
            public Builder clearAgeCatEnd() {

                ageCatEnd_ = 0;
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


            // @@protoc_insertion_point(builder_scope:Task)
        }

        // @@protoc_insertion_point(class_scope:Task)
        private static final org.example.protobuf.CompetitionProtobufs.Task DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new org.example.protobuf.CompetitionProtobufs.Task();
        }

        public static org.example.protobuf.CompetitionProtobufs.Task getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Task>
                PARSER = new com.google.protobuf.AbstractParser<Task>() {
            @java.lang.Override
            public Task parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Task(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Task> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Task> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Task getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface TaskDTOOrBuilder extends
            // @@protoc_insertion_point(interface_extends:TaskDTO)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        int getId();

        /**
         * <code>string description = 2;</code>
         * @return The description.
         */
        java.lang.String getDescription();
        /**
         * <code>string description = 2;</code>
         * @return The bytes for description.
         */
        com.google.protobuf.ByteString
        getDescriptionBytes();

        /**
         * <code>int32 ageCatStart = 3;</code>
         * @return The ageCatStart.
         */
        int getAgeCatStart();

        /**
         * <code>int32 ageCatEnd = 4;</code>
         * @return The ageCatEnd.
         */
        int getAgeCatEnd();

        /**
         * <code>int32 nochildren = 5;</code>
         * @return The nochildren.
         */
        int getNochildren();
    }
    /**
     * Protobuf type {@code TaskDTO}
     */
    public static final class TaskDTO extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:TaskDTO)
            TaskDTOOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use TaskDTO.newBuilder() to construct.
        private TaskDTO(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private TaskDTO() {
            description_ = "";
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new TaskDTO();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private TaskDTO(
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
                        case 8: {

                            id_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            description_ = s;
                            break;
                        }
                        case 24: {

                            ageCatStart_ = input.readInt32();
                            break;
                        }
                        case 32: {

                            ageCatEnd_ = input.readInt32();
                            break;
                        }
                        case 40: {

                            nochildren_ = input.readInt32();
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
            return org.example.protobuf.CompetitionProtobufs.internal_static_TaskDTO_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_TaskDTO_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.example.protobuf.CompetitionProtobufs.TaskDTO.class, org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        @java.lang.Override
        public int getId() {
            return id_;
        }

        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        private volatile java.lang.Object description_;
        /**
         * <code>string description = 2;</code>
         * @return The description.
         */
        @java.lang.Override
        public java.lang.String getDescription() {
            java.lang.Object ref = description_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                description_ = s;
                return s;
            }
        }
        /**
         * <code>string description = 2;</code>
         * @return The bytes for description.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getDescriptionBytes() {
            java.lang.Object ref = description_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                description_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int AGECATSTART_FIELD_NUMBER = 3;
        private int ageCatStart_;
        /**
         * <code>int32 ageCatStart = 3;</code>
         * @return The ageCatStart.
         */
        @java.lang.Override
        public int getAgeCatStart() {
            return ageCatStart_;
        }

        public static final int AGECATEND_FIELD_NUMBER = 4;
        private int ageCatEnd_;
        /**
         * <code>int32 ageCatEnd = 4;</code>
         * @return The ageCatEnd.
         */
        @java.lang.Override
        public int getAgeCatEnd() {
            return ageCatEnd_;
        }

        public static final int NOCHILDREN_FIELD_NUMBER = 5;
        private int nochildren_;
        /**
         * <code>int32 nochildren = 5;</code>
         * @return The nochildren.
         */
        @java.lang.Override
        public int getNochildren() {
            return nochildren_;
        }

        private byte memoizedIsInitialized = -1;
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
            if (id_ != 0) {
                output.writeInt32(1, id_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, description_);
            }
            if (ageCatStart_ != 0) {
                output.writeInt32(3, ageCatStart_);
            }
            if (ageCatEnd_ != 0) {
                output.writeInt32(4, ageCatEnd_);
            }
            if (nochildren_ != 0) {
                output.writeInt32(5, nochildren_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, id_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, description_);
            }
            if (ageCatStart_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, ageCatStart_);
            }
            if (ageCatEnd_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(4, ageCatEnd_);
            }
            if (nochildren_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(5, nochildren_);
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
            if (!(obj instanceof org.example.protobuf.CompetitionProtobufs.TaskDTO)) {
                return super.equals(obj);
            }
            org.example.protobuf.CompetitionProtobufs.TaskDTO other = (org.example.protobuf.CompetitionProtobufs.TaskDTO) obj;

            if (getId()
                    != other.getId()) return false;
            if (!getDescription()
                    .equals(other.getDescription())) return false;
            if (getAgeCatStart()
                    != other.getAgeCatStart()) return false;
            if (getAgeCatEnd()
                    != other.getAgeCatEnd()) return false;
            if (getNochildren()
                    != other.getNochildren()) return false;
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
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
            hash = (53 * hash) + getDescription().hashCode();
            hash = (37 * hash) + AGECATSTART_FIELD_NUMBER;
            hash = (53 * hash) + getAgeCatStart();
            hash = (37 * hash) + AGECATEND_FIELD_NUMBER;
            hash = (53 * hash) + getAgeCatEnd();
            hash = (37 * hash) + NOCHILDREN_FIELD_NUMBER;
            hash = (53 * hash) + getNochildren();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.TaskDTO parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.example.protobuf.CompetitionProtobufs.TaskDTO prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
        /**
         * Protobuf type {@code TaskDTO}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:TaskDTO)
                org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_TaskDTO_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_TaskDTO_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                org.example.protobuf.CompetitionProtobufs.TaskDTO.class, org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder.class);
            }

            // Construct using org.example.protobuf.CompetitionProtobufs.TaskDTO.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                description_ = "";

                ageCatStart_ = 0;

                ageCatEnd_ = 0;

                nochildren_ = 0;

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_TaskDTO_descriptor;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.TaskDTO getDefaultInstanceForType() {
                return org.example.protobuf.CompetitionProtobufs.TaskDTO.getDefaultInstance();
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.TaskDTO build() {
                org.example.protobuf.CompetitionProtobufs.TaskDTO result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.TaskDTO buildPartial() {
                org.example.protobuf.CompetitionProtobufs.TaskDTO result = new org.example.protobuf.CompetitionProtobufs.TaskDTO(this);
                result.id_ = id_;
                result.description_ = description_;
                result.ageCatStart_ = ageCatStart_;
                result.ageCatEnd_ = ageCatEnd_;
                result.nochildren_ = nochildren_;
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
                if (other instanceof org.example.protobuf.CompetitionProtobufs.TaskDTO) {
                    return mergeFrom((org.example.protobuf.CompetitionProtobufs.TaskDTO)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(org.example.protobuf.CompetitionProtobufs.TaskDTO other) {
                if (other == org.example.protobuf.CompetitionProtobufs.TaskDTO.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getDescription().isEmpty()) {
                    description_ = other.description_;
                    onChanged();
                }
                if (other.getAgeCatStart() != 0) {
                    setAgeCatStart(other.getAgeCatStart());
                }
                if (other.getAgeCatEnd() != 0) {
                    setAgeCatEnd(other.getAgeCatEnd());
                }
                if (other.getNochildren() != 0) {
                    setNochildren(other.getNochildren());
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
                org.example.protobuf.CompetitionProtobufs.TaskDTO parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (org.example.protobuf.CompetitionProtobufs.TaskDTO) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_ ;
            /**
             * <code>int32 id = 1;</code>
             * @return The id.
             */
            @java.lang.Override
            public int getId() {
                return id_;
            }
            /**
             * <code>int32 id = 1;</code>
             * @param value The id to set.
             * @return This builder for chaining.
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 id = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object description_ = "";
            /**
             * <code>string description = 2;</code>
             * @return The description.
             */
            public java.lang.String getDescription() {
                java.lang.Object ref = description_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    description_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <code>string description = 2;</code>
             * @return The bytes for description.
             */
            public com.google.protobuf.ByteString
            getDescriptionBytes() {
                java.lang.Object ref = description_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    description_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>string description = 2;</code>
             * @param value The description to set.
             * @return This builder for chaining.
             */
            public Builder setDescription(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                description_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>string description = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearDescription() {

                description_ = getDefaultInstance().getDescription();
                onChanged();
                return this;
            }
            /**
             * <code>string description = 2;</code>
             * @param value The bytes for description to set.
             * @return This builder for chaining.
             */
            public Builder setDescriptionBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                description_ = value;
                onChanged();
                return this;
            }

            private int ageCatStart_ ;
            /**
             * <code>int32 ageCatStart = 3;</code>
             * @return The ageCatStart.
             */
            @java.lang.Override
            public int getAgeCatStart() {
                return ageCatStart_;
            }
            /**
             * <code>int32 ageCatStart = 3;</code>
             * @param value The ageCatStart to set.
             * @return This builder for chaining.
             */
            public Builder setAgeCatStart(int value) {

                ageCatStart_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 ageCatStart = 3;</code>
             * @return This builder for chaining.
             */
            public Builder clearAgeCatStart() {

                ageCatStart_ = 0;
                onChanged();
                return this;
            }

            private int ageCatEnd_ ;
            /**
             * <code>int32 ageCatEnd = 4;</code>
             * @return The ageCatEnd.
             */
            @java.lang.Override
            public int getAgeCatEnd() {
                return ageCatEnd_;
            }
            /**
             * <code>int32 ageCatEnd = 4;</code>
             * @param value The ageCatEnd to set.
             * @return This builder for chaining.
             */
            public Builder setAgeCatEnd(int value) {

                ageCatEnd_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 ageCatEnd = 4;</code>
             * @return This builder for chaining.
             */
            public Builder clearAgeCatEnd() {

                ageCatEnd_ = 0;
                onChanged();
                return this;
            }

            private int nochildren_ ;
            /**
             * <code>int32 nochildren = 5;</code>
             * @return The nochildren.
             */
            @java.lang.Override
            public int getNochildren() {
                return nochildren_;
            }
            /**
             * <code>int32 nochildren = 5;</code>
             * @param value The nochildren to set.
             * @return This builder for chaining.
             */
            public Builder setNochildren(int value) {

                nochildren_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 nochildren = 5;</code>
             * @return This builder for chaining.
             */
            public Builder clearNochildren() {

                nochildren_ = 0;
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


            // @@protoc_insertion_point(builder_scope:TaskDTO)
        }

        // @@protoc_insertion_point(class_scope:TaskDTO)
        private static final org.example.protobuf.CompetitionProtobufs.TaskDTO DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new org.example.protobuf.CompetitionProtobufs.TaskDTO();
        }

        public static org.example.protobuf.CompetitionProtobufs.TaskDTO getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<TaskDTO>
                PARSER = new com.google.protobuf.AbstractParser<TaskDTO>() {
            @java.lang.Override
            public TaskDTO parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new TaskDTO(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<TaskDTO> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<TaskDTO> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.TaskDTO getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface ParticipationOrBuilder extends
            // @@protoc_insertion_point(interface_extends:Participation)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        int getId();

        /**
         * <code>.Child child = 2;</code>
         * @return Whether the child field is set.
         */
        boolean hasChild();
        /**
         * <code>.Child child = 2;</code>
         * @return The child.
         */
        org.example.protobuf.CompetitionProtobufs.Child getChild();
        /**
         * <code>.Child child = 2;</code>
         */
        org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder();

        /**
         * <code>.Task task = 3;</code>
         * @return Whether the task field is set.
         */
        boolean hasTask();
        /**
         * <code>.Task task = 3;</code>
         * @return The task.
         */
        org.example.protobuf.CompetitionProtobufs.Task getTask();
        /**
         * <code>.Task task = 3;</code>
         */
        org.example.protobuf.CompetitionProtobufs.TaskOrBuilder getTaskOrBuilder();
    }
    /**
     * Protobuf type {@code Participation}
     */
    public static final class Participation extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:Participation)
            ParticipationOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use Participation.newBuilder() to construct.
        private Participation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private Participation() {
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new Participation();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private Participation(
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
                        case 8: {

                            id_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            org.example.protobuf.CompetitionProtobufs.Child.Builder subBuilder = null;
                            if (child_ != null) {
                                subBuilder = child_.toBuilder();
                            }
                            child_ = input.readMessage(org.example.protobuf.CompetitionProtobufs.Child.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(child_);
                                child_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 26: {
                            org.example.protobuf.CompetitionProtobufs.Task.Builder subBuilder = null;
                            if (task_ != null) {
                                subBuilder = task_.toBuilder();
                            }
                            task_ = input.readMessage(org.example.protobuf.CompetitionProtobufs.Task.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(task_);
                                task_ = subBuilder.buildPartial();
                            }

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
            return org.example.protobuf.CompetitionProtobufs.internal_static_Participation_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_Participation_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.example.protobuf.CompetitionProtobufs.Participation.class, org.example.protobuf.CompetitionProtobufs.Participation.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        @java.lang.Override
        public int getId() {
            return id_;
        }

        public static final int CHILD_FIELD_NUMBER = 2;
        private org.example.protobuf.CompetitionProtobufs.Child child_;
        /**
         * <code>.Child child = 2;</code>
         * @return Whether the child field is set.
         */
        @java.lang.Override
        public boolean hasChild() {
            return child_ != null;
        }
        /**
         * <code>.Child child = 2;</code>
         * @return The child.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Child getChild() {
            return child_ == null ? org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance() : child_;
        }
        /**
         * <code>.Child child = 2;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder() {
            return getChild();
        }

        public static final int TASK_FIELD_NUMBER = 3;
        private org.example.protobuf.CompetitionProtobufs.Task task_;
        /**
         * <code>.Task task = 3;</code>
         * @return Whether the task field is set.
         */
        @java.lang.Override
        public boolean hasTask() {
            return task_ != null;
        }
        /**
         * <code>.Task task = 3;</code>
         * @return The task.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Task getTask() {
            return task_ == null ? org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance() : task_;
        }
        /**
         * <code>.Task task = 3;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.TaskOrBuilder getTaskOrBuilder() {
            return getTask();
        }

        private byte memoizedIsInitialized = -1;
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
            if (id_ != 0) {
                output.writeInt32(1, id_);
            }
            if (child_ != null) {
                output.writeMessage(2, getChild());
            }
            if (task_ != null) {
                output.writeMessage(3, getTask());
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, id_);
            }
            if (child_ != null) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(2, getChild());
            }
            if (task_ != null) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(3, getTask());
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
            if (!(obj instanceof org.example.protobuf.CompetitionProtobufs.Participation)) {
                return super.equals(obj);
            }
            org.example.protobuf.CompetitionProtobufs.Participation other = (org.example.protobuf.CompetitionProtobufs.Participation) obj;

            if (getId()
                    != other.getId()) return false;
            if (hasChild() != other.hasChild()) return false;
            if (hasChild()) {
                if (!getChild()
                        .equals(other.getChild())) return false;
            }
            if (hasTask() != other.hasTask()) return false;
            if (hasTask()) {
                if (!getTask()
                        .equals(other.getTask())) return false;
            }
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
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            if (hasChild()) {
                hash = (37 * hash) + CHILD_FIELD_NUMBER;
                hash = (53 * hash) + getChild().hashCode();
            }
            if (hasTask()) {
                hash = (37 * hash) + TASK_FIELD_NUMBER;
                hash = (53 * hash) + getTask().hashCode();
            }
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Participation parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.example.protobuf.CompetitionProtobufs.Participation prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
        /**
         * Protobuf type {@code Participation}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:Participation)
                org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Participation_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Participation_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                org.example.protobuf.CompetitionProtobufs.Participation.class, org.example.protobuf.CompetitionProtobufs.Participation.Builder.class);
            }

            // Construct using org.example.protobuf.CompetitionProtobufs.Participation.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                if (childBuilder_ == null) {
                    child_ = null;
                } else {
                    child_ = null;
                    childBuilder_ = null;
                }
                if (taskBuilder_ == null) {
                    task_ = null;
                } else {
                    task_ = null;
                    taskBuilder_ = null;
                }
                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Participation_descriptor;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Participation getDefaultInstanceForType() {
                return org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance();
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Participation build() {
                org.example.protobuf.CompetitionProtobufs.Participation result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Participation buildPartial() {
                org.example.protobuf.CompetitionProtobufs.Participation result = new org.example.protobuf.CompetitionProtobufs.Participation(this);
                result.id_ = id_;
                if (childBuilder_ == null) {
                    result.child_ = child_;
                } else {
                    result.child_ = childBuilder_.build();
                }
                if (taskBuilder_ == null) {
                    result.task_ = task_;
                } else {
                    result.task_ = taskBuilder_.build();
                }
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
                if (other instanceof org.example.protobuf.CompetitionProtobufs.Participation) {
                    return mergeFrom((org.example.protobuf.CompetitionProtobufs.Participation)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(org.example.protobuf.CompetitionProtobufs.Participation other) {
                if (other == org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (other.hasChild()) {
                    mergeChild(other.getChild());
                }
                if (other.hasTask()) {
                    mergeTask(other.getTask());
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
                org.example.protobuf.CompetitionProtobufs.Participation parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (org.example.protobuf.CompetitionProtobufs.Participation) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_ ;
            /**
             * <code>int32 id = 1;</code>
             * @return The id.
             */
            @java.lang.Override
            public int getId() {
                return id_;
            }
            /**
             * <code>int32 id = 1;</code>
             * @param value The id to set.
             * @return This builder for chaining.
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>int32 id = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private org.example.protobuf.CompetitionProtobufs.Child child_;
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder> childBuilder_;
            /**
             * <code>.Child child = 2;</code>
             * @return Whether the child field is set.
             */
            public boolean hasChild() {
                return childBuilder_ != null || child_ != null;
            }
            /**
             * <code>.Child child = 2;</code>
             * @return The child.
             */
            public org.example.protobuf.CompetitionProtobufs.Child getChild() {
                if (childBuilder_ == null) {
                    return child_ == null ? org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance() : child_;
                } else {
                    return childBuilder_.getMessage();
                }
            }
            /**
             * <code>.Child child = 2;</code>
             */
            public Builder setChild(org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    child_ = value;
                    onChanged();
                } else {
                    childBuilder_.setMessage(value);
                }

                return this;
            }
            /**
             * <code>.Child child = 2;</code>
             */
            public Builder setChild(
                    org.example.protobuf.CompetitionProtobufs.Child.Builder builderForValue) {
                if (childBuilder_ == null) {
                    child_ = builderForValue.build();
                    onChanged();
                } else {
                    childBuilder_.setMessage(builderForValue.build());
                }

                return this;
            }
            /**
             * <code>.Child child = 2;</code>
             */
            public Builder mergeChild(org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childBuilder_ == null) {
                    if (child_ != null) {
                        child_ =
                                org.example.protobuf.CompetitionProtobufs.Child.newBuilder(child_).mergeFrom(value).buildPartial();
                    } else {
                        child_ = value;
                    }
                    onChanged();
                } else {
                    childBuilder_.mergeFrom(value);
                }

                return this;
            }
            /**
             * <code>.Child child = 2;</code>
             */
            public Builder clearChild() {
                if (childBuilder_ == null) {
                    child_ = null;
                    onChanged();
                } else {
                    child_ = null;
                    childBuilder_ = null;
                }

                return this;
            }
            /**
             * <code>.Child child = 2;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Child.Builder getChildBuilder() {

                onChanged();
                return getChildFieldBuilder().getBuilder();
            }
            /**
             * <code>.Child child = 2;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder() {
                if (childBuilder_ != null) {
                    return childBuilder_.getMessageOrBuilder();
                } else {
                    return child_ == null ?
                            org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance() : child_;
                }
            }
            /**
             * <code>.Child child = 2;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>
            getChildFieldBuilder() {
                if (childBuilder_ == null) {
                    childBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>(
                            getChild(),
                            getParentForChildren(),
                            isClean());
                    child_ = null;
                }
                return childBuilder_;
            }

            private org.example.protobuf.CompetitionProtobufs.Task task_;
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Task, org.example.protobuf.CompetitionProtobufs.Task.Builder, org.example.protobuf.CompetitionProtobufs.TaskOrBuilder> taskBuilder_;
            /**
             * <code>.Task task = 3;</code>
             * @return Whether the task field is set.
             */
            public boolean hasTask() {
                return taskBuilder_ != null || task_ != null;
            }
            /**
             * <code>.Task task = 3;</code>
             * @return The task.
             */
            public org.example.protobuf.CompetitionProtobufs.Task getTask() {
                if (taskBuilder_ == null) {
                    return task_ == null ? org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance() : task_;
                } else {
                    return taskBuilder_.getMessage();
                }
            }
            /**
             * <code>.Task task = 3;</code>
             */
            public Builder setTask(org.example.protobuf.CompetitionProtobufs.Task value) {
                if (taskBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    task_ = value;
                    onChanged();
                } else {
                    taskBuilder_.setMessage(value);
                }

                return this;
            }
            /**
             * <code>.Task task = 3;</code>
             */
            public Builder setTask(
                    org.example.protobuf.CompetitionProtobufs.Task.Builder builderForValue) {
                if (taskBuilder_ == null) {
                    task_ = builderForValue.build();
                    onChanged();
                } else {
                    taskBuilder_.setMessage(builderForValue.build());
                }

                return this;
            }
            /**
             * <code>.Task task = 3;</code>
             */
            public Builder mergeTask(org.example.protobuf.CompetitionProtobufs.Task value) {
                if (taskBuilder_ == null) {
                    if (task_ != null) {
                        task_ =
                                org.example.protobuf.CompetitionProtobufs.Task.newBuilder(task_).mergeFrom(value).buildPartial();
                    } else {
                        task_ = value;
                    }
                    onChanged();
                } else {
                    taskBuilder_.mergeFrom(value);
                }

                return this;
            }
            /**
             * <code>.Task task = 3;</code>
             */
            public Builder clearTask() {
                if (taskBuilder_ == null) {
                    task_ = null;
                    onChanged();
                } else {
                    task_ = null;
                    taskBuilder_ = null;
                }

                return this;
            }
            /**
             * <code>.Task task = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Task.Builder getTaskBuilder() {

                onChanged();
                return getTaskFieldBuilder().getBuilder();
            }
            /**
             * <code>.Task task = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.TaskOrBuilder getTaskOrBuilder() {
                if (taskBuilder_ != null) {
                    return taskBuilder_.getMessageOrBuilder();
                } else {
                    return task_ == null ?
                            org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance() : task_;
                }
            }
            /**
             * <code>.Task task = 3;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Task, org.example.protobuf.CompetitionProtobufs.Task.Builder, org.example.protobuf.CompetitionProtobufs.TaskOrBuilder>
            getTaskFieldBuilder() {
                if (taskBuilder_ == null) {
                    taskBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Task, org.example.protobuf.CompetitionProtobufs.Task.Builder, org.example.protobuf.CompetitionProtobufs.TaskOrBuilder>(
                            getTask(),
                            getParentForChildren(),
                            isClean());
                    task_ = null;
                }
                return taskBuilder_;
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


            // @@protoc_insertion_point(builder_scope:Participation)
        }

        // @@protoc_insertion_point(class_scope:Participation)
        private static final org.example.protobuf.CompetitionProtobufs.Participation DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new org.example.protobuf.CompetitionProtobufs.Participation();
        }

        public static org.example.protobuf.CompetitionProtobufs.Participation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Participation>
                PARSER = new com.google.protobuf.AbstractParser<Participation>() {
            @java.lang.Override
            public Participation parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Participation(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Participation> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Participation> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Participation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface RequestOrBuilder extends
            // @@protoc_insertion_point(interface_extends:Request)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Request.Type type = 1;</code>
         * @return The enum numeric value on the wire for type.
         */
        int getTypeValue();
        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Request.Type type = 1;</code>
         * @return The type.
         */
        org.example.protobuf.CompetitionProtobufs.Request.Type getType();

        /**
         * <code>.User user = 2;</code>
         * @return Whether the user field is set.
         */
        boolean hasUser();
        /**
         * <code>.User user = 2;</code>
         * @return The user.
         */
        org.example.protobuf.CompetitionProtobufs.User getUser();
        /**
         * <code>.User user = 2;</code>
         */
        org.example.protobuf.CompetitionProtobufs.UserOrBuilder getUserOrBuilder();

        /**
         * <code>.Participation participation = 3;</code>
         * @return Whether the participation field is set.
         */
        boolean hasParticipation();
        /**
         * <code>.Participation participation = 3;</code>
         * @return The participation.
         */
        org.example.protobuf.CompetitionProtobufs.Participation getParticipation();
        /**
         * <code>.Participation participation = 3;</code>
         */
        org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder getParticipationOrBuilder();

        /**
         * <code>.Child child = 4;</code>
         * @return Whether the child field is set.
         */
        boolean hasChild();
        /**
         * <code>.Child child = 4;</code>
         * @return The child.
         */
        org.example.protobuf.CompetitionProtobufs.Child getChild();
        /**
         * <code>.Child child = 4;</code>
         */
        org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder();

        /**
         * <code>.Task task = 5;</code>
         * @return Whether the task field is set.
         */
        boolean hasTask();
        /**
         * <code>.Task task = 5;</code>
         * @return The task.
         */
        org.example.protobuf.CompetitionProtobufs.Task getTask();
        /**
         * <code>.Task task = 5;</code>
         */
        org.example.protobuf.CompetitionProtobufs.TaskOrBuilder getTaskOrBuilder();

        public org.example.protobuf.CompetitionProtobufs.Request.PayloadCase getPayloadCase();
    }
    /**
     * Protobuf type {@code Request}
     */
    public static final class Request extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:Request)
            RequestOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use Request.newBuilder() to construct.
        private Request(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private Request() {
            type_ = 0;
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new Request();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private Request(
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
                        case 8: {
                            int rawValue = input.readEnum();

                            type_ = rawValue;
                            break;
                        }
                        case 18: {
                            org.example.protobuf.CompetitionProtobufs.User.Builder subBuilder = null;
                            if (payloadCase_ == 2) {
                                subBuilder = ((org.example.protobuf.CompetitionProtobufs.User) payload_).toBuilder();
                            }
                            payload_ =
                                    input.readMessage(org.example.protobuf.CompetitionProtobufs.User.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((org.example.protobuf.CompetitionProtobufs.User) payload_);
                                payload_ = subBuilder.buildPartial();
                            }
                            payloadCase_ = 2;
                            break;
                        }
                        case 26: {
                            org.example.protobuf.CompetitionProtobufs.Participation.Builder subBuilder = null;
                            if (payloadCase_ == 3) {
                                subBuilder = ((org.example.protobuf.CompetitionProtobufs.Participation) payload_).toBuilder();
                            }
                            payload_ =
                                    input.readMessage(org.example.protobuf.CompetitionProtobufs.Participation.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((org.example.protobuf.CompetitionProtobufs.Participation) payload_);
                                payload_ = subBuilder.buildPartial();
                            }
                            payloadCase_ = 3;
                            break;
                        }
                        case 34: {
                            org.example.protobuf.CompetitionProtobufs.Child.Builder subBuilder = null;
                            if (payloadCase_ == 4) {
                                subBuilder = ((org.example.protobuf.CompetitionProtobufs.Child) payload_).toBuilder();
                            }
                            payload_ =
                                    input.readMessage(org.example.protobuf.CompetitionProtobufs.Child.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((org.example.protobuf.CompetitionProtobufs.Child) payload_);
                                payload_ = subBuilder.buildPartial();
                            }
                            payloadCase_ = 4;
                            break;
                        }
                        case 42: {
                            org.example.protobuf.CompetitionProtobufs.Task.Builder subBuilder = null;
                            if (payloadCase_ == 5) {
                                subBuilder = ((org.example.protobuf.CompetitionProtobufs.Task) payload_).toBuilder();
                            }
                            payload_ =
                                    input.readMessage(org.example.protobuf.CompetitionProtobufs.Task.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((org.example.protobuf.CompetitionProtobufs.Task) payload_);
                                payload_ = subBuilder.buildPartial();
                            }
                            payloadCase_ = 5;
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
            return org.example.protobuf.CompetitionProtobufs.internal_static_Request_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_Request_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.example.protobuf.CompetitionProtobufs.Request.class, org.example.protobuf.CompetitionProtobufs.Request.Builder.class);
        }

        /**
         * Protobuf enum {@code Request.Type}
         */
        public enum Type
                implements com.google.protobuf.ProtocolMessageEnum {
            /**
             * <code>Unknown = 0;</code>
             */
            Unknown(0),
            /**
             * <code>Login = 1;</code>
             */
            Login(1),
            /**
             * <code>Logout = 2;</code>
             */
            Logout(2),
            /**
             * <code>Get_participants = 3;</code>
             */
            Get_participants(3),
            /**
             * <code>Add_participation = 4;</code>
             */
            Add_participation(4),
            /**
             * <code>Update = 5;</code>
             */
            Update(5),
            /**
             * <code>Add_child = 6;</code>
             */
            Add_child(6),
            /**
             * <code>Find_last_child = 7;</code>
             */
            Find_last_child(7),
            /**
             * <code>Get_tasks = 8;</code>
             */
            Get_tasks(8),
            UNRECOGNIZED(-1),
            ;

            /**
             * <code>Unknown = 0;</code>
             */
            public static final int Unknown_VALUE = 0;
            /**
             * <code>Login = 1;</code>
             */
            public static final int Login_VALUE = 1;
            /**
             * <code>Logout = 2;</code>
             */
            public static final int Logout_VALUE = 2;
            /**
             * <code>Get_participants = 3;</code>
             */
            public static final int Get_participants_VALUE = 3;
            /**
             * <code>Add_participation = 4;</code>
             */
            public static final int Add_participation_VALUE = 4;
            /**
             * <code>Update = 5;</code>
             */
            public static final int Update_VALUE = 5;
            /**
             * <code>Add_child = 6;</code>
             */
            public static final int Add_child_VALUE = 6;
            /**
             * <code>Find_last_child = 7;</code>
             */
            public static final int Find_last_child_VALUE = 7;
            /**
             * <code>Get_tasks = 8;</code>
             */
            public static final int Get_tasks_VALUE = 8;


            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new java.lang.IllegalArgumentException(
                            "Can't get the number of an unknown enum value.");
                }
                return value;
            }

            /**
             * @param value The numeric wire value of the corresponding enum entry.
             * @return The enum associated with the given numeric wire value.
             * @deprecated Use {@link #forNumber(int)} instead.
             */
            @java.lang.Deprecated
            public static Type valueOf(int value) {
                return forNumber(value);
            }

            /**
             * @param value The numeric wire value of the corresponding enum entry.
             * @return The enum associated with the given numeric wire value.
             */
            public static Type forNumber(int value) {
                switch (value) {
                    case 0: return Unknown;
                    case 1: return Login;
                    case 2: return Logout;
                    case 3: return Get_participants;
                    case 4: return Add_participation;
                    case 5: return Update;
                    case 6: return Add_child;
                    case 7: return Find_last_child;
                    case 8: return Get_tasks;
                    default: return null;
                }
            }

            public static com.google.protobuf.Internal.EnumLiteMap<Type>
            internalGetValueMap() {
                return internalValueMap;
            }
            private static final com.google.protobuf.Internal.EnumLiteMap<
                    Type> internalValueMap =
                    new com.google.protobuf.Internal.EnumLiteMap<Type>() {
                        public Type findValueByNumber(int number) {
                            return Type.forNumber(number);
                        }
                    };

            public final com.google.protobuf.Descriptors.EnumValueDescriptor
            getValueDescriptor() {
                if (this == UNRECOGNIZED) {
                    throw new java.lang.IllegalStateException(
                            "Can't get the descriptor of an unrecognized enum value.");
                }
                return getDescriptor().getValues().get(ordinal());
            }
            public final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptorForType() {
                return getDescriptor();
            }
            public static final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.Request.getDescriptor().getEnumTypes().get(0);
            }

            private static final Type[] VALUES = values();

            public static Type valueOf(
                    com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new java.lang.IllegalArgumentException(
                            "EnumValueDescriptor is not for this type.");
                }
                if (desc.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[desc.getIndex()];
            }

            private final int value;

            private Type(int value) {
                this.value = value;
            }

            // @@protoc_insertion_point(enum_scope:Request.Type)
        }

        private int payloadCase_ = 0;
        private java.lang.Object payload_;
        public enum PayloadCase
                implements com.google.protobuf.Internal.EnumLite,
                com.google.protobuf.AbstractMessage.InternalOneOfEnum {
            USER(2),
            PARTICIPATION(3),
            CHILD(4),
            TASK(5),
            PAYLOAD_NOT_SET(0);
            private final int value;
            private PayloadCase(int value) {
                this.value = value;
            }
            /**
             * @param value The number of the enum to look for.
             * @return The enum associated with the given number.
             * @deprecated Use {@link #forNumber(int)} instead.
             */
            @java.lang.Deprecated
            public static PayloadCase valueOf(int value) {
                return forNumber(value);
            }

            public static PayloadCase forNumber(int value) {
                switch (value) {
                    case 2: return USER;
                    case 3: return PARTICIPATION;
                    case 4: return CHILD;
                    case 5: return TASK;
                    case 0: return PAYLOAD_NOT_SET;
                    default: return null;
                }
            }
            public int getNumber() {
                return this.value;
            }
        };

        public PayloadCase
        getPayloadCase() {
            return PayloadCase.forNumber(
                    payloadCase_);
        }

        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Request.Type type = 1;</code>
         * @return The enum numeric value on the wire for type.
         */
        @java.lang.Override public int getTypeValue() {
            return type_;
        }
        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Request.Type type = 1;</code>
         * @return The type.
         */
        @java.lang.Override public org.example.protobuf.CompetitionProtobufs.Request.Type getType() {
            @SuppressWarnings("deprecation")
            org.example.protobuf.CompetitionProtobufs.Request.Type result = org.example.protobuf.CompetitionProtobufs.Request.Type.valueOf(type_);
            return result == null ? org.example.protobuf.CompetitionProtobufs.Request.Type.UNRECOGNIZED : result;
        }

        public static final int USER_FIELD_NUMBER = 2;
        /**
         * <code>.User user = 2;</code>
         * @return Whether the user field is set.
         */
        @java.lang.Override
        public boolean hasUser() {
            return payloadCase_ == 2;
        }
        /**
         * <code>.User user = 2;</code>
         * @return The user.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.User getUser() {
            if (payloadCase_ == 2) {
                return (org.example.protobuf.CompetitionProtobufs.User) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance();
        }
        /**
         * <code>.User user = 2;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.UserOrBuilder getUserOrBuilder() {
            if (payloadCase_ == 2) {
                return (org.example.protobuf.CompetitionProtobufs.User) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance();
        }

        public static final int PARTICIPATION_FIELD_NUMBER = 3;
        /**
         * <code>.Participation participation = 3;</code>
         * @return Whether the participation field is set.
         */
        @java.lang.Override
        public boolean hasParticipation() {
            return payloadCase_ == 3;
        }
        /**
         * <code>.Participation participation = 3;</code>
         * @return The participation.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Participation getParticipation() {
            if (payloadCase_ == 3) {
                return (org.example.protobuf.CompetitionProtobufs.Participation) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance();
        }
        /**
         * <code>.Participation participation = 3;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder getParticipationOrBuilder() {
            if (payloadCase_ == 3) {
                return (org.example.protobuf.CompetitionProtobufs.Participation) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance();
        }

        public static final int CHILD_FIELD_NUMBER = 4;
        /**
         * <code>.Child child = 4;</code>
         * @return Whether the child field is set.
         */
        @java.lang.Override
        public boolean hasChild() {
            return payloadCase_ == 4;
        }
        /**
         * <code>.Child child = 4;</code>
         * @return The child.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Child getChild() {
            if (payloadCase_ == 4) {
                return (org.example.protobuf.CompetitionProtobufs.Child) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance();
        }
        /**
         * <code>.Child child = 4;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder() {
            if (payloadCase_ == 4) {
                return (org.example.protobuf.CompetitionProtobufs.Child) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance();
        }

        public static final int TASK_FIELD_NUMBER = 5;
        /**
         * <code>.Task task = 5;</code>
         * @return Whether the task field is set.
         */
        @java.lang.Override
        public boolean hasTask() {
            return payloadCase_ == 5;
        }
        /**
         * <code>.Task task = 5;</code>
         * @return The task.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Task getTask() {
            if (payloadCase_ == 5) {
                return (org.example.protobuf.CompetitionProtobufs.Task) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance();
        }
        /**
         * <code>.Task task = 5;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.TaskOrBuilder getTaskOrBuilder() {
            if (payloadCase_ == 5) {
                return (org.example.protobuf.CompetitionProtobufs.Task) payload_;
            }
            return org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance();
        }

        private byte memoizedIsInitialized = -1;
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
            if (type_ != org.example.protobuf.CompetitionProtobufs.Request.Type.Unknown.getNumber()) {
                output.writeEnum(1, type_);
            }
            if (payloadCase_ == 2) {
                output.writeMessage(2, (org.example.protobuf.CompetitionProtobufs.User) payload_);
            }
            if (payloadCase_ == 3) {
                output.writeMessage(3, (org.example.protobuf.CompetitionProtobufs.Participation) payload_);
            }
            if (payloadCase_ == 4) {
                output.writeMessage(4, (org.example.protobuf.CompetitionProtobufs.Child) payload_);
            }
            if (payloadCase_ == 5) {
                output.writeMessage(5, (org.example.protobuf.CompetitionProtobufs.Task) payload_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (type_ != org.example.protobuf.CompetitionProtobufs.Request.Type.Unknown.getNumber()) {
                size += com.google.protobuf.CodedOutputStream
                        .computeEnumSize(1, type_);
            }
            if (payloadCase_ == 2) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(2, (org.example.protobuf.CompetitionProtobufs.User) payload_);
            }
            if (payloadCase_ == 3) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(3, (org.example.protobuf.CompetitionProtobufs.Participation) payload_);
            }
            if (payloadCase_ == 4) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(4, (org.example.protobuf.CompetitionProtobufs.Child) payload_);
            }
            if (payloadCase_ == 5) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(5, (org.example.protobuf.CompetitionProtobufs.Task) payload_);
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
            if (!(obj instanceof org.example.protobuf.CompetitionProtobufs.Request)) {
                return super.equals(obj);
            }
            org.example.protobuf.CompetitionProtobufs.Request other = (org.example.protobuf.CompetitionProtobufs.Request) obj;

            if (type_ != other.type_) return false;
            if (!getPayloadCase().equals(other.getPayloadCase())) return false;
            switch (payloadCase_) {
                case 2:
                    if (!getUser()
                            .equals(other.getUser())) return false;
                    break;
                case 3:
                    if (!getParticipation()
                            .equals(other.getParticipation())) return false;
                    break;
                case 4:
                    if (!getChild()
                            .equals(other.getChild())) return false;
                    break;
                case 5:
                    if (!getTask()
                            .equals(other.getTask())) return false;
                    break;
                case 0:
                default:
            }
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
            hash = (37 * hash) + TYPE_FIELD_NUMBER;
            hash = (53 * hash) + type_;
            switch (payloadCase_) {
                case 2:
                    hash = (37 * hash) + USER_FIELD_NUMBER;
                    hash = (53 * hash) + getUser().hashCode();
                    break;
                case 3:
                    hash = (37 * hash) + PARTICIPATION_FIELD_NUMBER;
                    hash = (53 * hash) + getParticipation().hashCode();
                    break;
                case 4:
                    hash = (37 * hash) + CHILD_FIELD_NUMBER;
                    hash = (53 * hash) + getChild().hashCode();
                    break;
                case 5:
                    hash = (37 * hash) + TASK_FIELD_NUMBER;
                    hash = (53 * hash) + getTask().hashCode();
                    break;
                case 0:
                default:
            }
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Request parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.example.protobuf.CompetitionProtobufs.Request prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
        /**
         * Protobuf type {@code Request}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:Request)
                org.example.protobuf.CompetitionProtobufs.RequestOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Request_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Request_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                org.example.protobuf.CompetitionProtobufs.Request.class, org.example.protobuf.CompetitionProtobufs.Request.Builder.class);
            }

            // Construct using org.example.protobuf.CompetitionProtobufs.Request.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                type_ = 0;

                payloadCase_ = 0;
                payload_ = null;
                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Request_descriptor;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Request getDefaultInstanceForType() {
                return org.example.protobuf.CompetitionProtobufs.Request.getDefaultInstance();
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Request build() {
                org.example.protobuf.CompetitionProtobufs.Request result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Request buildPartial() {
                org.example.protobuf.CompetitionProtobufs.Request result = new org.example.protobuf.CompetitionProtobufs.Request(this);
                result.type_ = type_;
                if (payloadCase_ == 2) {
                    if (userBuilder_ == null) {
                        result.payload_ = payload_;
                    } else {
                        result.payload_ = userBuilder_.build();
                    }
                }
                if (payloadCase_ == 3) {
                    if (participationBuilder_ == null) {
                        result.payload_ = payload_;
                    } else {
                        result.payload_ = participationBuilder_.build();
                    }
                }
                if (payloadCase_ == 4) {
                    if (childBuilder_ == null) {
                        result.payload_ = payload_;
                    } else {
                        result.payload_ = childBuilder_.build();
                    }
                }
                if (payloadCase_ == 5) {
                    if (taskBuilder_ == null) {
                        result.payload_ = payload_;
                    } else {
                        result.payload_ = taskBuilder_.build();
                    }
                }
                result.payloadCase_ = payloadCase_;
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
                if (other instanceof org.example.protobuf.CompetitionProtobufs.Request) {
                    return mergeFrom((org.example.protobuf.CompetitionProtobufs.Request)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(org.example.protobuf.CompetitionProtobufs.Request other) {
                if (other == org.example.protobuf.CompetitionProtobufs.Request.getDefaultInstance()) return this;
                if (other.type_ != 0) {
                    setTypeValue(other.getTypeValue());
                }
                switch (other.getPayloadCase()) {
                    case USER: {
                        mergeUser(other.getUser());
                        break;
                    }
                    case PARTICIPATION: {
                        mergeParticipation(other.getParticipation());
                        break;
                    }
                    case CHILD: {
                        mergeChild(other.getChild());
                        break;
                    }
                    case TASK: {
                        mergeTask(other.getTask());
                        break;
                    }
                    case PAYLOAD_NOT_SET: {
                        break;
                    }
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
                org.example.protobuf.CompetitionProtobufs.Request parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (org.example.protobuf.CompetitionProtobufs.Request) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
            private int payloadCase_ = 0;
            private java.lang.Object payload_;
            public PayloadCase
            getPayloadCase() {
                return PayloadCase.forNumber(
                        payloadCase_);
            }

            public Builder clearPayload() {
                payloadCase_ = 0;
                payload_ = null;
                onChanged();
                return this;
            }


            private int type_ = 0;
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Request.Type type = 1;</code>
             * @return The enum numeric value on the wire for type.
             */
            @java.lang.Override public int getTypeValue() {
                return type_;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Request.Type type = 1;</code>
             * @param value The enum numeric value on the wire for type to set.
             * @return This builder for chaining.
             */
            public Builder setTypeValue(int value) {

                type_ = value;
                onChanged();
                return this;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Request.Type type = 1;</code>
             * @return The type.
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Request.Type getType() {
                @SuppressWarnings("deprecation")
                org.example.protobuf.CompetitionProtobufs.Request.Type result = org.example.protobuf.CompetitionProtobufs.Request.Type.valueOf(type_);
                return result == null ? org.example.protobuf.CompetitionProtobufs.Request.Type.UNRECOGNIZED : result;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Request.Type type = 1;</code>
             * @param value The type to set.
             * @return This builder for chaining.
             */
            public Builder setType(org.example.protobuf.CompetitionProtobufs.Request.Type value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                type_ = value.getNumber();
                onChanged();
                return this;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Request.Type type = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearType() {

                type_ = 0;
                onChanged();
                return this;
            }

            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.User, org.example.protobuf.CompetitionProtobufs.User.Builder, org.example.protobuf.CompetitionProtobufs.UserOrBuilder> userBuilder_;
            /**
             * <code>.User user = 2;</code>
             * @return Whether the user field is set.
             */
            @java.lang.Override
            public boolean hasUser() {
                return payloadCase_ == 2;
            }
            /**
             * <code>.User user = 2;</code>
             * @return The user.
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.User getUser() {
                if (userBuilder_ == null) {
                    if (payloadCase_ == 2) {
                        return (org.example.protobuf.CompetitionProtobufs.User) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance();
                } else {
                    if (payloadCase_ == 2) {
                        return userBuilder_.getMessage();
                    }
                    return org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance();
                }
            }
            /**
             * <code>.User user = 2;</code>
             */
            public Builder setUser(org.example.protobuf.CompetitionProtobufs.User value) {
                if (userBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    payload_ = value;
                    onChanged();
                } else {
                    userBuilder_.setMessage(value);
                }
                payloadCase_ = 2;
                return this;
            }
            /**
             * <code>.User user = 2;</code>
             */
            public Builder setUser(
                    org.example.protobuf.CompetitionProtobufs.User.Builder builderForValue) {
                if (userBuilder_ == null) {
                    payload_ = builderForValue.build();
                    onChanged();
                } else {
                    userBuilder_.setMessage(builderForValue.build());
                }
                payloadCase_ = 2;
                return this;
            }
            /**
             * <code>.User user = 2;</code>
             */
            public Builder mergeUser(org.example.protobuf.CompetitionProtobufs.User value) {
                if (userBuilder_ == null) {
                    if (payloadCase_ == 2 &&
                            payload_ != org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance()) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.User.newBuilder((org.example.protobuf.CompetitionProtobufs.User) payload_)
                                .mergeFrom(value).buildPartial();
                    } else {
                        payload_ = value;
                    }
                    onChanged();
                } else {
                    if (payloadCase_ == 2) {
                        userBuilder_.mergeFrom(value);
                    }
                    userBuilder_.setMessage(value);
                }
                payloadCase_ = 2;
                return this;
            }
            /**
             * <code>.User user = 2;</code>
             */
            public Builder clearUser() {
                if (userBuilder_ == null) {
                    if (payloadCase_ == 2) {
                        payloadCase_ = 0;
                        payload_ = null;
                        onChanged();
                    }
                } else {
                    if (payloadCase_ == 2) {
                        payloadCase_ = 0;
                        payload_ = null;
                    }
                    userBuilder_.clear();
                }
                return this;
            }
            /**
             * <code>.User user = 2;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.User.Builder getUserBuilder() {
                return getUserFieldBuilder().getBuilder();
            }
            /**
             * <code>.User user = 2;</code>
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.UserOrBuilder getUserOrBuilder() {
                if ((payloadCase_ == 2) && (userBuilder_ != null)) {
                    return userBuilder_.getMessageOrBuilder();
                } else {
                    if (payloadCase_ == 2) {
                        return (org.example.protobuf.CompetitionProtobufs.User) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance();
                }
            }
            /**
             * <code>.User user = 2;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.User, org.example.protobuf.CompetitionProtobufs.User.Builder, org.example.protobuf.CompetitionProtobufs.UserOrBuilder>
            getUserFieldBuilder() {
                if (userBuilder_ == null) {
                    if (!(payloadCase_ == 2)) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance();
                    }
                    userBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.User, org.example.protobuf.CompetitionProtobufs.User.Builder, org.example.protobuf.CompetitionProtobufs.UserOrBuilder>(
                            (org.example.protobuf.CompetitionProtobufs.User) payload_,
                            getParentForChildren(),
                            isClean());
                    payload_ = null;
                }
                payloadCase_ = 2;
                onChanged();;
                return userBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Participation, org.example.protobuf.CompetitionProtobufs.Participation.Builder, org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder> participationBuilder_;
            /**
             * <code>.Participation participation = 3;</code>
             * @return Whether the participation field is set.
             */
            @java.lang.Override
            public boolean hasParticipation() {
                return payloadCase_ == 3;
            }
            /**
             * <code>.Participation participation = 3;</code>
             * @return The participation.
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Participation getParticipation() {
                if (participationBuilder_ == null) {
                    if (payloadCase_ == 3) {
                        return (org.example.protobuf.CompetitionProtobufs.Participation) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance();
                } else {
                    if (payloadCase_ == 3) {
                        return participationBuilder_.getMessage();
                    }
                    return org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance();
                }
            }
            /**
             * <code>.Participation participation = 3;</code>
             */
            public Builder setParticipation(org.example.protobuf.CompetitionProtobufs.Participation value) {
                if (participationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    payload_ = value;
                    onChanged();
                } else {
                    participationBuilder_.setMessage(value);
                }
                payloadCase_ = 3;
                return this;
            }
            /**
             * <code>.Participation participation = 3;</code>
             */
            public Builder setParticipation(
                    org.example.protobuf.CompetitionProtobufs.Participation.Builder builderForValue) {
                if (participationBuilder_ == null) {
                    payload_ = builderForValue.build();
                    onChanged();
                } else {
                    participationBuilder_.setMessage(builderForValue.build());
                }
                payloadCase_ = 3;
                return this;
            }
            /**
             * <code>.Participation participation = 3;</code>
             */
            public Builder mergeParticipation(org.example.protobuf.CompetitionProtobufs.Participation value) {
                if (participationBuilder_ == null) {
                    if (payloadCase_ == 3 &&
                            payload_ != org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance()) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.Participation.newBuilder((org.example.protobuf.CompetitionProtobufs.Participation) payload_)
                                .mergeFrom(value).buildPartial();
                    } else {
                        payload_ = value;
                    }
                    onChanged();
                } else {
                    if (payloadCase_ == 3) {
                        participationBuilder_.mergeFrom(value);
                    }
                    participationBuilder_.setMessage(value);
                }
                payloadCase_ = 3;
                return this;
            }
            /**
             * <code>.Participation participation = 3;</code>
             */
            public Builder clearParticipation() {
                if (participationBuilder_ == null) {
                    if (payloadCase_ == 3) {
                        payloadCase_ = 0;
                        payload_ = null;
                        onChanged();
                    }
                } else {
                    if (payloadCase_ == 3) {
                        payloadCase_ = 0;
                        payload_ = null;
                    }
                    participationBuilder_.clear();
                }
                return this;
            }
            /**
             * <code>.Participation participation = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Participation.Builder getParticipationBuilder() {
                return getParticipationFieldBuilder().getBuilder();
            }
            /**
             * <code>.Participation participation = 3;</code>
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder getParticipationOrBuilder() {
                if ((payloadCase_ == 3) && (participationBuilder_ != null)) {
                    return participationBuilder_.getMessageOrBuilder();
                } else {
                    if (payloadCase_ == 3) {
                        return (org.example.protobuf.CompetitionProtobufs.Participation) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance();
                }
            }
            /**
             * <code>.Participation participation = 3;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Participation, org.example.protobuf.CompetitionProtobufs.Participation.Builder, org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder>
            getParticipationFieldBuilder() {
                if (participationBuilder_ == null) {
                    if (!(payloadCase_ == 3)) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance();
                    }
                    participationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Participation, org.example.protobuf.CompetitionProtobufs.Participation.Builder, org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder>(
                            (org.example.protobuf.CompetitionProtobufs.Participation) payload_,
                            getParentForChildren(),
                            isClean());
                    payload_ = null;
                }
                payloadCase_ = 3;
                onChanged();;
                return participationBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder> childBuilder_;
            /**
             * <code>.Child child = 4;</code>
             * @return Whether the child field is set.
             */
            @java.lang.Override
            public boolean hasChild() {
                return payloadCase_ == 4;
            }
            /**
             * <code>.Child child = 4;</code>
             * @return The child.
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Child getChild() {
                if (childBuilder_ == null) {
                    if (payloadCase_ == 4) {
                        return (org.example.protobuf.CompetitionProtobufs.Child) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance();
                } else {
                    if (payloadCase_ == 4) {
                        return childBuilder_.getMessage();
                    }
                    return org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance();
                }
            }
            /**
             * <code>.Child child = 4;</code>
             */
            public Builder setChild(org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    payload_ = value;
                    onChanged();
                } else {
                    childBuilder_.setMessage(value);
                }
                payloadCase_ = 4;
                return this;
            }
            /**
             * <code>.Child child = 4;</code>
             */
            public Builder setChild(
                    org.example.protobuf.CompetitionProtobufs.Child.Builder builderForValue) {
                if (childBuilder_ == null) {
                    payload_ = builderForValue.build();
                    onChanged();
                } else {
                    childBuilder_.setMessage(builderForValue.build());
                }
                payloadCase_ = 4;
                return this;
            }
            /**
             * <code>.Child child = 4;</code>
             */
            public Builder mergeChild(org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childBuilder_ == null) {
                    if (payloadCase_ == 4 &&
                            payload_ != org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance()) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.Child.newBuilder((org.example.protobuf.CompetitionProtobufs.Child) payload_)
                                .mergeFrom(value).buildPartial();
                    } else {
                        payload_ = value;
                    }
                    onChanged();
                } else {
                    if (payloadCase_ == 4) {
                        childBuilder_.mergeFrom(value);
                    }
                    childBuilder_.setMessage(value);
                }
                payloadCase_ = 4;
                return this;
            }
            /**
             * <code>.Child child = 4;</code>
             */
            public Builder clearChild() {
                if (childBuilder_ == null) {
                    if (payloadCase_ == 4) {
                        payloadCase_ = 0;
                        payload_ = null;
                        onChanged();
                    }
                } else {
                    if (payloadCase_ == 4) {
                        payloadCase_ = 0;
                        payload_ = null;
                    }
                    childBuilder_.clear();
                }
                return this;
            }
            /**
             * <code>.Child child = 4;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Child.Builder getChildBuilder() {
                return getChildFieldBuilder().getBuilder();
            }
            /**
             * <code>.Child child = 4;</code>
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder() {
                if ((payloadCase_ == 4) && (childBuilder_ != null)) {
                    return childBuilder_.getMessageOrBuilder();
                } else {
                    if (payloadCase_ == 4) {
                        return (org.example.protobuf.CompetitionProtobufs.Child) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance();
                }
            }
            /**
             * <code>.Child child = 4;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>
            getChildFieldBuilder() {
                if (childBuilder_ == null) {
                    if (!(payloadCase_ == 4)) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance();
                    }
                    childBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>(
                            (org.example.protobuf.CompetitionProtobufs.Child) payload_,
                            getParentForChildren(),
                            isClean());
                    payload_ = null;
                }
                payloadCase_ = 4;
                onChanged();;
                return childBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Task, org.example.protobuf.CompetitionProtobufs.Task.Builder, org.example.protobuf.CompetitionProtobufs.TaskOrBuilder> taskBuilder_;
            /**
             * <code>.Task task = 5;</code>
             * @return Whether the task field is set.
             */
            @java.lang.Override
            public boolean hasTask() {
                return payloadCase_ == 5;
            }
            /**
             * <code>.Task task = 5;</code>
             * @return The task.
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Task getTask() {
                if (taskBuilder_ == null) {
                    if (payloadCase_ == 5) {
                        return (org.example.protobuf.CompetitionProtobufs.Task) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance();
                } else {
                    if (payloadCase_ == 5) {
                        return taskBuilder_.getMessage();
                    }
                    return org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance();
                }
            }
            /**
             * <code>.Task task = 5;</code>
             */
            public Builder setTask(org.example.protobuf.CompetitionProtobufs.Task value) {
                if (taskBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    payload_ = value;
                    onChanged();
                } else {
                    taskBuilder_.setMessage(value);
                }
                payloadCase_ = 5;
                return this;
            }
            /**
             * <code>.Task task = 5;</code>
             */
            public Builder setTask(
                    org.example.protobuf.CompetitionProtobufs.Task.Builder builderForValue) {
                if (taskBuilder_ == null) {
                    payload_ = builderForValue.build();
                    onChanged();
                } else {
                    taskBuilder_.setMessage(builderForValue.build());
                }
                payloadCase_ = 5;
                return this;
            }
            /**
             * <code>.Task task = 5;</code>
             */
            public Builder mergeTask(org.example.protobuf.CompetitionProtobufs.Task value) {
                if (taskBuilder_ == null) {
                    if (payloadCase_ == 5 &&
                            payload_ != org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance()) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.Task.newBuilder((org.example.protobuf.CompetitionProtobufs.Task) payload_)
                                .mergeFrom(value).buildPartial();
                    } else {
                        payload_ = value;
                    }
                    onChanged();
                } else {
                    if (payloadCase_ == 5) {
                        taskBuilder_.mergeFrom(value);
                    }
                    taskBuilder_.setMessage(value);
                }
                payloadCase_ = 5;
                return this;
            }
            /**
             * <code>.Task task = 5;</code>
             */
            public Builder clearTask() {
                if (taskBuilder_ == null) {
                    if (payloadCase_ == 5) {
                        payloadCase_ = 0;
                        payload_ = null;
                        onChanged();
                    }
                } else {
                    if (payloadCase_ == 5) {
                        payloadCase_ = 0;
                        payload_ = null;
                    }
                    taskBuilder_.clear();
                }
                return this;
            }
            /**
             * <code>.Task task = 5;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Task.Builder getTaskBuilder() {
                return getTaskFieldBuilder().getBuilder();
            }
            /**
             * <code>.Task task = 5;</code>
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.TaskOrBuilder getTaskOrBuilder() {
                if ((payloadCase_ == 5) && (taskBuilder_ != null)) {
                    return taskBuilder_.getMessageOrBuilder();
                } else {
                    if (payloadCase_ == 5) {
                        return (org.example.protobuf.CompetitionProtobufs.Task) payload_;
                    }
                    return org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance();
                }
            }
            /**
             * <code>.Task task = 5;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Task, org.example.protobuf.CompetitionProtobufs.Task.Builder, org.example.protobuf.CompetitionProtobufs.TaskOrBuilder>
            getTaskFieldBuilder() {
                if (taskBuilder_ == null) {
                    if (!(payloadCase_ == 5)) {
                        payload_ = org.example.protobuf.CompetitionProtobufs.Task.getDefaultInstance();
                    }
                    taskBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Task, org.example.protobuf.CompetitionProtobufs.Task.Builder, org.example.protobuf.CompetitionProtobufs.TaskOrBuilder>(
                            (org.example.protobuf.CompetitionProtobufs.Task) payload_,
                            getParentForChildren(),
                            isClean());
                    payload_ = null;
                }
                payloadCase_ = 5;
                onChanged();;
                return taskBuilder_;
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


            // @@protoc_insertion_point(builder_scope:Request)
        }

        // @@protoc_insertion_point(class_scope:Request)
        private static final org.example.protobuf.CompetitionProtobufs.Request DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new org.example.protobuf.CompetitionProtobufs.Request();
        }

        public static org.example.protobuf.CompetitionProtobufs.Request getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Request>
                PARSER = new com.google.protobuf.AbstractParser<Request>() {
            @java.lang.Override
            public Request parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Request(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Request> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Request> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Request getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface ResponseOrBuilder extends
            // @@protoc_insertion_point(interface_extends:Response)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Response.Type type = 1;</code>
         * @return The enum numeric value on the wire for type.
         */
        int getTypeValue();
        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Response.Type type = 1;</code>
         * @return The type.
         */
        org.example.protobuf.CompetitionProtobufs.Response.Type getType();

        /**
         * <pre>
         *One of the following will be filled in, depending on the type.
         * </pre>
         *
         * <code>string error = 2;</code>
         * @return The error.
         */
        java.lang.String getError();
        /**
         * <pre>
         *One of the following will be filled in, depending on the type.
         * </pre>
         *
         * <code>string error = 2;</code>
         * @return The bytes for error.
         */
        com.google.protobuf.ByteString
        getErrorBytes();

        /**
         * <code>repeated .Child children = 3;</code>
         */
        java.util.List<org.example.protobuf.CompetitionProtobufs.Child>
        getChildrenList();
        /**
         * <code>repeated .Child children = 3;</code>
         */
        org.example.protobuf.CompetitionProtobufs.Child getChildren(int index);
        /**
         * <code>repeated .Child children = 3;</code>
         */
        int getChildrenCount();
        /**
         * <code>repeated .Child children = 3;</code>
         */
        java.util.List<? extends org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>
        getChildrenOrBuilderList();
        /**
         * <code>repeated .Child children = 3;</code>
         */
        org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildrenOrBuilder(
                int index);

        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        java.util.List<org.example.protobuf.CompetitionProtobufs.TaskDTO>
        getTasksList();
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        org.example.protobuf.CompetitionProtobufs.TaskDTO getTasks(int index);
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        int getTasksCount();
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        java.util.List<? extends org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder>
        getTasksOrBuilderList();
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder getTasksOrBuilder(
                int index);

        /**
         * <code>.User user = 5;</code>
         * @return Whether the user field is set.
         */
        boolean hasUser();
        /**
         * <code>.User user = 5;</code>
         * @return The user.
         */
        org.example.protobuf.CompetitionProtobufs.User getUser();
        /**
         * <code>.User user = 5;</code>
         */
        org.example.protobuf.CompetitionProtobufs.UserOrBuilder getUserOrBuilder();

        /**
         * <code>.Child child = 6;</code>
         * @return Whether the child field is set.
         */
        boolean hasChild();
        /**
         * <code>.Child child = 6;</code>
         * @return The child.
         */
        org.example.protobuf.CompetitionProtobufs.Child getChild();
        /**
         * <code>.Child child = 6;</code>
         */
        org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder();

        /**
         * <code>.Participation participation = 7;</code>
         * @return Whether the participation field is set.
         */
        boolean hasParticipation();
        /**
         * <code>.Participation participation = 7;</code>
         * @return The participation.
         */
        org.example.protobuf.CompetitionProtobufs.Participation getParticipation();
        /**
         * <code>.Participation participation = 7;</code>
         */
        org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder getParticipationOrBuilder();
    }
    /**
     * Protobuf type {@code Response}
     */
    public static final class Response extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:Response)
            ResponseOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use Response.newBuilder() to construct.
        private Response(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private Response() {
            type_ = 0;
            error_ = "";
            children_ = java.util.Collections.emptyList();
            tasks_ = java.util.Collections.emptyList();
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new Response();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private Response(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
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
                        case 8: {
                            int rawValue = input.readEnum();

                            type_ = rawValue;
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            error_ = s;
                            break;
                        }
                        case 26: {
                            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                                children_ = new java.util.ArrayList<org.example.protobuf.CompetitionProtobufs.Child>();
                                mutable_bitField0_ |= 0x00000001;
                            }
                            children_.add(
                                    input.readMessage(org.example.protobuf.CompetitionProtobufs.Child.parser(), extensionRegistry));
                            break;
                        }
                        case 34: {
                            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
                                tasks_ = new java.util.ArrayList<org.example.protobuf.CompetitionProtobufs.TaskDTO>();
                                mutable_bitField0_ |= 0x00000002;
                            }
                            tasks_.add(
                                    input.readMessage(org.example.protobuf.CompetitionProtobufs.TaskDTO.parser(), extensionRegistry));
                            break;
                        }
                        case 42: {
                            org.example.protobuf.CompetitionProtobufs.User.Builder subBuilder = null;
                            if (user_ != null) {
                                subBuilder = user_.toBuilder();
                            }
                            user_ = input.readMessage(org.example.protobuf.CompetitionProtobufs.User.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(user_);
                                user_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 50: {
                            org.example.protobuf.CompetitionProtobufs.Child.Builder subBuilder = null;
                            if (child_ != null) {
                                subBuilder = child_.toBuilder();
                            }
                            child_ = input.readMessage(org.example.protobuf.CompetitionProtobufs.Child.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(child_);
                                child_ = subBuilder.buildPartial();
                            }

                            break;
                        }
                        case 58: {
                            org.example.protobuf.CompetitionProtobufs.Participation.Builder subBuilder = null;
                            if (participation_ != null) {
                                subBuilder = participation_.toBuilder();
                            }
                            participation_ = input.readMessage(org.example.protobuf.CompetitionProtobufs.Participation.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(participation_);
                                participation_ = subBuilder.buildPartial();
                            }

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
                if (((mutable_bitField0_ & 0x00000001) != 0)) {
                    children_ = java.util.Collections.unmodifiableList(children_);
                }
                if (((mutable_bitField0_ & 0x00000002) != 0)) {
                    tasks_ = java.util.Collections.unmodifiableList(tasks_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_Response_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.example.protobuf.CompetitionProtobufs.internal_static_Response_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.example.protobuf.CompetitionProtobufs.Response.class, org.example.protobuf.CompetitionProtobufs.Response.Builder.class);
        }

        /**
         * Protobuf enum {@code Response.Type}
         */
        public enum Type
                implements com.google.protobuf.ProtocolMessageEnum {
            /**
             * <code>Unknown = 0;</code>
             */
            Unknown(0),
            /**
             * <code>Login = 1;</code>
             */
            Login(1),
            /**
             * <code>Logout = 2;</code>
             */
            Logout(2),
            /**
             * <code>Get_participants = 3;</code>
             */
            Get_participants(3),
            /**
             * <code>Add_participation = 4;</code>
             */
            Add_participation(4),
            /**
             * <code>Update = 5;</code>
             */
            Update(5),
            /**
             * <code>Add_child = 6;</code>
             */
            Add_child(6),
            /**
             * <code>Find_last_child = 7;</code>
             */
            Find_last_child(7),
            /**
             * <code>Ok = 8;</code>
             */
            Ok(8),
            /**
             * <code>Error = 9;</code>
             */
            Error(9),
            /**
             * <code>Get_tasks = 10;</code>
             */
            Get_tasks(10),
            UNRECOGNIZED(-1),
            ;

            /**
             * <code>Unknown = 0;</code>
             */
            public static final int Unknown_VALUE = 0;
            /**
             * <code>Login = 1;</code>
             */
            public static final int Login_VALUE = 1;
            /**
             * <code>Logout = 2;</code>
             */
            public static final int Logout_VALUE = 2;
            /**
             * <code>Get_participants = 3;</code>
             */
            public static final int Get_participants_VALUE = 3;
            /**
             * <code>Add_participation = 4;</code>
             */
            public static final int Add_participation_VALUE = 4;
            /**
             * <code>Update = 5;</code>
             */
            public static final int Update_VALUE = 5;
            /**
             * <code>Add_child = 6;</code>
             */
            public static final int Add_child_VALUE = 6;
            /**
             * <code>Find_last_child = 7;</code>
             */
            public static final int Find_last_child_VALUE = 7;
            /**
             * <code>Ok = 8;</code>
             */
            public static final int Ok_VALUE = 8;
            /**
             * <code>Error = 9;</code>
             */
            public static final int Error_VALUE = 9;
            /**
             * <code>Get_tasks = 10;</code>
             */
            public static final int Get_tasks_VALUE = 10;


            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new java.lang.IllegalArgumentException(
                            "Can't get the number of an unknown enum value.");
                }
                return value;
            }

            /**
             * @param value The numeric wire value of the corresponding enum entry.
             * @return The enum associated with the given numeric wire value.
             * @deprecated Use {@link #forNumber(int)} instead.
             */
            @java.lang.Deprecated
            public static Type valueOf(int value) {
                return forNumber(value);
            }

            /**
             * @param value The numeric wire value of the corresponding enum entry.
             * @return The enum associated with the given numeric wire value.
             */
            public static Type forNumber(int value) {
                switch (value) {
                    case 0: return Unknown;
                    case 1: return Login;
                    case 2: return Logout;
                    case 3: return Get_participants;
                    case 4: return Add_participation;
                    case 5: return Update;
                    case 6: return Add_child;
                    case 7: return Find_last_child;
                    case 8: return Ok;
                    case 9: return Error;
                    case 10: return Get_tasks;
                    default: return null;
                }
            }

            public static com.google.protobuf.Internal.EnumLiteMap<Type>
            internalGetValueMap() {
                return internalValueMap;
            }
            private static final com.google.protobuf.Internal.EnumLiteMap<
                    Type> internalValueMap =
                    new com.google.protobuf.Internal.EnumLiteMap<Type>() {
                        public Type findValueByNumber(int number) {
                            return Type.forNumber(number);
                        }
                    };

            public final com.google.protobuf.Descriptors.EnumValueDescriptor
            getValueDescriptor() {
                if (this == UNRECOGNIZED) {
                    throw new java.lang.IllegalStateException(
                            "Can't get the descriptor of an unrecognized enum value.");
                }
                return getDescriptor().getValues().get(ordinal());
            }
            public final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptorForType() {
                return getDescriptor();
            }
            public static final com.google.protobuf.Descriptors.EnumDescriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.Response.getDescriptor().getEnumTypes().get(0);
            }

            private static final Type[] VALUES = values();

            public static Type valueOf(
                    com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new java.lang.IllegalArgumentException(
                            "EnumValueDescriptor is not for this type.");
                }
                if (desc.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[desc.getIndex()];
            }

            private final int value;

            private Type(int value) {
                this.value = value;
            }

            // @@protoc_insertion_point(enum_scope:Response.Type)
        }

        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Response.Type type = 1;</code>
         * @return The enum numeric value on the wire for type.
         */
        @java.lang.Override public int getTypeValue() {
            return type_;
        }
        /**
         * <pre>
         * Identifies witch request is filled in.
         * </pre>
         *
         * <code>.Response.Type type = 1;</code>
         * @return The type.
         */
        @java.lang.Override public org.example.protobuf.CompetitionProtobufs.Response.Type getType() {
            @SuppressWarnings("deprecation")
            org.example.protobuf.CompetitionProtobufs.Response.Type result = org.example.protobuf.CompetitionProtobufs.Response.Type.valueOf(type_);
            return result == null ? org.example.protobuf.CompetitionProtobufs.Response.Type.UNRECOGNIZED : result;
        }

        public static final int ERROR_FIELD_NUMBER = 2;
        private volatile java.lang.Object error_;
        /**
         * <pre>
         *One of the following will be filled in, depending on the type.
         * </pre>
         *
         * <code>string error = 2;</code>
         * @return The error.
         */
        @java.lang.Override
        public java.lang.String getError() {
            java.lang.Object ref = error_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                error_ = s;
                return s;
            }
        }
        /**
         * <pre>
         *One of the following will be filled in, depending on the type.
         * </pre>
         *
         * <code>string error = 2;</code>
         * @return The bytes for error.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getErrorBytes() {
            java.lang.Object ref = error_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                error_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int CHILDREN_FIELD_NUMBER = 3;
        private java.util.List<org.example.protobuf.CompetitionProtobufs.Child> children_;
        /**
         * <code>repeated .Child children = 3;</code>
         */
        @java.lang.Override
        public java.util.List<org.example.protobuf.CompetitionProtobufs.Child> getChildrenList() {
            return children_;
        }
        /**
         * <code>repeated .Child children = 3;</code>
         */
        @java.lang.Override
        public java.util.List<? extends org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>
        getChildrenOrBuilderList() {
            return children_;
        }
        /**
         * <code>repeated .Child children = 3;</code>
         */
        @java.lang.Override
        public int getChildrenCount() {
            return children_.size();
        }
        /**
         * <code>repeated .Child children = 3;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Child getChildren(int index) {
            return children_.get(index);
        }
        /**
         * <code>repeated .Child children = 3;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildrenOrBuilder(
                int index) {
            return children_.get(index);
        }

        public static final int TASKS_FIELD_NUMBER = 4;
        private java.util.List<org.example.protobuf.CompetitionProtobufs.TaskDTO> tasks_;
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        @java.lang.Override
        public java.util.List<org.example.protobuf.CompetitionProtobufs.TaskDTO> getTasksList() {
            return tasks_;
        }
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        @java.lang.Override
        public java.util.List<? extends org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder>
        getTasksOrBuilderList() {
            return tasks_;
        }
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        @java.lang.Override
        public int getTasksCount() {
            return tasks_.size();
        }
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.TaskDTO getTasks(int index) {
            return tasks_.get(index);
        }
        /**
         * <code>repeated .TaskDTO tasks = 4;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder getTasksOrBuilder(
                int index) {
            return tasks_.get(index);
        }

        public static final int USER_FIELD_NUMBER = 5;
        private org.example.protobuf.CompetitionProtobufs.User user_;
        /**
         * <code>.User user = 5;</code>
         * @return Whether the user field is set.
         */
        @java.lang.Override
        public boolean hasUser() {
            return user_ != null;
        }
        /**
         * <code>.User user = 5;</code>
         * @return The user.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.User getUser() {
            return user_ == null ? org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance() : user_;
        }
        /**
         * <code>.User user = 5;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.UserOrBuilder getUserOrBuilder() {
            return getUser();
        }

        public static final int CHILD_FIELD_NUMBER = 6;
        private org.example.protobuf.CompetitionProtobufs.Child child_;
        /**
         * <code>.Child child = 6;</code>
         * @return Whether the child field is set.
         */
        @java.lang.Override
        public boolean hasChild() {
            return child_ != null;
        }
        /**
         * <code>.Child child = 6;</code>
         * @return The child.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Child getChild() {
            return child_ == null ? org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance() : child_;
        }
        /**
         * <code>.Child child = 6;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder() {
            return getChild();
        }

        public static final int PARTICIPATION_FIELD_NUMBER = 7;
        private org.example.protobuf.CompetitionProtobufs.Participation participation_;
        /**
         * <code>.Participation participation = 7;</code>
         * @return Whether the participation field is set.
         */
        @java.lang.Override
        public boolean hasParticipation() {
            return participation_ != null;
        }
        /**
         * <code>.Participation participation = 7;</code>
         * @return The participation.
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Participation getParticipation() {
            return participation_ == null ? org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance() : participation_;
        }
        /**
         * <code>.Participation participation = 7;</code>
         */
        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder getParticipationOrBuilder() {
            return getParticipation();
        }

        private byte memoizedIsInitialized = -1;
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
            if (type_ != org.example.protobuf.CompetitionProtobufs.Response.Type.Unknown.getNumber()) {
                output.writeEnum(1, type_);
            }
            if (!getErrorBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, error_);
            }
            for (int i = 0; i < children_.size(); i++) {
                output.writeMessage(3, children_.get(i));
            }
            for (int i = 0; i < tasks_.size(); i++) {
                output.writeMessage(4, tasks_.get(i));
            }
            if (user_ != null) {
                output.writeMessage(5, getUser());
            }
            if (child_ != null) {
                output.writeMessage(6, getChild());
            }
            if (participation_ != null) {
                output.writeMessage(7, getParticipation());
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (type_ != org.example.protobuf.CompetitionProtobufs.Response.Type.Unknown.getNumber()) {
                size += com.google.protobuf.CodedOutputStream
                        .computeEnumSize(1, type_);
            }
            if (!getErrorBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, error_);
            }
            for (int i = 0; i < children_.size(); i++) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(3, children_.get(i));
            }
            for (int i = 0; i < tasks_.size(); i++) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(4, tasks_.get(i));
            }
            if (user_ != null) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(5, getUser());
            }
            if (child_ != null) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(6, getChild());
            }
            if (participation_ != null) {
                size += com.google.protobuf.CodedOutputStream
                        .computeMessageSize(7, getParticipation());
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
            if (!(obj instanceof org.example.protobuf.CompetitionProtobufs.Response)) {
                return super.equals(obj);
            }
            org.example.protobuf.CompetitionProtobufs.Response other = (org.example.protobuf.CompetitionProtobufs.Response) obj;

            if (type_ != other.type_) return false;
            if (!getError()
                    .equals(other.getError())) return false;
            if (!getChildrenList()
                    .equals(other.getChildrenList())) return false;
            if (!getTasksList()
                    .equals(other.getTasksList())) return false;
            if (hasUser() != other.hasUser()) return false;
            if (hasUser()) {
                if (!getUser()
                        .equals(other.getUser())) return false;
            }
            if (hasChild() != other.hasChild()) return false;
            if (hasChild()) {
                if (!getChild()
                        .equals(other.getChild())) return false;
            }
            if (hasParticipation() != other.hasParticipation()) return false;
            if (hasParticipation()) {
                if (!getParticipation()
                        .equals(other.getParticipation())) return false;
            }
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
            hash = (37 * hash) + TYPE_FIELD_NUMBER;
            hash = (53 * hash) + type_;
            hash = (37 * hash) + ERROR_FIELD_NUMBER;
            hash = (53 * hash) + getError().hashCode();
            if (getChildrenCount() > 0) {
                hash = (37 * hash) + CHILDREN_FIELD_NUMBER;
                hash = (53 * hash) + getChildrenList().hashCode();
            }
            if (getTasksCount() > 0) {
                hash = (37 * hash) + TASKS_FIELD_NUMBER;
                hash = (53 * hash) + getTasksList().hashCode();
            }
            if (hasUser()) {
                hash = (37 * hash) + USER_FIELD_NUMBER;
                hash = (53 * hash) + getUser().hashCode();
            }
            if (hasChild()) {
                hash = (37 * hash) + CHILD_FIELD_NUMBER;
                hash = (53 * hash) + getChild().hashCode();
            }
            if (hasParticipation()) {
                hash = (37 * hash) + PARTICIPATION_FIELD_NUMBER;
                hash = (53 * hash) + getParticipation().hashCode();
            }
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static org.example.protobuf.CompetitionProtobufs.Response parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.example.protobuf.CompetitionProtobufs.Response prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
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
        /**
         * Protobuf type {@code Response}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:Response)
                org.example.protobuf.CompetitionProtobufs.ResponseOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Response_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Response_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                org.example.protobuf.CompetitionProtobufs.Response.class, org.example.protobuf.CompetitionProtobufs.Response.Builder.class);
            }

            // Construct using org.example.protobuf.CompetitionProtobufs.Response.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                    getChildrenFieldBuilder();
                    getTasksFieldBuilder();
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                type_ = 0;

                error_ = "";

                if (childrenBuilder_ == null) {
                    children_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000001);
                } else {
                    childrenBuilder_.clear();
                }
                if (tasksBuilder_ == null) {
                    tasks_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000002);
                } else {
                    tasksBuilder_.clear();
                }
                if (userBuilder_ == null) {
                    user_ = null;
                } else {
                    user_ = null;
                    userBuilder_ = null;
                }
                if (childBuilder_ == null) {
                    child_ = null;
                } else {
                    child_ = null;
                    childBuilder_ = null;
                }
                if (participationBuilder_ == null) {
                    participation_ = null;
                } else {
                    participation_ = null;
                    participationBuilder_ = null;
                }
                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return org.example.protobuf.CompetitionProtobufs.internal_static_Response_descriptor;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Response getDefaultInstanceForType() {
                return org.example.protobuf.CompetitionProtobufs.Response.getDefaultInstance();
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Response build() {
                org.example.protobuf.CompetitionProtobufs.Response result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Response buildPartial() {
                org.example.protobuf.CompetitionProtobufs.Response result = new org.example.protobuf.CompetitionProtobufs.Response(this);
                int from_bitField0_ = bitField0_;
                result.type_ = type_;
                result.error_ = error_;
                if (childrenBuilder_ == null) {
                    if (((bitField0_ & 0x00000001) != 0)) {
                        children_ = java.util.Collections.unmodifiableList(children_);
                        bitField0_ = (bitField0_ & ~0x00000001);
                    }
                    result.children_ = children_;
                } else {
                    result.children_ = childrenBuilder_.build();
                }
                if (tasksBuilder_ == null) {
                    if (((bitField0_ & 0x00000002) != 0)) {
                        tasks_ = java.util.Collections.unmodifiableList(tasks_);
                        bitField0_ = (bitField0_ & ~0x00000002);
                    }
                    result.tasks_ = tasks_;
                } else {
                    result.tasks_ = tasksBuilder_.build();
                }
                if (userBuilder_ == null) {
                    result.user_ = user_;
                } else {
                    result.user_ = userBuilder_.build();
                }
                if (childBuilder_ == null) {
                    result.child_ = child_;
                } else {
                    result.child_ = childBuilder_.build();
                }
                if (participationBuilder_ == null) {
                    result.participation_ = participation_;
                } else {
                    result.participation_ = participationBuilder_.build();
                }
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
                if (other instanceof org.example.protobuf.CompetitionProtobufs.Response) {
                    return mergeFrom((org.example.protobuf.CompetitionProtobufs.Response)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(org.example.protobuf.CompetitionProtobufs.Response other) {
                if (other == org.example.protobuf.CompetitionProtobufs.Response.getDefaultInstance()) return this;
                if (other.type_ != 0) {
                    setTypeValue(other.getTypeValue());
                }
                if (!other.getError().isEmpty()) {
                    error_ = other.error_;
                    onChanged();
                }
                if (childrenBuilder_ == null) {
                    if (!other.children_.isEmpty()) {
                        if (children_.isEmpty()) {
                            children_ = other.children_;
                            bitField0_ = (bitField0_ & ~0x00000001);
                        } else {
                            ensureChildrenIsMutable();
                            children_.addAll(other.children_);
                        }
                        onChanged();
                    }
                } else {
                    if (!other.children_.isEmpty()) {
                        if (childrenBuilder_.isEmpty()) {
                            childrenBuilder_.dispose();
                            childrenBuilder_ = null;
                            children_ = other.children_;
                            bitField0_ = (bitField0_ & ~0x00000001);
                            childrenBuilder_ =
                                    com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                                            getChildrenFieldBuilder() : null;
                        } else {
                            childrenBuilder_.addAllMessages(other.children_);
                        }
                    }
                }
                if (tasksBuilder_ == null) {
                    if (!other.tasks_.isEmpty()) {
                        if (tasks_.isEmpty()) {
                            tasks_ = other.tasks_;
                            bitField0_ = (bitField0_ & ~0x00000002);
                        } else {
                            ensureTasksIsMutable();
                            tasks_.addAll(other.tasks_);
                        }
                        onChanged();
                    }
                } else {
                    if (!other.tasks_.isEmpty()) {
                        if (tasksBuilder_.isEmpty()) {
                            tasksBuilder_.dispose();
                            tasksBuilder_ = null;
                            tasks_ = other.tasks_;
                            bitField0_ = (bitField0_ & ~0x00000002);
                            tasksBuilder_ =
                                    com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                                            getTasksFieldBuilder() : null;
                        } else {
                            tasksBuilder_.addAllMessages(other.tasks_);
                        }
                    }
                }
                if (other.hasUser()) {
                    mergeUser(other.getUser());
                }
                if (other.hasChild()) {
                    mergeChild(other.getChild());
                }
                if (other.hasParticipation()) {
                    mergeParticipation(other.getParticipation());
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
                org.example.protobuf.CompetitionProtobufs.Response parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (org.example.protobuf.CompetitionProtobufs.Response) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
            private int bitField0_;

            private int type_ = 0;
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Response.Type type = 1;</code>
             * @return The enum numeric value on the wire for type.
             */
            @java.lang.Override public int getTypeValue() {
                return type_;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Response.Type type = 1;</code>
             * @param value The enum numeric value on the wire for type to set.
             * @return This builder for chaining.
             */
            public Builder setTypeValue(int value) {

                type_ = value;
                onChanged();
                return this;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Response.Type type = 1;</code>
             * @return The type.
             */
            @java.lang.Override
            public org.example.protobuf.CompetitionProtobufs.Response.Type getType() {
                @SuppressWarnings("deprecation")
                org.example.protobuf.CompetitionProtobufs.Response.Type result = org.example.protobuf.CompetitionProtobufs.Response.Type.valueOf(type_);
                return result == null ? org.example.protobuf.CompetitionProtobufs.Response.Type.UNRECOGNIZED : result;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Response.Type type = 1;</code>
             * @param value The type to set.
             * @return This builder for chaining.
             */
            public Builder setType(org.example.protobuf.CompetitionProtobufs.Response.Type value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                type_ = value.getNumber();
                onChanged();
                return this;
            }
            /**
             * <pre>
             * Identifies witch request is filled in.
             * </pre>
             *
             * <code>.Response.Type type = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearType() {

                type_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object error_ = "";
            /**
             * <pre>
             *One of the following will be filled in, depending on the type.
             * </pre>
             *
             * <code>string error = 2;</code>
             * @return The error.
             */
            public java.lang.String getError() {
                java.lang.Object ref = error_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    error_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <pre>
             *One of the following will be filled in, depending on the type.
             * </pre>
             *
             * <code>string error = 2;</code>
             * @return The bytes for error.
             */
            public com.google.protobuf.ByteString
            getErrorBytes() {
                java.lang.Object ref = error_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    error_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <pre>
             *One of the following will be filled in, depending on the type.
             * </pre>
             *
             * <code>string error = 2;</code>
             * @param value The error to set.
             * @return This builder for chaining.
             */
            public Builder setError(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                error_ = value;
                onChanged();
                return this;
            }
            /**
             * <pre>
             *One of the following will be filled in, depending on the type.
             * </pre>
             *
             * <code>string error = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearError() {

                error_ = getDefaultInstance().getError();
                onChanged();
                return this;
            }
            /**
             * <pre>
             *One of the following will be filled in, depending on the type.
             * </pre>
             *
             * <code>string error = 2;</code>
             * @param value The bytes for error to set.
             * @return This builder for chaining.
             */
            public Builder setErrorBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                error_ = value;
                onChanged();
                return this;
            }

            private java.util.List<org.example.protobuf.CompetitionProtobufs.Child> children_ =
                    java.util.Collections.emptyList();
            private void ensureChildrenIsMutable() {
                if (!((bitField0_ & 0x00000001) != 0)) {
                    children_ = new java.util.ArrayList<org.example.protobuf.CompetitionProtobufs.Child>(children_);
                    bitField0_ |= 0x00000001;
                }
            }

            private com.google.protobuf.RepeatedFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder> childrenBuilder_;

            /**
             * <code>repeated .Child children = 3;</code>
             */
            public java.util.List<org.example.protobuf.CompetitionProtobufs.Child> getChildrenList() {
                if (childrenBuilder_ == null) {
                    return java.util.Collections.unmodifiableList(children_);
                } else {
                    return childrenBuilder_.getMessageList();
                }
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public int getChildrenCount() {
                if (childrenBuilder_ == null) {
                    return children_.size();
                } else {
                    return childrenBuilder_.getCount();
                }
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Child getChildren(int index) {
                if (childrenBuilder_ == null) {
                    return children_.get(index);
                } else {
                    return childrenBuilder_.getMessage(index);
                }
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder setChildren(
                    int index, org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childrenBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureChildrenIsMutable();
                    children_.set(index, value);
                    onChanged();
                } else {
                    childrenBuilder_.setMessage(index, value);
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder setChildren(
                    int index, org.example.protobuf.CompetitionProtobufs.Child.Builder builderForValue) {
                if (childrenBuilder_ == null) {
                    ensureChildrenIsMutable();
                    children_.set(index, builderForValue.build());
                    onChanged();
                } else {
                    childrenBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder addChildren(org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childrenBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureChildrenIsMutable();
                    children_.add(value);
                    onChanged();
                } else {
                    childrenBuilder_.addMessage(value);
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder addChildren(
                    int index, org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childrenBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureChildrenIsMutable();
                    children_.add(index, value);
                    onChanged();
                } else {
                    childrenBuilder_.addMessage(index, value);
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder addChildren(
                    org.example.protobuf.CompetitionProtobufs.Child.Builder builderForValue) {
                if (childrenBuilder_ == null) {
                    ensureChildrenIsMutable();
                    children_.add(builderForValue.build());
                    onChanged();
                } else {
                    childrenBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder addChildren(
                    int index, org.example.protobuf.CompetitionProtobufs.Child.Builder builderForValue) {
                if (childrenBuilder_ == null) {
                    ensureChildrenIsMutable();
                    children_.add(index, builderForValue.build());
                    onChanged();
                } else {
                    childrenBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder addAllChildren(
                    java.lang.Iterable<? extends org.example.protobuf.CompetitionProtobufs.Child> values) {
                if (childrenBuilder_ == null) {
                    ensureChildrenIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(
                            values, children_);
                    onChanged();
                } else {
                    childrenBuilder_.addAllMessages(values);
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder clearChildren() {
                if (childrenBuilder_ == null) {
                    children_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000001);
                    onChanged();
                } else {
                    childrenBuilder_.clear();
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public Builder removeChildren(int index) {
                if (childrenBuilder_ == null) {
                    ensureChildrenIsMutable();
                    children_.remove(index);
                    onChanged();
                } else {
                    childrenBuilder_.remove(index);
                }
                return this;
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Child.Builder getChildrenBuilder(
                    int index) {
                return getChildrenFieldBuilder().getBuilder(index);
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildrenOrBuilder(
                    int index) {
                if (childrenBuilder_ == null) {
                    return children_.get(index);  } else {
                    return childrenBuilder_.getMessageOrBuilder(index);
                }
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public java.util.List<? extends org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>
            getChildrenOrBuilderList() {
                if (childrenBuilder_ != null) {
                    return childrenBuilder_.getMessageOrBuilderList();
                } else {
                    return java.util.Collections.unmodifiableList(children_);
                }
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Child.Builder addChildrenBuilder() {
                return getChildrenFieldBuilder().addBuilder(
                        org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance());
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Child.Builder addChildrenBuilder(
                    int index) {
                return getChildrenFieldBuilder().addBuilder(
                        index, org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance());
            }
            /**
             * <code>repeated .Child children = 3;</code>
             */
            public java.util.List<org.example.protobuf.CompetitionProtobufs.Child.Builder>
            getChildrenBuilderList() {
                return getChildrenFieldBuilder().getBuilderList();
            }
            private com.google.protobuf.RepeatedFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>
            getChildrenFieldBuilder() {
                if (childrenBuilder_ == null) {
                    childrenBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>(
                            children_,
                            ((bitField0_ & 0x00000001) != 0),
                            getParentForChildren(),
                            isClean());
                    children_ = null;
                }
                return childrenBuilder_;
            }

            private java.util.List<org.example.protobuf.CompetitionProtobufs.TaskDTO> tasks_ =
                    java.util.Collections.emptyList();
            private void ensureTasksIsMutable() {
                if (!((bitField0_ & 0x00000002) != 0)) {
                    tasks_ = new java.util.ArrayList<org.example.protobuf.CompetitionProtobufs.TaskDTO>(tasks_);
                    bitField0_ |= 0x00000002;
                }
            }

            private com.google.protobuf.RepeatedFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.TaskDTO, org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder, org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder> tasksBuilder_;

            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public java.util.List<org.example.protobuf.CompetitionProtobufs.TaskDTO> getTasksList() {
                if (tasksBuilder_ == null) {
                    return java.util.Collections.unmodifiableList(tasks_);
                } else {
                    return tasksBuilder_.getMessageList();
                }
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public int getTasksCount() {
                if (tasksBuilder_ == null) {
                    return tasks_.size();
                } else {
                    return tasksBuilder_.getCount();
                }
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.TaskDTO getTasks(int index) {
                if (tasksBuilder_ == null) {
                    return tasks_.get(index);
                } else {
                    return tasksBuilder_.getMessage(index);
                }
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder setTasks(
                    int index, org.example.protobuf.CompetitionProtobufs.TaskDTO value) {
                if (tasksBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureTasksIsMutable();
                    tasks_.set(index, value);
                    onChanged();
                } else {
                    tasksBuilder_.setMessage(index, value);
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder setTasks(
                    int index, org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder builderForValue) {
                if (tasksBuilder_ == null) {
                    ensureTasksIsMutable();
                    tasks_.set(index, builderForValue.build());
                    onChanged();
                } else {
                    tasksBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder addTasks(org.example.protobuf.CompetitionProtobufs.TaskDTO value) {
                if (tasksBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureTasksIsMutable();
                    tasks_.add(value);
                    onChanged();
                } else {
                    tasksBuilder_.addMessage(value);
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder addTasks(
                    int index, org.example.protobuf.CompetitionProtobufs.TaskDTO value) {
                if (tasksBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    ensureTasksIsMutable();
                    tasks_.add(index, value);
                    onChanged();
                } else {
                    tasksBuilder_.addMessage(index, value);
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder addTasks(
                    org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder builderForValue) {
                if (tasksBuilder_ == null) {
                    ensureTasksIsMutable();
                    tasks_.add(builderForValue.build());
                    onChanged();
                } else {
                    tasksBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder addTasks(
                    int index, org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder builderForValue) {
                if (tasksBuilder_ == null) {
                    ensureTasksIsMutable();
                    tasks_.add(index, builderForValue.build());
                    onChanged();
                } else {
                    tasksBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder addAllTasks(
                    java.lang.Iterable<? extends org.example.protobuf.CompetitionProtobufs.TaskDTO> values) {
                if (tasksBuilder_ == null) {
                    ensureTasksIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(
                            values, tasks_);
                    onChanged();
                } else {
                    tasksBuilder_.addAllMessages(values);
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder clearTasks() {
                if (tasksBuilder_ == null) {
                    tasks_ = java.util.Collections.emptyList();
                    bitField0_ = (bitField0_ & ~0x00000002);
                    onChanged();
                } else {
                    tasksBuilder_.clear();
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public Builder removeTasks(int index) {
                if (tasksBuilder_ == null) {
                    ensureTasksIsMutable();
                    tasks_.remove(index);
                    onChanged();
                } else {
                    tasksBuilder_.remove(index);
                }
                return this;
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder getTasksBuilder(
                    int index) {
                return getTasksFieldBuilder().getBuilder(index);
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder getTasksOrBuilder(
                    int index) {
                if (tasksBuilder_ == null) {
                    return tasks_.get(index);  } else {
                    return tasksBuilder_.getMessageOrBuilder(index);
                }
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public java.util.List<? extends org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder>
            getTasksOrBuilderList() {
                if (tasksBuilder_ != null) {
                    return tasksBuilder_.getMessageOrBuilderList();
                } else {
                    return java.util.Collections.unmodifiableList(tasks_);
                }
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder addTasksBuilder() {
                return getTasksFieldBuilder().addBuilder(
                        org.example.protobuf.CompetitionProtobufs.TaskDTO.getDefaultInstance());
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder addTasksBuilder(
                    int index) {
                return getTasksFieldBuilder().addBuilder(
                        index, org.example.protobuf.CompetitionProtobufs.TaskDTO.getDefaultInstance());
            }
            /**
             * <code>repeated .TaskDTO tasks = 4;</code>
             */
            public java.util.List<org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder>
            getTasksBuilderList() {
                return getTasksFieldBuilder().getBuilderList();
            }
            private com.google.protobuf.RepeatedFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.TaskDTO, org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder, org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder>
            getTasksFieldBuilder() {
                if (tasksBuilder_ == null) {
                    tasksBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.TaskDTO, org.example.protobuf.CompetitionProtobufs.TaskDTO.Builder, org.example.protobuf.CompetitionProtobufs.TaskDTOOrBuilder>(
                            tasks_,
                            ((bitField0_ & 0x00000002) != 0),
                            getParentForChildren(),
                            isClean());
                    tasks_ = null;
                }
                return tasksBuilder_;
            }

            private org.example.protobuf.CompetitionProtobufs.User user_;
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.User, org.example.protobuf.CompetitionProtobufs.User.Builder, org.example.protobuf.CompetitionProtobufs.UserOrBuilder> userBuilder_;
            /**
             * <code>.User user = 5;</code>
             * @return Whether the user field is set.
             */
            public boolean hasUser() {
                return userBuilder_ != null || user_ != null;
            }
            /**
             * <code>.User user = 5;</code>
             * @return The user.
             */
            public org.example.protobuf.CompetitionProtobufs.User getUser() {
                if (userBuilder_ == null) {
                    return user_ == null ? org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance() : user_;
                } else {
                    return userBuilder_.getMessage();
                }
            }
            /**
             * <code>.User user = 5;</code>
             */
            public Builder setUser(org.example.protobuf.CompetitionProtobufs.User value) {
                if (userBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    user_ = value;
                    onChanged();
                } else {
                    userBuilder_.setMessage(value);
                }

                return this;
            }
            /**
             * <code>.User user = 5;</code>
             */
            public Builder setUser(
                    org.example.protobuf.CompetitionProtobufs.User.Builder builderForValue) {
                if (userBuilder_ == null) {
                    user_ = builderForValue.build();
                    onChanged();
                } else {
                    userBuilder_.setMessage(builderForValue.build());
                }

                return this;
            }
            /**
             * <code>.User user = 5;</code>
             */
            public Builder mergeUser(org.example.protobuf.CompetitionProtobufs.User value) {
                if (userBuilder_ == null) {
                    if (user_ != null) {
                        user_ =
                                org.example.protobuf.CompetitionProtobufs.User.newBuilder(user_).mergeFrom(value).buildPartial();
                    } else {
                        user_ = value;
                    }
                    onChanged();
                } else {
                    userBuilder_.mergeFrom(value);
                }

                return this;
            }
            /**
             * <code>.User user = 5;</code>
             */
            public Builder clearUser() {
                if (userBuilder_ == null) {
                    user_ = null;
                    onChanged();
                } else {
                    user_ = null;
                    userBuilder_ = null;
                }

                return this;
            }
            /**
             * <code>.User user = 5;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.User.Builder getUserBuilder() {

                onChanged();
                return getUserFieldBuilder().getBuilder();
            }
            /**
             * <code>.User user = 5;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.UserOrBuilder getUserOrBuilder() {
                if (userBuilder_ != null) {
                    return userBuilder_.getMessageOrBuilder();
                } else {
                    return user_ == null ?
                            org.example.protobuf.CompetitionProtobufs.User.getDefaultInstance() : user_;
                }
            }
            /**
             * <code>.User user = 5;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.User, org.example.protobuf.CompetitionProtobufs.User.Builder, org.example.protobuf.CompetitionProtobufs.UserOrBuilder>
            getUserFieldBuilder() {
                if (userBuilder_ == null) {
                    userBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.User, org.example.protobuf.CompetitionProtobufs.User.Builder, org.example.protobuf.CompetitionProtobufs.UserOrBuilder>(
                            getUser(),
                            getParentForChildren(),
                            isClean());
                    user_ = null;
                }
                return userBuilder_;
            }

            private org.example.protobuf.CompetitionProtobufs.Child child_;
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder> childBuilder_;
            /**
             * <code>.Child child = 6;</code>
             * @return Whether the child field is set.
             */
            public boolean hasChild() {
                return childBuilder_ != null || child_ != null;
            }
            /**
             * <code>.Child child = 6;</code>
             * @return The child.
             */
            public org.example.protobuf.CompetitionProtobufs.Child getChild() {
                if (childBuilder_ == null) {
                    return child_ == null ? org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance() : child_;
                } else {
                    return childBuilder_.getMessage();
                }
            }
            /**
             * <code>.Child child = 6;</code>
             */
            public Builder setChild(org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    child_ = value;
                    onChanged();
                } else {
                    childBuilder_.setMessage(value);
                }

                return this;
            }
            /**
             * <code>.Child child = 6;</code>
             */
            public Builder setChild(
                    org.example.protobuf.CompetitionProtobufs.Child.Builder builderForValue) {
                if (childBuilder_ == null) {
                    child_ = builderForValue.build();
                    onChanged();
                } else {
                    childBuilder_.setMessage(builderForValue.build());
                }

                return this;
            }
            /**
             * <code>.Child child = 6;</code>
             */
            public Builder mergeChild(org.example.protobuf.CompetitionProtobufs.Child value) {
                if (childBuilder_ == null) {
                    if (child_ != null) {
                        child_ =
                                org.example.protobuf.CompetitionProtobufs.Child.newBuilder(child_).mergeFrom(value).buildPartial();
                    } else {
                        child_ = value;
                    }
                    onChanged();
                } else {
                    childBuilder_.mergeFrom(value);
                }

                return this;
            }
            /**
             * <code>.Child child = 6;</code>
             */
            public Builder clearChild() {
                if (childBuilder_ == null) {
                    child_ = null;
                    onChanged();
                } else {
                    child_ = null;
                    childBuilder_ = null;
                }

                return this;
            }
            /**
             * <code>.Child child = 6;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Child.Builder getChildBuilder() {

                onChanged();
                return getChildFieldBuilder().getBuilder();
            }
            /**
             * <code>.Child child = 6;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.ChildOrBuilder getChildOrBuilder() {
                if (childBuilder_ != null) {
                    return childBuilder_.getMessageOrBuilder();
                } else {
                    return child_ == null ?
                            org.example.protobuf.CompetitionProtobufs.Child.getDefaultInstance() : child_;
                }
            }
            /**
             * <code>.Child child = 6;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>
            getChildFieldBuilder() {
                if (childBuilder_ == null) {
                    childBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Child, org.example.protobuf.CompetitionProtobufs.Child.Builder, org.example.protobuf.CompetitionProtobufs.ChildOrBuilder>(
                            getChild(),
                            getParentForChildren(),
                            isClean());
                    child_ = null;
                }
                return childBuilder_;
            }

            private org.example.protobuf.CompetitionProtobufs.Participation participation_;
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Participation, org.example.protobuf.CompetitionProtobufs.Participation.Builder, org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder> participationBuilder_;
            /**
             * <code>.Participation participation = 7;</code>
             * @return Whether the participation field is set.
             */
            public boolean hasParticipation() {
                return participationBuilder_ != null || participation_ != null;
            }
            /**
             * <code>.Participation participation = 7;</code>
             * @return The participation.
             */
            public org.example.protobuf.CompetitionProtobufs.Participation getParticipation() {
                if (participationBuilder_ == null) {
                    return participation_ == null ? org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance() : participation_;
                } else {
                    return participationBuilder_.getMessage();
                }
            }
            /**
             * <code>.Participation participation = 7;</code>
             */
            public Builder setParticipation(org.example.protobuf.CompetitionProtobufs.Participation value) {
                if (participationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    participation_ = value;
                    onChanged();
                } else {
                    participationBuilder_.setMessage(value);
                }

                return this;
            }
            /**
             * <code>.Participation participation = 7;</code>
             */
            public Builder setParticipation(
                    org.example.protobuf.CompetitionProtobufs.Participation.Builder builderForValue) {
                if (participationBuilder_ == null) {
                    participation_ = builderForValue.build();
                    onChanged();
                } else {
                    participationBuilder_.setMessage(builderForValue.build());
                }

                return this;
            }
            /**
             * <code>.Participation participation = 7;</code>
             */
            public Builder mergeParticipation(org.example.protobuf.CompetitionProtobufs.Participation value) {
                if (participationBuilder_ == null) {
                    if (participation_ != null) {
                        participation_ =
                                org.example.protobuf.CompetitionProtobufs.Participation.newBuilder(participation_).mergeFrom(value).buildPartial();
                    } else {
                        participation_ = value;
                    }
                    onChanged();
                } else {
                    participationBuilder_.mergeFrom(value);
                }

                return this;
            }
            /**
             * <code>.Participation participation = 7;</code>
             */
            public Builder clearParticipation() {
                if (participationBuilder_ == null) {
                    participation_ = null;
                    onChanged();
                } else {
                    participation_ = null;
                    participationBuilder_ = null;
                }

                return this;
            }
            /**
             * <code>.Participation participation = 7;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.Participation.Builder getParticipationBuilder() {

                onChanged();
                return getParticipationFieldBuilder().getBuilder();
            }
            /**
             * <code>.Participation participation = 7;</code>
             */
            public org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder getParticipationOrBuilder() {
                if (participationBuilder_ != null) {
                    return participationBuilder_.getMessageOrBuilder();
                } else {
                    return participation_ == null ?
                            org.example.protobuf.CompetitionProtobufs.Participation.getDefaultInstance() : participation_;
                }
            }
            /**
             * <code>.Participation participation = 7;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<
                    org.example.protobuf.CompetitionProtobufs.Participation, org.example.protobuf.CompetitionProtobufs.Participation.Builder, org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder>
            getParticipationFieldBuilder() {
                if (participationBuilder_ == null) {
                    participationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                            org.example.protobuf.CompetitionProtobufs.Participation, org.example.protobuf.CompetitionProtobufs.Participation.Builder, org.example.protobuf.CompetitionProtobufs.ParticipationOrBuilder>(
                            getParticipation(),
                            getParentForChildren(),
                            isClean());
                    participation_ = null;
                }
                return participationBuilder_;
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


            // @@protoc_insertion_point(builder_scope:Response)
        }

        // @@protoc_insertion_point(class_scope:Response)
        private static final org.example.protobuf.CompetitionProtobufs.Response DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new org.example.protobuf.CompetitionProtobufs.Response();
        }

        public static org.example.protobuf.CompetitionProtobufs.Response getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Response>
                PARSER = new com.google.protobuf.AbstractParser<Response>() {
            @java.lang.Override
            public Response parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Response(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Response> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Response> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public org.example.protobuf.CompetitionProtobufs.Response getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_User_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_User_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_Child_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_Child_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_Task_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_Task_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_TaskDTO_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_TaskDTO_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_Participation_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_Participation_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_Request_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_Request_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_Response_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_Response_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }
    private static  com.google.protobuf.Descriptors.FileDescriptor
            descriptor;
    static {
        java.lang.String[] descriptorData = {
                "\n\021Competition.proto\"V\n\004User\022\n\n\002id\030\001 \001(\005\022" +
                        "\014\n\004name\030\002 \001(\t\022\020\n\010username\030\003 \001(\t\022\020\n\010passw" +
                        "ord\030\004 \001(\t\022\020\n\010officeNo\030\005 \001(\005\".\n\005Child\022\n\n\002" +
                        "id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\013\n\003age\030\003 \001(\005\"O\n\004T" +
                        "ask\022\n\n\002id\030\001 \001(\005\022\023\n\013description\030\002 \001(\t\022\023\n\013" +
                        "ageCatStart\030\003 \001(\005\022\021\n\tageCatEnd\030\004 \001(\005\"f\n\007" +
                        "TaskDTO\022\n\n\002id\030\001 \001(\005\022\023\n\013description\030\002 \001(\t" +
                        "\022\023\n\013ageCatStart\030\003 \001(\005\022\021\n\tageCatEnd\030\004 \001(\005" +
                        "\022\022\n\nnochildren\030\005 \001(\005\"G\n\rParticipation\022\n\n" +
                        "\002id\030\001 \001(\005\022\025\n\005child\030\002 \001(\0132\006.Child\022\023\n\004task" +
                        "\030\003 \001(\0132\005.Task\"\272\002\n\007Request\022\033\n\004type\030\001 \001(\0162" +
                        "\r.Request.Type\022\025\n\004user\030\002 \001(\0132\005.UserH\000\022\'\n" +
                        "\rparticipation\030\003 \001(\0132\016.ParticipationH\000\022\027" +
                        "\n\005child\030\004 \001(\0132\006.ChildH\000\022\025\n\004task\030\005 \001(\0132\005." +
                        "TaskH\000\"\226\001\n\004Type\022\013\n\007Unknown\020\000\022\t\n\005Login\020\001\022" +
                        "\n\n\006Logout\020\002\022\024\n\020Get_participants\020\003\022\025\n\021Add" +
                        "_participation\020\004\022\n\n\006Update\020\005\022\r\n\tAdd_chil" +
                        "d\020\006\022\023\n\017Find_last_child\020\007\022\r\n\tGet_tasks\020\010B" +
                        "\t\n\007payload\"\351\002\n\010Response\022\034\n\004type\030\001 \001(\0162\016." +
                        "Response.Type\022\r\n\005error\030\002 \001(\t\022\030\n\010children" +
                        "\030\003 \003(\0132\006.Child\022\027\n\005tasks\030\004 \003(\0132\010.TaskDTO\022" +
                        "\023\n\004user\030\005 \001(\0132\005.User\022\025\n\005child\030\006 \001(\0132\006.Ch" +
                        "ild\022%\n\rparticipation\030\007 \001(\0132\016.Participati" +
                        "on\"\251\001\n\004Type\022\013\n\007Unknown\020\000\022\t\n\005Login\020\001\022\n\n\006L" +
                        "ogout\020\002\022\024\n\020Get_participants\020\003\022\025\n\021Add_par" +
                        "ticipation\020\004\022\n\n\006Update\020\005\022\r\n\tAdd_child\020\006\022" +
                        "\023\n\017Find_last_child\020\007\022\006\n\002Ok\020\010\022\t\n\005Error\020\t\022" +
                        "\r\n\tGet_tasks\020\nB,\n\024org.example.protobufB\024" +
                        "CompetitionProtobufsb\006proto3"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[] {
                        });
        internal_static_User_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_User_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_User_descriptor,
                new java.lang.String[] { "Id", "Name", "Username", "Password", "OfficeNo", });
        internal_static_Child_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_Child_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_Child_descriptor,
                new java.lang.String[] { "Id", "Name", "Age", });
        internal_static_Task_descriptor =
                getDescriptor().getMessageTypes().get(2);
        internal_static_Task_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_Task_descriptor,
                new java.lang.String[] { "Id", "Description", "AgeCatStart", "AgeCatEnd", });
        internal_static_TaskDTO_descriptor =
                getDescriptor().getMessageTypes().get(3);
        internal_static_TaskDTO_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_TaskDTO_descriptor,
                new java.lang.String[] { "Id", "Description", "AgeCatStart", "AgeCatEnd", "Nochildren", });
        internal_static_Participation_descriptor =
                getDescriptor().getMessageTypes().get(4);
        internal_static_Participation_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_Participation_descriptor,
                new java.lang.String[] { "Id", "Child", "Task", });
        internal_static_Request_descriptor =
                getDescriptor().getMessageTypes().get(5);
        internal_static_Request_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_Request_descriptor,
                new java.lang.String[] { "Type", "User", "Participation", "Child", "Task", "Payload", });
        internal_static_Response_descriptor =
                getDescriptor().getMessageTypes().get(6);
        internal_static_Response_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_Response_descriptor,
                new java.lang.String[] { "Type", "Error", "Children", "Tasks", "User", "Child", "Participation", });
    }

    // @@protoc_insertion_point(outer_class_scope)
}
