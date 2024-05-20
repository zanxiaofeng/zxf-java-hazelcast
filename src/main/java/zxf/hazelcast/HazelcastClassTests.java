package zxf.hazelcast;

import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;

public class HazelcastClassTests {
    public static void main(String[] args) {
        Reflections comReflections = new Reflections("com");
        for (Class<? extends IdentifiedDataSerializable> aClass : comReflections.getSubTypesOf(IdentifiedDataSerializable.class)) {
            System.out.println(aClass.getName() + ", Start");
            if (Modifier.isAbstract(aClass.getModifiers()) || Modifier.isInterface(aClass.getModifiers()) || !Modifier.isPublic(aClass.getModifiers())) {
                System.out.println(aClass.getName() + ", Abstract or Interface or Not Public");
                continue;
            }

            try {
                aClass.getConstructor();
            } catch (NoSuchMethodException ex) {
                System.out.println(aClass.getName() + ", No public default constructor");
            }

            try {
                IdentifiedDataSerializable serializable = aClass.newInstance();
                System.out.println(aClass.getName() + ", FactoryId=" + serializable.getFactoryId());
            } catch (Throwable throwable) {
                System.err.println(aClass.getName() + ", Exception");
                throwable.printStackTrace(System.err);
            }
        }
    }
}
