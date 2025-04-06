package com.js.mcp.stock.service;

import java.io.IOException;

public interface ApiProcessor {
    String callApi(String url) throws IOException, InterruptedException;
}
