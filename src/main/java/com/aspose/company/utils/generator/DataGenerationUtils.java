package com.aspose.company.utils.generator;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by serhii on 10/31/16.
 */
public final class DataGenerationUtils {

    public static AtomicInteger atomicLong = createInstance();

    public synchronized static AtomicInteger createInstance(){
        return new AtomicInteger(1);
    }

    public static int generateId(){
        return atomicLong.getAndIncrement();
    }

}
