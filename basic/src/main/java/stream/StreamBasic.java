package stream;


import common.StaticProcessor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description 基础的stream用法
 * @author fins
 * @date 2020/12/18
 **/
public class StreamBasic extends StaticProcessor {

    public static void main(String[] args) {

        // 数组的基本操作
        StreamBasic.process(StreamBasic.class, "numberOperation", false);

        // reduce缩减操作
        StreamBasic.process(StreamBasic.class, "reduceOperation", false);

        // 验证foreach里边是不是引用
        StreamBasic.process(StreamBasic.class, "checkForeachIsReference", false);

        // flatMap的操作
        StreamBasic.process(StreamBasic.class, "flatMapOperation", false);

        // collects的操作
        StreamBasic.process(StreamBasic.class, "collectOperation", true);
    }

    /**
     * collect操作
     */
    public static void collectOperation() {
        List<Map<String, String>>  mapList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>(20);
        map1.put("a1", "1");
        map1.put("b1", "2");
        mapList.add(map1);
        Map<String, String> map2 = new HashMap<>(20);
        map1.put("a2", "1");
        map1.put("b2", "2");
        mapList.add(map2);

        Map<String,String> afterMap = mapList.stream()
                .collect(() -> new HashMap<>(), HashMap::putAll, (m3, m4) -> {});

    }

    /**
     * flatMap的操作
     */
    public static void flatMapOperation() {
        // 现在想把 List<Map<String, String>> 将所有map的key提取出来变成List<String>
        List<Map<String, String>>  mapList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>(20);
        map1.put("a1", "1");
        map1.put("b1", "2");
        mapList.add(map1);
        Map<String, String> map2 = new HashMap<>(20);
        map1.put("a2", "1");
        map1.put("b2", "2");
        mapList.add(map2);

        mapList.stream()
                .flatMap(map -> map.keySet().stream())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * 验证修改forEach中的数组元素会不会对源数组进行修改
     */
    public static void checkForeachIsReference() {

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("a"));
        cars.add(new Car("b"));

        cars.forEach(car -> {
            car.name = "111";
        });
        cars.forEach(car -> {
            System.out.println(car.name);
        });
        // 结果是再foreach里去修改对象是会影响到原数组的

    }

    /**
     * Car
     */
    static class Car {
        private  String name;
        public Car(String name) {
            this.name = name;
        }
    }

    /**
     * 缩减操作
     */
    public static void reduceOperation() {

        List<Integer> exampleData2 = Arrays.asList(56, 3, -67, -78, 3, 56, null, 45, 100);
        exampleData2.stream()
                .filter(Objects::nonNull)
                .reduce((i1, i2) -> i1+i2*2)
                .ifPresent(System.out::println);

        Optional.ofNullable(exampleData2.stream()
                .filter(Objects::nonNull)
                .reduce(2, (o1, o2) -> o1*o2)).ifPresent(System.out::println);

        // 还有第三种reduce的方法, 只在并行流的情况下会使用combine的lambda函数
        // 1 1 -> 1
        // 1 2 -> 2
        // 2 3 -> 6
        // 6 4 -> 24
        // TODO: 为什么并行流最后不会影响到输出的结果
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
     */
    public static void numberOperation() {
        List<Integer> exampleData2 = Arrays.asList(56, 3, -67, -78, 3, 56, null, 45, 100);
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
