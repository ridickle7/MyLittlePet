package ridickle.co.kr.mylittlepet;

import retrofit2.Retrofit;

/**
 * Created by ridickle on 2017. 10. 7..
 */

public class NetworkPresenter {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build();

}
