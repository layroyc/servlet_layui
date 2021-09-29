package bean;

public class Master {
    private int id;
    private String name;
    private String sex;
    private String age;
    private String account;
    private String password;
    private String did;
    private String phone;
    private String del;

    @Override
    public String toString() {
        return "Mater{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", did='" + did + '\'' +
                ", phone='" + phone + '\'' +
                ", del='" + del + '\'' +
                '}';
    }

    public Master() {
        super();
    }

    public Master(int id, String name, String sex, String age, String account, String password, String did, String phone, String del) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.account = account;
        this.password = password;
        this.did = did;
        this.phone = phone;
        this.del = del;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
