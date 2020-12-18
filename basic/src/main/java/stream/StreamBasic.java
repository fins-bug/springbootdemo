package stream;


import org.assertj.core.util.Maps;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description 基础的stream用法
 * @author fins
 * @date 2020/12/18
 **/
public class StreamBasic {

    public static void main(String[] args) {
        // SourceData
        List<String> exampleData1 = Arrays.asList("f", "a", "c", "b", "g");
        List<Integer> exampleData2 = Arrays.asList(56, 3, -67, -78, 3, 56, null, 45, 100);
        Map<String, Object> exampleData3 = new HashMap<>(80);
        exampleData3.put("mac", "aa:vv:ss:ee:ff:bb");
        exampleData3.put("id", 10001);
        exampleData3.put("value", 100010000L);

        // 数组的基本操作
//        numberOperation(exampleData2);

        // reduce缩减操作
        reduceOperation(exampleData2);
    }

    /**
     * 缩减操作
     * @param exampleData2
     */
    private static void reduceOperation(List<Integer> exampleData2) {
        exampleData2.stream()
                .filter(Objects::nonNull)
                .reduce((i1, i2) -> i1+i2*2)
                .ifPresent(System.out::println);

        Optional.ofNullable(exampleData2.stream()
                .filter(Objects::nonNull)
                .reduce(2, (o1, o2) -> o1*o2)).ifPresent(System.out::println);

        // TODO: 还有第三种reduce的方法
        // 1 1 -> 1
        // 1 2 -> 2
        // 2 3 -> 6
        // 6 4 -> 24
        List<Integer> integerList = Arrays.asList(1,2,3,4, null);
        Optional.of(integerList
                .parallelStream()
                .unordered()
                .filter(Objects::nonNull)
                .reduce("a", (o1, o2) -> {
                    System.out.println("o1:" + o1 + ", o2:" + o2);
                    return o1 + o2;
                }, (x1, x2) -> {
                    System.out.println("x1:" + x1 + ", x2:" + x2);
                    return x1 + "-" + x2;
                })).ifPresent(System.out::println);

        Optional.of(integerList
                .stream()
                .filter(Objects::nonNull)
                .reduce("#", (o1, o2) -> {
                    return o1 + o2;
                }, (x1,x2)->null)).ifPresent(System.out::println);

        integerList.parallelStream()
                .map(integer -> {
                    System.out.println(Thread.currentThread().getName());
                    return String.valueOf(integer);
                }).count();
    }

    /**
     * 数字数组操作
     * @param exampleData2
     */
    private static void numberOperation(List<Integer> exampleData2) {
        // 除去空值并寻找最小值
        Optional<Integer> minInteger = exampleData2.stream().filter(Objects::nonNull).min(Integer::compareTo);
        minInteger.ifPresent(System.out::println);

        // 除去空值并寻找最大值
        Optional<Integer> maxInteger = exampleData2.stream().filter(Objects::nonNull).max(Integer::compareTo);
        maxInteger.ifPresent(System.out::println);

        // 过滤空值且<0的值,除去重复值
        exampleData2.stream().filter(Objects::nonNull).filter(i -> i>=0).distinct().forEach(System.out::println);

        // 排序
        exampleData2.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(o -> o))
                .forEach(e -> System.out.print(e +", "));

    }
}
