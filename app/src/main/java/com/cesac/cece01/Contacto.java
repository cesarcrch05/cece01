package com.cesac.cece01;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "contact")
public class Contacto {
    @PrimaryKey(autoGenerate = true)
    private int idcontacto;
    private String firstName;
    private String lastName;

    @NonNull
    private String phoneNumber;
    private Date createdDate;;
    private String direccion;
    private String correo;
    private String pais;



    public int getIdcontacto() {
        return idcontacto;
    }

    public void setIdcontacto(int idcontacto) {
        this.idcontacto = idcontacto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

/*    public Contacto(String firstName, String lastName, @NonNull String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }*/
}