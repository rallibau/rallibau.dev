package com.rallibau.shared.domain;

import org.apache.logging.log4j.message.Message;
import org.json.JSONObject;

import java.util.HashMap;

public class MonitoringMessage implements Message {


    private static final String CLASS = "class";
    private static final String METHOD = "method";
    private static final String TIME = "time";

    private  final String className;
    private  final String methodName;
    private final long time;





    public MonitoringMessage(String className, String methodName, long time) {

        this.className = className;
        this.methodName = methodName;
        this.time = time;
    }

    @Override
    public String getFormattedMessage() {
        JSONObject jsonToLog = new JSONObject(new HashMap() {{
            put(CLASS, getClassName());
            put(METHOD, getMethodName());
            put(TIME, getTime());
        }});

        return jsonToLog.toString();
    }

    @Override
    public String getFormat() {
        return "";
    }

    @Override
    public Object[] getParameters() {
        return new Object[0];
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public long getTime() {
        return time;
    }
}
