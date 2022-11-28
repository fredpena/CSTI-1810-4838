package com.mdcgroup.myapplication.utils;

public interface NetResponse<T> {

    //Listener
    /**
     * Callback interface for delivering parsed responses.
     * @param response
     */
    void onResponse(T response);

    /**
     * Callback interface for delivering error responses.
     */
    void onFailure(Throwable t);
}
