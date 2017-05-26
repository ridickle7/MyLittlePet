package yapp.co.kr.mlp.Fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import yapp.co.kr.mlp.Helper.ImageHelper;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-10-29.
 */
public class BoastAddFragment extends DialogFragment {
    final int REQ_CODE_SELECT_IMAGE = 100;
    LinearLayout cancleButton, OkButton;
    ImageButton boast_image;
    EditText boast_comment;
    String boast_title, img_path, strDate;
    Bitmap image_bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_boast, container, false);
        cancleButton = (LinearLayout) v.findViewById(R.id.dialog_layout_button_cancel);
        OkButton = (LinearLayout) v.findViewById(R.id.dialog_layout_button_ok);
        boast_image = (ImageButton) v.findViewById(R.id.boast_image);
        boast_comment = (EditText) v.findViewById(R.id.boast_comment);


        //취소버튼
        cancleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //확인 버튼
        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                strDate = sim.format(date).toString();
                new MyAsyncTask().execute(img_path);
            }
        });

        //사진 업로드
        boast_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 처리로직
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this.getActivity(), "resultCode : " + resultCode, Toast.LENGTH_SHORT).show();
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    img_path = getPath(data.getData());
                    Log.d("temp", img_path + "");

                    //이미지 데이터를 비트맵으로 받아온다.
                    ImageHelper help = new ImageHelper();
                    //image.setImageBitmap(help.decodeSampledBitmapFromResource(this.getResources(), R.id.image, 100, 100));
                    //option.inSampleSize = 2;
                    //image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    AssetFileDescriptor afd = getActivity().getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 4;
                    image_bitmap = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor(), null, opt);
                    image_bitmap = Bitmap.createScaledBitmap(image_bitmap, 640, 480, false);

                    //배치해놓은 ImageButton에 set
                    boast_image.setImageBitmap(image_bitmap);
                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Uri에서 파일 패스를 추출하는 로직
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        getActivity().startManagingCursor(cursor);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }

    public static BoastAddFragment newInstance() {
        BoastAddFragment fragment = new BoastAddFragment();
        return fragment;
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            multipart_ex(params[0]);
            return null;
        }
        protected void onPostExecute(Double result){
            Toast.makeText(getActivity(), "command sent", Toast.LENGTH_LONG).show();
            dismiss();
        }
        protected void onProgressUpdate(Integer... progress){
        }

        public void multipart_ex(String imagePath) {
            File imageFile = new File(imagePath);
            Charset chars = Charset.forName("UTF-8");
            cz.msebera.android.httpclient.client.HttpClient httpClient = new cz.msebera.android.httpclient.impl.client.DefaultHttpClient();
            cz.msebera.android.httpclient.client.methods.HttpPost httpPost = new cz.msebera.android.httpclient.client.methods.HttpPost("http://52.32.69.220:3000/secondshow");
            try {
                httpPost.setHeader("Accept-Charset", "UTF-8");
                cz.msebera.android.httpclient.HttpEntity entity = MultipartEntityBuilder
                        .create()
                        .addTextBody("sid","1")
                        .addTextBody("userid", "23589085092")       //userId는 페북, 네이버 연동 시 얻는 고유 아이디
                        .addBinaryBody("files", imageFile, ContentType.create("application/octet-stream"), imageFile.getName())
                        .addTextBody("date", strDate, ContentType.create("Multipart/related", "UTF-8"))
                                //.addTextBody("uphone", "c")
                        //.addTextBody("content", boast_comment.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                        .build();

                httpPost.setEntity(entity);
                cz.msebera.android.httpclient.HttpResponse response = httpClient.execute(httpPost);
                cz.msebera.android.httpclient.HttpEntity result = response.getEntity();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}