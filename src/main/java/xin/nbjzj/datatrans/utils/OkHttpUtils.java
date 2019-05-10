package xin.nbjzj.datatrans.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
	public static Response NewCardApi(String IdCard) {
		OkHttpClient client = new OkHttpClient();
		Response response = null;
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "undefined=");
		Request request = new Request.Builder()
				  .url("http://10.22.230.40:8080/2s3sb9do8S3VDaj5.htm?czrkgmsfhm="+IdCard)
				  .post(body)
				  .addHeader("powerMatters", "00844-000")
				  .addHeader("subPowerMatters", "00844-000")
				  .addHeader("Authorization", "Bearer 5bf4d012cff47e000790b43d2b9f26f3e0584426abbc23bccadd5d33")
				  .addHeader("cache-control", "no-cache")
				  .addHeader("Postman-Token", "9151c3ef-b954-4092-ae4d-a49cf606a22c")
				  .build();
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static Response DisabledInfoApi(String IdCard) {
		OkHttpClient client = new OkHttpClient();
		Response response = null;
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "undefined=");
		Request request = new Request.Builder()
				  .url("http://10.22.230.40:8080/disabledBaseInfo.htm?cardId="+IdCard)
				  .post(body)
				  .addHeader("powerMatters", "00844-000")
				  .addHeader("subPowerMatters", "00844-000")
				  .addHeader("Authorization", "Bearer 5bf4d012cff47e000790b43d4f47892bb74d4b00a79076b15a608670")
				  .addHeader("cache-control", "no-cache")
				  .addHeader("Postman-Token", "b90ea6aa-b353-4a70-94a6-5948190dd96e")
				  .build();

		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static Response DibaoInfoApi(String IdCard) {
		OkHttpClient client = new OkHttpClient();
		Response response = null;
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "undefined=");
		Request request = new Request.Builder()
				  .url("http://10.22.230.40:8080/lowestRescueInfo.htm?cardId="+IdCard)
				  .post(body)
				  .addHeader("powerMatters", "00844-000")
				  .addHeader("subPowerMatters", "00844-000")
				  .addHeader("Authorization", "Bearer 5bf4d012cff47e000790b43d65c6fabfc12f42d783f59470f7296d70")
				  .addHeader("cache-control", "no-cache")
				  .addHeader("Postman-Token", "9260ac74-2820-4054-8554-0d10d5b191cf")
				  .build();

		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
