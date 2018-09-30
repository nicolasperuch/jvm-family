package dev.peruch.rabbitxml.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.peruch.rabbitxml.model.PaymentModel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class XmlService {

    public static final String REGEX_ROOT_XML = "<CURRENT_VALUES>([\\s\\w\\<\\>:=\"\\/.-]+)<\\/CURRENT_VALUES>";
    public static final String REGEX_ATTRIBUTES = "\\s*\\<([\\s\\w]+)\\>\\s*<.+>([\\s\\w\\.\\:\\-]+)?<\\/value>\\s*<\\/\\1>\\s*";
    private Pattern patternRoot;
    private Pattern patternAttributes;
    private ObjectMapper objectMapper;

    public PaymentModel buildPaymentModel(Message message) throws Exception {
        String messageXml = new String(message.getBody());
        Matcher rootElements = patternRoot.matcher(messageXml);
        final Map<String, String> map = new HashMap<>(4);
        while (rootElements.find()){
            Matcher attributes = patternAttributes.matcher(rootElements.group(1));
            while (attributes.find()){
                map.put(attributes.group(1), attributes.group(2));
            }
        }
        if(map.isEmpty() || map.size() == 0) throw new Exception("Failed to parse XML " + messageXml);

        PaymentModel paymentModel = new PaymentModel()
                .setCpf(map.get("numeroDocumento"))
                .setDate(map.get("dataHoraTransacao"))
                .setId(map.get("numeroConta"))
                .setNzu(map.get(""))
                .setValue(map.get("valor"));
        return paymentModel;
    }

    public Pattern getPatternRoot() {
        return patternRoot;
    }

    public void setPatternRoot(Pattern patternRoot) {
        this.patternRoot = patternRoot;
    }

    public Pattern getPatternAttributes() {
        return patternAttributes;
    }

    public void setPatternAttributes(Pattern patternAttributes) {
        this.patternAttributes = patternAttributes;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
