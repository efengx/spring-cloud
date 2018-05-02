package com.ofengx.cloud.demo.filter

import groovy.util.logging.Slf4j

import javax.servlet.http.HttpServletRequest

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext

@Slf4j
class AccessFilter extends ZuulFilter{

//    do you need to filter
    @Override
    boolean shouldFilter() {
        return true
    }

//    filter order
    @Override
    int filterOrder() {
        return 0
    }

    /**
     * pre: before routing
     * routing: when routing
     * post: after routing
     * error: when sending an error
     */
    @Override
    String filterType() {
        return "pre"
    }

    @Override
    Object run() {
        RequestContext ctx = RequestContext.getCurrentContext()
        HttpServletRequest  request = ctx.getRequest()
        log.info(
                " send {} request to {} "
                , request.getMethod()
                , request.getRequestURL().toString()
        )
        Object accessToken = request.getParameter("accessToken")
        if( null == accessToken) {
            log.info("access token is empty")
            ctx.setSendZuulResponse(false)
            ctx.setResponseStatusCode(401)
            try {
                ctx.getResponse().getWriter().write('accessToken is empty')
            } catch ( e ) {
                assert e
            }
//			ctx.setResponseBody("")
        }
        log.info("access token ok")
        return null
    }

}
