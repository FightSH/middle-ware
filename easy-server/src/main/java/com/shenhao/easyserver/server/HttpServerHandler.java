package com.shenhao.easyserver.server;

import com.shenhao.easyserver.core.springmvc.factory.RequestHandlerFactory;
import com.shenhao.easyserver.core.springmvc.handler.RequestHandler;
import com.shenhao.easyserver.core.springmvc.util.UrlUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.AsciiString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final String FAVICON_ICO = "/favicon.ico";
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        log.info("handle http request:[{}]", fullHttpRequest);
        String uri = fullHttpRequest.uri();
        if (FAVICON_ICO.equals(uri)) {
            return;
        }
        RequestHandler requestHandler = RequestHandlerFactory.get(fullHttpRequest.method());
        FullHttpResponse fullHttpResponse;
        try {
            FullHttpResponse response = requestHandler.handle(fullHttpRequest);
        } catch (Throwable e) {
            log.error("Caught an unexpected error.", e);
            String requestPath = UrlUtil.getRequestPath(uri);

        }
    }
}
