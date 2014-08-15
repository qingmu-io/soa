!function($, window, undefined) {
	var Request = {};
	window.Request  =  window.Request || Request;
	//ajax 请求
	/*
	 * myUrl url请求
	 * myData 数据格式 {}
	 * sufu 成功请求后返回的结果
	 * isAsync 是否是异步请求 true 异步 false 同步
	 */
	Request.sync_ajax_post = function(myUrl, myData, sufn) {
		this.request(myUrl, myData, sufn, "post", false);
	};
	/**
	 * 异步post请求
	 */
	Request.async_ajax_post = function(myUrl, myData, sufn) {
		this.request(myUrl, myData, sufn, "post", true);
	};
	
	/**
	 * 同步get请求
	 */
	Request.sync_ajax_get = function(url,param,sufn){
		this.request(url, param, sufn, "get", false);
	};
	/**
	 * get 异步请求
	 */
	Request.async_ajax_get = function(myUrl, myData,sufn){
		this.request(myUrl, myData, sufn, "get", true);
	};
	
	
	Request.request = function(myUrl, myData,sufn,method,isAsync) {
		$.ajax({
			url : myUrl,
			data : myData,
			cache:false,
			async:isAsync,
			type:method,
			dataType : 'json',
			success : sufn,
			error : function(jqXHR, textStatus, errorThrown){
				
				}
		});
	};
}(jQuery, window);