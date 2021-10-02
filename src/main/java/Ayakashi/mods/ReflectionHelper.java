package Ayakashi.mods;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static Field findField(Class<?> clazz, String... fieldNames) {
        Exception failed = null;
        int var4 = fieldNames.length;
        int var5 = 0;

        while (var5 < var4) {
            String fieldName = fieldNames[var5];

            try {
                Field f = clazz.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } catch (Exception var7) {
                failed = var7;
                ++var5;
            }
        }

        throw new ReflectionHelper.UnableToFindFieldException(failed);
    }

    public static <T, E> T getPrivateValue(Class<? super E> classToAccess, E instance, int fieldIndex) {
        try {
            Field f = classToAccess.getDeclaredFields()[fieldIndex];
            f.setAccessible(true);
            return (T) f.get(instance);
        } catch (Exception var4) {
            throw new ReflectionHelper.UnableToAccessFieldException(new String[0], var4);
        }
    }

    public static <T, E> T getPrivateValue(Class<? super E> classToAccess, E instance, String... fieldNames) {
        try {
            return (T) findField(classToAccess, fieldNames).get(instance);
        } catch (Exception var4) {
            throw new ReflectionHelper.UnableToAccessFieldException(fieldNames, var4);
        }
    }

    public static Class<? super Object> getClass(ClassLoader loader, String... classNames) {
        Exception err = null;
        int var4 = classNames.length;
        int var5 = 0;

        while (var5 < var4) {
            String className = classNames[var5];

            try {
                return (Class<? super Object>) Class.forName(className, false, loader);
            } catch (Exception var7) {
                err = var7;
                ++var5;
            }
        }

        throw new ReflectionHelper.UnableToFindClassException(err);
    }

    public static class UnableToFindClassException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UnableToFindClassException(Exception err) {
            super(err);
        }
    }

    public static class UnableToFindFieldException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UnableToFindFieldException(Exception e) {
            super(e);
        }
    }

    public static class UnableToAccessFieldException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        private final String[] fieldNameList;

        public UnableToAccessFieldException(String[] fieldNames, Exception e) {
            super(e);
            this.fieldNameList = fieldNames;
        }
    }
}
