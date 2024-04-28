package zxf.hazelcast;

import com.hazelcast.internal.nio.IOUtil;
import com.hazelcast.internal.serialization.Data;
import com.hazelcast.map.EntryProcessor;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.web.SessionState;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MySessionUpdateEntryProcessor implements EntryProcessor<String, SessionState, Object>, IdentifiedDataSerializable {

    private Map<String, Data> attributes;

    public MySessionUpdateEntryProcessor(int size) {
        this.attributes = new HashMap<String, Data>(size);
    }

    public MySessionUpdateEntryProcessor(String key, Data value) {
        attributes = new HashMap<String, Data>(1);
        attributes.put(key, value);
    }

    public MySessionUpdateEntryProcessor() {
        attributes = Collections.emptyMap();
    }

    public Map<String, Data> getAttributes() {
        return attributes;
    }

    public int getFactoryId() {
        return 2002;
    }

    public int getClassId() {
        return 2;
    }

    public Object process(Map.Entry<String, SessionState> entry) {
        SessionState sessionState = entry.getValue();
        if (sessionState == null) {
            sessionState = new SessionState();
        }
        for (Map.Entry<String, Data> attribute : attributes.entrySet()) {
            String name = attribute.getKey();
            Data value = attribute.getValue();
            if (value == null) {
                sessionState.getAttributes().remove(name);
            } else {
                sessionState.getAttributes().put(name, value);
            }
        }
        entry.setValue(sessionState);
        return Boolean.TRUE;
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeInt(attributes.size());
        for (Map.Entry<String, Data> entry : attributes.entrySet()) {
            out.writeUTF(entry.getKey());
            IOUtil.writeData(out, entry.getValue());
        }
    }

    public void readData(ObjectDataInput in) throws IOException {
        int attCount = in.readInt();
        attributes = new HashMap<String, Data>(attCount);
        for (int i = 0; i < attCount; i++) {
            attributes.put(in.readUTF(), IOUtil.readData(in));
        }
    }
}