package com.example.myapplication.Login

//import android.app.Activity
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.myapplication.R
//import com.example.myapplication.databinding.activity_signin.xml
//import com.example.myapplication.databinding.ActivitySigninBinding
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvRegister.setOnClickListener(){
            val intent =Intent(this,SignUpActivity :: class.java)
            startActivity(intent)
        }
        binding.SignIn.setOnClickListener(){
            val email=binding.edtEmail.text.toString()
            val pass=binding.edtPassword.text.toString()
            if(email.isNotEmpty()&& pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent =Intent(this,HomeAuthActivity :: class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this,"Empty Fields is not Allowed",Toast.LENGTH_LONG).show()
            }

        }

    }
    override fun onStart(){
        super.onStart()
        if(firebaseAuth.currentUser !=null){
            val intent =Intent(this,HomeAuthActivity :: class.java)
            startActivity(intent)
        }
    }
}