package fil.iagl.iir.outils;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeedMeAspect {

  private static Logger log = Logger.getLogger(FeedMeAspect.class);

  @Around("execution(fil.iagl.iir.outils.DataReturn fil.iagl.iir.controller.**.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public DataReturn<?> injecterRetourDansObjet(ProceedingJoinPoint joinPoint) throws Throwable {
    DataReturn<?> result;
    try {
      result = (DataReturn<?>) joinPoint.proceed();
      MethodSignature ms = (MethodSignature) joinPoint.getSignature();
      Method m = ms.getMethod();
      result.setSucces(Arrays.stream(m.getAnnotations()).filter(ann -> ann.annotationType().equals(MessageSucces.class)).map(ann -> ((MessageSucces) (ann)).value())
        .findFirst().orElse(null));
    } catch (FeedMeException fme) {
      log.error("Une exception a été soulever dans la couche service.", fme);
      result = new DataReturn<>();
      result.setError(fme.getMessage());
    }
    return result;
  }

}
