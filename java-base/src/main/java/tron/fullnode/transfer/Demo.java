package tron.fullnode.transfer;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.bouncycastle.util.encoders.Hex;
import tron.fullnode.util.KeyPair;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.HashMap;

public class Demo {
    final static String URL = "https://nile.trongrid.io";

    static final String KEY_ONE = "589db98f4b5f01431533cb32a44e9e102ac01ed1cba822f5e2ad74271afda05b";
    static final String KEY_TWO = "da7ed050b8dbce78e840bc4a4a0bfbf131bcc43983ceb9fc01fb23bf40db5719";

    public static void main(String[] args) throws UnsupportedEncodingException {
        KeyPair keyPair = new KeyPair(KEY_ONE);
        //String private key
        System.out.println("私钥:"+keyPair.toPrivateKey());
        //String public key
        System.out.println("公钥:"+keyPair.toPublicKey());
        System.out.println("Base58地址:"+keyPair.toBase58CheckAddress());
        System.out.println("哈希地址:"+keyPair.toHexAddress());

        String createtransaction = createtransaction();
        JSONObject obj = JSONUtil.parseObj(createtransaction);

        String txId = obj.getStr("txID");
        byte[] decode = Hex.decode(txId);
        byte[] bytes = KeyPair.signTransaction(decode, keyPair);
        String signatureHex = bytesToHex(bytes);
        System.out.println("原始流水ID后的:"+txId);
        System.out.println("签名后的:"+signatureHex);


        HashMap<String, Object> map = new HashMap<>();
        map.put("signature", signatureHex);
        map.put("txID", txId);
        map.put("visible", true);

        map.put("raw_data", obj.getJSONObject("raw_data"));
        map.put("raw_data_hex", obj.getStr("raw_data_hex"));

        System.out.println("广播前打印请求参数:"+JSONUtil.toJsonPrettyStr(map));

        broadcasttransaction(map);
    }

    public static String bytesToHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }


    private static String createtransaction(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("owner_address", "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A");
        map.put("to_address", "TWVx3tmWpXfCEqcupkwFuy6wcejqNqaX9N");
        map.put("amount", 140);
        map.put("visible", true);

        String sub_url = URL + "/wallet/createtransaction";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("创建交易,响应的消息:" + body);
        return body;
    }

    /**
     * 广播签名
     */
    private static void broadcasttransaction(HashMap<String, Object> map){
        String sub_url = URL + "/wallet/broadcasttransaction";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("响应的消息:" + body);
    }
}
