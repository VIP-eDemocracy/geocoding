


import java.io.IOException;
import java.util.List;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;
import edu.stanford.nlp.classify.*;

@SpringBootApplication
public class Main {

    private static String serializedClassifier  = "lib/stanford-ner-2015-04-20/classifiers/english.all.3class.distsim.crf.ser.gz";
    private static AbstractSequenceClassifier<CoreLabel> classifier;

    static {
        try {
            classifier = CRFClassifier.getClassifier(serializedClassifier);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    public static String ExtractLocation() throws Exception {

        String ans = "Good afternoon Rajat Raina, how are you today? Stanford, Atlanta, Georgia, Seattle, Boston";
        if (classifier.classifyToString(ans).contains("LOCATION")) {
            List<Triple<String,Integer,Integer>> triples = classifier.classifyToCharacterOffsets(ans);
            for (Triple<String,Integer,Integer> trip : triples) {
                if (trip.first().equals("LOCATION")) {
                    String loc = ans.substring(trip.second, trip.third);
                    return loc;
                }
            }
        }
        return null;
    }
}
