package com.pjmike.retry;

public interface RetryService {
    void query();

    Object run();

    Object retry() throws Exception;
}
