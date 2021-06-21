package com.inetum.livetooling.apps.tagReader.filewatcher;


import com.inetum.livetooling.apps.tagReader.reader.TagListProcesor;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileListener extends FileAlterationListenerAdaptor {

    private static final Logger logger = LogManager.getLogger(FileListener.class);


    private final TagListProcesor tagListReader;

    public FileListener(TagListProcesor tagListReader) {
        this.tagListReader = tagListReader;
    }

    @Override
    public void onFileCreate(final File file) {
        logger.info("Fichero creado: {}", file.getAbsolutePath());

        tagListReader.processFile(file);

    }

    @Override
    public void onFileChange(final File file) {
        logger.info("Fichero cambiado: {}", file.getAbsolutePath());

    }
}
