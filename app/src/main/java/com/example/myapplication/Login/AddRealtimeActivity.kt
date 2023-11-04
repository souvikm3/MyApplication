package com.example.myapplication.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAddRealtimeBinding
import com.example.myapplication.databinding.ActivityShowRealtimeBinding
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.databinding.ActivitySigninBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.values

class AddRealtimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRealtimeBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddRealtimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference= FirebaseDatabase.getInstance().getReference("students")
        binding.addButton.setOnClickListener(){
            val name = binding.edtName.text.toString()
            val className = binding.edtClass.text.toString()
            val  rollNo= binding.edtRollNo.text.toString().toInt()

            val student=Student(name,className,rollNo)
            val studentId=databaseReference.push().key
            studentId?.let {
                databaseReference.child(studentId).setValue(student)
                    .addOnCompleteListener(){
                        if(it.isSuccessful){
                            Toast.makeText(this,"Student added Successfully", Toast.LENGTH_LONG).show()
                            val intent= Intent(this,ShowRealtimeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Error: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}