package com.inetum.livetooling.shared.domain;

import org.apache.commons.lang3.RandomStringUtils;

public final class WordMother {

    public static final int LENGTH = 20;

    public static String random() {
        return MotherCreator.random().lorem().word();
    }

    public static String randomNumber() {
        return RandomStringUtils.random(LENGTH, false, true);
    }
}
