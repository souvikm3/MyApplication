package com.example.myapplication.Login

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAddRealtimeBinding
import com.example.myapplication.databinding.ActivityUploadStorageBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class UploadStorageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadStorageBinding
    var fileUri: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUploadStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idBtnChooseImage.setOnClickListener(){
            val intent = Intent()
            intent.type="image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent,"Pick your image to upload"),101
            )
        }

        binding.idBtnUploadImage.setOnClickListener(){
            uploadImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==101 && resultCode== RESULT_OK && data!=null && data.data!=null){
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,fileUri)
                binding.idIVImage.setImageBitmap(bitmap)
            }catch (e:Exception){

            }

        }
    }

    fun uploadImage(){
        if (fileUri!=null){

            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading....")
            progressDialog.show()
            val ref: StorageReference = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID().toString())
            ref.putFile(fileUri!!).addOnSuccessListener {
                Toast.makeText(this,"Upload successfully", Toast.LENGTH_LONG).show()

                progressDialog.dismiss()
            }.addOnFailureListener{
                Toast.makeText(this,"Upload failed", Toast.LENGTH_LONG).show()

                progressDialog.dismiss()

            }
        }
    }
}