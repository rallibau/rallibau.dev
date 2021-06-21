package com.inetum.livetooling.apps.tagReader.filewatcher;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


@Service
public class DirectoryMonitor {

    private static final Logger logger = LogManager.getLogger(DirectoryMonitor.class);

    @Value("${directory.listener.path}")
    private String path;

    @Autowired
    FileListener fileListener;

    @EventListener(ApplicationReadyEvent.class)
    public void directoryListener() {

        String currentWorkingDirectory = System.getProperty("user.dir") + System.getProperty("file.separator");
        String completePath = currentWorkingDirectory.concat(path);

        FileAlterationObserver observer = new FileAlterationObserver(completePath);
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000);

        observer.addListener(this.fileListener);
        monitor.addObserver(observer);

        try {
            monitor.start();
            logger.info("Monitoring path: {}", completePath);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
