package www.tangan.com.bean;

/**
 * @author 谭淦
 */
public class User {
    private Integer userId;
    private String account;

    public Integer getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    private String password;
    private String name;
    private String sex;
    private String phone;
    private String school;
    private int position;
    private String sign;

    public User() {
    }

    public User(String account, String password, String name, String sex, String phone, String school, String sign) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.school = school;
        this.sign = sign;
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

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;

    }

    public String getSchool() {

        return school;
    }

    public void setSchool(String school) {

        this.school = school;
    }

    public int getPosition() {

        return position;
    }

    public void setPosition(int position) {

        this.position = position;
    }

    public String getSign() {

        return sign;
    }

    public void setSign(String sign) {

        this.sign = sign;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", school='" + school + '\'' +
                ", position=" + position +
                ", sign='" + sign + '\'' +
                '}';
    }
}
