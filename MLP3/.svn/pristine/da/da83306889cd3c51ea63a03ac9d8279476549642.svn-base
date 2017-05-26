package yapp.co.kr.mlp.component;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import yapp.co.kr.mlp.Fragment.LocationFragment;
import yapp.co.kr.mlp.Helper.GeoHelper;
import yapp.co.kr.mlp.Helper.ImageHelper;
import yapp.co.kr.mlp.Helper.MySingleton;
import yapp.co.kr.mlp.Item_item.Qna;
import yapp.co.kr.mlp.R;

public class EditActivity extends ActionBarActivity {
    //발리 테스트용
    ArrayList<Qna> abList;
    int item_num = 0;
    Qna ne1;
    RequestQueue mQueue2;
    File imageFile;

    String img_path, user_sex = "", pet_sex = "", lon = "", lat = "";
    ImageButton ok, cancel;
    ImageView image;
    Bitmap image_bitmap;
    RadioGroup pet_gender, user_gender;
    EditText pet_name, pet_age, pet_specific, pet_introduction_write, user_nickname, user_age;
    Button pet_birthday, user_location;
    final int REQ_CODE_SELECT_IMAGE = 100;
    Context ctx = this;
    ArrayList<String> lon_lat = new ArrayList<String>();

    int version_code = 1;
    int member_flag = 1;

    String findAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //like();
        like_post();
        init();
        listener();
    }

    //findViewById 모아둠
    public void init() {
        ok = (ImageButton) findViewById(R.id.ok);
        cancel = (ImageButton) findViewById(R.id.cancel);

        image = (ImageButton) findViewById(R.id.image);
        pet_name = (EditText) findViewById(R.id.pet_name);
        pet_gender = (RadioGroup) findViewById(R.id.pet_gender);
        pet_birthday = (Button) findViewById(R.id.pet_birthday);
        pet_age = (EditText) findViewById(R.id.pet_age);
        pet_specific = (EditText) findViewById(R.id.pet_specific);
        pet_introduction_write = (EditText) findViewById(R.id.pet_introduction_write);

        user_nickname = (EditText) findViewById(R.id.user_nickname);
        user_gender = (RadioGroup) findViewById(R.id.user_gender);
        user_age = (EditText) findViewById(R.id.user_age);
        user_location = (Button) findViewById(R.id.user_location);
    }

    //클릭 리스너 모아둠
    public void listener() {
        //등록 누를 시
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findAddress = user_location.getText().toString();
                //고정 주소값을 통해 위도 경도 뽑아내기
                new geoPointTask().execute(findAddress);

                //만약 첫 가입일시 -> 메인화면으로 넘어감 (처음 시작한 사람)   -   토스트로 띄워주기
                if (member_flag == 0) {
                    new MyAsyncTask().execute(img_path);
                }
                //이전에 가입한 사람일 시 -> 이것도 그냥 메인화면으로 넘어감    -   토스트로 띄워주기
                else {
                    new MyAsyncTask().execute(img_path);
                }
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                in.putExtra("name", user_nickname.getText().toString());

                //Convert to byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image_bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                in.putExtra("profile", byteArray);
                startActivity(in);
                finish();
            }
        });

        //취소 누를 시
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //사진 업로드
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 처리로직
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        //라디오그룹 (유저 성별)
        user_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.user_male:
                        user_sex = "남자";
                        break;

                    case R.id.user_female:
                        user_sex = "여자";
                        break;

                    default:
                        break;
                }
            }
        });

        //라디오그룹 (유저 성별)
        pet_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pet_male:
                        pet_sex = "남자";
                        break;

                    case R.id.pet_female:
                        pet_sex = "여자";
                        break;

                    default:
                        break;
                }
            }
        });

        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), year + "년" + (monthOfYear + 1) + "월" + dayOfMonth + "일", Toast.LENGTH_SHORT).show();
                pet_birthday.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        };

        //생일 리스너
        pet_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today = new Date();
                int year = today.getYear() + 1900;
                int month = today.getMonth();
                int date = today.getDate();
                Log.d("년도", year + " " + month + " " + date + "");
                DatePickerDialog dialog = new DatePickerDialog(ctx, listener, year, month, date);

                dialog.show();
            }
        });

        user_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationFragment dialogFragment = LocationFragment.newInstance();
                dialogFragment.setStyle(R.style.Theme_Dialog_Transparent, R.style.Theme_Dialog_Transparent);
                dialogFragment.show(getFragmentManager(), "TAG");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getBaseContext(), "resultCode : " + resultCode, Toast.LENGTH_SHORT).show();
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

                    AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 4;
                    image_bitmap = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor(), null, opt);
                    image_bitmap = Bitmap.createScaledBitmap(image_bitmap, 640, 480, false);

                    //배치해놓은 ImageButton에 set
                    image.setImageBitmap(image_bitmap);
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
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }

