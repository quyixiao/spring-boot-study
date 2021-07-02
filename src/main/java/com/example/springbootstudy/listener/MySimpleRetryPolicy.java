package com.example.springbootstudy.listener;

import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.context.RetryContextSupport;

import java.util.HashSet;
import java.util.Set;

public class MySimpleRetryPolicy implements RetryPolicy {

    private int maxCount = 8;

    @Override
    public boolean canRetry(RetryContext context) {
        MySimpleRetryContext mySimpleRetryContext = (MySimpleRetryContext) context;
        if (mySimpleRetryContext.getRetryCount() > maxCount) {
            return false;
        }
        for (Throwable throwable : mySimpleRetryContext.getThrowables()) {
            if (throwable instanceof ListenerExecutionFailedException) {
                if(throwable.getCause() instanceof NullPointerException){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public RetryContext open(RetryContext parent) {
        return new MySimpleRetryContext(parent);
    }

    @Override
    public void close(RetryContext context) {

    }

    @Override
    public void registerThrowable(RetryContext context, Throwable throwable) {
        MySimpleRetryContext mySimpleRetryContext = (MySimpleRetryContext) context;
        mySimpleRetryContext.addThrowables(throwable);
        mySimpleRetryContext.registerThrowable(throwable);
    }

    public class MySimpleRetryContext extends RetryContextSupport {
        private Set<Throwable> throwables = new HashSet<>();


        public MySimpleRetryContext(RetryContext parent) {
            super(parent);
        }

        public Set<Throwable> getThrowables() {
            return throwables;
        }

        public void addThrowables(Throwable throwable) {
            this.throwables.add(throwable);
        }
    }


}
