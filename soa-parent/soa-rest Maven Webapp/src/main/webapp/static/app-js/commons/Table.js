
function Table(meta){
	var buf = new StringBuffer();
	buf.append('<table class="table table-hover table-bordered table-condensed table-striped"><thead><tr>');
	var carr = new Array();
	$(meta.columns).each(function(){
		buf.append('<th>').append(this.title).append('</th>');
		carr.push(this.column);
	});
	buf.append('</tr></thead><tbody>');
	$(meta.data).each(function(){
		var _this = this;
		buf.append("<tr>");
		$(carr).each(function(){
			buf.append('<td>').append(_this[this]).append('</td>');
		});
		buf.append("</tr>");
	});
	buf.append('</tbody></table>');
	this.toString = function(){
		return buf.toString();
	};
};
	
var demo = {
		columns:[
				    {
				    	title : '编号',
				    	column : 'id',
				    },{
				    	title : '姓名',
				    	column : 'name',
				    }
				   ],
				 data:[{
			 			id:'123',
			 			name:'张三'
		 				},{
	 					id:'456',
			 			name:'李四'
		 				}]
			}  ;
