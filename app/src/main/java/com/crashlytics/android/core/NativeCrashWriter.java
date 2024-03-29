package com.crashlytics.android.core;

import com.crashlytics.android.core.internal.models.BinaryImageData;
import com.crashlytics.android.core.internal.models.CustomAttributeData;
import com.crashlytics.android.core.internal.models.DeviceData;
import com.crashlytics.android.core.internal.models.SessionEventData;
import com.crashlytics.android.core.internal.models.SignalData;
import com.crashlytics.android.core.internal.models.ThreadData;
import java.util.Map;
import java.util.TreeMap;
import p005b.p006a.p007a.p008a.Fabric;

class NativeCrashWriter {
    private static final SignalData DEFAULT_SIGNAL = new SignalData("", "", 0);
    private static final BinaryImageMessage[] EMPTY_BINARY_IMAGE_MESSAGES = new BinaryImageMessage[0];
    /* access modifiers changed from: private */
    public static final ProtobufMessage[] EMPTY_CHILDREN = new ProtobufMessage[0];
    private static final CustomAttributeMessage[] EMPTY_CUSTOM_ATTRIBUTE_MESSAGES = new CustomAttributeMessage[0];
    private static final FrameMessage[] EMPTY_FRAME_MESSAGES = new FrameMessage[0];
    private static final ThreadMessage[] EMPTY_THREAD_MESSAGES = new ThreadMessage[0];
    static final String NDK_CRASH_TYPE = "ndk-crash";

    private static final class ApplicationMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 3;

