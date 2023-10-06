package com.udemy.firestorerealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class EmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var idText :TextView
    private lateinit var nameText :TextView
    private lateinit var ageText :TextView
    private lateinit var salaryText :TextView
    private lateinit var btnUpdate :Button
    private lateinit var btnDelete :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        initView()
        setValuesOnView()
        openUpdateDialog(
            intent.getStringExtra("empId").toString(),
            intent.getStringExtra("empName").toString()

        )


    }



    private fun initView() {
        idText = findViewById(R.id.tvEmpId)
        nameText = findViewById(R.id.tvEmpName)
        ageText = findViewById(R.id.tvEmpAge)
        salaryText = findViewById(R.id.tvEmpSalary)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesOnView()
    {
        idText.text = intent.getStringExtra("empId")
        nameText.text = intent.getStringExtra("empName")
        ageText.text = intent.getStringExtra("empAge")
        salaryText.text = intent.getStringExtra("empSalary")
    }
    private fun openUpdateDialog(empId: String, empName:String) {
       val myDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val myDiaView = inflater.inflate(R.layout.update_dialog,null)
        myDialog.setView(myDiaView)

        val emName = myDiaView.findViewById<EditText>(R.id.etEmpName)
        val emAge = myDiaView.findViewById<EditText>(R.id.etEmpAge)
        val emSalary = myDiaView.findViewById<EditText>(R.id.etEmpSalary)
        val btnUpdateData = myDiaView.findViewById<Button>(R.id.btnUpdateData)

        emName.setText(intent.getStringExtra("empName").toString())
        emAge.setText(intent.getStringExtra("empAge").toString())
        emSalary.setText(intent.getStringExtra("empSalary").toString())
       myDialog.setTitle("Updating $emName Record")

        val alertDialog = myDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener(){
            upEmpData(
                empId,
                emName.text.toString(),
                emAge.text.toString(),
                emSalary.text.toString())

                Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_LONG).show()
                nameText.text = emName.text.toString()
                ageText.text = emAge.text.toString()
                salaryText.text = emSalary.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun upEmpData(
        id :String,
        name: String,
        age:String,
        salary:String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Employee").child(id)
        val empInfo = EmployeeModel(id,name,age,salary)
        dbRef.setValue(empInfo)

    }


}