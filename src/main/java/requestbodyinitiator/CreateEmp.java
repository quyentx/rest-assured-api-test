package requestbodyinitiator;

public class CreateEmp {

    private String salary;
    private String age;
    private String name;

    public String getName() {
        return name;
    }

    public CreateEmp setName(String name) {
        this.name = name;
        return this;
    }

    public String getSalary() {
        return salary;
    }

    public CreateEmp setSalary(String salary) {
        this.salary = salary;
        return this;
    }

    public String getAge() {
        return age;
    }

    public CreateEmp setAge(String age) {
        this.age = age;
        return this;
    }
}
