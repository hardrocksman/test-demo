import org.apache.jmeter.config.Argument;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;
import org.apache.jmeter.threads.JMeterVariables;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] arsg) {
//        JMeterVariables vars = new JMeterVariables();
//
//        TreeMap<String, String> paramTreeMap = new TreeMap<String, String>();
//        Iterator<Map.Entry<String, Object>> it = vars.getIterator();
//
//        while(it.hasNext()) {
//            Map.Entry<String, Object> p = it.next();
//
//            paramTreeMap.put(p.getKey(), p.getValue().toString());
//        }


        Arguments args = new Arguments();//sampler.getArguments();
        Map paramTreeMap = new TreeMap();

        Map paramMap = args.getArgumentsAsMap();//.getArgument(1);

        Iterator it = paramMap.entrySet().iterator();
        while(it.hasNext()) {
            Object p = it.next();

        }





//        String paramStr = argument.getValue();
//
//        PropertyIterator propertyIterator = args.propertyIterator();
//        while(propertyIterator.hasNext()) {
//            JMeterProperty property = propertyIterator.next();
//            paramTreeMap.put(property.getName(), property.getStringValue());
//        }

    }

}
