//package com.weng.openapigateway.filter;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.DigestUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Set;
//
//@Component //将过滤器交给spring容器管理
//@RequiredArgsConstructor
//@Slf4j
//public class CustomGlobalFilter implements GlobalFilter, Ordered {
//    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //1.用户鉴权，判断sign、nonce、timestamp是否合法
//        ServerHttpRequest request = exchange.getRequest();
//        HttpHeaders headers = request.getHeaders();
//        parseHeader(headers);
//        //2.
//
//
//        return chain.filter(exchange);
//    }
//
//    private void parseHeader(HttpHeaders headers) {
//        String sign = headers.getFirst("sign");
//        String nonce = headers.getFirst("nonce");
//        String timestamp = headers.getFirst("timestamp");
//        if (timestamp == null || nonce == null || sign == null) {
//            throw new RuntimeException("请求头不完整");
//        }
//        checkSign(sign);
//        checkNonce(nonce);
//        checkTimestamp(timestamp);
//
//    }
//
//    private void checkSign(String sign) {
//        List<User> users = userMapper.selectList(null);
//        for (User user : users) {
//            if (DigestUtils.md5DigestAsHex((user.getApiKey()).getBytes()).equals(sign)) {
//                return;
//            }
//        }
//        throw new RuntimeException("用户不存在");
//    }
//
//    /**
//     * 检查nonce是否存在，若存在则抛出异常。若不存在则将nonce存入redis，并设置过期时间为5分钟。和时间戳一起判断是否重放攻击
//     *
//     * @param nonce
//     */
//    private void checkNonce(String nonce) {
//        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
//        Set<String> keys = hashOperations.keys("nonce");
//        //判断随机数是否存在于redis中
//        if (keys.contains(nonce)) {
//            throw new RuntimeException("nonce已存在");
//        }
//        hashOperations.put("nonce", nonce, "-");
//        //设置过期时间为5分钟
//        stringRedisTemplate.expire("nonce", Duration.ofMinutes(5));
//    }
//
//    private void checkTimestamp(String timestamp) {
//        long timeMillis = Long.parseLong(timestamp);
//        long currentTimeMillis = System.currentTimeMillis();
//        //判断时间戳是否在5分钟内
//        if (currentTimeMillis - timeMillis < 0 || currentTimeMillis - timeMillis > 1000 * 60 * 5) {
//            throw new RuntimeException("时间戳不合法");
//        }
//    }
//
//    @Override
//    public int getOrder() {
//        //返回值越小，优先级越高，保证在NettyRoutingFilter之前执行
//        return 0;
//    }
//}
