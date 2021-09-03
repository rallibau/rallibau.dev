package com.rallibau.bpm.processFile.domain;

import java.util.List;

public interface BpmModelExtractor {
    List<BpmModel> extractProcess(String filePath) throws BpmModelExtractorException;
}
