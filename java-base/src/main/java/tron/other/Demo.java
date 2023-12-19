package tron.other;

import org.tron.trident.core.ApiWrapper;
import org.tron.trident.core.contract.Contract;
import org.tron.trident.core.contract.Trc20Contract;
import org.tron.trident.utils.Convert;

import java.math.BigInteger;

public class Demo {

    final static String ACCOUNT_FIRST_ADDRESS = "TBerc96QsrY78Pp3bjDKwJJBTdiZiHSM5A";
    final static String ACCOUNT_FIRST_ADDRESS_BASE = "589db98f4b5f01431533cb32a44e9e102ac01ed1cba822f5e2ad74271afda05b";
    final static String ACCOUNT_SECOND_ADDRESS = "TWVx3tmWpXfCEqcupkwFuy6wcejqNqaX9N";
    final static String ACCOUNT_SECOND_ADDRESS_BASE = "41e132ee0ee50c35307f8b3e6504e31dc9a07f314b";

        static final String smartContract = "TXLAQ63Xg1NAzckPwKHvzw7CSEmLMEqcdj";

    public static void main(String[] args) {

        transferTrc(ACCOUNT_FIRST_ADDRESS, ACCOUNT_SECOND_ADDRESS, "101", smartContract, ACCOUNT_FIRST_ADDRESS_BASE);
    }

    /**
     * USDT转账
     * @param fromAccount 转账的账户
     * @param fromPrivateKey 转账账户的私钥
     * @param toAccount 要转给的账户
     * @param contractAddress TRC20的合同地址
     * @param sunAmount 转账金额
     * @return
     */
    public String transferUSDT(String fromAccount,String fromPrivateKey,String toAccount,
                               String contractAddress,String sunAmount){
        String hashNumber = null;
        ApiWrapper wrapper = ApiWrapper.ofNile(fromPrivateKey);
        try {
            Contract contract = wrapper.getContract(contractAddress); //trc20合同地址 usdt
            Trc20Contract token = new Trc20Contract(contract, fromAccount, wrapper); //trc20的合同token
            BigInteger usdtValue = token.balanceOf(fromAccount); //获取转账账户的USDT余额
            BigInteger sunAmountValue = Convert.toSun(sunAmount, Convert.Unit.TRX).toBigInteger(); //获取想要转账的数额
            if (usdtValue.compareTo(sunAmountValue) >= 0) { //进行比较
                long l = Convert.toSun("10", Convert.Unit.TRX).longValue(); //设置最大矿工费用
                hashNumber = token.transfer(toAccount, sunAmountValue.longValue(), 0, "备注", l); //转账
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  hashNumber;
    }


    public static String transferTrc(String fromAddress, String toAddress, String amount, String contractAddress, String privateKey) {
        ApiWrapper wrapper = ApiWrapper.ofNile(privateKey);
        String ret = "";
        try {
            //根据合约地址获取封装好的合约
            Contract contract = wrapper.getContract(contractAddress);
            //创建一个TRC20合约对象
            Trc20Contract token = new Trc20Contract(contract, fromAddress, wrapper);
            //获取想要转移的数量
            BigInteger sunAmountValue = Convert.toSun(amount, Convert.Unit.TRX).toBigInteger();
            //设置最大手续费
            long maxTrx = Convert.toSun("17", Convert.Unit.TRX).longValue();
            ret = token.transfer(toAddress, sunAmountValue.longValue(), 0, "", maxTrx);
            System.out.println("哈希：" + ret);
        } catch (Exception e) {
            System.out.println("转移失败:" + e.getMessage().toString());
        } finally {
            wrapper.close();
        }
        return ret;
    }
}
