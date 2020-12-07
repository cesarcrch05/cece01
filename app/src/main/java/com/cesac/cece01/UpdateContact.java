package com.cesac.cece01;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

public class UpdateContact extends AppCompatActivity {
    public static String EXTRA_CONTACT_ID = "contact_id";
    private TextView mCreatedTimeTextView;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneNumberEditText;
    private EditText mDireccionEditText;
    private EditText mCorreoEditText;
    private EditText mPaisEditText;
    private Button mUpdateButton;
    private Toolbar mToolbar;
    private Contacto CONTACT;
    private ContactDAO mContactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries() //Allows room to do operation on main thread
                .build()
                .getContactDAO();
        CONTACT = mContactDAO.getContactWithId(getIntent().getStringExtra(EXTRA_CONTACT_ID));
        mFirstNameEditText = findViewById(R.id.firstNameEditText);
        mLastNameEditText = findViewById(R.id.lastNameEditText);
        mPhoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        mDireccionEditText = findViewById(R.id.tvminuevadireccion);
        mCorreoEditText = findViewById(R.id.editTextTextEmailAddress);
        mPaisEditText = findViewById(R.id.editTextTextpais);
        mUpdateButton = findViewById(R.id.updateButton);
        mCreatedTimeTextView = findViewById(R.id.createdTimeTextView);
        mToolbar = findViewById(R.id.toolbar);
        initViews();
    }
    private void initViews() {
        setSupportActionBar(mToolbar);
        mFirstNameEditText.setText(CONTACT.getFirstName());
        mLastNameEditText.setText(CONTACT.getLastName());
        mPhoneNumberEditText.setText(CONTACT.getPhoneNumber());
        mDireccionEditText.setText(CONTACT.getDireccion());
        mCorreoEditText.setText(CONTACT.getCorreo());
        mPaisEditText.setText(CONTACT.getPais());
        mCreatedTimeTextView.setText(CONTACT.getCreatedDate().toString());
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();
                String phoneNumber = mPhoneNumberEditText.getText().toString();
                String direction = mDireccionEditText.getText().toString();
                String email = mCorreoEditText.getText().toString();
                String country = mPaisEditText.getText().toString();
                if (firstName.length() == 0 || lastName.length() == 0 || phoneNumber.length() == 0)  {
                    Toast.makeText(UpdateContact.this, "Porfavor verifica que llenaste todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                CONTACT.setFirstName(firstName);
                CONTACT.setLastName(lastName);
                CONTACT.setPhoneNumber(phoneNumber);
                CONTACT.setDireccion(direction);
                CONTACT.setCorreo(email);
                CONTACT.setPais(country);
//Actualizando la BD
                mContactDAO.update(CONTACT);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete: {
                mContactDAO.delete(CONTACT);
                setResult(RESULT_OK);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}