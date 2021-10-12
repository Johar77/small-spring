package com.johar.springframework.aop.aspectj;

import com.johar.springframework.aop.Pointcut;
import com.johar.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @ClassName: AspectJExpressionPointcutAdvisor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 14:39
 * @Since: 1.0.0
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null){
            pointcut = new AspectJExpressionPointcut(expression);
        }

        return pointcut;
    }
}