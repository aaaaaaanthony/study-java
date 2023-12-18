package tron;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.net.http.HttpClient;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {


    final static String URL = "https://nile.trongrid.io";
    final static String ACCOUNT_FIRST_ADDRESS = "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A";
    final static String ACCOUNT_FIRST_ADDRESS_BASE = "411277bd3efc1e7d749d1feb65445b62557dfd7490";
    final static String ACCOUNT_SECOND_ADDRESS = "TWVx3tmWpXfCEqcupkwFuy6wcejqNqaX9N";
    final static String ACCOUNT_SECOND_ADDRESS_BASE = "41e132ee0ee50c35307f8b3e6504e31dc9a07f314b";
    final static String transactionID = "6fdc7596df8ad284bcd86cc3b332832eda71df6d08799fef50e5a5b18577a825";
    final static String transactionID2 = "cf15e72f57115b6291c6c03f704fe56a0c7febb1265334d931797987675a637d";


    public static void main(String[] args) {

//        getBalance();
//        getTransaction();
//        getSmartContactInfo();

        getEventsByTransactionId();
    }

    /**
     * 获取金额
     * 随便一个正确的账号就能获取到地址的吗?
     * 也不需要用的api key的吗?
     */
    private static void getBalance() {
        String sub_url = URL+"/v1/accounts/" + ACCOUNT_SECOND_ADDRESS;
        String s = HttpUtil.get(sub_url);
        System.out.println("响应的消息:" + s);
        String str = JSONUtil.parseObj(s).getJSONArray("data").getJSONObject(0).getStr("balance");
        System.out.println("账号的Trx金额:" + str);
    }

    /**
     * 获取交易记录
     */
    private static void getTransaction() {
        String sub_url = URL+"/v1/accounts/" + ACCOUNT_FIRST_ADDRESS+"/transactions";
        String s = HttpUtil.get(sub_url);
        System.out.println("响应的消息:" + s);
        System.out.println("转账历史记录:");
        JSONArray data = JSONUtil.parseObj(s).getJSONArray("data");
        System.out.println("=======================================");
        for (Object datum : data) {
            JSONObject jsonObject1 = JSONUtil.parseObj(datum);
            System.out.println("交易哈希:"+jsonObject1.get("txID"));
            String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(jsonObject1.getJSONObject("raw_data").getLong("timestamp")));
            System.out.println("交易时间:"+result1);
            System.out.println("交易金额:"+jsonObject1.getJSONObject("raw_data").getJSONArray("contract").getJSONObject(0).getJSONObject("parameter").getJSONObject("value").getLong("amount")/1000000+"Trx");
            System.out.println("转账方:"+jsonObject1.getJSONObject("raw_data").getJSONArray("contract").getJSONObject(0).getJSONObject("parameter").getJSONObject("value").getStr("owner_address"));
            System.out.println("接收方:"+jsonObject1.getJSONObject("raw_data").getJSONArray("contract").getJSONObject(0).getJSONObject("parameter").getJSONObject("value").getStr("to_address"));
            System.out.println("--------------------------------------");
        }
        System.out.println("=======================================");
    }

    /**
     * 获取合约信息,看不懂这是干啥的
     */
    private static void getSmartContactInfo() {
        String sub_url = URL+"/v1/accounts/"+ACCOUNT_SECOND_ADDRESS+"/transactions/trc20";
        String s = HttpUtil.get(sub_url);
        System.out.println("响应的消息:" + s);
    }

    private static void getEventsByTransactionId(){
        String sub_url = URL+"/v1/transactions/"+transactionID+"/events";
        String s = HttpUtil.get(sub_url);
        System.out.println("响应的消息:" + s);


    }
}
