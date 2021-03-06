package myapp.example.com.myproject;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by zhaoxu2014 on 16/8/19.
 */
public class Person extends SugarRecord {
    String id;
    int age;
    String name;

    List<Student> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }
}
