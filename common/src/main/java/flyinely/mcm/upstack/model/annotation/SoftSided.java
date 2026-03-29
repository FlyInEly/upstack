package flyinely.mcm.upstack.model.annotation;

import org.jetbrains.annotations.Contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("unused") // not for instantiation
public final class SoftSided {

   /**
    * Disallowed default constructor.
    */
   @Contract(value = " -> fail", pure = true)
   private SoftSided() {
      throw new AssertionError(SoftSided.class.getSimpleName() + " should not be instantiated");
   }
	
	/**
	 * Indicates that the target uses only client-side API.
	 */
	@SuppressWarnings("unused") // API completeness
   @Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface Client {
	
	}
	
	/**
	 * Indicates that the target uses only server-side API.
	 */
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface Server {
	
	}
}
