package com.example.pdfreader;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ParcelFileDescriptor parcelFileDescriptor;
    private android.graphics.pdf.PdfRenderer pdfRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      
        try {
            File file = copyAssetAndGetFile("Unit 8.pdf");
            parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            pdfRenderer = new android.graphics.pdf.PdfRenderer(parcelFileDescriptor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        PdfFragment fragment = PdfFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

  
    public android.graphics.Bitmap renderPageToBitmap(int index, int width, int height) {
        if (pdfRenderer == null) return null;
        if (index < 0 || index >= pdfRenderer.getPageCount()) return null;

        android.graphics.pdf.PdfRenderer.Page page = pdfRenderer.openPage(index);

        
        float pageWidth = page.getWidth();
        float pageHeight = page.getHeight();
        float scale = Math.min((float)width / pageWidth, (float)height / pageHeight);
        int bmpW = Math.max(1, (int)(pageWidth * scale));
        int bmpH = Math.max(1, (int)(pageHeight * scale));

        android.graphics.Bitmap bitmap = android.graphics.Bitmap.createBitmap(bmpW, bmpH, android.graphics.Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(android.graphics.Color.WHITE);
        page.render(bitmap, null, null, android.graphics.pdf.PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();
        return bitmap;
    }

    public int getPageCount() {
        return pdfRenderer == null ? 0 : pdfRenderer.getPageCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (pdfRenderer != null) pdfRenderer.close();
            if (parcelFileDescriptor != null) parcelFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    private File copyAssetAndGetFile(String assetName) throws IOException {
        File outFile = new File(getCacheDir(), assetName);
        if (outFile.exists()) return outFile;

        InputStream is = getAssets().open(assetName);
        FileOutputStream fos = new FileOutputStream(outFile);
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        is.close();
        return outFile;
    }
}
