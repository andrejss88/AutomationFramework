package com.github.handlers;

import java.net.http.HttpResponse;

public interface RequestHandler<T> {

    HttpResponse<T> sendGet(String url);

    HttpResponse<T> sendHead(String url);

    HttpResponse<T> sendOptions(String url);


}
