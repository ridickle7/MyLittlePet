package ridickle.co.kr.mylittlepet.Network.DataBody;

/**
 * Created by ridickle on 2017. 10. 10..
 */

public class Network_Data {
    String data1;
    String data2;

    public Network_Data(String data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }
}
