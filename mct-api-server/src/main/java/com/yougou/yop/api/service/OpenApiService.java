/**
 * 
 */
package com.yougou.yop.api.service;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import com.yougou.util.MD5Encryptor;
import com.yougou.util.SHA1Encryptor;
import com.yougou.yop.api.IOpenApiService;

/**
 * openapi 
 * @author he.wc
 *
 */
@Service(value="openApiService")
public class OpenApiService implements IOpenApiService {

	@Resource(name = "stringRedisTemplate")
	private HashOperations<String, String, String> hashOperations;
	
	@Override
	public boolean apiAuth(String url) throws Exception {
		int endIndex = url.lastIndexOf("api.sc?");
		url = url.substring(endIndex+7);
		System.out.println(" str ==>> "+url);
		
		Map<String, Object> context = new TreeMap<String, Object>();
		
		String sign = "";
		for(String param:url.split("&")){
			String key = param.split("=")[0];
			String value = param.split("=")[1];
			if ("sign".equalsIgnoreCase(key)) {
				sign = value;
				continue;
			}
		
		
			
			context.put(key, value);
			
		}
		
		//context.put("app_version", "1.0");
		//context.put("timestamp", "2014-3-31 17:1:58");
		
		System.out.println("context ==>> "+context);
		
		
		int paramIndex = 0;
		String signMethod = (String)context.get("sign_method");
		String appKey = (String)context.get("app_key");
		String secret = hashOperations.get("api.appkey.secret.hash", appKey);
		String[] params = new String[context.size()];
		for (Map.Entry<String, Object> entry : context.entrySet()) {
			params[paramIndex++] = entry.getKey() + entry.getValue();
		}
		
		Arrays.sort(params);
		secret += StringUtils.join(params, "");
		String serverSign =  "sha-1".equals(signMethod) ? SHA1Encryptor.encrypt(secret) : MD5Encryptor.encrypt(secret);

		System.out.println("secret ==>"+secret);
		System.out.println("sign ==>>" + sign);
		System.out.println("serverSign ==>>" + serverSign);
		if(StringUtils.equals(sign, serverSign)){
			return true;
		}
		return false;
	}

	
}
