package ridickle.co.kr.mylittlepet.Network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_FamousFace;

/**
 * Created by ridickle on 2017. 10. 13..
 */

public interface NetworkNaverModel {
//    requirement
//      1. Client ID와 Client Secret 정보를 헤더에 포함 (완료 : Retrofit header 설정에 완료)
//      2. 요청을 multipart 형식                      (완료 : Multipart로 적음)
//      3. 메시지의 이름은 image                       ()

    @Multipart
    @POST("/v1/vision/celebrity")
    public Call<Network_FamousFace> post_visionCelebrity(@Part("image") RequestBody image);

//    @Multipart
//    @POST("/v1/vision/face")
//    public Call<> post_visionFace(@Part("image") RequestBody image);
}
