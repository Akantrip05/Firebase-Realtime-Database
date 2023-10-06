package com.udemy.firestorerealtime

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.udemy.firestorerealtime.R.*


class InsertActivity : AppCompatActivity() {


    private lateinit var edtname:EditText
    private lateinit var edtage:EditText
    private lateinit var edtsalary:EditText
    private lateinit var btnsave:Button
    private lateinit var dbRef :DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_insert)

    edtname = findViewById(R.id.etname)
    edtage = findViewById(R.id.etdage)
    edtsalary = findViewById(R.id.etdSalary)
        btnsave = findViewById(R.id.btnSave)
    dbRef = FirebaseDatabase.getInstance().getReference("Employee")
btnsave.setOnClickListener(){
    saveRecord()
}

    }

    @SuppressLint("SuspiciousIndentation")
    private fun saveRecord() {
      val empName = edtname.text.toString()
      val empAge = edtage.text.toString()
      val empSalary = edtsalary.text.toString()

        if (empName.isEmpty()){
            edtname.error = "Please enter your name"
        }
        if (empAge.isEmpty()){
            edtage.error = "Please enter your age"
        }
        if (empSalary.isEmpty()){
            edtsalary.error = "Please enter your Salary"
        }
        val empId = dbRef.push().key!!
        val employeeModel = EmployeeModel(empId,empName,empAge,empSalary)


        dbRef.child(empId).setValue(employeeModel)
            .addOnCompleteListener {
                Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show()
                edtname.text.clear()
                edtage.text.clear()
                edtsalary.text.clear()

            }.addOnFailureListener { err->
                Toast.makeText(this,"${err.message}",Toast.LENGTH_SHORT).show()
            }

    }
}