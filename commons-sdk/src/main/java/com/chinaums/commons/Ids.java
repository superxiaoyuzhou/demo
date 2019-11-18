package com.chinaums.commons;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * 基础IDS
 *
 * @author cly
 */
public class Ids implements Iterable<Entry<String, Object>>, Serializable {

	private ConcurrentMap<String, String> head = new ConcurrentHashMap<>();

	private ConcurrentMap<String, Object> body = new ConcurrentHashMap<>();

	public Ids() {
	}

	public Ids(ConcurrentMap<String, String> head, ConcurrentMap<String, Object> body) {
		this.head = head;
		this.body = body;
	}

	public void setHead(final String k, final String v) {
		if (k != null && v != null) {
			head.put(k, v);
		}
	}

	public String getHead(final String k) {
		return head.get(k);
	}

	public void setBody(final String k, final Object v) {
		if (k != null && v != null) {
			body.put(k, v);
		}
	}

	public <T> T getBody(final String k, Class<T> clazz) {
		return (T) body.get(k);
	}

	public Map<String, String> getHead() {
		return head;
	}

	public void setHead(ConcurrentMap<String, String> head) {
		this.head = head;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public String getStringFromBody(String k){
		return (body.get(k) != null) ? String.valueOf(body.get(k)) : "";
	}

	public void setBody(ConcurrentMap<String, Object> body) {
		this.body = body;
	}

	public Set<String> headNames() {
		return head.keySet();
	}

	public Set<String> bodyName() {
		return body.keySet();
	}

	@Override
	public String toString() {
		return "{head=" + head + ", body=" + body + "}";
	}

	@Override
	public Iterator<Entry<String, Object>> iterator() {
		return this.body.entrySet().iterator();
	}

	public Map<String, Object> ids2Map() {
		Map<String, Object> map = new ConcurrentHashMap<>();
		map.putAll(this.getHead());
		map.putAll(this.getBody());
		if (map.containsKey(CommonFields.SIGNATURE)) {
			map.remove(CommonFields.SIGNATURE);
		}
		return map;
	}

	@Override
	public Ids clone() {
		Ids ids = new Ids();
		head.forEach(ids::setHead);
		body.forEach(ids::setBody);
		return ids;
	}

	public ResIds toResIds(){
		ResIds rsp = new ResIds();
		head.forEach((k, v) -> {
			if (!k.equals(CommonFields.SIGNATURE)) {
				rsp.setHead(k, v);
			}
		});
		return rsp;
	}

	public Ids cloneHead() {
		Ids ids = new Ids();
		head.forEach(ids::setHead);
		return ids;
	}

	public Ids cloneHead(String respCode, String respMsg) {
		Ids ids = cloneHead();
		ids.setHead(CommonFields.RESP_CODE, respCode);
		ids.setHead(CommonFields.RESP_MSG, respMsg);
		return ids;
	}

	public ReqIds toReqIds(){
		ReqIds req = new ReqIds();
		head.forEach(req::setHead);
		body.forEach(req::setBody);
		return req;
	}

	public ReqIds cloneReqHead(){
		ReqIds req = new ReqIds();
		head.forEach((k, v)->{
			if(!k.equals(CommonFields.SIGNATURE) || !k.equals(CommonFields.PLAT_CODE)) {
				req.setHead(k, v);
			}
		});
		return req;
	}

	public ReqIds cloneReqHead(String respCode, String respMsg){
		ReqIds reqIds = cloneReqHead();
		reqIds.setHead(CommonFields.RESP_CODE, respCode);
		reqIds.setHead(CommonFields.RESP_MSG, respMsg);
		return reqIds;
	}

	public List<Map> getArrayListFromBody(String k){
		List<Map> array = Collections.emptyList();
		if(this.getBody().containsKey(k)){
			Object val = this.getBody().get(k);
			if(val instanceof String){
				array = JSON.parseArray(val.toString(), Map.class);
			}else{
				array = (List<Map>)val;
			}
		}
		return array;
	}

	public void remove(final String k) {
		if(body.containsKey(k)){
			body.remove(k);
		}
	}

	@JSONField(serialize=false)
	public String getRespCode(){
		return this.head.get(CommonFields.RESP_CODE);
	}

	@JSONField(serialize=false)
	public String getRespMsg(){
		return this.head.get(CommonFields.RESP_MSG);
	}
}
