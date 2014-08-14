function Form(data){
	var buf = new StringBuffer();
	buf.append('<form class="form-inline form-horizontal"><fieldset><legend>'+data.title+'</legend>');
	$(data.columns).each(function(){
		buf.append('<div class="control-group"> \
				<label class="control-label" for="'+this.id+'">'+this.name+'</label>\
				<div class="controls">');
		if(this.type === 'text'){
			buf.append('<input id="'+this.id+'" type="'+this.type+'" /></div></div>');
		}else{
			buf.append(this.text);
		}
	});
	buf.append('</fieldset></form>');
	this.toString = function(){
		return buf.toString();
	};
};

var demo = {
		title:'添加用户',
		columns:[
		         {
		        	 id:'loginId'
		        	,name:'账户'
		        	,type:'text'
		        	,text :'<select><option>选项一</option></select>'
		         },
		         {
		        	 id:'password'
		        		 ,name:'账户'
		        			 ,type:'select'
		        				 ,text :'<select><option>选项一</option></select>'
		         }
		         ]	
};



new Form({
	title:'添加用户',
	columns:[
	         {
	        	 id:'loginId'
	        	,name:'账户'
	        	,type:'text'
	        	,text :'<select><option>选项一</option></select>'
	         },
	         {
	        	 id:'password'
	        		 ,name:'账户'
	        			 ,type:'select'
	        				 ,text :'<select><option>选项一</option></select>'
	         }
	         ]	
}).toString();

	
	
