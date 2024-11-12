package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String str = "kek";
        char k = 'k';
        int integer = 777;
        short shortValue = 32767;
        byte byteValue = 127;
        double doubleValue = 1.1123123;
        float floatValue = 1.432432F;
        boolean booleanValue = true;

        LOG.info("String: {}", str);
        LOG.info("char: {}", k);
        LOG.info("int: {}", integer);
        LOG.info("short: {}", shortValue);
        LOG.info("byte: {}", byteValue);
        LOG.info("double: {}", doubleValue);
        LOG.info("float: {}", floatValue);
        LOG.info("boolean: {}", booleanValue);
    }
}