package common;


import java.lang.reflect.Method;

/**
 * @description
 * @author fins
 * @date 2020/12/24
 **/
public class StaticProcessor {

    public static void process(Class<?> clazz, String methodName, boolean isShow) {
        if (clazz == null || !isShow) {
            return;
        }
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            method.invoke(null);
        } catch (Exception e) {
            System.out.println("反射调用异常:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
