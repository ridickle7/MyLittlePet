package ridickle.co.kr.mylittlepet.Network.DataBody;

/**
 * Created by ridickle on 2017. 10. 13..
 */


//      {
//        "info":{
//          "size":{
//              "width":900,
//              "height":675
//          },
//          "faceCount":2
//        },
//        "faces":
//          [
//              {
//              "celebrity":{
//                  "value":"안도하루카",
//                  "confidence":0.266675
//                  }
//              },
//              {
//                  "celebrity":{
//                      "value":"서효림",
//                      "confidence":0.304959
//                  }
//              }
//          ]
//        }
public class Network_FamousFace {
    ridickle.co.kr.mylittlepet.Network.DataBody.info info;
    ridickle.co.kr.mylittlepet.Network.DataBody.faces[] faces;

    public info getInfo() {
        return info;
    }

    public void setInfo(info info) {
        this.info = info;
    }

    public faces[] getFaces() {
        return faces;
    }

    public void setFaces(faces[] faces) {
        this.faces = faces;
    }
}

