package com.example.webservices;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import WebServices.Asynchtask;
import WebServices.WebService;

public class SaludoActivity extends AppCompatActivity implements Asynchtask {
    TextView txtSaludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);
        txtSaludo= findViewById(R.id.txtSaludo);
        Map<String, String> datos = new HashMap<String, String>();
        Bundle bundle = this.getIntent().getExtras();
        datos.put("usr",bundle.getString("usr") );
        datos.put("pass",bundle.getString("pass") );
        handleSSLHandshake();
            WebService ws= new WebService("http://uealecpeterson.net/ws/login.php", datos, SaludoActivity.this, SaludoActivity.this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        txtSaludo.setText("Resp:  "  +  result );
        /*String lstBancos="";
        JSONArray JSONlista =  new JSONArray(result);
        for(int i = 0; i< JSONlista.length(); i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
          lstBancos= lstBancos +"\n" +banco.getString("Empresa").toString();
        }
        txtSaludo.setText(lstBancos);
*/
    }
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

}