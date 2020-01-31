package com.github;

import com.github.handlers.RequestHandler;

import static com.github.handlers.RequestHandlerImpl.newInstance;

public class AbstractTestClass {

    protected static RequestHandler sender = newInstance();
}
