package fil.iagl.iir;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FeedMeApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {FeedMeConfiguration.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return ArrayUtils.EMPTY_CLASS_ARRAY;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}
