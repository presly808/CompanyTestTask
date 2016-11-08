package com.asp.company;

import com.asp.company.utils.service_locator.ServiceLocator;
import com.asp.company.view.CompanyServiceView;

/**
 * Created by serhii on 10/29/16.
 */
public class Run {

    public static void main(String[] args) {
        CompanyServiceView companyServiceView = ServiceLocator.getBean(CompanyServiceView.class);
        companyServiceView.start();
    }
}
