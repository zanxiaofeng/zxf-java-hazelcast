package zxf.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.internal.serialization.Data;
import com.hazelcast.map.IMap;
import com.hazelcast.spi.impl.SerializationServiceSupport;
import com.hazelcast.web.SessionState;

public class HazelcastTests {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance instance = HazelcastFactory.getHazelcastInstance("Abc", "dev", "localhost:5701");

        try {
            System.out.println("start");
            IMap map = instance.getMap("my.map");
            map.put("abc", "123");
            System.out.println("end");
            Thread.sleep(6000);

            Data data = ((SerializationServiceSupport) instance).getSerializationService().toData("Test Str");
            MySessionUpdateEntryProcessor sessionUpdateEntryProcessor = new MySessionUpdateEntryProcessor("attr1", data);
            map.executeOnKey("session-1", sessionUpdateEntryProcessor);
            SessionState session1 = (SessionState) map.get("session-1");
            System.out.println("Session: " + session1);
            Data attr1 = session1.getAttributes().get("attr1");
            String value = ((SerializationServiceSupport) instance).getSerializationService().toObject(attr1);
            System.out.println("Attr: " + value);
        } finally {
            instance.shutdown();
        }
    }
}
