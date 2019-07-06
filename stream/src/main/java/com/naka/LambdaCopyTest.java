package com.naka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 从一个集合选取某些属性复制到另一个集合
 *
 * @author: susie
 * @create: 2019/5/15 下午3:23
 **/
public class LambdaCopyTest {


    @Test
    public void testCopyList() {

        List<Girl> girls = new ArrayList<>();
        girls.add(Girl.builder().girlName("小花").build());
        girls.add(Girl.builder().girlName("小红").build());
//		List<Boy> boys = new ArrayList<Boy>();
//		boys.add(Boy.builder().name("张三").build());
//		boys.add(Boy.builder().name("李四").build());

        List<Boy> boys = girls.stream().map(girl -> {
            return Boy.builder().name(girl.getGirlName()).cardNoTo(girl.getCardNoTo()).build();
        }).collect(Collectors.toList());
        System.out.println(boys.toString());
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Girl implements Serializable {


        private static final long serialVersionUID = -7057305734154290491L;
        private String girlName;
        private String cardNoTo;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Boy implements Serializable {


        private static final long serialVersionUID = 5031724421241948730L;
        private String name;
        private String cardNoTo;
    }


}
