package com.github.pages.pricing;

import com.github.pages.AbstractGitHubPage;
import org.springframework.stereotype.Component;

@Component
public class PricingPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "pricing";

    public void openPage(){
        driver.get(PAGE_URL);
    }

}
