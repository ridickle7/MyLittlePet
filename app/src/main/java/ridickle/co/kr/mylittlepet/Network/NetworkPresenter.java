package ridickle.co.kr.mylittlepet.Network;

import java.io.IOException;
import java.util.Properties;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ridickle.co.kr.mylittlepet.MyApplication;

import static ridickle.co.kr.mylittlepet.MyApplication.getProp;

/**
 * Created by ridickle on 2017. 10. 7..
 * retrofit 설계 참고
 * 1. http://gun0912.tistory.com/50
 * 2. http://developer88.tistory.com/67
 */

public class NetworkPresenter {
    private static NetworkPresenter nPresenter = null;
    private static Retrofit retrofit1, retrofit2;
    private static NetworkModel retrofitService;
    private static NetworkNaverModel naverRetrofitService;

    public NetworkPresenter() {
        // Retrofit 은 파라메터, 쿼리, 헤더 등의 매핑작업, 결과 처리작업 등
        // 반복되는 작업들을 편리하게 처리할 수 있게끔 구현된 라이브러리

        // 레트로핏을 사용하지 않고 okhttp만을 이용해서도 작업이 가능하나,
        // url 매핑, 파라메터 매핑, 헤더세팅 등의 귀찮은 작업들이 많아지기 때문에 레트로핏 사용

        retrofit1 = new Retrofit.Builder()
                .baseUrl(getProp().getProperty("serverAddress"))
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create()) //Json Parser 추가
                .build();                                           //인터페이스 연결

        retrofitService = retrofit1.create(NetworkModel.class);

        retrofit2 = new Retrofit.Builder()
                .baseUrl(getProp().getProperty("NaverAPI")) // 기본은 유명인 얼굴 인식
                .client(getNaverRequestHeader())
                .addConverterFactory(GsonConverterFactory.create()) //Json Parser 추가
                .build();                                           //인터페이스 연결

        naverRetrofitService = retrofit2.create(NetworkNaverModel.class);
    }

    public static synchronized NetworkPresenter getInstance() {
        if (nPresenter == null) {
            nPresenter = new NetworkPresenter();
        }
        return nPresenter;
    }

    public static NetworkModel getRetrofitService() {
        return retrofitService;
    }

    public static NetworkNaverModel getNaverRetrofitService() {
        return naverRetrofitService;
    }

    private OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        return client;
    }

    private OkHttpClient getNaverRequestHeader() {
        final Properties prop = MyApplication.getProp();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("X-Naver-Client-Id", prop.getProperty("ClientId"))
                        .header("X-Naver-Client-Secret", prop.getProperty("ClientSecret"))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    /**     버전이 안 맞아서 이제 지원 안 하는 듯 (확인 필요
     * OkHttpClient 객체 생성 과정
     *
     * 1. OkHttpClient 객체 생성
     * 2. 세션 데이터의 획득을 위해 response 데이터 중 헤더 영역의 쿠키 값을 가로채기 위한 RecivedCookiesInterceptor 추가
     * 3. 서버로 데이터를 보내기 전 세션 데이터 삽입을 위해 AddCookiesInterceptor 추가
     * 4. OkHttpClient 빌드
     *
     * 주의) 가로채기 위한 메소드는 addInterceptor이고 삽입하기 위한 메소드는 addNetworkInterceptor
     */

//    // Request마다 Preference에 저장되어있는 쿠키값을 함께 Header에 넣어주는 클래스
//    public class AddCookiesInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request.Builder builder = chain.request().newBuilder();
//            // Preference에서 cookies를 가져오는 작업을 수행
//            Set<String> preferences = MySharedPreference.getInstance(MyApplication.getDefaultContext()).getSet(MySharedPreference.COOKIE);
//
//            for (String cookie : preferences) {
//                builder.addHeader("Cookie", cookie);
//            }
//
//            // Web,Android,iOS 구분을 위해 User-Agent세팅
//            builder.removeHeader("User-Agent").addHeader("User-Agent", "Android");
//            return chain.proceed(builder.build());
//        }
//    }
//
//    // Response로부터 쿠키정보를 가져와서 Preference에 저장하는 클래스
//    public class ReceivedCookiesInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Response originalResponse = chain.proceed(chain.request());
//            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//                HashSet<String> cookies = new HashSet<>();
//
//                for (String header : originalResponse.headers("Set-Cookie")) {
//                    cookies.add(header);
//                }
//
//                // Preference에 cookies를 넣어주는 작업을 수행
//                MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.COOKIE, cookies);
//            }
//            return originalResponse;
//        }
//    }
}

