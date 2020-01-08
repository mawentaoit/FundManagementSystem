package controller;

public class Person {
    private int PersonID;
    private String  Name;
    private  String  Sex;
    private String  Dept;
    private  String Job;
    private  String  Other;
    public Person(int PersonID, String Name, String Sex, String Dept, String Job, String Other) {
        this.PersonID = PersonID;
        this.Name = Name;
        this.Sex = Sex;
        this.Dept = Dept;
        this.Job = Job;
        this.Other = Other;
    }

    public Person(String Name, String Sex, String Dept, String Job, String Other) {
        this.Name = Name;
        this.Sex = Sex;
        this.Dept = Dept;
        this.Job = Job;
        this.Other = Other;

    }

    public String getDept() {
        return Dept;
    }

    public String getJob() {
        return Job;
    }

    public String getName() {
        return Name;
    }

    public String getOther() {
        return Other;
    }

    public int getPersonID() {
        return PersonID;
    }

    public String getSex() {
        return Sex;
    }

    public void setDept(String Dept) {
        this.Dept = Dept;
    }

    public void setJob(String Job) {
        this.Job = Job;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setOther(String Other) {
        this.Other = Other;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.PersonID;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.PersonID != other.PersonID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "人员编号：" + PersonID + ", 姓名：" +Name + ", 性别：" + Sex+"部门：" + Dept+ ", 职位：" +Job + ",其他：" + Other;
    }
}
