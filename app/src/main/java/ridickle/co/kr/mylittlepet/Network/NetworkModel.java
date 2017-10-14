package ridickle.co.kr.mylittlepet.Network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;

;

/**
 * Created by ridickle on 2017. 10. 8..
 */

public interface NetworkModel {
    // Get방식, 파라메터는 @Query("파라메터명")으로 보낼 수 있습니다.
    @GET("/users/login/{tokenId}")
    public Call<Network_User> get_usersLogin(@Path("tokenId") String tokenId);

    @GET("/users")
    public Call<ArrayList<Network_User>> get_users(@Query("orderBy") int orderBy);

    @GET("/users/{uId}")
    public Call<Network_User> get_usersDetail(@Path("uId") String uId);

    @POST("/users")
    public Call<Network_User> post_user(@Body Network_User body);

    @GET("/contents")
    Call<ArrayList<Network_Content>> get_content(@Query("uId") String uId);

    @GET("/contents/{cId}")
    Call<Network_Content> get_contentDetail(@Path("cId") String cId);

    @FormUrlEncoded
    @POST("/contents")
    Call<Network_Content> post_content(@Field("uId") String uId, @Field("cText") String cText, @Field("cImageURL") String cImageURL);

    @GET("/events")
    Call<ArrayList<Network_Event>> get_event();

    @GET("/events/{eId}")
    public Call<Network_Event> get_event(@Path("eId") String eId);

    @GET("/events/content/{eId}")
    public Call<ArrayList<Network_Content>> get_eventContent(@Path("eId") String eId);

    @FormUrlEncoded
    @POST("/events/content")
    Call<Network_Content> post_eventsContent(@Field("uId") String uId, @Field("eId") String eId, @Field("cText") String cText, @Field("cImageURL") String cImageURL);


}
