package tron.fullnode.transfer;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.bouncycastle.util.encoders.Hex;
import org.tron.trident.abi.FunctionEncoder;
import org.tron.trident.abi.datatypes.Address;
import org.tron.trident.abi.datatypes.Type;
import org.tron.trident.abi.datatypes.generated.Uint256;
import org.tron.trident.core.key.KeyPair;
import org.tron.trident.core.utils.ByteArray;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 智能合约
 */
public class Trc20Demo {
    final static String URL = "https://nile.trongrid.io";

    static final String ADDRESS_ONE = "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A";
    static final String KEY_ONE = "589db98f4b5f01431533cb32a44e9e102ac01ed1cba822f5e2ad74271afda05b";

    static final String ADDRESS_TWO = "TWVx3tmWpXfCEqcupkwFuy6wcejqNqaX9N";
    static final String KEY_TWO = "da7ed050b8dbce78e840bc4a4a0bfbf131bcc43983ceb9fc01fb23bf40db5719";

    // 合约地址
    static final String smartContract = "TXLAQ63Xg1NAzckPwKHvzw7CSEmLMEqcdj";

    public static void main(String[] args) throws UnsupportedEncodingException {
//        getSmartContract();
//        getAmountBySmartContract();
        transfer2();


    }

    private static void getAmountBySmartContract() {

        KeyPair keyPair = new KeyPair(KEY_ONE);

        HashMap<String, Object> map = new HashMap<>();
        map.put("contract_address", smartContract);
        map.put("owner_address", ADDRESS_ONE);
        map.put("function_selector", "balanceOf(address)");
        map.put("visible", "true");
        List<Type> inputParameters = new ArrayList<>();
        inputParameters.add(new Address(keyPair.toHexAddress().substring(2)));
        map.put("parameter", FunctionEncoder.encodeConstructor(inputParameters));

        String sub_url = URL + "/wallet/triggerconstantcontract";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("合约信息:" + body);

        BigDecimal amount = BigDecimal.ZERO;
        JSONObject obj = JSONUtil.parseObj(body);
        JSONArray results = obj.getJSONArray("constant_result");
        if (results != null && !results.isEmpty()) {
            BigInteger _amount = new BigInteger(results.getStr(0), 16);
            amount = new BigDecimal(_amount).divide(new BigDecimal("1000000"), 6, RoundingMode.FLOOR);
        }
        System.out.printf("账号%s的balance=%s%n", ADDRESS_ONE, amount.toString());
    }

    private static void getSmartContract() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("value", "TXLAQ63Xg1NAzckPwKHvzw7CSEmLMEqcdj");
        map.put("visible", "true");

        String sub_url = URL + "/wallet/getcontract";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("合约信息:" + body);
    }

    private static void transfer2() {
        BigDecimal amount = new BigDecimal("11");
        KeyPair keyPair = new KeyPair(KEY_ONE);
        HashMap<String, Object> map = new HashMap<>();
        map.put("visible", true);
        map.put("contract_address", smartContract);
        map.put("function_selector", "transfer(address,uint256)");

        List<Type> inputParameters = new ArrayList<>();
        inputParameters.add(new Address(keyPair.toHexAddress().substring(2)));
        inputParameters.add(new Uint256(amount.multiply(new BigDecimal("1000000")).toBigInteger()));
        String parameter = FunctionEncoder.encodeConstructor(inputParameters);
        map.put("parameter", parameter);
        map.put("owner_address", ADDRESS_ONE);
        map.put("call_value", 0);
        map.put("fee_limit", 9999000000L);

        String sub_url = URL + "/wallet/triggersmartcontract";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("转账响应的消息:" + body);


        JSONObject obj = JSONUtil.parseObj(body);
        JSONObject transaction = obj.getJSONObject("transaction");
        String txId = transaction.getStr("txID");
        byte[] decode = Hex.decode(txId);
        byte[] bytes = KeyPair.signTransaction(decode, keyPair);
        String signatureHex = ByteArray.toHexString(bytes);
        System.out.println("原始流水ID后的:" + txId);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("transaction", ByteArray.toHexString(bytes));
        System.out.println("广播前打印请求参数:" + JSONUtil.toJsonPrettyStr(map2));
        broadcasttransaction2(map);
    }




    static void broadcasttransaction2(HashMap<String, Object> map) {
        String sub_url = URL + "/wallet/broadcasthex";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("响应的消息:" + body);
    }
}
