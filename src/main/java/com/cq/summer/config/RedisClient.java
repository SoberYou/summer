package com.cq.summer.config;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisClient
 * @Description: Helper class that simplifies Redis data access code. 
 * @author qing.chen 
 * @date 2017年12月26日 下午1:13:34
 */
@Component
@Slf4j
public class RedisClient {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RedisClient.class);
	
	//默认缓存时间
	private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * @Description: Delete all all keys from all databases.        
	 * @author qing.chen 
	 * @date 2017年12月25日 上午11:28:23
	 */
	public Boolean flushAll(){
		try{
			redisTemplate.execute(new RedisCallback<Boolean>(){
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					connection.flushAll();
					return true;
				}
			});
		}catch(Exception e){
			logger.info("Cache清空失败:" + e);
			return true;
		}
		return null;
	}
	
	/**
	 * @Description: Delete given key.        
	 * @author qing.chen 
	 * @date 2017年12月25日 上午11:32:44
	 */
	public Boolean delete(Object key){
		try{
			redisTemplate.delete(key.toString());
			return true;
		}catch(Exception e){
			logger.error("Cache删除失败:" + e);
			return false;
		}
	}
	
	/**
	 * @Description: Set the value of a hash field.        
	 * @author qing.chen 
	 * @date 2017年12月25日 上午11:37:20
	 */
	public Boolean addHash(String key, String field, String value){
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.hSet(serializer.serialize(key), serializer.serialize(field), serializer.serialize(value));
				return true;
			}
		});
		return result;
	}
	
	/**
	 * @Description: Get value for given field from hash at key.  
	 * @author qing.chen 
	 * @date 2017年12月26日 上午9:26:18
	 */
	public String getHash(String key, String field){
		String result = redisTemplate.execute(new RedisCallback<String>(){
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.hGet(serializer.serialize(key), serializer.serialize(field));
				return serializer.deserialize(value);
			}
			
		});
		return result;
	}
	
	/**
	 * @Description: Delete given hash fields.        
	 * @author qing.chen 
	 * @date 2017年12月26日 上午9:32:05
	 */
	public Boolean delHash(String key, String field){
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.hDel(serializer.serialize(key), serializer.serialize(field));
				return true;
			}
		});
		return result;
	}
	
	/**
	 * @Description: set value for key , set the default expire time       
	 * @author qing.chen 
	 * @date 2017年12月26日 上午9:42:18
	 */
	public Boolean save(String key, String value){
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				connection.expire(serializer.serialize(key),DEFAULT_CACHE_SECONDS);
				return true;
			}
		});
		return result;
	}
	
	
	/**
	 * @Description:  set value for key , with given expire time           
	 * @author qing.chen 
	 * @date 2017年12月26日 上午9:47:57
	 */
	public Boolean save(String key, String value, int time){
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>(){
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				connection.expire(serializer.serialize(key), time);
				return true;
			}
		});
		return result;
	}
	
	/**
	 * @Description: Get the value of the key        
	 * @author qing.chen 
	 * @date 2017年12月26日 上午9:56:01
	 */
	public String get(String key){
		String result = redisTemplate.execute(new RedisCallback<String>(){
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));
				return serializer.deserialize(value);
			}
		});
		return result;
	}
	
	/**
	 * @Description: set the expire of key       
	 * @author qing.chen 
	 * @date 2017年12月26日 上午10:10:30
	 */
	public Boolean expire(Object key, int seconds){
		try{
			redisTemplate.expire(key.toString(), seconds, TimeUnit.SECONDS);
			return true;
		}catch(Exception e){
			logger.error("Cache设置超时时间失败:" + e);
			return false;
		}
	}
	
}
