package com.example.springbootstudy.resolver;

import com.example.springbootstudy.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;

public class UserParamterRelover implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return User.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, Message<?> message) throws Exception {
        System.out.println("----------");
        String object =  message.getPayload().toString();
        String [] as = object.split(" ");
        User user =  new User();
        user.setContent(as[0]);
        user.setSendTime(as[1]);
        return user;
    }
}
