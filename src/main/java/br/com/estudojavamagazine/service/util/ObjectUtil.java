package br.com.estudojavamagazine.service.util;

import java.util.Collection;

public abstract class ObjectUtil {

	public static final boolean isNotNull(Object obj) {
		if (obj == null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static final boolean isNull(Object obj) {
		if (isNotNull(obj)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static final boolean isNotNull(String str) {
		if (str == null || str.trim().isEmpty()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static final boolean isNull(String str) {
		if (isNotNull(str)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static final boolean isNotNullAndNotEmpty(Collection<?> collection) {
        if (isNull(collection) || collection.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
	
	public static final boolean isNotNull(Collection<?> collection) {
		if (collection == null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static final boolean isNull(Collection<?> collection) {
		if (isNotNull(collection)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
}
