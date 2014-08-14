!function($,window,undefined){
	/**
	 * 替换F5 和 Backspace 键盘操作事件
	 */
	$(document).on(
			'keydown',
			'body',
			function() {
				/*刷新按钮*/
				if (event.keyCode == 116) {
					event.returnValue = false;
					return false;
				}
				var elem = event.relatedTarget || event.srcElement || event.target
						|| event.currentTarget;
				if (event.keyCode == 8) {// 判断按键为backSpace键
					 elem = event.srcElement || event.currentTarget;
					var name = elem.nodeName;
					if (name != 'INPUT' && name != 'TEXTAREA') {
						return _stopIt(event);
					}
					var type_e = elem.type.toUpperCase();
					if (name == 'INPUT'
							&& (type_e != 'TEXT' && type_e != 'TEXTAREA'
									&& type_e != 'PASSWORD' && type_e != 'FILE')) {
						return _stopIt(event);
					}
					if (name == 'INPUT'&& (elem.readOnly|| elem.disabled)) {
						return _stopIt(event);
					}
				}
			});
	/**
	 * 设置键盘事件为false
	 * 
	 * @param e
	 * @returns {Boolean}
	 */
	function _stopIt(e) {
		if (e.returnValue) {
			e.returnValue = false;
		}
		if (e.preventDefault) {
			e.preventDefault();
		}
		return false;
	}
}(jQuery,window);