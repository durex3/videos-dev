package com.durex.videos.exception;

/**
 * @author gelong
 */
public class VideosException extends RuntimeException {
    public VideosException() {
        super();
    }

    public VideosException(String message) {
        super(message);
    }

    public VideosException(String message, Throwable cause) {
        super(message, cause);
    }

    public VideosException(Throwable cause) {
        super(cause);
    }

    protected VideosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}