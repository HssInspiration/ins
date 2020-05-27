package com.hss.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ins
 * @Package: com.hss.po
 * @ClassName: PersonPo
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/6/2 13:13
 * @Update_By:
 * @Update_Date: 2019/6/2 13:13
 * U.H. All Rights Reserved.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonPo implements Cloneable {
    private String name;
    private int    age;

    public PersonPo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        PersonPo p = new PersonPo(23, "zhangsSan");
        PersonPo p1 = null;
        try {
            p1 = (PersonPo) p.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(p);
        System.out.println(p1);
    }
}
