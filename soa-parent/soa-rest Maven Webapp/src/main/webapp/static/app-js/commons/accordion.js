//创建折叠组件
$("#soa-acc").collapse({
	toggle : true,
	parent : '#soa-acc'
});
// 为触发元素添加单击事件，在回调函数里打开折叠元素，此时由于上面已经指定了parent属性，所以Bootstrap会为我们自动将其他折叠组件关闭
var _this;
var activeLi = $('.nav-list li[class="active"]').get(0);
$(document).on('click', '.accordion-heading', function() {
	if (_this) {
		$(_this).nextAll().eq(0).collapse("hide");
	}
	_this = this;
	$(_this).nextAll().eq(0).collapse("show");
})
.on('click','.nav-list li',function(){
		if(activeLi)$(activeLi).removeClass('active');
		activeLi = this;
		$(activeLi).addClass('active');
});

