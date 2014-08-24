
!function($, window, undefined) {
window.Validata = {};
	/**
	 * 此方法是通用验证对象是否为空，如果不为空 则会调用预定义好的各自的判断
	 * @param _this input对象
	 * @param validateFn  验证function
	 * @returns {Boolean}  验证通过返回true 未通过返回false
	 */
	Validata.vali = function(_this,validateFn){
		if(!_this)return true;
		if(_this.nodeName == 'RADIO') return true;
		var val = $(_this).val();
		if(val =='' || undefined == val || null == val){
			$($(_this).next()).html($($($(_this).parent()).prev()).html()+"不能为空！");
			$($($(_this).parent()).parent()).removeClass("success");
			$($($(_this).parent()).parent()).addClass("error");
			return false;
		}else{
			var err = validateFn(_this,val);
			if(err){
				$($($(_this).parent()).parent()).removeClass("success");
				$($(_this).next()).html($($($(_this).parent()).prev()).html()+err+"！");
				$($($(_this).parent()).parent()).addClass("error");
				return false;
			}else{
				$($(_this).next()).html($($($(_this).parent()).prev()).html()+"填写正确！");
				$($($(_this).parent()).parent()).removeClass("error");
				$($($(_this).parent()).parent()).addClass("success");
				return true;
			}
		}
	};
	/**
	 * 绑定表单验证
	 * @param form
	 */
	Validata.bind = function(form){
		$(Util.getFormName(form)).each(function(){
			$(document).on('blur','input[name="'+this+'"]',function(){
				Validata.vali(this,validata);
			});
		});
	};
	/**
	 * 立即进行表单的验证操作
	 * 如果全部验证通过返回true  否则返回false
	 */
	Validata.valiForm = function(form){
		var isPass = true;
		$(Util.getFormName(form)).each(function(){
			var res = Validata.vali($('input[name="'+this+'"]').get(0), validata);
			if(!res){
				isPass = false;
			};
		});
		return isPass;
	};
	
	
}(jQuery, window);
	
	