package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    // recuperar objetos que permitem manipular o banco de dados, autenticação
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    private Button btnUpload;
    private ImageView imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpload = findViewById(R.id.btnUpload);
        imageview = findViewById(R.id.imageview);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Configura para imagem ser salva em memória
                imageview.setDrawingCacheEnabled(true);
                imageview.buildDrawingCache();

                //Recupera bitmap da imagem (image a ser carregada)
                Bitmap bitmap = imageview.getDrawingCache();

                //Comprimo bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                //converte o baos para pixel brutos em uma matriz de bytes
                // (dados da imagem)
                byte[] dadosImagem = baos.toByteArray();

                //Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("celular.jpeg");

                Glide.with(MainActivity.this).using(new FirebaseImageLoader()).load( imagemRef ).into( imageview );

                /*
                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Erro ao deletar", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Sucesso ao deletar", Toast.LENGTH_SHORT).show();
                    }
                });

                Nome da imagem*/
                String nomeArquivo = UUID.randomUUID().toString();
                //StorageReference imagemRef = imagens.child(nomeArquivo + ".jpeg");

                //Retorna objeto que irá controlar o upload
                UploadTask uploadTask = imagemRef.putBytes( dadosImagem );

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Upload da imagem falhou: " + e.getMessage().toString(), Toast.LENGTH_SHORT);
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Upload da imagem foi um sucesso: ", Toast.LENGTH_SHORT);
                            }
                        });

                    }
                });

            }
        });

        /*
        DatabaseReference usuarios = referencia.child("usuarios");

        //FILTROS
        //DatabaseReference usuarioPesquisa = usuarios.child(""); // <- id
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Caio");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);

        //Maior ou igual (>=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(35);

        //Menor ou igual (<=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(22);

        //Entre dois valores
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(18).endAt(30);

        //Filtrar palavras
        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("J").endAt("J" + "\uf8ff");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario dadosUsuario = dataSnapshot.getValue(Usuario.class);
                Log.i("Dados usuario: ", "nome: " + dadosUsuario.getNome() + "idade: " + dadosUsuario.getIdade());
                Log.i("Dados usuario: ", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*

        Usuario usuario = new Usuario();
        usuario.setNome("Caio");
        usuario.setSobrenome("Detore");
        usuario.setIdade(19);

        //cria um identificador unico para cada usuario
        usuarios.push().setValue( usuario );

        /* Deslogar usuario
        usuario.signOut();

        // Logar usuario
        usuario.signInWithEmailAndPassword("caiodetore@gmail.com", "ca12345")
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.i("SignInUser", "Sucesso ao logar usuario.");
                            } else {
                                Log.i("SignInUser", "Falha ao logar usuario.");
                            }
                        }
                    });

        /* Verifica usuario logado
        if (usuario.getCurrentUser() != null) {
            Log.i("CurrentUser", "Usuario logado.");
        } else {
            Log.i("CurrentUser", "Usuario não logado.");
        }


        /* Cadastro de usuario
        usuario.createUserWithEmailAndPassword("caiodetore@gmail.com", "ca12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao cadastrar o usuario.");
                        } else {
                            Log.i("CreateUser", "Falha ao cadastrar o usuario.");
                        }
                    }
                });

        /*
        .child cria um nó na db caso não exista
        .setvalue 'dá' um valor para o nó

        referencia.child( "pontos" ).child("001").setValue("100");

        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })


        /* adicionando valores utilizando as classes
        Usuario usuario = new Usuario();
        usuario.setNome("Caio");
        usuario.setSobrenome("Detore");
        usuario.setIdade(30);

        usuarios.child("001").setValue( usuario );

        Produto produto = new Produto();
        produto.setDescricao("Nexus");
        produto.setMarca("LG");
        produto.setPreco(899.99);

        produtos.child("001").setValue( produto );
        */

    }
}