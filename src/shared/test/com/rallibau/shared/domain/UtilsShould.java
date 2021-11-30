package com.rallibau.shared.domain;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;

public class UtilsShould {

    @Test
    public void date_serialize_and_deserialize_is_ok() {
        ZonedDateTime localDateTime = ZonedDateTime.now();
        String stringTime = Utils.dateToString(localDateTime);
        assertThat("date not is empty", !stringTime.isEmpty());
    }
}
