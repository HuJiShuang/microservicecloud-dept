package edu.bit.hjs.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

//使用lombok相关注解自动生成实体类中想要的方法
@AllArgsConstructor
@NoArgsConstructor
@Data   //setter和getter方法
@Accessors(chain = true)    //链式风格访问,dept.setDeptno(...).setDname(...).setDb_source(...)
public class Dept implements Serializable { // Dept (Entity) orm mysql->Dept(table) 类表关系映射

    private Long deptno;    //主键
    private String dname;   //部门名称
    private  String db_source;  //来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

}
