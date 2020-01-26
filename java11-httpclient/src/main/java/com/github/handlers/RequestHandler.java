package com.github.handlers;

import java.net.http.HttpResponse;

public interface RequestHandler {

    HttpResponse<String> sendGet(String url);

    HttpResponse<String> sendHead(String url);

    HttpResponse<String> sendOptions(String url);


}
