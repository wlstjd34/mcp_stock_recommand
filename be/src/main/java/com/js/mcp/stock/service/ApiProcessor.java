package com.js.mcp.stock.service;

public interface ApiProcessor {
    String callApi(String url) throws InterruptedException;
}
