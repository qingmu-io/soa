!function($, window, undefined) {
	var Request = window.Request || {};
	window.Requset = Request;
	//ajax 请求
	/*
	 * myUrl url请求
	 * myData 数据格式 {}
	 * sufu 成功请求后返回的结果
	 * isAsync 是否是异步请求
	 */
	Request.ajax = function(myUrl, myData, sufn,isAsync) {
		$.ajax({
			url : myUrl,
			data : myData,
			cache:false,
			async:isAsync,
			type:'post',
			dataType : 'json',
			success : sufn,
			error : function(jqXHR, textStatus, errorThrown){
				
				}
		});
	};
	

}(jQuery, window);