package com.Logger.Test;

public @interface ConfigItem 
{
	String ItemName() default "";
	
	String KeyField() default "";
}
