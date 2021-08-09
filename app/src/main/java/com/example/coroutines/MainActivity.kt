package com.example.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainLooper = mainLooper // or Looper.getMainLooper()
        //o mainLooper vai comunicar para a main thread quando necessário. Agora criamos o Handler pra mandar o sinal para o main thread.

//        //Usando a Thread para não quebrar a main thread:
//        Thread{
//            val imageUrl = URL("https://cdn.theatlantic.com/thumbor/ISgRyw-VeYqYCE38o7HkVAiz90c=/900x626/media/img/photo/2020/02/photos-superb-owl-sunday-iv/s01_1103328920/original.jpg")
//            val connection = imageUrl.openConnection() as HttpURLConnection
//            connection.doInput = true  //doInput pois apenas está recebendo dado.
//            connection.connect()  // cria a conexão
//
//            val inputStream = connection.inputStream
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//            // transfere os dados do link para o app abrindo uma stream e decoding pra bitmap
//
//            Handler(mainLooper).post { image.setImageBitmap(bitmap) }
//            //agora pode display na tela:
//          //  runOnUiThread { image.setImageBitmap(bitmap) }
//        }.start()


        Log.d("TaskTherad", Thread.currentThread().name)
        //GlobalScope is the simplest and easiest to use CoroutineScope.
        //Precisa passar uma função Lambda no launch pra definir o que a coroutine vai executar quando for iniciada.
        //toda coroutine tem o coroutine contex, que é uma série de regras para a coroutine seguir.
        //o dispatcher é uma dessas regras e é usado para definir threads para as coroutines.
        GlobalScope.launch (context = Dispatchers.IO){
            Log.d("TaskTherad", Thread.currentThread().name)
            val imageUrl = URL("https://cdn.theatlantic.com/thumbor/ISgRyw-VeYqYCE38o7HkVAiz90c=/900x626/media/img/photo/2020/02/photos-superb-owl-sunday-iv/s01_1103328920/original.jpg")
            val connection = imageUrl.openConnection() as HttpURLConnection
            connection.doInput = true  //doInput pois apenas está recebendo dado.
            connection.connect()  // cria a conexão

            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            // transfere os dados do link para o app abrindo uma stream e decoding pra bitmap

          //  runOnUiThread {
            launch(Dispatchers.Main) {
                Log.d("TaskTherad", Thread.currentThread().name)
                image.setImageBitmap(bitmap) }
        }

     }
}