/*    //Uri 에서 파일명을 추출하는 로직
    public String getImageNameToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        return imgName;
    }*/


    public void like() {
        // 서버에 뉴스피드 정보 요청
        final String URL_address = "http://52.32.69.220:3000/qnapage";
        final String imgURL_address = "http://imgnews.naver.com/image/092/2015/04/30/1JoQeLJIa9nBJ2F5n5Bu_99_20150430111706.jpg";
        //mQueue2 = Volley.newRequestQueue(this);       //싱글턴 사용 안할 시
        mQueue2 = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        // Request a string response from the provided URL.
        // 2) Request Obejct인 StringRequest 생성
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response is: ", response.toString());
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Log.d("error", error.toString());
                }
                // 네트워크 연결이 모두 끊어진 경우
                else if (error instanceof NoConnectionError) {
                    Log.d("error", error.toString());
                } else if (error instanceof AuthFailureError) {
                    Log.d("error", error.toString());
                }
                // 서버에러, URL에 해당 자료가 없어도 이곳이 불린다.
                else if (error instanceof ServerError) {
                    Log.d("error", error.toString());
                } else if (error instanceof NetworkError) {
                    Log.d("error", error.toString());
                } else if (error instanceof ParseError) {
                    Log.d("error", error.toString());
                }
            }
        });


        Response.Listener<Bitmap> image_listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap arg0) {
                // TODO Auto-generated method stub
                image.setImageBitmap(arg0);
            }
        };
        Response.ErrorListener image_err_listener = new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                image.setImageResource(R.mipmap.ic_launcher);
            }
        };

        //ImageRequest는 익명 리스너 사용이 안된다.
        ImageRequest imageRequest = new ImageRequest(imgURL_address, image_listener, 0, 0, null, image_err_listener);

        // 3) 생성한 StringRequest를 RequestQueue에 추가
        //mQueue2.add(stringRequest);   //싱글턴 사용 안할 시
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        MySingleton.getInstance(this).addToRequestQueue(imageRequest);
    }


    public void like_post() {
        // 서버에 뉴스피드 정보 요청
        final String URL_address = "http://52.32.69.220:3000/mypage";
        final String imgURL_address = "https://s3-ap-northeast-1.amazonaws.com/moviest/poster/172356880.jpg";
        //mQueue2 = Volley.newRequestQueue(this);       //싱글턴 사용 안할 시
        mQueue2 = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        // Request a string response from the provided URL.
        // 2) Request Obejct인 StringRequest 생성
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_address,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response is: ", response.toString());
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Log.d("error", error.toString());
                }
                // 네트워크 연결이 모두 끊어진 경우
                else if (error instanceof NoConnectionError) {
                    Log.d("error", error.toString());
                } else if (error instanceof AuthFailureError) {
                    Log.d("error", error.toString());
                }
                // 서버에러, URL에 해당 자료가 없어도 이곳이 불린다.
                else if (error instanceof ServerError) {
                    Log.d("error", error.toString());
                } else if (error instanceof NetworkError) {
                    Log.d("error", error.toString());
                } else if (error instanceof ParseError) {
                    Log.d("error", error.toString());
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", "a");
                map.put("content", "b");
                map.put("userid", "c");
                map.put("title", "a");
                map.put("content", "b");
                map.put("userid", "c");
                map.put("title", "a");
                map.put("content", "b");
                map.put("userid", "c");
                return map;
            }
        };


        Response.Listener<Bitmap> image_listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap arg0) {
                // TODO Auto-generated method stub
                image.setImageBitmap(arg0);
            }
        };
        Response.ErrorListener image_err_listener = new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                image.setImageResource(R.mipmap.ic_launcher);
            }
        };

        //ImageRequest는 익명 리스너 사용이 안된다.
        ImageRequest imageRequest = new ImageRequest(imgURL_address, image_listener, 0, 0, null, image_err_listener);

        // 3) 생성한 StringRequest를 RequestQueue에 추가
        //mQueue2.add(stringRequest);   //싱글턴 사용 안할 시
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        MySingleton.getInstance(this).addToRequestQueue(imageRequest);
    }


    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            multipart_ex(params[0]);
            return null;
        }

        protected void onPostExecute(Double result) {
            Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        public void multipart_ex(String imagePath) {
            imageFile = new File(imagePath);
            Charset chars = Charset.forName("UTF-8");
            cz.msebera.android.httpclient.client.HttpClient httpClient = new cz.msebera.android.httpclient.impl.client.DefaultHttpClient();
            cz.msebera.android.httpclient.client.methods.HttpPost httpPost = new cz.msebera.android.httpclient.client.methods.HttpPost("http://52.32.69.220:3000/mypage");
            try {
                httpPost.setHeader("Accept-Charset", "UTF-8");
                cz.msebera.android.httpclient.HttpEntity entity = MultipartEntityBuilder
                        .create()
                        .addTextBody("userid", "23589085092")       //userId는 페북, 네이버 연동 시 얻는 고유 아이디
                        .addBinaryBody("files", imageFile, ContentType.create("application/octet-stream"), imageFile.getName())
                        .addTextBody("uname", user_nickname.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                                //.addTextBody("uphone", "c")
                        .addTextBody("dname", pet_name.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                        .addTextBody("dage", pet_age.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                        .addTextBody("dyear", pet_birthday.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                        .addTextBody("dsex", pet_sex, ContentType.create("Multipart/related", "UTF-8"))
                        .addTextBody("dkind", pet_specific.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                        .addTextBody("lat", lat)
                        .addTextBody("lon", lon)
                        .addTextBody("uage", user_age.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                        .addTextBody("usex", user_sex, ContentType.create("Multipart/related", "UTF-8"))
                        .addTextBody("intro", pet_introduction_write.getText().toString(), ContentType.create("Multipart/related", "UTF-8"))
                        .build();

                httpPost.setEntity(entity);

                cz.msebera.android.httpclient.HttpResponse response = httpClient.execute(httpPost);
                cz.msebera.android.httpclient.HttpEntity result = response.getEntity();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //고정 주소값을 통해 위도 경도 뽑아내기
    private class geoPointTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {

            lon_lat = GeoHelper.getGeoPoint(GeoHelper.getLocationInfo(params[0].replace("\n", " ")
                    .replace(" ", "%20")));  //주소를 넘겨준다(공백이나 엔터는 제거합니다)
            lon = lon_lat.get(0);
            lat = lon_lat.get(1);
            Log.d("lon / lat", lon + " / " + lat);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}