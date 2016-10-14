package prv.mark.test.processtextfile.domain;

/**
 * The Person value object that corresponds to the records in persons.txt
 *
 * Created by mlglenn on 10/14/2016.
 */
public class Person {

    private String name;
    private Integer age;
    private String city;
    private String state;
    private String flags;


    public Person() {}

    public Person(String name, Integer age, String city, String state, String flags) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.state = state;
        this.flags = flags;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", flags='" + flags + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }
}
