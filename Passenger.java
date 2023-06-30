class Passengers {

    String name;
    String lastName;
    String nationalId;
    String sex;
    Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return this.sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return this.birthday;
    }
}