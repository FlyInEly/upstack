package flyinely.mcm.upstack.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class CContract {

   /**
    * Indicates that the method promises to return the same value throughout runtime.
    */
   @Retention(RetentionPolicy.SOURCE)
   @Target({ElementType.METHOD})
   @SuppressWarnings("unused") // api
   public @interface ConstantReturn {

   }

   /**
    * Indicates that the method promises that it is empty, and is used to trigger the class's static initializer block.
    */
   @Retention(RetentionPolicy.SOURCE)
   @Target({ElementType.METHOD})
   public @interface StaticInit {
   }

   /**
    * Indicates that the class promises that all public members are {@code static final}, and there
    * are no public methods.
    */
   @Retention(RetentionPolicy.SOURCE)
   @Target({ElementType.TYPE})
   public @interface StaticRegistry {
   }
}
