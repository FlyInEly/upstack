package flyinely.mcm.upstack.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class SoftSided {
	
	/**
	 * Indicates that the target uses only client-side API.
	 */
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
