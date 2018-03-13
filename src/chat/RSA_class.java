package chat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.util.Random;


public class RSA_class {

    static  private JSONObject key;


    public Object generationes() {


        try {
            JSONParser parser = new JSONParser();
            String separator = File.separator;
            Object obj = parser.parse(new FileReader("src"+separator+"data"+separator+"public_keys.json"));
            JSONObject jsonObject =  (JSONObject) obj;
            key = jsonObject;
        } catch (IOException e) {
            keys_generations keys = new keys_generations();
            try {
                key = keys.RunSearches();
            } catch (NoSuchAlgorithmException se) {
                se.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //String publickey = String.valueOf(key.get("public key"));
       // String exponent = String.valueOf(key.get("exponent"));

       // System.out.println("result " + publickey+"\n"+exponent);

        return key;


    }




}


    class keys_generations {
        public static JSONObject RunSearches() throws NoSuchAlgorithmException {
            Random rand1 = new Random(System.currentTimeMillis());
            Random rand2 = new Random(System.currentTimeMillis()*10);

            Random random = new Random();
            int num1 = 500 + random.nextInt(100);
            int num2 = 500 + random.nextInt(100);

            BigInteger p = BigInteger.probablePrime(num1,rand1);
            BigInteger q = BigInteger.probablePrime(num2,rand2);

            BigInteger p_1 = p.subtract(new BigInteger("1"));
            BigInteger q_1 = q.subtract(new BigInteger("1"));

            BigInteger n = p.multiply(q);
            BigInteger Fn = p_1.multiply(q_1);

            int pubkey = 900 + random.nextInt(1000);
            BigInteger vote;
            while (true) {
                vote = n.gcd(new BigInteger(""+pubkey));
                    if(vote.equals(BigInteger.ONE)) {
                        break;
                    }
            }
            BigInteger publickey = new BigInteger(""+pubkey);
            BigInteger privatekey = publickey.modInverse(n);
            Hashing hashing = new Hashing();
            String hash = hashing.hashing(publickey);
            JSONObject obj_publicKey = new JSONObject();
            JSONObject obj_privetKey = new JSONObject();
            obj_publicKey.put("public key", String.valueOf(n));
            obj_privetKey.put("private key", String.valueOf(privatekey));
            obj_publicKey.put("exponent", String.valueOf(publickey));
            obj_publicKey.put("hash", hash);
            String separator = File.separator;
            try (FileWriter file = new FileWriter("src"+"chat"+"data"+separator+"private_key.json")) {
                file.write(obj_privetKey.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (FileWriter file = new FileWriter("src"+separator+"data"+separator+"public_keys.json")) {
                file.write(obj_publicKey.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return obj_publicKey;
        }



}
