package com.nodomain.savewords.activities;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nodomain.savewords.R;
import com.nodomain.savewords.tasks.AddWordTask;
import com.nodomain.savewords.tasks.AddWordTaskListener;
import com.nodomain.savewords.tasks.TranslateWordTask;
import com.nodomain.savewords.tasks.TranslateWordTaskListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddWordActivity extends AppCompatActivity
        implements TranslateWordTaskListener, AddWordTaskListener {

    private ImageView imageViewPicture;
    private EditText editTextWord;

    private ProgressDialog progressDialogTranslation;
    private ProgressDialog progressDialogSaving;

    private TranslateWordTask translateWordTask;
    private AddWordTask addWordTask;

    private Uri imageUri;
    private byte[] image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        initViews();
        setListeners();

        translateWordTask = new TranslateWordTask(new Handler());
        addWordTask = new AddWordTask(new Handler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            imageUri = data.getData();
            imageViewPicture.setImageURI(imageUri);
            image = convertImageToByte(data.getData());
        }
    }

    @Override
    public void wordTranslated(String word) {
        hideTranslationProgress();
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Добавить в словарь: " + word + " - " + editTextWord.getText().toString())
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addWordTask.addWord(AddWordActivity.this, AddWordActivity.this, editTextWord.getText().toString(), image);
                        showSavingProgress();
                    }
                }).show();
    }

    @Override
    public void wordTranslateFail() {
        hideSavingProgress();
        Toast.makeText(this, "Ошибка соединения", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wordAdded() {
        hideSavingProgress();
        Toast.makeText(this, "Слово добавлено в словарь", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void wordAdditionFail() {
        hideSavingProgress();
        Toast.makeText(this, "Ошибка при сохранении слова", Toast.LENGTH_LONG).show();
    }

    private void initViews() {
        editTextWord = (EditText) findViewById(R.id.editText_word);
        imageViewPicture = (ImageView) findViewById(R.id.imageView_picture);
    }

    private void setListeners() {
        imageViewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectPictureClick();
            }
        });

        editTextWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String result = editable.toString().replaceAll(" ", "");
                if (!editable.toString().equals(result)) {
                    editTextWord.setText(result);
                    editTextWord.setSelection(result.length());
                }
            }
        });

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddWordClick();
            }
        });

        findViewById(R.id.imageView_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick();
            }
        });
    }

    private void onSelectPictureClick() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    private void onAddWordClick() {
        if (imageUri == null) {
            Toast.makeText(AddWordActivity.this, "Нужно выбрать изображение", Toast.LENGTH_LONG).show();
        } else if (editTextWord.getText().toString().length() == 0) {
            Toast.makeText(AddWordActivity.this, "Нужно ввести слово", Toast.LENGTH_LONG).show();
        } else {
            showTranslationProgress();
            translateWordTask.translate(AddWordActivity.this, editTextWord.getText().toString());
        }
    }

    private void onBackClick() {
        finish();
    }

    private void showTranslationProgress() {
        progressDialogTranslation = new ProgressDialog(this);
        progressDialogTranslation.setMessage("Получение перевода");
        progressDialogTranslation.setCancelable(false);
        progressDialogTranslation.show();
    }

    private void hideTranslationProgress() {
        progressDialogTranslation.hide();
    }

    private void showSavingProgress() {
        progressDialogSaving = new ProgressDialog(this);
        progressDialogSaving.setMessage("Сохранение");
        progressDialogSaving.setCancelable(false);
        progressDialogSaving.show();
    }

    private void hideSavingProgress() {
        progressDialogSaving.hide();
    }

    public byte[] convertImageToByte(Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