        public ApplicationMessage(ExecutionMessage executionMessage, RepeatedMessage repeatedMessage) {
            super(3, executionMessage, repeatedMessage);
        }
    }

    private static final class BinaryImageMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 4;
        private final long baseAddr;
        private final String filePath;
        private final long imageSize;
        private final String uuid;

        public BinaryImageMessage(BinaryImageData binaryImageData) {
            super(4, new ProtobufMessage[0]);
            this.baseAddr = binaryImageData.baseAddress;
            this.imageSize = binaryImageData.size;
            this.filePath = binaryImageData.path;
            this.uuid = binaryImageData.f1715id;
        }

        public int getPropertiesSize() {
            int computeUInt64Size = CodedOutputStream.computeUInt64Size(1, this.baseAddr);
            return computeUInt64Size + CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(this.filePath)) + CodedOutputStream.computeUInt64Size(2, this.imageSize) + CodedOutputStream.computeBytesSize(4, ByteString.copyFromUtf8(this.uuid));
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeUInt64(1, this.baseAddr);
            codedOutputStream.writeUInt64(2, this.imageSize);
            codedOutputStream.writeBytes(3, ByteString.copyFromUtf8(this.filePath));
            codedOutputStream.writeBytes(4, ByteString.copyFromUtf8(this.uuid));
        }
    }

    private static final class CustomAttributeMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 2;
        private final String key;
        private final String value;

        public CustomAttributeMessage(CustomAttributeData customAttributeData) {
            super(2, new ProtobufMessage[0]);
            this.key = customAttributeData.key;
            this.value = customAttributeData.value;
        }

        public int getPropertiesSize() {
            return CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(this.value == null ? "" : this.value)) + CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(this.key));
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeBytes(1, ByteString.copyFromUtf8(this.key));
            codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(this.value == null ? "" : this.value));
        }
    }

    private static final class DeviceMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 5;
        private final float batteryLevel;
        private final int batteryVelocity;
        private final long diskUsed;
        private final int orientation;
        private final boolean proximityOn;
        private final long ramUsed;

        public DeviceMessage(float f, int i, boolean z, int i2, long j, long j2) {
            super(5, new ProtobufMessage[0]);
            this.batteryLevel = f;
            this.batteryVelocity = i;
            this.proximityOn = z;
            this.orientation = i2;
            this.ramUsed = j;
            this.diskUsed = j2;
        }

        public int getPropertiesSize() {
            return 0 + CodedOutputStream.computeFloatSize(1, this.batteryLevel) + CodedOutputStream.computeSInt32Size(2, this.batteryVelocity) + CodedOutputStream.computeBoolSize(3, this.proximityOn) + CodedOutputStream.computeUInt32Size(4, this.orientation) + CodedOutputStream.computeUInt64Size(5, this.ramUsed) + CodedOutputStream.computeUInt64Size(6, this.diskUsed);
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeFloat(1, this.batteryLevel);
            codedOutputStream.writeSInt32(2, this.batteryVelocity);
            codedOutputStream.writeBool(3, this.proximityOn);
            codedOutputStream.writeUInt32(4, this.orientation);
            codedOutputStream.writeUInt64(5, this.ramUsed);
            codedOutputStream.writeUInt64(6, this.diskUsed);
        }
    }

    private static final class EventMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 10;
        private final String crashType;
        private final long time;

        public EventMessage(long j, String str, ProtobufMessage... protobufMessageArr) {
            super(10, protobufMessageArr);
            this.time = j;
            this.crashType = str;
        }

        public int getPropertiesSize() {
            return CodedOutputStream.computeUInt64Size(1, this.time) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(this.crashType));
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeUInt64(1, this.time);
            codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(this.crashType));
        }
    }

    private static final class ExecutionMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 1;

        public ExecutionMessage(SignalMessage signalMessage, RepeatedMessage repeatedMessage, RepeatedMessage repeatedMessage2) {
            super(1, repeatedMessage, signalMessage, repeatedMessage2);
        }
    }

    private static final class FrameMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 3;
        private final long address;
        private final String file;
        private final int importance;
        private final long offset;
        private final String symbol;

        public FrameMessage(ThreadData.FrameData frameData) {
            super(3, new ProtobufMessage[0]);
            this.address = frameData.address;
            this.symbol = frameData.symbol;
            this.file = frameData.file;
            this.offset = frameData.offset;
            this.importance = frameData.importance;
        }

        public int getPropertiesSize() {
            return CodedOutputStream.computeUInt64Size(1, this.address) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(this.symbol)) + CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(this.file)) + CodedOutputStream.computeUInt64Size(4, this.offset) + CodedOutputStream.computeUInt32Size(5, this.importance);
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeUInt64(1, this.address);
            codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(this.symbol));
            codedOutputStream.writeBytes(3, ByteString.copyFromUtf8(this.file));
            codedOutputStream.writeUInt64(4, this.offset);
            codedOutputStream.writeUInt32(5, this.importance);
        }
    }

    private static final class LogMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 6;
        ByteString logBytes;

        public LogMessage(ByteString byteString) {
            super(6, new ProtobufMessage[0]);
            this.logBytes = byteString;
        }

        public int getPropertiesSize() {
            return CodedOutputStream.computeBytesSize(1, this.logBytes);
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeBytes(1, this.logBytes);
        }
    }

    private static final class NullMessage extends ProtobufMessage {
        public NullMessage() {
            super(0, new ProtobufMessage[0]);
        }

        public void write(CodedOutputStream codedOutputStream) {
        }

        public int getSize() {
            return 0;
        }
    }

    private static abstract class ProtobufMessage {
        private final ProtobufMessage[] children;
        private final int tag;

        public ProtobufMessage(int i, ProtobufMessage... protobufMessageArr) {
            this.tag = i;
            this.children = protobufMessageArr == null ? NativeCrashWriter.EMPTY_CHILDREN : protobufMessageArr;
        }

        public int getSize() {
            int sizeNoTag = getSizeNoTag();
            return sizeNoTag + CodedOutputStream.computeRawVarint32Size(sizeNoTag) + CodedOutputStream.computeTagSize(this.tag);
        }

        public int getSizeNoTag() {
            int propertiesSize = getPropertiesSize();
            for (ProtobufMessage size : this.children) {
                propertiesSize += size.getSize();
            }
            return propertiesSize;
        }

        public void write(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeTag(this.tag, 2);
            codedOutputStream.writeRawVarint32(getSizeNoTag());
            writeProperties(codedOutputStream);
            for (ProtobufMessage write : this.children) {
                write.write(codedOutputStream);
            }
        }

        public int getPropertiesSize() {
            return 0;
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
        }
    }

    private static final class RepeatedMessage extends ProtobufMessage {
        private final ProtobufMessage[] messages;

        public RepeatedMessage(ProtobufMessage... protobufMessageArr) {
            super(0, new ProtobufMessage[0]);
            this.messages = protobufMessageArr;
        }

        public void write(CodedOutputStream codedOutputStream) {
            for (ProtobufMessage write : this.messages) {
                write.write(codedOutputStream);
            }
        }

        public int getSize() {
            int i = 0;
            for (ProtobufMessage size : this.messages) {
                i += size.getSize();
            }
            return i;
        }
    }

    private static final class SignalMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 3;
        private final long sigAddr;
        private final String sigCode;
        private final String sigName;

        public SignalMessage(SignalData signalData) {
            super(3, new ProtobufMessage[0]);
            this.sigName = signalData.name;
            this.sigCode = signalData.code;
            this.sigAddr = signalData.faultAddress;
        }

        public int getPropertiesSize() {
            return CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(this.sigName)) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(this.sigCode)) + CodedOutputStream.computeUInt64Size(3, this.sigAddr);
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            codedOutputStream.writeBytes(1, ByteString.copyFromUtf8(this.sigName));
            codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(this.sigCode));
            codedOutputStream.writeUInt64(3, this.sigAddr);
        }
    }

    private static final class ThreadMessage extends ProtobufMessage {
        private static final int PROTOBUF_TAG = 1;
        private final int importance;
        private final String name;

        public ThreadMessage(ThreadData threadData, RepeatedMessage repeatedMessage) {
            super(1, repeatedMessage);
            this.name = threadData.name;
            this.importance = threadData.importance;
        }

        public int getPropertiesSize() {
            return (hasName() ? CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(this.name)) : 0) + CodedOutputStream.computeUInt32Size(2, this.importance);
        }

        public void writeProperties(CodedOutputStream codedOutputStream) {
            if (hasName()) {
                codedOutputStream.writeBytes(1, ByteString.copyFromUtf8(this.name));
            }
            codedOutputStream.writeUInt32(2, this.importance);
        }

        private boolean hasName() {
            return this.name != null && this.name.length() > 0;
        }
    }

    NativeCrashWriter() {
    }

    private static EventMessage createEventMessage(SessionEventData sessionEventData, LogFileManager logFileManager, Map<String, String> map) {
        ApplicationMessage applicationMessage = new ApplicationMessage(new ExecutionMessage(new SignalMessage(sessionEventData.signal != null ? sessionEventData.signal : DEFAULT_SIGNAL), createThreadsMessage(sessionEventData.threads), createBinaryImagesMessage(sessionEventData.binaryImages)), createCustomAttributesMessage(mergeCustomAttributes(sessionEventData.customAttributes, map)));
        ProtobufMessage createDeviceMessage = createDeviceMessage(sessionEventData.deviceData);
        ByteString byteStringForLog = logFileManager.getByteStringForLog();
        if (byteStringForLog == null) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "No log data to include with this event.");
        }
        logFileManager.clearLog();
        return new EventMessage(sessionEventData.timestamp, NDK_CRASH_TYPE, applicationMessage, createDeviceMessage, byteStringForLog != null ? new LogMessage(byteStringForLog) : new NullMessage());
    }

    private static CustomAttributeData[] mergeCustomAttributes(CustomAttributeData[] customAttributeDataArr, Map<String, String> map) {
        int i = 0;
        TreeMap treeMap = new TreeMap(map);
        if (customAttributeDataArr != null) {
            for (CustomAttributeData customAttributeData : customAttributeDataArr) {
                treeMap.put(customAttributeData.key, customAttributeData.value);
            }
        }
        Map.Entry[] entryArr = (Map.Entry[]) treeMap.entrySet().toArray(new Map.Entry[treeMap.size()]);
        CustomAttributeData[] customAttributeDataArr2 = new CustomAttributeData[entryArr.length];
        while (true) {
            int i2 = i;
            if (i2 >= customAttributeDataArr2.length) {
                return customAttributeDataArr2;
            }
            customAttributeDataArr2[i2] = new CustomAttributeData((String) entryArr[i2].getKey(), (String) entryArr[i2].getValue());
            i = i2 + 1;
        }
    }

    private static ProtobufMessage createDeviceMessage(DeviceData deviceData) {
        if (deviceData == null) {
            return new NullMessage();
        }
        return new DeviceMessage(((float) deviceData.batteryCapacity) / 100.0f, deviceData.batteryVelocity, deviceData.proximity, deviceData.orientation, deviceData.totalPhysicalMemory - deviceData.availablePhysicalMemory, deviceData.totalInternalStorage - deviceData.availableInternalStorage);
    }

    private static RepeatedMessage createThreadsMessage(ThreadData[] threadDataArr) {
        ThreadMessage[] threadMessageArr = threadDataArr != null ? new ThreadMessage[threadDataArr.length] : EMPTY_THREAD_MESSAGES;
        for (int i = 0; i < threadMessageArr.length; i++) {
            ThreadData threadData = threadDataArr[i];
            threadMessageArr[i] = new ThreadMessage(threadData, createFramesMessage(threadData.frames));
        }
        return new RepeatedMessage(threadMessageArr);
    }

    private static RepeatedMessage createFramesMessage(ThreadData.FrameData[] frameDataArr) {
        FrameMessage[] frameMessageArr = frameDataArr != null ? new FrameMessage[frameDataArr.length] : EMPTY_FRAME_MESSAGES;
        for (int i = 0; i < frameMessageArr.length; i++) {
            frameMessageArr[i] = new FrameMessage(frameDataArr[i]);
        }
        return new RepeatedMessage(frameMessageArr);
    }

    private static RepeatedMessage createBinaryImagesMessage(BinaryImageData[] binaryImageDataArr) {
        BinaryImageMessage[] binaryImageMessageArr = binaryImageDataArr != null ? new BinaryImageMessage[binaryImageDataArr.length] : EMPTY_BINARY_IMAGE_MESSAGES;
        for (int i = 0; i < binaryImageMessageArr.length; i++) {
            binaryImageMessageArr[i] = new BinaryImageMessage(binaryImageDataArr[i]);
        }
        return new RepeatedMessage(binaryImageMessageArr);
    }

    private static RepeatedMessage createCustomAttributesMessage(CustomAttributeData[] customAttributeDataArr) {
        CustomAttributeMessage[] customAttributeMessageArr = customAttributeDataArr != null ? new CustomAttributeMessage[customAttributeDataArr.length] : EMPTY_CUSTOM_ATTRIBUTE_MESSAGES;
        for (int i = 0; i < customAttributeMessageArr.length; i++) {
            customAttributeMessageArr[i] = new CustomAttributeMessage(customAttributeDataArr[i]);
        }
        return new RepeatedMessage(customAttributeMessageArr);
    }

    public static void writeNativeCrash(SessionEventData sessionEventData, LogFileManager logFileManager, Map<String, String> map, CodedOutputStream codedOutputStream) {
        createEventMessage(sessionEventData, logFileManager, map).write(codedOutputStream);
    }
}
