package com.cesac.cece01;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateContact extends AppCompatActivity {

    @BindView(R.id.firstNameEditText)
    EditText firstNameEditText;
    @BindView(R.id.lastNameEditText)
    EditText lastNameEditText;
    @BindView(R.id.phoneNumberEditText)
    EditText phoneNumberEditText;
    @BindView(R.id.tvminuevadireccion)
    EditText tvminuevadireccion;
    @BindView(R.id.editTextTextEmailAddress)
    EditText editTextTextEmailAddress;
    @BindView(R.id.editTextTextpais)
    EditText editTextTextpais;
    private ContactDAO mContactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        ButterKnife.bind(this);
        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries() //Allows room to do operation on main thread
                .build()
                .getContactDAO();

    }

    @OnClick(R.id.saveButton)
    public void onViewClicked() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String direccion = tvminuevadireccion.getText().toString();
        String correo = editTextTextEmailAddress.getText().toString();
        String pais = editTextTextpais.getText().toString();


        if (firstName.length() == 0 || lastName.length() == 0 || phoneNumber.length() == 0) {
            Toast.makeText(CreateContact.this, "Porfavor verifica que llenaste todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        Contacto contact = new Contacto();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        contact.setDireccion(direccion);
        contact.setCorreo(correo);
        contact.setPais(pais);
        contact.setCreatedDate(new Date());
//Insertando a la base de datos
        try {
            mContactDAO.insert(contact);
            setResult(RESULT_OK);
            finish();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(CreateContact.this, "Un contacto con el mismo número actualmente existe.", Toast.LENGTH_SHORT).show();
        }
    }
}