package zxf.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientMetricsConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.client.properties.ClientProperty;
import com.hazelcast.config.GlobalSerializerConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import info.jerrinot.subzero.SubZero;
import info.jerrinot.subzero.relocated.com.esotericsoftware.minlog.Log;

public class HazelcastFactory {
    public static HazelcastInstance getHazelcastInstance(String instanceName, String clusterName, String address) {
        Log.set(Log.LEVEL_TRACE);
        System.setProperty("hazelcast.logging.type", "slf4j");
        ClientProperty.METRICS_ENABLED.setSystemProperty("false");
        ClientProperty.STATISTICS_ENABLED.setSystemProperty("false");
        ClientProperty.STATISTICS_PERIOD_SECONDS.setSystemProperty("120");
        ClientProperty.HEARTBEAT_INTERVAL.setSystemProperty("1200000");
        ClientProperty.HEARTBEAT_TIMEOUT.setSystemProperty("1200000");
        ClientProperty.EVENT_THREAD_COUNT.setSystemProperty("1");
        ClientProperty.IO_INPUT_THREAD_COUNT.setSystemProperty("1");
        ClientProperty.IO_OUTPUT_THREAD_COUNT.setSystemProperty("1");
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setProperty("hazelcast.logging.type", "slf4j");
        clientConfig.setProperty("hazelcast.diagnostics.enabled", "false");
        clientConfig.setInstanceName(instanceName);
        clientConfig.setClusterName(clusterName);
        ClientNetworkConfig networkConfig = new ClientNetworkConfig();
        networkConfig.addAddress(address);
        networkConfig.setSmartRouting(true);
        clientConfig.setNetworkConfig(networkConfig);
        clientConfig.setMetricsConfig(new ClientMetricsConfig().setEnabled(false));
        SubZero.useAsGlobalSerializer(clientConfig);
        return HazelcastClient.newHazelcastClient(clientConfig);
    }
}
