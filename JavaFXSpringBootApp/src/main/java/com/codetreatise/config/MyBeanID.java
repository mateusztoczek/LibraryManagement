package com.codetreatise.config;

import org.springframework.stereotype.Component;

@Component
public class MyBeanID {
    private long idBook;
    private long idCopy;
    private String roleUser;
    private long idLibrary;
    public long getIdBook() { return idBook; }
    public void setIdBook(long id) { this.idBook = id; }
    public long getIdCopy() { return idCopy; }
    public void setIdCopy(long id) { this.idCopy = id; }
    public long getIdLibrary() { return idLibrary; }
    public void setIdLibrary(long id) { this.idLibrary = id; }
    public String getRoleUSer() { return roleUser; }
    public void setRoleUser(String id) { this.roleUser = id; }
    
}
