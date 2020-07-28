package sahan.abr.entities;

public class Parent {

    private Integer id;
    private String surname;
    private String name;
    private String middleName;
    private String passport;
    private String contractNumber;
    private String contractDate;
    private String phoneNumber;
    private String contactPhoneNumber;
    private String email;
    private String vk;
    private boolean notCall;
    private boolean notEmail;
    private boolean notVK;
    private String childrenId;

    public Parent() {
    }

    public Parent(Integer id, String surname, String name, String middleName,
                  String passport, String contractNumber, String contractDate,
                  String phoneNumber, String contactPhoneNumber, String email,
                  String vk, boolean notCall, boolean notEmail, boolean notVK,
                  String childrenId) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.passport = passport;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.phoneNumber = phoneNumber;
        this.contactPhoneNumber = contactPhoneNumber;
        this.email = email;
        this.vk = vk;
        this.notCall = notCall;
        this.notEmail = notEmail;
        this.notVK = notVK;
        this.childrenId = childrenId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public boolean isNotCall() {
        return notCall;
    }

    public void setNotCall(boolean notCall) {
        this.notCall = notCall;
    }

    public boolean isNotEmail() {
        return notEmail;
    }

    public void setNotEmail(boolean notEmail) {
        this.notEmail = notEmail;
    }

    public boolean isNotVK() {
        return notVK;
    }

    public void setNotVK(boolean notVK) {
        this.notVK = notVK;
    }

    public String getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(String childrenId) {
        this.childrenId = childrenId;
    }
}

