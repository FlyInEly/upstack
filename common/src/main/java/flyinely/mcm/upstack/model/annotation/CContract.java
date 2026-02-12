package flyinely.mcm.upstack.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class CContract {

   /**
    * Indicates that the method promises to return the same value throughout runtime.
    */
   @Retention(RetentionPolicy.SOURCE)
   @Target({ElementType.METHOD})
   public @interface ConstantReturn {

   }
}
