package tron.fullnode.account;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

public class Demo {

    final static String URL = "https://nile.trongrid.io";


    public static void main(String[] args) {
//        validateAddress();
//        createaccount();
//        getAccount();
        createtransaction();
    }

    /**
     * 校验账户合法性
     */
    private static void validateAddress() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("address", "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A");
        map.put("visible", true);

        String sub_url = URL + "/wallet/validateaddress";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("响应的消息:" + body);
    }


    /**
     * 创建账户
     * TODO 有问题
     */
    private static void createaccount() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("address", "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A");
        map.put("visible", true);

        String sub_url = URL + "/wallet/createaccount";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("响应的消息:" + body);
    }

    /**
     * 获取账户信息
     * {
     *   "address": "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A",
     *   "balance": 1880000000,
     *   "create_time": 1702315944000,
     *   "latest_opration_time": 1702464768000,
     *   "free_net_usage": 238,
     *   "latest_consume_free_time": 1702464768000,
     *   "net_window_size": 28800000,
     *   "net_window_optimized": true,
     *   "account_resource": {
     *     "energy_window_size": 28800000,
     *     "energy_window_optimized": true
     *   },
     *   "owner_permission": {
     *     "permission_name": "owner",
     *     "threshold": 1,
     *     "keys": [
     *       {
     *         "address": "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A",
     *         "weight": 1
     *       }
     *     ]
     *   },
     *   "active_permission": [
     *     {
     *       "type": "Active",
     *       "id": 2,
     *       "permission_name": "active",
     *       "threshold": 1,
     *       "operations": "7fff1fc0033efb0f000000000000000000000000000000000000000000000000",
     *       "keys": [
     *         {
     *           "address": "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A",
     *           "weight": 1
     *         }
     *       ]
     *     }
     *   ],
     *   "frozenV2": [
     *     {
     *
     *     },
     *     {
     *       "type": "ENERGY"
     *     },
     *     {
     *       "type": "TRON_POWER"
     *     }
     *   ],
     *   "asset_optimized": true
     * }
     */
    private static void getAccount() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("address", "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A");
        map.put("visible", true);

        String sub_url = URL + "/wallet/getaccount";
        String body = HttpRequest.post(sub_url)
                .header("Content-Type", "application/json")
                .header("TRON-PRO-API-KEY", "eaea18fd-483c-4827-82f7-a8772483e54a")
                .body(JSONUtil.toJsonStr(map))
                .execute().body();
        System.out.println("响应的消息:" + body);
        System.out.println("剩余金额:" + JSONUtil.parseObj(body).getLong("balance") / 1000000 + "Trx");
    }

    /**
     * 转账
     *
     * {
     *   "visible": true,
     *   "txID": "c104614b6f36c4bd3467f43a36cb885dcf316edfa0af9a905b5fac26f00bad92",
     *   "raw_data": {
     *     "contract": [
     *       {
     *         "parameter": {
     *           "value": {
     *             "amount": 130,
     *             "owner_address": "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A",
     *             "to_address": "TWVx3tmWpXfCEqcupkwFuy6wcejqNqaX9N"
     *           },
     *           "type_url": "type.googleapis.com/protocol.TransferContract"
     *         },
     *         "type": "TransferContract"
     *       }
     *     ],
     *     "ref_block_bytes": "4618",
     *     "ref_block_hash": "0cbb344d507a5a14",
     *     "expiration": 1702471893000,
     *     "timestamp": 1702471834693
     *   },
     *   "raw_data_hex": "0a02461822080cbb344d507a5a14408880ee99c6315a66080112620a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412310a15411277bd3efc1e7d749d1feb65445b62557dfd7490121541e132ee0ee50c35307f8b3e6504e31dc9a07f314b18820170c5b8ea99c631"
     * }
     */
    private static void createtransaction(){
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
        System.out.println("响应的消息:" + body);
    }






}
