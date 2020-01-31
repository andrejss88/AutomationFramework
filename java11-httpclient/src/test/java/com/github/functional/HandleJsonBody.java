package com.github.functional;

import com.github.entities.Owner;
import com.github.handlers.JsonBodyHandler;
import com.github.handlers.RequestHandlerImpl;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static com.github.Constants.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class HandleJsonBody {

    @Test
    void handleJsonBody() {

        HttpResponse.BodyHandler<Owner> handler = JsonBodyHandler.jsonBodyHandler(Owner.class);
        var client = RequestHandlerImpl.newInstance(handler);

        HttpResponse<Owner> response = client.sendGet(BASE_URL + "users/andrejss88");

        String actualLogin = response.body().getLogin();

        assertThat(actualLogin).isEqualTo("andrejss88");
    }
}
