package br.com.guilhermebehs.ftgo.kitchen.infra;

import com.google.common.base.CaseFormat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static br.com.guilhermebehs.ftgo.kitchen.infra.ScapeUtil.scape;
import static br.com.guilhermebehs.ftgo.kitchen.infra.ScapeUtil.scapeStackTrace;
import static org.apache.commons.lang.exception.ExceptionUtils.getMessage;


@Aspect
@Component
public class LoggingAspect {

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.stereotype.Component *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the organization main packages.
     */
    @Pointcut("within(br.com.guilhermebehs.ftgo..*)")
    public void organizationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }


    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("organizationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = this.convertLowerCamelToLowerHyphen(joinPoint.getSignature().getName());

        var logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());

        try {

            logger.info("{}; start; arguments=\"{}\";", scape(methodName), scape(Arrays.toString(joinPoint.getArgs())));

            Object result = joinPoint.proceed();

            logger.info("{}; end; success; arguments=\"{}\"; result=\"{}\";",
                    scape(methodName),
                    scape(Arrays.toString(joinPoint.getArgs())),
                    scape(result));

            return result;

        } catch (IllegalArgumentException e) {
            logger.error("{}; exception; customMessage=\"Illegal argument with argument[s] = {}\"; " +
                            "message=\"{}\"; stackTrace=\"{}\";",
                    scape(methodName),
                    scape(Arrays.toString(joinPoint.getArgs())),
                    scape(getMessage(e)),
                    scapeStackTrace(e));
            throw e;
        }
    }

    private String convertLowerCamelToLowerHyphen(String value) {
        return CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(value);
    }

}