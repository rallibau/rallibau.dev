package com.rallibau.bpm.processFile.domain;

public class BpmModelExtractorException extends Exception {
    public BpmModelExtractorException(String message, Exception exception) {
        super(message, exception);
    }
}
