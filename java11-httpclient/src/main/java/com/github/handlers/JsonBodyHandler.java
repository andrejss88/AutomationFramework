package com.github.handlers;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.ByteArrayInputStream;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.BodySubscribers;
import static java.net.http.HttpResponse.ResponseInfo;

/**
 * A custom BodyHandler that implements the interface of the common BodyHandler API
 * This makes this class compatible and interchangeable with other standard handlers, such as ofString()
 */
public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {

    private final Jsonb jsonb;
    private final Class<T> type;

    private JsonBodyHandler(Jsonb jsonb, Class<T> type) {
        this.jsonb = jsonb;
        this.type = type;
    }

    public static <T> JsonBodyHandler<T> jsonBodyHandler(final Class<T> type) {
        return new JsonBodyHandler<>(JsonbBuilder.create(), type);
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(final ResponseInfo responseInfo) {
        return BodySubscribers.mapping(BodySubscribers.ofByteArray(),
                byteArray -> this.jsonb.fromJson(new ByteArrayInputStream(byteArray), this.type));
    }
}