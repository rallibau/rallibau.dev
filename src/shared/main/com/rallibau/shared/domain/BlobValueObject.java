package com.rallibau.shared.domain;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Objects;

public abstract class BlobValueObject {
    private Blob value;

    public BlobValueObject(String value) {
        try {
            this.value = new SerialBlob(value.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            this.value = null;
        }
    }

    public String value() {
        try {
            byte[] data = value.getBytes(1, (int) value.length());
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public void value(String value) throws SQLException {
        this.value = new SerialBlob(value.getBytes());
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlobValueObject)) {
            return false;
        }
        BlobValueObject that = (BlobValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
