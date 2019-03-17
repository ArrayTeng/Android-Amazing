package com.example.prototype;


/**
 * 简历实体类
 *
 * @author tengfei
 */
public class ResumeEntity implements Cloneable{

    public String name;
    public String age;
    public ResumeAddress resumeAddress;

    @Override
    public String toString() {
        return "ResumeEntity{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", resumeAddress=" + resumeAddress.address +
                '}';
    }

    @Override
    protected ResumeEntity clone() throws CloneNotSupportedException {
        ResumeEntity resumeEntity = (ResumeEntity) super.clone();
        resumeEntity.resumeAddress = (ResumeAddress) resumeAddress.clone();
        return resumeEntity;
    }
}
