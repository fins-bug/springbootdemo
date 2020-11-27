package work.finsbug.user.manager;


import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description 
 * @author fins
 * @date 2020/11/25
 **/
public class UserApplication {

    public static void main(String[] args) {
        String result= Stream.of(1,2,3,4,5)
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println(result);

        String json = "{\"versionName\":\"旗舰版\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer custCount = jsonObject.getInteger("custCount");
        System.out.println(custCount);

        Integer compareInt = 2;
        int res = compareInt.compareTo(custCount);
        System.out.println(res);
    }
}
