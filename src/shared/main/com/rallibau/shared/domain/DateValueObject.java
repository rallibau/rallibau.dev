package com.rallibau.shared.domain;

import java.util.Date;
import java.util.Objects;

public class DateValueObject {
    private Date value;

    public DateValueObject(Date value) {
        this.value = value;
    }

    public Date value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateValueObject)) {
            return false;
        }
        DateValueObject that = (DateValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
