import java.util.Objects;

public class Crush {
    private String id;
    private String name;
    private String description;
    private Double age;

    public Crush(String id, String name, String description, Double age) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.age = age;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Crush() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Crush)) return false;
        Crush product = (Crush) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(age, product.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, age);
    }

    @Override
    public String toString() {
        return "Crush{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                '}';
    }

}
