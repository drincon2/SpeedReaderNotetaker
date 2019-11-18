package edu.utep.cs.cs4330.speedreadernotetaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

//Main page
public class MainActivity extends AppCompatActivity {

    ListView listViewPdf;
    public static ArrayList<File> fileArrayList = new ArrayList<>();
    PDFAdapter adapter;
    public static int REQUEST_PERMISSION = 1;
    boolean permission;
    File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPdf = findViewById(R.id.fileList);
        file = new File(Environment.getExternalStorageDirectory().toString());
        permission_filename();
    }


    private void permission_filename() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            }

            else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            }
        }

        else {
            permission = true;
            getfile(file);
            adapter = new PDFAdapter(getApplicationContext(), fileArrayList);
            listViewPdf.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSION) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                permission = true;
                getfile(file);
                adapter = new PDFAdapter(getApplicationContext(), fileArrayList);
                listViewPdf.setAdapter(adapter);
            }

            else {
                Toast.makeText(this, "Please Allow The Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<File> getfile (File file) {
        File listFile[] = file.listFiles();
        if(listFile != null && listFile.length > 0) {
            for (int i  = 0; i < listFile.length; i++) {
                if(listFile[i].isDirectory()) {
                    getfile(listFile[i]);
                }
                else {
                    boolean booleanPdf = false;
                    if(listFile[i].getName().endsWith(".pdf")) {
                        for (int j = 0; j < fileArrayList.size(); j++) {
                            if(fileArrayList.get(j).getName().equals(listFile[i].getName())) {
                                booleanPdf = true;
                            }
                            else {

                            }
                        }

                        if(booleanPdf) {
                            booleanPdf = false;
                        }
                        else {
                            fileArrayList.add(listFile[i]);
                        }
                    }
                }
            }
        }
        return fileArrayList;
    }
}
