package projekat.config;

import feign.FeignException;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return (methodKey, response) -> {
            byte[] body = {};
            try {
                if (response.body() != null) {
                    body = Util.toByteArray(response.body().asInputStream());
                }
            } catch (IOException ignored) { // NOPMD
            }
            FeignException exception = new FeignException.BadRequest(response.reason(), response.request(), body, response.headers());
            if (response.status() >= 400) {
                return new RetryableException(
                        response.status(),
                        response.reason(),
                        response.request().httpMethod(),
                        exception,
                        Date.from(Instant.now().plus(15, ChronoUnit.MILLIS)),
                        response.request());
            }
            return exception;
        };
    }
}
