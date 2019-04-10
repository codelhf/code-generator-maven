package com.example.generator.config.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.*;
import java.util.stream.Collectors;

public class XmlPropertyHolder {

    private final static String NAME = "name";
    private final static String VALUE = "value";

    //获取标签的子节点Property
    public static Map<String, String> parseChildProperty(List<Element> childNodes) {
        Map<String, String> map = new HashMap<>();
        for (Element childNode : childNodes) {
            if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                continue;
            }
            String childNodeName = childNode.getName();
            if ("property".equals(childNodeName)) {
                map.putAll(parseProperty(childNode));
            }
        }
        return map;
    }

    //获取property标签的name和value的属性值
    public static Map<String, String> parseProperty(Element node) {
        Properties attributes = parseAttributes(node);
        String name = attributes.getProperty(NAME);
        String value = attributes.getProperty(VALUE);
        Map<String, String> map = new HashMap<>();
        map.put(name, value);
        return map;
    }

    //获取property标签的属性列表
    public static Properties parseAttributes(Element node) {
        Properties attributes = new Properties();
        List<Attribute> attributeList = node.attributes();
        for (Attribute attribute: attributeList) {
            attributes.put(attribute.getName(), attribute.getValue());
        }
        return attributes;
    }

    //获取property标签属性value中的变量值
    public static String parsePropertyTokens(String s) {

        List<String> answer = new ArrayList<>();

        final String OPEN = "${";
        final String CLOSE = "}";
        int currentIndex = 0;

        int markerStartIndex = s.indexOf(OPEN);
        if (markerStartIndex < 0) {
            // no parameter markers
            answer.add(s);
            currentIndex = s.length();
        }

        while (markerStartIndex > -1) {
            if (markerStartIndex > currentIndex) {
                // add the characters before the next parameter marker
                answer.add(s.substring(currentIndex, markerStartIndex));
                currentIndex = markerStartIndex;
            }

            int markerEndIndex = s.indexOf(CLOSE, currentIndex);

            if (markerEndIndex < 0) {
                // no closing delimiter, just move to the end of the string
                answer.add(s.substring(markerStartIndex));
                currentIndex = s.length();
                break;
            }

            // we have a valid property marker...
            String property = s.substring(markerStartIndex + OPEN.length(), markerEndIndex);
            String propertyValue = resolveProperty(property, null, null);
            if (propertyValue == null) {
                // add the property marker back into the stream
                answer.add(s.substring(markerStartIndex, markerEndIndex + 1));
            } else {
                answer.add(propertyValue);
            }

            currentIndex = markerEndIndex + CLOSE.length();
            markerStartIndex = s.indexOf(OPEN, currentIndex);
        }

        if (currentIndex < s.length()) {
            answer.add(s.substring(currentIndex));
        }

        return answer.stream().collect(Collectors.joining());
    }

    public static String resolveProperty(String key, Properties configProperties, Properties extraProperties) {
        String property = System.getProperty(key);

        if (property == null) {
            property = configProperties.getProperty(key);
        }

        if (property == null) {
            property = extraProperties.getProperty(key);
        }
        return property;
    }
}
