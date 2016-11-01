package com.aspose.company.utils.converter;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by serhii on 10/31/16.
 */
public class DateUtilsTest {

    @Test
    public void testToYears() throws Exception {

        Date hireDate = DateUtils.createDate(2006,1,1);

        Date now = DateUtils.createDate(2010,1,1);

        Assert.assertEquals(4, DateUtils.getDiffYears(hireDate, now));
    }
}